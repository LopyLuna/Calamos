package uwu.lopyluna.calamos.datagen.recipe;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.datagen.ModTags;
import uwu.lopyluna.calamos.datagen.recipe.builder.ModSingleItemRecipeBuilder;
import uwu.lopyluna.calamos.elements.ModBlocks;
import uwu.lopyluna.calamos.elements.ModDecorativeBlocks;
import uwu.lopyluna.calamos.elements.ModItems;

import java.util.List;

@SuppressWarnings({"all", "unused"})
public class CuttingProvider {
    private static ResourceLocation location(String path, int type) {
        String typeStr = switch (type) {case 1 -> "saw_milling";case 2 -> "metal_grinding";default -> "stonecutting";};
        return CalamosMod.asResource(typeStr + "/" + path);
    }
    public static void register(RecipeOutput consumer) {
        recipesStonecutting(consumer);
        recipesSawmilling(consumer);
        recipesMetalGrinding(consumer);
    }

    private static void recipesStonecutting(RecipeOutput consumer) {
        addStoneCutting(consumer, ModDecorativeBlocks.BASALT_BRICKS, 1, Items.BASALT, RecipeCategory.BUILDING_BLOCKS).save(consumer, stonecuttingLocation("bricks", "basalt", "minecraft"));
        addStoneCutting(consumer, ModDecorativeBlocks.CUT_BASALT, 1, Items.BASALT, RecipeCategory.BUILDING_BLOCKS).save(consumer, stonecuttingLocation("cut", "basalt", "minecraft"));

    }
    
