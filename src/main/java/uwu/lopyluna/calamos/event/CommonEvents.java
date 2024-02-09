package uwu.lopyluna.calamos.event;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.UUID;

public class CommonEvents {
    
    public CommonEvents() {}
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onEntityAdded(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof LivingEntity livingEntity)) {
            return;
        }
        if (entity instanceof Player) {
            return;
        }
        
        
        AttributeInstance healthInst = livingEntity.getAttribute(Attributes.MAX_HEALTH);
        AttributeInstance damageInst = livingEntity.getAttribute(Attributes.ATTACK_DAMAGE);
        CompoundTag tag = livingEntity.getPersistentData();
        if (healthInst != null && !tag.getBoolean("calamosModified_MAX_HEALTH")) {
            float calamosMH = livingEntity.getMaxHealth() * 5;
            healthInst.setBaseValue(calamosMH);
            livingEntity.heal(calamosMH);
            tag.putBoolean("calamosModified_MAX_HEALTH", true);
        }
        if (damageInst != null) {
            AttributeModifier damageModifier = new AttributeModifier(UUID.fromString("5a1d6084-5698-4066-998b-23c02b389392"), "Calamos Damage Multiplier", 2.0F, AttributeModifier.Operation.MULTIPLY_TOTAL);
            boolean hasCalamosDamageModifier = damageInst.hasModifier(damageModifier);
            if (!hasCalamosDamageModifier)
                damageInst.addPermanentModifier(damageModifier);
        }
    }
    
    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        float MaxHealth = 100.0F;
        Player player = event.getEntity();
        boolean hasCalamosDefaultHelath = player.getMaxHealth() >= MaxHealth;
        AttributeInstance inst = player.getAttribute(Attributes.MAX_HEALTH);
        AttributeInstance damageInst = player.getAttribute(Attributes.ATTACK_DAMAGE);
        if (!hasCalamosDefaultHelath) {
            if (inst != null)
                inst.setBaseValue(MaxHealth);
            player.heal(MaxHealth);
        }
        if (damageInst != null) {
            AttributeModifier damageModifier = new AttributeModifier(UUID.fromString("5a1d6084-5698-4066-998b-23c02b389392"), "Calamos Damage Multiplier", 2.0F, AttributeModifier.Operation.MULTIPLY_TOTAL);
            boolean hasCalamosDamageModifier = damageInst.hasModifier(damageModifier);
            if (!hasCalamosDamageModifier)
                damageInst.addPermanentModifier(damageModifier);
        }
    }
}
