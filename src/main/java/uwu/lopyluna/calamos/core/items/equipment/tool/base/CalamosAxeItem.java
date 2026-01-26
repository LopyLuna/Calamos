package uwu.lopyluna.calamos.core.items.equipment.tool.base;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Tier;

public class CalamosAxeItem extends AxeItem {
    public CalamosAxeItem(Tier pTier, float pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pProperties.attributes(AxeItem.createAttributes(pTier, pAttackDamageModifier, pAttackSpeedModifier)));
    }
}
