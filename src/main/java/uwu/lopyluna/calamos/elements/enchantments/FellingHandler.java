package uwu.lopyluna.calamos.elements.enchantments;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import uwu.lopyluna.calamos.datagen.ModEnchantmentProvider;
import uwu.lopyluna.calamos.utilities.ModUtils;

public class FellingHandler {

    private static boolean deforesting = false; // required as to not run into "recursions" over forge events on tree cutting

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
        int felling = ModUtils.getEnchantLevel(heldItemMainhand, event.getLevel(), ModEnchantmentProvider.FELLING);
        if (felling == 0)
            return;
        destroyTree((Level) event.getLevel(), event.getState(), event.getPos(), event.getPlayer());
    }
}
