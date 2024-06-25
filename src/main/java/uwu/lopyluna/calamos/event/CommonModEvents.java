package uwu.lopyluna.calamos.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.elements.ModEntity;
import uwu.lopyluna.calamos.elements.entity.Worm;
import uwu.lopyluna.calamos.elements.entity.boone.BooneEntity;
import uwu.lopyluna.calamos.elements.entity.dynamite.DynamiteEntity;
import uwu.lopyluna.calamos.elements.entity.eye.EyeEntity;
import uwu.lopyluna.calamos.elements.entity.machina.infected.MachinaZombie;
import uwu.lopyluna.calamos.elements.entity.machina.pestis_infection.PestisPlayerEntity;
import uwu.lopyluna.calamos.elements.entity.wildfire.WildfireEntity;

@Mod.EventBusSubscriber(modid = CalamosMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {

    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntity.WORM_HEAD.get(), Worm.createAttributes().build());
        event.put(ModEntity.WORM_PART.get(), Worm.createAttributes().build());
        event.put(ModEntity.PESTIS_PLAYER.get(), PestisPlayerEntity.createAttributes().build());
        event.put(ModEntity.MACHINA_ZOMBIE.get(), MachinaZombie.createAttributes().build());
        event.put(ModEntity.WILDFIRE.get(), WildfireEntity.createAttributes().build());
        event.put(ModEntity.EYE.get(), EyeEntity.createAttributes().build());
        event.put(ModEntity.BOONE_THE_BOOM.get(), BooneEntity.createAttributes().build());
    }
}
