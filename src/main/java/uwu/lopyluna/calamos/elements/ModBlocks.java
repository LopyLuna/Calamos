package uwu.lopyluna.calamos.elements;

import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.utilities.ModUtils;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = ModUtils.createRegister(DeferredRegister::createBlocks);

    public static void staticInit() {
        ModItems.staticInit();
    }
}
