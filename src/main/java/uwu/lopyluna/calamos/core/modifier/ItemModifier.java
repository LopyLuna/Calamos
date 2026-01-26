package uwu.lopyluna.calamos.core.modifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import uwu.lopyluna.calamos.CalamosRegistries;

import java.util.function.Consumer;

public record ItemModifier(Holder<Modifier> modifier, boolean showInTooltip) implements TooltipProvider {
    public static final Codec<ItemModifier> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Modifier.CODEC.fieldOf("modifier").forGetter(ItemModifier::modifier),
            Codec.BOOL.optionalFieldOf("show_in_tooltip", true).forGetter(ItemModifier::showInTooltip)
    ).apply(instance, ItemModifier::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, ItemModifier> STREAM_CODEC = StreamCodec.composite(
            Modifier.STREAM_CODEC, ItemModifier::modifier,
            ByteBufCodecs.BOOL, ItemModifier::showInTooltip,
            ItemModifier::new
    );

    @Override
    public void addToTooltip(Item.TooltipContext ctx, Consumer<Component> tooltip, TooltipFlag flag) {
        if (this.showInTooltip) {
            tooltip.accept(Component.translatable("calamos.tooltip.modifier_stats").withStyle(ChatFormatting.GOLD));
            this.modifier.value().attributeModifiers().forEach(attributeModifier -> tooltip.accept(CommonComponents.space().append(attributeTooltip(attributeModifier, flag))));
        }
    }

    public ItemModifier withTooltip(boolean showInTooltip) {
        return new ItemModifier(this.modifier, showInTooltip);
    }

    private MutableComponent attributeTooltip(Modifier.Entry modifier, TooltipFlag flag) {
        return modifier.attribute().value().toComponent(modifier.modifier(), flag);
    }

    public Component modifierName(Component itemName) {
        ResourceLocation loc = CalamosRegistries.MODIFIER_REGISTRY.getKey(this.modifier.value());
        if (loc == null) {
            return itemName;
        }
        return Component.translatable(loc.withPath(this.modifier.value().assetName()).toLanguageKey("modifier"), itemName);
    }

    public boolean supports(ItemStack stack) {
        return this.modifier.value().supportedItems().test(stack);
    }
}
