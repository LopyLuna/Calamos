package uwu.lopyluna.calamos.core.entity.wildfire;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.level.BlockGetter;

import javax.annotation.Nullable;

public class WildfirePanicGoal extends PanicGoal {
    public static final float PROBABILITY = 0.001F;
    protected final float probability;
    private final WildfireEntity wildfire;

    public WildfirePanicGoal(WildfireEntity pWildfire) {
        super(pWildfire, 2.5);
        this.wildfire = pWildfire;
        this.probability = 0.0F;
    }

    @Override
    public boolean canUse() {
        if (!this.shouldPanic()) {
            return false;
        } else {
            if (wildfire.getHealth() <= wildfire.getMaxHealth() * 0.75) {
                BlockPos blockpos = this.lookForLava(this.mob.level(), this.mob, 5);
                if (blockpos != null) {
                    this.posX = (double)blockpos.getX();
                    this.posY = (double)blockpos.getY();
                    this.posZ = (double)blockpos.getZ();
                    return true;
                }
            }

            return this.findRandomPosition();
        }
    }

    @Override
    protected boolean shouldPanic() {
        return this.mob.isInWater() ||
               this.mob.isFreezing() ||
               this.mob.isInPowderSnow ||
               !this.wildfire.hasActiveShields() ||
               wildfire.getHealth() <= wildfire.getMaxHealth() * 0.25 ||
               (wildfire.getHealth() <= wildfire.getMaxHealth() * 0.5 && this.wildfire.hasActiveShields());
    }


    @Nullable
    protected BlockPos lookForLava(BlockGetter pLevel, Entity pEntity, int pRange) {
        BlockPos blockpos = pEntity.blockPosition();
        return !pLevel.getBlockState(blockpos).getCollisionShape(pLevel, blockpos).isEmpty()
                ? null
                : BlockPos.findClosestMatch(pEntity.blockPosition(), pRange, 1, p_196649_ -> pLevel.getFluidState(p_196649_).is(FluidTags.LAVA))
                .orElse(null);
    }
}
