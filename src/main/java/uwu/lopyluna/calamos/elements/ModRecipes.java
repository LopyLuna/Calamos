package uwu.lopyluna.calamos.elements;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.core.recipe.MetalGrindingRecipe;
import uwu.lopyluna.calamos.core.recipe.ModSingleItemRecipe;
import uwu.lopyluna.calamos.core.recipe.SawmillRecipe;
import uwu.lopyluna.calamos.utilities.ModUtils;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = ModUtils.createRegister(Registries.RECIPE_SERIALIZER);
    public static final DeferredRegister<RecipeType<?>> RECIPES = ModUtils.createRegister(Registries.RECIPE_TYPE);
    
    public static final DeferredHolder<RecipeSerializer<?>, ModSingleItemRecipe.Serializer<SawmillRecipe>> SAW_MILLING_SER = SERIALIZERS.register("sawmilling", () -> new ModSingleItemRecipe.Serializer<>(SawmillRecipe::new));
    public static final DeferredHolder<RecipeType<?>, RecipeType<SawmillRecipe>> SAW_MILLING = registerRecipeType("sawmilling");
    
    public static final DeferredHolder<RecipeSerializer<?>, ModSingleItemRecipe.Serializer<MetalGrindingRecipe>> METAL_GRINDING_SER = SERIALIZERS.register("metal_grinding", () -> new ModSingleItemRecipe.Serializer<>(MetalGrindingRecipe::new));
    public static final DeferredHolder<RecipeType<?>, RecipeType<MetalGrindingRecipe>> METAL_GRINDING = registerRecipeType("metal_grinding");
    
    
    static <T extends Recipe<?>> DeferredHolder<RecipeType<?>, RecipeType<T>> registerRecipeType(final String pIdentifier) {
        return RECIPES.register(pIdentifier, () -> new RecipeType<>() {
            public String toString() {
                return pIdentifier;
            }
        });
    }
    public static void staticInit() {}
}
