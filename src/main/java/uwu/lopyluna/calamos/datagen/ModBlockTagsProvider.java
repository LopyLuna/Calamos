package uwu.lopyluna.calamos.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import uwu.lopyluna.calamos.elements.ModBlocks;
import uwu.lopyluna.calamos.elements.ModDecorativeBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, modId, existingFileHelper);
    }
    
    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.registerBlockMineables();
        this.registerMinecraftTags();
        this.registerForgeTags();
        this.registerModTags();
    }
    
    protected void registerModTags() {
        tag(ModTags.modBlockTag("large_planks")).add(
                ModDecorativeBlocks.LARGE_ACACIA_PLANKS.get(),
                ModDecorativeBlocks.LARGE_BIRCH_PLANKS.get(),
                ModDecorativeBlocks.LARGE_CRIMSON_PLANKS.get(),
                ModDecorativeBlocks.LARGE_DARK_OAK_PLANKS.get(),
                ModDecorativeBlocks.LARGE_JUNGLE_PLANKS.get(),
                ModDecorativeBlocks.LARGE_OAK_PLANKS.get(),
                ModDecorativeBlocks.LARGE_SPRUCE_PLANKS.get(),
                ModDecorativeBlocks.LARGE_WARPED_PLANKS.get()
        );
        tag(ModTags.modBlockTag("tree_attachments")).add(
                Blocks.BEE_NEST,
                Blocks.COCOA,
                Blocks.MOSS_CARPET,
                Blocks.SHROOMLIGHT,
                Blocks.VINE
        );
    }
    
    protected void registerBlockMineables() {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                ModBlocks.GARNET_BLOCK.get(),
                ModBlocks.JADE_BLOCK.get(),
                ModBlocks.KUNZITE_BLOCK.get(),
                ModBlocks.MOONSTONE_BLOCK.get(),
                ModBlocks.OPAL_BLOCK.get(),
                ModBlocks.RUBY_BLOCK.get(),
                ModBlocks.SAPPHIRE_BLOCK.get(),
                ModBlocks.SPINEL_BLOCK.get(),
                ModBlocks.SUNSTONE_BLOCK.get(),
                ModBlocks.TANZANITE_BLOCK.get(),
                ModBlocks.TOPAZ_BLOCK.get(),
                ModBlocks.URANIUM_BLOCK.get(),
                ModBlocks.PALLADIUM_ORE.get(),
                ModDecorativeBlocks.METEORITE.get(),
                ModDecorativeBlocks.METEORITE_STAIRS.get(),
                ModDecorativeBlocks.METEORITE_SLAB.get(),
                ModDecorativeBlocks.COBBLED_METEORITE.get(),
                ModDecorativeBlocks.POLISHED_METEORITE.get(),
                ModDecorativeBlocks.POLISHED_METEORITE_STAIRS.get(),
                ModDecorativeBlocks.POLISHED_METEORITE_SLAB.get(),
                ModDecorativeBlocks.METEORITE_BRICKS.get(),
                ModDecorativeBlocks.GILDED_METEORITE_BRICKS.get(),
                ModDecorativeBlocks.METEORITE_TILES.get(),
                ModDecorativeBlocks.LARGE_METEORITE_TILE.get(),
                ModDecorativeBlocks.CUT_METEORITE.get(),
                ModDecorativeBlocks.SMOOTH_METEORITE.get(),
                ModDecorativeBlocks.SMOOTH_METEORITE_STAIRS.get(),
                ModDecorativeBlocks.SMOOTH_METEORITE_SLAB.get(),
                ModDecorativeBlocks.SOUL_SANDSTONE.get(),
                ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE.get(),
                ModDecorativeBlocks.COBBLED_SANDSTONE.get(),
                
                ModBlocks.COPPER_ORE.get(),
                ModBlocks.METEORITE_ORE.get(),
                ModBlocks.GARNET_ORE.get(),
                ModBlocks.URANIUM_ORE.get(),
                ModBlocks.GOLD_ORE.get(),
                ModBlocks.IRON_ORE.get(),
                ModBlocks.OPAL_ORE.get(),
                ModBlocks.JADE_ORE.get(),
                ModBlocks.KUNZITE_ORE.get(),
                ModBlocks.RUBY_ORE.get(),
                ModBlocks.MOONSTONE_ORE.get(),
                ModBlocks.SAPPHIRE_ORE.get(),
                ModBlocks.SPINEL_ORE.get(),
                ModBlocks.PALLADIUM_ORE.get(),

                ModBlocks.STONE.get(),
                ModBlocks.UMBRALITE.get(),
                ModBlocks.PURRASITE.get(),
                
                ModDecorativeBlocks.AMETHYST_BRICKS.get(),
                ModDecorativeBlocks.CRYING_OBSIDIAN_BRICKS.get(),
                ModDecorativeBlocks.OBSIDIAN_BRICKS.get(),
                ModDecorativeBlocks.COBBLESTONE_BRICKS.get(),
                ModDecorativeBlocks.BASALT_BRICKS.get(),
                ModDecorativeBlocks.BONE_BRICKS.get(),
                ModDecorativeBlocks.NETHERRACK_BRICKS.get(),
                
                ModDecorativeBlocks.POLISHED_AMETHYST.get(),
                ModDecorativeBlocks.POLISHED_STONE.get(),
                ModDecorativeBlocks.POLISHED_NETHERRACK.get(),
                
                ModDecorativeBlocks.CUT_AMETHYST.get(),
                ModDecorativeBlocks.CUT_BASALT.get(),
                
                ModDecorativeBlocks.STONE_TILES.get(),
                ModDecorativeBlocks.BLACKSTONE_TILES.get(),
                
                ModDecorativeBlocks.SMOOTH_COBBLESTONE.get(),
                ModDecorativeBlocks.SMOOTH_DEEPSLATE.get(),
                ModDecorativeBlocks.SMOOTH_NETHERRACK.get(),
                
                ModDecorativeBlocks.CHISELED_RED_NETHER_BRICKS.get(),
                ModDecorativeBlocks.CHISELED_BONE.get(),
                
                ModDecorativeBlocks.LARGE_BRICKS.get(),
                
                ModBlocks.METAL_GRINDER.get()
        );
        
        tag(BlockTags.MINEABLE_WITH_AXE).add(
                ModDecorativeBlocks.OTHERWORLD_OAK_PLANKS.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_STAIRS.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_SLAB.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_FENCE.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_FENCE_GATE.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_LOG.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_WOOD.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_DOOR.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_TRAPDOOR.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_BUTTON.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_PRESSURE_PLATE.get(),
                ModDecorativeBlocks.STRIPPED_OTHERWORLD_OAK_LOG.get(),
                ModDecorativeBlocks.STRIPPED_OTHERWORLD_OAK_WOOD.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_SIGN.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_WALL_SIGN.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_HANGING_SIGN.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_WALL_HANGING_SIGN.get(),
                ModDecorativeBlocks.TWILIGHT_PLANKS.get(),
                ModDecorativeBlocks.TWILIGHT_STAIRS.get(),
                ModDecorativeBlocks.TWILIGHT_SLAB.get(),
                ModDecorativeBlocks.TWILIGHT_FENCE.get(),
                ModDecorativeBlocks.TWILIGHT_FENCE_GATE.get(),
                ModDecorativeBlocks.TWILIGHT_LOG.get(),
                ModDecorativeBlocks.TWILIGHT_WOOD.get(),
                ModDecorativeBlocks.TWILIGHT_DOOR.get(),
                ModDecorativeBlocks.TWILIGHT_TRAPDOOR.get(),
                ModDecorativeBlocks.TWILIGHT_BUTTON.get(),
                ModDecorativeBlocks.TWILIGHT_PRESSURE_PLATE.get(),
                ModDecorativeBlocks.STRIPPED_TWILIGHT_LOG.get(),
                ModDecorativeBlocks.STRIPPED_TWILIGHT_WOOD.get(),
                ModDecorativeBlocks.TWILIGHT_SIGN.get(),
                ModDecorativeBlocks.TWILIGHT_WALL_SIGN.get(),
                ModDecorativeBlocks.TWILIGHT_HANGING_SIGN.get(),
                ModDecorativeBlocks.TWILIGHT_WALL_HANGING_SIGN.get(),
                ModDecorativeBlocks.TWILIGHT_PLANKS.get(),
                ModDecorativeBlocks.TWILIGHT_STAIRS.get(),
                ModDecorativeBlocks.TWILIGHT_SLAB.get(),
                ModDecorativeBlocks.TWILIGHT_FENCE.get(),
                ModDecorativeBlocks.TWILIGHT_FENCE_GATE.get(),
                ModDecorativeBlocks.TWILIGHT_LOG.get(),
                ModDecorativeBlocks.TWILIGHT_WOOD.get(),
                ModDecorativeBlocks.TWILIGHT_DOOR.get(),
                ModDecorativeBlocks.TWILIGHT_TRAPDOOR.get(),
                ModDecorativeBlocks.TWILIGHT_BUTTON.get(),
                ModDecorativeBlocks.TWILIGHT_PRESSURE_PLATE.get(),
                ModDecorativeBlocks.STRIPPED_TWILIGHT_LOG.get(),
                ModDecorativeBlocks.STRIPPED_TWILIGHT_WOOD.get(),
                ModDecorativeBlocks.TWILIGHT_SIGN.get(),
                ModDecorativeBlocks.TWILIGHT_WALL_SIGN.get(),
                ModDecorativeBlocks.TWILIGHT_HANGING_SIGN.get(),
                ModDecorativeBlocks.TWILIGHT_WALL_HANGING_SIGN.get(),

                ModDecorativeBlocks.HOLLOW_PLANKS.get(),
                ModDecorativeBlocks.HOLLOW_STAIRS.get(),
                ModDecorativeBlocks.HOLLOW_SLAB.get(),
                ModDecorativeBlocks.HOLLOW_FENCE.get(),
                ModDecorativeBlocks.HOLLOW_FENCE_GATE.get(),
                ModDecorativeBlocks.HOLLOW_LOG.get(),
                ModDecorativeBlocks.HOLLOW_WOOD.get(),
                ModDecorativeBlocks.HOLLOW_DOOR.get(),
                ModDecorativeBlocks.HOLLOW_TRAPDOOR.get(),
                ModDecorativeBlocks.HOLLOW_BUTTON.get(),
                ModDecorativeBlocks.HOLLOW_PRESSURE_PLATE.get(),
                ModDecorativeBlocks.STRIPPED_HOLLOW_LOG.get(),
                ModDecorativeBlocks.STRIPPED_HOLLOW_WOOD.get(),
                ModDecorativeBlocks.HOLLOW_SIGN.get(),
                ModDecorativeBlocks.HOLLOW_WALL_SIGN.get(),
                ModDecorativeBlocks.HOLLOW_HANGING_SIGN.get(),
                ModDecorativeBlocks.HOLLOW_WALL_HANGING_SIGN.get(),
                
                //LARGE PLANKS
                ModDecorativeBlocks.LARGE_ACACIA_PLANKS.get(),
                ModDecorativeBlocks.LARGE_ACACIA_STAIRS.get(),
                ModDecorativeBlocks.LARGE_ACACIA_SLAB.get(),
                ModDecorativeBlocks.LARGE_BIRCH_PLANKS.get(),
                ModDecorativeBlocks.LARGE_BIRCH_STAIRS.get(),
                ModDecorativeBlocks.LARGE_BIRCH_SLAB.get(),
                ModDecorativeBlocks.LARGE_CRIMSON_PLANKS.get(),
                ModDecorativeBlocks.LARGE_CRIMSON_STAIRS.get(),
                ModDecorativeBlocks.LARGE_CRIMSON_SLAB.get(),
                ModDecorativeBlocks.LARGE_DARK_OAK_PLANKS.get(),
                ModDecorativeBlocks.LARGE_DARK_OAK_STAIRS.get(),
                ModDecorativeBlocks.LARGE_DARK_OAK_SLAB.get(),
                ModDecorativeBlocks.LARGE_JUNGLE_PLANKS.get(),
                ModDecorativeBlocks.LARGE_JUNGLE_STAIRS.get(),
                ModDecorativeBlocks.LARGE_JUNGLE_SLAB.get(),
                ModDecorativeBlocks.LARGE_OAK_PLANKS.get(),
                ModDecorativeBlocks.LARGE_OAK_STAIRS.get(),
                ModDecorativeBlocks.LARGE_OAK_SLAB.get(),
                ModDecorativeBlocks.LARGE_SPRUCE_PLANKS.get(),
                ModDecorativeBlocks.LARGE_SPRUCE_STAIRS.get(),
                ModDecorativeBlocks.LARGE_SPRUCE_SLAB.get(),
                ModDecorativeBlocks.LARGE_WARPED_PLANKS.get(),
                ModDecorativeBlocks.LARGE_WARPED_STAIRS.get(),
                ModDecorativeBlocks.LARGE_WARPED_SLAB.get(),
                
                ModBlocks.SAWMILL.get()
        );
        
        
    }
    protected void registerMinecraftTags() {
        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(
                ModDecorativeBlocks.CRYING_OBSIDIAN_BRICKS.get(),
                ModDecorativeBlocks.OBSIDIAN_BRICKS.get()
        );
        tag(BlockTags.DRAGON_IMMUNE).add(
                ModDecorativeBlocks.CRYING_OBSIDIAN_BRICKS.get(),
                ModDecorativeBlocks.OBSIDIAN_BRICKS.get()
        );
        tag(BlockTags.CRYSTAL_SOUND_BLOCKS).add(
                ModDecorativeBlocks.POLISHED_AMETHYST.get(),
                ModDecorativeBlocks.AMETHYST_BRICKS.get(),
                ModDecorativeBlocks.CUT_AMETHYST.get()
        );
        tag(BlockTags.COPPER_ORES).add(ModBlocks.COPPER_ORE.get());
        tag(BlockTags.SCULK_REPLACEABLE).add(ModDecorativeBlocks.SOUL_SANDSTONE.get());
        tag(BlockTags.SCULK_REPLACEABLE_WORLD_GEN).add(ModDecorativeBlocks.SOUL_SANDSTONE.get());
        tag(BlockTags.NETHER_CARVER_REPLACEABLES).add(ModDecorativeBlocks.SOUL_SANDSTONE.get());
        tag(BlockTags.SOUL_FIRE_BASE_BLOCKS).add(
                ModDecorativeBlocks.SOUL_SANDSTONE.get(),
                ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE.get(),
                ModDecorativeBlocks.SMOOTH_SOUL_SANDSTONE.get(),
                ModDecorativeBlocks.CUT_SOUL_SANDSTONE.get(),
                ModDecorativeBlocks.CHISELED_SOUL_SANDSTONE.get()
        );
        tag(BlockTags.INFINIBURN_END).add(
                ModDecorativeBlocks.NETHERRACK_BRICKS.get(),
                ModDecorativeBlocks.SMOOTH_NETHERRACK.get(),
                ModDecorativeBlocks.POLISHED_NETHERRACK.get()
        );
        tag(BlockTags.INFINIBURN_NETHER).add(
                ModDecorativeBlocks.NETHERRACK_BRICKS.get(),
                ModDecorativeBlocks.SMOOTH_NETHERRACK.get(),
                ModDecorativeBlocks.POLISHED_NETHERRACK.get()
        );
        tag(BlockTags.INFINIBURN_OVERWORLD).add(
                ModDecorativeBlocks.NETHERRACK_BRICKS.get(),
                ModDecorativeBlocks.SMOOTH_NETHERRACK.get(),
                ModDecorativeBlocks.POLISHED_NETHERRACK.get()
        );
        tag(BlockTags.WALLS).add(
                ModDecorativeBlocks.SOUL_SANDSTONE_WALL.get(),
                ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE_WALL.get(),
                ModDecorativeBlocks.COBBLED_SANDSTONE_WALL.get(),
                ModDecorativeBlocks.METEORITE_WALL.get(),
                ModDecorativeBlocks.SMOOTH_METEORITE_WALL.get(),
                ModDecorativeBlocks.POLISHED_METEORITE_WALL.get(),
                ModDecorativeBlocks.COBBLESTONE_BRICK_WALL.get()
        );
        tag(BlockTags.WOODEN_FENCES).add(
                ModDecorativeBlocks.OTHERWORLD_OAK_FENCE.get(),
                ModDecorativeBlocks.TWILIGHT_FENCE.get(),
                ModDecorativeBlocks.HOLLOW_FENCE.get()
        );
        tag(BlockTags.WOODEN_SLABS).add(
                ModDecorativeBlocks.OTHERWORLD_OAK_SLAB.get(),
                ModDecorativeBlocks.TWILIGHT_SLAB.get(),
                ModDecorativeBlocks.HOLLOW_SLAB.get(),

                ModDecorativeBlocks.LARGE_OAK_SLAB.get(),
                ModDecorativeBlocks.LARGE_SPRUCE_SLAB.get(),
                ModDecorativeBlocks.LARGE_BIRCH_SLAB.get(),
                ModDecorativeBlocks.LARGE_JUNGLE_SLAB.get(),
                ModDecorativeBlocks.LARGE_ACACIA_SLAB.get(),
                ModDecorativeBlocks.LARGE_DARK_OAK_SLAB.get(),
                ModDecorativeBlocks.LARGE_MANGROVE_SLAB.get(),
                ModDecorativeBlocks.LARGE_BAMBOO_SLAB.get(),
                ModDecorativeBlocks.LARGE_CRIMSON_SLAB.get(),
                ModDecorativeBlocks.LARGE_WARPED_SLAB.get()
        );
        tag(BlockTags.WOODEN_STAIRS).add(
                ModDecorativeBlocks.OTHERWORLD_OAK_STAIRS.get(),
                ModDecorativeBlocks.TWILIGHT_STAIRS.get(),
                ModDecorativeBlocks.HOLLOW_STAIRS.get(),

                ModDecorativeBlocks.LARGE_OAK_STAIRS.get(),
                ModDecorativeBlocks.LARGE_SPRUCE_STAIRS.get(),
                ModDecorativeBlocks.LARGE_BIRCH_STAIRS.get(),
                ModDecorativeBlocks.LARGE_JUNGLE_STAIRS.get(),
                ModDecorativeBlocks.LARGE_ACACIA_STAIRS.get(),
                ModDecorativeBlocks.LARGE_DARK_OAK_STAIRS.get(),
                ModDecorativeBlocks.LARGE_MANGROVE_STAIRS.get(),
                ModDecorativeBlocks.LARGE_BAMBOO_STAIRS.get(),
                ModDecorativeBlocks.LARGE_CRIMSON_STAIRS.get(),
                ModDecorativeBlocks.LARGE_WARPED_STAIRS.get()
        );
        tag(BlockTags.WOODEN_TRAPDOORS).add(
                ModDecorativeBlocks.OTHERWORLD_OAK_TRAPDOOR.get(),
                ModDecorativeBlocks.TWILIGHT_TRAPDOOR.get(),
                ModDecorativeBlocks.HOLLOW_TRAPDOOR.get()
        );
        tag(BlockTags.WOODEN_DOORS).add(
                ModDecorativeBlocks.OTHERWORLD_OAK_DOOR.get(),
                ModDecorativeBlocks.TWILIGHT_DOOR.get(),
                ModDecorativeBlocks.HOLLOW_DOOR.get()
        );
        tag(BlockTags.WOODEN_BUTTONS).add(
                ModDecorativeBlocks.OTHERWORLD_OAK_BUTTON.get(),
                ModDecorativeBlocks.TWILIGHT_BUTTON.get(),
                ModDecorativeBlocks.HOLLOW_BUTTON.get()
        );
        tag(BlockTags.WOODEN_PRESSURE_PLATES).add(
                ModDecorativeBlocks.OTHERWORLD_OAK_PRESSURE_PLATE.get(),
                ModDecorativeBlocks.HOLLOW_PRESSURE_PLATE.get(),
                ModDecorativeBlocks.HOLLOW_PRESSURE_PLATE.get()
        );
        tag(BlockTags.SIGNS).add(
                ModDecorativeBlocks.OTHERWORLD_OAK_SIGN.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_WALL_SIGN.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_HANGING_SIGN.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_WALL_HANGING_SIGN.get(),
                ModDecorativeBlocks.TWILIGHT_SIGN.get(),
                ModDecorativeBlocks.TWILIGHT_WALL_SIGN.get(),
                ModDecorativeBlocks.TWILIGHT_HANGING_SIGN.get(),
                ModDecorativeBlocks.TWILIGHT_WALL_HANGING_SIGN.get(),
                ModDecorativeBlocks.HOLLOW_SIGN.get(),
                ModDecorativeBlocks.HOLLOW_WALL_SIGN.get(),
                ModDecorativeBlocks.HOLLOW_HANGING_SIGN.get(),
                ModDecorativeBlocks.HOLLOW_WALL_HANGING_SIGN.get()
        );
        tag(BlockTags.FENCE_GATES).add(
                ModDecorativeBlocks.OTHERWORLD_OAK_FENCE_GATE.get(),
                ModDecorativeBlocks.TWILIGHT_FENCE_GATE.get(),
                ModDecorativeBlocks.HOLLOW_FENCE_GATE.get()
        );
        tag(BlockTags.SLABS).add(
                ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE_SLAB.get(),
                ModDecorativeBlocks.SOUL_SANDSTONE_SLAB.get(),
                ModDecorativeBlocks.SMOOTH_SOUL_SANDSTONE_SLAB.get(),
                ModDecorativeBlocks.CUT_SOUL_SANDSTONE_SLAB.get(),
                ModDecorativeBlocks.COBBLED_SANDSTONE_SLAB.get(),
                ModDecorativeBlocks.METEORITE_SLAB.get(),
                ModDecorativeBlocks.POLISHED_METEORITE_SLAB.get(),
                ModDecorativeBlocks.SMOOTH_METEORITE_SLAB.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_SLAB.get(),
                ModDecorativeBlocks.TWILIGHT_SLAB.get(),
                ModDecorativeBlocks.HOLLOW_SLAB.get(),

                ModDecorativeBlocks.LARGE_OAK_SLAB.get(),
                ModDecorativeBlocks.LARGE_SPRUCE_SLAB.get(),
                ModDecorativeBlocks.LARGE_BIRCH_SLAB.get(),
                ModDecorativeBlocks.LARGE_BAMBOO_SLAB.get(),
                ModDecorativeBlocks.LARGE_JUNGLE_SLAB.get(),
                ModDecorativeBlocks.LARGE_ACACIA_SLAB.get(),
                ModDecorativeBlocks.LARGE_DARK_OAK_SLAB.get(),
                ModDecorativeBlocks.LARGE_MANGROVE_SLAB.get(),
                ModDecorativeBlocks.LARGE_BAMBOO_SLAB.get(),
                ModDecorativeBlocks.LARGE_CRIMSON_SLAB.get(),
                ModDecorativeBlocks.LARGE_WARPED_SLAB.get(),

                ModDecorativeBlocks.COBBLESTONE_BRICK_SLAB.get()
        );
        tag(BlockTags.STAIRS).add(
                ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE_STAIRS.get(),
                ModDecorativeBlocks.SOUL_SANDSTONE_STAIRS.get(),
                ModDecorativeBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS.get(),
                ModDecorativeBlocks.COBBLED_SANDSTONE_STAIRS.get(),
                ModDecorativeBlocks.METEORITE_STAIRS.get(),
                ModDecorativeBlocks.POLISHED_METEORITE_STAIRS.get(),
                ModDecorativeBlocks.SMOOTH_METEORITE_STAIRS.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_STAIRS.get(),
                ModDecorativeBlocks.TWILIGHT_STAIRS.get(),
                ModDecorativeBlocks.HOLLOW_STAIRS.get(),

                ModDecorativeBlocks.LARGE_OAK_STAIRS.get(),
                ModDecorativeBlocks.LARGE_SPRUCE_STAIRS.get(),
                ModDecorativeBlocks.LARGE_BIRCH_STAIRS.get(),
                ModDecorativeBlocks.LARGE_BAMBOO_STAIRS.get(),
                ModDecorativeBlocks.LARGE_JUNGLE_STAIRS.get(),
                ModDecorativeBlocks.LARGE_ACACIA_STAIRS.get(),
                ModDecorativeBlocks.LARGE_DARK_OAK_STAIRS.get(),
                ModDecorativeBlocks.LARGE_MANGROVE_STAIRS.get(),
                ModDecorativeBlocks.LARGE_BAMBOO_STAIRS.get(),
                ModDecorativeBlocks.LARGE_CRIMSON_STAIRS.get(),
                ModDecorativeBlocks.LARGE_WARPED_STAIRS.get(),

                ModDecorativeBlocks.COBBLESTONE_BRICK_STAIRS.get()
        );
    }
    
    protected void registerForgeTags() {
        tag(ModTags.forgeBlockTag("cobbled_sandstone")).add(
                ModDecorativeBlocks.COBBLED_SANDSTONE.get(),
                ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE.get()
        );
        tag(ModTags.forgeBlockTag("cobbled_sandstone/normal")).add(
                ModDecorativeBlocks.COBBLED_SANDSTONE.get()
        );
        tag(ModTags.forgeBlockTag("cobbled_sandstone/soul")).add(
                ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE.get()
        );
        tag(ModTags.forgeBlockTag("soul_sandstone")).add(
                ModDecorativeBlocks.SOUL_SANDSTONE.get(),
                ModDecorativeBlocks.SMOOTH_SOUL_SANDSTONE.get(),
                ModDecorativeBlocks.CUT_SOUL_SANDSTONE.get(),
                ModDecorativeBlocks.CHISELED_SOUL_SANDSTONE.get()
        );
        tag(ModTags.forgeBlockTag("ores")).add(
                ModBlocks.COPPER_ORE.get(),
                ModBlocks.IRON_ORE.get(),
                ModBlocks.GOLD_ORE.get(),
                ModBlocks.METEORITE_ORE.get(),
                ModBlocks.GARNET_ORE.get(),
                ModBlocks.JADE_ORE.get(),
                ModBlocks.KUNZITE_ORE.get(),
                ModBlocks.MOONSTONE_ORE.get(),
                ModBlocks.OPAL_ORE.get(),
                ModBlocks.RUBY_ORE.get(),
                ModBlocks.SAPPHIRE_ORE.get(),
                ModBlocks.SPINEL_ORE.get(),
                ModBlocks.SUNSTONE_ORE.get(),
                ModBlocks.TANZANITE_ORE.get(),
                ModBlocks.TOPAZ_ORE.get(),
                ModBlocks.URANIUM_ORE.get(),
                ModBlocks.PALLADIUM_ORE.get()
        );
        tag(ModTags.forgeBlockTag("storage_blocks")).add(
                ModBlocks.GARNET_BLOCK.get(),
                ModBlocks.JADE_BLOCK.get(),
                ModBlocks.KUNZITE_BLOCK.get(),
                ModBlocks.MOONSTONE_BLOCK.get(),
                ModBlocks.OPAL_BLOCK.get(),
                ModBlocks.RUBY_BLOCK.get(),
                ModBlocks.SAPPHIRE_BLOCK.get(),
                ModBlocks.SPINEL_BLOCK.get(),
                ModBlocks.SUNSTONE_BLOCK.get(),
                ModBlocks.TANZANITE_BLOCK.get(),
                ModBlocks.TOPAZ_BLOCK.get(),
                ModBlocks.URANIUM_BLOCK.get(),
                ModBlocks.PALLADIUM_BLOCK.get()
        );
        tag(ModTags.forgeBlockTag("ores/copper")).add(ModBlocks.COPPER_ORE.get());
        tag(ModTags.forgeBlockTag("ores/iron")).add(ModBlocks.IRON_ORE.get());
        tag(ModTags.forgeBlockTag("ores/gold")).add(ModBlocks.GOLD_ORE.get());
        tag(ModTags.forgeBlockTag("ores/garnet")).add(ModBlocks.GARNET_ORE.get());
        tag(ModTags.forgeBlockTag("ores/jade")).add(ModBlocks.JADE_ORE.get());
        tag(ModTags.forgeBlockTag("ores/kunzite")).add(ModBlocks.KUNZITE_ORE.get());
        tag(ModTags.forgeBlockTag("ores/moonstone")).add(ModBlocks.MOONSTONE_ORE.get());
        tag(ModTags.forgeBlockTag("ores/opal")).add(ModBlocks.OPAL_ORE.get());
        tag(ModTags.forgeBlockTag("ores/ruby")).add(ModBlocks.RUBY_ORE.get());
        tag(ModTags.forgeBlockTag("ores/sapphire")).add(ModBlocks.SAPPHIRE_ORE.get());
        tag(ModTags.forgeBlockTag("ores/spinel")).add(ModBlocks.SPINEL_ORE.get());
        tag(ModTags.forgeBlockTag("ores/sunstone")).add(ModBlocks.SUNSTONE_ORE.get());
        tag(ModTags.forgeBlockTag("ores/tanzanite")).add(ModBlocks.TANZANITE_ORE.get());
        tag(ModTags.forgeBlockTag("ores/topaz")).add(ModBlocks.TOPAZ_ORE.get());
        tag(ModTags.forgeBlockTag("ores/uranium")).add(ModBlocks.URANIUM_ORE.get());
        tag(ModTags.forgeBlockTag("ores/palladium")).add(ModBlocks.PALLADIUM_ORE.get());

        tag(ModTags.forgeBlockTag("storage_blocks/garnet")).add(ModBlocks.GARNET_BLOCK.get());
        tag(ModTags.forgeBlockTag("storage_blocks/jade")).add(ModBlocks.JADE_BLOCK.get());
        tag(ModTags.forgeBlockTag("storage_blocks/kunzite")).add(ModBlocks.KUNZITE_BLOCK.get());
        tag(ModTags.forgeBlockTag("storage_blocks/moonstone")).add(ModBlocks.MOONSTONE_BLOCK.get());
        tag(ModTags.forgeBlockTag("storage_blocks/opal")).add(ModBlocks.OPAL_BLOCK.get());
        tag(ModTags.forgeBlockTag("storage_blocks/ruby")).add(ModBlocks.RUBY_BLOCK.get());
        tag(ModTags.forgeBlockTag("storage_blocks/sapphire")).add(ModBlocks.SAPPHIRE_BLOCK.get());
        tag(ModTags.forgeBlockTag("storage_blocks/spinel")).add(ModBlocks.SPINEL_BLOCK.get());
        tag(ModTags.forgeBlockTag("storage_blocks/sunstone")).add(ModBlocks.SUNSTONE_BLOCK.get());
        tag(ModTags.forgeBlockTag("storage_blocks/tanzanite")).add(ModBlocks.TANZANITE_BLOCK.get());
        tag(ModTags.forgeBlockTag("storage_blocks/topaz")).add(ModBlocks.TOPAZ_BLOCK.get());
        tag(ModTags.forgeBlockTag("storage_blocks/uranium")).add(ModBlocks.URANIUM_BLOCK.get());
        tag(ModTags.forgeBlockTag("storage_blocks/palladium")).add(ModBlocks.PALLADIUM_BLOCK.get());

    }
}
