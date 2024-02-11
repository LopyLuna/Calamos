package uwu.lopyluna.calamos.datagen.trim;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.level.ItemLike;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.elements.ModItems;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TrimMaterialGen {
    public static final ResourceKey<TrimMaterial> GARNET = registryKey("garnet");
    public static final ResourceKey<TrimMaterial> JADE = registryKey("jade");
    public static final ResourceKey<TrimMaterial> KUNZITE = registryKey("kunzite");
    public static final ResourceKey<TrimMaterial> MOONSTONE = registryKey("moonstone");
    //public static final ResourceKey<TrimMaterial> OPAL = registryKey("opal");
    public static final ResourceKey<TrimMaterial> RUBY = registryKey("ruby");
    public static final ResourceKey<TrimMaterial> SAPPHIRE = registryKey("sapphire");
    public static final ResourceKey<TrimMaterial> SPINEL = registryKey("spinel");
    public static final ResourceKey<TrimMaterial> SUNSTONE = registryKey("sunstone");
    public static final ResourceKey<TrimMaterial> TANZANITE = registryKey("tanzanite");
    public static final ResourceKey<TrimMaterial> TOPAZ = registryKey("topaz");

    private static ResourceKey<TrimMaterial> registryKey(String pKey) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, new ResourceLocation(CalamosMod.MODID, pKey));
    }

    public static void main(String[] args) {
        try {
            generateJson(GARNET, GARNET.location().toString(), 0xf33e35, 0.61F);
            generateJson(JADE, JADE.location().toString(), 0xa4c7a9, 0.62F);
            generateJson(KUNZITE, KUNZITE.location().toString(), 0xffafc2, 0.63F);
            generateJson(MOONSTONE, MOONSTONE.location().toString(), 0x9dc6be, 0.64F);
            generateJson(RUBY, RUBY.location().toString(), 0xffd4c4, 0.66F);
            generateJson(SAPPHIRE, SAPPHIRE.location().toString(), 0xc6e8f2, 0.67F);
            generateJson(SPINEL, SPINEL.location().toString(), 0xff8797, 0.68F);
            generateJson(SUNSTONE, SUNSTONE.location().toString(), 0xffd699, 0.69F);
            generateJson(TANZANITE, TANZANITE.location().toString(), 0x8672d2, 0.71F);
            generateJson(TOPAZ, TOPAZ.location().toString(), 0xfffeb3, 0.72F);
        } catch (Exception e) {
            CalamosMod.LOGGER.error("An error occurred while generating Trim Materials!\n{}", (Object) e.getStackTrace());
        }
    }


    public static void generateJson(ResourceKey<TrimMaterial> key, String ingredient, int color, float itemModelIndex) {
        JsonObject json = new JsonObject();
        Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();
        JsonObject description = new JsonObject();
        String colorHex = "#" + Integer.toHexString(color);
        String translationKey = "trim_material." + key.location().getNamespace() + "." + key.location().getPath();
        json.addProperty("asset_name", key.location().getPath());
        description.addProperty("color", colorHex);
        description.addProperty("translate", translationKey);
        json.add("description", description);
        json.addProperty("ingredient", ingredient);
        json.addProperty("item_model_index", itemModelIndex);
        String fileGson = gsonPretty.toJson(json);
        File file = new File("src/main/resources/data/minecraft/trim_material/" + key.location().getPath() + ".json");
        File directory = new File("src/main/resources/data/minecraft/trim_material");
        if (!directory.exists()) {
            CalamosMod.LOGGER.warn("> Notice | Full directory path does not exist yet, fixing that now... ");
            directory.mkdirs();
        }
        if (file.exists()) { // Regenerate the file if it exists. -- Zeus
            if (!file.delete()) {
                CalamosMod.LOGGER.error("> ERROR | An error occurred while deleting the file: {}", file);
                return;
            }
        }
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(fileGson);
            CalamosMod.LOGGER.warn("> Notice | Generated new Trim Material at {}", file);
        } catch (IOException e) {
            CalamosMod.LOGGER.error("> ERROR | An error occurred while generating Trim Material: {} \n{}", key.location().getPath(), e.getStackTrace());
        }
    }
}
