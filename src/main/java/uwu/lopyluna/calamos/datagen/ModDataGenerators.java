package uwu.lopyluna.calamos.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import uwu.lopyluna.calamos.CalamosMod;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModDataGenerators {
    public static void gatherDataEvent(GatherDataEvent event) {
        CalamosMod.LOGGER.info("[Calamos] Data Generation starts.");
        String modId = CalamosMod.MODID;
        DataGenerator dataGenerator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        PackOutput packOutput = dataGenerator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ModBlockTagsProvider blockTags = new ModBlockTagsProvider(packOutput, lookupProvider, modId, fileHelper);
        dataGenerator.addProvider(event.includeServer(), blockTags);
        dataGenerator.addProvider(event.includeServer(), new ModItemTagsProvider(packOutput, lookupProvider, blockTags.contentsGetter()));
        dataGenerator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(), List.of(
                new LootTableProvider.SubProviderEntry(ModBlockLootProvider::new, LootContextParamSets.BLOCK)
        ), lookupProvider));
        dataGenerator.addProvider(event.includeServer(), new ModDamageTypeTagsProvider(packOutput, lookupProvider, fileHelper));
        dataGenerator.addProvider(event.includeServer(), new ModEntityTagsProvider(packOutput, lookupProvider, fileHelper));
        dataGenerator.addProvider(event.includeClient() && event.includeServer(), new ModLanguageProvider(packOutput, modId, "en_us"));
        dataGenerator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, modId, fileHelper));
        dataGenerator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, modId, fileHelper));
        dataGenerator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput, lookupProvider));
        //dataGenerator.addProvider(event.includeServer(), new ModTrimMaterialProvider(packOutput, lookupProvider.));
        dataGenerator.addProvider(event.includeServer(), new ModDatapackProvider(packOutput, lookupProvider));
        dataGenerator.addProvider(event.includeClient(), new ModSoundsProvider(packOutput, fileHelper));
        dataGenerator.addProvider(event.includeServer(), new ModCuriosProvider(modId, packOutput, fileHelper, lookupProvider));
    }
}
