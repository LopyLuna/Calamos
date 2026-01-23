package uwu.lopyluna.calamos.networking.packets.S2C;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.client.ClientProxy;
import uwu.lopyluna.calamos.utilities.ModUtils;

import java.util.UUID;

public record AnimationHandlerPacket(String animation, UUID playerUUID, boolean renderFirstPerson) implements CustomPacketPayload {
    public static final Type<AnimationHandlerPacket> TYPE = new Type<>(CalamosMod.asResource("animation_handler"));

    public static final StreamCodec<FriendlyByteBuf, AnimationHandlerPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, AnimationHandlerPacket::animation,
            ModUtils.UUID_STREAM_CODEC, AnimationHandlerPacket::playerUUID,
            ByteBufCodecs.BOOL, AnimationHandlerPacket::renderFirstPerson,
            AnimationHandlerPacket::new
    );

    public void handle(IPayloadContext context) {
        context.enqueueWork(() -> {
            ClientProxy.handleAnimationPacket(this.animation, this.playerUUID, this.renderFirstPerson);
        });
    }

    @Override
    public Type<AnimationHandlerPacket> type() {
        return TYPE;
    }
}
