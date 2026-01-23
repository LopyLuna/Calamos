package uwu.lopyluna.calamos.worldgen.dimension;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.worldgen.biome.CalamosBiomes;

import java.util.OptionalLong;

public class CalamosDimensions {


    public static final ResourceKey<LevelStem> CALAMOS = registerKey("calamosdim");
    public static final ResourceKey<Level> CALAMOS_LEVEL = registerLevelKey("calamosdim");
    public static final ResourceKey<DimensionType> CALAMOS_TYPE = registerTypeKey("calamosdim_type");

    public static final ResourceKey<LevelStem> OTHERWORLD = registerKey("otherworld");
    public static final ResourceKey<Level> OTHERWORLD_LEVEL = registerLevelKey("otherworld");
    public static final ResourceKey<DimensionType> OTHERWORLD_TYPE = registerTypeKey("otherworld_type");

    /**
     * Look at {@link net.minecraft.data.worldgen.DimensionTypes}
     */
    public static void bootstrapType(BootstrapContext<DimensionType> context) {
        context.register(CALAMOS_TYPE, new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0D,
                true,
                true,
                -64,
                384,
                384,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)
        ));

        context.register(OTHERWORLD_TYPE, new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0D,
                true,
                true,
                -64,
                384,
                384,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)
        ));
    }

    public static void bootstrapStem(BootstrapContext<LevelStem> context) {
        HolderGetter<Biome> biome = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> type = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noise = context.lookup(Registries.NOISE_SETTINGS);

        NoiseBasedChunkGenerator generator = new NoiseBasedChunkGenerator(new FixedBiomeSource(biome.getOrThrow(CalamosBiomes.TEST_BIOME)), noise.getOrThrow(NoiseGeneratorSettings.OVERWORLD));
        NoiseBasedChunkGenerator otherworldGenerator = new NoiseBasedChunkGenerator(new FixedBiomeSource(biome.getOrThrow(CalamosBiomes.OTHERWORLD_PLAINS)), noise.getOrThrow(NoiseGeneratorSettings.OVERWORLD));

        LevelStem stem = new LevelStem(type.getOrThrow(CALAMOS_TYPE), generator);
        LevelStem otherworldStem = new LevelStem(type.getOrThrow(OTHERWORLD_TYPE), otherworldGenerator);

        context.register(CALAMOS, stem);
        context.register(OTHERWORLD, otherworldStem);
    }


    private static ResourceKey<LevelStem> registerKey(String name) {
        return ResourceKey.create(Registries.LEVEL_STEM, CalamosMod.asResource(name));
    }

    private static ResourceKey<Level> registerLevelKey(String name) {
        return ResourceKey.create(Registries.DIMENSION, CalamosMod.asResource(name));
    }

    private static ResourceKey<DimensionType> registerTypeKey(String name) {
        return ResourceKey.create(Registries.DIMENSION_TYPE, CalamosMod.asResource(name));
    }

}
