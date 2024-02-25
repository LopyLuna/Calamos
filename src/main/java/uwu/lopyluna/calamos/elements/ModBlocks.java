package uwu.lopyluna.calamos.elements;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.elements.block.HallowWorkbenchBlock;
import uwu.lopyluna.calamos.elements.block.IGoBoomBoomBoomTroom;
import uwu.lopyluna.calamos.utilities.ModUtils;

import java.util.Collection;
import java.util.function.Supplier;

@SuppressWarnings({"unused"})
public final class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = ModUtils.createRegister(DeferredRegister::createBlocks);
    public static Collection<DeferredHolder<Block, ? extends Block>> decorativeBlocks;
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

    
    
    
    
    
    
    
    
    

    //Ores
    public static final DeferredBlock<Block> COPPER_ORE = register("copper_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> METEORITE_ORE = register("meteorite_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());


    public static final DeferredBlock<Block> UNSTABLE_ULTIMITA_TNT = register("unstable_ultimita_tnt",
            () -> new IGoBoomBoomBoomTroom(25.0F, true, false, BlockBehaviour.Properties.ofFullCopy(Blocks.TNT)
            ), new Item.Properties());

    public static final DeferredBlock<Block> ULTIMITA_TNT = register("ultimita_tnt",
            () -> new IGoBoomBoomBoomTroom(20.0F, false, true, BlockBehaviour.Properties.ofFullCopy(Blocks.TNT)
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
