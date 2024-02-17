package uwu.lopyluna.calamos.networking.packets.S2C;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.client.ClientProxy;
import uwu.lopyluna.calamos.networking.packets.Packet;

public class PestisCameraPacket extends Packet {
    public static final ResourceLocation ID = new ResourceLocation(CalamosMod.MODID, "pestis_camera");
    private final int entityId;
    private final int targetId;
    private final boolean shouldRelease;
    
    public PestisCameraPacket(int entityId, int targetId, boolean shouldRelease) {
        this.entityId = entityId;
        this.targetId = targetId;
        this.shouldRelease = shouldRelease;
    }
    public PestisCameraPacket(FriendlyByteBuf buf) {
        this.entityId = buf.readInt();
        this.targetId = buf.readInt();
        this.shouldRelease = buf.readBoolean();
    }
    
    @Override
    public void handleServer(PlayPayloadContext context) {
    }
    
    @Override
    public void handleClient(PlayPayloadContext context) {
        context.workHandler().execute(() -> {

            ClientProxy.handlePestisCameraPacket(this.targetId, this.shouldRelease);

        });
    }
    
    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeInt(entityId);
        buf.writeInt(targetId);
        buf.writeBoolean(shouldRelease);
    }
    
    @Override
    public ResourceLocation id() {
        return ID;
    }
}
