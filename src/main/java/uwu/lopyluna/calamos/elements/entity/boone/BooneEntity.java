package uwu.lopyluna.calamos.elements.entity.boone;

import net.minecraft.client.model.WardenModel;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import org.w3c.dom.Attr;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.elements.entity.dynamite.DynamiteEntity;
import uwu.lopyluna.calamos.elements.entity.entity_definitions.Boss;
import uwu.lopyluna.calamos.elements.entity.entity_definitions.BossBarMonster;
import uwu.lopyluna.calamos.utilities.CalamosBossEvent;

import javax.annotation.Nullable;
import javax.swing.text.StyledEditorKit;
import java.util.EnumSet;
import java.util.List;

public class BooneEntity extends BossBarMonster implements RangedAttackMob {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState groundSmashAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public BooneEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 1.0F));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(5, new RangedAttackGoal(this, 1.0D, 2, 32));
        //this.goalSelector.addGoal(3, new BooneGroundSmashGoal(this, this.groundSmashAnimationState));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 250.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.2F)
                .add(Attributes.FOLLOW_RANGE, 32.0D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            setupAnimationStates();
        }
        //do ground smash but not every tick
        if (this.tickCount % 20 == 0) {
            if (!entitiesForGroundSmash().isEmpty()) {
                //do with a 25% chance
                if (this.random.nextInt(4) == 0) {
                    groundSmashAnimationState.start(tickCount);
                    for (Player player : entitiesForGroundSmash()) {
                        player.knockback(0.5F, player.getX() - this.getX(), player.getZ() - this.getZ());
                        //launch the entity into the air a bit
                        player.setDeltaMovement(player.getDeltaMovement().add(0, 0.75, 0));
                        player.hurt(this.damageSources().mobAttack(this), (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE));
                    }
                    clientDiggingParticles();
                }
            }
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

    public boolean ignoreExplosion(Explosion pExplosion) {
        return pExplosion.getIndirectSourceEntity() == this;
    }

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pVelocity) {
        DynamiteEntity dynamite = new DynamiteEntity(this.level(), this.getX(), this.getY(), this.getZ());
        dynamite.setOwner(this);
        double d0 = pTarget.getX() - this.getX();
        double d1 = pTarget.getY(0.3333333333333333);
        double d2 = pTarget.getZ() - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        dynamite.setHasFuse(true);
        dynamite.setFuse(160);
        dynamite.setFromBoone(true);
        dynamite.shoot(d0, d1 + d3 * 0.2F, d2, 1.6F, (float)(14 - this.level().getDifficulty().getId() * 4));
        this.level().addFreshEntity(dynamite);
    }

    public List<Player> entitiesForGroundSmash() {
        return this.level().getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(6.0D, 3.25D, 6.0D));
    }

    private void clientDiggingParticles() {
        RandomSource randomsource = this.getRandom();
        BlockState blockstate = this.getBlockStateOn();
        if (blockstate.getRenderShape() != RenderShape.INVISIBLE) {
            for(int i = 0; i < 30; ++i) {
                double d0 = this.getX() + (double) Mth.randomBetween(randomsource, -0.7F, 0.7F);
                double d1 = this.getY();
                double d2 = this.getZ() + (double)Mth.randomBetween(randomsource, -0.7F, 0.7F);
                this.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, blockstate), d0, d1, d2, 0.0, 0.3, 0.0);
            }
        }
    }
}
