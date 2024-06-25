package uwu.lopyluna.calamos.utilities.be;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.NeoForge;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class CalamosBlockEntity extends SyncedBlockEntity implements IPartialSafeNBT, IInteractionChecker {
    private boolean initialized = false;
    private boolean firstNbtRead = true;
    protected int lazyTickRate;
    protected int lazyTickCounter;
    private boolean chunkUnloaded;
    // Used for simulating this BE in a client-only setting
    private boolean virtualMode;
    public CalamosBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
    public void initialize() {
        if (firstNbtRead) {
            firstNbtRead = false;
        }
        
        lazyTick();
    }
    public void tick() {
        if (!initialized && hasLevel()) {
            initialize();
            initialized = true;
        }
        
        if (lazyTickCounter-- <= 0) {
            lazyTickCounter = lazyTickRate;
            lazyTick();
        }
    }
    public void lazyTick() {}
    
    protected void write(CompoundTag tag, boolean clientPacket) {
        super.saveAdditional(tag);
    }
    
    @Override
    public void writeSafe(CompoundTag tag) {
        super.saveAdditional(tag);
    }
    @Nullable
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
    
    public CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }
    
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.load(pkt.getTag());
    }
    
    protected void inventoryChanged() {
        super.setChanged();
        if (this.level != null) {
            this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);
        }
        
    }
    protected void read(CompoundTag tag, boolean clientPacket) {
        if (firstNbtRead) {
            firstNbtRead = false;
        }
        super.load(tag);
    }
    @Override
    public void load(CompoundTag tag) {
        read(tag, false);
    }
    
    @Override
    public void onChunkUnloaded() {
        super.onChunkUnloaded();
        chunkUnloaded = true;
    }
    
    @Override
    public void setRemoved() {
        super.setRemoved();
        if (!chunkUnloaded)
            remove();
        invalidate();
    }
    
    public void invalidate() {
    
    }
    
    public void remove() {}
    
    public void destroy() {
    
    }
    
    @Override
    public void saveAdditional(CompoundTag tag) {
        write(tag, false);
    }
    
    @Override
    public final void readClient(CompoundTag tag) {
        read(tag, true);
    }
    
    @Override
    public final CompoundTag writeClient(CompoundTag tag) {
        write(tag, true);
        return tag;
    }
    
    public void setLazyTickRate(int slowTickRate) {
        this.lazyTickRate = slowTickRate;
        this.lazyTickCounter = slowTickRate;
    }
    
    public void markVirtual() {
        virtualMode = true;
    }
    
    public boolean isVirtual() {
        return virtualMode;
    }
    
    public boolean isChunkUnloaded() {
        return chunkUnloaded;
    }
    @Override
    public boolean canPlayerUse(Player player) {
        if (level == null || level.getBlockEntity(worldPosition) != this)
            return false;
        return player.distanceToSqr(worldPosition.getX() + 0.5D, worldPosition.getY() + 0.5D,
                worldPosition.getZ() + 0.5D) <= 64.0D;
    }
    
    public void sendToMenu(FriendlyByteBuf buffer) {
        buffer.writeBlockPos(getBlockPos());
        buffer.writeNbt(getUpdateTag());
    }
    
    @SuppressWarnings("deprecation")
    public void refreshBlockState() {
        setBlockState(getLevel().getBlockState(getBlockPos()));
    }
}
