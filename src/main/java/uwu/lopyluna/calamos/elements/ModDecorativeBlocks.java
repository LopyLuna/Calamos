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
            () -> new ModCeilingHangingSignBlock(ModWoodTypes.HOLLOW, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN)));
    public static final DeferredBlock<Block> TWILIGHT_WALL_HANGING_SIGN = BLOCKS.register("twilight_wall_hanging_sign",
            () -> new ModHangingSignBlock(ModWoodTypes.TWILIGHT, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN)));
    //Hollow
    public static final DeferredBlock<Block> HOLLOW_LOG = register("hollow_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
            ), new Item.Properties());
    public static final DeferredBlock<Block> HOLLOW_WOOD = register("hollow_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
            ), new Item.Properties());
    public static final DeferredBlock<Block> STRIPPED_HOLLOW_LOG = register("stripped_hollow_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
            ), new Item.Properties());
    public static final DeferredBlock<Block> STRIPPED_HOLLOW_WOOD = register("stripped_hollow_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
            ), new Item.Properties());
    public static final DeferredBlock<Block> HOLLOW_PLANKS = register("hollow_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
            ), new Item.Properties());
    public static final DeferredBlock<Block> HOLLOW_STAIRS = register("hollow_stairs",
            () -> new StairBlock(HOLLOW_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
            ), new Item.Properties());
    public static final DeferredBlock<Block> HOLLOW_SLAB = register("hollow_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
            ), new Item.Properties());
    public static final DeferredBlock<Block> HOLLOW_DOOR = register("hollow_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)
            ), new Item.Properties());
    public static final DeferredBlock<Block> HOLLOW_TRAPDOOR = register("hollow_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)
            ), new Item.Properties());
    public static final DeferredBlock<Block> HOLLOW_FENCE = register("hollow_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> HOLLOW_FENCE_GATE = register("hollow_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE
            ), new Item.Properties());
    public static final DeferredBlock<Block> HOLLOW_PRESSURE_PLATE = register("hollow_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> HOLLOW_BUTTON = register("hollow_button",
            () -> new ButtonBlock(BlockSetType.OAK, 30, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)
            ), new Item.Properties());
    public static final DeferredBlock<Block> HOLLOW_SIGN = BLOCKS.register("hollow_sign",
            () -> new ModStandingSignBlock(ModWoodTypes.HOLLOW, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN)));
    public static final DeferredBlock<Block> HOLLOW_WALL_SIGN = BLOCKS.register("hollow_wall_sign",
            () -> new ModWallSignBlock(ModWoodTypes.HOLLOW, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)));
    public static final DeferredBlock<Block> HOLLOW_HANGING_SIGN = BLOCKS.register("hollow_hanging_sign",
            () -> new ModCeilingHangingSignBlock(ModWoodTypes.HOLLOW, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN)));
    public static final DeferredBlock<Block> HOLLOW_WALL_HANGING_SIGN = BLOCKS.register("hollow_wall_hanging_sign",
            () -> new ModHangingSignBlock(ModWoodTypes.HOLLOW, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN)));
    //Amethyst
    public static final DeferredBlock<Block> AMETHYST_BRICKS = register("amethyst_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)
            ), new Item.Properties());
    public static final DeferredBlock<Block> CUT_AMETHYST = register("cut_amethyst",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)
            ), new Item.Properties());
    public static final DeferredBlock<Block> POLISHED_AMETHYST = register("polished_amethyst",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)
            ), new Item.Properties());
    //Basalt
    public static final DeferredBlock<Block> BASALT_BRICKS = register("basalt_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BASALT)
            ), new Item.Properties());
    public static final DeferredBlock<Block> CUT_BASALT = register("cut_basalt",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BASALT)
            ), new Item.Properties());
    //Blackstone
    public static final DeferredBlock<Block> BLACKSTONE_TILES = register("blackstone_tiles",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE)
            ), new Item.Properties());
    //Bone
    public static final DeferredBlock<Block> BONE_BRICKS = register("bone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BONE_BLOCK)
            ), new Item.Properties());
    public static final DeferredBlock<Block> CHISELED_BONE = register("chiseled_bone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BONE_BLOCK)
            ), new Item.Properties());
    //Red Nether
    public static final DeferredBlock<Block> CHISELED_RED_NETHER_BRICKS = register("chiseled_red_nether_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICKS)
            ), new Item.Properties());
    //Stone
    public static final DeferredBlock<Block> COBBLESTONE_BRICKS = register("cobblestone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> COBBLESTONE_BRICK_STAIRS = register("cobblestone_brick_stairs",
            () -> new StairBlock(COBBLESTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> COBBLESTONE_BRICK_SLAB = register("cobblestone_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> COBBLESTONE_BRICK_WALL = register("cobblestone_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> SMOOTH_COBBLESTONE = register("smooth_cobblestone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> POLISHED_STONE = register("polished_stone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> STONE_TILES = register("stone_tiles",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> SMOOTH_DEEPSLATE = register("smooth_deepslate",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
            ), new Item.Properties());
    //Obsidian
    public static final DeferredBlock<Block> CRYING_OBSIDIAN_BRICKS = register("crying_obsidian_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CRYING_OBSIDIAN)
            ), new Item.Properties());
    public static final DeferredBlock<Block> OBSIDIAN_BRICKS = register("obsidian_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN)
            ), new Item.Properties());
    //Netherrack
    public static final DeferredBlock<Block> NETHERRACK_BRICKS = register("netherrack_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK)
            ), new Item.Properties());
    public static final DeferredBlock<Block> SMOOTH_NETHERRACK = register("smooth_netherrack",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK)
            ), new Item.Properties());
    public static final DeferredBlock<Block> POLISHED_NETHERRACK = register("polished_netherrack",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK)
            ), new Item.Properties());
    //Brick
    public static final DeferredBlock<Block> LARGE_BRICKS = register("large_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS)
            ), new Item.Properties());
    //Large Planks
        //Acacia
    public static final DeferredBlock<Block> LARGE_ACACIA_PLANKS = register("large_acacia_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)
            ), new Item.Properties());
    public static final DeferredBlock<Block> LARGE_ACACIA_STAIRS = register("large_acacia_stairs",
            () -> new StairBlock(LARGE_ACACIA_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_STAIRS)
            ), new Item.Properties());
    public static final DeferredBlock<Block> LARGE_ACACIA_SLAB = register("large_acacia_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_SLAB)
            ), new Item.Properties());
        //Bamboo
    public static final DeferredBlock<Block> LARGE_BAMBOO_PLANKS = register("large_bamboo_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PLANKS)
            ), new Item.Properties());
    public static final DeferredBlock<Block> LARGE_BAMBOO_STAIRS = register("large_bamboo_stairs",
            () -> new StairBlock(LARGE_BAMBOO_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_STAIRS)
            ), new Item.Properties());
    public static final DeferredBlock<Block> LARGE_BAMBOO_SLAB = register("large_bamboo_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_SLAB)
            ), new Item.Properties());
        //Birch
    public static final DeferredBlock<Block> LARGE_BIRCH_PLANKS = register("large_birch_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_PLANKS)
            ), new Item.Properties());
    public static final DeferredBlock<Block> LARGE_BIRCH_STAIRS = register("large_birch_stairs",
            () -> new StairBlock(LARGE_BIRCH_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_STAIRS)
            ), new Item.Properties());
    public static final DeferredBlock<Block> LARGE_BIRCH_SLAB = register("large_birch_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_SLAB)
            ), new Item.Properties());
        //Crimson
    public static final DeferredBlock<Block> LARGE_CRIMSON_PLANKS = register("large_crimson_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PLANKS)
            ), new Item.Properties());
    public static final DeferredBlock<Block> LARGE_CRIMSON_STAIRS = register("large_crimson_stairs",
            () -> new StairBlock(LARGE_CRIMSON_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_STAIRS)
            ), new Item.Properties());
    public static final DeferredBlock<Block> LARGE_CRIMSON_SLAB = register("large_crimson_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_SLAB)
            ), new Item.Properties());
        //Dark Oak
    public static final DeferredBlock<Block> LARGE_DARK_OAK_PLANKS = register("large_dark_oak_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS)
            ), new Item.Properties());
    public static final DeferredBlock<Block> LARGE_DARK_OAK_STAIRS = register("large_dark_oak_stairs",
            () -> new StairBlock(LARGE_DARK_OAK_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_STAIRS)
            ), new Item.Properties());
    public static final DeferredBlock<Block> LARGE_DARK_OAK_SLAB = register("large_dark_oak_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_SLAB)
            ), new Item.Properties());
        //Jungle
    public static final DeferredBlock<Block> LARGE_JUNGLE_PLANKS = register("large_jungle_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_PLANKS)
            ), new Item.Properties());
    public static final DeferredBlock<Block> LARGE_JUNGLE_STAIRS = register("large_jungle_stairs",
            () -> new StairBlock(LARGE_JUNGLE_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_STAIRS)
            ), new Item.Properties());
    public static final DeferredBlock<Block> LARGE_JUNGLE_SLAB = register("large_jungle_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_SLAB)
            ), new Item.Properties());
        //Mangrove
    public static final DeferredBlock<Block> LARGE_MANGROVE_PLANKS = register("large_mangrove_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PLANKS)
            ), new Item.Properties());
    public static final DeferredBlock<Block> LARGE_MANGROVE_STAIRS = register("large_mangrove_stairs",
            () -> new StairBlock(LARGE_MANGROVE_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_STAIRS)
            ), new Item.Properties());
    public static final DeferredBlock<Block> LARGE_MANGROVE_SLAB = register("large_mangrove_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_SLAB)
            ), new Item.Properties());
        //Oak
    public static final DeferredBlock<Block> LARGE_OAK_PLANKS = register("large_oak_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
            ), new Item.Properties());
    public static final DeferredBlock<Block> LARGE_OAK_STAIRS = register("large_oak_stairs",
            () -> new StairBlock(LARGE_OAK_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)
            ), new Item.Properties());
    public static final DeferredBlock<Block> LARGE_OAK_SLAB = register("large_oak_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)
            ), new Item.Properties());
        //Spruce
    public static final DeferredBlock<Block> LARGE_SPRUCE_PLANKS = register("large_spruce_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS)
            ), new Item.Properties());
    public static final DeferredBlock<Block> LARGE_SPRUCE_STAIRS = register("large_spruce_stairs",
            () -> new StairBlock(LARGE_SPRUCE_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_STAIRS)
            ), new Item.Properties());
    public static final DeferredBlock<Block> LARGE_SPRUCE_SLAB = register("large_spruce_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SLAB)
            ), new Item.Properties());
        //Warped
    public static final DeferredBlock<Block> LARGE_WARPED_PLANKS = register("large_warped_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)
            ), new Item.Properties());
    public static final DeferredBlock<Block> LARGE_WARPED_STAIRS = register("large_warped_stairs",
            () -> new StairBlock(LARGE_WARPED_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_STAIRS)
            ), new Item.Properties());
    public static final DeferredBlock<Block> LARGE_WARPED_SLAB = register("large_warped_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_SLAB)
            ), new Item.Properties());
    //Tiles
    public static final DeferredBlock<Block> ENDSTONE_TILES = register("endstone_tiles",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE_BRICKS)
            ), new Item.Properties());
    public static final DeferredBlock<Block> MUD_TILES = register("mud_tiles",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MUD_BRICKS)
            ), new Item.Properties());
    
    
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
