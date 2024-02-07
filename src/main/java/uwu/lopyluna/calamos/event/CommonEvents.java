package uwu.lopyluna.calamos.event;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class CommonEvents {
    
    public CommonEvents() {
        NeoForge.EVENT_BUS.register(this);
    }
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
        
        if (damageInst != null && !tag.getBoolean("calamosModified_ATTACK_DAMAGE")) {
            float calamosAD = (float) livingEntity.getAttributeValue(Attributes.ATTACK_DAMAGE) * 2;
            damageInst.setBaseValue(calamosAD);
            tag.putBoolean("calamosModified_ATTACK_DAMAGE", true);
        }
    }
    
    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        boolean hasCalamosDefaultHelath = player.getMaxHealth() >= 100.0F;
        AttributeInstance inst = player.getAttribute(Attributes.MAX_HEALTH);
        if (!hasCalamosDefaultHelath) {
            if (inst != null)
                inst.setBaseValue(100.0F);
            player.heal(100.0F);
        }
    }
}
