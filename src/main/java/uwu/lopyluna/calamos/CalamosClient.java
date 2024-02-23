package uwu.lopyluna.calamos;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterEntitySpectatorShadersEvent;
import net.neoforged.neoforge.client.event.RegisterGuiOverlaysEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.gui.overlay.VanillaGuiOverlay;
import uwu.lopyluna.calamos.client.wings.WingsLayer;
import uwu.lopyluna.calamos.client.wings.WingsModel;
import uwu.lopyluna.calamos.elements.ModEntity;
import uwu.lopyluna.calamos.elements.ModMenuType;
import uwu.lopyluna.calamos.elements.items.wings.FlightMeterOverlay;
import uwu.lopyluna.calamos.elements.screens.HallowWorkbenchScreen;

@SuppressWarnings("removal")
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = CalamosMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CalamosClient {
    public static final ModelLayerLocation WINGS_LAYER = new ModelLayerLocation(new ResourceLocation(CalamosMod.MODID, "wings"), "main");
    
    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAbove(VanillaGuiOverlay.AIR_LEVEL.id(), "flight_meter", FlightMeterOverlay.INSTANCE);
    }
    @SubscribeEvent
    public static void registerMenuScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuType.HALLOW_WORKBENCH_MENU.get(), HallowWorkbenchScreen::new);
    }
    
    @SubscribeEvent
    public static void registerSpectatorShaders(RegisterEntitySpectatorShadersEvent event) {
        event.register(ModEntity.PESTIS_PLAYER.get(), new ResourceLocation(CalamosMod.MODID, "shaders/post/pestis.json"));
    }
    @SubscribeEvent
    private static void registerLayerDefinitions(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(WINGS_LAYER, WingsModel::createBodyLayer);
    }
    @SubscribeEvent
    public static void addLayers(EntityRenderersEvent.AddLayers event) {
        EntityRenderDispatcher dispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        WingsLayer.registerOnAll(dispatcher, event.getEntityModels());
    }
    
    
}
