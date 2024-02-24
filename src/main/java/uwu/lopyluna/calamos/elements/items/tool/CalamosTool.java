package uwu.lopyluna.calamos.elements.items.tool;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

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
