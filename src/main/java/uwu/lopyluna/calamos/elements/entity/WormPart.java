package uwu.lopyluna.calamos.elements.entity;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class WormPart extends LivingEntity {

    public static final EntityDataAccessor<Optional<UUID>> PARENT_UUID = SynchedEntityData.defineId(WormPart.class, EntityDataSerializers.OPTIONAL_UUID);
    public UUID childUUID;
    public static final EntityDataAccessor<Optional<UUID>> WORM_UUID = SynchedEntityData.defineId(WormPart.class, EntityDataSerializers.OPTIONAL_UUID);
    public int segmentIndex;

    public WormPart(EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public WormPart(EntityType<? extends LivingEntity> pEntityType, Level pLevel, UUID parentUUID) {
        super(pEntityType, pLevel);
        this.entityData.set(PARENT_UUID, Optional.of(parentUUID));
    }

    public Worm getWorm() {
        if (this.level() instanceof ServerLevel serverLevel) {
            Entity entity = serverLevel.getEntity(this.getWormUUID());
            if (entity instanceof Worm worm)
                return worm;
        }
        return null;
    }

    public LivingEntity getParent() {
        if (this.level() instanceof ServerLevel serverLevel) {
            Entity entity = serverLevel.getEntity(this.getParentUUID());
            if (entity instanceof LivingEntity livingEntity)
                return livingEntity;
        }
        return null;
    }

    public List<WormPart> getChildren() {
        List<WormPart> children = new ArrayList<>();
        WormPart currentChild = this.getChild();
        while (currentChild != null) {
            children.add(currentChild);
            currentChild = currentChild.getChild();
        }
        return children;
    }

    public WormPart getChild() {
        if (this.level() instanceof ServerLevel serverLevel) {
            Entity entity = serverLevel.getEntity(this.childUUID);
            if (entity instanceof WormPart wormPart)
                return wormPart;
        }
        return null;
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(PARENT_UUID, Optional.empty());
        this.entityData.define(WORM_UUID, Optional.empty());
        super.defineSynchedData();
    }

    public void setParentUUID(UUID parentUUID) {
        this.entityData.set(PARENT_UUID, Optional.of(parentUUID));
    }

    public UUID getParentUUID() {
        return this.entityData.get(PARENT_UUID).orElse(null);
    }

    public void setWormUUID(UUID wormUUID) {
        this.entityData.set(WORM_UUID, Optional.of(wormUUID));
    }

    public UUID getWormUUID() {
        return this.entityData.get(WORM_UUID).orElse(null);
    }

    @Override
    public void die(DamageSource pDamageSource) {
        Worm worm = this.getWorm();
        if (worm != null) {

            if (worm.canSplit()) {
                worm.split(this.segmentIndex);
                worm.segments.remove(this.segmentIndex);
            }

        }
        super.die(pDamageSource);
    }

    public boolean canSplit() {
        return this.getWorm() != null && this.getWorm().canSplit();
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (!this.level().isClientSide && !this.canSplit()) {
            this.getWorm().hurt(pSource, pAmount);
            return false;
        }
        return super.hurt(pSource, pAmount);
    }

    @Override
    public float getHealth() {
        if (this.getWorm() == null || this.canSplit()) {
            return super.getHealth();
        }
        return this.getWorm().getHealth();
    }

    @Override
    public void tick() {
        this.setDeltaMovement(0, 0, 0);
        if (!this.level().isClientSide) {
            LivingEntity parent = this.getParent();
            if (parent == null) {
                this.discard();
                return;
            }
            Vec3 parentPos = parent.position();
            Vec3 lookVec = parent.getLookAngle().scale(-parent.getDimensions(parent.getPose()).width);
            Vec3 pos = parentPos.add(lookVec);
            this.teleportTo(pos.x, pos.y, pos.z);
        }
        super.tick();
        this.setDeltaMovement(0, 0, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putUUID("ParentUUID", this.getParentUUID());
        pCompound.putUUID("WormUUID", this.getWormUUID());
        pCompound.putInt("SegmentIndex", this.segmentIndex);
        super.addAdditionalSaveData(pCompound);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        if (pCompound.hasUUID("ParentUUID"))
            this.setParentUUID(pCompound.getUUID("ParentUUID"));
        if (pCompound.hasUUID("WormUUID"))
            this.setWormUUID(pCompound.getUUID("WormUUID"));
        if (pCompound.contains("SegmentIndex"))
            this.segmentIndex = pCompound.getInt("SegmentIndex");
        super.readAdditionalSaveData(pCompound);
    }

    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return NonNullList.withSize(1, ItemStack.EMPTY);
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot pSlot) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setItemSlot(EquipmentSlot pSlot, ItemStack pStack) {

    }

    @Override
    public HumanoidArm getMainArm() {
        return HumanoidArm.RIGHT;
    }

    @Override
    public boolean alwaysAccepts() {
        return super.alwaysAccepts();
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }
}
