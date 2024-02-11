package uwu.lopyluna.calamos.elements;

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.elements.items.properties.DebugHealthItem;
import uwu.lopyluna.calamos.elements.items.tool.CalamosReaper;
import uwu.lopyluna.calamos.elements.items.tool.CalamosSword;
import uwu.lopyluna.calamos.elements.items.tool.CalamosTiers;
import uwu.lopyluna.calamos.utilities.ModUtils;

import java.util.Collection;
import java.util.function.Supplier;


@SuppressWarnings({"unused"})
public final class ModItems {
    private static final Supplier<Item> SIMPLE_SUPPLIER = () -> new Item(new Item.Properties());
    public static final DeferredRegister.Items ITEMS = ModUtils.createRegister(DeferredRegister::createItems);
    public static final DeferredItem<Item> DEBUG_HEALTH = register("debug_health", () -> new DebugHealthItem(new Item.Properties()
            .fireResistant().stacksTo(1)
    ));

    public static final DeferredItem<Item> GARNET = registerSimple("garnet");
    public static final DeferredItem<Item> JADE = registerSimple("jade");
    public static final DeferredItem<Item> KUNZITE = registerSimple("kunzite");
    public static final DeferredItem<Item> MOONSTONE = registerSimple("moonstone");
    public static final DeferredItem<Item> OPAL = registerSimple("opal");
    public static final DeferredItem<Item> RUBY = registerSimple("ruby");
    public static final DeferredItem<Item> SAPPHIRE = registerSimple("sapphire");
    public static final DeferredItem<Item> SPINEL = registerSimple("spinel");
    public static final DeferredItem<Item> SUNSTONE = registerSimple("sunstone");
    public static final DeferredItem<Item> TANZANITE = registerSimple("tanzanite");
    public static final DeferredItem<Item> TOPAZ = registerSimple("topaz");
    public static final DeferredItem<Item> METEORITE_INGOT = registerSimple("meteorite_ingot", new Item.Properties().fireResistant());
    public static final DeferredItem<Item> RAW_METEORITE = registerSimple("raw_meteorite", new Item.Properties().fireResistant());
    public static final DeferredItem<Item> BLOOD_ORB = registerSimple("blood_orb");
    public static final DeferredItem<Item> ECTOPLASMA = registerSimple("ectoplasma");
    public static final DeferredItem<Item> BLOODBORE_INGOT = registerSimple("bloodbore_ingot");
    public static final DeferredItem<Item> CALAMATIUM_INGOT = registerSimple("calamatium_ingot");
    public static final DeferredItem<Item> ECTOLIGHT_INGOT = registerSimple("ectolight_ingot");
    public static final DeferredItem<Item> LEAD_INGOT = registerSimple("lead_ingot");
    public static final DeferredItem<Item> MAGNETITE_INGOT = registerSimple("magnetite_ingot");
    public static final DeferredItem<Item> OBSTEEL_INGOT = registerSimple("obsteel_ingot");
    public static final DeferredItem<Item> PLATINUM_INGOT = registerSimple("platinum_ingot");
    public static final DeferredItem<Item> STARINIUM_INGOT = registerSimple("starinium_ingot");
    public static final DeferredItem<Item> TERRAULITE_INGOT = registerSimple("terraulite_ingot");
    public static final DeferredItem<Item> ULTIMITA_INGOT = registerSimple("ultimita_ingot");
    public static final DeferredItem<Item> METEORITE_REAPER = register("meteorite_reaper", () -> new CalamosReaper(CalamosTiers.METEORITE, 3, -2.4F, 5, true, new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> METEORITE_SWORD = register("meteorite_sword", () -> new CalamosSword(CalamosTiers.METEORITE, 3, -2.4F, true, new Item.Properties().fireResistant()));
    private static DeferredItem<Item> registerSimple(String name, Item.Properties itemProperties) {
        return register(name, () -> new Item(itemProperties));
    }

    private static DeferredItem<Item> registerSimple(String name) {
        return register(name, SIMPLE_SUPPLIER);
    }

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

    public static Collection<DeferredHolder<Item, ? extends Item>> getItems() {
        return ITEMS.getEntries();
    }
}
