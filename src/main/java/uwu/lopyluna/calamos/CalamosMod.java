package uwu.lopyluna.calamos;

import com.mojang.logging.LogUtils;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;
import terrablender.api.SurfaceRuleManager;
import uwu.lopyluna.calamos.client.ClientProxy;
import uwu.lopyluna.calamos.datagen.ModDataGenerators;
import uwu.lopyluna.calamos.elements.*;
import uwu.lopyluna.calamos.elements.enchantments.FellingHandler;
import uwu.lopyluna.calamos.mixin.AccessorRangedAttribute;
import uwu.lopyluna.calamos.networking.CalamosMessages;
import uwu.lopyluna.calamos.worldgen.biome.CalamosOverworldRegions;
import uwu.lopyluna.calamos.worldgen.biome.CalamosSurfaceRules;

@SuppressWarnings({"removal", "deprecation", "all"})
@Mod(CalamosMod.MODID)
public class CalamosMod {
    private static CalamosMod INSTANCE;
    public static CommonProxy PROXY = FMLEnvironment.dist.isClient() ? new ClientProxy() : new CommonProxy();
    public static final String MODID = "calamos";
    public static final String NAME = "Calamos";
    public static final Logger LOGGER = LogUtils.getLogger();
    private final IEventBus modEventBus;

    public CalamosMod(IEventBus modEventBus) {
        this.modEventBus = modEventBus;
        INSTANCE = this;

        ModItems.staticInit();
        ModBlocks.staticInit();
        ModDecorativeBlocks.staticInit();
        ModArmorMaterials.staticInit();
        ModCreativeTab.staticInit();
        ModDataComponents.staticInit();
        ModParticleTypes.init(modEventBus);
        ModEntity.staticInit();
        ModModifiers.init(modEventBus);
        CalamosMessages.init();
        CalamosOverworldRegions.register();
        ModAttributes.init(modEventBus);
        ModEffects.staticInit();
        ModMenuType.staticInit();
        ModBlockEntities.staticInit();
        ModRecipes.staticInit();
        ModSoundEvents.staticInit();
        PROXY.commonInit();
        this.modEventBus.addListener(ModDataGenerators::gatherDataEvent);
        NeoForge.EVENT_BUS.addListener(FellingHandler::onBlockDestroyed);
        this.modEventBus.register(this);
    }

    @SubscribeEvent
    public void onLoadComplete(FMLLoadCompleteEvent event) {
        final Holder<Attribute> attribute = Attributes.MAX_HEALTH;
        final AccessorRangedAttribute accessor = (AccessorRangedAttribute) attribute.value();
        if (attribute.value() instanceof RangedAttribute ranged) {
            double maxHealthPossible = 1000000000.0D;
            LOGGER.debug("Modifying maximum value for %s from %f to %f.".formatted(attribute.getRegisteredName(), ranged.getMaxValue(), maxHealthPossible));
            accessor.calamos$setMaxValue(maxHealthPossible);
        }
        event.enqueueWork(() -> {
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MODID, CalamosSurfaceRules.makeRules());
        });
    }
    
    public static IEventBus getEventBus() {
        return INSTANCE.modEventBus;
    }

    public static ResourceLocation asResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
