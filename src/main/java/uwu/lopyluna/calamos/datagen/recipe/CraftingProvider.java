package uwu.lopyluna.calamos.datagen.recipe;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.elements.ModBlocks;
import uwu.lopyluna.calamos.elements.ModItems;

public class CraftingProvider {
    
    public static void register(RecipeOutput consumer) {
        recipesBlocks(consumer);
        recipesItems(consumer);
    }
    private static ResourceLocation location(String path) {
        return new ResourceLocation(CalamosMod.MODID, "crafting/" + path);
    }
    private static void recipesBlocks(RecipeOutput consumer) {
        compressedBlock(ModBlocks.GARNET_BLOCK.asItem(), ModItems.GARNET, false).save(consumer, location("garnet_block"));
        compressedBlock(ModBlocks.JADE_BLOCK.asItem(), ModItems.JADE, false).save(consumer, location("jade_block"));
        compressedBlock(ModBlocks.KUNZITE_BLOCK.asItem(), ModItems.KUNZITE, false).save(consumer, location("kunzite_block"));
        compressedBlock(ModBlocks.MOONSTONE_BLOCK.asItem(), ModItems.MOONSTONE, false).save(consumer, location("moonstone_block"));
        compressedBlock(ModBlocks.OPAL_BLOCK.asItem(), ModItems.OPAL, false).save(consumer, location("opal_block"));
        compressedBlock(ModBlocks.RUBY_BLOCK.asItem(), ModItems.RUBY, false).save(consumer, location("ruby_block"));
        compressedBlock(ModBlocks.SAPPHIRE_BLOCK.asItem(), ModItems.SAPPHIRE, false).save(consumer, location("sapphire_block"));
        compressedBlock(ModBlocks.SPINEL_BLOCK.asItem(), ModItems.SPINEL, false).save(consumer, location("spinel_block"));
        compressedBlock(ModBlocks.SUNSTONE_BLOCK.asItem(), ModItems.SUNSTONE, false).save(consumer, location("sunstone_block"));
        compressedBlock(ModBlocks.TANZANITE_BLOCK.asItem(), ModItems.TANZANITE, false).save(consumer, location("tanzanite_block"));
        compressedBlock(ModBlocks.TOPAZ_BLOCK.asItem(), ModItems.TOPAZ, false).save(consumer, location("topaz_block"));
        
        compressedBlock(ModBlocks.CUT_METEORITE.asItem(), ModBlocks.METEORITE.asItem(), true, 4).save(consumer, location("cut_meteorite"));
        compressedBlock(ModBlocks.POLISHED_METEORITE.asItem(), ModBlocks.COBBLED_METEORITE.asItem(), true, 4).save(consumer, location("polished_meteorite"));
        compressedBlock(ModBlocks.METEORITE_BRICKS.asItem(), ModBlocks.POLISHED_METEORITE.asItem(), true, 4).save(consumer, location("meteorite_bricks"));
        compressedBlock(ModBlocks.METEORITE_TILES.asItem(), ModBlocks.METEORITE_BRICKS.asItem(), true, 4).save(consumer, location("meteorite_tiles"));
        compressedBlock(ModBlocks.LARGE_METEORITE_TILE.asItem(), ModBlocks.METEORITE_TILES.asItem(), true, 4).save(consumer, location("large_meteorite_tile"));
    }
    private static void recipesItems(RecipeOutput consumer) {
        decompressedBlock(ModItems.GARNET.get(), ModBlocks.GARNET_BLOCK.asItem(), false).save(consumer, location("garnet"));
        decompressedBlock(ModItems.JADE.get(), ModBlocks.JADE_BLOCK.asItem(), false).save(consumer, location("jade"));
        decompressedBlock(ModItems.KUNZITE.get(), ModBlocks.KUNZITE_BLOCK.asItem(), false).save(consumer, location("kunzite"));
        decompressedBlock(ModItems.MOONSTONE.get(), ModBlocks.MOONSTONE_BLOCK.asItem(), false).save(consumer, location("moonstone"));
        decompressedBlock(ModItems.OPAL.get(), ModBlocks.OPAL_BLOCK.asItem(), false).save(consumer, location("opal"));
        decompressedBlock(ModItems.RUBY.get(), ModBlocks.RUBY_BLOCK.asItem(), false).save(consumer, location("ruby"));
        decompressedBlock(ModItems.SAPPHIRE.get(), ModBlocks.SAPPHIRE_BLOCK.asItem(), false).save(consumer, location("sapphire"));
        decompressedBlock(ModItems.SPINEL.get(), ModBlocks.SPINEL_BLOCK.asItem(), false).save(consumer, location("spinel"));
        decompressedBlock(ModItems.SUNSTONE.get(), ModBlocks.SUNSTONE_BLOCK.asItem(), false).save(consumer, location("sunstone"));
        decompressedBlock(ModItems.TANZANITE.get(), ModBlocks.TANZANITE_BLOCK.asItem(), false).save(consumer, location("tanzanite"));
        decompressedBlock(ModItems.TOPAZ.get(), ModBlocks.TOPAZ_BLOCK.asItem(), false).save(consumer, location("topaz"));
    }
    
    public static ShapelessRecipeBuilder decompressedBlock(Item result, ItemLike input, boolean fourByFour) {
        if (fourByFour) {
            return halfDecompress(result, input);
        } else {
            return fullDecompress(result, input);
        }
    }
    public static ShapedRecipeBuilder compressedBlock(Item result, ItemLike input, boolean fourByFour) {
        if (fourByFour) {
            return halfCompress(result, input,1);
        } else {
            return fullCompress(result, input,1);
        }
    }
    public static ShapedRecipeBuilder compressedBlock(Item result, ItemLike input, boolean fourByFour, int count) {
        if (fourByFour) { return halfCompress(result, input, count);
        } else {
            return fullCompress(result, input, count);
        }
    }
    public static ShapelessRecipeBuilder fullDecompress(Item result, ItemLike input) {
        String inputName = input.asItem().toString().split(":")[1];
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, 9).requires(input).unlockedBy("has_" + inputName, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    public static ShapelessRecipeBuilder halfDecompress(Item result, ItemLike input) {
        String inputName = input.asItem().toString().split(":")[1];
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, 4).requires(input).unlockedBy("has_" + inputName, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    public static ShapedRecipeBuilder fullCompress(Item result, ItemLike input, int count) {
        String inputName = input.asItem().toString().split(":")[1];
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, count).pattern("###").pattern("###").pattern("###").define('#', input).unlockedBy("has_" + inputName, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    public static ShapedRecipeBuilder halfCompress(Item result, ItemLike input, int count) {
        String inputName = input.asItem().toString().split(":")[1];
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, count).pattern("##").pattern("##").define('#', input).unlockedBy("has_" + inputName, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
}
