package uwu.lopyluna.calamos.elements.items.tool;

import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import uwu.lopyluna.calamos.elements.ModItems;

import java.util.function.Supplier;
@SuppressWarnings("deprecation")
public enum CalamosTiers implements Tier {
    METEORITE(5, 5031, 9.0F, 5.5F, 13, () -> Ingredient.of(ModItems.METEORITE_INGOT.get())),
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
