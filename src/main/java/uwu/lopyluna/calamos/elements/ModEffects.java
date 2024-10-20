package uwu.lopyluna.calamos.elements;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.elements.effects.DefaultMobEffect;
import uwu.lopyluna.calamos.elements.effects.IrradiatedEffect;
import uwu.lopyluna.calamos.elements.effects.MachinaPestisEffect;
import uwu.lopyluna.calamos.utilities.ModUtils;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = ModUtils.createRegister(Registries.MOB_EFFECT);
    
    public static final DeferredHolder<MobEffect, MobEffect> PESTIS = EFFECTS.register("machina_pestis",
            () -> new MachinaPestisEffect(0x61454e));

    public static final DeferredHolder<MobEffect, MobEffect> HEALING_SICKNESS = EFFECTS.register("healing_sickness",
            () -> new DefaultMobEffect(MobEffectCategory.NEUTRAL,0xc42558));
    public static final DeferredHolder<MobEffect, MobEffect> MANA_SICKNESS = EFFECTS.register("mana_sickness",
            () -> new DefaultMobEffect(MobEffectCategory.NEUTRAL,0x1f62bf));

    public static final DeferredHolder<MobEffect, MobEffect> POTION_SICKNESS = EFFECTS.register("potion_sickness",
            () -> new DefaultMobEffect(MobEffectCategory.NEUTRAL,0xbdc225));

    public static final DeferredHolder<MobEffect, MobEffect> IRRADIATED = EFFECTS.register("irradiated", IrradiatedEffect::new);

    public static void staticInit() {
        CalamosMod.LOGGER.info("nauseous metbal");
    }
}
