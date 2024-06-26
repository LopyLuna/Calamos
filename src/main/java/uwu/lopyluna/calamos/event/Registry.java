package uwu.lopyluna.calamos.event;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.client.entity.model.WormHead;
import uwu.lopyluna.calamos.client.entity.model.WormSegment;
import uwu.lopyluna.calamos.client.entity.renderer.PestisPlayerRenderer;
import uwu.lopyluna.calamos.client.entity.renderer.WormHeadRenderer;
import uwu.lopyluna.calamos.client.entity.renderer.WormSegmentRenderer;
import uwu.lopyluna.calamos.client.entity.renderer.machina.MachinaZombieRenderer;
import uwu.lopyluna.calamos.elements.ModEntity;
import uwu.lopyluna.calamos.elements.entity.boone.BooneRenderer;
import uwu.lopyluna.calamos.elements.entity.dynamite.DynamiteModel;
import uwu.lopyluna.calamos.elements.entity.dynamite.DynamiteRenderer;
import uwu.lopyluna.calamos.elements.entity.eye.EyeRenderer;
import uwu.lopyluna.calamos.elements.entity.wildfire.WildfireRenderer;

@Mod.EventBusSubscriber(modid = CalamosMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Registry {


    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntity.WORM_HEAD.get(), WormHeadRenderer::new);
        event.registerEntityRenderer(ModEntity.WORM_PART.get(), WormSegmentRenderer::new);
        event.registerEntityRenderer(ModEntity.PESTIS_PLAYER.get(), PestisPlayerRenderer::new);
        event.registerEntityRenderer(ModEntity.WILDFIRE.get(), WildfireRenderer::new);
        event.registerEntityRenderer(ModEntity.BOONE_THE_BOOM.get(), BooneRenderer::new);
        event.registerEntityRenderer(ModEntity.EYE.get(), EyeRenderer::new);
        event.registerEntityRenderer(ModEntity.DYNAMITE.get(), DynamiteRenderer::new);
        event.registerEntityRenderer(ModEntity.MACHINA_ZOMBIE.get(), MachinaZombieRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(WormHead.LAYER_LOCATION, WormHead::createBodyLayer);
        event.registerLayerDefinition(WormSegment.LAYER_LOCATION, WormSegment::createBodyLayer);
        event.registerLayerDefinition(DynamiteModel.LAYER_LOCATION, DynamiteModel::createBodyLayer);
    }


}
