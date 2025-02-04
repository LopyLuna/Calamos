package uwu.lopyluna.calamos.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import top.theillusivec4.curios.api.CuriosDataProvider;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.concurrent.CompletableFuture;

public class ModCuriosProvider extends CuriosDataProvider {
    public ModCuriosProvider(String modId, PackOutput output, ExistingFileHelper fileHelper, CompletableFuture<HolderLookup.Provider> registries) {
        super(modId, output, fileHelper, registries);
    }

    @Override
    public void generate(HolderLookup.Provider provider, ExistingFileHelper existingFileHelper) {
        this.genSlots(provider, existingFileHelper);
        this.genEntities(provider, existingFileHelper);
    }

    public void genSlots(HolderLookup.Provider provider, ExistingFileHelper existingFileHelper) {
        this.createSlot("accessory")
                .size(8)
                .addCosmetic(true);
        this.createSlot("wings")
                .size(1)
                .addCosmetic(true);
    }

    public void genEntities(HolderLookup.Provider provider, ExistingFileHelper existingFileHelper) {
        this.createEntities("calamos_entities")
                .addPlayer()
                .addEntities(EntityType.ARMOR_STAND)
                .addSlots("wings", "accessory");
    }
}
