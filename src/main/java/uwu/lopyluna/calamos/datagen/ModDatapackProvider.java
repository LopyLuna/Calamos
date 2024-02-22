package uwu.lopyluna.calamos.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.worldgen.biome.CalamosBiomes;
import uwu.lopyluna.calamos.worldgen.dimension.CalamosDimensions;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDatapackProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DIMENSION_TYPE, CalamosDimensions::bootstrapType)
            .add(Registries.LEVEL_STEM, CalamosDimensions::bootstrapStem)
            .add(Registries.BIOME, CalamosBiomes::bootstrap);

    public ModDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookProvider) {
        super(output, lookProvider, BUILDER, Set.of(CalamosMod.MODID));
    }

}
