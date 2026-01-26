package uwu.lopyluna.calamos.core.entity.machina.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;

public class MachinaLookAtPlayerGoal extends LookAtPlayerGoal {
    public MachinaLookAtPlayerGoal(Mob pMob, Class<? extends LivingEntity> pLookAtType, float pLookDistance) {
        super(pMob, pLookAtType, pLookDistance);
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
