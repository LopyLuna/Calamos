package uwu.lopyluna.calamos.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uwu.lopyluna.calamos.core.systems.modifier.ItemModifier;
import uwu.lopyluna.calamos.elements.ModDataComponents;

@Mixin(ItemStack.class)
public class ItemStackMixin {

    @Inject(method = "getHoverName", at = @At("RETURN"), cancellable = true)
    public void itemName(CallbackInfoReturnable<Component> cir) {
        ItemStack ths = (ItemStack) (Object) this;
        if (ths.has(ModDataComponents.MODIFIER)) {
            cir.setReturnValue(ths.get(ModDataComponents.MODIFIER).modifierName(cir.getReturnValue()));
        }
    }

    @Inject(method = "inventoryTick(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/Entity;IZ)V", at = @At("HEAD"))
    public void invTick(Level level, Entity entity, int inventorySlot, boolean isCurrentItem, CallbackInfo ci) {
        ItemStack ths = (ItemStack) (Object) this;
        if (ths.has(ModDataComponents.MODIFIER)) {
            ItemModifier modifier = ths.get(ModDataComponents.MODIFIER);
            if (modifier != null && !modifier.supports(ths)) {
                ths.remove(ModDataComponents.MODIFIER);
            }
        }
    }
}
