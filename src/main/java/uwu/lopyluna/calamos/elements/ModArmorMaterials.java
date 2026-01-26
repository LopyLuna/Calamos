package uwu.lopyluna.calamos.elements;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.core.tag.ModItemTags;
import uwu.lopyluna.calamos.utilities.ModUtils;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {
    public static final DeferredRegister<ArmorMaterial> MATERIALS = ModUtils.createRegister(Registries.ARMOR_MATERIAL);

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> COPPER = register("copper", new int[] { 1, 3, 4, 2, 6 }, 15, SoundEvents.ARMOR_EQUIP_IRON, () -> Ingredient.of(ModItemTags.COPPER_TOOL_MATERIALS), 0.0F, 0.0F);
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> PLATINUM = register("platinum", new int[] { 2, 5, 6, 2, 8 }, 9, SoundEvents.ARMOR_EQUIP_GOLD, () -> Ingredient.of(ModItemTags.PLATINUM_TOOL_MATERIALS), 0.0F, 0.0F);

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> SPECTRE = register("spectre", new int[] { 1, 10, 14, 18, 20 }, 12, SoundEvents.ARMOR_EQUIP_NETHERITE, () -> Ingredient.of(ModItemTags.INGOTS_ECTOLIGHT), 0.0F, 0.0F);


    private static DeferredHolder<ArmorMaterial, ArmorMaterial> register(String name, int[] defense, int enchantmentValue, Holder<SoundEvent> equipSound, Supplier<Ingredient> repairIngredient, float toughness, float knockbackResistance) {
        List<ArmorMaterial.Layer> list = List.of(new ArmorMaterial.Layer(CalamosMod.asResource(name)));
        EnumMap<ArmorItem.Type, Integer> defenceMap = Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
            map.put(ArmorItem.Type.BOOTS, defense[0]);
            map.put(ArmorItem.Type.LEGGINGS, defense[1]);
            map.put(ArmorItem.Type.CHESTPLATE, defense[2]);
            map.put(ArmorItem.Type.HELMET, defense[3]);
            map.put(ArmorItem.Type.BODY, defense[4]);
        });
        return MATERIALS.register(name, () -> new ArmorMaterial(defenceMap, enchantmentValue, equipSound, repairIngredient, list, toughness, knockbackResistance));
    }

    public static void staticInit() {}
}
