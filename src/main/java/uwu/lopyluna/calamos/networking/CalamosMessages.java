package uwu.lopyluna.calamos.networking;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.networking.packets.C2S.TestPacket;
import uwu.lopyluna.calamos.networking.packets.Packet;
import uwu.lopyluna.calamos.networking.packets.S2C.AnimationHandlerPacket;
import uwu.lopyluna.calamos.networking.packets.S2C.PestisCameraPacket;
import uwu.lopyluna.calamos.networking.packets.S2C.UpdateBossBarPacket;

import java.lang.reflect.InvocationTargetException;

public class CalamosMessages {

    private static IPayloadRegistrar registrar;

    public static void init() {
        CalamosMod.getEventBus().addListener(CalamosMessages::registerMessages);
    }

    private static void registerMessages(RegisterPayloadHandlerEvent event) {
        registrar = event.registrar(CalamosMod.MODID);

        registerPacket(TestPacket.class, TestPacket.ID);
        registerPacket(PestisCameraPacket.class, PestisCameraPacket.ID);
        registerPacket(AnimationHandlerPacket.class, AnimationHandlerPacket.ID);
        registerPacket(UpdateBossBarPacket.class, UpdateBossBarPacket.ID);
    }


    private static <P extends Packet> void registerPacket(Class<P> packet, ResourceLocation id) {
        registrar.play(id, (buffer) -> {
            try {
                return packet.getConstructor(FriendlyByteBuf.class).newInstance(buffer);
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }, Packet.CalamosPacket::handle);
    }

    public static <MSG extends CustomPacketPayload> void send(MSG packet, PacketDistributor.PacketTarget target) {
        target.send(packet);
    }

    public static <MSG extends CustomPacketPayload> void sendToServer(MSG packet) {
        send(packet, PacketDistributor.SERVER.noArg());
    }

    public static <MSG extends CustomPacketPayload> void sendToPlayer(MSG packet, Player player) {
        if (player instanceof ServerPlayer serverPlayer)
            send(packet, PacketDistributor.PLAYER.with(serverPlayer));
    }

}
