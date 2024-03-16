package uwu.lopyluna.calamos.networking.packets.S2C;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.client.ClientProxy;
import uwu.lopyluna.calamos.networking.packets.Packet;

import java.util.UUID;

public class UpdateBossBarPacket extends Packet {
    public static final ResourceLocation ID = new ResourceLocation(CalamosMod.MODID, "update_boss_bar");

    private UUID bossID;
    private boolean remove;
    private ResourceLocation registryName;

    public UpdateBossBarPacket(UUID bossID, LivingEntity entity) {
        this.bossID = bossID;
        if (entity != null) {
            this.registryName = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
            this.remove = false;
        }
        else {
            this.registryName = null;
            this.remove = true;
        }
    }
    public UpdateBossBarPacket(FriendlyByteBuf buf) {
        this.bossID = buf.readUUID();
        this.remove = buf.readBoolean();
        this.registryName = buf.readResourceLocation();
    }

    @Override
    public void handleServer(PlayPayloadContext context) {

    }

    @Override
    public void handleClient(PlayPayloadContext context) {
        context.workHandler().execute(() -> {
            if (this.registryName == null) {
                ClientProxy.bossBarRegistryNames.remove(this.bossID);
            }
            else {
                ClientProxy.bossBarRegistryNames.put(this.bossID, this.registryName);
            }
        });
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeUUID(this.bossID);
        buf.writeBoolean(this.remove);
        if (!this.remove && this.registryName != null) buf.writeResourceLocation(this.registryName);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }
}
