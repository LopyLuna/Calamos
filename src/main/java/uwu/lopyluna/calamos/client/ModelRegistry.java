package uwu.lopyluna.calamos.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.core.items.equipment.armor.SpectreArmorItem;

@EventBusSubscriber(modid = CalamosMod.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ModelRegistry {

    public static SpectreArmorItem.Model SPECTRE_ARMOR;

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(SpectreArmorItem.Model.LAYER, SpectreArmorItem.Model::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.AddLayers event) {
        SPECTRE_ARMOR = new SpectreArmorItem.Model(event.getEntityModels().bakeLayer(SpectreArmorItem.Model.LAYER));
    }
}
