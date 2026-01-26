package uwu.lopyluna.calamos.core.entity.machina.goal;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal;

import java.util.function.BooleanSupplier;

public class MachinaMoveThroughVillageGoal extends MoveThroughVillageGoal {
    public MachinaMoveThroughVillageGoal(PathfinderMob pMob, double pSpeedModifier, boolean pOnlyAtNight, int pDistanceToPoi, BooleanSupplier pCanDealWithDoors) {
        super(pMob, pSpeedModifier, pOnlyAtNight, pDistanceToPoi, pCanDealWithDoors);
    }
    @Override
    public boolean canUse() {
        boolean isActive = mob.getPersistentData().getBoolean("inRangeOfAntenna") || mob.getPersistentData().getBoolean("inRangeOfMainframe");
        if (!isActive) {
            return false;
        } else
            return super.canUse();
    }
    @Override
    public boolean canContinueToUse() {
        boolean isActive = mob.getPersistentData().getBoolean("inRangeOfAntenna") || mob.getPersistentData().getBoolean("inRangeOfMainframe");
        if (!isActive) {
            return false;
        } else
            return super.canContinueToUse();
    }
}
