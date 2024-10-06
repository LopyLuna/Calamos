package uwu.lopyluna.calamos.elements.items.properties;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import uwu.lopyluna.calamos.datagen.ModTags;
import uwu.lopyluna.calamos.elements.entity.dynamite.DynamiteEntity;

public class DynamiteItem extends Item {
    public DynamiteItem(Properties pProperties) {
        super(pProperties);
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        ItemStack oppositeHandStack = pPlayer.getItemInHand(getOppositeHand(pHand));
        CompoundTag compoundtag = itemstack.getTag();
        pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.FISHING_BOBBER_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
        pPlayer.getCooldowns().addCooldown(this, 20);
        if (!pLevel.isClientSide) {
            DynamiteEntity dynamite = new DynamiteEntity(pLevel, pPlayer.getX(), pPlayer.getEyeY() - 0.10000000149011612, pPlayer.getZ());
            if (compoundtag != null && compoundtag.contains("ExplosionPower", 99)) {
                dynamite.setExplosionPower(compoundtag.getFloat("ExplosionPower"));
            }
            if (oppositeHandStack.is(ModTags.mcItemTag("creeper_igniters"))) {
                dynamite.setHasFuse(true);
            }
            dynamite.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 1.0F);
            pLevel.addFreshEntity(dynamite);
        }

        pPlayer.awardStat(Stats.ITEM_USED.get(this));
        if (!pPlayer.getAbilities().instabuild) {
            itemstack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }

    public InteractionHand getOppositeHand(InteractionHand pHand) {
        return pHand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
    }
}
