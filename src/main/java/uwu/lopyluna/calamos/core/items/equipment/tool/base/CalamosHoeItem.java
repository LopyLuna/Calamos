package uwu.lopyluna.calamos.core.items.equipment.tool.base;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Tier;

public class CalamosHoeItem extends HoeItem {
    public CalamosHoeItem(Tier pTier, int pAttackDamage, float pAttackSpeed, Properties pProperties) {
        super(pTier, pProperties.attributes(HoeItem.createAttributes(pTier, pAttackDamage, pAttackSpeed)));
    }
}
