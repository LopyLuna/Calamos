package uwu.lopyluna.calamos.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.loaders.SeparateTransformsModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import uwu.lopyluna.calamos.elements.ModDecorativeBlocks;
import uwu.lopyluna.calamos.elements.ModItems;
import uwu.lopyluna.calamos.utilities.ModUtils;

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
        this.basicItem(ModItems.WINGS);

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
        this.basicItem(ModItems.STELLAR_INGOT);
        this.basicItem(ModItems.ELEGANT_BLOOM);
        this.basicItem(ModItems.URANIUM_INGOT);
        this.basicItem(ModItems.RAW_URANIUM);
        this.basicItem(ModItems.URANIUM_NUGGET);
        this.basicItem(ModItems.PALLADIUM_INGOT);
        this.basicItem(ModItems.RAW_PALLADIUM);
        this.basicItem(ModItems.PALLADIUM_NUGGET);

        //SPAWN EGGS
        //this.bossSpawnEgg(ModItems.BOONE_THE_BOOM_SPAWN_EGG);
        //this.bossSpawnEgg(ModItems.WILDFIRE_SPAWN_EGG);
        //this.SpawnEgg(ModItems. _SPAWN_EGG);


        this.basicItem(ModItems.BERSERKER_CRAW);
        this.basicItem(ModItems.TEST_LOOTBAG);
        this.handheld32(ModItems.METEORITE_REAPER, "gui", "handheld");
        this.handheld32(ModItems.METEORITE_SWORD, "gui", "handheld");

        this.handheld32(ModItems.VOLCANITE_SWORD, "gui", "handheld");

        this.basicItem(ModItems.WEAK_HEALING_POTION);
        this.basicItem(ModItems.WEAK_RECOVERING_POTION);
        this.basicItem(ModItems.HEALING_POTION);
        this.basicItem(ModItems.RECOVERING_POTION);
        this.basicItem(ModItems.DUPLEX_HEALING_POTION);
        this.basicItem(ModItems.DUPLEX_RECOVERING_POTION);
        this.basicItem(ModItems.ENHANCED_HEALING_POTION);
        this.basicItem(ModItems.ENHANCED_RECOVERING_POTION);
        this.basicItem(ModItems.SUBLIME_HEALING_POTION);
        this.basicItem(ModItems.SUBLIME_RECOVERING_POTION);

        this.basicItem(ModItems.RECALL_POTION);
        this.basicItem(ModItems.DYNAMITE);

        this.doorItem(ModDecorativeBlocks.OTHERWORLD_OAK_DOOR);
        this.basicItem(ModItems.OTHERWORLD_OAK_SIGN);
        this.basicItem(ModItems.OTHERWORLD_OAK_HANGING_SIGN);

        this.doorItem(ModDecorativeBlocks.TWILIGHT_DOOR);
        this.basicItem(ModItems.TWILIGHT_SIGN);
        this.basicItem(ModItems.TWILIGHT_HANGING_SIGN);

        this.doorItem(ModDecorativeBlocks.HOLLOW_DOOR);
        this.basicItem(ModItems.HOLLOW_SIGN);
        this.basicItem(ModItems.HOLLOW_HANGING_SIGN);

        this.basicItem(ModItems.IRRADIATED_ARROW);

        //Equipment
        this.equipmentItem(ModItems.COPPER_SWORD, ModUtils.EquipmentType.SWORD, "copper");
        this.equipmentItem(ModItems.COPPER_SHOVEL, ModUtils.EquipmentType.SHOVEL, "copper");
        this.equipmentItem(ModItems.COPPER_PICKAXE, ModUtils.EquipmentType.PICKAXE, "copper");
        this.equipmentItem(ModItems.COPPER_AXE, ModUtils.EquipmentType.AXE, "copper");
        this.equipmentItem(ModItems.COPPER_HOE, ModUtils.EquipmentType.HOE, "copper");

        this.equipmentItem(ModItems.PLATINUM_SWORD, ModUtils.EquipmentType.SWORD, "platinum");
        this.equipmentItem(ModItems.PLATINUM_SHOVEL, ModUtils.EquipmentType.SHOVEL, "platinum");
        this.equipmentItem(ModItems.PLATINUM_PICKAXE, ModUtils.EquipmentType.PICKAXE, "platinum");
        this.equipmentItem(ModItems.PLATINUM_AXE, ModUtils.EquipmentType.AXE, "platinum");
        this.equipmentItem(ModItems.PLATINUM_HOE, ModUtils.EquipmentType.HOE, "platinum");
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

    private ItemModelBuilder doorItem(Supplier<? extends Block> block) {
        String name = BuiltInRegistries.BLOCK.getKey(block.get()).getPath();
        return getBuilder(name)
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", ModUtils.location("item/" + name + "_item"));
    }

    private ItemModelBuilder equipmentItem(DeferredHolder<Item, ? extends Item> item, ModUtils.EquipmentType type, String material) {
        ModelFile.UncheckedModelFile parent = type.isHandheld() ? new ModelFile.UncheckedModelFile("item/handheld") : new ModelFile.UncheckedModelFile("item/generated");
        return getBuilder(item.getKey().location().getPath())
                .parent(parent)
                .texture("layer0", ModUtils.toolTextureLoc(type, material));
    }
}
