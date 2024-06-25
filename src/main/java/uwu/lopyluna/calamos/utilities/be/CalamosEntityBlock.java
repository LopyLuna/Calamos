package uwu.lopyluna.calamos.utilities.be;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class CalamosEntityBlock extends BaseEntityBlock {
    public static final MapCodec<? extends CalamosEntityBlock> CODEC = simpleCodec(CalamosEntityBlock::new);
    
    protected CalamosEntityBlock(Properties pProperties) {
        super(pProperties);
    }
    
    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
    
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return null;
    }
}
