package uwu.lopyluna.calamos.networking.packets.S2C;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.client.ClientProxy;

public record PestisCameraPacket(int entityId, int targetId, boolean shouldRelease) implements CustomPacketPayload {
    public static final Type<PestisCameraPacket> TYPE = new Type<>(CalamosMod.asResource("pestis_camera"));

    public static final StreamCodec<FriendlyByteBuf, PestisCameraPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, PestisCameraPacket::entityId,
            ByteBufCodecs.INT, PestisCameraPacket::targetId,
            ByteBufCodecs.BOOL, PestisCameraPacket::shouldRelease,
            PestisCameraPacket::new
    );

    public void handle(IPayloadContext context) {
        context.enqueueWork(() -> {
            ClientProxy.handlePestisCameraPacket(this.targetId, this.shouldRelease);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
