package uwu.lopyluna.calamos.datagen.recipe;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.elements.ModBlocks;

import static uwu.lopyluna.calamos.utilities.ModUtils.secondsToTicks;

public class FurnaceProvider {
    
    public static void register(RecipeOutput consumer) {
        smelting(consumer);
        //blasting(consumer);
        //smoking(consumer);
    }
    private static ResourceLocation location(String path, int type) {
        String typeStr = switch (type) {case 1 -> "blasting";case 2 -> "smoking";default -> "smelting";};
        return new ResourceLocation(CalamosMod.MODID, typeStr + "/" + path);
    }
    private static void smelting(RecipeOutput consumer) {
        smeltingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.METEORITE.asItem(), ModBlocks.COBBLED_METEORITE.asItem(), 0.1f, secondsToTicks(10)).save(consumer, location("meteorite_from_cobbled_meteorite", 0));
        smeltingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_METEORITE.asItem(), ModBlocks.METEORITE.asItem(), 0.1f, secondsToTicks(10)).save(consumer, location("smooth_meteorite_from_meteorite", 0));
    }
    
    private static void blasting(RecipeOutput consumer) {
    
    }
    
    private static void smoking(RecipeOutput consumer) {
    
    }
    
    
    
    public static void smeltAndBlast(RecipeCategory category, Item result, ItemLike input, float xp, int time, RecipeOutput consumer) {
        int blastTime = time / 2;
        smeltingRecipe(category, result, input, xp, time).save(consumer, result.toString());
        blastingRecipe(category, result, input, xp, blastTime).save(consumer, result.toString());
    }
    
    public static void smeltAndSmoke(RecipeCategory category, Item result, ItemLike input, float xp, int time, RecipeOutput consumer) {
        int smokeTime = time / 2;
        smeltingRecipe(category, result, input, xp, time).save(consumer, result.toString());
        smokingRecipe(category, result, input, xp, smokeTime).save(consumer, result.toString());
    }
    
    public static SimpleCookingRecipeBuilder smeltingRecipe(RecipeCategory category, Item result, ItemLike input, float xp, int time) {
        String inputName = input.asItem().toString().split(":")[1];
        return SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), category, result, xp, time).unlockedBy("has_" + inputName, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    
    public static SimpleCookingRecipeBuilder blastingRecipe(RecipeCategory category, Item result, ItemLike input, float xp, int time) {
        String inputName = input.asItem().toString().split(":")[1];
        return SimpleCookingRecipeBuilder.blasting(Ingredient.of(input), category, result, xp, time).unlockedBy("has_" + inputName, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    
    public static SimpleCookingRecipeBuilder smokingRecipe(RecipeCategory category, Item result, ItemLike input, float xp, int time) {
        String inputName = input.asItem().toString().split(":")[1];
        return SimpleCookingRecipeBuilder.smoking(Ingredient.of(input), category, result, xp, time).unlockedBy("has_" + inputName, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    
    
}
