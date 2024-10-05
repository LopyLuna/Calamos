package uwu.lopyluna.calamos.elements.items.potions;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import uwu.lopyluna.calamos.elements.ModEffects;

import java.util.Optional;

public class RecallPotionItem extends SimplePotionItemWithCooldown {
    public RecallPotionItem(int cooldownDuration, int drinkingDuration, Properties pProperties) {
        super(MobEffects.CONFUSION, 0, 0, ModEffects.HEALING_SICKNESS.get(), cooldownDuration, drinkingDuration, "Recall", pProperties);
    }

    public void applyCooldownPotionFunction(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        super.applyCooldownPotionFunction(pStack, pLevel, pEntityLiving);
        if (pEntityLiving instanceof ServerPlayer player) {
            ServerLevel serverWorld = (ServerLevel) pLevel;
            if (!pLevel.isClientSide) {
                BlockPos respawnPoint = player.getRespawnPosition();
                if (respawnPoint != null) {
                    Optional<Vec3> respawnPosition = Player.findRespawnPositionAndUseSpawnBlock(serverWorld, respawnPoint, player.getYRot(), player.isRespawnForced(), false);
                    if (respawnPosition.isPresent()) {
                        ServerLevel respawnWorld = serverWorld.getServer().getLevel(player.getRespawnDimension());

                        if (respawnWorld != null) {
                            player.teleportTo(
                                    respawnWorld,
                                    respawnPosition.get().x,
                                    respawnPosition.get().y,
                                    respawnPosition.get().z,
                                    player.getYRot(),
                                    player.getXRot());
                        }
                    }
                }
            }
        }
    }
}
