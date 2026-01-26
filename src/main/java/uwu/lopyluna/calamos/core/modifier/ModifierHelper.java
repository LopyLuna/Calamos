package uwu.lopyluna.calamos.core.modifier;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import uwu.lopyluna.calamos.datagen.ModTags;
import uwu.lopyluna.calamos.elements.ModAttachmentTypes;
import uwu.lopyluna.calamos.elements.ModAttributes;

public class ModifierHelper {

    private final LivingEntity owner;
    private ModifierHelper(LivingEntity owner) {
        this.owner = owner;
    }

    public static ModifierHelper create(LivingEntity owner) {
        return new ModifierHelper(owner);
    }

    private double getAttributeSafe(Holder<Attribute> attribute, double fallback) {
        if (this.owner.getAttributes().hasAttribute(attribute)) {
            return this.owner.getAttributeValue(attribute);
        }
        return fallback;
    }

    @SafeVarargs
    private double sumAttributes(Holder<Attribute>... attributes) {
        double sum = 0.0;
        for (Holder<Attribute> attribute : attributes) {
            sum += getAttributeSafe(attribute, 0);
        }
        return sum;
    }

    public float useDuration(ItemStack stack, int ticks) {
        if (stack.is(ModTags.modItemTag("modifiable/weapon/ranged"))) {
            float speedMultiplier = (float) getAttributeSafe(ModAttributes.DRAW_SPEED, 1.0f);
            return speedMultiplier != 1 ? ticks * (1+(1-speedMultiplier)) : ticks;
        }
        return ticks;
    }

    public void projectileFired(Projectile proj) {
        var damageAttribute = ModAttributes.RANGED_DAMAGE;
        var critAttribute = ModAttributes.RANGED_CRIT_CHANCE;
        // Magical Projectiles should use magic-specific attributes. Not ranged.
        if (proj.getType().is(ModTags.modEntityTag("magical_projectile"))) {
            damageAttribute = ModAttributes.MAGIC_DAMAGE;
            critAttribute = ModAttributes.MAGIC_CRIT_CHANCE;
        }
        proj.setData(ModAttachmentTypes.DAMAGE_MULTIPLIER, getAttributeSafe(damageAttribute, 1.0f));
        proj.setData(ModAttachmentTypes.CRIT_CHANCE, sumAttributes(critAttribute, ModAttributes.GLOBAL_CRIT_CHANCE));
        var velocity = proj.getDeltaMovement().scale(getAttributeSafe(ModAttributes.PROJECTILE_VELOCITY, 1.0f));
        proj.setDeltaMovement(velocity);
    }

    public float modifyProjectileKnockback(float original, ItemStack weapon) {
        var knockback = getAttributeSafe(ModAttributes.PROJECTILE_KNOCKBACK, 1.0f);
        if (weapon != null && weapon.is(ModTags.modItemTag("modifiable/weapon/ranged"))) return (float) (original * knockback);
        return original;
    }
}
