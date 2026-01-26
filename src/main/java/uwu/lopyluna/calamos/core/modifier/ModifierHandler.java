package uwu.lopyluna.calamos.core.modifier;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import uwu.lopyluna.calamos.elements.ModAttachmentTypes;

public class ModifierHandler {

    public static void incomingDamage(LivingIncomingDamageEvent event) {
        Entity directCause = event.getSource().getDirectEntity();
        if (directCause != null) {
            float multiplier = directCause.getData(ModAttachmentTypes.DAMAGE_MULTIPLIER).floatValue();
            if (multiplier != 1.0) {
                event.setAmount(event.getAmount() * multiplier);
            }
            float critChance = directCause.getData(ModAttachmentTypes.CRIT_CHANCE).floatValue();
            if (critChance <= directCause.getRandom().nextFloat()) {
                event.setAmount(event.getAmount() * 2);
            }
        }
    }

    public static void entityAdded(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Projectile proj) {
            if (proj.getOwner() instanceof LivingEntity user) {
                ModifierHelper modifiers = ModifierHelper.create(user);
                modifiers.projectileFired(proj);
            }
        }
    }
}
