package uwu.lopyluna.calamos.datagen;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import uwu.lopyluna.calamos.datagen.recipe.CraftingProvider;
import uwu.lopyluna.calamos.datagen.recipe.CuttingProvider;
import uwu.lopyluna.calamos.datagen.recipe.FurnaceProvider;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }
    
    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        CraftingProvider.register(pRecipeOutput);
        FurnaceProvider.register(pRecipeOutput);
        CuttingProvider.register(pRecipeOutput);
    }
}
