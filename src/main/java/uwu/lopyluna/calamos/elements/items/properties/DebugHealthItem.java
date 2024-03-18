package uwu.lopyluna.calamos.elements.items.properties;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class DebugHealthItem extends Item {
    public DebugHealthItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        super.finishUsingItem(pStack, pLevel, pEntityLiving);
        double MaxHealth1 = 100.0D;
        double MaxHealth2 = 200.0D;
        double MaxHealth3 = 400.0D;
        double MaxHealth4 = 600.0D;
        double MaxHealth5 = 800.0D;

        if (pEntityLiving instanceof ServerPlayer serverplayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, pStack);
            serverplayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (pEntityLiving instanceof Player player && !player.isCrouching()) {
            AttributeInstance inst = player.getAttribute(Attributes.MAX_HEALTH);

            if (player.getMaxHealth() == MaxHealth5) {
                if (inst != null)
                    inst.setBaseValue(MaxHealth1);
                player.setHealth((float) MaxHealth1);

            } else if (player.getMaxHealth() == MaxHealth4) {
                if (inst != null)
                    inst.setBaseValue(MaxHealth5);
                player.setHealth((float) MaxHealth5);

            } else if (player.getMaxHealth() == MaxHealth3) {
                if (inst != null)
                    inst.setBaseValue(MaxHealth4);
                player.setHealth((float) MaxHealth4);

            } else if (player.getMaxHealth() == MaxHealth2) {
                if (inst != null)
                    inst.setBaseValue(MaxHealth3);
                player.setHealth((float) MaxHealth3);

            } else if (player.getMaxHealth() == MaxHealth1) {
                if (inst != null)
                    inst.setBaseValue(MaxHealth2);
                player.setHealth((float) MaxHealth2);

            } else {
                if (inst != null)
                    inst.setBaseValue(MaxHealth1);
                player.setHealth((float) MaxHealth1);
            }
            pLevel.playSound(null, player.blockPosition(), NoteBlockInstrument.BELL.getSoundEvent().value(), SoundSource.MASTER, 0.65F, 0.8F);
            pLevel.playSound(null, player.blockPosition(), SoundEvents.CONDUIT_AMBIENT_SHORT, SoundSource.MASTER, 0.25F, 0.5F);
        } else if (pEntityLiving instanceof Player player && player.isCrouching()) {
            AttributeInstance inst = player.getAttribute(Attributes.MAX_HEALTH);

            if (player.getMaxHealth() == MaxHealth5) {
                if (inst != null)
                    inst.setBaseValue(MaxHealth4);
                player.setHealth((float) MaxHealth4);

            } else if (player.getMaxHealth() == MaxHealth4) {
                if (inst != null)
                    inst.setBaseValue(MaxHealth3);
                player.setHealth((float) MaxHealth3);

            } else if (player.getMaxHealth() == MaxHealth3) {
                if (inst != null)
                    inst.setBaseValue(MaxHealth2);
                player.setHealth((float) MaxHealth2);

            } else if (player.getMaxHealth() == MaxHealth2) {
                if (inst != null)
                    inst.setBaseValue(MaxHealth1);
                player.setHealth((float) MaxHealth1);

            } else if (player.getMaxHealth() == MaxHealth1) {
                if (inst != null)
                    inst.setBaseValue(MaxHealth5);
                player.setHealth((float) MaxHealth5);

            } else {
                if (inst != null)
                    inst.setBaseValue(MaxHealth1);
                player.setHealth((float) MaxHealth1);
            }
            pLevel.playSound(null, player.blockPosition(), NoteBlockInstrument.BELL.getSoundEvent().value(), SoundSource.MASTER, 0.65F, 0.6F);
            pLevel.playSound(null, player.blockPosition(), SoundEvents.CONDUIT_AMBIENT_SHORT, SoundSource.MASTER, 0.25F, 0.5F);

        }
        return pStack;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 5;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.CROSSBOW;
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return SoundEvents.CONDUIT_AMBIENT_SHORT;
    }

    @Override
    public SoundEvent getEatingSound() {
        return SoundEvents.CONDUIT_AMBIENT_SHORT;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pHand);
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }
}
