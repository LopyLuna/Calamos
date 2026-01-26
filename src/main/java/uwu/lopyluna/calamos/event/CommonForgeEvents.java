package uwu.lopyluna.calamos.event;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.CustomizeGuiOverlayEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.client.ClientProxy;
import uwu.lopyluna.calamos.client.gui.CalamosBossBar;
import uwu.lopyluna.calamos.core.attribute.AttributeHandler;
import uwu.lopyluna.calamos.core.entity.machina.MachinaPestisHandler;
import uwu.lopyluna.calamos.core.items.equipment.wings.WingsHandler;
import uwu.lopyluna.calamos.core.mana.ManaHandler;
import uwu.lopyluna.calamos.core.modifier.ModifierHandler;


@EventBusSubscriber(modid = CalamosMod.MODID)
public class CommonForgeEvents {
    
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onEntityAdded(EntityJoinLevelEvent event) {
        AttributeHandler.onEntityAdded(event);
        ModifierHandler.entityAdded(event);
    }

    @SubscribeEvent
    public static void incomingDamage(LivingIncomingDamageEvent event) {
        ModifierHandler.incomingDamage(event);
    }

    @SubscribeEvent
    public static void onLivingTick(EntityTickEvent.Pre event) {
        ManaHandler.entityTick(event);
    }

    @SubscribeEvent
    public static void playerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        MachinaPestisHandler.playerJoin(event);
    }

    @SubscribeEvent
    public static void entityDeath(LivingDeathEvent event) {
        MachinaPestisHandler.entityDeath(event);
    }

    @SubscribeEvent
    public static void playerTick(PlayerTickEvent.Post event) {
        WingsHandler.playerTick(event);
        MachinaPestisHandler.playerTick(event);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onRenderBossBar(CustomizeGuiOverlayEvent.BossEventProgress event){
        ResourceLocation bossRegistryName = ClientProxy.bossBarRegistryNames.getOrDefault(event.getBossEvent().getId(), null);
        if (bossRegistryName == null) return;
        CalamosBossBar customBossBar = CalamosBossBar.customBars.getOrDefault(bossRegistryName, null);
        if (customBossBar == null) return;

        event.setCanceled(true);
        customBossBar.renderBossBar(event);
    }
}
