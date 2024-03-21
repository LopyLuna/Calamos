package uwu.lopyluna.calamos.elements.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import uwu.lopyluna.calamos.elements.ModEntity;
import uwu.lopyluna.calamos.elements.entity.entity_definitions.Boss;
import uwu.lopyluna.calamos.elements.entity.entity_definitions.BossBarMonster;
import uwu.lopyluna.calamos.utilities.CalamosBossEvent;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;


public class Worm extends BossBarMonster implements Boss {
    public static final EntityDataAccessor<Integer> MAX_SEGMENT_COUNT = SynchedEntityData.defineId(Worm.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> SEGMENT_COUNT = SynchedEntityData.defineId(Worm.class, EntityDataSerializers.INT);
    public final HashMap<Integer, WormPart> segments = new HashMap<>();
    protected WormPart previous = null;

    public Worm(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.entityData.set(MAX_SEGMENT_COUNT, 8);
        this.createAllSegments();
    }

    public Worm(EntityType<? extends Monster> entityType, Level level, int segmentCount) {
        super(entityType, level);
        this.entityData.set(MAX_SEGMENT_COUNT, segmentCount);
        this.createAllSegments();
    }

    public Worm(EntityType<? extends Monster> pEntityType, Level pLevel, List<WormPart> segments) {
        super(pEntityType, pLevel);
        this.entityData.set(MAX_SEGMENT_COUNT, segments.size());
        for (int i = 0; i < segments.size(); i++) {
            WormPart segment = segments.get(i);
            segment.setWormUUID(this.getUUID());
            this.segments.put(i + 1, segment);
        }
        this.segments.get(1).setParentUUID(this.getUUID());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 10.0F)
                .add(Attributes.KNOCKBACK_RESISTANCE, 100)
                .add(Attributes.MOVEMENT_SPEED, 0.2F)
                .add(Attributes.FOLLOW_RANGE, 32.0D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    @Override
    public void tick() {
        this.setDeltaMovement(0, 0, 0);
        super.tick();
        this.setDeltaMovement(0, 0, 0);
    }

    public boolean canSplit() {
        return false;
    }

    public void split(int index) {
        if (this.segments.containsKey(index)) {
            WormPart wormPart = this.segments.get(index);
            int trailingSegments = this.segments.size() - index;

            if (trailingSegments <= 1) {
                wormPart.discard();
                this.segments.remove(index + 1);
                return;
            }

            WormPart newRoot = this.segments.getOrDefault(index + 1, null);
            if (newRoot == null) {
                wormPart.discard();
                return;
            }

            List<WormPart> children = wormPart.getChildren();

            if (children.isEmpty()) {
                wormPart.discard();
                return;
            }

            Worm newWorm = new Worm(ModEntity.WORM_HEAD.get(), this.level(), children);
            newWorm.teleportTo(newRoot.getX(), newRoot.getY(), newRoot.getZ());
            this.level().addFreshEntity(newWorm);

            for (int i = 0; i < trailingSegments; i++) {
                this.segments.remove(index + i + 1);
            }
        }
    }

    @Override
    public void die(DamageSource pDamageSource) {
        if (this.canSplit()) {
            this.split(1);
            return;
        }
        super.die(pDamageSource);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(MAX_SEGMENT_COUNT, 5);
        this.entityData.define(SEGMENT_COUNT, 0);
        super.defineSynchedData();
    }

    public int getMaxSegmentCount() {
        return this.entityData.get(MAX_SEGMENT_COUNT);
    }

    public int getSegmentCount() {
        return this.entityData.get(SEGMENT_COUNT);
    }

    public void setSegmentCount(int count) {
        this.entityData.set(SEGMENT_COUNT, count);
    }

    public WormPart createSegment() {
        LivingEntity parent = this.segments.isEmpty() ? this : this.segments.get(this.segments.size());
        WormPart wormPart = new WormPart(ModEntity.WORM_PART.get(), this.level(), parent.getUUID());
        wormPart.segmentIndex = this.segments.size() + 1;
        wormPart.setWormUUID(this.getUUID());
        wormPart.setPos(this.getX(), this.getY(), this.getZ());
        this.setSegmentCount(this.getSegmentCount() + 1);
        this.level().addFreshEntity(wormPart);

        if (this.previous != null) {
            this.previous.childUUID = wormPart.getUUID();
        }

        this.previous = wormPart;
        return wormPart;
    }

    public void createAllSegments() {
        for (int i = 0; i < this.getMaxSegmentCount(); i++) {
            WormPart wormPart = this.createSegment();
            this.segments.put(i + 1, wormPart);
            if (!this.canSplit()) {
                this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(this.getMaxHealth() + wormPart.getMaxHealth());
                this.setHealth(this.getMaxHealth());
            }
        }
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }
}
