package uwu.lopyluna.calamos.elements.entity.machina.infected;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import uwu.lopyluna.calamos.elements.blockEntity.AntennaBlockEntity;
import uwu.lopyluna.calamos.elements.entity.entity_definitions.MachinaHusk;
import uwu.lopyluna.calamos.elements.entity.machina.goal.*;

public class MachinaZombie extends Zombie implements MachinaHusk {
    private static boolean inRangeOfAntenna;
    private static boolean inRangeOfMainframe;
    private static BlockPos antennaPos;
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
    protected void registerGoals() {
        this.goalSelector.addGoal(8, new MachinaLookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new MachinaRandomLookAroundGoal(this));
        this.addBehaviourGoals();
    }
    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(2, new MachinaZombieAttackGoal(this, 1.0, false));
        this.goalSelector.addGoal(6, new MachinaMoveThroughVillageGoal(this, 1.0, true, 4, this::canBreakDoors));
        this.goalSelector.addGoal(7, new MachinaWaterAvoidingRandomStrollGoal(this, 1.0));
        this.targetSelector.addGoal(1, (new MachinaHurtByTargetGoal(this)).setAlertOthers(ZombifiedPiglin.class));
        this.targetSelector.addGoal(2, new MachinaNearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new MachinaNearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(3, new MachinaNearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setInRangeOfAntenna(pCompound.getBoolean("inRangeOfAntenna"));
        this.setInRangeOfMainframe(pCompound.getBoolean("inRangeOfMainframe"));
        antennaPos = new BlockPos.MutableBlockPos(pCompound.getDouble("antennaX"), pCompound.getDouble("antennaY"), pCompound.getDouble("antennaZ"));
    }
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("inRangeOfAntenna", inRangeOfAntenna());
        pCompound.putBoolean("inRangeOfMainframe", inRangeOfMainframe());
        pCompound.putDouble("antennaX", antennaPos.getX());
        pCompound.putDouble("antennaY", antennaPos.getY());
        pCompound.putDouble("antennaZ", antennaPos.getZ());
    }
    protected boolean convertsInWater() {
        return false;
    }
    public void setInRangeOfAntenna(boolean inRangeOfAntenna) {
        this.getPersistentData().putBoolean("inRangeOfAntenna", inRangeOfAntenna);
    }
    public void setInRangeOfMainframe(boolean inRangeOfMainframe) {
        this.getPersistentData().putBoolean("inRangeOfMainframe", inRangeOfMainframe);
    }
    public void clearAntenna() {
        this.getPersistentData().putBoolean("inRangeOfAntenna", false);
        this.getPersistentData().putDouble("antennaX", 0);
        this.getPersistentData().putDouble("antennaY", 0);
        this.getPersistentData().putDouble("antennaZ", 0);
    }
    @Override
    public boolean inRangeOfAntenna() {
        return inRangeOfAntenna;
    }
    
    @Override
    public boolean inRangeOfMainframe() {
        return inRangeOfMainframe;
    }
    
    @Override
    public boolean isMainframe() {
        return false;
    }
    
    @Override
    public double getAntennaX() {
        return this.getPersistentData().getDouble("antennaX");
    }
    
    @Override
    public double getAntennaY() {
        return this.getPersistentData().getDouble("antennaY");
    }
    
    @Override
    public double getAntennaZ() {
        return this.getPersistentData().getDouble("antennaZ");
    }
    
    public BlockPos getAntennaPos() {
        return new BlockPos.MutableBlockPos(getAntennaX(), getAntennaY(), getAntennaZ());
    }
    public AntennaBlockEntity getAntenna() {
        return (AntennaBlockEntity) this.level.getBlockEntity(getAntennaPos());
    }
    
    public void checkAntenna() {
        if (this.getAntenna() == null) {
            setInRangeOfAntenna(false);
        }
        AABB antennaRange = getAntenna().getAntennaRange();
        if (!antennaRange.intersects(this.getBoundingBox())) {
            clearAntenna();
        }
    }
    public void spawnSmoke() {
        if (this.level.isClientSide) {
            this.level.addParticle(ParticleTypes.LARGE_SMOKE, this.getX(), this.getY() + 1.0D, this.getZ(), 0.0D, 0.0D, 0.0D);
        }
    }
    
}
