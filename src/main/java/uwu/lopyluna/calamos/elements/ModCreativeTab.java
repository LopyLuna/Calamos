package uwu.lopyluna.calamos.elements;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.utilities.ModUtils;

import java.util.function.Consumer;

//TODO : Need some cleanup later when items and blocks are mostly implemented
public class ModCreativeTab {
    private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = ModUtils.createRegister(Registries.CREATIVE_MODE_TAB);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CALAMOS_TAB;

    static {
        CALAMOS_TAB = registerTab("calamos", ModItems.DEBUG_HEALTH, output -> {
            //Add Items to this
            //Items
            output.accept(ModItems.DEBUG_HEALTH);
            //Blocks
            output.accept(ModBlocks.STONE);
        }, builder -> builder.withTabsBefore(CreativeModeTabs.COMBAT));
    }

    private static DeferredHolder<CreativeModeTab, CreativeModeTab> registerTab(String name, Holder<Item> icon, Consumer<CreativeModeTab.Output> displayItems) {
        return registerTab(name, icon, displayItems, ModUtils.noAction());
    }

    private static DeferredHolder<CreativeModeTab, CreativeModeTab> registerTab(String name, Holder<Item> icon, Consumer<CreativeModeTab.Output> displayItems, Consumer<CreativeModeTab.Builder> additionalProperties) {
        return CREATIVE_MODE_TABS.register(name, id -> {
            final CreativeModeTab.Builder builder = CreativeModeTab.builder();
            builder.title(Component.translatable(id.toLanguageKey("itemGroup")))
                    .icon(() -> new ItemStack(icon))
                    .displayItems((pParameters, pOutput) -> displayItems.accept(pOutput));
            additionalProperties.accept(builder);
            return builder.build();
        });
    }

    public static void staticInit() {

    }
}
