package uwu.lopyluna.calamos.worldgen.biome;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.SurfaceRules;
import uwu.lopyluna.calamos.elements.ModBlocks;

import java.util.function.Supplier;

public class CalamosSurfaceRules {

    /**
     * Usable references for future development:
     * https://github.com/Glitchfiend/TerraBlender/blob/1.20.4/Example/NeoForge/src/main/java/terrablender/example/TestSurfaceRuleData.java
     * https://github.com/TheForsakenFurby/Surface-Rules-Guide-Minecraft-JE-1.18/blob/main/Guide.md
     */

    private static final SurfaceRules.RuleSource OTHERWORLD_DIRT = makeStateRule(ModBlocks.OTHERWORLD_DIRT);
    private static final SurfaceRules.RuleSource OTHERWORLD_GRASS_BLOCK = makeStateRule(ModBlocks.OTHERWORLD_GRASS_BLOCK);

    public static SurfaceRules.RuleSource makeRules()
    {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, OTHERWORLD_GRASS_BLOCK), OTHERWORLD_DIRT);

        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(CalamosBiomes.OTHERWORLD_PLAINS), grassSurface)
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Supplier<? extends Block> block)
    {
        return SurfaceRules.state(block.get().defaultBlockState());
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block)
    {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
