package uwu.lopyluna.calamos.elements;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.utilities.ModUtils;

import java.util.function.Supplier;

@SuppressWarnings({"unused"})
public class ModBlocks {
    public static final DeferredRegister.Items ITEMS = ModUtils.createRegister(DeferredRegister::createItems);
    public static final DeferredRegister.Blocks BLOCKS = ModUtils.createRegister(DeferredRegister::createBlocks);


    public static final DeferredBlock<Block> STONE = regBlock("stone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .mapColor(MapColor.STONE)
                    .strength(3.5F)
                    .explosionResistance(7.0F)
            ), new Item.Properties()
                    .fireResistant());





    //Extra Cool shit :3
    public static <T extends Block> DeferredBlock<T> regBlock(String id, Supplier<T> block, Item.Properties pIProp) {
        DeferredBlock<T> toReturn = BLOCKS.register(id.toLowerCase(), block);
        regBlockItem(id.toLowerCase(), toReturn, pIProp);
        return toReturn;
    }
    public static <T extends Block> void regBlockItem(String id, DeferredBlock<T> block, Item.Properties pIProp) {ITEMS.register(id.toLowerCase(), () -> new BlockItem(block.get(), pIProp));}
    public static void staticInit() {}
    public static DeferredRegister.Items getBlockItems() {return ITEMS;}
    public static DeferredRegister.Blocks getBlocks() {return BLOCKS;}
}
