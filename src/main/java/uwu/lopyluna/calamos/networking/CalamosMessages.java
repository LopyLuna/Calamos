package uwu.lopyluna.calamos.networking;

import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.networking.packets.C2S.HookThrowingPacket;
import uwu.lopyluna.calamos.networking.packets.S2C.AnimationHandlerPacket;
import uwu.lopyluna.calamos.networking.packets.S2C.PestisCameraPacket;
import uwu.lopyluna.calamos.networking.packets.S2C.UpdateBossBarPacket;

public class CalamosMessages {

    private static PayloadRegistrar registrar;

    public static void init() {
        CalamosMod.getEventBus().addListener(CalamosMessages::registerMessages);
    }

    private static void registerMessages(RegisterPayloadHandlersEvent event) {
        registrar = event.registrar(CalamosMod.MODID);

        registrar.playToServer(HookThrowingPacket.TYPE, HookThrowingPacket.STREAM_CODEC, HookThrowingPacket::handle);
        registrar.playToClient(PestisCameraPacket.TYPE, PestisCameraPacket.STREAM_CODEC, PestisCameraPacket::handle);
        registrar.playToClient(AnimationHandlerPacket.TYPE, AnimationHandlerPacket.STREAM_CODEC, AnimationHandlerPacket::handle);
        registrar.playToClient(UpdateBossBarPacket.TYPE, UpdateBossBarPacket.STREAM_CODEC, UpdateBossBarPacket::handle);
    }
}
