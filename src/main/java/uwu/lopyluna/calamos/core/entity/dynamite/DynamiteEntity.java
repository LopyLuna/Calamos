package uwu.lopyluna.calamos.core.entity.dynamite;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import uwu.lopyluna.calamos.elements.ModEntity;

import javax.annotation.Nullable;
import java.util.Iterator;

public class DynamiteEntity extends ThrowableProjectile {
    private static final EntityDataAccessor<Integer> DATA_FUSE_ID;
    private static final EntityDataAccessor<Boolean> DATA_HAS_FUSE;
    private static final EntityDataAccessor<Float> DATA_EXPLOSION_POWER;
    private static final EntityDataAccessor<Boolean> DATA_NO_PHYSICS;
    private static final EntityDataAccessor<Boolean> DATA_FROM_BOONE;
    @Nullable
    private BlockState lastState;
    protected boolean inGround;
    protected int inGroundTime;
    private static final int DEFAULT_FUSE_TIME = 100;
    private static final int FUSE_TIME_externallyTriggered = 10;

    public DynamiteEntity(EntityType<DynamiteEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public DynamiteEntity(Level pLevel, double pX, double pY, double pZ) {
        super(ModEntity.DYNAMITE.get(), pLevel);
        this.setPos(pX, pY + 2, pZ);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(DATA_FUSE_ID, DEFAULT_FUSE_TIME);
        builder.define(DATA_HAS_FUSE, false);
        builder.define(DATA_EXPLOSION_POWER, 4.0F);
        builder.define(DATA_NO_PHYSICS, false);
        builder.define(DATA_FROM_BOONE, false);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("inBlockState", 10)) {
            this.lastState = NbtUtils.readBlockState(this.level().holderLookup(Registries.BLOCK), pCompound.getCompound("inBlockState"));
        }
        this.inGround = pCompound.getBoolean("inGround");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        if (this.lastState != null) {
            pCompound.put("inBlockState", NbtUtils.writeBlockState(this.lastState));
        }
        pCompound.putBoolean("inGround", this.inGround);
    }

    public void setExplosionPower(float pPower) {
        this.entityData.set(DATA_EXPLOSION_POWER, pPower);
    }

    public float getExplosionPower() {
        return this.entityData.get(DATA_EXPLOSION_POWER);
    }

    public void setHasFuse(boolean pHasFuse) {
        this.entityData.set(DATA_HAS_FUSE, pHasFuse);
    }

    public boolean hasFuse() {
        return this.entityData.get(DATA_HAS_FUSE);
    }

    public void setFuse(int pFuse) {
        this.entityData.set(DATA_FUSE_ID, pFuse);
    }

    public int getFuse() {
        return this.entityData.get(DATA_FUSE_ID);
    }

    public void setFromBoone(boolean pFromBoone) {
        this.entityData.set(DATA_FROM_BOONE, pFromBoone);
    }

