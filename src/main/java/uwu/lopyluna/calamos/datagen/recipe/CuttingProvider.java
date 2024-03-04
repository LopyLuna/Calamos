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

@SuppressWarnings({"all", "unused"})
public class CuttingProvider {
    private static ResourceLocation location(String path, int type) {
        String typeStr = switch (type) {case 1 -> "saw_milling";case 2 -> "metal_grinding";default -> "stonecutting";};
        return new ResourceLocation(CalamosMod.MODID, typeStr + "/" + path);
    }
    public static void register(RecipeOutput consumer) {
        recipesStonecutting(consumer);
        recipesSawmilling(consumer);
        recipesMetalGrinding(consumer);
    }

    private static void recipesStonecutting(RecipeOutput consumer) {
        addSimpleRecipe(consumer, ModDecorativeBlocks.BASALT_BRICKS, 1, Items.BASALT, "cutting");
        addSimpleRecipe(consumer, ModDecorativeBlocks.CUT_BASALT, 1, Items.BASALT, "cutting");

    }
    
    private static void recipesSawmilling(RecipeOutput consumer) {


        sawmillLogWoodSet(consumer, Items.ACACIA_LOG, true, false);
        sawmillLogWoodSet(consumer, Items.BIRCH_LOG, true, false);
        sawmillLogWoodSet(consumer, Items.DARK_OAK_LOG, true, false);
        sawmillLogWoodSet(consumer, Items.JUNGLE_LOG, true, false);
        sawmillLogWoodSet(consumer, Items.OAK_LOG, true, false);
        sawmillLogWoodSet(consumer, Items.SPRUCE_LOG, true, false);
        sawmillLogWoodSet(consumer, ModDecorativeBlocks.OTHERWORLD_OAK_LOG, false, false);
        sawmillLogWoodSet(consumer, ModDecorativeBlocks.TWILIGHT_LOG, false, false);
        sawmillLogWoodSet(consumer, ModDecorativeBlocks.HOLLOW_LOG, false, false);

        sawmillLogWoodSet(consumer, Items.STRIPPED_ACACIA_LOG, true, true);
        sawmillLogWoodSet(consumer, Items.STRIPPED_BIRCH_LOG, true, true);
        sawmillLogWoodSet(consumer, Items.STRIPPED_DARK_OAK_LOG, true, true);
        sawmillLogWoodSet(consumer, Items.STRIPPED_JUNGLE_LOG, true, true);
        sawmillLogWoodSet(consumer, Items.STRIPPED_OAK_LOG, true, true);
        sawmillLogWoodSet(consumer, Items.STRIPPED_SPRUCE_LOG, true, true);
        sawmillLogWoodSet(consumer, ModDecorativeBlocks.STRIPPED_OTHERWORLD_OAK_LOG, false, true);
        sawmillLogWoodSet(consumer, ModDecorativeBlocks.STRIPPED_TWILIGHT_LOG, false, true);
        sawmillLogWoodSet(consumer, ModDecorativeBlocks.STRIPPED_HOLLOW_LOG, false, true);

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
        sawmillWoodSet(consumer, ModDecorativeBlocks.OTHERWORLD_OAK_PLANKS, false);
        sawmillWoodSet(consumer, ModDecorativeBlocks.TWILIGHT_PLANKS, false);
        sawmillWoodSet(consumer, ModDecorativeBlocks.HOLLOW_PLANKS, false);
        
        sawmillStairsSlab(consumer, ModDecorativeBlocks.LARGE_ACACIA_PLANKS);
        sawmillStairsSlab(consumer, ModDecorativeBlocks.LARGE_BIRCH_PLANKS);
        sawmillStairsSlab(consumer, ModDecorativeBlocks.LARGE_BAMBOO_PLANKS);
        sawmillStairsSlab(consumer, ModDecorativeBlocks.LARGE_CRIMSON_PLANKS);
        sawmillStairsSlab(consumer, ModDecorativeBlocks.LARGE_DARK_OAK_PLANKS);
        sawmillStairsSlab(consumer, ModDecorativeBlocks.LARGE_JUNGLE_PLANKS);
        sawmillStairsSlab(consumer, ModDecorativeBlocks.LARGE_OAK_PLANKS);
        sawmillStairsSlab(consumer, ModDecorativeBlocks.LARGE_SPRUCE_PLANKS);
        sawmillStairsSlab(consumer, ModDecorativeBlocks.LARGE_WARPED_PLANKS);
    }
    
