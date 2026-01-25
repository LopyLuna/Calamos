package uwu.lopyluna.calamos.elements.entity.orb;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.scores.Team;
import uwu.lopyluna.calamos.client.particle.GlowParticleOptions;
import uwu.lopyluna.calamos.elements.ModEntity;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.stream.Collectors;

public class LifestealOrb extends AbstractOrbEntity {

    private static final EntityDataAccessor<Float> HEALING = SynchedEntityData.defineId(LifestealOrb.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> TARGET_ENTITY_ID = SynchedEntityData.defineId(LifestealOrb.class, EntityDataSerializers.INT);

    public LifestealOrb(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.noPhysics = true;
    }

    public LifestealOrb(Level pLevel) {
        super(ModEntity.LIFESTEAL_ORB.get(), pLevel);
        this.noPhysics = true;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(HEALING, 0F);
        builder.define(TARGET_ENTITY_ID, -1);
    }

    @Override
    public void tick() {
        super.tick();
        decideTarget();
        int id = this.getTargetID();
        if (id == -1) {
            if (!level().isClientSide) {
                Entity owner = this.getOwner();
                if (owner != null) {
                    this.setTargetID(owner.getId());
                } else {
                    this.discard();
                }
            }
        } else {
            Entity target = level().getEntity(id);
            if (target != null) {
                Vec3 arcVec = target.position().add(0, 0.65F * target.getBbHeight(), 0).subtract(this.position());
                if(arcVec.length() > target.getBbWidth()){
                    this.setDeltaMovement(this.getDeltaMovement().scale(0.3F).add(arcVec.normalize().scale(0.7F)));
                }
            }
        }

        if (level().isClientSide()) {
            double deltaX = getX() - this.position().x;
            double deltaY = getY() - this.position().y;
            double deltaZ = getZ() - this.position().z;
            double dist = Math.ceil(Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ) * 10);
            GlowParticleOptions options = new GlowParticleOptions(GlowParticleOptions.SPECTRE_LIFESTEAL, 1/1.75f, 24);
            for (double i = 0; i < dist; i++) {
                double coeff = i / dist;
                level().addAlwaysVisibleParticle(options, true, this.position().x + deltaX * coeff, this.position().y + deltaY * coeff, this.position().z + deltaZ * coeff, 0.125f*(random.nextFloat()-0.5f), 0.125f*(random.nextFloat()-0.5f), 0.125f*(random.nextFloat()-0.5f));
            }
        }
    }

    private void decideTarget() {
        Entity owner = this.getOwner();
        if (owner instanceof LivingEntity living) {
            AABB alliesRange = new AABB(owner.blockPosition()).inflate(25);
            Team team = owner.getTeam();
            if (team != null) {
                Map<LivingEntity, Float> allies = level().getEntitiesOfClass(LivingEntity.class, alliesRange, e -> (e != owner && e.isAlliedTo(team))).stream().collect(Collectors.toMap(le -> le, le -> le.getHealth()));
                float lowestHealth = Float.MAX_VALUE;
                LivingEntity mostVulnerable = living;
                for (Map.Entry<LivingEntity, Float> entry : allies.entrySet()) {
                    if (entry.getValue() < lowestHealth) {
                        mostVulnerable = entry.getKey();
                        lowestHealth = entry.getValue();
                    }
                }
                setTargetID(mostVulnerable.getId());
            } else {
                setTargetID(owner.getId());
            }
        }
    }

    @Override
    public boolean deflect(ProjectileDeflection deflection, @Nullable Entity entity, @Nullable Entity owner, boolean deflectedByPlayer) {
        return false;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity victim = result.getEntity();
        if (victim.getId() == getTargetID()) {
            if (victim instanceof LivingEntity living) {
                living.heal(getHealingAmount());
                this.discard();
            } else {
                this.discard();
            }
        }
    }

    private int getTargetID() {
        return this.entityData.get(TARGET_ENTITY_ID);
    }

    private void setTargetID(int id) {
        this.entityData.set(TARGET_ENTITY_ID, id);
    }

    private float getHealingAmount() {
        return this.entityData.get(HEALING);
    }

    public void setHealingAmount(float amount) {
        this.entityData.set(HEALING, amount);
    }
}