    private static void recipesSawmilling(RecipeOutput consumer) {


        sawmillLogWoodSet(consumer, Items.ACACIA_LOG, true, false, false);
        sawmillLogWoodSet(consumer, Items.BIRCH_LOG, true, false, false);
        sawmillLogWoodSet(consumer, Items.DARK_OAK_LOG, true, false, false);
        sawmillLogWoodSet(consumer, Items.JUNGLE_LOG, true, false, false);
        sawmillLogWoodSet(consumer, Items.OAK_LOG, true, false, false);
        sawmillLogWoodSet(consumer, Items.SPRUCE_LOG, true, false, false);
        sawmillLogWoodSet(consumer, Items.MANGROVE_LOG, true, false, false);
        sawmillLogWoodSet(consumer, Items.CRIMSON_STEM, true, false, true);
        sawmillLogWoodSet(consumer, Items.WARPED_STEM, true, false, true);
        sawmillLogWoodSet(consumer, Items.CHERRY_LOG, true, false, false);
        sawmillLogWoodSet(consumer, ModDecorativeBlocks.OTHERWORLD_OAK_LOG, true, false, false);
        sawmillLogWoodSet(consumer, ModDecorativeBlocks.TWILIGHT_LOG, true, false, false);
        sawmillLogWoodSet(consumer, ModDecorativeBlocks.HOLLOW_LOG, true, false, false);

        sawmillLogWoodSet(consumer, Items.STRIPPED_ACACIA_LOG, true, true, false);
        sawmillLogWoodSet(consumer, Items.STRIPPED_BIRCH_LOG, true, true, false);
        sawmillLogWoodSet(consumer, Items.STRIPPED_DARK_OAK_LOG, true, true, false);
        sawmillLogWoodSet(consumer, Items.STRIPPED_JUNGLE_LOG, true, true, false);
        sawmillLogWoodSet(consumer, Items.STRIPPED_OAK_LOG, true, true, false);
        sawmillLogWoodSet(consumer, Items.STRIPPED_SPRUCE_LOG, true, true, false);
        sawmillLogWoodSet(consumer, Items.STRIPPED_MANGROVE_LOG, true, true, false);
        sawmillLogWoodSet(consumer, Items.STRIPPED_CRIMSON_STEM, true, true, true);
        sawmillLogWoodSet(consumer, Items.STRIPPED_WARPED_STEM, true, true, true);
        sawmillLogWoodSet(consumer, Items.STRIPPED_CHERRY_LOG, true, true, false);
        sawmillLogWoodSet(consumer, ModDecorativeBlocks.STRIPPED_OTHERWORLD_OAK_LOG, true, true, false);
        sawmillLogWoodSet(consumer, ModDecorativeBlocks.STRIPPED_TWILIGHT_LOG, true, true, false);
        sawmillLogWoodSet(consumer, ModDecorativeBlocks.STRIPPED_HOLLOW_LOG, true, true, false);

        //sawmillWoodSet(consumer, Items.BAMBOO_LOG, true);
        //sawmillWoodSet(consumer, Items.CRIMSON_LOG, true);
        //sawmillWoodSet(consumer, Items.WARPED_LOG, true);

        sawmillWoodSet(consumer, Items.ACACIA_PLANKS, true);
        sawmillWoodSet(consumer, Items.BIRCH_PLANKS, true);
        sawmillWoodSet(consumer, Items.BAMBOO_PLANKS, true);
        sawmillWoodSet(consumer, Items.CRIMSON_PLANKS, true);
        sawmillWoodSet(consumer, Items.DARK_OAK_PLANKS, true);
        sawmillWoodSet(consumer, Items.JUNGLE_PLANKS, true);
        sawmillWoodSet(consumer, Items.OAK_PLANKS, true);
        sawmillWoodSet(consumer, Items.SPRUCE_PLANKS, true);
        sawmillWoodSet(consumer, Items.WARPED_PLANKS, true);
        sawmillWoodSet(consumer, Items.MANGROVE_PLANKS, true);
        sawmillWoodSet(consumer, Items.CHERRY_PLANKS, true);
        sawmillWoodSet(consumer, ModDecorativeBlocks.OTHERWORLD_OAK_PLANKS, true);
        sawmillWoodSet(consumer, ModDecorativeBlocks.TWILIGHT_PLANKS, true);
        sawmillWoodSet(consumer, ModDecorativeBlocks.HOLLOW_PLANKS, true);

        sawmillStairsSlab(consumer, ModDecorativeBlocks.LARGE_OTHERWORLD_OAK_PLANKS);
        sawmillStairsSlab(consumer, ModDecorativeBlocks.LARGE_TWILIGHT_PLANKS);
        sawmillStairsSlab(consumer, ModDecorativeBlocks.LARGE_HOLLOW_PLANKS);
        sawmillStairsSlab(consumer, ModDecorativeBlocks.LARGE_ACACIA_PLANKS);
        sawmillStairsSlab(consumer, ModDecorativeBlocks.LARGE_BIRCH_PLANKS);
        sawmillStairsSlab(consumer, ModDecorativeBlocks.LARGE_BAMBOO_PLANKS);
        sawmillStairsSlab(consumer, ModDecorativeBlocks.LARGE_CRIMSON_PLANKS);
        sawmillStairsSlab(consumer, ModDecorativeBlocks.LARGE_DARK_OAK_PLANKS);
        sawmillStairsSlab(consumer, ModDecorativeBlocks.LARGE_JUNGLE_PLANKS);
        sawmillStairsSlab(consumer, ModDecorativeBlocks.LARGE_OAK_PLANKS);
        sawmillStairsSlab(consumer, ModDecorativeBlocks.LARGE_SPRUCE_PLANKS);
        sawmillStairsSlab(consumer, ModDecorativeBlocks.LARGE_WARPED_PLANKS);
        sawmillStairsSlab(consumer, ModDecorativeBlocks.LARGE_MANGROVE_PLANKS);
        sawmillStairsSlab(consumer, ModDecorativeBlocks.LARGE_CHERRY_PLANKS);
    }
    
