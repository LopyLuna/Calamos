package uwu.lopyluna.calamos.elements.items.tool;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import uwu.lopyluna.calamos.utilities.ModUtils;
import uwu.lopyluna.calamos.utilities.RenderingUtils;

public class CalamosReaper extends HoeItem implements CalamosTool{
    protected int harvestRadius;
    private final boolean twoHanded;
    public CalamosReaper(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, int harvestRadius, boolean twoHanded, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        this.harvestRadius = harvestRadius;
        this.twoHanded = twoHanded;
    }
    
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (pPlayer.isShiftKeyDown()) {
            pPlayer.getCooldowns().addCooldown(this, ModUtils.secondsToTicks(3));
            return harvest(this.harvestRadius, pLevel, pPlayer, pUsedHand);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
    @Override
    public void idleHeldPose(HumanoidModel<LivingEntity> model, LivingEntity entity, boolean offHand, float pAgeInTicks) {
        if (isTwoHanded()) {
            RenderingUtils.twoHanded(model, entity, offHand, pAgeInTicks);
        }
    }
    @Override
    public void swingPose(HumanoidModel<LivingEntity> model, LivingEntity entity, boolean offHand, float pAgeInTicks) {
        if (isTwoHanded()) {
            RenderingUtils.reaperSwing(model, entity, offHand, pAgeInTicks);
        }
    }
    
    
    
    public static InteractionResultHolder<ItemStack> harvest(int harvestRadius, Level world, Player player, InteractionHand hand) {
        BlockPos pos = player.getOnPos();
        BlockPos blockPos = new BlockPos(ModUtils.roundThat((float) pos.getX()), ModUtils.roundThat((float) pos.getZ()), ModUtils.roundThat((float) pos.getZ()));
        ItemStack item = player.getItemBySlot(EquipmentSlot.MAINHAND);
        
        int lvl = EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, player);
        int radius = (int) (Math.floor(lvl / 2.0) + harvestRadius);
        boolean harvestInCircle = (harvestRadius + lvl) % 2 == 0;
        
        for (int x = -radius; x <= radius; ++x) {
            for (int y = -1; y <= 1; ++y) {
                for (int z = -radius; z <= radius; ++z) {
                    BlockPos newBlockPos = new BlockPos(blockPos.getX() + x, blockPos.getY() + y, blockPos.getZ() + z);
                    if (harvestInCircle &&
                            ((y == -1 && newBlockPos.distManhattan(blockPos.above()) > radius) ||
                                    (y == 0 && newBlockPos.distManhattan(blockPos) > radius) ||
                                    (y == 1 && newBlockPos.distManhattan(blockPos.below()) > radius))) {
                        continue;
                    }
                    BlockState blockState = world.getBlockState(newBlockPos);
                    Block block = blockState.getBlock();
                    if (block instanceof CropBlock && ((CropBlock) block).isMaxAge(blockState)) {
                        CropBlock cropsBlock = (CropBlock) block;
                        Block.dropResources(blockState, world, newBlockPos);
                        item.hurtAndBreak(1, player, (stack) -> stack.broadcastBreakEvent(hand));
                        world.setBlockAndUpdate(newBlockPos, cropsBlock.getStateForAge(0));
                    }
                }
            }
        }
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
    
    @Override
    public boolean isTwoHanded() {
        return twoHanded;
    }
    
    @Override
    public boolean isBeingUsed() {
        return false;
    }
    
    @Override
    public boolean hasUsePose() {
        return false;
    }
    
    @Override
    public boolean hasIdleHeldPose() {
        return isTwoHanded();
    }
    
    @Override
    public boolean hasSwingPose() {
        return true;
    }
}
