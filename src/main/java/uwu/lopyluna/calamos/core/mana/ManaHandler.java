package uwu.lopyluna.calamos.core.mana;

import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import uwu.lopyluna.calamos.elements.ModAttachmentTypes;
import uwu.lopyluna.calamos.elements.ModAttributes;

public class ManaHandler {

    public static void entityTick(EntityTickEvent.Pre event) {
        if (event.getEntity() instanceof LivingEntity living) {
            var level = living.level();
            if (!level.isClientSide) {
                if (living.getAttributeValue(ModAttributes.MAX_MANA) == 0 && !living.hasData(ModAttachmentTypes.MANA)) {
                    return;
                }
                var data = living.getData(ModAttachmentTypes.MANA);
                data.tickData(living);
            }
        }
    }
}
