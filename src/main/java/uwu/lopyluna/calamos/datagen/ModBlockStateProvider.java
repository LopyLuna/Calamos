package uwu.lopyluna.calamos.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import uwu.lopyluna.calamos.elements.ModBlocks;
import uwu.lopyluna.calamos.utilities.ModUtils;

import java.util.function.Supplier;

class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        //meteorite
        this.simpleBlockItem(ModBlocks.METEORITE_ORE);
        this.simpleBlockAndItem(ModBlocks.METEORITE);
        this.simpleBlockAndItem(ModBlocks.METEORITE_BRICKS);
        this.simpleBlockAndItem(ModBlocks.METEORITE_TILES);
        this.simpleBlockAndItem(ModBlocks.GILDED_METEORITE_BRICKS);
        this.simpleBlockAndItem(ModBlocks.LARGE_METEORITE_TILE);
        this.simpleBlockAndItem(ModBlocks.COBBLED_METEORITE);
        this.simpleBlockAndItem(ModBlocks.CUT_METEORITE);
        this.simpleBlockAndItem(ModBlocks.POLISHED_METEORITE);
        this.simpleBlockAndItem(ModBlocks.SMOOTH_METEORITE);
        //gems
        this.simpleBlockAndItem(ModBlocks.GARNET_BLOCK);
        this.simpleBlockAndItem(ModBlocks.JADE_BLOCK);
        this.simpleBlockAndItem(ModBlocks.KUNZITE_BLOCK);
        this.simpleBlockAndItem(ModBlocks.MOONSTONE_BLOCK);
        this.simpleBlockAndItem(ModBlocks.OPAL_BLOCK);
        this.simpleBlockAndItem(ModBlocks.RUBY_BLOCK);
        this.simpleBlockAndItem(ModBlocks.SAPPHIRE_BLOCK);
        this.simpleBlockAndItem(ModBlocks.SPINEL_BLOCK);
        this.simpleBlockAndItem(ModBlocks.SUNSTONE_BLOCK);
        this.simpleBlockAndItem(ModBlocks.TANZANITE_BLOCK);
        this.simpleBlockAndItem(ModBlocks.TOPAZ_BLOCK);
    }

    private void simpleBlockItem(Supplier<? extends Block> block) {
        super.simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile(ModUtils.location("block/" + this.name(block.get()))));
    }
    private void simpleBlockWithItem(Supplier<? extends Block> block) {
        super.simpleBlockWithItem(block.get(), new ModelFile.UncheckedModelFile(ModUtils.location("block/" + this.name(block.get()))));
    }
    private void simpleBlockAndItem(Supplier<? extends Block> block) {
        this.simpleBlock(block.get());
        this.simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile(ModUtils.location("block/" + this.name(block.get()))));
    }

    private String name(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }
}