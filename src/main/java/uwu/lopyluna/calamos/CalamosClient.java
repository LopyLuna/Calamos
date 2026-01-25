package uwu.lopyluna.calamos;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.registries.DeferredItem;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;
import uwu.lopyluna.calamos.client.ArmorClientItemExtensions;
import uwu.lopyluna.calamos.client.CalamosReloadableModels;
import uwu.lopyluna.calamos.client.model.hook.HookModel;
import uwu.lopyluna.calamos.client.model.item.CurioModel;
import uwu.lopyluna.calamos.client.model.item.IRenderableCurio;
import uwu.lopyluna.calamos.client.particle.GlowParticle;
import uwu.lopyluna.calamos.client.render.CurioRenderer;
import uwu.lopyluna.calamos.client.wings.WingsModel;
import uwu.lopyluna.calamos.elements.*;
import uwu.lopyluna.calamos.elements.items.equipment.armor.CalamosArmorItem;
import uwu.lopyluna.calamos.elements.items.equipment.wings.FlightMeterOverlay;
import uwu.lopyluna.calamos.elements.screens.HallowWorkbenchScreen;
import uwu.lopyluna.calamos.elements.screens.MetalGrinderScreen;
import uwu.lopyluna.calamos.elements.screens.SawmillScreen;
import uwu.lopyluna.calamos.utilities.ModWoodTypes;

import java.util.function.Function;

@SuppressWarnings("removal")
@EventBusSubscriber(value = Dist.CLIENT, modid = CalamosMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class CalamosClient {
    public static final ModelLayerLocation WINGS_LAYER = new ModelLayerLocation(CalamosMod.asResource("wings"), "main");

    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiLayersEvent event) {
        event.registerAbove(VanillaGuiLayers.AIR_LEVEL, CalamosMod.asResource("flight_meter"), FlightMeterOverlay.INSTANCE);
    }
    @SubscribeEvent
    public static void registerMenuScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuType.HALLOW_WORKBENCH_MENU.get(), HallowWorkbenchScreen::new);
        event.register(ModMenuType.SAWMILL_MENU.get(), SawmillScreen::new);
        event.register(ModMenuType.METAL_GRINDER_MENU.get(), MetalGrinderScreen::new);
    }
    
    @SubscribeEvent
    public static void registerSpectatorShaders(RegisterEntitySpectatorShadersEvent event) {
        event.register(ModEntity.PESTIS_PLAYER.get(), CalamosMod.asResource("shaders/post/pestis.json"));
        event.register(ModEntity.MACHINA_ZOMBIE.get(), CalamosMod.asResource("shaders/post/pestis.json"));
    }
    @SubscribeEvent
    private static void registerLayerDefinitions(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        CuriosRenderers.onLayerRegister(event);
        event.registerLayerDefinition(WINGS_LAYER, WingsModel::createBodyLayer);
        event.registerLayerDefinition(HookModel.LAYER_LOCATION, HookModel::createBodyLayer);
    }
    @SubscribeEvent
    public static void addLayers(EntityRenderersEvent.AddLayers event) {
        EntityRenderDispatcher dispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        //WingsLayer.registerOnAll(dispatcher, event.getEntityModels());
    }
    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
    }
    @SubscribeEvent
    public static void addWoodTypes(FMLClientSetupEvent event) {
        CuriosRenderers.register();
        Sheets.addWoodType(ModWoodTypes.OTHERWORLD_OAK);
        Sheets.addWoodType(ModWoodTypes.TWILIGHT);
    }

    @SubscribeEvent
    public static void addParticleProvider(RegisterParticleProvidersEvent event) {
        event.registerSprite(ModParticleTypes.GLOW.get(), new GlowParticle.Provider());
    }



    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        Function<DeferredItem<? extends CalamosArmorItem>, Void> armor = (item) -> {
            CalamosArmorItem armorItem = item.get();
            event.registerItem(new ArmorClientItemExtensions(armorItem::getModel), item.value());
            CalamosMod.LOGGER.debug("Registered Client Extension for item: {}", item.getRegisteredName());
            return null;
        };
        armor.apply(ModItems.SPECTRE_HOOD);
    }

    @SubscribeEvent
    public static void onModelRegister(ModelEvent.RegisterAdditional evt) {
        var resourceManager = Minecraft.getInstance().getResourceManager();
        CalamosReloadableModels.INSTANCE.onModelRegister(resourceManager,
                id -> evt.register(ModelResourceLocation.standalone(id)));
    }

    public static class CuriosRenderers {
        public static void register() {
            for (Item item : BuiltInRegistries.ITEM.stream().toList()) {
                if (!(item instanceof IRenderableCurio))
                    continue;

                CuriosRendererRegistry.register(item, CurioRenderer::new);
            }
        }

        public static void onLayerRegister(final EntityRenderersEvent.RegisterLayerDefinitions event) {
            for (Item item : BuiltInRegistries.ITEM.stream().toList()) {
                if (!(item instanceof IRenderableCurio renderable))
                    continue;

                event.registerLayerDefinition(CurioModel.getLayerLocation(item), renderable::constructLayerDefinition);
            }
        }
    }
}
