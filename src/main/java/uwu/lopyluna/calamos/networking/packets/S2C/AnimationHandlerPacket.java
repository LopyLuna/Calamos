package uwu.lopyluna.calamos.networking.packets.S2C;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.client.ClientProxy;
import uwu.lopyluna.calamos.networking.packets.Packet;

import java.util.UUID;

public class AnimationHandlerPacket extends Packet {
    public static final ResourceLocation ID = new ResourceLocation(CalamosMod.MODID, "animation_handler");

    private final String animation;
    private final UUID playerUUID;
    private final boolean renderFirstPerson;

    public AnimationHandlerPacket(String animation, UUID playerUUID, boolean renderFirstPerson) {
        this.animation = animation;
        this.playerUUID = playerUUID;
        this.renderFirstPerson = renderFirstPerson;
    }

    public AnimationHandlerPacket(FriendlyByteBuf buf) {
        this.animation = buf.readUtf(32767);
        this.playerUUID = buf.readUUID();
        this.renderFirstPerson = buf.readBoolean();
    }

    @Override
    public void handleServer(PlayPayloadContext context) {
    }

    @Override
    public void handleClient(PlayPayloadContext context) {
        context.workHandler().execute(() -> {
            ClientProxy.handleAnimationPacket(this.animation, this.playerUUID, this.renderFirstPerson);
        });
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeUtf(this.animation);
        buf.writeUUID(this.playerUUID);
        buf.writeBoolean(this.renderFirstPerson);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }
}
