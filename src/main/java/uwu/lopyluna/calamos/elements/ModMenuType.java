package uwu.lopyluna.calamos.elements;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.core.menu.HallowWorkbenchMenu;
import uwu.lopyluna.calamos.core.menu.MetalGrinderMenu;
import uwu.lopyluna.calamos.core.menu.SawmillMenu;
import uwu.lopyluna.calamos.utilities.ModUtils;

public class ModMenuType {
    public static final DeferredRegister<MenuType<?>> MENUS = ModUtils.createRegister(Registries.MENU);

    public static final DeferredHolder<MenuType<?>, MenuType<HallowWorkbenchMenu>> HALLOW_WORKBENCH_MENU = register("hallow_workbench_menu",
            HallowWorkbenchMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<SawmillMenu>> SAWMILL_MENU = register("sawmill_menu",
            SawmillMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<MetalGrinderMenu>> METAL_GRINDER_MENU = register("metal_grinder_menu",
            MetalGrinderMenu::new);

    private static <T extends AbstractContainerMenu>DeferredHolder<MenuType<?>, MenuType<T>> register(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void staticInit(){}
}
