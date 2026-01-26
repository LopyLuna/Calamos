package uwu.lopyluna.calamos.core.items.equipment.hook;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import uwu.lopyluna.calamos.core.entity.hook.AbstractHookEntity;
import uwu.lopyluna.calamos.core.entity.hook.HookEntity;

public class HookItem extends AbstractHookItem {

    private final int amount;
    private final float range;
    private final HookEntity.Variant variant;

    public HookItem(int amount, float range, int variant) {
        super(new Properties());
        this.amount = amount;
        this.range = range;
        this.variant = HookEntity.Variant.byId(variant);
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public float getRange() {
        return range;
    }

    @Override
    public AbstractHookEntity getHook(ItemStack itemStack, AbstractHookItem item, Player player, Level level) {
        return new HookEntity(item, player, level, variant);
    }
}
