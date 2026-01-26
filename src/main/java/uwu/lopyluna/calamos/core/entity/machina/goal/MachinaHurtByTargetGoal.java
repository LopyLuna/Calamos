package uwu.lopyluna.calamos.core.entity.machina.goal;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;

public class MachinaHurtByTargetGoal extends HurtByTargetGoal {
    public MachinaHurtByTargetGoal(PathfinderMob pMob, Class<?>... pToIgnoreDamage) {
        super(pMob, pToIgnoreDamage);
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
