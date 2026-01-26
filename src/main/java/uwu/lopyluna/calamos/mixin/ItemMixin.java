package uwu.lopyluna.calamos.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uwu.lopyluna.calamos.core.modifier.ModifierHelper;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "getUseDuration(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/LivingEntity;)I", at = @At("RETURN"), cancellable = true)
    public void useDuration(ItemStack stack, LivingEntity entity, CallbackInfoReturnable<Integer> cir) {
        int ticks = cir.getReturnValue();
        ModifierHelper modifiers = ModifierHelper.create(entity);
        cir.setReturnValue(Math.round(modifiers.useDuration(stack, ticks)));
    }
}
