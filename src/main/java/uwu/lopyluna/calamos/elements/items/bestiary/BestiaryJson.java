package uwu.lopyluna.calamos.elements.items.bestiary;

import com.google.gson.*;
import com.mojang.logging.LogUtils;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootTable;
import org.slf4j.Logger;

import java.util.List;
import java.util.Map;

public class BestiaryJson extends SimpleJsonResourceReloadListener {
    public static List<JsonElement> bestiaryEntries;
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    public BestiaryJson() {
        super(GSON, "calamos/bestiary/entries");
    }
    
    @Override
    protected void apply(Map<ResourceLocation, JsonElement> pObject, ResourceManager pResourceManager, ProfilerFiller pProfiler) {
        pObject.forEach((resourceLocation, jsonElement) -> {
            LOGGER.info("%s ||| %s".formatted(resourceLocation, jsonElement));
            bestiaryEntries.add(jsonElement);
        });
    }
    
    private EntityType<?> getEntryEntity(JsonElement bestiaryJson) {
        JsonObject json = bestiaryJson.getAsJsonObject();
        ResourceLocation entityReg = new ResourceLocation(json.get("entity").getAsString());
        return BuiltInRegistries.ENTITY_TYPE.get(entityReg);
    }
    private MutableComponent getLoreDesc(JsonElement bestiaryJson) {
        JsonObject json = bestiaryJson.getAsJsonObject();
        String loreKey = json.get("lore_key").getAsString();
        return Component.translatable(loreKey);
    }
    private List<MutableComponent> getSpawnBiomes(JsonElement bestiaryJson) {
        List<MutableComponent> spawnBiomes = NonNullList.create();
        JsonObject json = bestiaryJson.getAsJsonObject();
        JsonArray biomeArray = json.getAsJsonArray("spawn_biomes");
        biomeArray.forEach(biome -> {
            String biomeKey = biome.getAsString();
            spawnBiomes.add(Component.translatable(biomeKey));
        });
        return spawnBiomes;
    }
    private LootTable getEntityLoot(EntityType<?> entity, ServerLevel level) {
        ResourceLocation loot = entity.getDefaultLootTable();
        return level.getServer().getLootData().getLootTable(loot);
    }
}
