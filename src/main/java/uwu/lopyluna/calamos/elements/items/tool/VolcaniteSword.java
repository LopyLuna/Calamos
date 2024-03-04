package uwu.lopyluna.calamos.elements.items.tool;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

@SuppressWarnings({"all"})
public class VolcaniteSword extends SwordItem {
    public VolcaniteSword( Properties pProperties) {
        super(CalamosTiers.VOLCANITE, 3, -2.2F, pProperties);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack pStack, Player pAttacker, Entity pTarget) {
        Level pLevel = pAttacker.level();
        if (pTarget instanceof Slime slime && !(pTarget instanceof MagmaCube)) {
            playExplosionParticles(pLevel, pTarget.position());
            pLevel.playSound(null, slime.blockPosition(), SoundEvents.GENERIC_EXTINGUISH_FIRE,
                    SoundSource.NEUTRAL, 1.25f, 0.65f);
            pLevel.playSound(null, slime.blockPosition(), SoundEvents.LAVA_POP,
                    SoundSource.NEUTRAL, 1.25f, 0.65f);
            pLevel.playSound(null, slime.blockPosition(), SoundEvents.MAGMA_CUBE_JUMP,
                    SoundSource.NEUTRAL, 1.5f, 0.65f);

            MagmaCube magmaCube = EntityType.MAGMA_CUBE.create(pLevel);

            CompoundTag serializeNBT = slime.saveWithoutId(new CompoundTag());
            serializeNBT.remove("UUID");

            magmaCube.deserializeNBT(serializeNBT);
            magmaCube.setPos(slime.getPosition(0));
            pLevel.addFreshEntity(magmaCube);
            slime.discard();

        } else if (!pTarget.fireImmune() && !pAttacker.getCooldowns().isOnCooldown(pStack.getItem()) && (!pTarget.onGround() || !pAttacker.onGround()) && pTarget.isAlive()) {
            playExplosionParticles(pLevel, pTarget.position());
            playExplosionSound(pAttacker, pLevel);
            pTarget.setSecondsOnFire(10);
            pTarget.hurt(pTarget.damageSources().explosion(pAttacker, pTarget), (float) (6 + pAttacker.getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.1));
            pAttacker.getCooldowns().addCooldown(this, 30);
        } else if (!pTarget.fireImmune()) {
            pTarget.setSecondsOnFire(10);
            pTarget.hurt(pTarget.damageSources().onFire(), 1);
        }
        return super.onLeftClickEntity(pStack, pAttacker, pTarget);
    }

    private void playExplosionSound(LivingEntity pAttacker, Level pLevel) {
        //TEMP UNTIL WE GOT PROPER EXPLOSION SOUND FOR EXPLODING SWORD SOUNDS
        pLevel.playSound(null, pAttacker.getX(), pAttacker.getY(), pAttacker.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 0.1F, 0.5F + pLevel.random.nextFloat() * 0.3F );
        pLevel.playSound(null, pAttacker.getX(), pAttacker.getY(), pAttacker.getZ(), SoundEvents.ENDER_DRAGON_HURT, SoundSource.PLAYERS, 0.2F, 0.4F + pLevel.random.nextFloat() * 0.3F );
        pLevel.playSound(null, pAttacker.getX(), pAttacker.getY(), pAttacker.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 0.2F, 0.4F + pLevel.random.nextFloat() * 0.3F );
        pLevel.playSound(null, pAttacker.getX(), pAttacker.getY(), pAttacker.getZ(), SoundEvents.LAVA_POP, SoundSource.PLAYERS, 1.25F, 0.4F + pLevel.random.nextFloat() * 0.3F );
}
    private void playExplosionParticles(Level pLevel, Vec3 pPos) {
        if (pLevel.isClientSide) {
            for (int i = 0; i < 40; i++) {
                Vec3 motion = offsetRandomly(Vec3.ZERO, pLevel.random, .125f);
                pLevel.addParticle(ParticleTypes.FLAME, pPos.x, pPos.y, pPos.z, motion.x, motion.y, motion.z);
                pLevel.addParticle(ParticleTypes.LAVA, pPos.x, pPos.y, pPos.z, motion.x, motion.y, motion.z);
                Vec3 circle = motion.multiply(1, 0, 1)
                        .normalize()
                        .scale(.6f);
                pLevel.addParticle(ParticleTypes.LAVA, circle.x, pPos.y, circle.z, 0, -0.125, 0);
                pLevel.addParticle(ParticleTypes.LARGE_SMOKE, circle.x, pPos.y, circle.z, 0, -0.125, 0);
                pLevel.addParticle(ParticleTypes.SMOKE, circle.x, pPos.y, circle.z, 0, -0.125, 0);
            }
        }
    }

    public static Vec3 offsetRandomly(Vec3 vec, RandomSource r, float radius) {
        return new Vec3(vec.x + (r.nextFloat() - .5f) * 2 * radius, vec.y + (r.nextFloat() - .5f) * 2 * radius,
                vec.z + (r.nextFloat() - .5f) * 2 * radius);
    }
}
