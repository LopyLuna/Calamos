package uwu.lopyluna.calamos.elements.effects;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.GameType;
import uwu.lopyluna.calamos.elements.ModEntity;
import uwu.lopyluna.calamos.elements.entity.machina.pestis_infection.PestisPlayerEntity;

import java.util.UUID;

public class MachinaPestisEffect extends MobEffect {
    public MachinaPestisEffect(int pColor) {
        super(MobEffectCategory.HARMFUL, pColor);
    }
    public void onEffectStarted(LivingEntity pLivingEntity, int pAmplifier) {
        if (!(pLivingEntity instanceof ServerPlayer pPlayer))
            return;
        ServerLevel level = (ServerLevel) pPlayer.level();
        BlockPos playerPos = pPlayer.blockPosition();
        UUID playerUUID = pPlayer.getUUID();
        GameType playerGameType = pPlayer.gameMode.getGameModeForPlayer();
        PestisPlayerEntity pestisPlayer = new PestisPlayerEntity(ModEntity.PESTIS_PLAYER.get(), level);
        PestisPlayerEntity.linkedPlayer = playerUUID;
        pestisPlayer.linkedPlayerGameType = playerGameType;
        pestisPlayer.setPos(playerPos.getX(), playerPos.getY(), playerPos.getZ());
        pestisPlayer.setHealth(pPlayer.getHealth());
        pestisPlayer.setItemInHand(pestisPlayer.getUsedItemHand(), pPlayer.getMainHandItem());
        pestisPlayer.setItemSlot(EquipmentSlot.OFFHAND, pPlayer.getOffhandItem());
        pestisPlayer.setItemSlot(EquipmentSlot.HEAD, pPlayer.getItemBySlot(EquipmentSlot.HEAD));
        pestisPlayer.setItemSlot(EquipmentSlot.CHEST, pPlayer.getItemBySlot(EquipmentSlot.CHEST));
        pestisPlayer.setItemSlot(EquipmentSlot.LEGS, pPlayer.getItemBySlot(EquipmentSlot.LEGS));
        pestisPlayer.setItemSlot(EquipmentSlot.FEET, pPlayer.getItemBySlot(EquipmentSlot.FEET));
        pestisPlayer.setCustomName(pPlayer.getDisplayName());
        pestisPlayer.setCustomNameVisible(true);
        level.addFreshEntity(pestisPlayer);
        pPlayer.setGameMode(GameType.SPECTATOR);
        pPlayer.setCamera(pestisPlayer);
    }
}
