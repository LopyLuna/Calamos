package uwu.lopyluna.calamos.core.entity.machina;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import uwu.lopyluna.calamos.core.entity.machina.pestis_infection.PestisPlayerEntity;
import uwu.lopyluna.calamos.elements.ModAttachmentTypes;
import uwu.lopyluna.calamos.elements.ModEffects;
import uwu.lopyluna.calamos.networking.packets.S2C.PestisCameraPacket;

import java.util.UUID;

public class MachinaPestisHandler {

    public static void playerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        MachinaPestisData pestisData = player.getData(ModAttachmentTypes.MACHINA_PESTIS);
        if (pestisData.isLinked() && player.hasEffect(ModEffects.PESTIS)) {
            ServerLevel level = (ServerLevel) player.level;
            Entity pestisPlayer = level.getEntity(pestisData.getLinkedClone().get());
            if (pestisPlayer != null && player instanceof ServerPlayer serverPlayer) {
                PacketDistributor.sendToPlayer(serverPlayer, new PestisCameraPacket(player.getId(), pestisPlayer.getId(), false));
            }
        }
    }

    public static void playerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        MachinaPestisData pestisData = player.getData(ModAttachmentTypes.MACHINA_PESTIS);
        if (pestisData.isLinked() && !player.hasEffect(ModEffects.PESTIS)) {
            UUID pestisUUID = pestisData.getLinkedClone().get();
            MinecraftServer server = player.getServer();
            pestisData.unlink();
            player.setData(ModAttachmentTypes.MACHINA_PESTIS, pestisData);
            if (server != null) {
                ServerLevel level = server.getLevel(player.level.dimension());
                Entity pestisPlayer = level.getEntity(pestisUUID);
                if (pestisPlayer != null) {
                    pestisPlayer.kill();
                }
            }
        }
    }

    public static void entityDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof PestisPlayerEntity pestisPlayer) {
            UUID linkedPlayer = PestisPlayerEntity.linkedPlayer;
            if (linkedPlayer != null) {
                ServerPlayer player = (ServerPlayer) event.getEntity().level().getPlayerByUUID(linkedPlayer);
                if (player != null) {
                    player.setGameMode(pestisPlayer.linkedPlayerGameType);
                    player.teleportTo(pestisPlayer.getX(), pestisPlayer.getY(), pestisPlayer.getZ());
                    player.setYRot(pestisPlayer.getYRot());
                    player.setXRot(pestisPlayer.getXRot());
                    PacketDistributor.sendToPlayer(player, new PestisCameraPacket(player.getId(), pestisPlayer.getId(), true));
                }
            }
        }
    }
}
