package uwu.lopyluna.calamos.core.entity.machina.goal;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;

public class MachinaWaterAvoidingRandomStrollGoal extends WaterAvoidingRandomStrollGoal {
    public MachinaWaterAvoidingRandomStrollGoal(PathfinderMob pMob, double pSpeedModifier) {
        super(pMob, pSpeedModifier);
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
