package uwu.lopyluna.calamos.core.attribute;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.elements.ModAttachmentTypes;

public class AttributeHandler {

    public static void onEntityAdded(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof LivingEntity livingEntity) {
            if (livingEntity instanceof Player) {
                updatePlayerHealth(livingEntity);
            } else updateMobHealth(livingEntity);
            addDamageMultiplier(livingEntity);
        }
    }

    private static void updatePlayerHealth(LivingEntity livingEntity) {
        ModifiedAttributesData modData = livingEntity.getData(ModAttachmentTypes.MODIFIED_ATTRIBUTES);
        AttributeInstance inst = livingEntity.getAttribute(Attributes.MAX_HEALTH);
        if (inst != null && !modData.isModified(Attributes.MAX_HEALTH)) {
            float calamosMaxHealth = 100.0F;
            inst.setBaseValue(calamosMaxHealth);
            livingEntity.heal(calamosMaxHealth);
            modData.notifyModified(Attributes.MAX_HEALTH);
            livingEntity.setData(ModAttachmentTypes.MODIFIED_ATTRIBUTES, modData);
        }
    }

    private static void updateMobHealth(LivingEntity livingEntity) {
        ModifiedAttributesData modData = livingEntity.getData(ModAttachmentTypes.MODIFIED_ATTRIBUTES);
        AttributeInstance instance = livingEntity.getAttribute(Attributes.MAX_HEALTH);
        if (instance != null && !modData.isModified(Attributes.MAX_HEALTH)) {
            float calamosMaxHealth = livingEntity.getMaxHealth() * 5;
            instance.setBaseValue(calamosMaxHealth);
            livingEntity.heal(calamosMaxHealth);
            modData.notifyModified(Attributes.MAX_HEALTH);
            livingEntity.setData(ModAttachmentTypes.MODIFIED_ATTRIBUTES, modData);
        }
    }

    private static void addDamageMultiplier(LivingEntity livingEntity) {
        AttributeInstance instance = livingEntity.getAttribute(Attributes.ATTACK_DAMAGE);
        if (instance != null) {
            AttributeModifier damageModifier = new AttributeModifier(CalamosMod.asResource("attack_damage.global_multiplier"), 2.0F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
            boolean hasCalamosDamageModifier = instance.hasModifier(CalamosMod.asResource("attack_damage.global_multiplier"));
            if (!hasCalamosDamageModifier)
                instance.addPermanentModifier(damageModifier);
        }
    }
}
