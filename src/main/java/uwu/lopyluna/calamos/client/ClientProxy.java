package uwu.lopyluna.calamos.client;

import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import uwu.lopyluna.calamos.CommonProxy;

public class ClientProxy extends CommonProxy {
    public static CameraType lastPOV = CameraType.FIRST_PERSON;
    public void setRenderViewEntity(Player player, Entity entity) {
        boolean flag = entity != Minecraft.getInstance().getCameraEntity();
        if (player == Minecraft.getInstance().player && Minecraft.getInstance().getCameraEntity() == Minecraft.getInstance().player) {
            lastPOV = Minecraft.getInstance().options.getCameraType();
            Minecraft.getInstance().setCameraEntity(entity);
            Minecraft.getInstance().options.setCameraType(CameraType.FIRST_PERSON);
        }
        if (flag) {
            Minecraft.getInstance().levelRenderer.allChanged();
        }
    }
    public void resetRenderViewEntity(Player player) {
        boolean flag = Minecraft.getInstance().player != Minecraft.getInstance().getCameraEntity();
        if (player == Minecraft.getInstance().player) {
            Minecraft.getInstance().level = (ClientLevel) Minecraft.getInstance().player.level();
            Minecraft.getInstance().setCameraEntity(Minecraft.getInstance().player);
            Minecraft.getInstance().options.setCameraType(lastPOV);
        }
        if (flag) {
            Minecraft.getInstance().levelRenderer.allChanged();
        }
    }
}
