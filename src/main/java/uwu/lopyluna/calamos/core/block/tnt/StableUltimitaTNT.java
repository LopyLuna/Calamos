package uwu.lopyluna.calamos.core.block.tnt;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"deprecation", "all"})
public class StableUltimitaTNT extends Block {
    public StableUltimitaTNT(Properties properties) {
        super(properties);
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (stack.is(Items.FLINT_AND_STEEL)) {
            explode(pLevel, pPos);
            return ItemInteractionResult.CONSUME;
        }
        return super.useItemOn(stack, pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return false;
    }

    @Override
    public void onBlockExploded(BlockState state, Level level, BlockPos pos, Explosion explosion) {
        super.onBlockExploded(state, level, pos, explosion);
        explode(level, pos);
    }

    @Override
    public void playerDestroy(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState, @org.jetbrains.annotations.Nullable BlockEntity pBlockEntity, ItemStack pTool) {
        pPlayer.awardStat(Stats.BLOCK_MINED.get(this));
        pPlayer.causeFoodExhaustion(0.05F);
        explode(pLevel, pPos);
    }

    private static void explode(Level pLevel, BlockPos pPos) {
        float blastRadius = 50.0F;
        boolean haveFire = false;
        List<Sphere> spheres = generateSphere(0, 0, 0, 3, 5, true);
        if (pLevel.isClientSide) {
            pLevel.playLocalSound(pPos, SoundEvents.GENERIC_EXPLODE.value(), SoundSource.BLOCKS, (blastRadius + blastRadius * 0.25F) * 0.75F, (0.7F + (pLevel.random.nextFloat() - pLevel.random.nextFloat()) * 0.2F) * 0.7F, false);
            pLevel.playLocalSound(pPos, SoundEvents.WIND_CHARGE_BURST.value(), SoundSource.BLOCKS, (blastRadius + blastRadius * 0.25F) * 0.75F, (0.7F + (pLevel.random.nextFloat() - pLevel.random.nextFloat()) * 0.2F) * 0.7F, false);
            pLevel.playLocalSound(pPos, SoundEvents.GENERIC_EXPLODE.value(), SoundSource.BLOCKS, (blastRadius + blastRadius * 0.25F) * 0.75F, (0.7F + (pLevel.random.nextFloat() - pLevel.random.nextFloat()) * 0.2F) * 0.7F, true);
            pLevel.playLocalSound(pPos, SoundEvents.WIND_CHARGE_BURST.value(), SoundSource.BLOCKS, (blastRadius + blastRadius * 0.25F) * 0.75F, (0.7F + (pLevel.random.nextFloat() - pLevel.random.nextFloat()) * 0.2F) * 0.7F, true);
        }

        if (!pLevel.isClientSide) {
            Vec3 vec3 = pPos.getCenter();
            pLevel.removeBlock(pPos, false);
            pLevel.explode(null, pLevel.damageSources().badRespawnPointExplosion(vec3), null, vec3, blastRadius + pLevel.random.nextFloat(), haveFire, Level.ExplosionInteraction.BLOCK);

            for (Sphere sphere : spheres) {
                float xV = sphere.x;
                float yV = sphere.y;
                float zV = sphere.z;
                float x = pPos.getX() + xV;
                float y = pPos.getY() + yV;
                float z = pPos.getZ() + zV;
                Vec3 spherePos = pPos.getCenter().add(xV, yV, zV);
                BlockPos sphereBlockPos = pPos.offset((int) xV, (int) yV, (int) zV);

                if (!pLevel.getBlockState(sphereBlockPos).is(BlockTags.WITHER_IMMUNE)) {

                    pLevel.explode(
                            null,
                            pLevel.damageSources().badRespawnPointExplosion(spherePos),
                            null,
                            x,
                            y,
                            z,
                            blastRadius + pLevel.random.nextFloat(),
                            haveFire,
                            Level.ExplosionInteraction.BLOCK,
                            ParticleTypes.GUST,
                            ParticleTypes.GUST_EMITTER_LARGE,
                            SoundEvents.WIND_CHARGE_BURST
                            );
                    pLevel.addParticle(ParticleTypes.EXPLOSION_EMITTER, x, y, z, 1.0, 0.0, 0.0);
                }
            }
        }
    }


    // CenterXYZ is pos of sphere | Radius of Sphere | Intensity 5 of Sphere | Weather if it be Hollow Sphere
    public static List<Sphere> generateSphere(int centerX, int centerY, int centerZ, int radius, int intensity, boolean hollow) {
        List<Sphere> sphere = new ArrayList<>();
        int maxIntensity = intensity * intensity;
        int outerRadiusSquared = radius * radius;
        int innerRadiusSquared = (radius - 1) * (radius - 1); // For hollow sphere

        for (int x = centerX - radius; x <= centerX + radius; x++) {
            int xSquared = (x - centerX) * (x - centerX);
            for (int y = centerY - radius; y <= centerY + radius; y++) {
                int ySquared = (y - centerY) * (y - centerY);
                for (int z = centerZ - radius; z <= centerZ + radius; z++) {
                    int zSquared = (z - centerZ) * (z - centerZ);
                    int distanceSquared = xSquared + ySquared + zSquared;
                    if (hollow && distanceSquared >= innerRadiusSquared && distanceSquared <= outerRadiusSquared) {
                        sphere.add(new Sphere(x, y, z));
                    } else if (!hollow && distanceSquared <= maxIntensity) {
                        sphere.add(new Sphere(x, y, z));
                    }
                }
            }
        }
        return sphere;
    }

    static class Sphere {
        int x, y, z;

        Sphere(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
