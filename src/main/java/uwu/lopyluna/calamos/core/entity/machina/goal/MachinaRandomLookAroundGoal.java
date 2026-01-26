package uwu.lopyluna.calamos.core.entity.machina.goal;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class MachinaRandomLookAroundGoal extends Goal {
    private final Mob mob;
    private double relX;
    private double relZ;
    private int lookTime;
    
    public MachinaRandomLookAroundGoal(Mob pMob) {
        this.mob = pMob;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }
    @Override
    public boolean canUse() {
        boolean isActive = mob.getPersistentData().getBoolean("inRangeOfAntenna") || mob.getPersistentData().getBoolean("inRangeOfMainframe");
        if (!isActive) {
            return false;
        } else
            return this.mob.getRandom().nextFloat() < 0.02F;
    }
    
    public void start() {
        double d0 = 6.283185307179586 * this.mob.getRandom().nextDouble();
        this.relX = Math.cos(d0);
        this.relZ = Math.sin(d0);
        this.lookTime = 20 + this.mob.getRandom().nextInt(20);
    }
    
    public boolean requiresUpdateEveryTick() {
        return true;
    }
    
    public void tick() {
        --this.lookTime;
        this.mob.getLookControl().setLookAt(this.mob.getX() + this.relX, this.mob.getEyeY(), this.mob.getZ() + this.relZ);
    }
    public boolean canContinueToUse() {
        boolean isActive = mob.getPersistentData().getBoolean("inRangeOfAntenna") || mob.getPersistentData().getBoolean("inRangeOfMainframe");
        if (!isActive) {
            return false;
        } else
            return this.lookTime >= 0;
    }
}
