package uwu.lopyluna.calamos.elements;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.elements.effects.MachinaPestisEffect;
import uwu.lopyluna.calamos.utilities.ModUtils;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = ModUtils.createRegister(Registries.MOB_EFFECT);
    
    public static final DeferredHolder<MobEffect, MobEffect> PESTIS = EFFECTS.register("machina_pestis", () -> new MachinaPestisEffect(0x61454e));
    
    public static void staticInit() {
        CalamosMod.LOGGER.info("nauseous metbal");
    }
}
