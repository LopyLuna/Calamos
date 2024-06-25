package uwu.lopyluna.calamos;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.gui.overlay.VanillaGuiOverlay;
import uwu.lopyluna.calamos.client.ClientProxy;
import uwu.lopyluna.calamos.client.gui.CalamosBossBar;
import uwu.lopyluna.calamos.client.wings.WingsLayer;
import uwu.lopyluna.calamos.client.wings.WingsModel;
import uwu.lopyluna.calamos.elements.ModBlockEntities;
import uwu.lopyluna.calamos.elements.ModEntity;
import uwu.lopyluna.calamos.elements.ModMenuType;
import uwu.lopyluna.calamos.elements.items.wings.FlightMeterOverlay;
import uwu.lopyluna.calamos.elements.screens.HallowWorkbenchScreen;
import uwu.lopyluna.calamos.elements.screens.MetalGrinderScreen;
import uwu.lopyluna.calamos.elements.screens.SawmillScreen;
import uwu.lopyluna.calamos.utilities.ModWoodTypes;

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
        event.register(ModMenuType.SAWMILL_MENU.get(), SawmillScreen::new);
        event.register(ModMenuType.METAL_GRINDER_MENU.get(), MetalGrinderScreen::new);
    }
    
    @SubscribeEvent
    public static void registerSpectatorShaders(RegisterEntitySpectatorShadersEvent event) {
        event.register(ModEntity.PESTIS_PLAYER.get(), new ResourceLocation(CalamosMod.MODID, "shaders/post/pestis.json"));
        event.register(ModEntity.MACHINA_ZOMBIE.get(), new ResourceLocation(CalamosMod.MODID, "shaders/post/pestis.json"));
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
    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
    }
    @SubscribeEvent
    public static void addWoodTypes(FMLClientSetupEvent event) {
        Sheets.addWoodType(ModWoodTypes.OTHERWORLD_OAK);
        Sheets.addWoodType(ModWoodTypes.TWILIGHT);
    }
}
