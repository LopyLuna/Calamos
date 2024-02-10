package uwu.lopyluna.calamos.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import uwu.lopyluna.calamos.CalamosMod;

import java.util.concurrent.CompletableFuture;

public class ModDataGenerators {
    public static void gatherDataEvent(GatherDataEvent event) {
        CalamosMod.LOGGER.info("[Calamos] Data Generation starts.");
        String modId = CalamosMod.MODID;
        DataGenerator dataGenerator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        PackOutput packOutput = dataGenerator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        dataGenerator.addProvider(event.includeClient() && event.includeServer(), new ModLanguageProvider(packOutput, modId, "en_us"));
        dataGenerator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, modId, fileHelper));
        dataGenerator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, modId, fileHelper));
    }
}
