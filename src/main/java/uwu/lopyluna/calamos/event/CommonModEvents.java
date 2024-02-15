package uwu.lopyluna.calamos.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.elements.ModEntity;
import uwu.lopyluna.calamos.elements.entity.Worm;

@Mod.EventBusSubscriber(modid = CalamosMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {

    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntity.WORM_HEAD.get(), Worm.createAttributes().build());
        event.put(ModEntity.WORM_PART.get(), Worm.createAttributes().build());
    }

}