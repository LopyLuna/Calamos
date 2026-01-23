package uwu.lopyluna.calamos.datagen;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.armortrim.TrimMaterial;
import uwu.lopyluna.calamos.elements.ModArmorTrimMaterials;

public class ModTrimMaterialProvider {

    private static void register(BootstrapContext<TrimMaterial> context) {
        ModArmorTrimMaterials.MATERIALS.forEach(context::register);
    }

    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        register(context);
    }
}
