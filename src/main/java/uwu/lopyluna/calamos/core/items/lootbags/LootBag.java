package uwu.lopyluna.calamos.core.items.lootbags;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import uwu.lopyluna.calamos.utilities.WeightedRandom;

public abstract class LootBag extends Item {

    private final WeightedRandom<ItemLike> type = new WeightedRandom<>();

    public LootBag(Properties pProperties) {
        super(pProperties);
        this.init(this.type);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (pLevel.isClientSide) return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));

        int minRolls = rolls()[0];
        int maxRolls = rolls()[1];

        if (minRolls < 1 || maxRolls < 1) throw new IllegalArgumentException("Rolls must be a natural number.");

        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        itemStack.shrink(1);

        final int rolls = pPlayer.getRandom().nextIntBetweenInclusive(minRolls, maxRolls);

        for (int i = 0; i < rolls; i++) {
            ItemStack item = this.type.get().asItem().getDefaultInstance();
            ItemEntity itemEntity = new ItemEntity(pLevel, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), item);
            pLevel.addFreshEntity(itemEntity);
        }


        return InteractionResultHolder.success(itemStack);
    }

    public abstract void init(WeightedRandom<ItemLike> weightedRandom);

    /**
     * Must be a natural number.
     * @return index 0 = minRolls, index 1 = maxRolls
     */
    public abstract int[] rolls();

}
