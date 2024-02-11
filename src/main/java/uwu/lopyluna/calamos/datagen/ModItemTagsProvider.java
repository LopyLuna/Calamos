package uwu.lopyluna.calamos.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import uwu.lopyluna.calamos.elements.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags) {
        super(pOutput, pLookupProvider, pBlockTags);
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
        this.tag(ModTags.mcItemTag("trim_materials")).add(
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
        this.tag(ModTags.forgeItemTag("ingots")).add(
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
        this.tag(ModTags.forgeItemTag("raw_materials")).add(
                ModItems.RAW_METEORITE.get()
        );
        this.tag(ModTags.forgeItemTag("gems/garnet")).add(ModItems.GARNET.get());
        this.tag(ModTags.forgeItemTag("gems/jade")).add(ModItems.JADE.get());
        this.tag(ModTags.forgeItemTag("gems/kunzite")).add(ModItems.KUNZITE.get());
        this.tag(ModTags.forgeItemTag("gems/moonstone")).add(ModItems.MOONSTONE.get());
        this.tag(ModTags.forgeItemTag("gems/opal")).add(ModItems.OPAL.get());
        this.tag(ModTags.forgeItemTag("gems/ruby")).add(ModItems.RUBY.get());
        this.tag(ModTags.forgeItemTag("gems/sapphire")).add(ModItems.SAPPHIRE.get());
        this.tag(ModTags.forgeItemTag("gems/spinel")).add(ModItems.SPINEL.get());
        this.tag(ModTags.forgeItemTag("gems/sunstone")).add(ModItems.SUNSTONE.get());
        this.tag(ModTags.forgeItemTag("gems/tanzanite")).add(ModItems.TANZANITE.get());
        this.tag(ModTags.forgeItemTag("gems/topaz")).add(ModItems.TOPAZ.get());

        this.tag(ModTags.forgeItemTag("raw_materials/meteorite")).add(ModItems.RAW_METEORITE.get());
        //ingots
        this.tag(ModTags.forgeItemTag("ingots/meteorite")).add(ModItems.METEORITE_INGOT.get());
        this.tag(ModTags.forgeItemTag("ingots/bloodbore_ingot")).add(ModItems.BLOODBORE_INGOT.get());
        this.tag(ModTags.forgeItemTag("ingots/calamatium_ingot")).add(ModItems.CALAMATIUM_INGOT.get());
        this.tag(ModTags.forgeItemTag("ingots/ectolight_ingot")).add(ModItems.ECTOLIGHT_INGOT.get());
        this.tag(ModTags.forgeItemTag("ingots/lead_ingot")).add(ModItems.LEAD_INGOT.get());
        this.tag(ModTags.forgeItemTag("ingots/magnetite_ingot")).add(ModItems.MAGNETITE_INGOT.get());
        this.tag(ModTags.forgeItemTag("ingots/obsteel_ingot")).add(ModItems.OBSTEEL_INGOT.get());
        this.tag(ModTags.forgeItemTag("ingots/platinum_ingot")).add(ModItems.PLATINUM_INGOT.get());
        this.tag(ModTags.forgeItemTag("ingots/starinium_ingot")).add(ModItems.STARINIUM_INGOT.get());
        this.tag(ModTags.forgeItemTag("ingots/terraulite_ingot")).add(ModItems.TERRAULITE_INGOT.get());
        this.tag(ModTags.forgeItemTag("ingots/ultimita_ingot")).add(ModItems.ULTIMITA_INGOT.get());
    }
}
