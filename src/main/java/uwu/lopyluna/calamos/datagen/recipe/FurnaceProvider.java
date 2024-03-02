package uwu.lopyluna.calamos.datagen.recipe;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.elements.ModBlocks;
import uwu.lopyluna.calamos.elements.ModDecorativeBlocks;
import uwu.lopyluna.calamos.elements.ModItems;

import static uwu.lopyluna.calamos.utilities.ModUtils.secondsToTicks;

public class FurnaceProvider {
    public static void register(RecipeOutput consumer) {
        smelting(consumer);
        blasting(consumer);
        //smoking(consumer);
    }
    private static ResourceLocation location(String path, int type) {
        String typeStr = switch (type) {case 1 -> "blasting";case 2 -> "smoking";default -> "smelting";};
        return new ResourceLocation(CalamosMod.MODID, typeStr + "/" + path);
    }
    private static void smelting(RecipeOutput consumer) {
        smeltingRecipe(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.METEORITE.asItem(), ModDecorativeBlocks.COBBLED_METEORITE.asItem(), 0.1f, secondsToTicks(10)).save(consumer, location("meteorite_from_cobbled_meteorite", 0));
        smeltingRecipe(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.SMOOTH_METEORITE.asItem(), ModDecorativeBlocks.METEORITE.asItem(), 0.1f, secondsToTicks(10)).save(consumer, location("smooth_meteorite_from_meteorite", 0));
        smeltingRecipe(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.SMOOTH_DEEPSLATE.asItem(), Items.DEEPSLATE, 0.1f, secondsToTicks(10)).save(consumer, location("smooth_deepslate_from_deepslate", 0));
    }
    
    private static void blasting(RecipeOutput consumer) {
        smeltAndBlast(RecipeCategory.MISC, ModItems.METEORITE_INGOT.get(), ModItems.RAW_METEORITE.get(), 0.65f, secondsToTicks(120), consumer);
        smeltAndBlast(RecipeCategory.MISC, ModItems.URANIUM_INGOT.get(), ModItems.RAW_URANIUM.get(), 0.65f, secondsToTicks(120), consumer);
        blastingRecipe(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.SMOOTH_NETHERRACK.asItem(), Items.NETHERRACK, 0.1f, secondsToTicks(10)).save(consumer, location("smooth_netherrack_from_netherrack", 1));
    }
    
    private static void smoking(RecipeOutput consumer) {
    
    }
    
    
    
    public static void smeltAndBlast(RecipeCategory category, Item result, ItemLike input, float xp, int time, RecipeOutput consumer) {
        int blastTime = time / 2;
        String inputName = input.asItem().toString().split(":")[1];
        String resultName = result.toString().split(":")[1];
        smeltingRecipe(category, result, input, xp, time).save(consumer, location(resultName + "_from_" + inputName, 0));
        blastingRecipe(category, result, input, xp, blastTime).save(consumer, location(resultName + "_from_" + inputName, 1));
    }
    
    public static void smeltAndSmoke(RecipeCategory category, Item result, ItemLike input, float xp, int time, RecipeOutput consumer) {
        int smokeTime = time / 2;
        String inputName = input.asItem().toString().split(":")[1];
        String resultName = result.toString().split(":")[1];
        smeltingRecipe(category, result, input, xp, time).save(consumer, location(resultName + "_from_" + inputName, 0));
        smokingRecipe(category, result, input, xp, smokeTime).save(consumer, location(resultName + "_from_" + inputName, 2));
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
