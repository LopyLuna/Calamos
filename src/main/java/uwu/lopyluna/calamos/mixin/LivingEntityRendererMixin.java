package uwu.lopyluna.calamos.mixin;

import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uwu.lopyluna.calamos.elements.items.equipment.tool.CalamosTool;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {
    
    @Inject(method = "getAttackAnim(Lnet/minecraft/world/entity/LivingEntity;F)F", at = @At("HEAD"), cancellable = true)
    private void calamos$getAttackAnim(LivingEntity entity, float tickDelta, CallbackInfoReturnable<Float> cir) {
        if (entity.getMainHandItem().getItem() instanceof CalamosTool tool && tool.hasSwingPose()) {
            cir.setReturnValue(0.0F);
        }
    }
}
