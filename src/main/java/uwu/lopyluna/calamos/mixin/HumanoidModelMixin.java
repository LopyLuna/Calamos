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
        if (entity.getMainHandItem().getItem() instanceof CalamosTool tool && tool.isTwoHanded()) {
            model.rightArm.xRot = -0.97079635F;
            model.leftArm.xRot = model.rightArm.xRot - 0.033F;
            model.rightArm.yRot = -0.6F;
            model.leftArm.yRot = -model.rightArm.yRot;
        } else if (entity.getOffhandItem().getItem() instanceof CalamosTool tool && tool.isTwoHanded()) {
            model.leftArm.xRot = -0.97079635F;
            model.rightArm.xRot = model.leftArm.xRot - 0.033F;
            model.leftArm.yRot = 0.6F;
            model.rightArm.yRot = -model.leftArm.yRot;
        }
    }
}
