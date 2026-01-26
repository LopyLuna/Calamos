package uwu.lopyluna.calamos.core.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import uwu.lopyluna.calamos.core.menu.HallowWorkbenchMenu;

public class HallowWorkbenchBlock extends Block {
    private static final Component CONTAINER_TITLE = Component.translatable("container.hallow_workbench");

    public HallowWorkbenchBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult p_316850_) {
        if (pLevel.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            pPlayer.openMenu(pState.getMenuProvider(pLevel, pPos));
            return InteractionResult.CONSUME;
        }
    }

    @Override
    public MenuProvider getMenuProvider(BlockState pState, Level pLevel, BlockPos pPos) {
        return new SimpleMenuProvider(
                (id, inventory, player) -> new HallowWorkbenchMenu(id, inventory, ContainerLevelAccess.create(pLevel, pPos)), CONTAINER_TITLE
        );
    }
}
