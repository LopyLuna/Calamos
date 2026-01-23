package uwu.lopyluna.calamos.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.client.wings.WingsModel;

public class ModArmorLayers {
    public static final ModelLayerLocation WINGS = createLocation("wings", "main");
    
    public static void register(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(WINGS, WingsModel::createBodyLayer);
    }
    
    private static ModelLayerLocation createLocation(String model, String layer) {
        return new ModelLayerLocation(CalamosMod.asResource(model), layer);
    }
}
