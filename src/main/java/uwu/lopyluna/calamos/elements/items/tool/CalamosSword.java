package uwu.lopyluna.calamos.elements.items.tool;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class CalamosSword extends SwordItem implements CalamosTool{
    
    private final boolean twoHanded;
    public CalamosSword(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, boolean twoHanded, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        this.twoHanded = twoHanded;
    }
    public boolean isTwoHanded() {
        return twoHanded;
    }
}
