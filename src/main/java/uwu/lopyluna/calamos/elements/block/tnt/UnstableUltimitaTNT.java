package uwu.lopyluna.calamos.elements.block.tnt;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
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

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"deprecation", "all"})
public class UnstableUltimitaTNT extends Block {

    public UnstableUltimitaTNT(Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        Item hand1 = pPlayer.getMainHandItem().getItem();
        Item hand2 = pPlayer.getOffhandItem().getItem();
        Item useItem = Items.FLINT_AND_STEEL;
        if (hand1 == useItem || hand2 == useItem) {
            explode(pLevel, pPos);
            return InteractionResult.CONSUME;
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Override
    public void onCaughtFire(BlockState pState, Level pLevel, BlockPos pPos, @Nullable net.minecraft.core.Direction face, @Nullable LivingEntity pEntity) {
        explode(pLevel, pPos);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public void onBlockExploded(BlockState state, Level level, BlockPos pos, Explosion explosion) {
        super.onBlockExploded(state, level, pos, explosion);
        explode(level, pos);
    }

    @Override
    public void playerDestroy(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState, @org.jetbrains.annotations.Nullable BlockEntity pBlockEntity, ItemStack pTool) {
        explode(pLevel, pPos);
    }

    private static void explode(Level pLevel, BlockPos pPos) {
        float blastRadius = 35.0F;
        boolean haveFire = true;
        List<StableUltimitaTNT.Sphere> spheres = generateSphere(0, 0, 0, 1, 3, true);
        if (pLevel.isClientSide) {
            pLevel.playLocalSound(pPos, SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, (blastRadius + blastRadius * 0.25F) * 0.75F, (0.7F + (pLevel.random.nextFloat() - pLevel.random.nextFloat()) * 0.2F) * 0.7F, false);
            pLevel.playLocalSound(pPos, SoundEvents.WIND_BURST, SoundSource.BLOCKS, (blastRadius + blastRadius * 0.25F) * 0.75F, (0.7F + (pLevel.random.nextFloat() - pLevel.random.nextFloat()) * 0.2F) * 0.7F, false);
            pLevel.playLocalSound(pPos, SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, (blastRadius + blastRadius * 0.25F) * 0.75F, (0.7F + (pLevel.random.nextFloat() - pLevel.random.nextFloat()) * 0.2F) * 0.7F, true);
            pLevel.playLocalSound(pPos, SoundEvents.WIND_BURST, SoundSource.BLOCKS, (blastRadius + blastRadius * 0.25F) * 0.75F, (0.7F + (pLevel.random.nextFloat() - pLevel.random.nextFloat()) * 0.2F) * 0.7F, true);
        }

        if (!pLevel.isClientSide) {
            Vec3 vec3 = pPos.getCenter();
            pLevel.removeBlock(pPos, false);
            pLevel.explode(null, pLevel.damageSources().badRespawnPointExplosion(vec3), null, vec3, blastRadius + pLevel.random.nextFloat(), haveFire, Level.ExplosionInteraction.BLOCK);

            for (StableUltimitaTNT.Sphere sphere : spheres) {
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
                            ParticleTypes.GUST_EMITTER,
                            SoundEvents.WIND_BURST
                    );
                    pLevel.addParticle(ParticleTypes.EXPLOSION_EMITTER, x, y, z, 1.0, 0.0, 0.0);
                }
            }
        }
    }


    // CenterXYZ is pos of sphere | Radius of Sphere | Intensity 5 of Sphere | Weather if it be Hollow Sphere
    public static List<StableUltimitaTNT.Sphere> generateSphere(int centerX, int centerY, int centerZ, int radius, int intensity, boolean hollow) {
        List<StableUltimitaTNT.Sphere> sphere = new ArrayList<>();
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
                        sphere.add(new StableUltimitaTNT.Sphere(x, y, z));
                    } else if (!hollow && distanceSquared <= maxIntensity) {
                        sphere.add(new StableUltimitaTNT.Sphere(x, y, z));
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