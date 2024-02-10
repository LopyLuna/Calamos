package uwu.lopyluna.calamos.elements;

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.elements.items.properties.DebugHealthItem;
import uwu.lopyluna.calamos.utilities.ModUtils;

import java.util.Collection;
import java.util.function.Supplier;


@SuppressWarnings({"unused"})
public class ModItems {
    public static final DeferredRegister.Items ITEMS = ModUtils.createRegister(DeferredRegister::createItems);

    public static final DeferredItem<Item> DEBUG_HEALTH = register("debug_health", () -> new DebugHealthItem(new Item.Properties()
            .fireResistant().stacksTo(1)
    ));
    
    public static final DeferredItem<Item> GARNET = register("garnet", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> JADE = register("jade", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> KUNZITE = register("kunzite", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MOONSTONE = register("moonstone", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> OPAL = register("opal", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RUBY = register("ruby", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SAPPHIRE = register("sapphire", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SPINEL = register("spinel", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SUNSTONE = register("sunstone", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TANZANITE = register("tanzanite", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TOPAZ = register("topaz", () -> new Item(new Item.Properties()));
    
    
    public static final DeferredItem<Item> METEORITE_INGOT = register("meteorite_ingot", () -> new Item(new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> RAW_METEORITE = register("raw_meteorite", () -> new Item(new Item.Properties().fireResistant()));
    
    
    
    
    
    
    
    
    
    
    

    private static <T extends Item> DeferredItem<T> register(String id, Supplier<T> pIProp) {
        return ITEMS.register(id.toLowerCase(), pIProp);
    }

    public static void staticInit() {
        CalamosMod.LOGGER.info("metbal");
    }

    //private-package so block register class can use
    static void registerBlockItem(Holder<Block> blockHolder) {
        registerBlockItem(blockHolder, new Item.Properties());
    }

    //private-package so block register class can use
    static void registerBlockItem(Holder<Block> blockHolder, Item.Properties properties) {
        ITEMS.registerSimpleBlockItem(blockHolder, properties);
    }
    static void registerSimpleItem(String name) {
        ITEMS.registerSimpleItem(name);
    }

    public static Collection<DeferredHolder<Item,? extends Item>> getItems() {
        return ITEMS.getEntries();
    }
}
