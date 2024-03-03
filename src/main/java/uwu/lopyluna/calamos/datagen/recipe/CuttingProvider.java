package uwu.lopyluna.calamos.datagen.recipe;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.datagen.recipe.builder.ModSingleItemRecipeBuilder;
import uwu.lopyluna.calamos.elements.ModDecorativeBlocks;

public class CuttingProvider {
    private static ResourceLocation location(String path, int type) {
        String typeStr = switch (type) {case 1 -> "saw_milling";case 2 -> "metal_grinding";default -> "stonecutting";};
        return new ResourceLocation(CalamosMod.MODID, typeStr + "/" + path);
    }
    public static void register(RecipeOutput consumer) {
        recipesStonecutting(consumer);
        recipesSawmilling(consumer);
        recipesMetalGrinding(consumer);
    }
    
    private static void recipesStonecutting(RecipeOutput consumer) {
    }
    
    private static void recipesSawmilling(RecipeOutput consumer) {
        addSawMilling(consumer, ModDecorativeBlocks.LARGE_ACACIA_PLANKS, 1, Items.ACACIA_PLANKS, RecipeCategory.BUILDING_BLOCKS).save(consumer, location("large_acacia_planks", 1));
        addSawMilling(consumer, ModDecorativeBlocks.LARGE_BIRCH_PLANKS, 1, Items.BIRCH_PLANKS, RecipeCategory.BUILDING_BLOCKS).save(consumer, location("large_birch_planks", 1));
        addSawMilling(consumer, ModDecorativeBlocks.LARGE_BAMBOO_PLANKS, 1, Items.BAMBOO_PLANKS, RecipeCategory.BUILDING_BLOCKS).save(consumer, location("large_bamboo_planks", 1));
        addSawMilling(consumer, ModDecorativeBlocks.LARGE_CRIMSON_PLANKS, 1, Items.CRIMSON_PLANKS, RecipeCategory.BUILDING_BLOCKS).save(consumer, location("large_crimson_planks", 1));
        addSawMilling(consumer, ModDecorativeBlocks.LARGE_DARK_OAK_PLANKS, 1, Items.DARK_OAK_PLANKS, RecipeCategory.BUILDING_BLOCKS).save(consumer, location("large_dark_oak_planks", 1));
        addSawMilling(consumer, ModDecorativeBlocks.LARGE_JUNGLE_PLANKS, 1, Items.JUNGLE_PLANKS, RecipeCategory.BUILDING_BLOCKS).save(consumer, location("large_jungle_planks", 1));
        addSawMilling(consumer, ModDecorativeBlocks.LARGE_OAK_PLANKS, 1, Items.OAK_PLANKS, RecipeCategory.BUILDING_BLOCKS).save(consumer, location("large_oak_planks", 1));
        addSawMilling(consumer, ModDecorativeBlocks.LARGE_SPRUCE_PLANKS, 1, Items.SPRUCE_PLANKS, RecipeCategory.BUILDING_BLOCKS).save(consumer, location("large_spruce_planks", 1));
        addSawMilling(consumer, ModDecorativeBlocks.LARGE_WARPED_PLANKS, 1, Items.WARPED_PLANKS, RecipeCategory.BUILDING_BLOCKS).save(consumer, location("large_warped_planks", 1));
    }
    
    private static void recipesMetalGrinding(RecipeOutput consumer) {
    }
    
    
    
    private static SingleItemRecipeBuilder addStoneCutting(RecipeOutput consumer, ItemLike result, int count, ItemLike input, RecipeCategory pCategory) {
        String name = input.asItem().toString().split(":")[1];
        return SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), pCategory, result, count).unlockedBy("has_" + name, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    private static ModSingleItemRecipeBuilder addSawMilling(RecipeOutput consumer, ItemLike result, int count, ItemLike input, RecipeCategory pCategory) {
        String name = input.asItem().toString().split(":")[1];
        return ModSingleItemRecipeBuilder.sawMilling(Ingredient.of(input), pCategory, result, count).unlockedBy("has_" + name, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    private static ModSingleItemRecipeBuilder addMetalGrinding(RecipeOutput consumer, ItemLike result, int count, ItemLike input, RecipeCategory pCategory) {
        String name = input.asItem().toString().split(":")[1];
        return ModSingleItemRecipeBuilder.metalGrinding(Ingredient.of(input), pCategory, result, count).unlockedBy("has_" + name, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    
}
