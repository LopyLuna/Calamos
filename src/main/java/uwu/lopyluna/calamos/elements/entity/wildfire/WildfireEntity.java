package uwu.lopyluna.calamos.elements.entity.wildfire;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import uwu.lopyluna.calamos.elements.ModSoundEvents;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.EnumSet;
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@SuppressWarnings("deprecation")
public class WildfireEntity extends Monster {
    private float damageAmountCounter = 0.0F;
    private float allowedHeightOffset = 0.5F;
    private int nextHeightOffsetChangeTick;
    public static final int defaultActiveShieldsCount = 4;
    public static final int defaultTicksUntilShieldRegen = 300;
    public static final int defaultSummonedBlazesCount = 0;
    public static final int maxSummonedBlazesCount = 2;
    private static final String activeShields_NBT = "ActiveShieldsCount";
    private static final String ticksUntilShieldRegen_NBT = "TicksUntilShieldRegen";
    private static final String summonedBlazesCount_NBT = "SummonedBlazesCount";
    private static final EntityDataAccessor<Integer> ACTIVE_SHIELDS_COUNT = SynchedEntityData.defineId(WildfireEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TICKS_UNTIL_SHIELD_REGENERATION = SynchedEntityData.defineId(WildfireEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SUMMONED_BLAZES_COUNT = SynchedEntityData.defineId(WildfireEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(WildfireEntity.class, EntityDataSerializers.BYTE);
    public WildfireEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.LAVA, 8.0F);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 0.0F);
        this.xpReward = 30;
    }
    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        this.setActiveShieldsCount(defaultActiveShieldsCount);
        this.setSummonedBlazesCount(defaultSummonedBlazesCount);
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(4, new WildfireAttackGoal(this));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0, 0.0F));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }
    
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.ATTACK_DAMAGE, 8.0).add(Attributes.MOVEMENT_SPEED, 0.23F).add(Attributes.FOLLOW_RANGE, 64.0).add(Attributes.MAX_HEALTH, 80.0);
    }
    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setActiveShieldsCount(tag.getInt(activeShields_NBT));
        this.setTicksUntilShieldRegeneration(tag.getInt(ticksUntilShieldRegen_NBT));
        this.setSummonedBlazesCount(tag.getInt(summonedBlazesCount_NBT));
    }
    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt(activeShields_NBT, this.getActiveShieldsCount());
        tag.putInt(ticksUntilShieldRegen_NBT, this.getTicksUntilShieldRegeneration());
        tag.putInt(summonedBlazesCount_NBT, this.getSummonedBlazesCount());
    }
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FLAGS_ID, (byte)0);
        this.entityData.define(ACTIVE_SHIELDS_COUNT, defaultActiveShieldsCount);
        this.entityData.define(TICKS_UNTIL_SHIELD_REGENERATION, defaultTicksUntilShieldRegen);
        this.entityData.define(SUMMONED_BLAZES_COUNT, defaultSummonedBlazesCount);
    }
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSoundEvents.WILDFIRE_IDLE.get();
    }
    
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return ModSoundEvents.WILDFIRE_HURT.get();
    }
    
    @Override
    protected SoundEvent getDeathSound() {
        return ModSoundEvents.WILDFIRE_DEATH.get();
    }
    public SoundEvent getShootSound() {
        return ModSoundEvents.WILDFIRE_SHOOT.get();
    }
    
    public void playShootSound() {
        this.playSound(this.getShootSound(), this.getSoundVolume(), this.getVoicePitch());
    }
    public void breakShield() {
        this.setActiveShieldsCount(this.getActiveShieldsCount() - 1);
    }
    
    public void regenerateShield() {
        if (this.getActiveShieldsCount() >= defaultActiveShieldsCount) {
            return;
        }
        
        this.setActiveShieldsCount(this.getActiveShieldsCount() + 1);
    }
    
    public int getActiveShieldsCount() {
        return this.entityData.get(ACTIVE_SHIELDS_COUNT);
    }
    
    public void setActiveShieldsCount(int activeShields) {
        this.entityData.set(ACTIVE_SHIELDS_COUNT, activeShields);
    }
    
    public boolean hasActiveShields() {
        return this.getActiveShieldsCount() > 0;
    }
    
    public int getTicksUntilShieldRegeneration() {
        return this.entityData.get(TICKS_UNTIL_SHIELD_REGENERATION);
    }
    
    public void setTicksUntilShieldRegeneration(int ticksUntilShieldRegeneration) {
        this.entityData.set(TICKS_UNTIL_SHIELD_REGENERATION, ticksUntilShieldRegeneration);
    }
    
    public void resetTicksUntilShieldRegeneration() {
        this.setTicksUntilShieldRegeneration(defaultTicksUntilShieldRegen);
    }
    
    public int getSummonedBlazesCount() {
        return this.entityData.get(SUMMONED_BLAZES_COUNT);
    }
    
    public void setSummonedBlazesCount(int summonedBlazesCount) {
        this.entityData.set(SUMMONED_BLAZES_COUNT, summonedBlazesCount);
    }
    public SoundEvent getShieldBreakSound() {
        return ModSoundEvents.WILDFIRE_SHIELD_BREAK.get();
    }
    
    public void playShieldBreakSound() {
        this.playSound(this.getShieldBreakSound(), this.getSoundVolume(), this.getVoicePitch());
    }
    
    public boolean areBlazesSummoned() {
        return this.getSummonedBlazesCount() > 0;
    }
    @Override
    public float getLightLevelDependentMagicValue() {
        return 1.0F;
    }
    
    //protected SoundEvent getStepSound() {
    //    return SoundEvents.ZOMBIE_STEP;
    //}
    //
    //@Override
    //protected void playStepSound(BlockPos pPos, BlockState pBlock) {
    //    this.playSound(this.getStepSound(), 0.15F, 1.0F);
    //}
    @Override
    public void aiStep() {
        if (!this.onGround() && this.getDeltaMovement().y < 0.0) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0, 0.6, 1.0));
        }
        
        if (this.level().isClientSide) {
            //if (this.random.nextInt(24) == 0 && !this.isSilent()) {
            //    this.level().playLocalSound(
            //                    this.getX() + 0.5,
            //                    this.getY() + 0.5,
            //                    this.getZ() + 0.5,
            //                    ModSoundEvents.WILDFIRE_IDLE.get(),
            //                    this.getSoundSource(),
            //                    1.0F + this.random.nextFloat(),
            //                    this.random.nextFloat() * 0.7F + 0.3F,
            //                    false
            //            );
            //}
            
            for(int i = 0; i < 2; ++i) {
                this.level().addParticle(ParticleTypes.LARGE_SMOKE, this.getRandomX(0.5), this.getRandomY(), this.getRandomZ(0.5), 0.0, 0.0, 0.0);
                
            }
        }
        super.aiStep();
    }
    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source == this.damageSources().generic()) {
            return super.hurt(source, amount);
        }
        
        Entity attacker = source.getEntity();
        
        //if (source == this.damageSources().inFire() || (attacker != null && attacker.getType().is(FriendsAndFoesTags.WILDFIRE_ALLIES))) {
        //    return false;
        //}
        
        if (this.hasActiveShields()) {
            this.damageAmountCounter += amount;
            float shieldBreakDamageThreshold = (float) this.getAttributeValue(Attributes.MAX_HEALTH) * 0.25F;
            
            if (this.damageAmountCounter >= shieldBreakDamageThreshold) {
                if (attacker instanceof LivingEntity) {
                    attacker.hurt(this.damageSources().mobAttack(this), 8.0F);
                }
                
                this.breakShield();
                this.playShieldBreakSound();
                this.damageAmountCounter = 0;
            }
            
            amount = 0.0F;
        }
        
        this.resetTicksUntilShieldRegeneration();
        
        boolean damageResult = super.hurt(source, amount);
        
        //if (damageResult && attacker instanceof LivingEntity) {
        //    WildfireBrain.onAttacked(this, (LivingEntity) attacker);
        //}
        
        return damageResult;
    }
    @Override
    public void tick() {
        super.tick();
        
        this.setTicksUntilShieldRegeneration(this.getTicksUntilShieldRegeneration() - 1);
        
        if (this.getTicksUntilShieldRegeneration() == 0) {
            this.regenerateShield();
            this.resetTicksUntilShieldRegeneration();
        }
    }
    @Override
    public boolean isSensitiveToWater() {
        return true;
    }
    
    @Override
    protected void customServerAiStep() {
        --this.nextHeightOffsetChangeTick;
        if (this.nextHeightOffsetChangeTick <= 0) {
            this.nextHeightOffsetChangeTick = 100;
            this.allowedHeightOffset = (float)this.random.triangle(0.5, 3.891);
        }
        
        LivingEntity livingentity = this.getTarget();
        if (livingentity != null && livingentity.getEyeY() > this.getEyeY() + (double)this.allowedHeightOffset && this.canAttack(livingentity)) {
            Vec3 vec3 = this.getDeltaMovement();
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, (0.3F - vec3.y) * 0.3F, 0.0));
            this.hasImpulse = true;
        }
        
        super.customServerAiStep();
    }
    
    /**
     * Returns {@code true} if the entity is on fire. Used by render to add the fire effect on rendering.
     */
    @Override
    public boolean isOnFire() {
        return this.isCharged();
    }
    
    private boolean isCharged() {
        return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
    }
    
    void setCharged(boolean pCharged) {
        byte b0 = this.entityData.get(DATA_FLAGS_ID);
        if (pCharged) {
            b0 = (byte)(b0 | 1);
        } else {
            b0 = (byte)(b0 & -2);
        }
        
        this.entityData.set(DATA_FLAGS_ID, b0);
    }
    
    class WildfireAttackGoal extends Goal {
        private final WildfireEntity wildfire;
        private int attackStep;
        private int attackTime;
        private int lastSeen;
        
        public WildfireAttackGoal(WildfireEntity pWildfire) {
            this.wildfire = pWildfire;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }
        
        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this method as well.
         */
        @Override
        public boolean canUse() {
            LivingEntity livingentity = this.wildfire.getTarget();
            return livingentity != null && livingentity.isAlive() && this.wildfire.canAttack(livingentity);
        }
        
        /**
         * Execute a one shot task or start executing a continuous task
         */
        @Override
        public void start() {
            this.attackStep = 0;
        }
        
        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        @Override
        public void stop() {
            this.wildfire.setCharged(false);
            this.lastSeen = 0;
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
            --this.attackTime;
            LivingEntity livingentity = this.wildfire.getTarget();
            if (livingentity != null) {
                boolean flag = this.wildfire.getSensing().hasLineOfSight(livingentity);
                if (flag) {
                    this.lastSeen = 0;
                } else {
                    ++this.lastSeen;
                }
                
                double d0 = this.wildfire.distanceToSqr(livingentity);
                if (d0 < 4.0) {
                    if (!flag) {
                        return;
                    }
                    
                    if (this.attackTime <= 0) {
                        this.attackTime = 20;
                        this.wildfire.doHurtTarget(livingentity);
                    }
                    
                    this.wildfire.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0);
                } else if (d0 < this.getFollowDistance() * this.getFollowDistance() && flag) {
                    double d1 = livingentity.getX() - this.wildfire.getX();
                    double d2 = livingentity.getY(0.5) - this.wildfire.getY(0.5);
                    double d3 = livingentity.getZ() - this.wildfire.getZ();
                    if (this.attackTime <= 0) {
                        ++this.attackStep;
                        if (this.attackStep == 1) {
                            this.attackTime = 60;
                            this.wildfire.setCharged(true);
                        } else if (this.attackStep <= 4) {
                            this.attackTime = 6;
                        } else {
                            this.attackTime = 100;
                            this.attackStep = 0;
                            this.wildfire.setCharged(false);
                        }
                        
                        if (this.attackStep > 1) {
                            double d4 = Math.sqrt(Math.sqrt(d0)) * 0.5;
                            if (!this.wildfire.isSilent()) {
                                this.wildfire.level().levelEvent(null, 1018, this.wildfire.blockPosition(), 0);
                            }
                            
                            for(int i = 0; i < 4; ++i) {
                                SmallFireball smallfireball = new SmallFireball(
                                        this.wildfire.level(),
                                        this.wildfire,
                                        this.wildfire.getRandom().triangle(d1, 2.297 * d4),
                                        d2,
                                        this.wildfire.getRandom().triangle(d3, 2.297 * d4)
                                );
                                smallfireball.setPos(smallfireball.getX(), this.wildfire.getY(0.5) + 0.5, smallfireball.getZ());
                                this.wildfire.level().addFreshEntity(smallfireball);
                            }
                        }
                    }
                    
                    this.wildfire.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);
                } else if (this.lastSeen < 5) {
                    this.wildfire.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0);
                }
                
                super.tick();
            }
        }
        
        private double getFollowDistance() {
            return this.wildfire.getAttributeValue(Attributes.FOLLOW_RANGE);
        }
    }
}