    public boolean isFromBoone() {
        return this.entityData.get(DATA_FROM_BOONE);
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public void kill() {
        Level.ExplosionInteraction level$explosioninteraction = isFromBoone() ? Level.ExplosionInteraction.NONE : Level.ExplosionInteraction.TNT;
        this.level().explode(this, this.getX(), this.getY(0.0625D), this.getZ(), this.getExplosionPower(), level$explosioninteraction);
        super.kill();
    }

    public void triggerOtherDynamite() {
        this.level().getEntitiesOfClass(DynamiteEntity.class, this.getBoundingBox().inflate(getExplosionPower())).forEach((dynamite) -> {
            if (dynamite != this) {
                dynamite.setHasFuse(true);
                int randomFuseAddition = Mth.clamp(this.random.nextInt(10) - 5, 0, 10);
                dynamite.setFuse(FUSE_TIME_externallyTriggered + randomFuseAddition);
            }
        });
    }

    @Override
    public void tick() {
        super.tick();
        boolean flag = this.isNoPhysics();
        Vec3 vec3 = this.getDeltaMovement();
        if (getFuse() <= 0) {
            triggerOtherDynamite();
            this.kill();
        }
        if (this.hasFuse()) {
            this.setFuse(getFuse() - 1);
        }
        // Logic so it doesn't clip through blocks (Copied from AbstractArrow)
        BlockPos blockpos = this.blockPosition();
        BlockState blockstate = this.level().getBlockState(blockpos);
        Vec3 vec33;
        if (!blockstate.isAir() && !flag) {
            VoxelShape voxelshape = blockstate.getCollisionShape(this.level(), blockpos);
            if (!voxelshape.isEmpty()) {
                vec33 = this.position();
                Iterator var7 = voxelshape.toAabbs().iterator();

                while(var7.hasNext()) {
                    AABB aabb = (AABB)var7.next();
                    if (aabb.move(blockpos).contains(vec33)) {
                        this.inGround = true;
                        break;
                    }
                }
            }
        }
        if (this.inGround && !flag) {
            if (this.lastState != blockstate && this.shouldFall()) {
                this.startFalling();
            }
            ++this.inGroundTime;
        } else {
            this.inGroundTime = 0;
            Vec3 vec32 = this.position();
            vec33 = vec32.add(vec3);
            HitResult hitresult = this.level().clip(new ClipContext(vec32, vec33, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
            if (hitresult.getType() != HitResult.Type.MISS) {
                vec33 = hitresult.getLocation();
            }
        }
    }

    protected void onHitBlock(BlockHitResult pResult) {
        this.lastState = this.level().getBlockState(pResult.getBlockPos());
        super.onHitBlock(pResult);
        Vec3 vec3 = pResult.getLocation().subtract(this.getX(), this.getY(), this.getZ());
        this.setDeltaMovement(vec3);
        Vec3 vec31 = vec3.normalize().scale(0.05000000074505806);
        this.setPosRaw(this.getX() - vec31.x, this.getY() - vec31.y, this.getZ() - vec31.z);
        this.inGround = true;
    }

    @Override
    public void shoot(double pX, double pY, double pZ, float pVelocity, float pInaccuracy) {
        super.shoot(pX, pY, pZ, pVelocity, pInaccuracy);
    }

    private boolean shouldFall() {
        return this.inGround && this.level().noCollision((new AABB(this.position(), this.position())).inflate(0.06));
    }

    private void startFalling() {
        this.inGround = false;
        Vec3 vec3 = this.getDeltaMovement();
        this.setDeltaMovement(vec3.multiply((double)(this.random.nextFloat() * 0.2F), (double)(this.random.nextFloat() * 0.2F), (double)(this.random.nextFloat() * 0.2F)));
    }

    @Override
    public void move(MoverType pType, Vec3 pPos) {
        super.move(pType, pPos);
        if (pType != MoverType.SELF && this.shouldFall()) {
            this.startFalling();
        }

    }

    public void setNoPhysics(boolean pNoPhysics) {
        this.noPhysics = pNoPhysics;
        this.entityData.set(DATA_NO_PHYSICS, pNoPhysics);
    }

    public boolean isNoPhysics() {
        if (!this.level().isClientSide) {
            return this.noPhysics;
        } else {
            return this.entityData.get(DATA_NO_PHYSICS);
        }
    }

    static {
        DATA_FUSE_ID = SynchedEntityData.defineId(DynamiteEntity.class, EntityDataSerializers.INT);
        DATA_HAS_FUSE = SynchedEntityData.defineId(DynamiteEntity.class, EntityDataSerializers.BOOLEAN);
        DATA_EXPLOSION_POWER = SynchedEntityData.defineId(DynamiteEntity.class, EntityDataSerializers.FLOAT);
        DATA_NO_PHYSICS = SynchedEntityData.defineId(DynamiteEntity.class, EntityDataSerializers.BOOLEAN);
        DATA_FROM_BOONE = SynchedEntityData.defineId(DynamiteEntity.class, EntityDataSerializers.BOOLEAN);
    }
}
