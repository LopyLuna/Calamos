package uwu.lopyluna.calamos.core.items.equipment.tool;

import net.minecraft.tags.TagKey;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import uwu.lopyluna.calamos.datagen.ModTags;
import uwu.lopyluna.calamos.core.tag.ModItemTags;

import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public enum CalamosTiers implements Tier {
    COPPER(ModTags.modBlockTag("incorrect_for_copper_tool"), 131, 4.0F, 1.0F, 5, () -> Ingredient.of(ModItemTags.COPPER_TOOL_MATERIALS)),
    PLATINUM(ModTags.modBlockTag("incorrect_for_platinum_tool"), 450, 5.0F, 2.0F, 7, () -> Ingredient.of(ModItemTags.PLATINUM_TOOL_MATERIALS)),
    METEORITE(ModTags.modBlockTag("incorrect_for_meteorite_tool"), 5031, 9.0F, 8.5F, 14, () -> Ingredient.of(ModItemTags.METEORITE_TOOL_MATERIALS)),
    VOLCANITE(ModTags.modBlockTag("incorrect_for_volcanite_tool"), 10264, 10.0F, 9.5F, 21, () -> Ingredient.of(ModItemTags.VOLCANITE_TOOL_MATERIALS)),
    ;

    private final TagKey<Block> incorrectBlocksForDrops;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> repairIngredient;
    
    CalamosTiers(TagKey<Block> incorrectBlocksForDrops, int pUses, float pSpeed, float pDamage, int pEnchantmentValue, Supplier<Ingredient> pRepairIngredient) {
        this.incorrectBlocksForDrops = incorrectBlocksForDrops;
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
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return this.incorrectBlocksForDrops;
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
