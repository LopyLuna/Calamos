package uwu.lopyluna.calamos.elements.enchantments.axe;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.level.BlockEvent;
import org.jetbrains.annotations.NotNull;
import uwu.lopyluna.calamos.elements.ModEnchantments;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FellingEnchantment extends Enchantment {
    private static boolean deforesting = false; // required as to not run into "recursions" over forge events on tree cutting
    public FellingEnchantment(Rarity pRarity, EnchantmentCategory category, EquipmentSlot... pApplicableSlots) {
        super(pRarity, category, pApplicableSlots);
    }
    
    @Override
    public int getMinCost(int pEnchantmentLevel) {
        return pEnchantmentLevel * 25;
    }
    
    @Override
    public int getMaxCost(int pEnchantmentLevel) {
        return this.getMinCost(pEnchantmentLevel) + 50;
    }
    public boolean canEnchant(@NotNull ItemStack pStack) {
        super.canEnchant(pStack);
        return pStack.getItem() instanceof AxeItem;
    }
    public boolean isTreasureOnly() {
        return false;
    }
    public int getMaxLevel() {
        return 1;
    }
    
    public static void destroyTree(Level iWorld, BlockState state, BlockPos pos, Player player) {
        
        if (deforesting || player.isCrouching() ||!(state.is(BlockTags.LOGS) || !(iWorld instanceof  Level)))
            return;
        Level worldIn = iWorld;
        Vec3 vec = player.getLookAngle();
        
        deforesting = true;
        TreeCutter.findTree(worldIn, pos).destroyBlocks(worldIn, player, (dropPos, item) -> dropItemFromCutTree(worldIn, pos, vec, dropPos, item));
        deforesting = false;
    }
    public static void dropItemFromCutTree(Level world, BlockPos breakingPos, Vec3 fallDirection, BlockPos pos, ItemStack stack) {
        float distance = (float) Math.sqrt(pos.distSqr(breakingPos));
        Vec3 dropPos = getCenterOf(pos);
        ItemEntity entity = new ItemEntity(world, dropPos.x, dropPos.y, dropPos.z, stack);
        entity.setDeltaMovement(fallDirection.scale(distance / 20f));
        world.addFreshEntity(entity);
    }
    
    public static Vec3 getCenterOf(Vec3i pos) {
        if (pos.equals(Vec3i.ZERO))
            return new Vec3(.5, .5, .5);
        return Vec3.atLowerCornerOf(pos)
                .add(.5f, .5f, .5f);
    }
    
    @SubscribeEvent
    public static void onBlockDestroyed(BlockEvent.BreakEvent event) {
        ItemStack heldItemMainhand = event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND);
        if (heldItemMainhand.getEnchantmentLevel(ModEnchantments.FELLING.get()) == 0)
            return;
        FellingEnchantment.destroyTree((Level) event.getLevel(), event.getState(), event.getPos(), event.getPlayer());
    }
}