    private static void recipesMetalGrinding(RecipeOutput consumer) {
        addSimpleRecipe(consumer, ModDecorativeBlocks.POLISHED_AMETHYST, 1, Items.AMETHYST_BLOCK, "grinding");
        addSimpleRecipe(consumer, ModDecorativeBlocks.AMETHYST_BRICKS, 1, Items.AMETHYST_BLOCK, "grinding");
        addSimpleRecipe(consumer, ModDecorativeBlocks.CUT_AMETHYST, 1, Items.AMETHYST_BLOCK, "grinding");

        addMetalGrinding(consumer, ModDecorativeBlocks.OBSIDIAN_BRICKS, 1, Items.OBSIDIAN, RecipeCategory.BUILDING_BLOCKS).save(consumer, location("obsidian_bricks", 2));
        addMetalGrinding(consumer, ModDecorativeBlocks.CRYING_OBSIDIAN_BRICKS, 1, Items.CRYING_OBSIDIAN, RecipeCategory.BUILDING_BLOCKS).save(consumer, location("crying_obsidian_bricks", 2));


        // TAG CONVERSIONS TO MOD ITEMS
        addSimpleRecipe(consumer, ModItems.URANIUM_INGOT, 1, ModTags.forgeItemTag("ingots/uranium"), "grinding");
        addSimpleRecipe(consumer, ModItems.VOLCANITE_INGOT, 1, ModTags.forgeItemTag("ingots/volcanite"), "grinding");
        addSimpleRecipe(consumer, ModItems.ULTIMITA_INGOT, 1, ModTags.forgeItemTag("ingots/ultimita"), "grinding");
        addSimpleRecipe(consumer, ModItems.TERRAULITE_INGOT, 1, ModTags.forgeItemTag("ingots/terraulite"), "grinding");
        addSimpleRecipe(consumer, ModItems.STARINIUM_INGOT, 1, ModTags.forgeItemTag("ingots/starinium"), "grinding");
        addSimpleRecipe(consumer, ModItems.PLATINUM_INGOT, 1, ModTags.forgeItemTag("ingots/platinum"), "grinding");
        addSimpleRecipe(consumer, ModItems.OBSTEEL_INGOT, 1, ModTags.forgeItemTag("ingots/obsteel"), "grinding");
        addSimpleRecipe(consumer, ModItems.MAGNETITE_INGOT, 1, ModTags.forgeItemTag("ingots/magnetite"), "grinding");
        addSimpleRecipe(consumer, ModItems.LEAD_INGOT, 1, ModTags.forgeItemTag("ingots/lead"), "grinding");
        addSimpleRecipe(consumer, ModItems.METEORITE_INGOT, 1, ModTags.forgeItemTag("ingots/meteorite"), "grinding");
        addSimpleRecipe(consumer, ModItems.BLOODBORE_INGOT, 1, ModTags.forgeItemTag("ingots/bloodbore"), "grinding");
        addSimpleRecipe(consumer, ModItems.CALAMATIUM_INGOT, 1, ModTags.forgeItemTag("ingots/calamatium"), "grinding");
        addSimpleRecipe(consumer, ModItems.ECTOLIGHT_INGOT, 1, ModTags.forgeItemTag("ingots/ectolight"), "grinding");
        addSimpleRecipe(consumer, ModItems.ELEGANT_BLOOM, 1, ModTags.forgeItemTag("ingots/elegant"), "grinding");
        addSimpleRecipe(consumer, ModItems.STELLAR_INGOT, 1, ModTags.forgeItemTag("ingots/stellar"), "grinding");
        addSimpleRecipe(consumer, ModItems.PALLADIUM_INGOT, 1, ModTags.forgeItemTag("ingots/palladium"), "grinding");


        addSimpleRecipe(consumer, ModBlocks.URANIUM_BLOCK, 1, ModTags.forgeItemTag("storage_blocks/uranium"), "grinding");
        addSimpleRecipe(consumer, ModBlocks.PALLADIUM_BLOCK, 1, ModTags.forgeItemTag("storage_blocks/palladium"), "grinding");
    }
    
