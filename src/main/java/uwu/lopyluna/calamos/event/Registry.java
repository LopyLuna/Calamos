package uwu.lopyluna.calamos.event;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
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
import uwu.lopyluna.calamos.elements.ModEntity;
import uwu.lopyluna.calamos.elements.entity.boone.BooneModel;
import uwu.lopyluna.calamos.elements.entity.boone.BooneRenderer;
import uwu.lopyluna.calamos.elements.entity.machina.pestis_infection.PestisPlayerEntity;
import uwu.lopyluna.calamos.elements.entity.wildfire.WildfireRenderer;

@Mod.EventBusSubscriber(modid = CalamosMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Registry {


    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntity.WORM_HEAD.get(), WormHeadRenderer::new);
        event.registerEntityRenderer(ModEntity.WORM_PART.get(), WormSegmentRenderer::new);
        event.registerEntityRenderer(ModEntity.PESTIS_PLAYER.get(), (EntityRendererProvider.Context pContext) -> new PestisPlayerRenderer(pContext, PestisPlayerEntity.shouldUseSlimModel()));
        event.registerEntityRenderer(ModEntity.WILDFIRE.get(), WildfireRenderer::new);
        event.registerEntityRenderer(ModEntity.BOONE_THE_BOOM.get(), BooneRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(WormHead.LAYER_LOCATION, WormHead::createBodyLayer);
        event.registerLayerDefinition(WormSegment.LAYER_LOCATION, WormSegment::createBodyLayer);
    }


}
