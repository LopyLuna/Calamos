package uwu.lopyluna.calamos.elements.entity.machina.infected;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import uwu.lopyluna.calamos.elements.entity.entity_definitions.MachinaHusk;
import uwu.lopyluna.calamos.elements.entity.machina.goal.*;

public class MachinaZombie extends Zombie implements MachinaHusk {
    private static final EntityDataAccessor<BlockPos> ANTENNA_POS = SynchedEntityData.defineId(MachinaZombie.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<Boolean> IN_RANGE_OF_ANTENNA = SynchedEntityData.defineId(MachinaZombie.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IN_RANGE_OF_MAINFRAME = SynchedEntityData.defineId(MachinaZombie.class, EntityDataSerializers.BOOLEAN);
    public MachinaZombie(EntityType<? extends Zombie> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public void tick() {
        super.tick();
        if (isActive()) {
            checkAntenna();
        } else {
            spawnSmoke();
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ANTENNA_POS, BlockPos.ZERO);
        builder.define(IN_RANGE_OF_ANTENNA, false);
        builder.define(IN_RANGE_OF_MAINFRAME, false);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(8, new MachinaLookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new MachinaRandomLookAroundGoal(this));
        this.addBehaviourGoals();
    }

    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(2, new MachinaZombieAttackGoal(this, 1.0, false));
        this.goalSelector.addGoal(6, new MoveThroughVillageGoal(this, 1.0, true, 4, this::canBreakDoors));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.targetSelector.addGoal(1, (new MachinaHurtByTargetGoal(this)).setAlertOthers(MachinaZombie.class));
        this.targetSelector.addGoal(2, new MachinaNearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new MachinaNearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(3, new MachinaNearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
    }

    protected boolean convertsInWater() {
        return false;
    }

    public void setInRangeOfAntenna(boolean inRangeOfAntenna) {
        this.entityData.set(IN_RANGE_OF_ANTENNA, inRangeOfAntenna);
    }

    public void setInRangeOfMainframe(boolean inRangeOfMainframe) {
        this.entityData.set(IN_RANGE_OF_MAINFRAME, inRangeOfMainframe);
    }

    @Override
    public boolean inRangeOfAntenna() {
        return this.entityData.get(IN_RANGE_OF_ANTENNA);
    }
    
    @Override
    public boolean inRangeOfMainframe() {
        return this.entityData.get(IN_RANGE_OF_MAINFRAME);
    }
    
    @Override
    public boolean isMainframe() {
        return false;
    }
    
    public BlockPos getAntennaPos() {
        return this.entityData.get(ANTENNA_POS);
    }
    
    public void checkAntenna() {
        setInRangeOfAntenna(true);
    }

    public void spawnSmoke() {
        if (this.level.isClientSide) {
            this.level.addParticle(ParticleTypes.LARGE_SMOKE, this.getX(), this.getY() + 1.0D, this.getZ(), 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public void move(MoverType pType, Vec3 pPos) {
        if (!this.inRangeOfAntenna()) {
            return;
        }
        super.move(pType, pPos);
    }
    
}
