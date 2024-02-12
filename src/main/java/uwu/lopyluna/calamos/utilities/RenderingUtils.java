package uwu.lopyluna.calamos.utilities;

import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;

import static net.minecraft.client.model.AnimationUtils.bobArms;

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
    public static <T extends LivingEntity> void twoHandSwing(HumanoidModel<LivingEntity> model, T pEntity, boolean offHand, float pAgeInTicks) {
        twoHanded(model, pEntity, offHand, pAgeInTicks);
        float f = Mth.sin(pAgeInTicks * (float) Math.PI);
        float f1 = Mth.sin((1.0F - (1.0F - pAgeInTicks) * (1.0F - pAgeInTicks)) * (float) Math.PI);
        model.rightArm.zRot = 0.0F;
        model.leftArm.zRot = 0.0F;
        model.rightArm.yRot = (float) (Math.PI / 20);
        model.leftArm.yRot = (float) (-Math.PI / 20);
        if (pEntity.getMainArm() == HumanoidArm.RIGHT) {
            model.rightArm.xRot = -1.8849558F + Mth.cos(pAgeInTicks * 0.09F) * 0.15F;
            model.leftArm.xRot = -0.0F + Mth.cos(pAgeInTicks * 0.19F) * 0.5F;
            model.rightArm.xRot += f * 2.2F - f1 * 0.4F;
            model.leftArm.xRot += f * 1.2F - f1 * 0.4F;
        } else {
            model.rightArm.xRot = -0.0F + Mth.cos(pAgeInTicks * 0.19F) * 0.5F;
            model.leftArm.xRot = -1.8849558F + Mth.cos(pAgeInTicks * 0.09F) * 0.15F;
            model.rightArm.xRot += f * 1.2F - f1 * 0.4F;
            model.leftArm.xRot += f * 2.2F - f1 * 0.4F;
        }
        
        bobArms(model.rightArm, model.leftArm, pAgeInTicks);
    }
}
