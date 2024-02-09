package uwu.lopyluna.calamos.elements;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.elements.items.properties.DebugHealthItem;
import uwu.lopyluna.calamos.utilities.ModUtils;

import java.util.function.Supplier;


@SuppressWarnings({"unused"})
public class ModItems {

    public static final DeferredRegister.Items ITEMS = ModUtils.createRegister(DeferredRegister::createItems);

    public static final DeferredItem<Item> DEBUG_HEALTH = regItem("debug_health", () -> new DebugHealthItem(new Item.Properties()
            .fireResistant().stacksTo(1)
    ));



    public static <T extends Item> DeferredItem<T> regItem(String id, Supplier<T> pIProp) {return ITEMS.register(id.toLowerCase(), pIProp);}
    public static void staticInit() {}

    public static DeferredRegister.Items getItems() {
        return ITEMS;
    }
}
