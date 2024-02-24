package uwu.lopyluna.calamos.elements.items.potions;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
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
public class SimplePotionItemWithCooldown extends SimplePotionItem {
    public final MobEffect cooldownEffect;
    public final int cooldownDuration;

    public SimplePotionItemWithCooldown(MobEffect potionEffect, int potionPower, int potionDuration, MobEffect cooldownEffect, int cooldownDuration, int drinkingDuration, String displayName, Properties pProperties) {
        super(potionEffect, potionPower, potionDuration, drinkingDuration, displayName, pProperties);
        this.cooldownEffect = cooldownEffect;
        this.cooldownDuration = cooldownDuration;
    }

    public @NotNull ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        super.finishUsingItem(pStack, pLevel, pEntityLiving);
        if (!pLevel.isClientSide) {
            pEntityLiving.addEffect(new MobEffectInstance(cooldownEffect, cooldownDuration, 0));
        }
        if (pEntityLiving instanceof ServerPlayer pPlayer) {
            pPlayer.getCooldowns().addCooldown(this, cooldownDuration);
        }
        return pStack;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(Component.nullToEmpty("§6" + displayName + (potionDuration + potionPower + cooldownDuration + drinkingDuration == 0 ? "" : "§9 - ")
                + (potionDuration == 0 ? "" : "§9Timer: §6" + (float)potionDuration / 20 + "§9 (Sec)") + (potionDuration + potionPower == 0 || potionDuration + cooldownDuration == 0 && drinkingDuration != 0 ? "" : " | ")
                + (potionPower == 0 ? "" : "§9Level: §6" + (potionPower + 1)) + (potionPower + cooldownDuration == 0  && drinkingDuration != 0 ? "" : " | ")
                + (cooldownDuration == 0 ? "" : "§9Cooldown: §6" + (float)cooldownDuration / 20 + "§9 (Sec)") + (cooldownDuration == 0 && drinkingDuration != 0 ? "" : " | ")
                + (drinkingDuration == 0 ? "§6'Instant'" : "")));
    }
}