    private static void sawmillStairsSlab(RecipeOutput consumer, ItemLike initialWood) {
        String name = getItemName(initialWood);
        String modName = getItemModName(initialWood);
        String stairsName = modName + ":" + name.replace("_planks", "_stairs");
        ItemLike stairs = getItem(stairsName);
        String slabName = modName + ":" + name.replace("_planks", "_slab");
        ItemLike slab = getItem(slabName);
        addSawMilling(consumer, stairs, 1, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(stairsName.replace(':', '_'), name.replace(':', '_')), 1));
        addSawMilling(consumer, slab, 2, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(slabName.replace(':', '_'), name.replace(':', '_')), 1));
    }

    private static void sawmillLogWoodSet(RecipeOutput consumer, ItemLike initialWood, boolean hasLarge, boolean isStripped) {
        String name = getItemName(initialWood);
        String modName = getItemModName(initialWood);

        String chest_name = "minecraft:chest";
        ItemLike chest = getItem(chest_name);

        if (!isStripped) {
            String planksName = modName + ":" + name.replace("_log", "_planks");
            ItemLike planks = getItem(planksName);

            String strippedLogName = modName + ":stripped_" + name;
            ItemLike strippedLog = getItem(strippedLogName);

            String stairsName = modName + ":" + name.replace("_log", "_stairs");
            ItemLike stairs = getItem(stairsName);
            String slabName = modName + ":" + name.replace("_log", "_slab");
            ItemLike slab = getItem(slabName);
            String fenceName = modName + ":" + name.replace("_log", "_fence");
            ItemLike fence = getItem(fenceName);
            String fenceGateName = modName + ":" + name.replace("_log", "_fence_gate");
            ItemLike fenceGate = getItem(fenceGateName);
            String doorName = modName + ":" + name.replace("_log", "_door");
            ItemLike door = getItem(doorName);
            String trapdoorName = modName + ":" + name.replace("_log", "_trapdoor");
            ItemLike trapdoor = getItem(trapdoorName);
            String buttonName = modName + ":" + name.replace("_log", "_button");
            ItemLike button = getItem(buttonName);
            String pressurePlateName = modName + ":" + name.replace("_log", "_pressure_plate");
            ItemLike pressurePlate = getItem(pressurePlateName);
            String signName = modName + ":" + name.replace("_log", "_sign");
            ItemLike sign = getItem(signName);
            String hangingSignName = modName + ":" + name.replace("_log", "_hanging_sign");
            ItemLike hangingSign = getItem(hangingSignName);

            if (hasLarge) {
                String largePlanksName = "calamos:large_" + name.replace("_log", "_planks");
                ItemLike largePlanks = getItem(largePlanksName);
                String largeStairsName = "calamos:large_" + name.replace("_log", "_stairs");
                ItemLike largeStairs = getItem(largeStairsName);
                String largeSlabName = "calamos:large_" + name.replace("_log", "_slab");
                ItemLike largeSlab = getItem(largeSlabName);
                addSawMilling(consumer, largePlanks, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(largePlanksName.replace(':', '_'), name.replace(':', '_')), 1));
                addSawMilling(consumer, largeStairs, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(largeStairsName.replace(':', '_'), name.replace(':', '_')), 1));
                addSawMilling(consumer, largeSlab, 8, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(largeSlabName.replace(':', '_'), name.replace(':', '_')), 1));
            }
            addSawMilling(consumer, planks, 6, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(planksName.replace(':', '_'), name.replace(':', '_')), 1));
            addSawMilling(consumer, strippedLog, 1, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(strippedLogName.replace(':', '_'), name.replace(':', '_')), 1));

            addSawMilling(consumer, stairs, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(stairsName.replace(':', '_'), name.replace(':', '_')), 1));
            addSawMilling(consumer, slab, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(slabName.replace(':', '_'), name.replace(':', '_')), 1));
            addSawMilling(consumer, fence, 12, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(fenceName.replace(':', '_'), name.replace(':', '_')), 1));
            addSawMilling(consumer, fenceGate, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(fenceGateName.replace(':', '_'), name.replace(':', '_')), 1));
            addSawMilling(consumer, door, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(doorName.replace(':', '_'), name.replace(':', '_')), 1));
            addSawMilling(consumer, trapdoor, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(trapdoorName.replace(':', '_'), name.replace(':', '_')), 1));
            addSawMilling(consumer, button, 32, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(buttonName.replace(':', '_'), name.replace(':', '_')), 1));
            addSawMilling(consumer, pressurePlate, 16, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(pressurePlateName.replace(':', '_'), name.replace(':', '_')), 1));
            addSawMilling(consumer, sign, 16, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(signName.replace(':', '_'), name.replace(':', '_')), 1));
            addSawMilling(consumer, hangingSign, 8, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(hangingSignName.replace(':', '_'), name.replace(':', '_')), 1));

            addSawMilling(consumer, chest, 1, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(chest_name.replace(':', '_'), name.replace(':', '_')), 1));
        } else {
            String s_planksName = modName + ":" + name.replace("_log", "_planks").replace("stripped_", "");
            ItemLike s_planks = getItem(s_planksName);

            String s_stairsName = modName + ":" + name.replace("_log", "_stairs").replace("stripped_", "");
            ItemLike s_stairs = getItem(s_stairsName);
            String s_slabName = modName + ":" + name.replace("_log", "_slab").replace("stripped_", "");
            ItemLike s_slab = getItem(s_slabName);
            String s_fenceName = modName + ":" + name.replace("_log", "_fence").replace("stripped_", "");
            ItemLike s_fence = getItem(s_fenceName);
            String s_fenceGateName = modName + ":" + name.replace("_log", "_fence_gate").replace("stripped_", "");
            ItemLike s_fenceGate = getItem(s_fenceGateName);
            String s_doorName = modName + ":" + name.replace("_log", "_door").replace("stripped_", "");
            ItemLike s_door = getItem(s_doorName);
            String s_trapdoorName = modName + ":" + name.replace("_log", "_trapdoor").replace("stripped_", "");
            ItemLike s_trapdoor = getItem(s_trapdoorName);
            String s_buttonName = modName + ":" + name.replace("_log", "_button").replace("stripped_", "");
            ItemLike s_button = getItem(s_buttonName);
            String s_pressurePlateName = modName + ":" + name.replace("_log", "_pressure_plate").replace("stripped_", "");
            ItemLike s_pressurePlate = getItem(s_pressurePlateName);
            String s_signName = modName + ":" + name.replace("_log", "_sign").replace("stripped_", "");
            ItemLike s_sign = getItem(s_signName);
            String s_hangingSignName = modName + ":" + name.replace("_log", "_hanging_sign").replace("stripped_", "");
            ItemLike s_hangingSign = getItem(s_hangingSignName);

            if (hasLarge) {
                String largePlanksName = "calamos:large_" + name.replace("_log", "_planks").replace("stripped_", "");
                ItemLike largePlanks = getItem(largePlanksName);
                String largeStairsName = "calamos:large_" + name.replace("_log", "_stairs").replace("stripped_", "");
                ItemLike largeStairs = getItem(largeStairsName);
                String largeSlabName = "calamos:large_" + name.replace("_log", "_slab").replace("stripped_", "");
                ItemLike largeSlab = getItem(largeSlabName);
                addSawMilling(consumer, largePlanks, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(largePlanksName.replace(':', '_'), name.replace(':', '_')), 1));
                addSawMilling(consumer, largeStairs, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(largeStairsName.replace(':', '_'), name.replace(':', '_')), 1));
                addSawMilling(consumer, largeSlab, 8, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(largeSlabName.replace(':', '_'), name.replace(':', '_')), 1));
            }
            addSawMilling(consumer, s_planks, 6, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(s_planksName.replace(':', '_'), name.replace(':', '_')), 1));
            addSawMilling(consumer, s_stairs, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(s_stairsName.replace(':', '_'), name.replace(':', '_')), 1));
            addSawMilling(consumer, s_slab, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(s_slabName.replace(':', '_'), name.replace(':', '_')), 1));
            addSawMilling(consumer, s_fence, 12, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(s_fenceName.replace(':', '_'), name.replace(':', '_')), 1));
            addSawMilling(consumer, s_fenceGate, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(s_fenceGateName.replace(':', '_'), name.replace(':', '_')), 1));
            addSawMilling(consumer, s_door, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(s_doorName.replace(':', '_'), name.replace(':', '_')), 1));
            addSawMilling(consumer, s_trapdoor, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(s_trapdoorName.replace(':', '_'), name.replace(':', '_')), 1));
            addSawMilling(consumer, s_button, 32, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(s_buttonName.replace(':', '_'), name.replace(':', '_')), 1));
            addSawMilling(consumer, s_pressurePlate, 16, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(s_pressurePlateName.replace(':', '_'), name.replace(':', '_')), 1));
            addSawMilling(consumer, s_sign, 16, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(s_signName.replace(':', '_'), name.replace(':', '_')), 1));
            addSawMilling(consumer, s_hangingSign, 8, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(s_hangingSignName.replace(':', '_'), name.replace(':', '_')), 1));

            addSawMilling(consumer, chest, 1, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(chest_name.replace(':', '_'), name.replace(':', '_')), 1));
        }
    }

    private static void sawmillWoodSet(RecipeOutput consumer, ItemLike initialWood, boolean hasLarge) {
        String name = getItemName(initialWood);
        String modName = getItemModName(initialWood);
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
        if (hasLarge) {
            String largePlanksName = "calamos:large_" + name;
            ItemLike largePlanks = getItem(largePlanksName);
            String largeStairsName = "calamos:large_" + name.replace("_planks", "_stairs");
            ItemLike largeStairs = getItem(largeStairsName);
            String largeSlabName = "calamos:large_" + name.replace("_planks", "_slab");
            ItemLike largeSlab = getItem(largeSlabName);
            addSawMilling(consumer, largePlanks, 1, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(largePlanksName.replace(':', '_'), name.replace(':', '_')), 1));
            addSawMilling(consumer, largeStairs, 1, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(largeStairsName.replace(':', '_'), name.replace(':', '_')), 1));
            addSawMilling(consumer, largeSlab, 2, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(largeSlabName.replace(':', '_'), name.replace(':', '_')), 1));
        }
        addSawMilling(consumer, stairs, 1, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(stairsName.replace(':', '_'), name.replace(':', '_')), 1));
        addSawMilling(consumer, slab, 2, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(slabName.replace(':', '_'), name.replace(':', '_')), 1));
        addSawMilling(consumer, fence, 3, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(fenceName.replace(':', '_'), name.replace(':', '_')), 1));
        addSawMilling(consumer, fenceGate, 1, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(fenceGateName.replace(':', '_'), name.replace(':', '_')), 1));
        addSawMilling(consumer, door, 1, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(doorName.replace(':', '_'), name.replace(':', '_')), 1));
        addSawMilling(consumer, trapdoor, 1, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(trapdoorName.replace(':', '_'), name.replace(':', '_')), 1));
        addSawMilling(consumer, button, 8, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(buttonName.replace(':', '_'), name.replace(':', '_')), 1));
        addSawMilling(consumer, pressurePlate, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(pressurePlateName.replace(':', '_'), name.replace(':', '_')), 1));
        addSawMilling(consumer, sign, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(signName.replace(':', '_'), name.replace(':', '_')), 1));
        addSawMilling(consumer, hangingSign, 2, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(from(hangingSignName.replace(':', '_'), name.replace(':', '_')), 1));
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
        return BuiltInRegistries.ITEM.get(new ResourceLocation(pName));
    }

    private static void addSimpleRecipe(RecipeOutput consumer, ItemLike output, int count, ItemLike input, String type) {
        if (type == "grinding") {
            addMetalGrinding(consumer, output, count, input, RecipeCategory.BUILDING_BLOCKS).save(consumer, location(getItemName(output), 2));
        }
        if (type == "milling") {
            addSawMilling(consumer, output, count, input, RecipeCategory.BUILDING_BLOCKS).save(consumer, location(getItemName(output), 2));
        }
        if (type == "cutting") {
            addStoneCutting(consumer, output, count, input, RecipeCategory.BUILDING_BLOCKS).save(consumer, location(getItemName(output), 2));
        }
    }

    private static void addSimpleRecipe(RecipeOutput consumer, ItemLike output, int count, TagKey<Item> input, String type) {
        if (type == "grinding") {
            addMetalGrinding(consumer, output, count, Ingredient.of(input), output, RecipeCategory.BUILDING_BLOCKS).save(consumer, location(getItemName(output), 2));
        }
        if (type == "milling") {
            addSawMilling(consumer, output, count, Ingredient.of(input), output, RecipeCategory.BUILDING_BLOCKS).save(consumer, location(getItemName(output), 2));
        }
        if (type == "cutting") {
            addStoneCutting(consumer, output, count, Ingredient.of(input), output, RecipeCategory.BUILDING_BLOCKS).save(consumer, location(getItemName(output), 2));
        }
    }
}
