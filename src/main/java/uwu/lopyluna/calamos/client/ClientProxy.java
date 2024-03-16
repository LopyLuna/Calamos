package uwu.lopyluna.calamos.client;

import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import uwu.lopyluna.calamos.CommonProxy;
import uwu.lopyluna.calamos.utilities.AnimationHandler;

import net.neoforged.neoforge.common.NeoForge;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ClientProxy extends CommonProxy {
    public static final Map<UUID, ResourceLocation> bossBarRegistryNames = new HashMap<>();

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

    public static void handlePestisCameraPacket(int targetId, boolean shouldRelease) {
        Player player = Minecraft.getInstance().player;
        if (player == null) return;
        Entity target = Minecraft.getInstance().level.getEntity(targetId);
        if (target != null && !shouldRelease) {
            Minecraft.getInstance().setCameraEntity(target);
            Minecraft.getInstance().options.setCameraType(CameraType.FIRST_PERSON);
            Minecraft.getInstance().gameRenderer.setRenderHand(false); // TODO
        }
        if (shouldRelease) {
            Minecraft.getInstance().setCameraEntity(player);
            Minecraft.getInstance().options.setCameraType(ClientProxy.lastPOV);
            Minecraft.getInstance().gameRenderer.setRenderHand(true);
        }
    }

    public static void handleAnimationPacket(String animation, UUID playerUUID, boolean renderFirstPerson) {
        if (animation.equals("null")) {
            AnimationHandler.playAnimationClient(Minecraft.getInstance().level.getPlayerByUUID(playerUUID), null, renderFirstPerson);
        } else {
            AnimationHandler.playAnimationClient(Minecraft.getInstance().level.getPlayerByUUID(playerUUID), AnimationHandler.getKeyframeAnimation(animation), renderFirstPerson);
        }
    }

    public static void handleUpdateBossBarPacket(UUID bossID, ResourceLocation registryName) {
        if (registryName == null) {
            bossBarRegistryNames.remove(bossID);
        }
        else {
            bossBarRegistryNames.put(bossID, registryName);
        }
    }
}
