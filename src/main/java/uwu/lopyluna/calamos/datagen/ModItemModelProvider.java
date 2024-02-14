package uwu.lopyluna.calamos.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
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
import uwu.lopyluna.calamos.elements.ModItems;
import uwu.lopyluna.calamos.utilities.ModUtils;

import java.util.Objects;
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

        //Ingots
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
        this.basicItem(ModItems.VOLCANITE_INGOT);

        this.basicItem(ModItems.TEST_LOOTBAG);
        this.handheld32(ModItems.METEORITE_REAPER, "gui", "handheld");
        this.handheld32(ModItems.METEORITE_SWORD, "gui", "handheld");
    }

    private void separateTransform(DeferredHolder<Item, ? extends Item> item) {
        item.unwrapKey().ifPresent(
                itemName -> {
                    ResourceLocation itemModelLoc = itemName.location().withPrefix("item/");
                    ItemModelBuilder gui = super.nested().parent(new ModelFile.UncheckedModelFile(itemModelLoc.withSuffix("_gui")));
                    ItemModelBuilder twoDim = super.nested().parent(new ModelFile.UncheckedModelFile(itemModelLoc.withSuffix("_handheld")));
                    super.withExistingParent(itemModelLoc.getPath(), mcLoc("item/handheld"))
                            .customLoader(SeparateTransformsModelBuilder::begin)
                            .perspective(ItemDisplayContext.GUI, gui)
                            .perspective(ItemDisplayContext.FIXED, twoDim)
                            .base(twoDim);
                });
    }

    private void basicItem(Supplier<? extends Item> item) {
        super.basicItem(item.get());
    }

    private ItemModelBuilder basicItem(DeferredHolder<Item, ? extends Item> item, UnaryOperator<ResourceLocation> modelLocationModifier) {
        ResourceLocation name = item.getKey().location().withPrefix("item/");

        return getBuilder(modelLocationModifier.apply(name).getPath())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", ModUtils.location(modelLocationModifier.apply(name).getPath()));
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

    private ResourceLocation handheld32(DeferredHolder<Item, ? extends Item> item, String guiLocationModifier, String handheldLocationModifier) {
        return handheld(item, 32, loc -> loc.withSuffix("_" + guiLocationModifier), loc -> loc.withSuffix("_" + handheldLocationModifier));
    }

    private ResourceLocation handheld48(DeferredHolder<Item, ? extends Item> item, String guiLocationModifier, String handheldLocationModifier) {
        return handheld(item, 48, loc -> loc.withSuffix("_" + guiLocationModifier), loc -> loc.withSuffix("_" + handheldLocationModifier));
    }

    private ResourceLocation handheld64(DeferredHolder<Item, ? extends Item> item, String guiLocationModifier, String handheldLocationModifier) {
        return handheld(item, 64, loc -> loc.withSuffix("_" + guiLocationModifier), loc -> loc.withSuffix("_" + handheldLocationModifier));
    }

    private ResourceLocation handheld(DeferredHolder<Item, ? extends Item> item, int x) {
        return handheld(item, x, UnaryOperator.identity(), UnaryOperator.identity());
    }

    private ResourceLocation handheld(DeferredHolder<Item, ? extends Item> item, int x, UnaryOperator<ResourceLocation> guiLocationModifier, UnaryOperator<ResourceLocation> handheldLocationModifier) {
        ResourceLocation name = item.getKey().location().withPrefix("item/");
        super.withExistingParent(handheldLocationModifier.apply(name).getPath(), ModUtils.location("item/templates/handheld%sx".formatted(x)))
                .texture("layer0", name);
        separateTransform(item);
        basicItem(item, guiLocationModifier);
        return name;
    }
}
