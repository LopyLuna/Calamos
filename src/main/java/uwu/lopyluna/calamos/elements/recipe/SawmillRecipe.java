package uwu.lopyluna.calamos.elements.recipe;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import uwu.lopyluna.calamos.elements.ModBlocks;
import uwu.lopyluna.calamos.elements.ModRecipes;

public class SawmillRecipe extends ModSingleItemRecipe {
    public SawmillRecipe(String pGroup, Ingredient pIngredient, ItemStack pResult) {
        super(ModRecipes.SAW_MILLING.get(), ModRecipes.SAW_MILLING_SER.get(), pGroup, pIngredient, pResult);
    }
    
    /**
     * Used to check if a recipe matches current crafting inventory
     */
    @Override
    public boolean matches(Container pInv, Level pLevel) {
        return this.ingredient.test(pInv.getItem(0));
    }
    
    
    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.SAWMILL);
    }
}
