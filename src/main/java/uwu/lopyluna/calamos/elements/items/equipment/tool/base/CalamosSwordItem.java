package uwu.lopyluna.calamos.elements.items.equipment.tool.base;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class CalamosSwordItem extends SwordItem {
    public CalamosSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pProperties.attributes(SwordItem.createAttributes(pTier, pAttackDamageModifier, pAttackSpeedModifier)));
    }
}
