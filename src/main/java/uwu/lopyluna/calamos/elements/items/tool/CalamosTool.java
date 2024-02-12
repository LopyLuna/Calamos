package uwu.lopyluna.calamos.elements.items.tool;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.LivingEntity;

public interface CalamosTool {
    boolean isTwoHanded();
    boolean isBeingUsed();
    boolean hasUsePose();
    boolean hasIdleHeldPose();
    default void idleHeldPose(HumanoidModel<LivingEntity> model, LivingEntity entity, boolean offHand, float pAgeInTicks) {
    
    }
    default void usePose(HumanoidModel<LivingEntity> model, LivingEntity entity, boolean offHand, float pAgeInTicks) {
    
    }
    default void swingPose(HumanoidModel<LivingEntity> model, LivingEntity entity, boolean offHand, float pAgeInTicks) {
    
    }
}
