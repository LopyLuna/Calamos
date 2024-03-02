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
import uwu.lopyluna.calamos.elements.ModBlocks;
import uwu.lopyluna.calamos.elements.ModDecorativeBlocks;
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
        this.registerModTags();
        this.registerForgeTags();
        this.registerMinecraftTags();
    }
    
    
    private void registerMinecraftTags() {
        tag(ItemTags.WALLS).add(
                ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE_WALL.get().asItem(),
                ModDecorativeBlocks.SOUL_SANDSTONE_WALL.get().asItem(),
                ModDecorativeBlocks.COBBLED_SANDSTONE_WALL.get().asItem(),
                ModDecorativeBlocks.METEORITE_WALL.get().asItem(),
                ModDecorativeBlocks.SMOOTH_METEORITE_WALL.get().asItem(),
                ModDecorativeBlocks.POLISHED_METEORITE_WALL.get().asItem()
        );
        tag(ItemTags.SLABS).add(
                ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE_SLAB.get().asItem(),
                ModDecorativeBlocks.SOUL_SANDSTONE_SLAB.get().asItem(),
                ModDecorativeBlocks.SMOOTH_SOUL_SANDSTONE_SLAB.get().asItem(),
                ModDecorativeBlocks.CUT_SOUL_SANDSTONE_SLAB.get().asItem(),
                ModDecorativeBlocks.COBBLED_SANDSTONE_SLAB.get().asItem(),
                ModDecorativeBlocks.METEORITE_SLAB.get().asItem(),
                ModDecorativeBlocks.SMOOTH_METEORITE_SLAB.get().asItem(),
                ModDecorativeBlocks.POLISHED_METEORITE_SLAB.get().asItem()
        );
        tag(ItemTags.STAIRS).add(
                ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE_STAIRS.get().asItem(),
                ModDecorativeBlocks.SOUL_SANDSTONE_STAIRS.get().asItem(),
                ModDecorativeBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS.get().asItem(),
                ModDecorativeBlocks.COBBLED_SANDSTONE_STAIRS.get().asItem(),
                ModDecorativeBlocks.METEORITE_STAIRS.get().asItem(),
                ModDecorativeBlocks.SMOOTH_METEORITE_STAIRS.get().asItem(),
                ModDecorativeBlocks.POLISHED_METEORITE_STAIRS.get().asItem()
        );
    }
    private void registerModTags() {
        tag(ModTags.modItemTag("wings")).add(
                ModItems.WINGS.get()
        );
        tag(ModTags.modItemTag("reapers")).add(
                ModItems.METEORITE_REAPER.get()
        );
    }

    protected void registerForgeTags() {
        tag(ItemTags.SWORDS).add(
                ModItems.METEORITE_SWORD.get()
        );
        tag(ModTags.forgeItemTag("gems")).add(
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
        tag(ModTags.forgeItemTag("storage_blocks")).add(
                ModBlocks.GARNET_BLOCK.get().asItem(),
                ModBlocks.JADE_BLOCK.get().asItem(),
                ModBlocks.KUNZITE_BLOCK.get().asItem(),
                ModBlocks.MOONSTONE_BLOCK.get().asItem(),
                ModBlocks.OPAL_BLOCK.get().asItem(),
                ModBlocks.RUBY_BLOCK.get().asItem(),
                ModBlocks.SAPPHIRE_BLOCK.get().asItem(),
                ModBlocks.SPINEL_BLOCK.get().asItem(),
                ModBlocks.SUNSTONE_BLOCK.get().asItem(),
                ModBlocks.TANZANITE_BLOCK.get().asItem(),
                ModBlocks.TOPAZ_BLOCK.get().asItem(),
                ModBlocks.URANIUM_BLOCK.get().asItem()
        );
        tag(ItemTags.TRIM_MATERIALS).add(
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
        tag(Tags.Items.INGOTS).add(
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
                ModItems.ULTIMITA_INGOT.get(),
                ModItems.VOLCANITE_INGOT.get(),
                ModItems.URANIUM_INGOT.get()
        );
        tag(Tags.Items.NUGGETS).add(
                ModItems.URANIUM_NUGGET.get()
        );
        tag(Tags.Items.RAW_MATERIALS).add(
                ModItems.RAW_METEORITE.get(),
                ModItems.RAW_URANIUM.get()
        );
        for (Triplet<TagKey<Item>, Supplier<? extends Item>, String> tag : ModItemTags.ALL_TAGS) {
            tag(tag.getA()).add(tag.getB().get());
        }
        for (Triplet<TagKey<Item>, Supplier<? extends Block>, String> tag : ModItemTags.BLOCK_ITEM_TAGS) {
            tag(tag.getA()).add(tag.getB().get().asItem());
        }
    }
}
