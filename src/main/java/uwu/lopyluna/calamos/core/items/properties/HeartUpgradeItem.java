package uwu.lopyluna.calamos.core.items.properties;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import uwu.lopyluna.calamos.core.systems.health.HeartData;
import uwu.lopyluna.calamos.core.systems.health.HeartType;
import uwu.lopyluna.calamos.elements.ModAttachmentTypes;

public class HeartUpgradeItem extends Item {
    private final HeartType upgradeType;

    public HeartUpgradeItem(Properties properties, HeartType upgradeType) {
        super(properties);
        this.upgradeType = upgradeType;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            HeartData heartData = player.getData(ModAttachmentTypes.HEARTS);

            if (!heartData.canUpgrade(upgradeType)) {
                player.displayClientMessage(
                        Component.literal(getErrorMessage(heartData)),
                        true
                );
                return InteractionResultHolder.fail(stack);
            }

            if (heartData.tryUpgrade(upgradeType)) {
                int oldValue = getOldHeartValue(upgradeType);
                int newValue = HeartData.getHeartValue(upgradeType);
                int healthIncrease = newValue - oldValue;

                float newMaxHealth = heartData.calculateMaxHealth();
                player.getAttribute(Attributes.MAX_HEALTH)
                        .setBaseValue(newMaxHealth);

                player.heal(healthIncrease);

                level.playSound(null, player.blockPosition(),
                        SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 0.7F, 1.0F);

                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }

                return InteractionResultHolder.success(stack);
            }
        }

        return InteractionResultHolder.pass(stack);
    }

    private int getOldHeartValue(HeartType newType) {
        return switch (newType) {
            case GOLDEN -> 20;
            case ENLIGHTENED -> 30;
            case STELLATECH -> 40;
            default -> 0;
        };
    }

    private String getErrorMessage(HeartData heartData) {
        return switch (upgradeType) {
            case GOLDEN -> "§cNo default hearts left to upgrade!";
            case ENLIGHTENED -> heartData.getGoldenHearts() == 0 ?
                    "§cNo golden hearts left to upgrade!" : "§cUpgrade all default hearts first!";
            case STELLATECH -> heartData.getEnlightenedHearts() == 0 ?
                    "§cNo enlightened hearts left to upgrade!" : "§cUpgrade all golden hearts first!";
            default -> "§cCannot upgrade!";
        };
    }
}
