package uwu.lopyluna.calamos.elements.items.equipment.tool;

import net.minecraft.world.entity.LivingEntity;

public interface CalamosTool {
    boolean isTwoHanded();
    boolean isBeingUsed();
    boolean hasUsePose();
    boolean hasIdleHeldPose();
    boolean hasSwingPose();
    int attackTimeAddition();
    default void idleHeldPose(LivingEntity entity, boolean offHand, float pAgeInTicks) {
    
    }
    default void usePose(LivingEntity entity, boolean offHand, float pAgeInTicks) {
    
    }
    default void swingPose(LivingEntity entity, boolean offHand, float pAgeInTicks) {
    
    }
}
