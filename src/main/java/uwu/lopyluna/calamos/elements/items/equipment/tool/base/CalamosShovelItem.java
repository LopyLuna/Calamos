package uwu.lopyluna.calamos.elements.items.equipment.tool.base;

import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;

public class CalamosShovelItem extends ShovelItem {
    public CalamosShovelItem(Tier pTier, float pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pProperties.attributes(ShovelItem.createAttributes(pTier, pAttackDamageModifier, pAttackSpeedModifier)));
    }
}
