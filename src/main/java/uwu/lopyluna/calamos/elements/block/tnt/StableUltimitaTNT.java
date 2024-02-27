package uwu.lopyluna.calamos.elements.block.tnt;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

@SuppressWarnings({"deprecation", "all"})
public class StableUltimitaTNT extends Block {

    public StableUltimitaTNT(Properties properties) {
        super(properties);
    }

    public static float blastRadius = 20.0F;
    public static boolean haveFire = false;

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pPlayer.getMainHandItem().getItem() == Items.FLINT_AND_STEEL) {
            explode(pLevel, pPos);
            return InteractionResult.CONSUME;
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
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
        if (!pLevel.isClientSide) {
            Vec3 vec3 = pPos.getCenter();
            pLevel.removeBlock(pPos, false);
            pLevel.explode(null, pLevel.damageSources().badRespawnPointExplosion(vec3), null, vec3, blastRadius, haveFire, Level.ExplosionInteraction.TNT);
            pLevel.setBlock(pPos, Blocks.OBSIDIAN.defaultBlockState(), 3);
        }
    }
}