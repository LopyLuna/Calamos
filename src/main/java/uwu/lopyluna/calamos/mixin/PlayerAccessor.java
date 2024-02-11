package uwu.lopyluna.calamos.mixin;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
@Mixin(Player.class)
public interface PlayerAccessor {
    @Accessor
    Inventory getInventory();
}
