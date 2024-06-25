package uwu.lopyluna.calamos.elements.block.machina;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import uwu.lopyluna.calamos.elements.ModBlockEntities;
import uwu.lopyluna.calamos.elements.blockEntity.AntennaBlockEntity;
import uwu.lopyluna.calamos.utilities.be.CalamosBlockEntity;
import uwu.lopyluna.calamos.utilities.be.CalamosBlockEntityTicker;
import uwu.lopyluna.calamos.utilities.be.CalamosEntityBlock;
import uwu.lopyluna.calamos.utilities.be.ICalamosBE;

public class AntennaBlock extends CalamosEntityBlock implements ICalamosBE<AntennaBlockEntity> {
    public AntennaBlock(Properties properties) {
        super(properties);
    }
    
    @Override
    public Class<AntennaBlockEntity> getBlockEntityClass() {
        return AntennaBlockEntity.class;
    }
    
    @Override
    public BlockEntityType<? extends AntennaBlockEntity> getBlockEntityType() {
        return ModBlockEntities.MACHINA_ANTENNA.get();
    }
    
    @Override
    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }
}
