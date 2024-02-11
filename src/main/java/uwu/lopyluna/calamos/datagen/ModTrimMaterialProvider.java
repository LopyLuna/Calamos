package uwu.lopyluna.calamos.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.Util;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.armortrim.TrimMaterial;
import uwu.lopyluna.calamos.elements.ModArmorTrimMaterials;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModTrimMaterialProvider implements DataProvider {
    private final PackOutput packOutput;

    public ModTrimMaterialProvider(PackOutput packOutput) {
        this.packOutput = packOutput;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput pOutput) {
        Set<CompletableFuture<?>> builder = new HashSet<>();

        ModArmorTrimMaterials.MATERIAL_MAP.forEach((key, material) ->
            builder.add(
                    DataProvider.saveStable(
                            pOutput,
                            this.toJson(material),
                            this.packOutput.getOutputFolder(PackOutput.Target.DATA_PACK).resolve("minecraft").resolve("trim_material").resolve("%s.json".formatted(key.location().getPath())))
            )
        );

        return CompletableFuture.allOf(
                builder.toArray(CompletableFuture[]::new)
        );
    }

    //TODO deal with maps later (if any)
    private JsonObject toJson(TrimMaterial trimMaterial) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("asset_name", trimMaterial.assetName());
        jsonObject.add("description", Component.Serializer.toJsonTree(trimMaterial.description()));
        jsonObject.addProperty("ingredient", trimMaterial.ingredient().unwrapKey().orElseThrow().location().toString());
        jsonObject.addProperty("item_model_index", trimMaterial.itemModelIndex());
        return jsonObject;
    }

    @Override
    public String getName() {
        return "Calamos's Trim Material";
    }
}
