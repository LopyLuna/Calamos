package uwu.lopyluna.calamos.elements.entity.boone;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import uwu.lopyluna.calamos.elements.entity.dynamite.DynamiteEntity;
import uwu.lopyluna.calamos.elements.entity.entity_definitions.Boss;
import uwu.lopyluna.calamos.elements.entity.entity_definitions.BossBarMonster;
import uwu.lopyluna.calamos.utilities.CalamosBossEvent;

import javax.annotation.Nullable;
import javax.swing.text.StyledEditorKit;
import java.util.EnumSet;

public class BooneEntity extends BossBarMonster implements Boss {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public BooneEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0F));
        this.goalSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(7, new BooneDynamiteGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 175.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.2F)
                .add(Attributes.FOLLOW_RANGE, 32.0D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    static class BooneDynamiteGoal extends Goal {
        private final BooneEntity boone;
        private int attackStep;
        private int attackTime;
        private int lastSeen;

        public BooneDynamiteGoal(BooneEntity pBoone) {
            this.boone = pBoone;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            LivingEntity target = this.boone.getTarget();
            return target != null && target.isAlive() && this.boone.canAttack(target);
        }

        @Override
        public void start() {
            Level projectileLevel = this.boone.level();
            LivingEntity target = this.boone.getTarget();

            DynamiteEntity dynamite = new DynamiteEntity(projectileLevel);

            dynamite.setOwner(this.boone);
            dynamite.shoot(target.getX(), target.getY(), target.getZ(), 2.0F, 0.0F);

            super.start();
        }

        @Override
        public void tick() {
            Level projectileLevel = this.boone.level();
            LivingEntity target = this.boone.getTarget();

            DynamiteEntity dynamite = new DynamiteEntity(projectileLevel);

            dynamite.setOwner(this.boone);
            dynamite.shoot(target.getX(), target.getY(), target.getZ(), 2.0F, 0.0F);

            super.tick();
        }
    }
}
