package uwu.lopyluna.calamos.elements.entity.hook;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import uwu.lopyluna.calamos.elements.items.equipment.hook.AbstractHookItem;
import uwu.lopyluna.calamos.utilities.CuriosUtil;

import java.util.Optional;
import java.util.function.IntFunction;

public abstract class AbstractHookEntity extends Projectile {
    private int time = 0;
    public static final EntityDataAccessor<Integer> DATA_HOOK_STATE = SynchedEntityData.defineId(AbstractHookEntity.class, EntityDataSerializers.INT);
    private final float hookRangeSqr;
    public float lastDelta = 0.0F;

    public AbstractHookEntity(EntityType<? extends AbstractHookEntity> entityType, Level pLevel) {
        super(entityType, pLevel);
        this.hookRangeSqr = 0.0F;
    }

    public AbstractHookEntity(EntityType<? extends AbstractHookEntity> entityType, AbstractHookItem item, Player player, Level level) {
        super(entityType, level);
        this.hookRangeSqr = item.getRange() * item.getRange();
        setOwner(player);
        setNoGravity(true);
        setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(DATA_HOOK_STATE, 0);
    }

    public HookState getState() {
        return HookState.byId(entityData.get(DATA_HOOK_STATE));
    }

    public void setHookState(HookState state) {
        entityData.set(DATA_HOOK_STATE, state.id);
    }

    protected void onHooked(BlockHitResult hitResult) {
        BlockPos blockPos = hitResult.getBlockPos();
        level().gameEvent(GameEvent.PROJECTILE_LAND, blockPos, GameEvent.Context.of(this, level().getBlockState(blockPos)));
        setDeltaMovement(Vec3.ZERO);
        setHookState(HookState.HOOKED);
        this.hasImpulse = true;
    }

    @Override
    public void tick() {
        super.tick();
        Entity owner = getOwner();
        if (owner == null || owner.isRemoved()) {
            discard();
            return;
        }

        Vec3 motion = getDeltaMovement();
        double x = getX() + motion.x;
        double y = getY() + motion.y;
        double z = getZ() + motion.z;
        setPos(x, y, z);

        HookState hookState = getState();

        time++;
        if (time >= 100) {
            setHookState(HookState.PULL);
        }

        if (level().isClientSide) return;
        if (hookState == HookState.HOOKED) {
            Vec3 subtract = position().subtract(owner.position());
            if ((distanceToSqr(owner) < 20)) {
                Vec3 motions = subtract.normalize().scale(0.06);
                owner.setDeltaMovement(owner.getDeltaMovement().scale(0.85).add(motions));
            } else {
                Vec3 motions = subtract.normalize().scale(0.12);
                owner.setDeltaMovement(owner.getDeltaMovement().scale(0.85).add(motions));
            }
            if ((distanceToSqr(owner) < 10 && !owner.onGround()) || distanceToSqr(owner) > 2000) {
                setHookState(HookState.PULL);
            }
            return;
        }

        Optional<ItemStack> hook = CuriosUtil.getSlot((LivingEntity) owner, "hook", 0);
        if (hook.isEmpty()) {
            discard();
            return;
        }

        //if (hookState == HookState.PULL) {
        //    discard();
        //    return;
        //}

        if (distanceToSqr(owner) > hookRangeSqr) {
            setHookState(HookState.PULL);
            return;
        }

        if (hookState == HookState.PUSH) {
            if (distanceToSqr(owner) < 4) {
                setDeltaMovement(getDeltaMovement().scale(1.3));
            }
            Vec3 pos = position();
            Vec3 nextPos = pos.add(motion);

            BlockHitResult hitResult = level().clip(new ClipContext(pos, nextPos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));

            if (hitResult.getType() == HitResult.Type.BLOCK) {
                Vec3 hitPos = hitResult.getLocation();

                double adjustX = hitPos.x - pos.x;
                double adjustY = hitPos.y - pos.y;
                double adjustZ = hitPos.z - pos.z;

                double newX = pos.x + adjustX * 0.8;
                double newY = pos.y + adjustY * 0.8;
                double newZ = pos.z + adjustZ * 0.8;

                if (hitResult.isInside()) {
                    setPos(getX() - adjustX, getY() - adjustY, getZ() - adjustZ);
                } else {
                    setPos(newX, newY, newZ);
                }
                onHitBlock(hitResult);
                onHooked(hitResult);
            }
        }
    }

    @Override
    public void shootFromRotation(@NotNull Entity pShooter, float pX, float pY, float pZ, float pVelocity, float pInaccuracy) {
        time = 0;
        float x = -Mth.sin(pY * Mth.DEG_TO_RAD) * Mth.cos(pX * Mth.DEG_TO_RAD);
        float y = -Mth.sin((pX + pZ) * Mth.DEG_TO_RAD);
        float z = Mth.cos(pY * Mth.DEG_TO_RAD) * Mth.cos(pX * Mth.DEG_TO_RAD);
        shoot(x, y, z, pVelocity, pInaccuracy);
        setDeltaMovement(getDeltaMovement().scale(15));
    }

    public enum HookState implements StringRepresentable {
        PUSH(0, "push"),
        PULL(1, "pull"),
        HOOKED(2, "hooked");

        private static final IntFunction<HookState> BY_ID = ByIdMap.continuous(HookState::getId, values(), ByIdMap.OutOfBoundsStrategy.CLAMP);
        final int id;
        private final String name;

        HookState(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public static HookState byId(int pId) {
            return BY_ID.apply(pId);
        }

        public @NotNull String getSerializedName() {
            return name;
        }
    }
}
