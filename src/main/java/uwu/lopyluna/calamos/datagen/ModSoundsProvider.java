package uwu.lopyluna.calamos.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SoundDefinition;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.elements.ModSoundEvents;

public class ModSoundsProvider extends SoundDefinitionsProvider {
    public ModSoundsProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, CalamosMod.MODID, fileHelper);
    }
    @Override
    public void registerSounds() {
        this.add(ModSoundEvents.WILDFIRE_DEATH, SoundDefinition.definition().with(this.simpleSound("wildfire/death1")).with(this.simpleSound("wildfire/death2")).with(this.simpleSound("wildfire/death3")));
        this.add(ModSoundEvents.WILDFIRE_HURT, SoundDefinition.definition().with(this.simpleSound("wildfire/hurt1")).with(this.simpleSound("wildfire/hurt2")).with(this.simpleSound("wildfire/hurt3")));
        this.add(ModSoundEvents.WILDFIRE_IDLE, SoundDefinition.definition().with(this.simpleSound("wildfire/idle1")).with(this.simpleSound("wildfire/idle2")).with(this.simpleSound("wildfire/idle3")).with(this.simpleSound("wildfire/idle4")));
        this.add(ModSoundEvents.WILDFIRE_IDLE_LOOP, SoundDefinition.definition().with(this.simpleSound("wildfire/idle_loop")));
        this.add(ModSoundEvents.WILDFIRE_SHIELD_BREAK, SoundDefinition.definition().with(this.simpleSound("wildfire/shield_break1")).with(this.simpleSound("wildfire/shield_break2")).with(this.simpleSound("wildfire/shield_break3")));
        this.add(ModSoundEvents.WILDFIRE_SHOCKWAVE, SoundDefinition.definition().with(this.simpleSound("wildfire/shockwave1")).with(this.simpleSound("wildfire/shockwave2")).with(this.simpleSound("wildfire/shockwave3")));
        this.add(ModSoundEvents.WILDFIRE_STEP, SoundDefinition.definition().with(this.simpleSound("wildfire/step1")).with(this.simpleSound("wildfire/step2")).with(this.simpleSound("wildfire/step3")).with(this.simpleSound("wildfire/step4")));
        this.add(ModSoundEvents.WILDFIRE_SHOOT, SoundDefinition.definition().with(this.simpleSound("wildfire/shoot1")).with(this.simpleSound("wildfire/shoot2")).with(this.simpleSound("wildfire/shoot3")).with(this.simpleSound("wildfire/shoot4")));
        this.add(ModSoundEvents.WILDFIRE_DEBRIS_IMPACT, SoundDefinition.definition().with(this.simpleSound("wildfire/debris_impact1")).with(this.simpleSound("wildfire/debris_impact2")).with(this.simpleSound("wildfire/debris_impact3")).with(this.simpleSound("wildfire/debris_impact4")));
    }
    
    private SoundDefinition.Sound simpleSound(String name) {
        return SoundDefinition.Sound.sound(new ResourceLocation(CalamosMod.MODID, name), SoundDefinition.SoundType.SOUND);
    }
}
