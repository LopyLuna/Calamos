package uwu.lopyluna.calamos.elements.entity.boone;

import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class BooneGroundSmashGoal extends Goal {
    private final BooneEntity boone;
    private final AnimationState groundSmashAnimationState;

    public BooneGroundSmashGoal(BooneEntity boone, AnimationState groundSmashAnimationState) {
        this.boone = boone;
        this.groundSmashAnimationState = groundSmashAnimationState;
    }

    @Override
    public boolean canUse() {
        List<Player> toAttack = this.boone.entitiesForGroundSmash();
        return !toAttack.isEmpty();
    }

    @Override
    public void start() {
        super.start();
        if (this.groundSmashAnimationState != null) {
            groundSmashAnimationState.start(boone.tickCount);
        }
        //knockback all entities in the area away from the boone
        List<Player> toAttack = this.boone.entitiesForGroundSmash();
        for (Player entity : toAttack) {
            entity.knockback(2.5F, entity.getX() - boone.getX(), entity.getZ() - boone.getZ());
            //launch the entity into the air a bit
            entity.setDeltaMovement(entity.getDeltaMovement().add(0, 1.5, 0));
        }
    }

    @Override
    public void stop() {
        if (this.groundSmashAnimationState != null) {
            groundSmashAnimationState.stop();
        }
        super.stop();
    }

    @Override
    public void tick() {
        super.tick();
    }
}
