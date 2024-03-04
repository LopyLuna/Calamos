package uwu.lopyluna.calamos.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uwu.lopyluna.calamos.elements.ModEnchantments;
import uwu.lopyluna.calamos.elements.items.tool.CalamosTool;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    
    @Shadow public abstract ItemStack getMainHandItem();
    
    @Inject(method = "getCurrentSwingDuration", at = @At("HEAD"), cancellable = true)
    public void calamos$getCurrentSwingDuration(CallbackInfoReturnable<Integer> cir) {
        if (this.getMainHandItem().getItem() instanceof CalamosTool tool && tool.hasSwingPose()) {
            cir.setReturnValue(6 + tool.attackTimeAddition());
        }
        if (this.getMainHandItem().getEnchantmentLevel(ModEnchantments.FELLING.get()) > 0) {
            cir.setReturnValue(8);
        }
    }
}
