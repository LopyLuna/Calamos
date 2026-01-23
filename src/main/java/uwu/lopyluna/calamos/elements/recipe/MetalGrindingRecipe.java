package uwu.lopyluna.calamos.elements.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import uwu.lopyluna.calamos.elements.ModBlocks;
import uwu.lopyluna.calamos.elements.ModRecipes;

public class MetalGrindingRecipe extends ModSingleItemRecipe {
    public MetalGrindingRecipe(String pGroup, Ingredient pIngredient, ItemStack pResult) {
        super(ModRecipes.METAL_GRINDING.get(), ModRecipes.METAL_GRINDING_SER.get(), pGroup, pIngredient, pResult);
    }

    @Override
    public boolean matches(SingleRecipeInput input, Level level) {
        return this.ingredient.test(input.getItem(0));
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.METAL_GRINDER);
    }
}
