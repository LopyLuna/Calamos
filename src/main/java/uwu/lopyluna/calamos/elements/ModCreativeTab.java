package uwu.lopyluna.calamos.elements;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


import static uwu.lopyluna.calamos.CalamosMod.MODID;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CALAMOS_TAB = CREATIVE_MODE_TABS.register("calamos_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("Calamos"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ModItems.DEBUG_HEALTH.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                //Add Items to this
                //Items
                output.accept(ModItems.DEBUG_HEALTH);
                //Blocks
                output.accept(ModBlocks.STONE);



            }).build());
}
