package uwu.lopyluna.calamos.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import uwu.lopyluna.calamos.elements.ModBlocks;
import uwu.lopyluna.calamos.elements.ModDecorativeBlocks;
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
        this.simpleBlockAndItem(ModDecorativeBlocks.METEORITE);
        this.simpleBlockAndItem(ModDecorativeBlocks.METEORITE_BRICKS);
        this.simpleBlockAndItem(ModDecorativeBlocks.METEORITE_TILES);
        this.simpleBlockAndItem(ModDecorativeBlocks.GILDED_METEORITE_BRICKS);
        this.simpleBlockAndItem(ModDecorativeBlocks.LARGE_METEORITE_TILE);
        this.simpleBlockAndItem(ModDecorativeBlocks.COBBLED_METEORITE);
        this.simpleBlockAndItem(ModDecorativeBlocks.CUT_METEORITE);
        this.simpleBlockAndItem(ModDecorativeBlocks.POLISHED_METEORITE);
        this.simpleBlockAndItem(ModDecorativeBlocks.SMOOTH_METEORITE);
        this.simpleStairs(ModDecorativeBlocks.SMOOTH_METEORITE_STAIRS, ModDecorativeBlocks.SMOOTH_METEORITE);
        this.simpleSlab(ModDecorativeBlocks.SMOOTH_METEORITE_SLAB, ModDecorativeBlocks.SMOOTH_METEORITE);
        this.simpleStairs(ModDecorativeBlocks.POLISHED_METEORITE_STAIRS, ModDecorativeBlocks.POLISHED_METEORITE);
        this.simpleSlab(ModDecorativeBlocks.POLISHED_METEORITE_SLAB, ModDecorativeBlocks.POLISHED_METEORITE);
        this.simpleStairs(ModDecorativeBlocks.METEORITE_STAIRS, ModDecorativeBlocks.METEORITE);
        this.simpleSlab(ModDecorativeBlocks.METEORITE_SLAB, ModDecorativeBlocks.METEORITE);
        this.simpleWall(ModDecorativeBlocks.SMOOTH_METEORITE_WALL, ModDecorativeBlocks.SMOOTH_METEORITE);
        this.simpleWall(ModDecorativeBlocks.METEORITE_WALL, ModDecorativeBlocks.METEORITE);
        this.simpleWall(ModDecorativeBlocks.POLISHED_METEORITE_WALL, ModDecorativeBlocks.POLISHED_METEORITE);
        //soul sandstone
        this.cubeBottomTop(ModDecorativeBlocks.SOUL_SANDSTONE, ModUtils.location("block/cobbled_soul_sandstone"), ModUtils.location("block/smooth_soul_sandstone"));
        this.sidedSlab(ModDecorativeBlocks.SOUL_SANDSTONE_SLAB, ModUtils.location("block/soul_sandstone"), ModUtils.location("block/smooth_soul_sandstone"), ModUtils.location("block/smooth_soul_sandstone"));
        this.sidedStairs(ModDecorativeBlocks.SOUL_SANDSTONE_STAIRS, ModUtils.location("block/soul_sandstone"), ModUtils.location("block/smooth_soul_sandstone"), ModUtils.location("block/smooth_soul_sandstone"));
        this.simpleWall(ModDecorativeBlocks.SOUL_SANDSTONE_WALL, ModDecorativeBlocks.SOUL_SANDSTONE);
        this.simpleBlockAndItem(ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE);
        this.simpleSlab(ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE_SLAB, ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE);
        this.simpleStairs(ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE_STAIRS, ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE);
        this.simpleWall(ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE_WALL, ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE);
        this.simpleBlockAndItem(ModDecorativeBlocks.SMOOTH_SOUL_SANDSTONE);
        this.simpleSlab(ModDecorativeBlocks.SMOOTH_SOUL_SANDSTONE_SLAB, ModDecorativeBlocks.SMOOTH_SOUL_SANDSTONE);
        this.simpleStairs(ModDecorativeBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS, ModDecorativeBlocks.SMOOTH_SOUL_SANDSTONE);
        this.cubeBottomTop(ModDecorativeBlocks.CUT_SOUL_SANDSTONE, ModUtils.location("block/smooth_soul_sandstone"), ModUtils.location("block/smooth_soul_sandstone"));
        this.sidedSlab(ModDecorativeBlocks.CUT_SOUL_SANDSTONE_SLAB, ModUtils.location("block/cut_soul_sandstone"), ModUtils.location("block/smooth_soul_sandstone"), ModUtils.location("block/smooth_soul_sandstone"));
        this.cubeBottomTop(ModDecorativeBlocks.CHISELED_SOUL_SANDSTONE, ModUtils.location("block/smooth_soul_sandstone"), ModUtils.location("block/smooth_soul_sandstone"));
        
        //vanilla variants
        this.simpleBlockAndItem(ModDecorativeBlocks.COBBLED_SANDSTONE);
        this.simpleStairs(ModDecorativeBlocks.COBBLED_SANDSTONE_STAIRS, ModDecorativeBlocks.COBBLED_SANDSTONE);
        this.simpleSlab(ModDecorativeBlocks.COBBLED_SANDSTONE_SLAB, ModDecorativeBlocks.COBBLED_SANDSTONE);
        this.simpleWall(ModDecorativeBlocks.COBBLED_SANDSTONE_WALL, ModDecorativeBlocks.COBBLED_SANDSTONE);
        
        
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
    private void cubeBottomTop(Supplier<? extends Block> block, ResourceLocation top, ResourceLocation bottom) {
        String name = this.name(block.get());
        ResourceLocation side = ModUtils.location("block/" + name);
        this.simpleBlock(block.get(), this.models().cubeBottomTop(name, side, top, bottom));
        this.simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile(ModUtils.location("block/" + this.name(block.get()))));
    }
    private void sidedSlab(Supplier<? extends Block> block, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        String name = this.name(block.get());
        ResourceLocation doubleSlab = ModUtils.location("block/" + name.replace("_slab", ""));
        this.slabBlock((SlabBlock) block.get(), doubleSlab, side, top, bottom);
        this.simpleBlockItem(block.get(), models().slab(name + "_inventory", side, top, bottom));
        this.simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile(ModUtils.location("block/" + this.name(block.get()) + "_inventory")));
    }
    private void sidedStairs(Supplier<? extends Block> block, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        String name = this.name(block.get());
        this.stairsBlock((StairBlock) block.get(), name, side, top, bottom);
        this.simpleBlockItem(block.get(), models().stairs(name + "_inventory", side, top, bottom));
        this.simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile(ModUtils.location("block/" + this.name(block.get()) + "_inventory")));
    }
    
    private void simpleSlab(Supplier<? extends Block> block, Supplier<? extends Block> texture) {
        String name = this.name(block.get());
        ResourceLocation main = blockTexture(texture.get());
        ResourceLocation doubleSlab = ModUtils.location("block/" + name.replace("_slab", ""));
        this.slabBlock((SlabBlock) block.get(), doubleSlab, main, main, main);
        this.simpleBlockItem(block.get(), models().slab(name + "_inventory", main, main, main));
        this.simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile(ModUtils.location("block/" + this.name(block.get()) + "_inventory")));
    }
    private void simpleStairs(Supplier<? extends Block> block, Supplier<? extends Block> texture) {
        String name = this.name(block.get());
        ResourceLocation main = blockTexture(texture.get());
        this.stairsBlock((StairBlock) block.get(), name, main, main, main);
        this.simpleBlockItem(block.get(), models().stairs(name + "_inventory", main, main, main));
        this.simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile(ModUtils.location("block/" + this.name(block.get()) + "_inventory")));
    }
    private void simpleWall(Supplier<? extends Block> block, Supplier<? extends Block> texture) {
        String name = this.name(block.get());
        this.wallBlock((WallBlock) block.get(), name, blockTexture(texture.get()));
        this.simpleBlockItem(block.get(), models().wallInventory(name + "_inventory", blockTexture(texture.get())));
        this.simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile(ModUtils.location("block/" + this.name(block.get()) + "_inventory")));
    }

    private String name(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }
}
