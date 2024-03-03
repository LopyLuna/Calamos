package uwu.lopyluna.calamos.datagen.recipe;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.datagen.recipe.builder.ModSingleItemRecipeBuilder;
import uwu.lopyluna.calamos.elements.ModDecorativeBlocks;

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
    }
    
    private static void recipesSawmilling(RecipeOutput consumer) {
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
        addMetalGrinding(consumer, ModDecorativeBlocks.OBSIDIAN_BRICKS, 1, Items.OBSIDIAN, RecipeCategory.BUILDING_BLOCKS).save(consumer, location("obsidian_bricks", 2));
        addMetalGrinding(consumer, ModDecorativeBlocks.CRYING_OBSIDIAN_BRICKS, 1, Items.CRYING_OBSIDIAN, RecipeCategory.BUILDING_BLOCKS).save(consumer, location("crying_obsidian_bricks", 2));
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
    private static ModSingleItemRecipeBuilder addSawMilling(RecipeOutput consumer, ItemLike result, int count, ItemLike input, RecipeCategory pCategory) {
        String name = input.asItem().toString().split(":")[1];
        return ModSingleItemRecipeBuilder.sawMilling(Ingredient.of(input), pCategory, result, count).unlockedBy("has_" + name, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    private static ModSingleItemRecipeBuilder addMetalGrinding(RecipeOutput consumer, ItemLike result, int count, ItemLike input, RecipeCategory pCategory) {
        String name = input.asItem().toString().split(":")[1];
        return ModSingleItemRecipeBuilder.metalGrinding(Ingredient.of(input), pCategory, result, count).unlockedBy("has_" + name, InventoryChangeTrigger.TriggerInstance.hasItems(input));
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
}
