package uwu.lopyluna.calamos.elements;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.elements.block.ModCeilingHangingSignBlock;
import uwu.lopyluna.calamos.elements.block.ModHangingSignBlock;
import uwu.lopyluna.calamos.elements.block.ModStandingSignBlock;
import uwu.lopyluna.calamos.elements.block.ModWallSignBlock;
import uwu.lopyluna.calamos.utilities.ModUtils;
import uwu.lopyluna.calamos.utilities.ModWoodTypes;

import java.util.Collection;
import java.util.function.Supplier;

public class ModDecorativeBlocks {
    public static final DeferredRegister.Blocks BLOCKS = ModUtils.createRegister(DeferredRegister::createBlocks);
    
    //Meteorite Blocks
    public static final DeferredBlock<Block> METEORITE  = register("meteorite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());
    public static final DeferredBlock<StairBlock> METEORITE_STAIRS = register("meteorite_stairs",
            () -> new StairBlock(() -> ModDecorativeBlocks.METEORITE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS)
            ), new Item.Properties());
    public static final DeferredBlock<SlabBlock> METEORITE_SLAB = register("meteorite_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB)
            ), new Item.Properties());
    public static final DeferredBlock<WallBlock> METEORITE_WALL = register("meteorite_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_WALL)
            ), new Item.Properties());
    public static final DeferredBlock<Block> COBBLED_METEORITE  = register("cobbled_meteorite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> CUT_METEORITE  = register("cut_meteorite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> GILDED_METEORITE_BRICKS  = register("gilded_meteorite_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> LARGE_METEORITE_TILE  = register("large_meteorite_tile",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> METEORITE_BRICKS  = register("meteorite_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> METEORITE_TILES  = register("meteorite_tiles",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> POLISHED_METEORITE  = register("polished_meteorite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());
    public static final DeferredBlock<StairBlock> POLISHED_METEORITE_STAIRS = register("polished_meteorite_stairs",
            () -> new StairBlock(() -> ModDecorativeBlocks.POLISHED_METEORITE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS)
            ), new Item.Properties());
    public static final DeferredBlock<SlabBlock> POLISHED_METEORITE_SLAB = register("polished_meteorite_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB)
            ), new Item.Properties());
    public static final DeferredBlock<WallBlock> POLISHED_METEORITE_WALL = register("polished_meteorite_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_WALL)
            ), new Item.Properties());
    public static final DeferredBlock<Block> SMOOTH_METEORITE  = register("smooth_meteorite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());
    public static final DeferredBlock<StairBlock> SMOOTH_METEORITE_STAIRS = register("smooth_meteorite_stairs",
            () -> new StairBlock(() -> ModDecorativeBlocks.SMOOTH_METEORITE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS)
            ), new Item.Properties());
    public static final DeferredBlock<SlabBlock> SMOOTH_METEORITE_SLAB = register("smooth_meteorite_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB)
            ), new Item.Properties());
    public static final DeferredBlock<WallBlock> SMOOTH_METEORITE_WALL = register("smooth_meteorite_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_WALL)
            ), new Item.Properties());
    
    //Soul Blocks
    public static final DeferredBlock<Block> SOUL_SANDSTONE  = register("soul_sandstone",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.COW_BELL).requiresCorrectToolForDrops().strength(0.8F)
            ), new Item.Properties());
    public static final DeferredBlock<Block> SOUL_SANDSTONE_STAIRS  = register("soul_sandstone_stairs",
            () -> new StairBlock(SOUL_SANDSTONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SOUL_SANDSTONE.get())
            ), new Item.Properties());
    public static final DeferredBlock<Block> SOUL_SANDSTONE_SLAB  = register("soul_sandstone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SOUL_SANDSTONE.get())
            ), new Item.Properties());
    public static final DeferredBlock<Block> SOUL_SANDSTONE_WALL  = register("soul_sandstone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(SOUL_SANDSTONE.get())
            ), new Item.Properties());
    public static final DeferredBlock<Block> COBBLED_SOUL_SANDSTONE  = register("cobbled_soul_sandstone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(SOUL_SANDSTONE.get())
            ), new Item.Properties());
    public static final DeferredBlock<Block> COBBLED_SOUL_SANDSTONE_STAIRS  = register("cobbled_soul_sandstone_stairs",
            () -> new StairBlock(COBBLED_SOUL_SANDSTONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(COBBLED_SOUL_SANDSTONE.get())
            ), new Item.Properties());
    public static final DeferredBlock<Block> COBBLED_SOUL_SANDSTONE_SLAB  = register("cobbled_soul_sandstone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(COBBLED_SOUL_SANDSTONE.get())
            ), new Item.Properties());
    public static final DeferredBlock<Block> COBBLED_SOUL_SANDSTONE_WALL  = register("cobbled_soul_sandstone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(COBBLED_SOUL_SANDSTONE.get())
            ), new Item.Properties());
    public static final DeferredBlock<Block> SMOOTH_SOUL_SANDSTONE  = register("smooth_soul_sandstone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(SOUL_SANDSTONE.get())
            ), new Item.Properties());
    public static final DeferredBlock<Block> SMOOTH_SOUL_SANDSTONE_STAIRS  = register("smooth_soul_sandstone_stairs",
            () -> new StairBlock(SMOOTH_SOUL_SANDSTONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SMOOTH_SOUL_SANDSTONE.get())
            ), new Item.Properties());
    public static final DeferredBlock<Block> SMOOTH_SOUL_SANDSTONE_SLAB  = register("smooth_soul_sandstone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SMOOTH_SOUL_SANDSTONE.get())
            ), new Item.Properties());
    public static final DeferredBlock<Block> CUT_SOUL_SANDSTONE  = register("cut_soul_sandstone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(SOUL_SANDSTONE.get())
            ), new Item.Properties());
    public static final DeferredBlock<Block> CUT_SOUL_SANDSTONE_SLAB  = register("cut_soul_sandstone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(CUT_SOUL_SANDSTONE.get())
            ), new Item.Properties());
    public static final DeferredBlock<Block> CHISELED_SOUL_SANDSTONE  = register("chiseled_soul_sandstone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(SOUL_SANDSTONE.get())
            ), new Item.Properties());
    
    
    //Vanilla Variants
    public static final DeferredBlock<Block> COBBLED_SANDSTONE  = register("cobbled_sandstone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> COBBLED_SANDSTONE_STAIRS  = register("cobbled_sandstone_stairs",
            () -> new StairBlock(COBBLED_SANDSTONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(COBBLED_SANDSTONE.get())
            ), new Item.Properties());
    public static final DeferredBlock<Block> COBBLED_SANDSTONE_SLAB  = register("cobbled_sandstone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(COBBLED_SANDSTONE.get())
            ), new Item.Properties());
    public static final DeferredBlock<Block> COBBLED_SANDSTONE_WALL  = register("cobbled_sandstone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(COBBLED_SANDSTONE.get())
            ), new Item.Properties());

    //Otherworld Oak
    public static final DeferredBlock<Block> OTHERWORLD_OAK_LOG = register("otherworld_oak_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
            ), new Item.Properties());
    public static final DeferredBlock<Block> OTHERWORLD_OAK_WOOD = register("otherworld_oak_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
            ), new Item.Properties());
    public static final DeferredBlock<Block> STRIPPED_OTHERWORLD_OAK_LOG = register("stripped_otherworld_oak_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
            ), new Item.Properties());
    public static final DeferredBlock<Block> STRIPPED_OTHERWORLD_OAK_WOOD = register("stripped_otherworld_oak_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
            ), new Item.Properties());
    public static final DeferredBlock<Block> OTHERWORLD_OAK_PLANKS = register("otherworld_oak_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
            ), new Item.Properties());
    public static final DeferredBlock<Block> OTHERWORLD_OAK_STAIRS = register("otherworld_oak_stairs",
            () -> new StairBlock(OTHERWORLD_OAK_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
            ), new Item.Properties());
    public static final DeferredBlock<Block> OTHERWORLD_OAK_SLAB = register("otherworld_oak_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
            ), new Item.Properties());
    public static final DeferredBlock<Block> OTHERWORLD_OAK_DOOR = register("otherworld_oak_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)
            ), new Item.Properties());
    public static final DeferredBlock<Block> OTHERWORLD_OAK_TRAPDOOR = register("otherworld_oak_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)
            ), new Item.Properties());
    public static final DeferredBlock<Block> OTHERWORLD_OAK_FENCE = register("otherworld_oak_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> OTHERWORLD_OAK_FENCE_GATE = register("otherworld_oak_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE
            ), new Item.Properties());
    public static final DeferredBlock<Block> OTHERWORLD_OAK_PRESSURE_PLATE = register("otherworld_oak_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> OTHERWORLD_OAK_BUTTON = register("otherworld_oak_button",
            () -> new ButtonBlock(BlockSetType.OAK, 30, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)
            ), new Item.Properties());
    public static final DeferredBlock<Block> OTHERWORLD_OAK_SIGN = BLOCKS.register("otherworld_oak_sign",
            () -> new ModStandingSignBlock(ModWoodTypes.OTHERWORLD_OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN)));
    public static final DeferredBlock<Block> OTHERWORLD_OAK_WALL_SIGN = BLOCKS.register("otherworld_oak_wall_sign",
            () -> new ModWallSignBlock(ModWoodTypes.OTHERWORLD_OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)));
    public static final DeferredBlock<Block> OTHERWORLD_OAK_HANGING_SIGN = BLOCKS.register("otherworld_oak_hanging_sign",
            () -> new ModCeilingHangingSignBlock(ModWoodTypes.OTHERWORLD_OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN)));
    public static final DeferredBlock<Block> OTHERWORLD_OAK_WALL_HANGING_SIGN = BLOCKS.register("otherworld_oak_wall_hanging_sign",
            () -> new ModHangingSignBlock(ModWoodTypes.OTHERWORLD_OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN)));
    //Twilight
    public static final DeferredBlock<Block> TWILIGHT_LOG = register("twilight_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
            ), new Item.Properties());
    public static final DeferredBlock<Block> TWILIGHT_WOOD = register("twilight_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
            ), new Item.Properties());
    public static final DeferredBlock<Block> STRIPPED_TWILIGHT_LOG = register("stripped_twilight_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
            ), new Item.Properties());
    public static final DeferredBlock<Block> STRIPPED_TWILIGHT_WOOD = register("stripped_twilight_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
            ), new Item.Properties());
    public static final DeferredBlock<Block> TWILIGHT_PLANKS = register("twilight_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
            ), new Item.Properties());
    public static final DeferredBlock<Block> TWILIGHT_STAIRS = register("twilight_stairs",
            () -> new StairBlock(TWILIGHT_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
            ), new Item.Properties());
    public static final DeferredBlock<Block> TWILIGHT_SLAB = register("twilight_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
            ), new Item.Properties());
    public static final DeferredBlock<Block> TWILIGHT_DOOR = register("twilight_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)
            ), new Item.Properties());
    public static final DeferredBlock<Block> TWILIGHT_TRAPDOOR = register("twilight_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)
            ), new Item.Properties());
    public static final DeferredBlock<Block> TWILIGHT_FENCE = register("twilight_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> TWILIGHT_FENCE_GATE = register("twilight_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE
            ), new Item.Properties());
    public static final DeferredBlock<Block> TWILIGHT_PRESSURE_PLATE = register("twilight_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> TWILIGHT_BUTTON = register("twilight_button",
            () -> new ButtonBlock(BlockSetType.OAK, 30, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)
            ), new Item.Properties());
    public static final DeferredBlock<Block> TWILIGHT_SIGN = BLOCKS.register("twilight_sign",
            () -> new ModStandingSignBlock(ModWoodTypes.TWILIGHT, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN)));
    public static final DeferredBlock<Block> TWILIGHT_WALL_SIGN = BLOCKS.register("twilight_wall_sign",
            () -> new ModWallSignBlock(ModWoodTypes.TWILIGHT, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)));
    public static final DeferredBlock<Block> TWILIGHT_HANGING_SIGN = BLOCKS.register("twilight_hanging_sign",
            () -> new ModCeilingHangingSignBlock(ModWoodTypes.OTHERWORLD_OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN)));
    public static final DeferredBlock<Block> TWILIGHT_WALL_HANGING_SIGN = BLOCKS.register("twilight_wall_hanging_sign",
            () -> new ModHangingSignBlock(ModWoodTypes.TWILIGHT, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN)));

    
    
    private static <T extends Block> DeferredBlock<T> register(String id, Supplier<T> block, Item.Properties pIProp) {
        DeferredBlock<T> toReturn = BLOCKS.register(id.toLowerCase(), block);
        makeBlockItem(toReturn, pIProp);
        return toReturn;
    }
    
    private static <T extends Block> void makeBlockItem(DeferredBlock<T> block, Item.Properties pIProp) {
        ModItems.registerBlockItem(block, pIProp);
    }
    public static void staticInit() {
    }
    
    public static Collection<DeferredHolder<Block, ? extends Block>> getBlocks() {
        return BLOCKS.getEntries();
    }
}
