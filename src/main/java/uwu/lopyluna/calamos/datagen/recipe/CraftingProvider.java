package uwu.lopyluna.calamos.datagen.recipe;

import java.util.function.Consumer;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.elements.ModBlocks;
import uwu.lopyluna.calamos.elements.ModItems;

public class CraftingProvider {
    
    public static void register(RecipeOutput consumer) {
        recipesBlocks(consumer);
    }
    
    private static void recipesBlocks(RecipeOutput consumer) {
        compressedBlock(ModBlocks.GARNET_BLOCK.asItem(), ModItems.GARNET, false).save(consumer, new ResourceLocation(CalamosMod.MODID, "garnet_block"));
        compressedBlock(ModBlocks.JADE_BLOCK.asItem(), ModItems.JADE, false).save(consumer, new ResourceLocation(CalamosMod.MODID, "jade_block"));
        compressedBlock(ModBlocks.KUNZITE_BLOCK.asItem(), ModItems.KUNZITE, false).save(consumer, new ResourceLocation(CalamosMod.MODID, "kunzite_block"));
        compressedBlock(ModBlocks.MOONSTONE_BLOCK.asItem(), ModItems.MOONSTONE, false).save(consumer, new ResourceLocation(CalamosMod.MODID, "moonstone_block"));
        compressedBlock(ModBlocks.OPAL_BLOCK.asItem(), ModItems.OPAL, false).save(consumer, new ResourceLocation(CalamosMod.MODID, "opal_block"));
        compressedBlock(ModBlocks.RUBY_BLOCK.asItem(), ModItems.RUBY, false).save(consumer, new ResourceLocation(CalamosMod.MODID, "ruby_block"));
        compressedBlock(ModBlocks.SAPPHIRE_BLOCK.asItem(), ModItems.SAPPHIRE, false).save(consumer, new ResourceLocation(CalamosMod.MODID, "sapphire_block"));
        compressedBlock(ModBlocks.SPINEL_BLOCK.asItem(), ModItems.SPINEL, false).save(consumer, new ResourceLocation(CalamosMod.MODID, "spinel_block"));
        compressedBlock(ModBlocks.SUNSTONE_BLOCK.asItem(), ModItems.SUNSTONE, false).save(consumer, new ResourceLocation(CalamosMod.MODID, "sunstone_block"));
        compressedBlock(ModBlocks.TANZANITE_BLOCK.asItem(), ModItems.TANZANITE, false).save(consumer, new ResourceLocation(CalamosMod.MODID, "tanzanite_block"));
        compressedBlock(ModBlocks.TOPAZ_BLOCK.asItem(), ModItems.TOPAZ, false).save(consumer, new ResourceLocation(CalamosMod.MODID, "topaz_block"));
    }
    
    
    
    
    public static ShapedRecipeBuilder compressedBlock(Item result, ItemLike input, boolean fourByFour) {
        if (fourByFour) {
            return halfCompress(result, input);
        } else {
            return fullCompress(result, input);
        }
    }
    
    public static ShapedRecipeBuilder fullCompress(Item result, ItemLike input) {
        String inputName = input.asItem().toString().split(":")[1];
        return ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 1).pattern("###").pattern("###").pattern("###").define('#', input).unlockedBy("has_" + inputName, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    public static ShapedRecipeBuilder halfCompress(Item result, ItemLike input) {
        String inputName = input.asItem().toString().split(":")[1];
        return ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 1).pattern("##").pattern("##").define('#', input).unlockedBy("has_" + inputName, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    
    
}
