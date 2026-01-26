package uwu.lopyluna.calamos.core.items.equipment.tool.base;

import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;

public class CalamosPickaxeItem extends PickaxeItem {
    public CalamosPickaxeItem(Tier pTier, int pAttackDamage, float pAttackSpeed, Properties pProperties) {
        super(pTier, pProperties.attributes(PickaxeItem.createAttributes(pTier, pAttackDamage, pAttackSpeed)));
    }
}
