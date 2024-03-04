package uwu.lopyluna.calamos.utilities;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.BlockEvent;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class BlockHelper {
    
    public static BlockState setZeroAge(BlockState blockState) {
        if (blockState.hasProperty(BlockStateProperties.AGE_1))
            return blockState.setValue(BlockStateProperties.AGE_1, 0);
        if (blockState.hasProperty(BlockStateProperties.AGE_2))
            return blockState.setValue(BlockStateProperties.AGE_2, 0);
        if (blockState.hasProperty(BlockStateProperties.AGE_3))
            return blockState.setValue(BlockStateProperties.AGE_3, 0);
        if (blockState.hasProperty(BlockStateProperties.AGE_5))
            return blockState.setValue(BlockStateProperties.AGE_5, 0);
        if (blockState.hasProperty(BlockStateProperties.AGE_7))
            return blockState.setValue(BlockStateProperties.AGE_7, 0);
        if (blockState.hasProperty(BlockStateProperties.AGE_15))
            return blockState.setValue(BlockStateProperties.AGE_15, 0);
        if (blockState.hasProperty(BlockStateProperties.AGE_25))
            return blockState.setValue(BlockStateProperties.AGE_25, 0);
        if (blockState.hasProperty(BlockStateProperties.LEVEL_HONEY))
            return blockState.setValue(BlockStateProperties.LEVEL_HONEY, 0);
        if (blockState.hasProperty(BlockStateProperties.HATCH))
            return blockState.setValue(BlockStateProperties.HATCH, 0);
        if (blockState.hasProperty(BlockStateProperties.STAGE))
            return blockState.setValue(BlockStateProperties.STAGE, 0);
        if (blockState.is(BlockTags.CAULDRONS))
            return Blocks.CAULDRON.defaultBlockState();
        if (blockState.hasProperty(BlockStateProperties.LEVEL_COMPOSTER))
            return blockState.setValue(BlockStateProperties.LEVEL_COMPOSTER, 0);
        if (blockState.hasProperty(BlockStateProperties.EXTENDED))
            return blockState.setValue(BlockStateProperties.EXTENDED, false);
        return blockState;
    }
    
    public static int findAndRemoveInInventory(BlockState block, Player player, int amount) {
        int amountFound = 0;
        Item required = getRequiredItem(block).getItem();
        
        boolean needsTwo = block.hasProperty(BlockStateProperties.SLAB_TYPE)
                && block.getValue(BlockStateProperties.SLAB_TYPE) == SlabType.DOUBLE;
        
        if (needsTwo)
            amount *= 2;
        
        if (block.hasProperty(BlockStateProperties.EGGS))
            amount *= block.getValue(BlockStateProperties.EGGS);
        
        if (block.hasProperty(BlockStateProperties.PICKLES))
            amount *= block.getValue(BlockStateProperties.PICKLES);
        
        {
            // Try held Item first
            int preferredSlot = player.getInventory().selected;
            ItemStack itemstack = player.getInventory()
                    .getItem(preferredSlot);
            int count = itemstack.getCount();
            if (itemstack.getItem() == required && count > 0) {
                int taken = Math.min(count, amount - amountFound);
                player.getInventory()
                        .setItem(preferredSlot, new ItemStack(itemstack.getItem(), count - taken));
                amountFound += taken;
            }
        }
        
        // Search inventory
        for (int i = 0; i < player.getInventory()
                .getContainerSize(); ++i) {
            if (amountFound == amount)
                break;
            
            ItemStack itemstack = player.getInventory()
                    .getItem(i);
            int count = itemstack.getCount();
            if (itemstack.getItem() == required && count > 0) {
                int taken = Math.min(count, amount - amountFound);
                player.getInventory()
                        .setItem(i, new ItemStack(itemstack.getItem(), count - taken));
                amountFound += taken;
            }
        }
        
        if (needsTwo) {
            // Give back 1 if uneven amount was removed
            if (amountFound % 2 != 0)
                player.getInventory()
                        .add(new ItemStack(required));
            amountFound /= 2;
        }
        
        return amountFound;
    }
    
    public static ItemStack getRequiredItem(BlockState state) {
        ItemStack itemStack = new ItemStack(state.getBlock());
        Item item = itemStack.getItem();
        if (item == Items.FARMLAND || item == Items.DIRT_PATH)
            itemStack = new ItemStack(Items.DIRT);
        return itemStack;
    }
    
    public static void destroyBlock(Level world, BlockPos pos, float effectChance) {
        destroyBlock(world, pos, effectChance, stack -> Block.popResource(world, pos, stack));
    }
    
    public static void destroyBlock(Level world, BlockPos pos, float effectChance,
                                    Consumer<ItemStack> droppedItemCallback) {
        destroyBlockAs(world, pos, null, ItemStack.EMPTY, effectChance, droppedItemCallback);
    }
    
    public static void destroyBlockAs(Level world, BlockPos pos, @Nullable Player player, ItemStack usedTool,
                                      float effectChance, Consumer<ItemStack> droppedItemCallback) {
        FluidState fluidState = world.getFluidState(pos);
        BlockState state = world.getBlockState(pos);
        
        if (world.random.nextFloat() < effectChance)
            world.levelEvent(2001, pos, Block.getId(state));
        BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
        
        if (player != null) {
            BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(world, pos, state, player);
            NeoForge.EVENT_BUS.post(event);
            if (event.isCanceled())
                return;
            
            if (event.getExpToDrop() > 0 && world instanceof ServerLevel)
                state.getBlock()
                        .popExperience((ServerLevel) world, pos, event.getExpToDrop());
            
            usedTool.mineBlock(world, state, pos, player);
            player.awardStat(Stats.BLOCK_MINED.get(state.getBlock()));
        }
        
        if (world instanceof ServerLevel && world.getGameRules()
                .getBoolean(GameRules.RULE_DOBLOCKDROPS) && !world.restoringBlockSnapshots
                && (player == null || !player.isCreative())) {
            for (ItemStack itemStack : Block.getDrops(state, (ServerLevel) world, pos, blockEntity, player, usedTool))
                droppedItemCallback.accept(itemStack);
            
            // Simulating IceBlock#playerDestroy. Not calling method directly as it would drop item
            // entities as a side-effect
            if (state.getBlock() instanceof IceBlock && usedTool.getEnchantmentLevel(Enchantments.SILK_TOUCH) == 0) {
                if (world.dimensionType()
                        .ultraWarm())
                    return;
                
                BlockState blockstate = world.getBlockState(pos.below());
                if (blockstate.blocksMotion() || blockstate.liquid())
                    world.setBlockAndUpdate(pos, Blocks.WATER.defaultBlockState());
                return;
            }
            
            state.spawnAfterBreak((ServerLevel) world, pos, ItemStack.EMPTY, true);
        }
        
        world.setBlockAndUpdate(pos, fluidState.createLegacyBlock());
    }
}
