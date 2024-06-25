package uwu.lopyluna.calamos.elements.entity.machina.goal;

import net.minecraft.world.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.world.entity.monster.Zombie;

public class MachinaZombieAttackGoal extends ZombieAttackGoal {
    public MachinaZombieAttackGoal(Zombie pZombie, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pZombie, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
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
