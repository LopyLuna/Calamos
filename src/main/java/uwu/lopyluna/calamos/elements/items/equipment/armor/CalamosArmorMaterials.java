package uwu.lopyluna.calamos.elements.items.equipment.armor;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import uwu.lopyluna.calamos.elements.tag.ModItemTags;

import java.util.function.Supplier;

public enum CalamosArmorMaterials implements ArmorMaterial {
        COPPER("copper", 5, new int[] { 1, 3, 4, 2 }, 15, () -> null, 0.0F, 0.0F, () -> Ingredient.of(ModItemTags.COPPER_TOOL_MATERIALS)),
        PLATINUM("platinum", 15, new int[] { 2, 5, 6, 2 }, 9, () -> null, 0.0F, 0.0F, () -> Ingredient.of(ModItemTags.PLATINUM_TOOL_MATERIALS)),
    ;

    private static final int[] MAX_DAMAGE_ARRAY = new int[] { 13, 15, 16, 11 };
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final Supplier<SoundEvent> soundEvent;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairMaterial;

    CalamosArmorMaterials(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, Supplier<SoundEvent> soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type pType) {
        return MAX_DAMAGE_ARRAY[pType.getSlot().getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type pType) {
        return this.damageReductionAmountArray[pType.getSlot().getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.soundEvent.get();
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
