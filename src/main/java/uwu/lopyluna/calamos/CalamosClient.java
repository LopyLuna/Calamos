package uwu.lopyluna.calamos;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterGuiOverlaysEvent;
import net.neoforged.neoforge.client.gui.overlay.VanillaGuiOverlay;
import uwu.lopyluna.calamos.elements.items.wings.FlightMeterOverlay;

@SuppressWarnings("removal")
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = CalamosMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CalamosClient {
    
    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAbove(VanillaGuiOverlay.AIR_LEVEL.id(), "flight_meter", FlightMeterOverlay.INSTANCE);
    }
}
