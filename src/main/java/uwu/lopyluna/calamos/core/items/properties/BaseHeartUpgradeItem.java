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
import uwu.lopyluna.calamos.elements.ModAttachmentTypes;

public class BaseHeartUpgradeItem extends Item {

    public BaseHeartUpgradeItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            HeartData heartData = player.getData(ModAttachmentTypes.HEARTS);

            if (!heartData.canAddDefaultHeart()) {
                player.displayClientMessage(
                        Component.literal("§cMaximum hearts reached!"),
                        true
                );
                return InteractionResultHolder.fail(stack);
            }

            if (heartData.tryAddDefaultHeart()) {
                float newMaxHealth = heartData.calculateMaxHealth();
                player.getAttribute(Attributes.MAX_HEALTH)
                        .setBaseValue(newMaxHealth);

                player.heal(20);

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
}
