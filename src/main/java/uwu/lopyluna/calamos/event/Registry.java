package uwu.lopyluna.calamos.event;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.resources.PlayerSkin;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.client.entity.model.WormHead;
import uwu.lopyluna.calamos.client.entity.model.WormSegment;
import uwu.lopyluna.calamos.client.entity.renderer.LifestealOrbRenderer;
import uwu.lopyluna.calamos.client.entity.renderer.PestisPlayerRenderer;
import uwu.lopyluna.calamos.client.entity.renderer.WormHeadRenderer;
import uwu.lopyluna.calamos.client.entity.renderer.WormSegmentRenderer;
import uwu.lopyluna.calamos.client.entity.renderer.machina.MachinaZombieRenderer;
import uwu.lopyluna.calamos.client.render.hook.HookRenderer;
import uwu.lopyluna.calamos.elements.ModEntity;
import uwu.lopyluna.calamos.elements.entity.boone.BooneRenderer;
import uwu.lopyluna.calamos.elements.entity.dynamite.DynamiteModel;
import uwu.lopyluna.calamos.elements.entity.dynamite.DynamiteRenderer;
import uwu.lopyluna.calamos.elements.entity.eye.EyeRenderer;
import uwu.lopyluna.calamos.elements.entity.machina.pestis_infection.PestisPlayerEntity;
import uwu.lopyluna.calamos.elements.entity.wildfire.WildfireRenderer;
import uwu.lopyluna.calamos.elements.items.equipment.tool.arrow.irradiated.IrradiatedArrowRenderer;

import java.util.Map;

@EventBusSubscriber(modid = CalamosMod.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class Registry {
    private static final Map<PlayerSkin.Model, EntityRendererProvider<PestisPlayerEntity>> PLAYER_PROVIDERS;

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntity.WORM_HEAD.get(), WormHeadRenderer::new);
        event.registerEntityRenderer(ModEntity.WORM_PART.get(), WormSegmentRenderer::new);
        event.registerEntityRenderer(ModEntity.WILDFIRE.get(), WildfireRenderer::new);
        event.registerEntityRenderer(ModEntity.BOONE_THE_BOOM.get(), BooneRenderer::new);
        event.registerEntityRenderer(ModEntity.EYE.get(), EyeRenderer::new);
        event.registerEntityRenderer(ModEntity.DYNAMITE.get(), DynamiteRenderer::new);
        event.registerEntityRenderer(ModEntity.IRRADIATED_ARROW.get(), IrradiatedArrowRenderer::new);
        event.registerEntityRenderer(ModEntity.MACHINA_ZOMBIE.get(), MachinaZombieRenderer::new);
        event.registerEntityRenderer(ModEntity.HOOK.get(), HookRenderer::new);
        event.registerEntityRenderer(ModEntity.LIFESTEAL_ORB.get(), LifestealOrbRenderer::new);
        PLAYER_PROVIDERS.forEach((model, provider) -> event.registerEntityRenderer(ModEntity.PESTIS_PLAYER.get(), provider));
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(WormHead.LAYER_LOCATION, WormHead::createBodyLayer);
        event.registerLayerDefinition(WormSegment.LAYER_LOCATION, WormSegment::createBodyLayer);
        event.registerLayerDefinition(DynamiteModel.LAYER_LOCATION, DynamiteModel::createBodyLayer);
    }

    static {
        PLAYER_PROVIDERS = Map.of(PlayerSkin.Model.WIDE, (context) -> new PestisPlayerRenderer(context, false), PlayerSkin.Model.SLIM, (context) -> new PestisPlayerRenderer(context, true));
    }
}
