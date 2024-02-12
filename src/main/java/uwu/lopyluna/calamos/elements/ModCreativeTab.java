package uwu.lopyluna.calamos.elements;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.elements.items.annotations.NoTab;
import uwu.lopyluna.calamos.utilities.ModUtils;

import java.util.function.Consumer;

//TODO : Need some cleanup later when items and blocks are mostly implemented
public final class ModCreativeTab {
    private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = ModUtils.createRegister(Registries.CREATIVE_MODE_TAB);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CALAMOS_TAB;

    static {
        CALAMOS_TAB = registerTab("calamos", ModItems.CALAMATIUM_INGOT, output -> {

            for (DeferredHolder<Item, ? extends Item> registry : ModItems.getItems()) {
                if (registry.get() instanceof BlockItem || registry.get().getClass().isAnnotationPresent(NoTab.class)) continue;
                output.accept(registry.get());
            }

            for (DeferredHolder<Block, ? extends Block> registry : ModBlocks.getBlocks()) {
                if (registry.get().getClass().isAnnotationPresent(NoTab.class)) continue;
                output.accept(registry.get());
            }

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
