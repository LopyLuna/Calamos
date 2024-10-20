package uwu.lopyluna.calamos.elements.items.equipment.tool.arrow.irradiated;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import uwu.lopyluna.calamos.elements.ModEffects;
import uwu.lopyluna.calamos.elements.ModEntity;
import uwu.lopyluna.calamos.elements.ModItems;

public class IrradiatedArrow extends AbstractArrow {
    private static final ItemStack DEFAULT_ARROW_STACK = new ItemStack(ModItems.IRRADIATED_ARROW.get());
    private int duration = 200;

    public IrradiatedArrow(EntityType<? extends IrradiatedArrow> entityType, Level level) {
        super(entityType, level, DEFAULT_ARROW_STACK);
    }

    public IrradiatedArrow(Level pLevel, LivingEntity pOwner, ItemStack pPickupItemStack) {
        super(ModEntity.IRRADIATED_ARROW.get(), pOwner, pLevel, pPickupItemStack);
    }

    public IrradiatedArrow(Level pLevel, double pX, double pY, double pZ, ItemStack pPickupItemStack) {
        super(ModEntity.IRRADIATED_ARROW.get(), pX, pY, pZ, pLevel, pPickupItemStack);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            if (!onGround()) {
                this.level().addParticle(ParticleTypes.GLOW, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
                this.level().addParticle(ParticleTypes.HAPPY_VILLAGER, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            } else {
                if (this.level().random.nextFloat() < getIdleParticleChance()) {
                    Vec3 ppos = offsetRandomly(this.position(), this.level().random, .5f);
                    Vec3 motion = offsetRandomly(ppos, this.level().random, 1.5f);
                    this.level().addParticle(ParticleTypes.HAPPY_VILLAGER, ppos.x, this.getY() + 0.5, ppos.z, motion.x, motion.y-.1f, motion.z);
                    this.level().addParticle(ParticleTypes.GLOW, ppos.x, this.getY() + 0.5, ppos.x, motion.x, motion.y, motion.z);
                }
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        Vec3 basemotion = new Vec3(0, 1, 0);
        this.level().addParticle(ParticleTypes.FLASH, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
        for (int i = 0; i < 20; i++) {
            Vec3 motion = offsetRandomly(basemotion, this.level().random, 1.5f);
            this.level().addParticle(ParticleTypes.HAPPY_VILLAGER, this.getX(), this.getY(), this.getZ(), motion.x, motion.y, motion.z);
            this.level().addParticle(ParticleTypes.GLOW, this.getX(), this.getY(), this.getZ(), motion.x, motion.y, motion.z);
        }
        super.onHitEntity(pResult);
    }

    @Override
    public void deflect() {
        super.deflect();
        Vec3 basemotion = new Vec3(0, 1, 0);
        this.level().addParticle(ParticleTypes.FLASH, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
        for (int i = 0; i < 20; i++) {
            Vec3 motion = offsetRandomly(basemotion, this.level().random, 1.5f);
            this.level().addParticle(ParticleTypes.HAPPY_VILLAGER, this.getX(), this.getY(), this.getZ(), motion.x, motion.y, motion.z);
            this.level().addParticle(ParticleTypes.GLOW, this.getX(), this.getY(), this.getZ(), motion.x, motion.y, motion.z);
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        Vec3 basemotion = new Vec3(0, 1, 0);
        this.level().addParticle(ParticleTypes.FLASH, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
        for (int i = 0; i < 20; i++) {
            Vec3 motion = offsetRandomly(basemotion, this.level().random, 1.5f);
            this.level().addParticle(ParticleTypes.HAPPY_VILLAGER, this.getX(), this.getY(), this.getZ(), motion.x, motion.y, motion.z);
            this.level().addParticle(ParticleTypes.GLOW, this.getX(), this.getY(), this.getZ(), motion.x, motion.y, motion.z);
        }
    }

    @Override
    protected void doPostHurtEffects(LivingEntity pLiving) {
        super.doPostHurtEffects(pLiving);
        MobEffectInstance mobeffectinstance = new MobEffectInstance(ModEffects.IRRADIATED.get(), this.duration, 0);
        pLiving.addEffect(mobeffectinstance, this.getEffectSource());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("Duration")) {
            this.duration = pCompound.getInt("Duration");
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Duration", this.duration);
    }

    protected float getIdleParticleChance() {
        return Mth.clamp(-11, 5, 100) / 128f;
    }

    public static Vec3 offsetRandomly(Vec3 vec, RandomSource r, float radius) {
        return new Vec3(vec.x + (r.nextFloat() - .5f) * 2 * radius, vec.y + (r.nextFloat() - .5f) * 2 * radius,
                vec.z + (r.nextFloat() - .5f) * 2 * radius);
    }
}
