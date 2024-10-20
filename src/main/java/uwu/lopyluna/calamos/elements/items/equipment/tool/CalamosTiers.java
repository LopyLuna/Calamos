package uwu.lopyluna.calamos.elements.items.equipment.tool;

import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.SpectralArrowItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import uwu.lopyluna.calamos.elements.tag.ModItemTags;

import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public enum CalamosTiers implements Tier {
    COPPER(1, 131, 4.0F, 1.0F, 5, () -> Ingredient.of(ModItemTags.COPPER_TOOL_MATERIALS)),
    PLATINUM(2, 450, 5.0F, 2.0F, 7, () -> Ingredient.of(ModItemTags.PLATINUM_TOOL_MATERIALS)),
    METEORITE(5, 5031, 9.0F, 8.5F, 14, () -> Ingredient.of(ModItemTags.METEORITE_TOOL_MATERIALS)),
    VOLCANITE(5, 10264, 10.0F, 9.5F, 21, () -> Ingredient.of(ModItemTags.VOLCANITE_TOOL_MATERIALS)),
    ;

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> repairIngredient;
    
    CalamosTiers(int pLevel, int pUses, float pSpeed, float pDamage, int pEnchantmentValue, Supplier<Ingredient> pRepairIngredient) {
        this.level = pLevel;
        this.uses = pUses;
        this.speed = pSpeed;
        this.damage = pDamage;
        this.enchantmentValue = pEnchantmentValue;
        this.repairIngredient = new LazyLoadedValue<>(pRepairIngredient);
    }
    @Override
    public int getUses() {
        return this.uses;
    }
    
    @Override
    public float getSpeed() {
        return this.speed;
    }
    
    @Override
    public float getAttackDamageBonus() {
        return this.damage;
    }
    
    @Override
    public int getLevel() {
        return this.level;
    }
    
    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }
    
    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
