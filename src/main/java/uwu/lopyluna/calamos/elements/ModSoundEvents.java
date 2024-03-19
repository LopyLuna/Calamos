package uwu.lopyluna.calamos.elements;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.utilities.ModUtils;

import static uwu.lopyluna.calamos.CalamosMod.MODID;

public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = ModUtils.createRegister(Registries.SOUND_EVENT);
    public static final DeferredHolder<SoundEvent, SoundEvent> WILDFIRE_DEATH = register("entity","wildfire.death");
    public static final DeferredHolder<SoundEvent, SoundEvent> WILDFIRE_HURT = register("entity","wildfire.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> WILDFIRE_IDLE = register("entity","wildfire.idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> WILDFIRE_IDLE_LOOP = register("entity","wildfire.idle_loop");
    public static final DeferredHolder<SoundEvent, SoundEvent> WILDFIRE_STEP = register("entity","wildfire.step");
    public static final DeferredHolder<SoundEvent, SoundEvent> WILDFIRE_SHIELD_BREAK = register("entity","wildfire.shield_break");
    public static final DeferredHolder<SoundEvent, SoundEvent> WILDFIRE_SHOCKWAVE = register("entity","wildfire.shockwave");
    public static final DeferredHolder<SoundEvent, SoundEvent> WILDFIRE_SHOOT = register("entity","wildfire.shoot");
    public static final DeferredHolder<SoundEvent, SoundEvent> WILDFIRE_DEBRIS_IMPACT = register("entity","wildfire.debris_impact");
    
    
    public static DeferredHolder<SoundEvent, SoundEvent> register(String pType, String pName) {
        String id = pType + "." + pName;
        return SOUND_EVENTS.register(pName, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MODID, id)));
    }
    public static void staticInit() {
    
    }
}
