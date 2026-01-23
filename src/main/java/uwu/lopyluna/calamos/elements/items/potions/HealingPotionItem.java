package uwu.lopyluna.calamos.elements.items.potions;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import uwu.lopyluna.calamos.elements.ModEffects;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.ParametersAreNullableByDefault;

@ParametersAreNonnullByDefault
@ParametersAreNullableByDefault
public class HealingPotionItem extends SimplePotionItemWithCooldown {
    public final int healingAmount;

    public HealingPotionItem(int healingAmount, int cooldownDuration, int drinkingDuration, Properties pProperties) {
        super(MobEffects.HEAL, 0, 0, ModEffects.HEALING_SICKNESS, cooldownDuration, drinkingDuration, "Healing", pProperties);
        this.healingAmount = healingAmount;
    }

    public void applyCooldownPotionFunction(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        super.applyCooldownPotionFunction(pStack, pLevel, pEntityLiving);
        if (!pLevel.isClientSide) {
            pEntityLiving.heal(healingAmount);
        }
    }
}
