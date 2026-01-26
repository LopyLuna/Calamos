package uwu.lopyluna.calamos.core.block.machina;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

public class MachinaHeartstoneBlock extends MachinaBlock {

    public MachinaHeartstoneBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!pLevel.isAreaLoaded(pPos, 1)) return;
        if (!pLevel.getBlockState(pPos).getValue(ACTIVE)) {
            assert pLevel.getDragonFight() != null;
            if (pLevel.getDragonFight().hasPreviouslyKilledDragon()) {
                var newState = pState.setValue(ACTIVE, true);
                pLevel.setBlockAndUpdate(pPos, newState);
            }
        }
    }


}
