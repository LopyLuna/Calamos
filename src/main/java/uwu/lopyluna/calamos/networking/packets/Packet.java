package uwu.lopyluna.calamos.networking.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPlayPayloadHandler;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import net.neoforged.neoforge.network.registration.IDirectionAwarePayloadHandlerBuilder;


// Why are NeoForge packets records? -- Zeus.
public abstract class Packet implements CustomPacketPayload {

    public Packet() {
        new CalamosPacket<>(this, this.id());
    }

    public Packet(FriendlyByteBuf buf) { // Not directly called, don't delete this!

    }

    public abstract void handleServer(PlayPayloadContext context);
    public abstract void handleClient(PlayPayloadContext context);

    public abstract void write(FriendlyByteBuf buf);

    public abstract ResourceLocation id();

    public record CalamosPacket<MSG extends Packet>(MSG packet, ResourceLocation id) implements CustomPacketPayload {


        @Override
        public void write(FriendlyByteBuf pBuffer) {
            this.packet.write(pBuffer);
        }

        public MSG decode(FriendlyByteBuf pBuffer) {
            try {
                return (MSG) this.packet.getClass().getConstructor(FriendlyByteBuf.class).newInstance(pBuffer);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public ResourceLocation id() {
            return this.id;
        }

        public static <P extends Packet> void handle(IDirectionAwarePayloadHandlerBuilder<P, IPlayPayloadHandler<P>> handler) {
            handler.server(Packet::handleServer);
            handler.client(Packet::handleClient);
        }

    }

}
