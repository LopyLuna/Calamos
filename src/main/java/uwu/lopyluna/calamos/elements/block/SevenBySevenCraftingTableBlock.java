package uwu.lopyluna.calamos.elements.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class SevenBySevenCraftingTableBlock extends CraftingTableBlock {
    /*
    public static final MapCodec<SevenBySevenCraftingTableBlock> CODEC = simpleCodec(SevenBySevenCraftingTableBlock::new);
    private static final Component CONTAINER_TITLE = Component.translatable("container.crafting");

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

     */

    public SevenBySevenCraftingTableBlock(Properties p_49795_) {
        super(p_49795_);
    }

    /*
    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pLevel.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            pPlayer.openMenu(pState.getMenuProvider(pLevel, pPos));
            return InteractionResult.CONSUME;
        }
    }

    @Nullable
    @Override
    public MenuProvider getMenuProvider(BlockState pState, Level pLevel, BlockPos pPos) {
        return new SimpleMenuProvider(
                (pContainerId, pPlayerInventory, pPlayer) -> new CraftingMenu(pContainerId, pPlayerInventory, ContainerLevelAccess.create(pLevel, pPos)), CONTAINER_TITLE
        );
    }
     */
}
