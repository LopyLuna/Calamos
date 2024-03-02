package uwu.lopyluna.calamos.datagen;

import com.supermartijn642.fusion.api.provider.FusionModelProvider;
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
        this.simpleBlockItem(ModBlocks.IRON_ORE);
        this.simpleBlockItem(ModBlocks.GOLD_ORE);
        this.simpleBlockItem(ModBlocks.GARNET_ORE);
        this.simpleBlockItem(ModBlocks.JADE_ORE);
        this.simpleBlockItem(ModBlocks.KUNZITE_ORE);
        this.simpleBlockItem(ModBlocks.MOONSTONE_ORE);
        this.simpleBlockItem(ModBlocks.OPAL_ORE);
        this.simpleBlockItem(ModBlocks.RUBY_ORE);
        this.simpleBlockItem(ModBlocks.SAPPHIRE_ORE);
        this.simpleBlockItem(ModBlocks.SPINEL_ORE);
        this.simpleBlockItem(ModBlocks.SUNSTONE_ORE);
        this.simpleBlockItem(ModBlocks.TANZANITE_ORE);
        this.simpleBlockItem(ModBlocks.TOPAZ_ORE);
        //otherworld oak
        this.woodSet(ModDecorativeBlocks.OTHERWORLD_OAK_LOG, ModDecorativeBlocks.OTHERWORLD_OAK_WOOD, ModDecorativeBlocks.STRIPPED_OTHERWORLD_OAK_LOG, ModDecorativeBlocks.STRIPPED_OTHERWORLD_OAK_WOOD, ModDecorativeBlocks.OTHERWORLD_OAK_PLANKS, ModDecorativeBlocks.OTHERWORLD_OAK_STAIRS, ModDecorativeBlocks.OTHERWORLD_OAK_SLAB, ModDecorativeBlocks.OTHERWORLD_OAK_DOOR, ModDecorativeBlocks.OTHERWORLD_OAK_TRAPDOOR, ModDecorativeBlocks.OTHERWORLD_OAK_FENCE, ModDecorativeBlocks.OTHERWORLD_OAK_FENCE_GATE, ModDecorativeBlocks.OTHERWORLD_OAK_PRESSURE_PLATE, ModDecorativeBlocks.OTHERWORLD_OAK_BUTTON, ModDecorativeBlocks.OTHERWORLD_OAK_SIGN, ModDecorativeBlocks.OTHERWORLD_OAK_WALL_SIGN, ModDecorativeBlocks.OTHERWORLD_OAK_HANGING_SIGN, ModDecorativeBlocks.OTHERWORLD_OAK_WALL_HANGING_SIGN);
        //Twilight
        this.woodSet(ModDecorativeBlocks.TWILIGHT_LOG, ModDecorativeBlocks.TWILIGHT_WOOD, ModDecorativeBlocks.STRIPPED_TWILIGHT_LOG, ModDecorativeBlocks.STRIPPED_TWILIGHT_WOOD, ModDecorativeBlocks.TWILIGHT_PLANKS, ModDecorativeBlocks.TWILIGHT_STAIRS, ModDecorativeBlocks.TWILIGHT_SLAB, ModDecorativeBlocks.TWILIGHT_DOOR, ModDecorativeBlocks.TWILIGHT_TRAPDOOR, ModDecorativeBlocks.TWILIGHT_FENCE, ModDecorativeBlocks.TWILIGHT_FENCE_GATE, ModDecorativeBlocks.TWILIGHT_PRESSURE_PLATE, ModDecorativeBlocks.TWILIGHT_BUTTON, ModDecorativeBlocks.TWILIGHT_SIGN, ModDecorativeBlocks.TWILIGHT_WALL_SIGN, ModDecorativeBlocks.TWILIGHT_HANGING_SIGN, ModDecorativeBlocks.TWILIGHT_WALL_HANGING_SIGN);

        this.blockBottomTop(ModBlocks.ULTIMITA_TNT);
        this.blockBottomTop(ModBlocks.UNSTABLE_ULTIMITA_TNT);
    }
    
    private void woodSet(Supplier<? extends Block> log,
                         Supplier<? extends Block> wood,
                         Supplier<? extends Block> strippedLog,
                         Supplier<? extends Block> strippedWood,
                         Supplier<? extends Block>planks,
                         Supplier<? extends Block> stairs,
                         Supplier<? extends Block> slab,
                         Supplier<? extends Block> door,
                         Supplier<? extends Block> trapDoor,
                         Supplier<? extends Block> fence,
                         Supplier<? extends Block> fenceGate,
                         Supplier<? extends Block> pressurePlate,
                         Supplier<? extends Block> button,
                         Supplier<? extends Block> sign,
                         Supplier<? extends Block> wallSign,
                         Supplier<? extends Block> hangingSign,
                         Supplier<? extends Block> wallHangingSign
    ) {
        this.simpleLogBlock(log);
        this.simpleBlockAndItem(wood);
        this.simpleLogBlock(strippedLog);
        this.simpleBlockAndItem(strippedWood);
        this.simpleBlockAndItem(planks);
        this.simpleStairs(stairs, planks);
        this.simpleSlab(slab, planks);
        this.simpleDoorBlock(door);
        this.simpleTrapDoorBlock(trapDoor);
        this.simpleFence(fence, planks);
        this.simpleFenceGate(fenceGate, planks);
        this.simplePressurePlate(pressurePlate, planks);
        this.simpleButton(button, planks);
        this.simpleSignBlock(sign, wallSign, planks);
        this.simpleHangingSignBlock(hangingSign, wallHangingSign, planks);
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
    private void blockBottomTop(Supplier<? extends Block> block) {
        String name = this.name(block.get());
        ResourceLocation side = ModUtils.location("block/" + name);
        ResourceLocation top = ModUtils.location("block/" + name + "_top");
        ResourceLocation bottom = ModUtils.location("block/" + name + "_bottom");
        this.simpleBlock(block.get(), this.models().cubeBottomTop(name, side, bottom, top));
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
        ResourceLocation doubleSlab = ModUtils.location("block/" + this.name(texture.get()));
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
    private void simpleFence(Supplier<? extends Block> block, Supplier<? extends Block> texture) {
        String name = this.name(block.get());
        this.fenceBlock((FenceBlock) block.get(), name, blockTexture(texture.get()));
        this.simpleBlockItem(block.get(), models().fenceInventory(name + "_inventory", blockTexture(texture.get())));
        this.simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile(ModUtils.location("block/" + this.name(block.get()) + "_inventory")));
    }
    private void simpleFenceGate(Supplier<? extends Block> block, Supplier<? extends Block> texture) {
        String name = this.name(block.get());
        this.fenceGateBlock((FenceGateBlock) block.get(), name, blockTexture(texture.get()));
        this.simpleBlockItem(block.get(), models().fenceGate(name + "_inventory", blockTexture(texture.get())));
        this.simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile(ModUtils.location("block/" + this.name(block.get()) + "_inventory")));
    }
    private void simplePressurePlate(Supplier<? extends Block> block, Supplier<? extends Block> texture) {
        this.pressurePlateBlock((PressurePlateBlock) block.get(), blockTexture(texture.get()));
        this.simpleBlockItem(block);
    }
    private void simpleButton(Supplier<? extends Block> block, Supplier<? extends Block>  texture) {
        String name = this.name(block.get());
        this.buttonBlock((ButtonBlock) block.get(), blockTexture(texture.get()));
        this.simpleBlockItem(block.get(), models().buttonInventory(name + "_inventory", blockTexture(texture.get())));
        this.simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile(ModUtils.location("block/" + this.name(block.get()) + "_inventory")));
    }
    private void simpleLogBlock(Supplier<? extends Block> block) {
        this.logBlock((RotatedPillarBlock) block.get());
        this.simpleBlockItem(block);
    }
    private void simpleDoorBlock(Supplier<? extends Block> block) {
        String name = this.name(block.get());
        ResourceLocation bottomLocation = ModUtils.location("block/" + name + "_bottom");
        ResourceLocation topLocation = ModUtils.location("block/" + name + "_top");
        this.doorBlockWithRenderType((DoorBlock) block.get(), bottomLocation, topLocation, "cutout");
    }
    private void simpleTrapDoorBlock(Supplier<? extends Block> block) {
        ResourceLocation location = ModUtils.location("block/" + this.name(block.get()));
        this.trapdoorBlockWithRenderType((TrapDoorBlock) block.get(), location, true, "cutout");
        this.simpleBlockItem(block.get(), models().trapdoorBottom(this.name(block.get()), location));
    }
    private void simpleSignBlock(Supplier<? extends Block> block, Supplier<? extends Block> wallBlock, Supplier<? extends Block> texture) {
        this.signBlock((StandingSignBlock) block.get(), ((WallSignBlock) wallBlock.get()),
                blockTexture(texture.get()));
    }
    private void simpleHangingSignBlock(Supplier<? extends Block> block, Supplier<? extends Block> wallBlock, Supplier<? extends Block> texture) {
        ModelFile sign = models().sign(this.name(block.get()), blockTexture(texture.get()));
        this.simpleBlock(block.get(), sign);
        this.simpleBlock(wallBlock.get(), sign);
    }

    private String name(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }
}
