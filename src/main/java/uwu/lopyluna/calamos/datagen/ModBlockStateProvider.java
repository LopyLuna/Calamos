package uwu.lopyluna.calamos.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
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
        this.simpleBlockAndItem(ModBlocks.STONE);
        this.simpleBlockAndItem(ModBlocks.PURRASITE);
        this.simpleBlockAndItem(ModBlocks.UMBRALITE);
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
        this.simpleStairs(ModBlocks.SMOOTH_METEORITE_STAIRS, ModBlocks.SMOOTH_METEORITE);
        this.simpleSlab(ModBlocks.SMOOTH_METEORITE_SLAB, ModBlocks.SMOOTH_METEORITE);
        this.simpleStairs(ModBlocks.POLISHED_METEORITE_STAIRS, ModBlocks.POLISHED_METEORITE);
        this.simpleSlab(ModBlocks.POLISHED_METEORITE_SLAB, ModBlocks.POLISHED_METEORITE);
        this.simpleStairs(ModBlocks.METEORITE_STAIRS, ModBlocks.METEORITE);
        this.simpleSlab(ModBlocks.METEORITE_SLAB, ModBlocks.METEORITE);
        this.simpleWall(ModBlocks.SMOOTH_METEORITE_WALL, ModBlocks.SMOOTH_METEORITE);
        this.simpleWall(ModBlocks.METEORITE_WALL, ModBlocks.METEORITE);
        this.simpleWall(ModBlocks.POLISHED_METEORITE_WALL, ModBlocks.POLISHED_METEORITE);
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
        //ores
        this.simpleBlockItem(ModBlocks.COPPER_ORE);
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

    private void simpleStairs(Supplier<? extends StairBlock> block, Supplier<? extends Block> texture) {
        this.stairsBlock(block.get(), blockTexture(texture.get()));
        this.simpleBlockItem(block);
    }
    private void simpleSlab(Supplier<? extends SlabBlock> block, Supplier<? extends Block> texture) {
        this.slabBlock(block.get(), blockTexture(texture.get()), blockTexture(texture.get()));
        this.simpleBlockItem(block);
    }
    private void simpleWall(Supplier<? extends WallBlock> block, Supplier<? extends Block> texture) {
        this.wallBlock(block.get(), blockTexture(texture.get()));
    }

    private String name(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }
}
