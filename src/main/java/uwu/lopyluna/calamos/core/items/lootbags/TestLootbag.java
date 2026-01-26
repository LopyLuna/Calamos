package uwu.lopyluna.calamos.core.items.lootbags;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import uwu.lopyluna.calamos.elements.ModItems;
import uwu.lopyluna.calamos.utilities.AnimationHandler;
import uwu.lopyluna.calamos.utilities.WeightedRandom;

public class TestLootbag extends LootBag {
    public TestLootbag(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void init(WeightedRandom<ItemLike> weightedRandom) {
        weightedRandom
                .add(ModItems.METEORITE_INGOT, 0.8F)
                .add(ModItems.METEORITE_SWORD, 0.1F)
                .add(ModItems.METEORITE_REAPER, 0.1F);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (pLevel instanceof ServerLevel) // EXAMPLE ANIMATION
            AnimationHandler.playAnimationServer(pPlayer, "hand", true);
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public int[] rolls() {
        return new int[]{1, 3};
    }

}
