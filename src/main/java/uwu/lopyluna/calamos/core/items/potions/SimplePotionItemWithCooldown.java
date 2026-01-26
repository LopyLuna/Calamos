package uwu.lopyluna.calamos.core.items.potions;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.ParametersAreNullableByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
@ParametersAreNullableByDefault
public class SimplePotionItemWithCooldown extends SimplePotionItem {
    public final Holder<MobEffect> cooldownEffect;
    public final int cooldownDuration;

    public SimplePotionItemWithCooldown(Holder<MobEffect> potionEffect, int potionPower, int potionDuration, Holder<MobEffect> cooldownEffect, int cooldownDuration, int drinkingDuration, String displayName, Properties pProperties) {
        super(potionEffect, potionPower, potionDuration, drinkingDuration, displayName, pProperties);
        this.cooldownEffect = cooldownEffect;
        this.cooldownDuration = cooldownDuration;
    }
    @Override
    @NotNull
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        if (!pEntityLiving.hasEffect(cooldownEffect)) {
            super.finishUsingItem(pStack, pLevel, pEntityLiving);
            applyCooldownPotionFunction(pStack, pLevel, pEntityLiving);
        }
        return pStack;
    }

    public void applyCooldownPotionFunction(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        if (!pLevel.isClientSide) {
            pEntityLiving.addEffect(new MobEffectInstance(cooldownEffect, cooldownDuration, 0));
        }
        if (pEntityLiving instanceof ServerPlayer pPlayer) {
            pPlayer.getCooldowns().addCooldown(this, cooldownDuration);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        String transTimer = Component.translatable("calamos.potion.tooltip.timer").getString();
        String transLevel = Component.translatable("calamos.potion.tooltip.level").getString();
        String transDrinkTime = Component.translatable("calamos.potion.tooltip.drink_time").getString();
        String transInstant = Component.translatable("calamos.potion.tooltip.instant").getString();
        String transSeconds = Component.translatable("calamos.potion.tooltip.seconds").getString();
        String transCooldown = Component.translatable("calamos.potion.tooltip.cooldown").getString();
        tooltipComponents.add(Component.nullToEmpty("§6" + displayName + (potionDuration + potionPower + cooldownDuration + drinkingDuration == 0 ? "" : "§9 - ")
                + (potionDuration == 0 ? "" : transTimer + (float)potionDuration / 20 + transSeconds) + (potionDuration + potionPower == 0 || potionDuration + cooldownDuration == 0 && drinkingDuration != 0 ? "" : " | ")
                + (potionPower == 0 ? "" : transLevel + (potionPower + 1)) + (potionPower + cooldownDuration == 0  && drinkingDuration != 0 ? "" : " | ")
                + (cooldownDuration == 0 ? "" : transCooldown + (float)cooldownDuration / 20 + transSeconds) + (cooldownDuration == 0 && drinkingDuration != 0 ? "" : " | ")
                + (drinkingDuration == 0 ? transInstant : transDrinkTime + (float)drinkingDuration / 20 + transSeconds)));
    }

    @Override
    @NotNull
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        if (!pPlayer.hasEffect(cooldownEffect)) {
            return ItemUtils.startUsingInstantly(pLevel, pPlayer, pHand);
        } else return InteractionResultHolder.pass(pPlayer.getItemInHand(pHand));
    }
}
