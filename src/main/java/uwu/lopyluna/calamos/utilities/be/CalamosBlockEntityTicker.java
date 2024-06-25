package uwu.lopyluna.calamos.utilities.be;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;

public class CalamosBlockEntityTicker<T extends BlockEntity> implements BlockEntityTicker<T> {
    
    @Override
    public void tick(Level level, BlockPos pos, BlockState state, T t) {
        if (!t.hasLevel())
            t.setLevel(level);
        ((CalamosBlockEntity) t).tick();
    }
}
