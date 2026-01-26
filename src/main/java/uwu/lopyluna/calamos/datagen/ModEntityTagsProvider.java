package uwu.lopyluna.calamos.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import uwu.lopyluna.calamos.CalamosMod;

import java.util.concurrent.CompletableFuture;

public class ModEntityTagsProvider extends EntityTypeTagsProvider {

    public ModEntityTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, CalamosMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.modEntityTag("magical_projectile")).add(
                EntityType.BREEZE_WIND_CHARGE,
                EntityType.SHULKER_BULLET,
                EntityType.SMALL_FIREBALL,
                EntityType.FIREBALL,
                EntityType.DRAGON_FIREBALL,
                EntityType.WIND_CHARGE
        );
    }
}
