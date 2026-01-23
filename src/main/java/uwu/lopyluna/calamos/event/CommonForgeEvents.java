package uwu.lopyluna.calamos.event;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.CustomizeGuiOverlayEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.client.ClientProxy;
import uwu.lopyluna.calamos.client.gui.CalamosBossBar;
import uwu.lopyluna.calamos.elements.ModEffects;
import uwu.lopyluna.calamos.elements.entity.machina.pestis_infection.PestisPlayerEntity;
import uwu.lopyluna.calamos.networking.packets.S2C.PestisCameraPacket;

import java.util.UUID;


@EventBusSubscriber(modid = CalamosMod.MODID, bus = EventBusSubscriber.Bus.GAME)
public class CommonForgeEvents {
    
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onEntityAdded(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player player) {
            float MaxHealth = 100.0F;
            boolean hasCalamosDefaultHealth = player.getMaxHealth() >= MaxHealth;
            AttributeInstance inst = player.getAttribute(Attributes.MAX_HEALTH);
            AttributeInstance damageInst = player.getAttribute(Attributes.ATTACK_DAMAGE);
            if (!hasCalamosDefaultHealth) {
                if (inst != null)
                    inst.setBaseValue(MaxHealth);
                player.heal(MaxHealth);
            }
            if (damageInst != null) {
                AttributeModifier damageModifier = new AttributeModifier(CalamosMod.asResource("attack_damage.global_multiplier"), 2.0F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
                boolean hasCalamosDamageModifier = damageInst.hasModifier(CalamosMod.asResource("attack_damage.global_multiplier"));
                if (!hasCalamosDamageModifier)
                    damageInst.addPermanentModifier(damageModifier);
            }
        }
        if (!(entity instanceof LivingEntity livingEntity) || entity instanceof Player) {
            return;
        }


        AttributeInstance healthInst = livingEntity.getAttribute(Attributes.MAX_HEALTH);
        AttributeInstance damageInst = livingEntity.getAttribute(Attributes.ATTACK_DAMAGE);
        CompoundTag tag = livingEntity.getPersistentData();
        if (healthInst != null && !tag.getBoolean("calamosModified_MAX_HEALTH")) {
            float calamosMaxHealth = livingEntity.getMaxHealth() * 5;
            healthInst.setBaseValue(calamosMaxHealth);
            livingEntity.heal(calamosMaxHealth);
            tag.putBoolean("calamosModified_MAX_HEALTH", true);
        }
        if (damageInst != null) {
            AttributeModifier damageModifier = new AttributeModifier(CalamosMod.asResource("attack_damage.global_multiplier"), 2.0F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
            boolean hasCalamosDamageModifier = damageInst.hasModifier(CalamosMod.asResource("attack_damage.global_multiplier"));
            if (!hasCalamosDamageModifier)
                damageInst.addPermanentModifier(damageModifier);
        }


    }
    @SubscribeEvent
    public static void playerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        CompoundTag tag = player.getPersistentData();
        if (tag.contains("LinkedPestisClone") && player.hasEffect(ModEffects.PESTIS)) {
            UUID pestisUUID = tag.getUUID("LinkedPestisClone");
            ServerLevel level = (ServerLevel) player.level;
            Entity pestisPlayer = level.getEntity(pestisUUID);
            if (pestisPlayer != null && player instanceof ServerPlayer serverPlayer) {
                PacketDistributor.sendToPlayer(serverPlayer, new PestisCameraPacket(player.getId(), pestisPlayer.getId(), false));
            }
        }
    }
    @SubscribeEvent
    public static void entityDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof PestisPlayerEntity pestisPlayer) {
            UUID linkedPlayer = PestisPlayerEntity.linkedPlayer;
            if (linkedPlayer != null) {
                ServerPlayer player = (ServerPlayer) event.getEntity().level().getPlayerByUUID(linkedPlayer);
                if (player != null) {
                    player.setGameMode(pestisPlayer.linkedPlayerGameType);
                    player.teleportTo(pestisPlayer.getX(), pestisPlayer.getY(), pestisPlayer.getZ());
                    player.setYRot(pestisPlayer.getYRot());
                    player.setXRot(pestisPlayer.getXRot());
                    PacketDistributor.sendToPlayer(player, new PestisCameraPacket(player.getId(), pestisPlayer.getId(), true));
                }
            }
        }
    }
    @SubscribeEvent
    public static void playerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        CompoundTag tag = player.getPersistentData();
        if (!tag.contains("max_flight_meter")) {
            tag.putFloat("max_flight_meter", 30.0F);
        }
        if (tag.getFloat("flight_meter") > tag.getFloat("max_flight_meter")) {
            tag.putFloat("flight_meter", tag.getFloat("max_flight_meter"));
        }
        if (tag.contains("LinkedPestisClone") && !player.hasEffect(ModEffects.PESTIS)) {
            UUID pestisUUID = tag.getUUID("LinkedPestisClone");
            MinecraftServer server = player.getServer();
            tag.remove("LinkedPestisClone");
            if (server != null) {
                ServerLevel level = server.getLevel(player.level.dimension());
                Entity pestisPlayer = level.getEntity(pestisUUID);
                if (pestisPlayer != null) {
                    pestisPlayer.kill();
                }
            }
        }
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
