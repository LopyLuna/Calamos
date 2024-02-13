package uwu.lopyluna.calamos;

import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;
import uwu.lopyluna.calamos.datagen.ModDataGenerators;
import uwu.lopyluna.calamos.elements.ModBlocks;
import uwu.lopyluna.calamos.elements.ModCreativeTab;
import uwu.lopyluna.calamos.elements.ModItems;
import uwu.lopyluna.calamos.event.CommonEvents;
import uwu.lopyluna.calamos.mixin.AccessorRangedAttribute;
import uwu.lopyluna.calamos.networking.CalamosMessages;
import uwu.lopyluna.calamos.worldgen.biome.CalamosOverworldRegions;

@Mod(CalamosMod.MODID)
public class CalamosMod {
    private static CalamosMod INSTANCE;
    public static final String MODID = "calamos";
    public static final Logger LOGGER = LogUtils.getLogger();
    private final IEventBus modEventBus;

    public CalamosMod(IEventBus modEventBus) {
        this.modEventBus = modEventBus;
        INSTANCE = this;

        ModItems.staticInit();
        ModBlocks.staticInit();
        ModCreativeTab.staticInit();
        CalamosMessages.init(modEventBus);
        CalamosOverworldRegions.register();


        this.modEventBus.addListener(ModDataGenerators::gatherDataEvent);
        this.modEventBus.register(this);
        NeoForge.EVENT_BUS.register(new CommonEvents());
    }

    @SubscribeEvent
    public void onLoadComplete(FMLLoadCompleteEvent event) {
        final Attribute attribute = Attributes.MAX_HEALTH;
        final AccessorRangedAttribute accessor = (AccessorRangedAttribute) attribute;
        if (attribute instanceof RangedAttribute ranged) {
            double maxHealthPossible = 1000000000.0D;
            LOGGER.debug("Modifying maximum value for %s from %f to %f.".formatted(attribute.getDescriptionId(), ranged.getMaxValue(), maxHealthPossible));
            accessor.calamos$setMaxValue(maxHealthPossible);
        }
    }

    public static IEventBus getEventBus() {
        return INSTANCE.modEventBus;
    }
}
