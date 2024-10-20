package uwu.lopyluna.calamos.event;

import net.minecraft.world.entity.projectile.WindCharge;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import uwu.lopyluna.calamos.elements.entity.boone.BooneModel;
import uwu.lopyluna.calamos.elements.entity.eye.EyeModel;
import uwu.lopyluna.calamos.elements.entity.wildfire.WildfireModel;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEvents {
    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(WildfireModel.LAYER_LOCATION, WildfireModel::createBodyLayer);
        event.registerLayerDefinition(BooneModel.LAYER_LOCATION, BooneModel::createBodyLayer);
        event.registerLayerDefinition(EyeModel.LAYER_LOCATION, EyeModel::createBodyLayer);
    }
}