    private static void recipesMetalGrinding(RecipeOutput consumer) {
        addMetalGrinding(consumer, ModDecorativeBlocks.POLISHED_AMETHYST, 1, Items.AMETHYST_BLOCK, RecipeCategory.BUILDING_BLOCKS).save(consumer, metalGrindingLocation("polished", "amethyst", "minecraft"));
        addMetalGrinding(consumer, ModDecorativeBlocks.AMETHYST_BRICKS, 1, Items.AMETHYST_BLOCK, RecipeCategory.BUILDING_BLOCKS).save(consumer, metalGrindingLocation("bricks", "amethyst", "minecraft"));
        addMetalGrinding(consumer, ModDecorativeBlocks.CUT_AMETHYST, 1, Items.AMETHYST_BLOCK, RecipeCategory.BUILDING_BLOCKS).save(consumer, metalGrindingLocation("cut", "amethyst", "minecraft"));
        addMetalGrinding(consumer, ModDecorativeBlocks.OBSIDIAN_BRICKS, 1, Items.OBSIDIAN, RecipeCategory.BUILDING_BLOCKS).save(consumer, metalGrindingLocation("bricks", "obsidian", "minecraft"));
        addMetalGrinding(consumer, ModDecorativeBlocks.CRYING_OBSIDIAN_BRICKS, 1, Items.CRYING_OBSIDIAN, RecipeCategory.BUILDING_BLOCKS).save(consumer, metalGrindingLocation("bricks", "obsidian/crying", "minecraft"));


        // TAG CONVERSIONS TO MOD ITEMS
        addMetalGrinding(consumer, ModItems.URANIUM_INGOT, 1, ModTags.commonItemTag("ingots/uranium"), ModItems.URANIUM_INGOT, RecipeCategory.MISC).save(consumer, metalGrindingLocation("tag_conversion/ingot", "uranium", "calamos"));
        addMetalGrinding(consumer, ModItems.VOLCANITE_INGOT, 1, ModTags.commonItemTag("ingots/volcanite"), ModItems.VOLCANITE_INGOT, RecipeCategory.MISC).save(consumer, metalGrindingLocation("tag_conversion/ingot", "volcanite", "calamos"));
        addMetalGrinding(consumer, ModItems.ULTIMITA_INGOT, 1, ModTags.commonItemTag("ingots/ultimita"), ModItems.ULTIMITA_INGOT, RecipeCategory.MISC).save(consumer, metalGrindingLocation("tag_conversion/ingot", "ultimita", "calamos"));
        addMetalGrinding(consumer, ModItems.TERRAULITE_INGOT, 1, ModTags.commonItemTag("ingots/terraulite"), ModItems.TERRAULITE_INGOT, RecipeCategory.MISC).save(consumer, metalGrindingLocation("tag_conversion/ingot", "terraulite", "calamos"));
        addMetalGrinding(consumer, ModItems.STARINIUM_INGOT, 1, ModTags.commonItemTag("ingots/starinium"), ModItems.STARINIUM_INGOT, RecipeCategory.MISC).save(consumer, metalGrindingLocation("tag_conversion/ingot", "starinium", "calamos"));
        addMetalGrinding(consumer, ModItems.PLATINUM_INGOT, 1, ModTags.commonItemTag("ingots/platinum"), ModItems.PLATINUM_INGOT, RecipeCategory.MISC).save(consumer, metalGrindingLocation("tag_conversion/ingot", "platinum", "calamos"));
        addMetalGrinding(consumer, ModItems.OBSTEEL_INGOT, 1, ModTags.commonItemTag("ingots/obsteel"), ModItems.OBSTEEL_INGOT, RecipeCategory.MISC).save(consumer, metalGrindingLocation("tag_conversion/ingot", "obsteel", "calamos"));
        addMetalGrinding(consumer, ModItems.MAGNETITE_INGOT, 1, ModTags.commonItemTag("ingots/magnetite"), ModItems.MAGNETITE_INGOT, RecipeCategory.MISC).save(consumer, metalGrindingLocation("tag_conversion/ingot", "magnetite", "calamos"));
        addMetalGrinding(consumer, ModItems.LEAD_INGOT, 1, ModTags.commonItemTag("ingots/lead"), ModItems.LEAD_INGOT, RecipeCategory.MISC).save(consumer, metalGrindingLocation("tag_conversion/ingot", "lead", "calamos"));
        addMetalGrinding(consumer, ModItems.METEORITE_INGOT, 1, ModTags.commonItemTag("ingots/meteorite"), ModItems.METEORITE_INGOT, RecipeCategory.MISC).save(consumer, metalGrindingLocation("tag_conversion/ingot", "meteorite", "calamos"));
        addMetalGrinding(consumer, ModItems.BLOODBORE_INGOT, 1, ModTags.commonItemTag("ingots/bloodbore"), ModItems.BLOODBORE_INGOT, RecipeCategory.MISC).save(consumer, metalGrindingLocation("tag_conversion/ingot", "bloodbore", "calamos"));
        addMetalGrinding(consumer, ModItems.CALAMATIUM_INGOT, 1, ModTags.commonItemTag("ingots/calamatium"), ModItems.CALAMATIUM_INGOT, RecipeCategory.MISC).save(consumer, metalGrindingLocation("tag_conversion/ingot", "calamatium", "calamos"));
        addMetalGrinding(consumer, ModItems.ECTOLIGHT_INGOT, 1, ModTags.commonItemTag("ingots/ectolight"), ModItems.ECTOLIGHT_INGOT, RecipeCategory.MISC).save(consumer, metalGrindingLocation("tag_conversion/ingot", "ectolight", "calamos"));
        addMetalGrinding(consumer, ModItems.ELEGANT_BLOOM, 1, ModTags.commonItemTag("ingots/elegant"), ModItems.ELEGANT_BLOOM, RecipeCategory.MISC).save(consumer, metalGrindingLocation("tag_conversion/ingot", "elegant", "calamos"));
        addMetalGrinding(consumer, ModItems.STELLAR_INGOT, 1, ModTags.commonItemTag("ingots/stellar"), ModItems.STELLAR_INGOT, RecipeCategory.MISC).save(consumer, metalGrindingLocation("tag_conversion/ingot", "stellar", "calamos"));
        addMetalGrinding(consumer, ModItems.PALLADIUM_INGOT, 1, ModTags.commonItemTag("ingots/palladium"), ModItems.PALLADIUM_INGOT, RecipeCategory.MISC).save(consumer, metalGrindingLocation("tag_conversion/ingot", "palladium", "calamos"));

        addMetalGrinding(consumer, ModBlocks.URANIUM_BLOCK, 1, ModTags.commonItemTag("storage_blocks/uranium"), ModBlocks.URANIUM_BLOCK, RecipeCategory.BUILDING_BLOCKS).save(consumer, metalGrindingLocation("tag_conversion/block", "uranium", "calamos"));
        addMetalGrinding(consumer, ModBlocks.PALLADIUM_BLOCK, 1, ModTags.commonItemTag("storage_blocks/palladium"), ModBlocks.PALLADIUM_BLOCK, RecipeCategory.BUILDING_BLOCKS).save(consumer, metalGrindingLocation("tag_conversion/block", "palladium", "calamos"));
    }
    
