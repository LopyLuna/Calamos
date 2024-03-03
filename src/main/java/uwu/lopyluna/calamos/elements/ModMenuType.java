package uwu.lopyluna.calamos.elements;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.elements.menu.HallowWorkbenchMenu;
import uwu.lopyluna.calamos.elements.menu.SawmillMenu;
import uwu.lopyluna.calamos.utilities.ModUtils;

public class ModMenuType {
    public static final DeferredRegister<MenuType<?>> MENUS = ModUtils.createRegister(Registries.MENU);

    public static final DeferredHolder<MenuType<?>, MenuType<HallowWorkbenchMenu>> HALLOW_WORKBENCH_MENU = register("hallow_workbench_menu",
            HallowWorkbenchMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<SawmillMenu>> SAWMILL_MENU = register("sawmill_menu",
            SawmillMenu::new);

    private static <T extends AbstractContainerMenu>DeferredHolder<MenuType<?>, MenuType<T>> register(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void staticInit(){}
}
