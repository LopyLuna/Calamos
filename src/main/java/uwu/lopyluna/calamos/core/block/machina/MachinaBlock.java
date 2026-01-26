package uwu.lopyluna.calamos.core.block.machina;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class MachinaBlock extends Block {
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    public MachinaBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(ACTIVE, false));
    }
    
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(ACTIVE, false);
    }
    
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(ACTIVE);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (isActive(state)) {
            for (Direction e : Direction.values()) {
                this.activate(level, pos.relative(e));
            }
        }
    }

    private void activate(Level level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        if (state.getBlock() instanceof MachinaBlock && !isActive(state) && !state.getValue(ACTIVE)) {
            level.setBlockAndUpdate(pos, state.setValue(ACTIVE, true));
            level.scheduleTick(pos, state.getBlock(), 2 + level.getRandom().nextInt(5));
        }
    }

    boolean isActive(BlockState state) {
        return state.hasProperty(ACTIVE) && state.getValue(ACTIVE);
    }
}
