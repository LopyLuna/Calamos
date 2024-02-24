package uwu.lopyluna.calamos.elements.items.potions;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.ParametersAreNullableByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
@ParametersAreNullableByDefault
public class SimplePotionItem extends Item {
    public final int drinkingDuration;
    public final MobEffect potionEffect;
    public final int potionDuration;
    public final int potionPower;
    public final String displayName;

    public SimplePotionItem(MobEffect potionEffect, int potionPower, int potionDuration, int drinkingDuration, String displayName, Properties pProperties) {
        super(pProperties.stacksTo(16));
        this.drinkingDuration = drinkingDuration;
        this.potionEffect = potionEffect;
        this.potionDuration = potionDuration;
        this.potionPower = potionPower;
        this.displayName = displayName;
    }

    @Override
    @NotNull
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        super.finishUsingItem(pStack, pLevel, pEntityLiving);
        applyPotionFunction(pStack, pLevel, pEntityLiving);
        return pStack;
    }

    public void applyPotionFunction(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        if (!pLevel.isClientSide) {
            if (pEntityLiving instanceof ServerPlayer serverplayer) {
                CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, pStack);
                serverplayer.awardStat(Stats.ITEM_USED.get(this));
                pEntityLiving.gameEvent(GameEvent.DRINK);
                if (!serverplayer.getAbilities().instabuild) {
                    pStack.shrink(1);
                }
            }
        }
        if (!pLevel.isClientSide) {
            pEntityLiving.addEffect(new MobEffectInstance(potionEffect, potionDuration, potionPower));
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(Component.nullToEmpty("§6" + displayName + (potionDuration + potionPower == 0 && drinkingDuration != 0 ? "" : "§9 - ")
                + (potionDuration == 0 ? "" : "§9Timer: §6" + potionDuration / 20 + "§9 (Sec)") + (potionDuration + potionPower == 0 && drinkingDuration != 0 ? "" : " | ")
                + (potionPower == 0 ? "" : "§9Level: §6" + (potionPower + 1)) + (potionPower == 0 && drinkingDuration != 0 ? "" : " | ")
                + (drinkingDuration == 0 ? "§6'Instant'" : "")));
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return drinkingDuration;
    }

    @Override
    @NotNull
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.DRINK;
    }

    @Override
    @NotNull
    public SoundEvent getDrinkingSound() {
        return SoundEvents.WITCH_DRINK;
    }

    @Override
    @NotNull
    public SoundEvent getEatingSound() {
        return SoundEvents.WITCH_DRINK;
    }

    @Override
    @NotNull
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pHand);
    }
}
