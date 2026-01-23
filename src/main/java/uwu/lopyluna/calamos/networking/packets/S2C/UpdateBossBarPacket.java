package uwu.lopyluna.calamos.networking.packets.S2C;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.client.ClientProxy;
import uwu.lopyluna.calamos.utilities.ModUtils;

import java.util.Optional;
import java.util.UUID;

public record UpdateBossBarPacket(UUID bossID, boolean remove, ResourceLocation registryName) implements CustomPacketPayload {
    public static final Type<UpdateBossBarPacket> TYPE = new Type<>(CalamosMod.asResource("update_boss_bar"));

    public static final StreamCodec<FriendlyByteBuf, UpdateBossBarPacket> STREAM_CODEC = StreamCodec.composite(
            ModUtils.UUID_STREAM_CODEC, UpdateBossBarPacket::bossID,
            ByteBufCodecs.BOOL, UpdateBossBarPacket::remove,
            ByteBufCodecs.optional(ResourceLocation.STREAM_CODEC).map(or -> or.orElse(null), Optional::ofNullable), UpdateBossBarPacket::registryName,
            UpdateBossBarPacket::new
    );

    public static UpdateBossBarPacket create(UUID bossID, LivingEntity entity) {
        ResourceLocation resourceLocation;
        boolean doRemove;
        if (entity != null) {
            resourceLocation = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
            doRemove = false;
        } else {
            resourceLocation = null;
            doRemove = true;
        }
        return new UpdateBossBarPacket(bossID, doRemove, resourceLocation);
    }

    public void handle(IPayloadContext context) {
        context.enqueueWork(() -> ClientProxy.handleUpdateBossBarPacket(this.bossID, this.registryName));
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
