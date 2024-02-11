package uwu.lopyluna.calamos.networking.packets.C2S;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.networking.packets.Packet;

public class TestPacket extends Packet {

    public static final ResourceLocation ID = new ResourceLocation(CalamosMod.MODID, "test_packet");

    public int test;

    public TestPacket(int test) {
        super();
        this.test = test;
    }

    public TestPacket(FriendlyByteBuf buf) {
        this.test = buf.readInt();
    }

    public TestPacket() { // Need this for reflection.

    }


    @Override
    public void handleServer(PlayPayloadContext context) {
        CalamosMod.LOGGER.info("Test packet received {}", this.test);
    }

    @Override
    public void handleClient(PlayPayloadContext context) {
        CalamosMod.LOGGER.info("Test packet received {}", this.test);
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeInt(this.test);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }
}
