package uwu.lopyluna.calamos;

import com.mojang.logging.LogUtils;
import net.minecraft.client.HotbarManager;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.player.inventory.Hotbar;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import org.slf4j.Logger;
import uwu.lopyluna.calamos.event.CommonEvents;
import uwu.lopyluna.calamos.mixin.AccessorRangedAttribute;

@Mod(CalamosMod.MODID)
@Mod.EventBusSubscriber(modid = CalamosMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CalamosMod {
    private static CalamosMod INSTANCE;
    public static final String MODID = "calamos";
    private static final Logger LOGGER = LogUtils.getLogger();
    private final IEventBus modEventBus;
    public CalamosMod(IEventBus modEventBus) {
        this.modEventBus = modEventBus;
        NeoForge.EVENT_BUS.register(new CommonEvents());
        INSTANCE = this;
    }
    
    @SubscribeEvent
    public static void onLoadComplete(FMLLoadCompleteEvent event) {
        final Attribute attribute = Attributes.MAX_HEALTH;
        final AccessorRangedAttribute accessor = (AccessorRangedAttribute) attribute;
        if (attribute instanceof RangedAttribute ranged) {
            double maxHealthPossible = 1000000000.0D;
            LOGGER.debug("Modifying maximum value for "+ Attributes.MAX_HEALTH.getDescriptionId() + " from " + ranged.getMaxValue() + " to " + maxHealthPossible + ".");
            accessor.calamos$setMaxValue(maxHealthPossible);
        }
    }

    public static IEventBus getEventBus() {
        return INSTANCE.modEventBus;
    }
    
    
    
    
}
