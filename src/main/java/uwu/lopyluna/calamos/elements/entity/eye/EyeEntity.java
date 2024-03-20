package uwu.lopyluna.calamos.elements.entity.eye;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import uwu.lopyluna.calamos.elements.ModSoundEvents;
import uwu.lopyluna.calamos.elements.entity.entity_definitions.Boss;

import java.util.EnumSet;

public class EyeEntity extends Monster implements Boss, RangedAttackMob {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public EyeEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        //super(pEntityType, pLevel, BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS);
        super(pEntityType, pLevel);
        this.moveControl = new EyeEntity.EyeMoveControl(this);
        this.xpReward = 3;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(4, new EyeAttackGoal());
        this.goalSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, Player.class, false));
        this.goalSelector.addGoal(9, new EyeLookAtPlayerGoal(this, Player.class, 128.0F));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, Raider.class).setAlertOthers());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 485.0F) //485000.0F main health
                .add(Attributes.MOVEMENT_SPEED, 0.5F)
                .add(Attributes.FOLLOW_RANGE, 128.0D)
                .add(Attributes.ATTACK_DAMAGE, 50.0D);
    }

    @Override
    public void move(@NotNull MoverType pType, @NotNull Vec3 pPos) {
        super.move(pType, pPos);
        this.checkInsideBlocks();
    }

    @Override
    public void tick() {
        if (this.level().isClientSide()) {
            setupAnimationStates();
        }
        this.noPhysics = true;
        super.tick();
        this.noPhysics = false;
        this.setNoGravity(true);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSoundEvents.WILDFIRE_IDLE.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSoundEvents.WILDFIRE_DEATH.get();
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return ModSoundEvents.WILDFIRE_HURT.get();
    }


    @Override
    public void performRangedAttack(@NotNull LivingEntity pTarget, float pVelocity) {

    }


    class EyeMoveControl extends MoveControl {
        public EyeMoveControl(EyeEntity eye) {
            super(eye);
        }

        @Override
        public void tick() {
            if (this.operation == MoveControl.Operation.MOVE_TO) {
                Vec3 vec3 = new Vec3(this.wantedX - EyeEntity.this.getX(), this.wantedY - EyeEntity.this.getY(), this.wantedZ - EyeEntity.this.getZ());
                double d0 = vec3.length();
                if (d0 < EyeEntity.this.getBoundingBox().getSize()) {
                    this.operation = MoveControl.Operation.WAIT;
                    EyeEntity.this.setDeltaMovement(EyeEntity.this.getDeltaMovement().scale(0.5));
                } else {
                    EyeEntity.this.setDeltaMovement(EyeEntity.this.getDeltaMovement().add(vec3.scale(this.speedModifier * 0.05 / d0)));
                    if (EyeEntity.this.getTarget() == null) {
                        Vec3 vec31 = EyeEntity.this.getDeltaMovement();
                        EyeEntity.this.setYRot(-((float) Mth.atan2(vec31.x, vec31.z)) * (180.0F / (float)Math.PI));
                        EyeEntity.this.yBodyRot = EyeEntity.this.getYRot();
                    } else {
                        double d2 = EyeEntity.this.getTarget().getX() - EyeEntity.this.getX();
                        double d1 = EyeEntity.this.getTarget().getZ() - EyeEntity.this.getZ();
                        EyeEntity.this.setYRot(-((float)Mth.atan2(d2, d1)) * (180.0F / (float)Math.PI));
                        EyeEntity.this.yBodyRot = EyeEntity.this.getYRot();
                    }
                }
            }
        }
    }

    static class EyeLookAtPlayerGoal extends LookAtPlayerGoal {

        public EyeLookAtPlayerGoal(Mob pMob, Class<? extends LivingEntity> pLookAtType, float pLookDistance) {
            super(pMob, pLookAtType, pLookDistance);
        }

        @Override
        public boolean canUse() {
            if (this.mob.getTarget() != null) {
                this.lookAt = this.mob.getTarget();
            }
            if (this.lookAtType == Player.class) {
                this.lookAt = this.mob.level().getNearestPlayer(this.lookAtContext, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
            } else {
                this.lookAt = this.mob
                        .level()
                        .getNearestEntity(
                                this.mob
                                        .level()
                                        .getEntitiesOfClass(
                                                this.lookAtType,
                                                this.mob.getBoundingBox().inflate((double)this.lookDistance, 3.0, (double)this.lookDistance),
                                                p_148124_ -> true
                                        ),
                                this.lookAtContext,
                                this.mob,
                                this.mob.getX(),
                                this.mob.getEyeY(),
                                this.mob.getZ()
                        );
            }
            return this.lookAt != null;
        }

        @Override
        public boolean canContinueToUse() {
            assert this.lookAt != null;
            if (!this.lookAt.isAlive()) {
                return false;
            }
            else return !(this.mob.distanceToSqr(this.lookAt) > (double) (this.lookDistance * this.lookDistance));
        }

        @Override
        public void tick() {
            if (this.lookAt.isAlive()) {
                this.mob.getLookControl().setLookAt(this.lookAt.getX(), this.lookAt.getEyeY(), this.lookAt.getZ());
            }
        }
    }

    class EyeAttackGoal extends Goal {
        public EyeAttackGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this method as well.
         */
        @Override
        public boolean canUse() {
            LivingEntity livingentity = EyeEntity.this.getTarget();
            if (livingentity != null && livingentity.isAlive() && !EyeEntity.this.getMoveControl().hasWanted() && EyeEntity.this.random.nextInt(reducedTickDelay(7)) == 0) {
                return EyeEntity.this.distanceToSqr(livingentity) > 4.0;
            } else {
                return false;
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        @Override
        public boolean canContinueToUse() {
            return EyeEntity.this.getMoveControl().hasWanted() && EyeEntity.this.getTarget() != null && EyeEntity.this.getTarget().isAlive();
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        @Override
        public void start() {
            LivingEntity livingentity = EyeEntity.this.getTarget();
            if (livingentity != null) {
                Vec3 vec3 = livingentity.getEyePosition();
                EyeEntity.this.moveControl.setWantedPosition(vec3.x, vec3.y, vec3.z, 1.0);
            }
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        @Override
        public void tick() {
            LivingEntity livingentity = EyeEntity.this.getTarget();
            if (livingentity != null) {
                if (EyeEntity.this.getBoundingBox().intersects(livingentity.getBoundingBox())) {
                    EyeEntity.this.doHurtTarget(livingentity);
                } else {
                    double d0 = EyeEntity.this.distanceToSqr(livingentity);
                    if (d0 < 9.0) {
                        Vec3 vec3 = livingentity.getEyePosition();
                        EyeEntity.this.moveControl.setWantedPosition(vec3.x, vec3.y, vec3.z, 1.0);
                    }
                }
            }
        }
    }
}
