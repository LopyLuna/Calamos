package uwu.lopyluna.calamos.elements.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import uwu.lopyluna.calamos.elements.blockEntity.ModHangingBlockEntity;

public class ModHangingSignBlock extends WallHangingSignBlock {
    public ModHangingSignBlock(WoodType woodType, Properties properties) {
        super(woodType, properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ModHangingBlockEntity(pPos, pState);
    }
}
