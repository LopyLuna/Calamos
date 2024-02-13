package uwu.lopyluna.calamos.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.loaders.SeparateTransformsModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.elements.ModItems;
import uwu.lopyluna.calamos.utilities.ModUtils;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //this.registerTrims();
        this.basicItem(ModItems.DEBUG_HEALTH);
        this.basicItem(ModItems.GARNET);
        this.basicItem(ModItems.JADE);
        this.basicItem(ModItems.KUNZITE);
        this.basicItem(ModItems.MOONSTONE);
        this.basicItem(ModItems.OPAL);
        this.basicItem(ModItems.RUBY);
        this.basicItem(ModItems.SAPPHIRE);
        this.basicItem(ModItems.SPINEL);
        this.basicItem(ModItems.SUNSTONE);
        this.basicItem(ModItems.TANZANITE);
        this.basicItem(ModItems.TOPAZ);
        this.basicItem(ModItems.METEORITE_INGOT);
        this.basicItem(ModItems.RAW_METEORITE);
        this.basicItem(ModItems.BLOOD_ORB);
        this.basicItem(ModItems.ECTOPLASMA);
        this.basicItem(ModItems.BLOODBORE_INGOT);
        this.basicItem(ModItems.TERRAULITE_INGOT);
        this.basicItem(ModItems.CALAMATIUM_INGOT);
        this.basicItem(ModItems.LEAD_INGOT);
        this.basicItem(ModItems.ECTOLIGHT_INGOT);
        this.basicItem(ModItems.MAGNETITE_INGOT);
        this.basicItem(ModItems.OBSTEEL_INGOT);
        this.basicItem(ModItems.STARINIUM_INGOT);
        this.basicItem(ModItems.ULTIMITA_INGOT);
        this.basicItem(ModItems.PLATINUM_INGOT);
        this.basicItem(ModItems.TEST_LOOTBAG);
        this.handheld32(ModItems.METEORITE_REAPER);
        this.handheld32(ModItems.METEORITE_SWORD, loc -> loc.withSuffix("_2d"));
        this.separateTransform(ModItems.METEORITE_SWORD);
    }

    private void separateTransform(DeferredHolder<Item, ? extends Item> item) {
        item.unwrapKey().ifPresent(
                itemName -> {
                    ResourceLocation itemModelLoc = itemName.location().withPrefix("item/");
                    ItemModelBuilder twoDim = super.nested().parent(new ModelFile.UncheckedModelFile(itemModelLoc.withSuffix("_2d")));
                    super.withExistingParent(itemModelLoc.getPath(), mcLoc("item/handheld"))
                            .customLoader(SeparateTransformsModelBuilder::begin)
                            .perspective(ItemDisplayContext.GUI, twoDim)
                            .perspective(ItemDisplayContext.FIXED, twoDim)
                            .base(twoDim);
                });
    }

    private void basicItem(Supplier<? extends Item> item) {
        super.basicItem(item.get());
    }

    private ResourceLocation handheld32(DeferredHolder<Item, ? extends Item> item) {
        return handheld(item, 32);
    }

    private ResourceLocation handheld48(DeferredHolder<Item, ? extends Item> item) {
        return handheld(item, 48);
    }

    private ResourceLocation handheld64(DeferredHolder<Item, ? extends Item> item) {
        return handheld(item, 64);
    }

    private ResourceLocation handheld32(DeferredHolder<Item, ? extends Item> item, UnaryOperator<ResourceLocation> modelLocationModifier) {
        return handheld(item, 32, modelLocationModifier);
    }

    private ResourceLocation handheld48(DeferredHolder<Item, ? extends Item> item, UnaryOperator<ResourceLocation> modelLocationModifier) {
        return handheld(item, 48, modelLocationModifier);
    }

    private ResourceLocation handheld64(DeferredHolder<Item, ? extends Item> item, UnaryOperator<ResourceLocation> modelLocationModifier) {
        return handheld(item, 64, modelLocationModifier);
    }

    private ResourceLocation handheld(DeferredHolder<Item, ? extends Item> item, int x) {
        return handheld(item, x, UnaryOperator.identity());
    }

    private ResourceLocation handheld(DeferredHolder<Item, ? extends Item> item, int x, UnaryOperator<ResourceLocation> modelLocationModifier) {
        ResourceLocation name = item.getKey().location().withPrefix("item/");
        super.withExistingParent(modelLocationModifier.apply(name).getPath(), ModUtils.location("item/templates/handheld%sx".formatted(x)))
                .texture("layer0", name);
        return name;
    }
}
