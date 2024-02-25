package uwu.lopyluna.calamos.elements.items.accessories;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

@SuppressWarnings({"all"})
public class BerserkerCrawItem extends Item implements ICurioItem {

    public BerserkerCrawItem() {
        super(new Item.Properties().stacksTo(1));
    }



    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        if (livingEntity instanceof Player player) {
            Level level = player.level();
            BlockPos blockPos = player.blockPosition();
            Vec3 pos = player.position();
            ItemStack handItem = player.getMainHandItem();
            ItemStack offItem = player.getOffhandItem();





        }

    }

}
