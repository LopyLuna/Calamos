package uwu.lopyluna.calamos.datagen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import uwu.lopyluna.calamos.CalamosMod;

public class ModEnchantmentProvider {

    //Wings
    public static final ResourceKey<Enchantment> FAST_FLIGHT = create("fast_flight");
    public static final ResourceKey<Enchantment> FLIGHT_CHARGE = create("flight_charge");
    public static final ResourceKey<Enchantment> SAVING_GRACE = create("saving_grace");
    public static final ResourceKey<Enchantment> FELLING = create("felling");

    private static ResourceKey<Enchantment> create(String name) {
        return ResourceKey.create(Registries.ENCHANTMENT, CalamosMod.asResource(name));
    }

    private static void register(BootstrapContext<Enchantment> context) {
        HolderGetter<DamageType> damageTypes = context.lookup(Registries.DAMAGE_TYPE);
        HolderGetter<Enchantment> enchantments = context.lookup(Registries.ENCHANTMENT);
        HolderGetter<Item> items = context.lookup(Registries.ITEM);
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);
        HolderSet<Enchantment> empty = HolderSet.empty();
        DataComponentMap noEffects = DataComponentMap.builder().build();
        register(context, FAST_FLIGHT, Enchantment.enchantment(Enchantment.definition(
                items.getOrThrow(ModTags.curiosItemTag("wings")),
                1,
                3,
                Enchantment.constantCost(25),
                Enchantment.constantCost(50),
                8,
                EquipmentSlotGroup.CHEST
        )));
        register(context, FLIGHT_CHARGE, Enchantment.enchantment(Enchantment.definition(
                items.getOrThrow(ModTags.curiosItemTag("wings")),
                1,
                3,
                Enchantment.constantCost(25),
                Enchantment.constantCost(50),
                8,
                EquipmentSlotGroup.CHEST
        )));
        register(context, SAVING_GRACE, Enchantment.enchantment(Enchantment.definition(
                items.getOrThrow(ModTags.curiosItemTag("wings")),
                1,
                3,
                Enchantment.constantCost(25),
                Enchantment.constantCost(50),
                8,
                EquipmentSlotGroup.CHEST
        )));
        register(context, FELLING, Enchantment.enchantment(Enchantment.definition(
                items.getOrThrow(ItemTags.AXES), 1, 1,
                Enchantment.constantCost(25),
                Enchantment.constantCost(50),
                8,
                EquipmentSlotGroup.HAND
        )));
    }

    private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.EnchantmentDefinition definition, HolderSet<Enchantment> exclusiveSet, DataComponentMap effects) {
        Component description = Component.translatable(key.location().toLanguageKey());
        context.register(key, new Enchantment(description, definition, exclusiveSet, effects));
    }

    private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        context.register(key, builder.build(key.location()));
    }

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        register(context);
    }
}
