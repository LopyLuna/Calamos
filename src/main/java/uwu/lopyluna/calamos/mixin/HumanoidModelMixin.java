package uwu.lopyluna.calamos.mixin;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uwu.lopyluna.calamos.elements.items.tool.CalamosTool;

@Mixin(HumanoidModel.class)
public class HumanoidModelMixin {
    @SuppressWarnings("unchecked")
    @Inject(method = "setupAnim*", at = @At("TAIL"))
    private void calamos$setupAnimTail(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        var model = ((HumanoidModel<LivingEntity>) (Object) this);
        if (entity.getMainHandItem().getItem() instanceof CalamosTool tool && tool.isTwoHanded() && !tool.isBeingUsed() && tool.hasIdleHeldPose()) {
            if (entity.swinging)
                tool.swingPose(model, entity, false, entity.swingTime);
            else
                tool.idleHeldPose(model, entity, false, ageInTicks);
        } else if (entity.getOffhandItem().getItem() instanceof CalamosTool tool && tool.isTwoHanded() && !tool.isBeingUsed() && tool.hasIdleHeldPose()) {
            if (entity.swinging)
                tool.swingPose(model, entity, true, entity.swingTime);
            else
                tool.idleHeldPose(model, entity, true, ageInTicks);
        }
    }
}
