package uwu.lopyluna.calamos.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
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
                ModDecorativeBlocks.METEORITE.get(),
                ModDecorativeBlocks.COBBLED_METEORITE.get(),
                ModDecorativeBlocks.POLISHED_METEORITE.get(),
                ModDecorativeBlocks.METEORITE_BRICKS.get(),
                ModDecorativeBlocks.GILDED_METEORITE_BRICKS.get(),
                ModDecorativeBlocks.METEORITE_TILES.get(),
                ModDecorativeBlocks.LARGE_METEORITE_TILE.get(),
                ModDecorativeBlocks.CUT_METEORITE.get(),
                ModDecorativeBlocks.SMOOTH_METEORITE.get(),
                ModDecorativeBlocks.SOUL_SANDSTONE.get(),
                ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE.get(),
                ModDecorativeBlocks.COBBLED_SANDSTONE.get(),
                
                ModBlocks.COPPER_ORE.get(),
                ModBlocks.METEORITE_ORE.get(),

                ModBlocks.STONE.get(),
                ModBlocks.UMBRALITE.get(),
                ModBlocks.PURRASITE.get()
        );
    }
    protected void registerMinecraftTags() {
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
        tag(BlockTags.WALLS).add(
                ModDecorativeBlocks.SOUL_SANDSTONE_WALL.get(),
                ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE_WALL.get(),
                ModDecorativeBlocks.COBBLED_SANDSTONE_WALL.get(),
                ModDecorativeBlocks.METEORITE_WALL.get(),
                ModDecorativeBlocks.SMOOTH_METEORITE_WALL.get(),
                ModDecorativeBlocks.POLISHED_METEORITE_WALL.get()
        );
        tag(BlockTags.WOODEN_FENCES).add(
                ModDecorativeBlocks.OTHERWORLD_OAK_FENCE.get(),
                ModDecorativeBlocks.TWILIGHT_FENCE.get()
        );
        tag(BlockTags.FENCE_GATES).add(
                ModDecorativeBlocks.OTHERWORLD_OAK_FENCE_GATE.get(),
                ModDecorativeBlocks.TWILIGHT_FENCE_GATE.get()
        );
        tag(BlockTags.SLABS).add(
                ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE_SLAB.get(),
                ModDecorativeBlocks.SOUL_SANDSTONE_SLAB.get(),
                ModDecorativeBlocks.SMOOTH_SOUL_SANDSTONE_SLAB.get(),
                ModDecorativeBlocks.CUT_SOUL_SANDSTONE_SLAB.get(),
                ModDecorativeBlocks.COBBLED_SANDSTONE_SLAB.get(),
                ModDecorativeBlocks.METEORITE_SLAB.get(),
                ModDecorativeBlocks.POLISHED_METEORITE_SLAB.get(),
                ModDecorativeBlocks.SMOOTH_METEORITE_SLAB.get()
        );
        tag(BlockTags.STAIRS).add(
                ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE_STAIRS.get(),
                ModDecorativeBlocks.SOUL_SANDSTONE_STAIRS.get(),
                ModDecorativeBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS.get(),
                ModDecorativeBlocks.COBBLED_SANDSTONE_STAIRS.get(),
                ModDecorativeBlocks.METEORITE_STAIRS.get(),
                ModDecorativeBlocks.POLISHED_METEORITE_STAIRS.get(),
                ModDecorativeBlocks.SMOOTH_METEORITE_STAIRS.get()
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
                ModBlocks.METEORITE_ORE.get()
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
                ModBlocks.TOPAZ_BLOCK.get()
        );
        tag(ModTags.forgeBlockTag("ores/copper")).add(ModBlocks.COPPER_ORE.get());
        tag(ModTags.forgeBlockTag("ores/meteorite")).add(ModBlocks.METEORITE_ORE.get());
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
        
    }
}
