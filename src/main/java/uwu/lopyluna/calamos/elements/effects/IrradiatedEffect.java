package uwu.lopyluna.calamos.elements.effects;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.Vec3;
import uwu.lopyluna.calamos.CalamosMod;

public class IrradiatedEffect extends MobEffect {

    public IrradiatedEffect() {
        super(MobEffectCategory.HARMFUL, 0x78ec4f);

    }

    public void onEffectStarted(LivingEntity pLivingEntity, int pAmplifier) {
        double healthModifier = (0.5f * (((pAmplifier + 1) * 0.5f) / 2)) * -1;
        this.addAttributeModifier(Attributes.MAX_HEALTH, CalamosMod.asResource("max_health.irradiated"), healthModifier, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        if (pLivingEntity.getCommandSenderWorld().isClientSide()) {
        Vec3 basemotion = new Vec3(0, 1, 0);
        pLivingEntity.level().addParticle(ParticleTypes.FLASH, pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(), 0, 0, 0);
            for (int i = 0; i < 20; i++) {
                Vec3 motion = offsetRandomly(basemotion, pLivingEntity.level().random, pAmplifier + 1);
                pLivingEntity.level().addParticle(ParticleTypes.HAPPY_VILLAGER, pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(), motion.x, motion.y, motion.z);
                pLivingEntity.level().addParticle(ParticleTypes.GLOW, pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(), motion.x, motion.y, motion.z);
            }
        }
        super.onEffectStarted(pLivingEntity, pAmplifier);
    }

    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);
        pLivingEntity.hurt(pLivingEntity.damageSources().wither(), 1.0F);
        if (pLivingEntity.getCommandSenderWorld().isClientSide()) {
            if (pLivingEntity.level().random.nextFloat() < getIdleParticleChance()) {
                Vec3 ppos = offsetRandomly(pLivingEntity.position(), pLivingEntity.level().random, 1.5f);
                Vec3 motion = offsetRandomly(ppos, pLivingEntity.level().random, 1.5f);
                pLivingEntity.level().addParticle(ParticleTypes.HAPPY_VILLAGER, ppos.x, pLivingEntity.getY() + 0.5, ppos.z, motion.x, motion.y - .1f, motion.z);
                pLivingEntity.level().addParticle(ParticleTypes.GLOW, pLivingEntity.getX(), pLivingEntity.getY() + 0.5, pLivingEntity.getZ(), motion.x, motion.y, motion.z);
            }
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        int i = 40 >> pDuration;
        if (i > 0) {
            return pDuration % i == 0;
        } else {
            return true;
        }
    }

    public static Vec3 offsetRandomly(Vec3 vec, RandomSource r, float radius) {
        return new Vec3(vec.x + (r.nextFloat() - .5f) * 2 * radius, vec.y + (r.nextFloat() - .5f) * 2 * radius,
                vec.z + (r.nextFloat() - .5f) * 2 * radius);
    }

    protected float getIdleParticleChance() {
        return Mth.clamp(-11, 5, 100) / 128f;
    }
}
