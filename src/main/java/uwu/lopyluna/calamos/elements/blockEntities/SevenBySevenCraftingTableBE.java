package uwu.lopyluna.calamos.elements.blockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import uwu.lopyluna.calamos.elements.ModBlockEntities;

public class SevenBySevenCraftingTableBE extends BlockEntity implements MenuProvider {
    public SevenBySevenCraftingTableBE(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.SEVEN_BY_SEVEN_CRAFTING_TABLE_BE.get(), pPos, pBlockState);
    }

    @Override
    public Component getDisplayName() {
        return null;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return null;
    }
}
