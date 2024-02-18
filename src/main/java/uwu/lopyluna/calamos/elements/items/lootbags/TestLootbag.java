package uwu.lopyluna.calamos.elements.items.lootbags;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import uwu.lopyluna.calamos.elements.ModItems;
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
    public int[] rolls() {
        return new int[]{1, 3};
    }

}
