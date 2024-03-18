package uwu.lopyluna.calamos.elements.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import uwu.lopyluna.calamos.elements.ModBlocks;
import uwu.lopyluna.calamos.elements.ModMenuType;

import java.util.Optional;

public class HallowWorkbenchMenu extends RecipeBookMenu<CraftingContainer>{
    public static final int RESULT_SLOT = 0;
    private static final int CRAFT_SLOT_START = 1;
    private static final int CRAFT_SLOT_END = 10;
    private static final int INV_SLOT_START = 10;
    private static final int INV_SLOT_END = 37;
    private static final int USE_ROW_SLOT_START = 37;
    private static final int USE_ROW_SLOT_END = 62;
    private final CraftingContainer craftSlots = new TransientCraftingContainer(this, 5, 5);
    private final ResultContainer resultSlots = new ResultContainer();
    private final ContainerLevelAccess access;
    private final Player player;

    public HallowWorkbenchMenu(int pContainerId, Inventory pPlayerInventory, FriendlyByteBuf friendlyByteBuf) {
        this(pContainerId, pPlayerInventory, ContainerLevelAccess.NULL);
    }

    public HallowWorkbenchMenu(int pContainerId, Inventory pPlayerInventory, ContainerLevelAccess pAccess) {
        super(ModMenuType.HALLOW_WORKBENCH_MENU.get(), pContainerId);
        this.access = pAccess;
        this.player = pPlayerInventory.player;
        this.addSlot(new ResultSlot(pPlayerInventory.player, this.craftSlots, this.resultSlots, 0, 142, 35));

        for(int i = 0; i < 5; ++i) {
            for(int j = 0; j < 5; ++j) {
                this.addSlot(new Slot(this.craftSlots, j + i * 3, 12 + j * 18, -1 + i * 18));
            }
        }

        for(int k = 0; k < 3; ++k) {
            for(int i1 = 0; i1 < 9; ++i1) {
                this.addSlot(new Slot(pPlayerInventory, i1 + k * 9 + 9, 8 + i1 * 18, 102 + k * 18));
            }
        }

        for(int l = 0; l < 9; ++l) {
            this.addSlot(new Slot(pPlayerInventory, l, 8 + l * 18, 160));
        }
    }

    protected static void slotChangedCraftingGrid(
            AbstractContainerMenu pMenu, Level pLevel, Player pPlayer, CraftingContainer pContainer, ResultContainer pResult
    ) {
        if (!pLevel.isClientSide) {
            ServerPlayer serverplayer = (ServerPlayer)pPlayer;
            ItemStack itemstack = ItemStack.EMPTY;
            Optional<RecipeHolder<CraftingRecipe>> optional = pLevel.getServer().getRecipeManager().getRecipeFor(RecipeType.CRAFTING, pContainer, pLevel);
            if (optional.isPresent()) {
                RecipeHolder<CraftingRecipe> recipeholder = optional.get();
                CraftingRecipe craftingrecipe = recipeholder.value();
                if (pResult.setRecipeUsed(pLevel, serverplayer, recipeholder)) {
                    ItemStack itemstack1 = craftingrecipe.assemble(pContainer, pLevel.registryAccess());
                    if (itemstack1.isItemEnabled(pLevel.enabledFeatures())) {
                        itemstack = itemstack1;
                    }
                }
            }

            pResult.setItem(0, itemstack);
            pMenu.setRemoteSlot(0, itemstack);
            serverplayer.connection.send(new ClientboundContainerSetSlotPacket(pMenu.containerId, pMenu.incrementStateId(), 0, itemstack));
        }
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    @Override
    public void slotsChanged(Container pInventory) {
        this.access.execute((p_39386_, p_39387_) -> slotChangedCraftingGrid(this, p_39386_, this.player, this.craftSlots, this.resultSlots));
    }

    @Override
    public void fillCraftSlotsStackedContents(StackedContents pItemHelper) {
        this.craftSlots.fillStackedContents(pItemHelper);
    }

    @Override
    public void clearCraftingContent() {
        this.craftSlots.clearContent();
        this.resultSlots.clearContent();
    }

    @Override
    public boolean recipeMatches(RecipeHolder<? extends Recipe<CraftingContainer>> pRecipe) {
        return pRecipe.value().matches(this.craftSlots, this.player.level());
    }

    /**
     * Called when the container is closed.
     */
    @Override
    public void removed(Player pPlayer) {
        super.removed(pPlayer);
        this.access.execute((p_39371_, p_39372_) -> this.clearContainer(pPlayer, this.craftSlots));
    }

    /**
     * Determines whether supplied player can use this container
     */
    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(this.access, pPlayer, ModBlocks.HALLOW_WORKBENCH.get());
    }

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player inventory and the other inventory(s).
     */
    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(pIndex);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (pIndex == 0) {
                this.access.execute((p_39378_, p_39379_) -> itemstack1.getItem().onCraftedBy(itemstack1, p_39378_, pPlayer));
                if (!this.moveItemStackTo(itemstack1, 10, 46, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (pIndex >= 10 && pIndex < 62) {
                if (!this.moveItemStackTo(itemstack1, 1, 26, false)) {
                    if (pIndex < 37) {
                        if (!this.moveItemStackTo(itemstack1, 37, 62, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.moveItemStackTo(itemstack1, 10, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.moveItemStackTo(itemstack1, 10, 46, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(pPlayer, itemstack1);
            if (pIndex == 0) {
                pPlayer.drop(itemstack1, false);
            }
        }

        return itemstack;
    }

    /**
     * Called to determine if the current slot is valid for the stack merging (double-click) code. The stack passed in is null for the initial slot that was double-clicked.
     */
    @Override
    public boolean canTakeItemForPickAll(ItemStack pStack, Slot pSlot) {
        return pSlot.container != this.resultSlots && super.canTakeItemForPickAll(pStack, pSlot);
    }

    @Override
    public int getResultSlotIndex() {
        return 0;
    }

    @Override
    public int getGridWidth() {
        return this.craftSlots.getWidth();
    }

    @Override
    public int getGridHeight() {
        return this.craftSlots.getHeight();
    }

    @Override
    public int getSize() {
        return 26;
    }

    @Override
    public RecipeBookType getRecipeBookType() {
        return RecipeBookType.CRAFTING;
    }

    @Override
    public boolean shouldMoveToInventory(int pSlotIndex) {
        return pSlotIndex != this.getResultSlotIndex();
    }
}
