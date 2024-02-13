package uwu.lopyluna.calamos.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import oshi.util.tuples.Triplet;
import uwu.lopyluna.calamos.elements.ModItems;
import uwu.lopyluna.calamos.elements.tag.ModItemTags;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags) {
        super(pOutput, pLookupProvider, pBlockTags);
        ModItemTags.staticInit();
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        //this.registerModTags();
        this.registerForgeTags();
    }

    private void registerModTags() {
    }

    protected void registerForgeTags() {
        this.tag(ModTags.forgeItemTag("gems")).add(
                ModItems.GARNET.get(),
                ModItems.JADE.get(),
                ModItems.KUNZITE.get(),
                ModItems.MOONSTONE.get(),
                ModItems.OPAL.get(),
                ModItems.RUBY.get(),
                ModItems.SAPPHIRE.get(),
                ModItems.SPINEL.get(),
                ModItems.SUNSTONE.get(),
                ModItems.TANZANITE.get(),
                ModItems.TOPAZ.get()
        );
        this.tag(ItemTags.TRIM_MATERIALS).add(
                ModItems.GARNET.get(),
                ModItems.JADE.get(),
                ModItems.KUNZITE.get(),
                ModItems.MOONSTONE.get(),
                ModItems.OPAL.get(),
                ModItems.RUBY.get(),
                ModItems.SAPPHIRE.get(),
                ModItems.SPINEL.get(),
                ModItems.SUNSTONE.get(),
                ModItems.TANZANITE.get(),
                ModItems.TOPAZ.get()
        );
        this.tag(Tags.Items.INGOTS).add(
                ModItems.METEORITE_INGOT.get(),
                ModItems.BLOODBORE_INGOT.get(),
                ModItems.CALAMATIUM_INGOT.get(),
                ModItems.ECTOLIGHT_INGOT.get(),
                ModItems.LEAD_INGOT.get(),
                ModItems.MAGNETITE_INGOT.get(),
                ModItems.OBSTEEL_INGOT.get(),
                ModItems.PLATINUM_INGOT.get(),
                ModItems.STARINIUM_INGOT.get(),
                ModItems.TERRAULITE_INGOT.get(),
                ModItems.ULTIMITA_INGOT.get()
        );
        this.tag(Tags.Items.RAW_MATERIALS).add(
                ModItems.RAW_METEORITE.get()
        );
        for (Triplet<TagKey<Item>, Supplier<? extends Item>, String> tag : ModItemTags.ALL_TAGS) {
            super.tag(tag.getA()).add(tag.getB().get());
        }
    }
}
