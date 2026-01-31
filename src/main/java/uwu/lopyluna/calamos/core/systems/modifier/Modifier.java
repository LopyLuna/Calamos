package uwu.lopyluna.calamos.core.systems.modifier;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredHolder;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.CalamosRegistries;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
//TODO: Affect Projectiles
public record Modifier(List<Entry> attributeModifiers, Ingredient supportedItems, String assetName) {

    public static final Codec<Modifier> DIRECT_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Entry.CODEC.listOf().fieldOf("modifiers").forGetter(Modifier::attributeModifiers),
            Ingredient.CODEC.fieldOf("supported_items").forGetter(Modifier::supportedItems),
            Codec.STRING.fieldOf("asset_name").forGetter(Modifier::assetName)
    ).apply(instance, Modifier::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, Modifier> DIRECT_STREAM_CODEC = StreamCodec.composite(
            Entry.STREAM_CODEC.apply(ByteBufCodecs.list()), Modifier::attributeModifiers,
            Ingredient.CONTENTS_STREAM_CODEC, Modifier::supportedItems,
            ByteBufCodecs.STRING_UTF8, Modifier::assetName,
            Modifier::new
    );

    public static final Codec<Holder<Modifier>> CODEC = RegistryFixedCodec.create(CalamosRegistries.MODIFIER);
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<Modifier>> STREAM_CODEC = ByteBufCodecs.holderRegistry(CalamosRegistries.MODIFIER);

    public record Entry(Holder<Attribute> attribute, AttributeModifier modifier, EquipmentSlotGroup slotGroup, List<String> curioSlots) {
        public static final Codec<Entry> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
                Attribute.CODEC.fieldOf("type").forGetter(Entry::attribute),
                AttributeModifier.MAP_CODEC.forGetter(Entry::modifier),
                EquipmentSlotGroup.CODEC.fieldOf("slots").forGetter(Entry::slotGroup),
                Codec.STRING.listOf().optionalFieldOf("curio_slots", List.of()).forGetter(Entry::curioSlots)
        ).apply(instance, Entry::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, Entry> STREAM_CODEC = StreamCodec.composite(
                Attribute.STREAM_CODEC, Entry::attribute,
                AttributeModifier.STREAM_CODEC, Entry::modifier,
                EquipmentSlotGroup.STREAM_CODEC, Entry::slotGroup,
                ByteBufCodecs.STRING_UTF8.apply(ByteBufCodecs.list()), Entry::curioSlots,
                Entry::new);
    }

    public boolean supports(ItemStack stack) {
        return this.supportedItems.test(stack);
    }

    public static Builder builder(String id) {
        return new Builder(id);
    }

    public static class Builder {
        private final ImmutableList.Builder<Entry> entries = ImmutableList.builder();
        private Ingredient supportedItems = Ingredient.EMPTY;
        private String id;
        private String assetName;

        private EquipmentSlotGroup currentSlotGroup = EquipmentSlotGroup.ANY;

        private List<String> currentCurioSlots = List.of();

        Builder(String id) {
            this.id = id;
            this.assetName = id;
        }

        public Builder prefix(String prefix) {
            this.id = prefix + id;
            return this;
        }

        public Builder suffix(String suffix) {
            this.id = id + suffix;
            return this;
        }

        public Builder supportTag(TagKey<Item> tagKey) {
            this.supportedItems = Ingredient.of(tagKey);
            return this;
        }

        public Builder setSlot(EquipmentSlotGroup slotGroup) {
            this.currentSlotGroup = slotGroup;
            return this;
        }

        public Builder setSlot(String... curioSlots) {
            this.currentCurioSlots = Arrays.asList(curioSlots);
            return this;
        }

        @SafeVarargs
        public final Builder supportItems(Holder<Item>... items) {
            this.supportedItems = Ingredient.of(Arrays.stream(items).map(Holder::value).toArray(Item[]::new));
            return this;
        }

        public Builder add(Holder<Attribute> attribute, AttributeModifier modifier) {
            this.entries.add(new Entry(attribute, modifier, this.currentSlotGroup, this.currentCurioSlots));
            return this;
        }

        public Builder add(Holder<Attribute> attribute, double amount, AttributeModifier.Operation operation) {
            this.entries.add(new Entry(attribute, modifier(attribute, amount, operation), this.currentSlotGroup, this.currentCurioSlots));
            return this;
        }

        private AttributeModifier modifier(Holder<Attribute> attribute, double amount, AttributeModifier.Operation operation) {
            String name = attribute.unwrapKey().map(key -> key.location().toString().replace(":", "_") + "/").orElse("");
            ResourceLocation location = CalamosMod.asResource("modifier/" + name + assetName);
            return new AttributeModifier(location, amount, operation);
        }

        public Builder name(String assetName) {
            this.assetName = assetName;
            return this;
        }

        @SafeVarargs
        public final Builder apply(Function<Builder, Builder>... functions) {
            Builder builder = this;
            for (Function<Builder, Builder> function : functions) {
                builder = function.apply(builder);
            }
            return builder;
        }

        public Modifier build() {
            return new Modifier(this.entries.build(), supportedItems, assetName);
        }

        public DeferredHolder<Modifier, Modifier> register(BiFunction<String, Supplier<? extends Modifier>, DeferredHolder<Modifier, Modifier>> function) {
            return function.apply(id, this::build);
        }
    }
}
