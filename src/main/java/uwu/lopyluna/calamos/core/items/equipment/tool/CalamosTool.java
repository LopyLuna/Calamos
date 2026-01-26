package uwu.lopyluna.calamos.core.items.equipment.tool;

import com.google.common.collect.Maps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;
import uwu.lopyluna.calamos.core.items.equipment.damage.MultiDamageHandler;

import java.util.Map;

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

    default Map<ResourceKey<DamageType>, Float> getDamageSources(LivingEntity entity) {
        return Maps.newHashMap();
    }

    default MultiDamageHandler createDamageHandler(LivingEntity entity) {
        MultiDamageHandler handler = new MultiDamageHandler(entity.registryAccess());
        Map<ResourceKey<DamageType>, Float> damageSources = getDamageSources(entity);
        for (var entry : damageSources.entrySet()) {
            handler.addDamageSource(entry.getKey(), entry.getValue());
        }
        handler.setCausingEntity(entity);
        return handler;
    }
}
