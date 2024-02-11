package uwu.lopyluna.calamos.utilities;

import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.LivingEntity;

public class RenderingUtils {
    
    public static void defaultPose(HumanoidModel<LivingEntity> model, LivingEntity entity, boolean offHand, float pAgeInTicks) {
        AnimationUtils.bobModelPart(model.rightArm, pAgeInTicks, 1.0F);
        AnimationUtils.bobModelPart(model.leftArm, pAgeInTicks, 1.0F);
    }
    public static void twoHanded(HumanoidModel<LivingEntity> model, LivingEntity entity, boolean offHand, float pAgeInTicks) {
        if (offHand) {
            model.leftArm.xRot = -0.97079635F;
            model.rightArm.xRot = model.leftArm.xRot - 0.233F;
            model.leftArm.yRot = 0.58F;
            model.rightArm.yRot = -model.leftArm.yRot;
        } else {
            model.rightArm.xRot = -0.97079635F;
            model.leftArm.xRot = model.rightArm.xRot - 0.233F;
            model.rightArm.yRot = -0.58F;
            model.leftArm.yRot = -model.rightArm.yRot;
        }
    }
}
