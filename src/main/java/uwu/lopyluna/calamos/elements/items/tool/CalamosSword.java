package uwu.lopyluna.calamos.elements.items.tool;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import uwu.lopyluna.calamos.utilities.RenderingUtils;

public class CalamosSword extends SwordItem implements CalamosTool{
    
    private final boolean twoHanded;
    public CalamosSword(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, boolean twoHanded, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        this.twoHanded = twoHanded;
    }
    public boolean isTwoHanded() {
        return twoHanded;
    }
    
    @Override
    public void idleHeldPose(HumanoidModel<LivingEntity> model, LivingEntity entity, boolean offHand, float pAgeInTicks) {
        if (isTwoHanded()) {
            RenderingUtils.twoHanded(model, entity, offHand, pAgeInTicks);
        }
    }
    @Override
    public boolean isBeingUsed() {
        return false;
    }
    
    @Override
    public boolean hasUsePose() {
        return false;
    }
    
    @Override
    public boolean hasIdleHeldPose() {
        return isTwoHanded();
    }
    
    @Override
    public boolean hasSwingPose() {
        return false;
    }
}
