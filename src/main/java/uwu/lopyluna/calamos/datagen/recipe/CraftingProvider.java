package uwu.lopyluna.calamos.datagen.recipe;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.datagen.ModTags;
import uwu.lopyluna.calamos.elements.ModBlocks;
import uwu.lopyluna.calamos.elements.ModDecorativeBlocks;
import uwu.lopyluna.calamos.elements.ModItems;

public class CraftingProvider {
    
    public static void register(RecipeOutput consumer) {
        recipesBlocks(consumer);
        recipesItems(consumer);
        recipesSpecial(consumer);
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
        compressedBlock(ModBlocks.URANIUM_BLOCK.asItem(), ModItems.URANIUM_INGOT, false).save(consumer, location("uranium_block"));
        compressedBlock(ModBlocks.RAW_URANIUM_BLOCK.asItem(), ModItems.RAW_URANIUM, false).save(consumer, location("raw_uranium_block"));
        compressedBlock(ModBlocks.PALLADIUM_BLOCK.asItem(), ModItems.PALLADIUM_INGOT, false).save(consumer, location("palladium_block"));
        compressedBlock(ModBlocks.RAW_PALLADIUM_BLOCK.asItem(), ModItems.RAW_PALLADIUM, false).save(consumer, location("raw_palladium_block"));
        
        compressedBlock(ModDecorativeBlocks.CUT_METEORITE.asItem(), ModDecorativeBlocks.METEORITE.asItem(), true, 4).save(consumer, location("cut_meteorite"));
        compressedBlock(ModDecorativeBlocks.POLISHED_METEORITE.asItem(), ModDecorativeBlocks.COBBLED_METEORITE.asItem(), true, 4).save(consumer, location("polished_meteorite"));
        compressedBlock(ModDecorativeBlocks.METEORITE_BRICKS.asItem(), ModDecorativeBlocks.POLISHED_METEORITE.asItem(), true, 4).save(consumer, location("meteorite_bricks"));
        compressedBlock(ModDecorativeBlocks.METEORITE_TILES.asItem(), ModDecorativeBlocks.METEORITE_BRICKS.asItem(), true, 4).save(consumer, location("meteorite_tiles"));
        compressedBlock(ModDecorativeBlocks.LARGE_METEORITE_TILE.asItem(), ModDecorativeBlocks.METEORITE_TILES.asItem(), true, 4).save(consumer, location("large_meteorite_tile"));
        
        simpleStairs(ModDecorativeBlocks.METEORITE_STAIRS.asItem(), ModDecorativeBlocks.METEORITE.asItem()).save(consumer, location("meteorite_stairs"));
        simpleStairs(ModDecorativeBlocks.POLISHED_METEORITE_STAIRS.asItem(), ModDecorativeBlocks.POLISHED_METEORITE.asItem()).save(consumer, location("polished_meteorite_stairs"));
        simpleStairs(ModDecorativeBlocks.SMOOTH_METEORITE_STAIRS.asItem(), ModDecorativeBlocks.SMOOTH_METEORITE.asItem()).save(consumer, location("smooth_meteorite_stairs"));
        
        simpleSlab(ModDecorativeBlocks.METEORITE_SLAB.asItem(), ModDecorativeBlocks.METEORITE.asItem()).save(consumer, location("meteorite_slab"));
        simpleSlab(ModDecorativeBlocks.POLISHED_METEORITE_SLAB.asItem(), ModDecorativeBlocks.POLISHED_METEORITE.asItem()).save(consumer, location("polished_meteorite_slab"));
        simpleSlab(ModDecorativeBlocks.SMOOTH_METEORITE_SLAB.asItem(), ModDecorativeBlocks.SMOOTH_METEORITE.asItem()).save(consumer, location("smooth_meteorite_slab"));
        
        simpleWall(ModDecorativeBlocks.METEORITE_WALL.asItem(), ModDecorativeBlocks.METEORITE.asItem()).save(consumer, location("meteorite_wall"));
        simpleWall(ModDecorativeBlocks.POLISHED_METEORITE_WALL.asItem(), ModDecorativeBlocks.POLISHED_METEORITE.asItem()).save(consumer, location("polished_meteorite_wall"));
        simpleWall(ModDecorativeBlocks.SMOOTH_METEORITE_WALL.asItem(), ModDecorativeBlocks.SMOOTH_METEORITE.asItem()).save(consumer, location("smooth_meteorite_wall"));
        
        simpleStairs(ModDecorativeBlocks.SOUL_SANDSTONE_STAIRS.asItem(), ModDecorativeBlocks.SOUL_SANDSTONE.asItem()).save(consumer, location("soul_sandstone_stairs"));
        simpleStairs(ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE_STAIRS.asItem(), ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE.asItem()).save(consumer, location("cobbled_soul_sandstone_stairs"));
        simpleStairs(ModDecorativeBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS.asItem(), ModDecorativeBlocks.SMOOTH_SOUL_SANDSTONE.asItem()).save(consumer, location("smooth_soul_sandstone_stairs"));
        simpleStairs(ModDecorativeBlocks.COBBLED_SANDSTONE_STAIRS.asItem(), ModDecorativeBlocks.COBBLED_SANDSTONE.asItem()).save(consumer, location("cobbled_sandstone_stairs"));
        
        simpleSlab(ModDecorativeBlocks.SOUL_SANDSTONE_SLAB.asItem(), ModDecorativeBlocks.SOUL_SANDSTONE.asItem()).save(consumer, location("soul_sandstone_slab"));
        simpleSlab(ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE_SLAB.asItem(), ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE.asItem()).save(consumer, location("cobbled_soul_sandstone_slab"));
        simpleSlab(ModDecorativeBlocks.SMOOTH_SOUL_SANDSTONE_SLAB.asItem(), ModDecorativeBlocks.SMOOTH_SOUL_SANDSTONE.asItem()).save(consumer, location("smooth_soul_sandstone_slab"));
        simpleSlab(ModDecorativeBlocks.COBBLED_SANDSTONE_SLAB.asItem(), ModDecorativeBlocks.COBBLED_SANDSTONE.asItem()).save(consumer, location("cobbled_sandstone_slab"));
        
        simpleWall(ModDecorativeBlocks.SOUL_SANDSTONE_WALL.asItem(), ModDecorativeBlocks.SOUL_SANDSTONE.asItem()).save(consumer, location("soul_sandstone_wall"));
        simpleWall(ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE_WALL.asItem(), ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE.asItem()).save(consumer, location("cobbled_soul_sandstone_wall"));
        simpleWall(ModDecorativeBlocks.COBBLED_SANDSTONE_WALL.asItem(), ModDecorativeBlocks.COBBLED_SANDSTONE.asItem()).save(consumer, location("cobbled_sandstone_wall"));
        
        //WOOD
        halfCompress(ModDecorativeBlocks.OTHERWORLD_OAK_WOOD.asItem(), ModDecorativeBlocks.OTHERWORLD_OAK_LOG.asItem(), 3).save(consumer, location("otherworld_oak_wood"));
        halfCompress(ModDecorativeBlocks.TWILIGHT_WOOD.asItem(), ModDecorativeBlocks.TWILIGHT_LOG.asItem(), 3).save(consumer, location("twilight_wood"));
        halfCompress(ModDecorativeBlocks.HOLLOW_WOOD.asItem(), ModDecorativeBlocks.HOLLOW_LOG.asItem(), 3).save(consumer, location("hollow_wood"));

        halfCompress(ModDecorativeBlocks.STRIPPED_OTHERWORLD_OAK_WOOD.asItem(), ModDecorativeBlocks.STRIPPED_OTHERWORLD_OAK_LOG.asItem(), 3).save(consumer, location("stripped_otherworld_oak_wood"));
        halfCompress(ModDecorativeBlocks.STRIPPED_TWILIGHT_WOOD.asItem(), ModDecorativeBlocks.STRIPPED_TWILIGHT_LOG.asItem(), 3).save(consumer, location("stripped_twilight_wood"));
        halfCompress(ModDecorativeBlocks.STRIPPED_HOLLOW_WOOD.asItem(), ModDecorativeBlocks.STRIPPED_HOLLOW_LOG.asItem(), 3).save(consumer, location("stripped_hollow_wood"));

        simpleStairs(ModDecorativeBlocks.OTHERWORLD_OAK_STAIRS.asItem(), ModDecorativeBlocks.OTHERWORLD_OAK_PLANKS.asItem()).save(consumer, location("otherworld_oak_stairs"));
        simpleStairs(ModDecorativeBlocks.TWILIGHT_STAIRS.asItem(), ModDecorativeBlocks.TWILIGHT_PLANKS.asItem()).save(consumer, location("twilight_stairs"));
        simpleStairs(ModDecorativeBlocks.HOLLOW_STAIRS.asItem(), ModDecorativeBlocks.HOLLOW_PLANKS.asItem()).save(consumer, location("hollow_stairs"));

        simpleSlab(ModDecorativeBlocks.OTHERWORLD_OAK_SLAB.asItem(), ModDecorativeBlocks.OTHERWORLD_OAK_PLANKS.asItem()).save(consumer, location("otherworld_oak_slab"));
        simpleSlab(ModDecorativeBlocks.TWILIGHT_SLAB.asItem(), ModDecorativeBlocks.TWILIGHT_PLANKS.asItem()).save(consumer, location("twilight_slab"));
        simpleSlab(ModDecorativeBlocks.HOLLOW_SLAB.asItem(), ModDecorativeBlocks.HOLLOW_PLANKS.asItem()).save(consumer, location("hollow_slab"));

        simpleFence(ModDecorativeBlocks.OTHERWORLD_OAK_FENCE.asItem(), ModDecorativeBlocks.OTHERWORLD_OAK_PLANKS.asItem()).save(consumer, location("otherworld_oak_fence"));
        simpleFence(ModDecorativeBlocks.TWILIGHT_FENCE.asItem(), ModDecorativeBlocks.TWILIGHT_PLANKS.asItem()).save(consumer, location("twilight_fence"));
        simpleFence(ModDecorativeBlocks.HOLLOW_FENCE.asItem(), ModDecorativeBlocks.HOLLOW_PLANKS.asItem()).save(consumer, location("hollow_fence"));

        simpleFenceGate(ModDecorativeBlocks.OTHERWORLD_OAK_FENCE_GATE.asItem(), ModDecorativeBlocks.OTHERWORLD_OAK_PLANKS.asItem()).save(consumer, location("otherworld_oak_fence_gate"));
        simpleFenceGate(ModDecorativeBlocks.TWILIGHT_FENCE_GATE.asItem(), ModDecorativeBlocks.TWILIGHT_PLANKS.asItem()).save(consumer, location("twilight_fence_gate"));
        simpleFenceGate(ModDecorativeBlocks.HOLLOW_FENCE_GATE.asItem(), ModDecorativeBlocks.HOLLOW_PLANKS.asItem()).save(consumer, location("hollow_fence_gate"));

        simpleDoor(ModDecorativeBlocks.OTHERWORLD_OAK_DOOR.asItem(), ModDecorativeBlocks.OTHERWORLD_OAK_PLANKS.asItem()).save(consumer, location("otherworld_oak_door"));
        simpleDoor(ModDecorativeBlocks.TWILIGHT_DOOR.asItem(), ModDecorativeBlocks.TWILIGHT_PLANKS.asItem()).save(consumer, location("twilight_door"));
        simpleDoor(ModDecorativeBlocks.HOLLOW_DOOR.asItem(), ModDecorativeBlocks.HOLLOW_PLANKS.asItem()).save(consumer, location("hollow_door"));

        simpleTrapdoor(ModDecorativeBlocks.OTHERWORLD_OAK_TRAPDOOR.asItem(), ModDecorativeBlocks.OTHERWORLD_OAK_PLANKS.asItem()).save(consumer, location("otherworld_oak_trapdoor"));
        simpleTrapdoor(ModDecorativeBlocks.TWILIGHT_TRAPDOOR.asItem(), ModDecorativeBlocks.TWILIGHT_PLANKS.asItem()).save(consumer, location("twilight_trapdoor"));
        simpleTrapdoor(ModDecorativeBlocks.HOLLOW_TRAPDOOR.asItem(), ModDecorativeBlocks.HOLLOW_PLANKS.asItem()).save(consumer, location("hollow_trapdoor"));

        simpleSign(ModItems.OTHERWORLD_OAK_SIGN.get(), ModDecorativeBlocks.OTHERWORLD_OAK_PLANKS.asItem()).save(consumer, location("otherworld_oak_sign"));
        simpleSign(ModItems.TWILIGHT_SIGN.get(), ModDecorativeBlocks.TWILIGHT_PLANKS.asItem()).save(consumer, location("twilight_sign"));
        simpleSign(ModItems.HOLLOW_SIGN.get(), ModDecorativeBlocks.HOLLOW_PLANKS.asItem()).save(consumer, location("hollow_sign"));

        simpleHangingSign(ModItems.OTHERWORLD_OAK_HANGING_SIGN.get(), ModDecorativeBlocks.STRIPPED_OTHERWORLD_OAK_LOG.asItem()).save(consumer, location("otherworld_oak_hanging_sign"));
        simpleHangingSign(ModItems.TWILIGHT_HANGING_SIGN.get(), ModDecorativeBlocks.STRIPPED_TWILIGHT_LOG.asItem()).save(consumer, location("twilight_hanging_sign"));
        simpleHangingSign(ModItems.HOLLOW_HANGING_SIGN.get(), ModDecorativeBlocks.STRIPPED_HOLLOW_LOG.asItem()).save(consumer, location("hollow_hanging_sign"));

        simpleShapeless(ModDecorativeBlocks.OTHERWORLD_OAK_BUTTON.asItem(), ModDecorativeBlocks.OTHERWORLD_OAK_PLANKS.asItem(), 1).save(consumer, location("otherworld_oak_button"));
        simpleShapeless(ModDecorativeBlocks.TWILIGHT_BUTTON.asItem(), ModDecorativeBlocks.TWILIGHT_PLANKS.asItem(), 1).save(consumer, location("twilight_button"));
        simpleShapeless(ModDecorativeBlocks.HOLLOW_BUTTON.asItem(), ModDecorativeBlocks.HOLLOW_PLANKS.asItem(), 1).save(consumer, location("hollow_button"));

        simpleShapeless(ModDecorativeBlocks.OTHERWORLD_OAK_PLANKS.asItem(), ModDecorativeBlocks.OTHERWORLD_OAK_LOG.asItem(), 4).save(consumer, location("otherworld_oak_planks"));
        simpleShapeless(ModDecorativeBlocks.TWILIGHT_PLANKS.asItem(), ModDecorativeBlocks.TWILIGHT_LOG.asItem(), 4).save(consumer, location("twilight_planks"));
        simpleShapeless(ModDecorativeBlocks.HOLLOW_PLANKS.asItem(), ModDecorativeBlocks.HOLLOW_LOG.asItem(), 4).save(consumer, location("hollow_planks"));

        simpleStairs(ModDecorativeBlocks.LARGE_OTHERWORLD_OAK_STAIRS.asItem(), ModDecorativeBlocks.LARGE_OTHERWORLD_OAK_PLANKS.asItem()).save(consumer, location("large_otherworld_oak_stairs"));
        simpleStairs(ModDecorativeBlocks.LARGE_TWILIGHT_STAIRS.asItem(), ModDecorativeBlocks.LARGE_TWILIGHT_PLANKS.asItem()).save(consumer, location("large_twilight_stairs"));
        simpleStairs(ModDecorativeBlocks.LARGE_HOLLOW_STAIRS.asItem(), ModDecorativeBlocks.LARGE_HOLLOW_PLANKS.asItem()).save(consumer, location("large_hollow_stairs"));
        simpleStairs(ModDecorativeBlocks.LARGE_ACACIA_STAIRS.asItem(), ModDecorativeBlocks.LARGE_ACACIA_PLANKS.asItem()).save(consumer, location("large_acacia_stairs"));
        simpleStairs(ModDecorativeBlocks.LARGE_BIRCH_STAIRS.asItem(), ModDecorativeBlocks.LARGE_BIRCH_PLANKS.asItem()).save(consumer, location("large_birch_stairs"));
        simpleStairs(ModDecorativeBlocks.LARGE_CRIMSON_STAIRS.asItem(), ModDecorativeBlocks.LARGE_CRIMSON_PLANKS.asItem()).save(consumer, location("large_crimson_stairs"));
        simpleStairs(ModDecorativeBlocks.LARGE_DARK_OAK_STAIRS.asItem(), ModDecorativeBlocks.LARGE_DARK_OAK_PLANKS.asItem()).save(consumer, location("large_dark_oak_stairs"));
        simpleStairs(ModDecorativeBlocks.LARGE_JUNGLE_STAIRS.asItem(), ModDecorativeBlocks.LARGE_JUNGLE_PLANKS.asItem()).save(consumer, location("large_jungle_stairs"));
        simpleStairs(ModDecorativeBlocks.LARGE_OAK_STAIRS.asItem(), ModDecorativeBlocks.LARGE_OAK_PLANKS.asItem()).save(consumer, location("large_oak_stairs"));
        simpleStairs(ModDecorativeBlocks.LARGE_SPRUCE_STAIRS.asItem(), ModDecorativeBlocks.LARGE_SPRUCE_PLANKS.asItem()).save(consumer, location("large_spruce_stairs"));
        simpleStairs(ModDecorativeBlocks.LARGE_WARPED_STAIRS.asItem(), ModDecorativeBlocks.LARGE_WARPED_PLANKS.asItem()).save(consumer, location("large_warped_stairs"));
        simpleStairs(ModDecorativeBlocks.LARGE_MANGROVE_STAIRS.asItem(), ModDecorativeBlocks.LARGE_MANGROVE_PLANKS.asItem()).save(consumer, location("large_mangrove_stairs"));
        simpleStairs(ModDecorativeBlocks.LARGE_CHERRY_STAIRS.asItem(), ModDecorativeBlocks.LARGE_CHERRY_PLANKS.asItem()).save(consumer, location("large_cherry_stairs"));

        simpleSlab(ModDecorativeBlocks.LARGE_OTHERWORLD_OAK_SLAB.asItem(), ModDecorativeBlocks.LARGE_OTHERWORLD_OAK_PLANKS.asItem()).save(consumer, location("large_otherworld_oak_slab"));
        simpleSlab(ModDecorativeBlocks.LARGE_TWILIGHT_SLAB.asItem(), ModDecorativeBlocks.LARGE_TWILIGHT_PLANKS.asItem()).save(consumer, location("large_twilight_slab"));
        simpleSlab(ModDecorativeBlocks.LARGE_HOLLOW_SLAB.asItem(), ModDecorativeBlocks.LARGE_HOLLOW_PLANKS.asItem()).save(consumer, location("large_hollow_slab"));
        simpleSlab(ModDecorativeBlocks.LARGE_ACACIA_SLAB.asItem(), ModDecorativeBlocks.LARGE_ACACIA_PLANKS.asItem()).save(consumer, location("large_acacia_slab"));
        simpleSlab(ModDecorativeBlocks.LARGE_BIRCH_SLAB.asItem(), ModDecorativeBlocks.LARGE_BIRCH_PLANKS.asItem()).save(consumer, location("large_birch_slab"));
        simpleSlab(ModDecorativeBlocks.LARGE_CRIMSON_SLAB.asItem(), ModDecorativeBlocks.LARGE_CRIMSON_PLANKS.asItem()).save(consumer, location("large_crimson_slab"));
        simpleSlab(ModDecorativeBlocks.LARGE_DARK_OAK_SLAB.asItem(), ModDecorativeBlocks.LARGE_DARK_OAK_PLANKS.asItem()).save(consumer, location("large_dark_oak_slab"));
        simpleSlab(ModDecorativeBlocks.LARGE_JUNGLE_SLAB.asItem(), ModDecorativeBlocks.LARGE_JUNGLE_PLANKS.asItem()).save(consumer, location("large_jungle_slab"));
        simpleSlab(ModDecorativeBlocks.LARGE_OAK_SLAB.asItem(), ModDecorativeBlocks.LARGE_OAK_PLANKS.asItem()).save(consumer, location("large_oak_slab"));
        simpleSlab(ModDecorativeBlocks.LARGE_SPRUCE_SLAB.asItem(), ModDecorativeBlocks.LARGE_SPRUCE_PLANKS.asItem()).save(consumer, location("large_spruce_slab"));
        simpleSlab(ModDecorativeBlocks.LARGE_WARPED_SLAB.asItem(), ModDecorativeBlocks.LARGE_WARPED_PLANKS.asItem()).save(consumer, location("large_warped_slab"));
        simpleSlab(ModDecorativeBlocks.LARGE_MANGROVE_SLAB.asItem(), ModDecorativeBlocks.LARGE_MANGROVE_PLANKS.asItem()).save(consumer, location("large_mangrove_slab"));
        simpleSlab(ModDecorativeBlocks.LARGE_CHERRY_SLAB.asItem(), ModDecorativeBlocks.LARGE_CHERRY_PLANKS.asItem()).save(consumer, location("large_cherry_slab"));
        
        //STONE STUFFS
        compressedBlock(ModDecorativeBlocks.STONE_TILES.asItem(), Items.STONE_BRICKS, true, 4).save(consumer, location("stone_tiles"));
        compressedBlock(ModDecorativeBlocks.ENDSTONE_TILES.asItem(), Items.END_STONE_BRICKS, true, 4).save(consumer, location("endstone_tiles"));
        compressedBlock(ModDecorativeBlocks.MUD_TILES.asItem(), Items.MUD_BRICKS, true, 4).save(consumer, location("mud_tiles"));
        compressedBlock(ModDecorativeBlocks.BLACKSTONE_TILES.asItem(), Items.POLISHED_BLACKSTONE_BRICKS, true, 4).save(consumer, location("blackstone_tiles"));
        compressedBlock(ModDecorativeBlocks.COBBLESTONE_BRICKS.asItem(), Items.COBBLESTONE, true, 4).save(consumer, location("cobblestone_bricks"));
        
        simpleStairs(ModDecorativeBlocks.COBBLESTONE_BRICK_STAIRS.asItem(), ModDecorativeBlocks.COBBLESTONE_BRICKS.asItem()).save(consumer, location("cobblestone_brick_stairs"));
        
        simpleSlab(ModDecorativeBlocks.COBBLESTONE_BRICK_SLAB.asItem(), ModDecorativeBlocks.COBBLESTONE_BRICKS.asItem()).save(consumer, location("cobblestone_brick_slab"));
        
        simpleWall(ModDecorativeBlocks.COBBLESTONE_BRICK_WALL.asItem(), ModDecorativeBlocks.COBBLESTONE_BRICKS.asItem()).save(consumer, location("cobblestone_brick_wall"));
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
        decompressedBlock(ModItems.URANIUM_INGOT.get(), ModBlocks.URANIUM_BLOCK.asItem(), false).save(consumer, location("uranium_ingot_from_uranium_block"));
        decompressedBlock(ModItems.RAW_URANIUM.get(), ModBlocks.RAW_URANIUM_BLOCK.asItem(), false).save(consumer, location("raw_uranium"));
        decompressedBlock(ModItems.URANIUM_NUGGET.get(), ModItems.URANIUM_INGOT.get(), false).save(consumer, location("uranium_nugget"));
        decompressedBlock(ModItems.PALLADIUM_INGOT.get(), ModBlocks.PALLADIUM_BLOCK.asItem(), false).save(consumer, location("palladium_ingot_from_palladium_block"));
        decompressedBlock(ModItems.RAW_PALLADIUM.get(), ModBlocks.RAW_PALLADIUM_BLOCK.asItem(), false).save(consumer, location("raw_palladium"));
        decompressedBlock(ModItems.PALLADIUM_NUGGET.get(), ModItems.PALLADIUM_INGOT.get(), false).save(consumer, location("palladium_nugget"));
        
        compressedBlock(ModItems.URANIUM_INGOT.get(), ModItems.URANIUM_NUGGET.get(), false).save(consumer, location("uranium_ingot_from_nuggets"));
        compressedBlock(ModItems.PALLADIUM_INGOT.get(), ModItems.PALLADIUM_NUGGET.get(), false).save(consumer, location("palladium_ingot_from_nuggets"));
    }
    private static void recipesSpecial(RecipeOutput consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Items.BLAST_FURNACE, 1)
                .pattern("III")
                .pattern("IFI")
                .pattern("DDD")
                .define('I', ModTags.forgeItemTag("ingots/iron"))
                .define('F', Items.FURNACE)
                .define('D', ModDecorativeBlocks.SMOOTH_DEEPSLATE.asItem())
                .unlockedBy("has_smooth_deepslate", InventoryChangeTrigger.TriggerInstance.hasItems(ModDecorativeBlocks.SMOOTH_DEEPSLATE.asItem()))
                .save(consumer, location("blast_furnace_from_smooth_deepslate"));
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SAWMILL.asItem(), 1)
                .pattern(" I ")
                .pattern("LPL")
                .define('I', ModTags.forgeItemTag("ingots/iron"))
                .define('L', ItemTags.LOGS)
                .define('P', ItemTags.PLANKS)
                .unlockedBy("has_planks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.OAK_PLANKS))
                .save(consumer, location("sawmill"));
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.METAL_GRINDER.asItem(), 1)
                .pattern(" I ")
                .pattern("SBS")
                .define('I', ModTags.forgeItemTag("ingots/iron"))
                .define('S', Items.SMOOTH_STONE)
                .define('B', Items.STONE_BRICKS)
                .unlockedBy("has_stone_bricks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE_BRICKS))
                .save(consumer, location("metal_grinder"));
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
    public static ShapelessRecipeBuilder simpleShapeless(Item result, ItemLike input, int count) {
        String inputName = input.asItem().toString().split(":")[1];
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, count).requires(input).unlockedBy("has_" + inputName, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    public static ShapedRecipeBuilder fullCompress(Item result, ItemLike input, int count) {
        String inputName = input.asItem().toString().split(":")[1];
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, count).pattern("###").pattern("###").pattern("###").define('#', input).unlockedBy("has_" + inputName, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    public static ShapedRecipeBuilder halfCompress(Item result, ItemLike input, int count) {
        String inputName = input.asItem().toString().split(":")[1];
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, count).pattern("##").pattern("##").define('#', input).unlockedBy("has_" + inputName, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    
    public static ShapedRecipeBuilder simpleStairs(Item result, ItemLike input) {
        String inputName = input.asItem().toString().split(":")[1];
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, 4).pattern("#  ").pattern("## ").pattern("###").define('#', input).unlockedBy("has_" + inputName, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    public static ShapedRecipeBuilder simpleSlab(Item result, ItemLike input) {
        String inputName = input.asItem().toString().split(":")[1];
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, 6).pattern("###").define('#', input).unlockedBy("has_" + inputName, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    public static ShapedRecipeBuilder simpleWall(Item result, ItemLike input) {
        String inputName = input.asItem().toString().split(":")[1];
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, 6).pattern("###").pattern("###").define('#', input).unlockedBy("has_" + inputName, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    public static ShapedRecipeBuilder simpleFence(Item result, ItemLike input) {
        String inputName = input.asItem().toString().split(":")[1];
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, 3).pattern("#S#").pattern("#S#").define('#', input).define('S', Items.STICK).unlockedBy("has_" + inputName, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    public static ShapedRecipeBuilder simpleFenceGate(Item result, ItemLike input) {
        String inputName = input.asItem().toString().split(":")[1];
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, 1).pattern("S#S").pattern("S#S").define('#', input).define('S', Items.STICK).unlockedBy("has_" + inputName, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    public static ShapedRecipeBuilder simpleDoor(Item result, ItemLike input) {
        String inputName = input.asItem().toString().split(":")[1];
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, 3).pattern("##").pattern("##").pattern("##").define('#', input).unlockedBy("has_" + inputName, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    public static ShapedRecipeBuilder simpleTrapdoor(Item result, ItemLike input) {
        String inputName = input.asItem().toString().split(":")[1];
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, 2).pattern("###").pattern("###").define('#', input).unlockedBy("has_" + inputName, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    public static ShapedRecipeBuilder simpleSign(Item result, ItemLike input) {
        String inputName = input.asItem().toString().split(":")[1];
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, 3).pattern("###").pattern("###").pattern(" S ").define('#', input).define('S', Items.STICK).unlockedBy("has_" + inputName, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    public static ShapedRecipeBuilder simpleHangingSign(Item result, ItemLike input) {
        String inputName = input.asItem().toString().split(":")[1];
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, 6).pattern("C C").pattern("###").pattern("###").define('#', input).define('C', Items.CHAIN).unlockedBy("has_" + inputName, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    
}
