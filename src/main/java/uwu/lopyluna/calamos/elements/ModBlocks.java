package uwu.lopyluna.calamos.elements;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.elements.block.HallowWorkbenchBlock;
import uwu.lopyluna.calamos.utilities.ModUtils;

import java.util.Collection;
import java.util.function.Supplier;

@SuppressWarnings({"unused"})
public final class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = ModUtils.createRegister(DeferredRegister::createBlocks);

    //Gem Blocks
    public static final DeferredBlock<Block> GARNET_BLOCK = register("garnet_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)),
            new Item.Properties());
    public static final DeferredBlock<Block> JADE_BLOCK = register("jade_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)),
            new Item.Properties());
    public static final DeferredBlock<Block> KUNZITE_BLOCK = register("kunzite_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)),
            new Item.Properties());
    public static final DeferredBlock<Block> MOONSTONE_BLOCK = register("moonstone_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)),
            new Item.Properties());
    public static final DeferredBlock<Block> OPAL_BLOCK = register("opal_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)),
            new Item.Properties());
    public static final DeferredBlock<Block> RUBY_BLOCK = register("ruby_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)),
            new Item.Properties());
    public static final DeferredBlock<Block> SAPPHIRE_BLOCK = register("sapphire_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)),
            new Item.Properties());
    public static final DeferredBlock<Block> SPINEL_BLOCK = register("spinel_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)),
            new Item.Properties());
    public static final DeferredBlock<Block> SUNSTONE_BLOCK = register("sunstone_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)),
            new Item.Properties());
    public static final DeferredBlock<Block> TANZANITE_BLOCK = register("tanzanite_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)),
            new Item.Properties());
    public static final DeferredBlock<Block> TOPAZ_BLOCK = register("topaz_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)),
            new Item.Properties());

    public static final DeferredBlock<Block> STONE = register("stone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .mapColor(MapColor.STONE)
                    .strength(3.5F)
                    .explosionResistance(7.0F)
            ), new Item.Properties()
                    .fireResistant());
    public static final DeferredBlock<Block> PURRASITE = register("purrasite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .mapColor(MapColor.STONE)
                    .strength(3.5F)
                    .explosionResistance(7.0F)
            ), new Item.Properties()
                    .fireResistant());
    public static final DeferredBlock<Block> UMBRALITE = register("umbralite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .mapColor(MapColor.STONE)
                    .strength(3.5F)
                    .explosionResistance(7.0F)
            ), new Item.Properties()
                    .fireResistant());

    public static final DeferredBlock<HallowWorkbenchBlock> HALLOW_WORKBENCH = register("hallow_workbench",
            () -> new HallowWorkbenchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)),
            new Item.Properties().fireResistant());

    //Meteorite Blocks
    public static final DeferredBlock<Block> METEORITE_ORE = register("meteorite_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
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
    public static final DeferredBlock<Block> METEORITE  = register("meteorite",
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
    public static final DeferredBlock<Block> SMOOTH_METEORITE  = register("smooth_meteorite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());
    //Ores
    public static final DeferredBlock<Block> COPPER_ORE = register("copper_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());


    //Extra Cool shit :3
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
