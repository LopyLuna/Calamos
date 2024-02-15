package uwu.lopyluna.calamos.utilities;

import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.EnderpearlItem;
import org.joml.Vector3f;

import static net.minecraft.client.model.AnimationUtils.bobArms;

public class RenderingUtils {
    public static float degToRad(double deg) {
        return (float) (deg * (Math.PI/180));
    }
    public static float ticksToSeconds(double ticks) {
        return (float) (ticks/20);
    }

    public static void defaultPose(HumanoidModel<LivingEntity> model, LivingEntity entity, boolean offHand, float pAgeInTicks) {
        AnimationUtils.bobModelPart(model.rightArm, pAgeInTicks, 1.0F);
        AnimationUtils.bobModelPart(model.leftArm, pAgeInTicks, 1.0F);
    }
    public static void twoHanded(HumanoidModel<LivingEntity> model, LivingEntity entity, boolean offHand, float pAgeInTicks) {
        if (entity.isSprinting()) {
            twoHandedSprint(model, entity, offHand, pAgeInTicks);
        }
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
    public static void twoHandedSprint(HumanoidModel<LivingEntity> model, LivingEntity entity, boolean offHand, float pAgeInTicks) {
        float f;
        f = (float)entity.getDeltaMovement().lengthSqr();
        f /= 0.2F;
        f *= f * f;
        if (f < 1.0F) {
            f = 1.0F;
        }
        if (offHand) {
            model.leftArm.zRot = -1.0F;
            model.leftArm.xRot = 1.5F + (TweenUtils.circOut((pAgeInTicks / 8.0F)));
            model.rightArm.xRot = -1.20379635F;
            model.rightArm.yRot = 0.58F - (TweenUtils.elasticOut((pAgeInTicks / 8.0F)));
            model.body.xRot = 0.5F;
            model.rightLeg.z = 4.0F;
            model.leftLeg.z = 4.0F;
        } else {
            model.rightArm.zRot = 1.0F;
            model.rightArm.xRot = -1.5F + (TweenUtils.circOut((pAgeInTicks / 8.0F)));
            model.leftArm.xRot = 1.20379635F;
            model.leftArm.yRot = -0.58F - (TweenUtils.elasticOut((pAgeInTicks / 8.0F)));
            model.body.xRot = 0.5F;
            model.rightLeg.z = 4.0F;
            model.leftLeg.z = 4.0F;
        
        }
    }
    public static void twoHandSwing(HumanoidModel<LivingEntity> model, LivingEntity entity, boolean offHand, float pAgeInTicks) {
        twoHanded(model, entity, offHand, pAgeInTicks);
        float f = Mth.sin(pAgeInTicks * (float) Math.PI);
        float f1 = Mth.sin((1.0F - (1.0F - pAgeInTicks) * (1.0F - pAgeInTicks)) * (float) Math.PI);
        model.rightArm.zRot = 0.0F;
        model.leftArm.zRot = 0.0F;
        model.rightArm.yRot = (float) (Math.PI / 20);
        model.leftArm.yRot = (float) (-Math.PI / 20);
        if (entity.getMainArm() == HumanoidArm.RIGHT) {
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
    
    //TODO: Fix the swing animation. The player's arm phases through the body.
    public static void reaperSwing(HumanoidModel<LivingEntity> model, LivingEntity entity, boolean offHand, float pAgeInTicks) {
        //entity.sendSystemMessage(Component.literal("Swing Time: " + pAgeInTicks));
        if (!offHand) {
            model.rightArm.xRot = pAgeInTicks  <= 2 ? degToRad(200) : pAgeInTicks > 2 || pAgeInTicks <= 6 ? degToRad(200) + ((TweenUtils.backIn((pAgeInTicks - 2)/6.0F)) * degToRad(25)) : degToRad(300);
            model.rightArm.zRot = degToRad(-15);

            //model.rightArm.xRot = -1.5F + (TweenUtils.circOut((pAgeInTicks / 8.0F)));
            //model.leftArm.xRot = degToRad(-60);
            //model.leftArm.yRot = -0.58F - (TweenUtils.elasticOut((pAgeInTicks / 8.0F)));
        } else {
            bobArms(model.rightArm, model.leftArm, pAgeInTicks);
        }
    }
}
