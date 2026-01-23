package uwu.lopyluna.calamos.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import org.jetbrains.annotations.UnknownNullability;
import uwu.lopyluna.calamos.CalamosMod;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class CalamosReloadableModels {
    public static final CalamosReloadableModels INSTANCE = new CalamosReloadableModels();

    private final Map<ResourceLocation, Function<BakedModel, BakedModel>> afterBakeModifiers;
    private final Map<ResourceLocation, Consumer<BakedModel>> modelConsumers;

    private static final String[] hookTypes = new String[]{
            "grappling", "amethyst", "topaz", "sapphire",
            "emerald", "ruby", "diamond"
    };

    private static final ResourceLocation[] hookModelIds = Arrays.stream(hookTypes)
            .map(type -> CalamosMod.asResource("item/hook/" + type + "/hook")).toArray(ResourceLocation[]::new);
    private static final ResourceLocation[] chainModelIds = Arrays.stream(hookTypes)
            .map(type -> CalamosMod.asResource("item/hook/" + type + "/chain")).toArray(ResourceLocation[]::new);

    public boolean registeredModels = false;

    @UnknownNullability
    public BakedModel
            // Hooks
            hook_grappling, chain_grappling,
            hook_amethyst, chain_amethyst,
            hook_topaz, chain_topaz,
            hook_sapphire, chain_sapphire,
            hook_emerald, chain_emerald,
            hook_ruby, chain_ruby,
            hook_diamond, chain_diamond
            ;

    //public final BakedModel[] hookModels;
    //public final BakedModel[] chainModels;

    public void onModelRegister(ResourceManager rm, Consumer<ResourceLocation> consumer) {
        modelConsumers.keySet().forEach(consumer);

        if (!registeredModels) {
            registeredModels = true;
        }
    }

    public void onModelBake(ModelBakery loader, Map<ModelResourceLocation, BakedModel> map) {
        if (!registeredModels) {
            CalamosMod.LOGGER.error("Additional models failed to register! Aborting baking models to avoid early crashing.");
            return;
        }
        afterBakeModifiers.forEach((resourceLocation, afterBakeModifier) -> map
                .computeIfPresent(new ModelResourceLocation(resourceLocation, ""),
                        (resourceLoc, bakedModel) -> afterBakeModifier.apply(bakedModel)));
        modelConsumers.forEach((resourceLocation, bakedModelConsumer) -> bakedModelConsumer
                .accept(map.get(new ModelResourceLocation(resourceLocation, "standalone"))));
    }

    private ResourceLocation stripBlockPrefix(ResourceLocation id) {
        String path = id.getPath();
        return ResourceLocation.fromNamespaceAndPath(id.getNamespace(), path.startsWith("block/") ? path.substring(6) : path);
    }

    private CalamosReloadableModels() {
        afterBakeModifiers = new HashMap<>();

        modelConsumers = new HashMap<>();

        // Location Providers
        Function<String, ResourceLocation> hookProv = type -> CalamosMod.asResource("item/hook/" + type + "/hook");
        Function<String, ResourceLocation> chainProv = type -> CalamosMod.asResource("item/hook/" + type + "/chain");

        // Hooks
        modelConsumers.put(hookProv.apply("grappling"), bakedModel -> this.hook_grappling = bakedModel);
        modelConsumers.put(chainProv.apply("grappling"), bakedModel -> this.chain_grappling = bakedModel);
        modelConsumers.put(hookProv.apply("amethyst"), bakedModel -> this.hook_amethyst = bakedModel);
        modelConsumers.put(chainProv.apply("amethyst"), bakedModel -> this.chain_amethyst = bakedModel);
        modelConsumers.put(hookProv.apply("topaz"), bakedModel -> this.hook_topaz = bakedModel);
        modelConsumers.put(chainProv.apply("topaz"), bakedModel -> this.chain_topaz = bakedModel);
        modelConsumers.put(hookProv.apply("sapphire"), bakedModel -> this.hook_sapphire = bakedModel);
        modelConsumers.put(chainProv.apply("sapphire"), bakedModel -> this.chain_sapphire = bakedModel);
        modelConsumers.put(hookProv.apply("emerald"), bakedModel -> this.hook_emerald = bakedModel);
        modelConsumers.put(chainProv.apply("emerald"), bakedModel -> this.chain_emerald = bakedModel);
        modelConsumers.put(hookProv.apply("ruby"), bakedModel -> this.hook_ruby = bakedModel);
        modelConsumers.put(chainProv.apply("ruby"), bakedModel -> this.chain_ruby = bakedModel);
        modelConsumers.put(hookProv.apply("diamond"), bakedModel -> this.hook_diamond = bakedModel);
        modelConsumers.put(chainProv.apply("diamond"), bakedModel -> this.chain_diamond = bakedModel);

        //hookModels = getBakedModels(modelConsumers, hookModelIds);
        //chainModels = getBakedModels(modelConsumers, chainModelIds);
    }

    private static BakedModel[] getBakedModels(Map<ResourceLocation, Consumer<BakedModel>> consumers, ResourceLocation[] ids) {
        final BakedModel[] bakedModels = new BakedModel[ids.length];
        for (int i = 0; i < ids.length; i++) {
            int index = i;
            consumers.put(ids[index], bakedModel -> bakedModels[index] = bakedModel);
        }
        return bakedModels;
    }

    public static BakedModel getResourceModel(ResourceLocation id) {
        BakedModel model = Minecraft.getInstance().getModelManager().getModel(new ModelResourceLocation(id, "standalone"));
        return model != Minecraft.getInstance().getModelManager().getMissingModel() ? model : null;
    }
}
