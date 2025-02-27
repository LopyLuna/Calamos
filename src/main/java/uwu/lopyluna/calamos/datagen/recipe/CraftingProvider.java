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
        recipesEquipment(consumer);
    }
    private static ResourceLocation location(String path) {
        return new ResourceLocation(CalamosMod.MODID, "crafting/" + path);
    }
    private static void recipesBlocks(RecipeOutput consumer) {
        compressedBlock(ModBlocks.GARNET_BLOCK.asItem(), ModItems.GARNET, false).save(consumer, location("materials/garnet/block"));
        compressedBlock(ModBlocks.JADE_BLOCK.asItem(), ModItems.JADE, false).save(consumer, location("materials/jade/block"));
        compressedBlock(ModBlocks.KUNZITE_BLOCK.asItem(), ModItems.KUNZITE, false).save(consumer, location("materials/kunzite/block"));
        compressedBlock(ModBlocks.MOONSTONE_BLOCK.asItem(), ModItems.MOONSTONE, false).save(consumer, location("materials/moonstone/block"));
        compressedBlock(ModBlocks.OPAL_BLOCK.asItem(), ModItems.OPAL, false).save(consumer, location("materials/opal/block"));
        compressedBlock(ModBlocks.RUBY_BLOCK.asItem(), ModItems.RUBY, false).save(consumer, location("materials/ruby/block"));
        compressedBlock(ModBlocks.SAPPHIRE_BLOCK.asItem(), ModItems.SAPPHIRE, false).save(consumer, location("materials/sapphire/block"));
        compressedBlock(ModBlocks.SPINEL_BLOCK.asItem(), ModItems.SPINEL, false).save(consumer, location("materials/spinel/block"));
        compressedBlock(ModBlocks.SUNSTONE_BLOCK.asItem(), ModItems.SUNSTONE, false).save(consumer, location("materials/sunstone/block"));
        compressedBlock(ModBlocks.TANZANITE_BLOCK.asItem(), ModItems.TANZANITE, false).save(consumer, location("materials/tanzanite/block"));
        compressedBlock(ModBlocks.TOPAZ_BLOCK.asItem(), ModItems.TOPAZ, false).save(consumer, location("materials/topaz/block"));
        compressedBlock(ModBlocks.URANIUM_BLOCK.asItem(), ModItems.URANIUM_INGOT, false).save(consumer, location("materials/uranium/block"));
        compressedBlock(ModBlocks.RAW_URANIUM_BLOCK.asItem(), ModItems.RAW_URANIUM, false).save(consumer, location("materials/uranium/raw/block"));
        compressedBlock(ModBlocks.PALLADIUM_BLOCK.asItem(), ModItems.PALLADIUM_INGOT, false).save(consumer, location("materials/palladium/block"));
        compressedBlock(ModBlocks.RAW_PALLADIUM_BLOCK.asItem(), ModItems.RAW_PALLADIUM, false).save(consumer, location("materials/palladium/raw/block"));
        
        compressedBlock(ModDecorativeBlocks.CUT_METEORITE.asItem(), ModDecorativeBlocks.METEORITE.asItem(), true, 4).save(consumer, location("decoration/masonry/meteorite/cut"));
        compressedBlock(ModDecorativeBlocks.POLISHED_METEORITE.asItem(), ModDecorativeBlocks.COBBLED_METEORITE.asItem(), true, 4).save(consumer, location("decoration/masonry/meteorite/polished"));
        compressedBlock(ModDecorativeBlocks.METEORITE_BRICKS.asItem(), ModDecorativeBlocks.POLISHED_METEORITE.asItem(), true, 4).save(consumer, location("decoration/masonry/meteorite/bricks"));
        compressedBlock(ModDecorativeBlocks.METEORITE_TILES.asItem(), ModDecorativeBlocks.METEORITE_BRICKS.asItem(), true, 4).save(consumer, location("decoration/masonry/meteorite/tiles"));
        compressedBlock(ModDecorativeBlocks.LARGE_METEORITE_TILE.asItem(), ModDecorativeBlocks.METEORITE_TILES.asItem(), true, 4).save(consumer, location("decoration/masonry/meteorite/large_tile"));
        
        simpleStairs(ModDecorativeBlocks.METEORITE_STAIRS.asItem(), ModDecorativeBlocks.METEORITE.asItem()).save(consumer, location("decoration/masonry/meteorite/stairs"));
        simpleStairs(ModDecorativeBlocks.POLISHED_METEORITE_STAIRS.asItem(), ModDecorativeBlocks.POLISHED_METEORITE.asItem()).save(consumer, location("decoration/masonry/meteorite/polished/stairs"));
        simpleStairs(ModDecorativeBlocks.SMOOTH_METEORITE_STAIRS.asItem(), ModDecorativeBlocks.SMOOTH_METEORITE.asItem()).save(consumer, location("decoration/masonry/meteorite/smooth/stairs"));
        
        simpleSlab(ModDecorativeBlocks.METEORITE_SLAB.asItem(), ModDecorativeBlocks.METEORITE.asItem()).save(consumer, location("decoration/masonry/meteorite/slab"));
        simpleSlab(ModDecorativeBlocks.POLISHED_METEORITE_SLAB.asItem(), ModDecorativeBlocks.POLISHED_METEORITE.asItem()).save(consumer, location("decoration/masonry/meteorite/polished/slab"));
        simpleSlab(ModDecorativeBlocks.SMOOTH_METEORITE_SLAB.asItem(), ModDecorativeBlocks.SMOOTH_METEORITE.asItem()).save(consumer, location("decoration/masonry/meteorite/smooth/slab"));
        
        simpleWall(ModDecorativeBlocks.METEORITE_WALL.asItem(), ModDecorativeBlocks.METEORITE.asItem()).save(consumer, location("decoration/masonry/meteorite/wall"));
        simpleWall(ModDecorativeBlocks.POLISHED_METEORITE_WALL.asItem(), ModDecorativeBlocks.POLISHED_METEORITE.asItem()).save(consumer, location("decoration/masonry/meteorite/polished/wall"));
        simpleWall(ModDecorativeBlocks.SMOOTH_METEORITE_WALL.asItem(), ModDecorativeBlocks.SMOOTH_METEORITE.asItem()).save(consumer, location("decoration/masonry/meteorite/smooth/wall"));
        
        simpleStairs(ModDecorativeBlocks.SOUL_SANDSTONE_STAIRS.asItem(), ModDecorativeBlocks.SOUL_SANDSTONE.asItem()).save(consumer, location("decoration/masonry/soul_sandstone/stairs"));
        simpleStairs(ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE_STAIRS.asItem(), ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE.asItem()).save(consumer, location("decoration/masonry/soul_sandstone/cobbled/stairs"));
        simpleStairs(ModDecorativeBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS.asItem(), ModDecorativeBlocks.SMOOTH_SOUL_SANDSTONE.asItem()).save(consumer, location("decoration/masonry/soul_sandstone/smooth/stairs"));
        simpleStairs(ModDecorativeBlocks.COBBLED_SANDSTONE_STAIRS.asItem(), ModDecorativeBlocks.COBBLED_SANDSTONE.asItem()).save(consumer, location("decoration/masonry/sandstone/cobbled/stairs"));
        
        simpleSlab(ModDecorativeBlocks.SOUL_SANDSTONE_SLAB.asItem(), ModDecorativeBlocks.SOUL_SANDSTONE.asItem()).save(consumer, location("decoration/masonry/soul_sandstone/slab"));
        simpleSlab(ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE_SLAB.asItem(), ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE.asItem()).save(consumer, location("decoration/masonry/soul_sandstone/cobbled/slab"));
        simpleSlab(ModDecorativeBlocks.SMOOTH_SOUL_SANDSTONE_SLAB.asItem(), ModDecorativeBlocks.SMOOTH_SOUL_SANDSTONE.asItem()).save(consumer, location("decoration/masonry/soul_sandstone/smooth/slab"));
        simpleSlab(ModDecorativeBlocks.COBBLED_SANDSTONE_SLAB.asItem(), ModDecorativeBlocks.COBBLED_SANDSTONE.asItem()).save(consumer, location("decoration/masonry/sandstone/cobbled/slab"));
        
        simpleWall(ModDecorativeBlocks.SOUL_SANDSTONE_WALL.asItem(), ModDecorativeBlocks.SOUL_SANDSTONE.asItem()).save(consumer, location("decoration/masonry/soul_sandstone/wall"));
        simpleWall(ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE_WALL.asItem(), ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE.asItem()).save(consumer, location("decoration/masonry/soul_sandstone/cobbled/wall"));
        simpleWall(ModDecorativeBlocks.COBBLED_SANDSTONE_WALL.asItem(), ModDecorativeBlocks.COBBLED_SANDSTONE.asItem()).save(consumer, location("decoration/masonry/sandstone/cobbled/wall"));
        
        //WOOD
        halfCompress(ModDecorativeBlocks.OTHERWORLD_OAK_WOOD.asItem(), ModDecorativeBlocks.OTHERWORLD_OAK_LOG.asItem(), 3).save(consumer, location("decoration/carpentry/otherworld_oak/wood"));
        halfCompress(ModDecorativeBlocks.TWILIGHT_WOOD.asItem(), ModDecorativeBlocks.TWILIGHT_LOG.asItem(), 3).save(consumer, location("decoration/carpentry/twilight/wood"));
        halfCompress(ModDecorativeBlocks.HOLLOW_WOOD.asItem(), ModDecorativeBlocks.HOLLOW_LOG.asItem(), 3).save(consumer, location("decoration/carpentry/hollow/wood"));

        halfCompress(ModDecorativeBlocks.STRIPPED_OTHERWORLD_OAK_WOOD.asItem(), ModDecorativeBlocks.STRIPPED_OTHERWORLD_OAK_LOG.asItem(), 3).save(consumer, location("decoration/carpentry/otherworld_oak/stripped_wood"));
        halfCompress(ModDecorativeBlocks.STRIPPED_TWILIGHT_WOOD.asItem(), ModDecorativeBlocks.STRIPPED_TWILIGHT_LOG.asItem(), 3).save(consumer, location("decoration/carpentry/twilight/stripped_wood"));
        halfCompress(ModDecorativeBlocks.STRIPPED_HOLLOW_WOOD.asItem(), ModDecorativeBlocks.STRIPPED_HOLLOW_LOG.asItem(), 3).save(consumer, location("decoration/carpentry/hollow/stripped_wood"));

        simpleStairs(ModDecorativeBlocks.OTHERWORLD_OAK_STAIRS.asItem(), ModDecorativeBlocks.OTHERWORLD_OAK_PLANKS.asItem()).save(consumer, location("decoration/carpentry/otherworld_oak/stairs"));
        simpleStairs(ModDecorativeBlocks.TWILIGHT_STAIRS.asItem(), ModDecorativeBlocks.TWILIGHT_PLANKS.asItem()).save(consumer, location("decoration/carpentry/twilight/stairs"));
        simpleStairs(ModDecorativeBlocks.HOLLOW_STAIRS.asItem(), ModDecorativeBlocks.HOLLOW_PLANKS.asItem()).save(consumer, location("decoration/carpentry/hollow/stairs"));

        simpleSlab(ModDecorativeBlocks.OTHERWORLD_OAK_SLAB.asItem(), ModDecorativeBlocks.OTHERWORLD_OAK_PLANKS.asItem()).save(consumer, location("decoration/carpentry/otherworld_oak/slab"));
        simpleSlab(ModDecorativeBlocks.TWILIGHT_SLAB.asItem(), ModDecorativeBlocks.TWILIGHT_PLANKS.asItem()).save(consumer, location("decoration/carpentry/twilight/slab"));
        simpleSlab(ModDecorativeBlocks.HOLLOW_SLAB.asItem(), ModDecorativeBlocks.HOLLOW_PLANKS.asItem()).save(consumer, location("decoration/carpentry/hollow/slab"));

        simpleFence(ModDecorativeBlocks.OTHERWORLD_OAK_FENCE.asItem(), ModDecorativeBlocks.OTHERWORLD_OAK_PLANKS.asItem()).save(consumer, location("decoration/carpentry/otherworld_oak/fence"));
        simpleFence(ModDecorativeBlocks.TWILIGHT_FENCE.asItem(), ModDecorativeBlocks.TWILIGHT_PLANKS.asItem()).save(consumer, location("decoration/carpentry/twilight/fence"));
        simpleFence(ModDecorativeBlocks.HOLLOW_FENCE.asItem(), ModDecorativeBlocks.HOLLOW_PLANKS.asItem()).save(consumer, location("decoration/carpentry/hollow/fence"));

        simpleFenceGate(ModDecorativeBlocks.OTHERWORLD_OAK_FENCE_GATE.asItem(), ModDecorativeBlocks.OTHERWORLD_OAK_PLANKS.asItem()).save(consumer, location("decoration/carpentry/otherworld_oak/fence_gate"));
        simpleFenceGate(ModDecorativeBlocks.TWILIGHT_FENCE_GATE.asItem(), ModDecorativeBlocks.TWILIGHT_PLANKS.asItem()).save(consumer, location("decoration/carpentry/twilight/fence_gate"));
        simpleFenceGate(ModDecorativeBlocks.HOLLOW_FENCE_GATE.asItem(), ModDecorativeBlocks.HOLLOW_PLANKS.asItem()).save(consumer, location("decoration/carpentry/hollow/fence_gate"));

        simpleDoor(ModDecorativeBlocks.OTHERWORLD_OAK_DOOR.asItem(), ModDecorativeBlocks.OTHERWORLD_OAK_PLANKS.asItem()).save(consumer, location("decoration/carpentry/otherworld_oak/door"));
        simpleDoor(ModDecorativeBlocks.TWILIGHT_DOOR.asItem(), ModDecorativeBlocks.TWILIGHT_PLANKS.asItem()).save(consumer, location("decoration/carpentry/twilight/door"));
        simpleDoor(ModDecorativeBlocks.HOLLOW_DOOR.asItem(), ModDecorativeBlocks.HOLLOW_PLANKS.asItem()).save(consumer, location("decoration/carpentry/hollow/door"));

        simpleTrapdoor(ModDecorativeBlocks.OTHERWORLD_OAK_TRAPDOOR.asItem(), ModDecorativeBlocks.OTHERWORLD_OAK_PLANKS.asItem()).save(consumer, location("decoration/carpentry/otherworld_oak/trapdoor"));
        simpleTrapdoor(ModDecorativeBlocks.TWILIGHT_TRAPDOOR.asItem(), ModDecorativeBlocks.TWILIGHT_PLANKS.asItem()).save(consumer, location("decoration/carpentry/twilight/trapdoor"));
        simpleTrapdoor(ModDecorativeBlocks.HOLLOW_TRAPDOOR.asItem(), ModDecorativeBlocks.HOLLOW_PLANKS.asItem()).save(consumer, location("decoration/carpentry/hollow/trapdoor"));

        simpleSign(ModItems.OTHERWORLD_OAK_SIGN.get(), ModDecorativeBlocks.OTHERWORLD_OAK_PLANKS.asItem()).save(consumer, location("decoration/carpentry/otherworld_oak/sign"));
        simpleSign(ModItems.TWILIGHT_SIGN.get(), ModDecorativeBlocks.TWILIGHT_PLANKS.asItem()).save(consumer, location("decoration/carpentry/twilight/sign"));
        simpleSign(ModItems.HOLLOW_SIGN.get(), ModDecorativeBlocks.HOLLOW_PLANKS.asItem()).save(consumer, location("decoration/carpentry/hollow/sign"));

        simpleHangingSign(ModItems.OTHERWORLD_OAK_HANGING_SIGN.get(), ModDecorativeBlocks.STRIPPED_OTHERWORLD_OAK_LOG.asItem()).save(consumer, location("decoration/carpentry/otherworld_oak/hanging_sign"));
        simpleHangingSign(ModItems.TWILIGHT_HANGING_SIGN.get(), ModDecorativeBlocks.STRIPPED_TWILIGHT_LOG.asItem()).save(consumer, location("decoration/carpentry/twilight/hanging_sign"));
        simpleHangingSign(ModItems.HOLLOW_HANGING_SIGN.get(), ModDecorativeBlocks.STRIPPED_HOLLOW_LOG.asItem()).save(consumer, location("decoration/carpentry/hollow/hanging_sign"));

        simpleShapeless(ModDecorativeBlocks.OTHERWORLD_OAK_BUTTON.asItem(), ModDecorativeBlocks.OTHERWORLD_OAK_PLANKS.asItem(), 1).save(consumer, location("decoration/carpentry/otherworld_oak/button"));
        simpleShapeless(ModDecorativeBlocks.TWILIGHT_BUTTON.asItem(), ModDecorativeBlocks.TWILIGHT_PLANKS.asItem(), 1).save(consumer, location("decoration/carpentry/twilight/button"));
        simpleShapeless(ModDecorativeBlocks.HOLLOW_BUTTON.asItem(), ModDecorativeBlocks.HOLLOW_PLANKS.asItem(), 1).save(consumer, location("decoration/carpentry/hollow/button"));

        simpleShapeless(ModDecorativeBlocks.OTHERWORLD_OAK_PLANKS.asItem(), ModDecorativeBlocks.OTHERWORLD_OAK_LOG.asItem(), 4).save(consumer, location("decoration/carpentry/otherworld_oak/planks"));
        simpleShapeless(ModDecorativeBlocks.TWILIGHT_PLANKS.asItem(), ModDecorativeBlocks.TWILIGHT_LOG.asItem(), 4).save(consumer, location("decoration/carpentry/twilight/planks"));
        simpleShapeless(ModDecorativeBlocks.HOLLOW_PLANKS.asItem(), ModDecorativeBlocks.HOLLOW_LOG.asItem(), 4).save(consumer, location("decoration/carpentry/hollow/planks"));

        simpleStairs(ModDecorativeBlocks.LARGE_OTHERWORLD_OAK_STAIRS.asItem(), ModDecorativeBlocks.LARGE_OTHERWORLD_OAK_PLANKS.asItem()).save(consumer, location("decoration/carpentry/otherworld_oak/large_stairs"));
        simpleStairs(ModDecorativeBlocks.LARGE_TWILIGHT_STAIRS.asItem(), ModDecorativeBlocks.LARGE_TWILIGHT_PLANKS.asItem()).save(consumer, location("decoration/carpentry/twilight/large_stairs"));
        simpleStairs(ModDecorativeBlocks.LARGE_HOLLOW_STAIRS.asItem(), ModDecorativeBlocks.LARGE_HOLLOW_PLANKS.asItem()).save(consumer, location("decoration/carpentry/hollow/large_stairs"));
        simpleStairs(ModDecorativeBlocks.LARGE_ACACIA_STAIRS.asItem(), ModDecorativeBlocks.LARGE_ACACIA_PLANKS.asItem()).save(consumer, location("decoration/carpentry/acacia/large_stairs"));
        simpleStairs(ModDecorativeBlocks.LARGE_BIRCH_STAIRS.asItem(), ModDecorativeBlocks.LARGE_BIRCH_PLANKS.asItem()).save(consumer, location("decoration/carpentry/birch/large_stairs"));
        simpleStairs(ModDecorativeBlocks.LARGE_CRIMSON_STAIRS.asItem(), ModDecorativeBlocks.LARGE_CRIMSON_PLANKS.asItem()).save(consumer, location("decoration/carpentry/crimson/large_stairs"));
        simpleStairs(ModDecorativeBlocks.LARGE_DARK_OAK_STAIRS.asItem(), ModDecorativeBlocks.LARGE_DARK_OAK_PLANKS.asItem()).save(consumer, location("decoration/carpentry/dark_oak/large_stairs"));
        simpleStairs(ModDecorativeBlocks.LARGE_JUNGLE_STAIRS.asItem(), ModDecorativeBlocks.LARGE_JUNGLE_PLANKS.asItem()).save(consumer, location("decoration/carpentry/jungle/large_stairs"));
        simpleStairs(ModDecorativeBlocks.LARGE_OAK_STAIRS.asItem(), ModDecorativeBlocks.LARGE_OAK_PLANKS.asItem()).save(consumer, location("decoration/carpentry/oak/large_stairs"));
        simpleStairs(ModDecorativeBlocks.LARGE_SPRUCE_STAIRS.asItem(), ModDecorativeBlocks.LARGE_SPRUCE_PLANKS.asItem()).save(consumer, location("decoration/carpentry/spruce/large_stairs"));
        simpleStairs(ModDecorativeBlocks.LARGE_WARPED_STAIRS.asItem(), ModDecorativeBlocks.LARGE_WARPED_PLANKS.asItem()).save(consumer, location("decoration/carpentry/warped/large_stairs"));
        simpleStairs(ModDecorativeBlocks.LARGE_MANGROVE_STAIRS.asItem(), ModDecorativeBlocks.LARGE_MANGROVE_PLANKS.asItem()).save(consumer, location("decoration/carpentry/mangrove/large_stairs"));
        simpleStairs(ModDecorativeBlocks.LARGE_CHERRY_STAIRS.asItem(), ModDecorativeBlocks.LARGE_CHERRY_PLANKS.asItem()).save(consumer, location("decoration/carpentry/cherry/large_stairs"));

        simpleSlab(ModDecorativeBlocks.LARGE_OTHERWORLD_OAK_SLAB.asItem(), ModDecorativeBlocks.LARGE_OTHERWORLD_OAK_PLANKS.asItem()).save(consumer, location("decoration/carpentry/otherworld_oak/large_slab"));
        simpleSlab(ModDecorativeBlocks.LARGE_TWILIGHT_SLAB.asItem(), ModDecorativeBlocks.LARGE_TWILIGHT_PLANKS.asItem()).save(consumer, location("decoration/carpentry/twilight/large_slab"));
        simpleSlab(ModDecorativeBlocks.LARGE_HOLLOW_SLAB.asItem(), ModDecorativeBlocks.LARGE_HOLLOW_PLANKS.asItem()).save(consumer, location("decoration/carpentry/hollow/large_slab"));
        simpleSlab(ModDecorativeBlocks.LARGE_ACACIA_SLAB.asItem(), ModDecorativeBlocks.LARGE_ACACIA_PLANKS.asItem()).save(consumer, location("decoration/carpentry/acacia/large_slab"));
        simpleSlab(ModDecorativeBlocks.LARGE_BIRCH_SLAB.asItem(), ModDecorativeBlocks.LARGE_BIRCH_PLANKS.asItem()).save(consumer, location("decoration/carpentry/birch/large_slab"));
        simpleSlab(ModDecorativeBlocks.LARGE_CRIMSON_SLAB.asItem(), ModDecorativeBlocks.LARGE_CRIMSON_PLANKS.asItem()).save(consumer, location("decoration/carpentry/crimson/large_slab"));
        simpleSlab(ModDecorativeBlocks.LARGE_DARK_OAK_SLAB.asItem(), ModDecorativeBlocks.LARGE_DARK_OAK_PLANKS.asItem()).save(consumer, location("decoration/carpentry/dark_oak/large_slab"));
        simpleSlab(ModDecorativeBlocks.LARGE_JUNGLE_SLAB.asItem(), ModDecorativeBlocks.LARGE_JUNGLE_PLANKS.asItem()).save(consumer, location("decoration/carpentry/jungle/large_slab"));
        simpleSlab(ModDecorativeBlocks.LARGE_OAK_SLAB.asItem(), ModDecorativeBlocks.LARGE_OAK_PLANKS.asItem()).save(consumer, location("decoration/carpentry/oak/large_slab"));
        simpleSlab(ModDecorativeBlocks.LARGE_SPRUCE_SLAB.asItem(), ModDecorativeBlocks.LARGE_SPRUCE_PLANKS.asItem()).save(consumer, location("decoration/carpentry/spruce/large_slab"));
        simpleSlab(ModDecorativeBlocks.LARGE_WARPED_SLAB.asItem(), ModDecorativeBlocks.LARGE_WARPED_PLANKS.asItem()).save(consumer, location("decoration/carpentry/warped/large_slab"));
        simpleSlab(ModDecorativeBlocks.LARGE_MANGROVE_SLAB.asItem(), ModDecorativeBlocks.LARGE_MANGROVE_PLANKS.asItem()).save(consumer, location("decoration/carpentry/mangrove/large_slab"));
        simpleSlab(ModDecorativeBlocks.LARGE_CHERRY_SLAB.asItem(), ModDecorativeBlocks.LARGE_CHERRY_PLANKS.asItem()).save(consumer, location("decoration/carpentry/cherry/large_slab"));
        
        //STONE STUFFS
        compressedBlock(ModDecorativeBlocks.STONE_TILES.asItem(), Items.STONE_BRICKS, true, 4).save(consumer, location("decoration/masonry/stone/tiles"));
        compressedBlock(ModDecorativeBlocks.ENDSTONE_TILES.asItem(), Items.END_STONE_BRICKS, true, 4).save(consumer, location("decoration/masonry/endstone/tiles"));
        compressedBlock(ModDecorativeBlocks.MUD_TILES.asItem(), Items.MUD_BRICKS, true, 4).save(consumer, location("decoration/masonry/mud/tiles"));
        compressedBlock(ModDecorativeBlocks.BLACKSTONE_TILES.asItem(), Items.POLISHED_BLACKSTONE_BRICKS, true, 4).save(consumer, location("decoration/masonry/blackstone/tiles"));
        compressedBlock(ModDecorativeBlocks.COBBLESTONE_BRICKS.asItem(), Items.COBBLESTONE, true, 4).save(consumer, location("decoration/masonry/cobblestone/bricks"));
        
        simpleStairs(ModDecorativeBlocks.COBBLESTONE_BRICK_STAIRS.asItem(), ModDecorativeBlocks.COBBLESTONE_BRICKS.asItem()).save(consumer, location("decoration/masonry/cobblestone/brick/stairs"));
        
        simpleSlab(ModDecorativeBlocks.COBBLESTONE_BRICK_SLAB.asItem(), ModDecorativeBlocks.COBBLESTONE_BRICKS.asItem()).save(consumer, location("decoration/masonry/cobblestone/brick/slab"));
        
        simpleWall(ModDecorativeBlocks.COBBLESTONE_BRICK_WALL.asItem(), ModDecorativeBlocks.COBBLESTONE_BRICKS.asItem()).save(consumer, location("decoration/masonry/cobblestone/brick/wall"));
    }
    private static void recipesItems(RecipeOutput consumer) {
        decompressedBlock(ModItems.GARNET.get(), ModBlocks.GARNET_BLOCK.asItem(), false).save(consumer, location("materials/garnet/from_block"));
        decompressedBlock(ModItems.JADE.get(), ModBlocks.JADE_BLOCK.asItem(), false).save(consumer, location("materials/jade/from_block"));
        decompressedBlock(ModItems.KUNZITE.get(), ModBlocks.KUNZITE_BLOCK.asItem(), false).save(consumer, location("materials/kunzite/from_block"));
        decompressedBlock(ModItems.MOONSTONE.get(), ModBlocks.MOONSTONE_BLOCK.asItem(), false).save(consumer, location("materials/moonstone/from_block"));
        decompressedBlock(ModItems.OPAL.get(), ModBlocks.OPAL_BLOCK.asItem(), false).save(consumer, location("materials/opal/from_block"));
        decompressedBlock(ModItems.RUBY.get(), ModBlocks.RUBY_BLOCK.asItem(), false).save(consumer, location("materials/ruby/from_block"));
        decompressedBlock(ModItems.SAPPHIRE.get(), ModBlocks.SAPPHIRE_BLOCK.asItem(), false).save(consumer, location("materials/sapphire/from_block"));
        decompressedBlock(ModItems.SPINEL.get(), ModBlocks.SPINEL_BLOCK.asItem(), false).save(consumer, location("materials/spinel/from_block"));
        decompressedBlock(ModItems.SUNSTONE.get(), ModBlocks.SUNSTONE_BLOCK.asItem(), false).save(consumer, location("materials/sunstone/from_block"));
        decompressedBlock(ModItems.TANZANITE.get(), ModBlocks.TANZANITE_BLOCK.asItem(), false).save(consumer, location("materials/tanzanite/from_block"));
        decompressedBlock(ModItems.TOPAZ.get(), ModBlocks.TOPAZ_BLOCK.asItem(), false).save(consumer, location("materials/topaz/from_block"));
        decompressedBlock(ModItems.URANIUM_INGOT.get(), ModBlocks.URANIUM_BLOCK.asItem(), false).save(consumer, location("materials/uranium/ingot/from_block"));
        decompressedBlock(ModItems.RAW_URANIUM.get(), ModBlocks.RAW_URANIUM_BLOCK.asItem(), false).save(consumer, location("materials/uranium/raw/from_block"));
        decompressedBlock(ModItems.URANIUM_NUGGET.get(), ModItems.URANIUM_INGOT.get(), false).save(consumer, location("materials/uranium/nugget"));
        decompressedBlock(ModItems.PALLADIUM_INGOT.get(), ModBlocks.PALLADIUM_BLOCK.asItem(), false).save(consumer, location("materials/palladium/ingot/from_block"));
        decompressedBlock(ModItems.RAW_PALLADIUM.get(), ModBlocks.RAW_PALLADIUM_BLOCK.asItem(), false).save(consumer, location("materials/palladium/raw/from_block"));
        decompressedBlock(ModItems.PALLADIUM_NUGGET.get(), ModItems.PALLADIUM_INGOT.get(), false).save(consumer, location("materials/palladium/nugget"));
        
        compressedBlock(ModItems.URANIUM_INGOT.get(), ModItems.URANIUM_NUGGET.get(), false).save(consumer, location("materials/uranium/ingot/from_nuggets"));
        compressedBlock(ModItems.PALLADIUM_INGOT.get(), ModItems.PALLADIUM_NUGGET.get(), false).save(consumer, location("materials/palladium/ingot/from_nuggets"));
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
                .save(consumer, location("functional/blast_furnace/deepslate"));
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SAWMILL.asItem(), 1)
                .pattern(" I ")
                .pattern("LPL")
                .define('I', ModTags.forgeItemTag("ingots/iron"))
                .define('L', ItemTags.LOGS)
                .define('P', ItemTags.PLANKS)
                .unlockedBy("has_planks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.OAK_PLANKS))
                .save(consumer, location("functional/sawmill/block"));
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.METAL_GRINDER.asItem(), 1)
                .pattern(" I ")
                .pattern("SBS")
                .define('I', ModTags.forgeItemTag("ingots/iron"))
                .define('S', Items.SMOOTH_STONE)
                .define('B', Items.STONE_BRICKS)
                .unlockedBy("has_stone_bricks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE_BRICKS))
                .save(consumer, location("functional/metal_grinder/block"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.IRRADIATED_ARROW.asItem(), 4)
                .pattern(" U ")
                .pattern("UAU")
                .pattern(" U ")
                .define('U', ModItems.URANIUM_NUGGET)
                .define('A', Items.ARROW)
                .unlockedBy("has_nugget", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.URANIUM_NUGGET))
                .save(consumer, location("combat/arrows/irradiated"));
    }
    private static void recipesEquipment(RecipeOutput consumer) {
        simpleSword(ModItems.COPPER_SWORD.get(), Items.COPPER_INGOT).save(consumer, location("equipment/copper/sword"));
        simpleSword(ModItems.PLATINUM_SWORD.get(), ModItems.PLATINUM_INGOT.get()).save(consumer, location("equipment/platinum/sword"));

        simpleAxe(ModItems.COPPER_AXE.get(), Items.COPPER_INGOT).save(consumer, location("equipment/copper/axe"));
        simpleAxe(ModItems.PLATINUM_AXE.get(), ModItems.PLATINUM_INGOT.get()).save(consumer, location("equipment/platinum/axe"));

        simplePickaxe(ModItems.COPPER_PICKAXE.get(), Items.COPPER_INGOT).save(consumer, location("equipment/copper/pickaxe"));
        simplePickaxe(ModItems.PLATINUM_PICKAXE.get(), ModItems.PLATINUM_INGOT.get()).save(consumer, location("equipment/platinum/pickaxe"));

        simpleShovel(ModItems.COPPER_SHOVEL.get(), Items.COPPER_INGOT).save(consumer, location("equipment/copper/shovel"));
        simpleShovel(ModItems.PLATINUM_SHOVEL.get(), ModItems.PLATINUM_INGOT.get()).save(consumer, location("equipment/platinum/shovel"));

        simpleHoe(ModItems.COPPER_HOE.get(), Items.COPPER_INGOT).save(consumer, location("equipment/copper/hoe"));
        simpleHoe(ModItems.PLATINUM_HOE.get(), ModItems.PLATINUM_INGOT.get()).save(consumer, location("equipment/platinum/hoe"));
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
    public static ShapedRecipeBuilder simpleSword(Item result, ItemLike input) {
        String inputName = input.asItem().toString().split(":")[1];
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1).pattern(" # ").pattern(" # ").pattern(" S ").define('#', input).define('S', Items.STICK).unlockedBy("has_" + inputName, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    public static ShapedRecipeBuilder simpleAxe(Item result, ItemLike input) {
        String inputName = input.asItem().toString().split(":")[1];
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1).pattern("## ").pattern("#S ").pattern(" S ").define('#', input).define('S', Items.STICK).unlockedBy("has_" + inputName, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    public static ShapedRecipeBuilder simplePickaxe(Item result, ItemLike input) {
        String inputName = input.asItem().toString().split(":")[1];
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1).pattern("###").pattern(" S ").pattern(" S ").define('#', input).define('S', Items.STICK).unlockedBy("has_" + inputName, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    public static ShapedRecipeBuilder simpleShovel(Item result, ItemLike input) {
        String inputName = input.asItem().toString().split(":")[1];
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1).pattern(" # ").pattern(" S ").pattern(" S ").define('#', input).define('S', Items.STICK).unlockedBy("has_" + inputName, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    public static ShapedRecipeBuilder simpleHoe(Item result, ItemLike input) {
        String inputName = input.asItem().toString().split(":")[1];
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1).pattern("## ").pattern(" S ").pattern(" S ").define('#', input).define('S', Items.STICK).unlockedBy("has_" + inputName, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
}