    private static void sawmillStairsSlab(RecipeOutput consumer, ItemLike initialWood) {
        String name = getItemName(initialWood);
        String modName = getItemModName(initialWood);
        String woodTypeName = name.replace("_planks", "").replace("large_", "");
        String stairsName = modName + ":" + name.replace("_planks", "_stairs");
        ItemLike stairs = getItem(stairsName);
        String slabName = modName + ":" + name.replace("_planks", "_slab");
        ItemLike slab = getItem(slabName);
        addSawMilling(consumer, stairs, 1, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "from_planks/large/stairs", modName));
        addSawMilling(consumer, slab, 2, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "from_planks/large/slab", modName));
    }

    private static void sawmillLogWoodSet(RecipeOutput consumer, ItemLike initialWood, boolean hasLarge, boolean isStripped, boolean isStem) {
        String name = getItemName(initialWood);
        String modName = getItemModName(initialWood);

        String woodTypeName = (isStem ? name.replace("_stem", "") : name.replace("_log", "")).replace("stripped_", "");

        String chest_name = "minecraft:chest";
        ItemLike chest = getItem(chest_name);

        List<String> allowedNamespaces = List.of("minecraft", "calamos");
        boolean isCompat = !allowedNamespaces.contains(modName);

        if (!isStripped) {
            String planksName = isStem ? modName + ":" + name.replace("_stem", "_planks") : modName + ":" + name.replace("_log", "_planks");ItemLike planks = getItem(planksName);
            String strippedLogName = modName + ":stripped_" + name;ItemLike strippedLog = getItem(strippedLogName);

            String stairsName = isStem ? modName + ":" + name.replace("_stem", "_stairs") : modName + ":" + name.replace("_log", "_stairs");ItemLike stairs = getItem(stairsName);
            String slabName = isStem ? modName + ":" + name.replace("_stem", "_slab") : modName + ":" + name.replace("_log", "_slab");ItemLike slab = getItem(slabName);
            String fenceName = isStem ? modName + ":" + name.replace("_stem", "_fence") : modName + ":" + name.replace("_log", "_fence");ItemLike fence = getItem(fenceName);
            String fenceGateName = isStem ? modName + ":" + name.replace("_stem", "_fence_gate") : modName + ":" + name.replace("_log", "_fence_gate");ItemLike fenceGate = getItem(fenceGateName);
            String doorName = isStem ? modName + ":" + name.replace("_stem", "_door") : modName + ":" + name.replace("_log", "_door");ItemLike door = getItem(doorName);
            String trapdoorName = isStem ? modName + ":" + name.replace("_stem", "_trapdoor") : modName + ":" + name.replace("_log", "_trapdoor");ItemLike trapdoor = getItem(trapdoorName);
            String buttonName = isStem ? modName + ":" + name.replace("_stem", "_button") : modName + ":" + name.replace("_log", "_button");ItemLike button = getItem(buttonName);
            String pressurePlateName = isStem ? modName + ":" + name.replace("_stem", "_pressure_plate") : modName + ":" + name.replace("_log", "_pressure_plate");ItemLike pressurePlate = getItem(pressurePlateName);
            String signName = isStem ? modName + ":" + name.replace("_stem", "_sign") : modName + ":" + name.replace("_log", "_sign");ItemLike sign = getItem(signName);
            String hangingSignName = isStem ? modName + ":" + name.replace("_stem", "_hanging_sign") : modName + ":" + name.replace("_log", "_hanging_sign");ItemLike hangingSign = getItem(hangingSignName);

            if (hasLarge) {
                String largePlanksName = isStem ? "calamos:large_" + name.replace("_stem", "_planks") : "calamos:large_" + name.replace("_log", "_planks");
                ItemLike largePlanks = getItem(largePlanksName);
                String largeStairsName = isStem ? "calamos:large_" + name.replace("_stem", "_stairs") : "calamos:large_" + name.replace("_log", "_stairs");
                ItemLike largeStairs = getItem(largeStairsName);
                String largeSlabName = isStem ? "calamos:large_" + name.replace("_stem", "_slab") : "calamos:large_" + name.replace("_log", "_slab");
                ItemLike largeSlab = getItem(largeSlabName);
                addSawMilling(consumer, largePlanks, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "large_planks", modName));
                addSawMilling(consumer, largeStairs, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "large_stairs", modName));
                addSawMilling(consumer, largeSlab, 8, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "large_slab", modName));
            }
            addSawMilling(consumer, planks, 6, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "planks", modName));
            addSawMilling(consumer, strippedLog, 1, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "stripped_log", modName));

            addSawMilling(consumer, stairs, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "stairs", modName));
            addSawMilling(consumer, slab, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "slab", modName));
            addSawMilling(consumer, fence, 12, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "fence", modName));
            addSawMilling(consumer, fenceGate, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "fence_gate", modName));
            addSawMilling(consumer, door, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "door", modName));
            addSawMilling(consumer, trapdoor, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "trapdoor", modName));
            addSawMilling(consumer, button, 32, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "button", modName));
            addSawMilling(consumer, pressurePlate, 16, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "pressure_plate", modName));
            addSawMilling(consumer, sign, 16, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "sign", modName));
            addSawMilling(consumer, hangingSign, 8, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "hanging_sign", modName));

            addSawMilling(consumer, chest, 1, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "chest", modName));
        } else {
            String s_planksName = isStem ? modName + ":" + name.replace("_stem", "_planks").replace("stripped_", "") : modName + ":" + name.replace("_log", "_planks").replace("stripped_", "");ItemLike s_planks = getItem(s_planksName);

            String s_stairsName = isStem ? modName + ":" + name.replace("_stem", "_stairs").replace("stripped_", "") : modName + ":" + name.replace("_log", "_stairs").replace("stripped_", "");ItemLike s_stairs = getItem(s_stairsName);
            String s_slabName = isStem ? modName + ":" + name.replace("_stem", "_slab").replace("stripped_", "") : modName + ":" + name.replace("_log", "_slab").replace("stripped_", "");ItemLike s_slab = getItem(s_slabName);
            String s_fenceName = isStem ? modName + ":" + name.replace("_stem", "_fence").replace("stripped_", "") : modName + ":" + name.replace("_log", "_fence").replace("stripped_", "");ItemLike s_fence = getItem(s_fenceName);
            String s_fenceGateName = isStem ? modName + ":" + name.replace("_stem", "_fence_gate").replace("stripped_", "") : modName + ":" + name.replace("_log", "_fence_gate").replace("stripped_", "");ItemLike s_fenceGate = getItem(s_fenceGateName);
            String s_doorName = isStem ? modName + ":" + name.replace("_stem", "_door").replace("stripped_", "") : modName + ":" + name.replace("_log", "_door").replace("stripped_", "");ItemLike s_door = getItem(s_doorName);
            String s_trapdoorName = isStem ? modName + ":" + name.replace("_stem", "_trapdoor").replace("stripped_", "") : modName + ":" + name.replace("_log", "_trapdoor").replace("stripped_", "");ItemLike s_trapdoor = getItem(s_trapdoorName);
            String s_buttonName = isStem ? modName + ":" + name.replace("_stem", "_button").replace("stripped_", "") : modName + ":" + name.replace("_log", "_button").replace("stripped_", "");ItemLike s_button = getItem(s_buttonName);
            String s_pressurePlateName = isStem ? modName + ":" + name.replace("_stem", "_pressure_plate").replace("stripped_", "") : modName + ":" + name.replace("_log", "_pressure_plate").replace("stripped_", "");ItemLike s_pressurePlate = getItem(s_pressurePlateName);
            String s_signName = isStem ? modName + ":" + name.replace("_stem", "_sign").replace("stripped_", "") : modName + ":" + name.replace("_log", "_sign").replace("stripped_", "");ItemLike s_sign = getItem(s_signName);
            String s_hangingSignName = isStem ? modName + ":" + name.replace("_stem", "_hanging_sign").replace("stripped_", "") : modName + ":" + name.replace("_log", "_hanging_sign").replace("stripped_", "");ItemLike s_hangingSign = getItem(s_hangingSignName);

            if (hasLarge) {
                String largePlanksName = isStem ? "calamos:large_" + name.replace("_stem", "_planks").replace("stripped_", "") : "calamos:large_" + name.replace("_log", "_planks").replace("stripped_", "");
                ItemLike largePlanks = getItem(largePlanksName);
                String largeStairsName = isStem ? "calamos:large_" + name.replace("_stem", "_stairs").replace("stripped_", "") : "calamos:large_" + name.replace("_log", "_stairs").replace("stripped_", "");
                ItemLike largeStairs = getItem(largeStairsName);
                String largeSlabName = isStem ? "calamos:large_" + name.replace("_stem", "_slab").replace("stripped_", "") : "calamos:large_" + name.replace("_log", "_slab").replace("stripped_", "");
                ItemLike largeSlab = getItem(largeSlabName);
                addSawMilling(consumer, largePlanks, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "large_planks_from_stripped", modName));
                addSawMilling(consumer, largeStairs, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "large_stairs_from_stripped", modName));
                addSawMilling(consumer, largeSlab, 8, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "large_slab_from_stripped", modName));
            }
            addSawMilling(consumer, s_planks, 6, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "planks_from_stripped", modName));
            addSawMilling(consumer, s_stairs, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "stairs_from_stripped", modName));
            addSawMilling(consumer, s_slab, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "slab_from_stripped", modName));
            addSawMilling(consumer, s_fence, 12, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "fence_from_stripped", modName));
            addSawMilling(consumer, s_fenceGate, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "fence_gate_from_stripped", modName));
            addSawMilling(consumer, s_door, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "door_from_stripped", modName));
            addSawMilling(consumer, s_trapdoor, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "trapdoor_from_stripped", modName));
            addSawMilling(consumer, s_button, 32, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "button_from_stripped", modName));
            addSawMilling(consumer, s_pressurePlate, 16, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "pressure_plate_from_stripped", modName));
            addSawMilling(consumer, s_sign, 16, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "sign_from_stripped", modName));
            addSawMilling(consumer, s_hangingSign, 8, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "hanging_sign_from_stripped", modName));

            addSawMilling(consumer, chest, 1, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "chest_from_stripped", modName));
        }
    }

    private static void sawmillWoodSet(RecipeOutput consumer, ItemLike initialWood, boolean hasLarge) {
        String name = getItemName(initialWood);
        String modName = getItemModName(initialWood);
        String woodTypeName = name.replace("_planks", "");
        String stairsName = modName + ":" + name.replace("_planks", "_stairs");
        ItemLike stairs = getItem(stairsName);
        String slabName = modName + ":" + name.replace("_planks", "_slab");
        ItemLike slab = getItem(slabName);
        String fenceName = modName + ":" + name.replace("_planks", "_fence");
        ItemLike fence = getItem(fenceName);
        String fenceGateName = modName + ":" + name.replace("_planks", "_fence_gate");
        ItemLike fenceGate = getItem(fenceGateName);
        String doorName = modName + ":" + name.replace("_planks", "_door");
        ItemLike door = getItem(doorName);
        String trapdoorName = modName + ":" + name.replace("_planks", "_trapdoor");
        ItemLike trapdoor = getItem(trapdoorName);
        String buttonName = modName + ":" + name.replace("_planks", "_button");
        ItemLike button = getItem(buttonName);
        String pressurePlateName = modName + ":" + name.replace("_planks", "_pressure_plate");
        ItemLike pressurePlate = getItem(pressurePlateName);
        String signName = modName + ":" + name.replace("_planks", "_sign");
        ItemLike sign = getItem(signName);
        String hangingSignName = modName + ":" + name.replace("_planks", "_hanging_sign");
        ItemLike hangingSign = getItem(hangingSignName);
        ItemLike chest = getItem("minecraft:chest");
        if (hasLarge) {
            String largePlanksName = "calamos:large_" + name;
            ItemLike largePlanks = getItem(largePlanksName);
            String largeStairsName = "calamos:large_" + name.replace("_planks", "_stairs");
            ItemLike largeStairs = getItem(largeStairsName);
            String largeSlabName = "calamos:large_" + name.replace("_planks", "_slab");
            ItemLike largeSlab = getItem(largeSlabName);
            addSawMilling(consumer, largePlanks, 1, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "from_planks/large_planks", modName));
            addSawMilling(consumer, largeStairs, 1, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "from_planks/large_stairs", modName));
            addSawMilling(consumer, largeSlab, 2, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "from_planks/large_slab", modName));
        }
        addSawMilling(consumer, stairs, 1, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "from_planks/stairs", modName));
        addSawMilling(consumer, slab, 2, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "from_planks/slab", modName));
        addSawMilling(consumer, fence, 3, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "from_planks/fence", modName));
        addSawMilling(consumer, fenceGate, 1, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "from_planks/fence_gate", modName));
        addSawMilling(consumer, door, 1, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "from_planks/door", modName));
        addSawMilling(consumer, trapdoor, 1, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "from_planks/trapdoor", modName));
        addSawMilling(consumer, button, 8, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "from_planks/button", modName));
        addSawMilling(consumer, pressurePlate, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "from_planks/pressure_plate", modName));
        addSawMilling(consumer, sign, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "from_planks/sign", modName));
        addSawMilling(consumer, hangingSign, 2, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, sawmillLocation(woodTypeName, "from_planks/hanging_sign", modName));
    }
    
    private static SingleItemRecipeBuilder addStoneCutting(RecipeOutput consumer, ItemLike result, int count, ItemLike input, RecipeCategory pCategory) {
        String name = input.asItem().toString().split(":")[1];
        return SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), pCategory, result, count).unlockedBy("has_" + name, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    private static SingleItemRecipeBuilder addStoneCutting(RecipeOutput consumer, ItemLike result, int count, Ingredient pIngredient, ItemLike display, RecipeCategory pCategory) {
        String name = display.asItem().toString().split(":")[1];
        return SingleItemRecipeBuilder.stonecutting(pIngredient, pCategory, result, count).unlockedBy("has_" + name, InventoryChangeTrigger.TriggerInstance.hasItems(display));
    }
    private static ModSingleItemRecipeBuilder addSawMilling(RecipeOutput consumer, ItemLike result, int count, ItemLike input, RecipeCategory pCategory) {
        String name = input.asItem().toString().split(":")[1];
        return ModSingleItemRecipeBuilder.sawMilling(Ingredient.of(input), pCategory, result, count).unlockedBy("has_" + name, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    private static ModSingleItemRecipeBuilder addSawMilling(RecipeOutput consumer, ItemLike result, int count, Ingredient pIngredient, ItemLike display, RecipeCategory pCategory) {
        String name = display.asItem().toString().split(":")[1];
        return ModSingleItemRecipeBuilder.sawMilling(pIngredient, pCategory, result, count).unlockedBy("has_" + name, InventoryChangeTrigger.TriggerInstance.hasItems(display));
    }
    private static ModSingleItemRecipeBuilder addMetalGrinding(RecipeOutput consumer, ItemLike result, int count, ItemLike input, RecipeCategory pCategory) {
        String name = input.asItem().toString().split(":")[1];
        return ModSingleItemRecipeBuilder.metalGrinding(Ingredient.of(input), pCategory, result, count).unlockedBy("has_" + name, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    private static ModSingleItemRecipeBuilder addMetalGrinding(RecipeOutput consumer, ItemLike result, int count, Ingredient pIngredient, ItemLike display, RecipeCategory pCategory) {
        String name = display.asItem().toString().split(":")[1];
        return ModSingleItemRecipeBuilder.metalGrinding(pIngredient, pCategory, result, count).unlockedBy("has_" + name, InventoryChangeTrigger.TriggerInstance.hasItems(display));
    }
    private static ModSingleItemRecipeBuilder addMetalGrinding(RecipeOutput consumer, ItemLike result, int count, TagKey<Item> input, ItemLike display, RecipeCategory pCategory) {
        String name = display.asItem().toString().split(":")[1];
        return ModSingleItemRecipeBuilder.metalGrinding(Ingredient.of(input), pCategory, result, count).unlockedBy("has_" + name, InventoryChangeTrigger.TriggerInstance.hasItems(display));
    }
    protected static String getHasName(ItemLike pItemLike) {
        return "has_" + getItemName(pItemLike);
    }
    protected static String from(String result, String input) {
        return result + "_from_" + input;
    }
    
    protected static String getItemName(ItemLike pItemLike) {
        return BuiltInRegistries.ITEM.getKey(pItemLike.asItem()).getPath();
    }
    
    protected static String getItemModName(ItemLike pItemLike) {
        return BuiltInRegistries.ITEM.getKey(pItemLike.asItem()).getNamespace();
    }
    protected static ItemLike getItem(String pName) {
        return BuiltInRegistries.ITEM.get(ResourceLocation.parse(pName));
    }

    private static void addSimpleRecipe(RecipeOutput consumer, ItemLike output, int count, ItemLike input, String type) {
        if (type == "grinding") {
            addMetalGrinding(consumer, output, count, input, RecipeCategory.BUILDING_BLOCKS).save(consumer, location(getItemName(output), 2));
        }
        if (type == "milling") {
            addSawMilling(consumer, output, count, input, RecipeCategory.BUILDING_BLOCKS).save(consumer, location(getItemName(output), 1));
        }
        if (type == "cutting") {
            addStoneCutting(consumer, output, count, input, RecipeCategory.BUILDING_BLOCKS).save(consumer, location(getItemName(output), 0));
        }
    }

    private static void addSimpleRecipe(RecipeOutput consumer, ItemLike output, int count, TagKey<Item> input, String type) {
        if (type == "grinding") {
            addMetalGrinding(consumer, output, count, Ingredient.of(input), output, RecipeCategory.BUILDING_BLOCKS).save(consumer, location(getItemName(output), 2));
        }
        if (type == "milling") {
            addSawMilling(consumer, output, count, Ingredient.of(input), output, RecipeCategory.BUILDING_BLOCKS).save(consumer, location(getItemName(output), 1));
        }
        if (type == "cutting") {
            addStoneCutting(consumer, output, count, Ingredient.of(input), output, RecipeCategory.BUILDING_BLOCKS).save(consumer, location(getItemName(output), 0));
        }
    }

    private static ResourceLocation sawmillLocation(String woodSet, String recipeId, String namespace) {
        List<String> allowedNamespaces = List.of("minecraft", "calamos");
        boolean compat = !allowedNamespaces.contains(namespace);
        if (compat) {
            return CalamosMod.asResource("saw_milling/compat/%s/%s/%s".formatted(namespace, woodSet, recipeId));
        } else {
            return CalamosMod.asResource("saw_milling/%s/%s".formatted(woodSet, recipeId));
        }
    }

    private static ResourceLocation metalGrindingLocation(String type, String material, String namespace) {
        List<String> allowedNamespaces = List.of("minecraft", "calamos");
        boolean compat = !allowedNamespaces.contains(namespace);
        if (compat) {
            return CalamosMod.asResource("metal_grinding/compat/%s/%s/%s".formatted(namespace, material, type));
        } else {
            return CalamosMod.asResource("metal_grinding/%s/%s".formatted(material, type));
        }
    }

    private static ResourceLocation stonecuttingLocation(String type, String material, String namespace) {
        List<String> allowedNamespaces = List.of("minecraft", "calamos");
        boolean compat = !allowedNamespaces.contains(namespace);
        if (compat) {
            return CalamosMod.asResource("stonecutting/compat/%s/%s/%s".formatted(namespace, material, type));
        } else {
            return CalamosMod.asResource("stonecutting/%s/%s".formatted(material, type));
        }
    }
}
