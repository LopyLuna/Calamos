package uwu.lopyluna.calamos.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import uwu.lopyluna.calamos.elements.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, modId, existingFileHelper);
    }
    
    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.registerBlockMineables();
        //this.registerMinecraftTags();
        //this.registerForgeTags();
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
                ModBlocks.METEORITE.get(),
                ModBlocks.COBBLED_METEORITE.get(),
                ModBlocks.POLISHED_METEORITE.get(),
                ModBlocks.METEORITE_BRICKS.get(),
                ModBlocks.GILDED_METEORITE_BRICKS.get(),
                ModBlocks.METEORITE_TILES.get(),
                ModBlocks.LARGE_METEORITE_TILE.get(),
                ModBlocks.CUT_METEORITE.get()
        );
    }
    protected void registerMinecraftTags() {
    
    }
    
    protected void registerForgeTags() {
    
    }
}
