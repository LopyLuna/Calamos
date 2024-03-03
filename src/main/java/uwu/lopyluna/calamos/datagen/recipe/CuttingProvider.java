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
        addSawMilling(consumer, ModDecorativeBlocks.LARGE_ACACIA_PLANKS, 1, Items.ACACIA_PLANKS, RecipeCategory.BUILDING_BLOCKS).save(consumer, location("large_acacia_planks", 1));
        addSawMilling(consumer, ModDecorativeBlocks.LARGE_BIRCH_PLANKS, 1, Items.BIRCH_PLANKS, RecipeCategory.BUILDING_BLOCKS).save(consumer, location("large_birch_planks", 1));
        addSawMilling(consumer, ModDecorativeBlocks.LARGE_BAMBOO_PLANKS, 1, Items.BAMBOO_PLANKS, RecipeCategory.BUILDING_BLOCKS).save(consumer, location("large_bamboo_planks", 1));
        addSawMilling(consumer, ModDecorativeBlocks.LARGE_CRIMSON_PLANKS, 1, Items.CRIMSON_PLANKS, RecipeCategory.BUILDING_BLOCKS).save(consumer, location("large_crimson_planks", 1));
        addSawMilling(consumer, ModDecorativeBlocks.LARGE_DARK_OAK_PLANKS, 1, Items.DARK_OAK_PLANKS, RecipeCategory.BUILDING_BLOCKS).save(consumer, location("large_dark_oak_planks", 1));
        addSawMilling(consumer, ModDecorativeBlocks.LARGE_JUNGLE_PLANKS, 1, Items.JUNGLE_PLANKS, RecipeCategory.BUILDING_BLOCKS).save(consumer, location("large_jungle_planks", 1));
        addSawMilling(consumer, ModDecorativeBlocks.LARGE_OAK_PLANKS, 1, Items.OAK_PLANKS, RecipeCategory.BUILDING_BLOCKS).save(consumer, location("large_oak_planks", 1));
        addSawMilling(consumer, ModDecorativeBlocks.LARGE_SPRUCE_PLANKS, 1, Items.SPRUCE_PLANKS, RecipeCategory.BUILDING_BLOCKS).save(consumer, location("large_spruce_planks", 1));
        addSawMilling(consumer, ModDecorativeBlocks.LARGE_WARPED_PLANKS, 1, Items.WARPED_PLANKS, RecipeCategory.BUILDING_BLOCKS).save(consumer, location("large_warped_planks", 1));
        
        sawmillWoodSet(consumer, Items.ACACIA_PLANKS, Items.ACACIA_STAIRS, Items.ACACIA_SLAB, Items.ACACIA_FENCE, Items.ACACIA_FENCE_GATE, Items.ACACIA_DOOR, Items.ACACIA_TRAPDOOR, Items.ACACIA_BUTTON, Items.ACACIA_PRESSURE_PLATE, Items.ACACIA_SIGN, Items.ACACIA_HANGING_SIGN);
        sawmillWoodSet(consumer, Items.BIRCH_PLANKS, Items.BIRCH_STAIRS, Items.BIRCH_SLAB, Items.BIRCH_FENCE, Items.BIRCH_FENCE_GATE, Items.BIRCH_DOOR, Items.BIRCH_TRAPDOOR, Items.BIRCH_BUTTON, Items.BIRCH_PRESSURE_PLATE, Items.BIRCH_SIGN, Items.BIRCH_HANGING_SIGN);
        sawmillWoodSet(consumer, Items.BAMBOO_PLANKS, Items.BAMBOO_STAIRS, Items.BAMBOO_SLAB, Items.BAMBOO_FENCE, Items.BAMBOO_FENCE_GATE, Items.BAMBOO_DOOR, Items.BAMBOO_TRAPDOOR, Items.BAMBOO_BUTTON, Items.BAMBOO_PRESSURE_PLATE, Items.BAMBOO_SIGN, Items.BAMBOO_HANGING_SIGN);
        sawmillWoodSet(consumer, Items.CRIMSON_PLANKS, Items.CRIMSON_STAIRS, Items.CRIMSON_SLAB, Items.CRIMSON_FENCE, Items.CRIMSON_FENCE_GATE, Items.CRIMSON_DOOR, Items.CRIMSON_TRAPDOOR, Items.CRIMSON_BUTTON, Items.CRIMSON_PRESSURE_PLATE, Items.CRIMSON_SIGN, Items.CRIMSON_HANGING_SIGN);
        sawmillWoodSet(consumer, Items.DARK_OAK_PLANKS, Items.DARK_OAK_STAIRS, Items.DARK_OAK_SLAB, Items.DARK_OAK_FENCE, Items.DARK_OAK_FENCE_GATE, Items.DARK_OAK_DOOR, Items.DARK_OAK_TRAPDOOR, Items.DARK_OAK_BUTTON, Items.DARK_OAK_PRESSURE_PLATE, Items.DARK_OAK_SIGN, Items.DARK_OAK_HANGING_SIGN);
        sawmillWoodSet(consumer, Items.JUNGLE_PLANKS, Items.JUNGLE_STAIRS, Items.JUNGLE_SLAB, Items.JUNGLE_FENCE, Items.JUNGLE_FENCE_GATE, Items.JUNGLE_DOOR, Items.JUNGLE_TRAPDOOR, Items.JUNGLE_BUTTON, Items.JUNGLE_PRESSURE_PLATE, Items.JUNGLE_SIGN, Items.JUNGLE_HANGING_SIGN);
        sawmillWoodSet(consumer, Items.OAK_PLANKS, Items.OAK_STAIRS, Items.OAK_SLAB, Items.OAK_FENCE, Items.OAK_FENCE_GATE, Items.OAK_DOOR, Items.OAK_TRAPDOOR, Items.OAK_BUTTON, Items.OAK_PRESSURE_PLATE, Items.OAK_SIGN, Items.OAK_HANGING_SIGN);
        sawmillWoodSet(consumer, Items.SPRUCE_PLANKS, Items.SPRUCE_STAIRS, Items.SPRUCE_SLAB, Items.SPRUCE_FENCE, Items.SPRUCE_FENCE_GATE, Items.SPRUCE_DOOR, Items.SPRUCE_TRAPDOOR, Items.SPRUCE_BUTTON, Items.SPRUCE_PRESSURE_PLATE, Items.SPRUCE_SIGN, Items.SPRUCE_HANGING_SIGN);
        sawmillWoodSet(consumer, Items.WARPED_PLANKS, Items.WARPED_STAIRS, Items.WARPED_SLAB, Items.WARPED_FENCE, Items.WARPED_FENCE_GATE, Items.WARPED_DOOR, Items.WARPED_TRAPDOOR, Items.WARPED_BUTTON, Items.WARPED_PRESSURE_PLATE, Items.WARPED_SIGN, Items.WARPED_HANGING_SIGN);
        sawmillWoodSet(consumer, ModDecorativeBlocks.OTHERWORLD_OAK_PLANKS, ModDecorativeBlocks.OTHERWORLD_OAK_STAIRS, ModDecorativeBlocks.OTHERWORLD_OAK_SLAB, ModDecorativeBlocks.OTHERWORLD_OAK_FENCE, ModDecorativeBlocks.OTHERWORLD_OAK_FENCE_GATE, ModDecorativeBlocks.OTHERWORLD_OAK_DOOR, ModDecorativeBlocks.OTHERWORLD_OAK_TRAPDOOR, ModDecorativeBlocks.OTHERWORLD_OAK_BUTTON, ModDecorativeBlocks.OTHERWORLD_OAK_PRESSURE_PLATE, ModDecorativeBlocks.OTHERWORLD_OAK_SIGN, ModDecorativeBlocks.OTHERWORLD_OAK_HANGING_SIGN);
        sawmillWoodSet(consumer, ModDecorativeBlocks.TWILIGHT_PLANKS, ModDecorativeBlocks.TWILIGHT_STAIRS, ModDecorativeBlocks.TWILIGHT_SLAB, ModDecorativeBlocks.TWILIGHT_FENCE, ModDecorativeBlocks.TWILIGHT_FENCE_GATE, ModDecorativeBlocks.TWILIGHT_DOOR, ModDecorativeBlocks.TWILIGHT_TRAPDOOR, ModDecorativeBlocks.TWILIGHT_BUTTON, ModDecorativeBlocks.TWILIGHT_PRESSURE_PLATE, ModDecorativeBlocks.TWILIGHT_SIGN, ModDecorativeBlocks.TWILIGHT_HANGING_SIGN);
        sawmillWoodSet(consumer, ModDecorativeBlocks.HOLLOW_PLANKS, ModDecorativeBlocks.HOLLOW_STAIRS, ModDecorativeBlocks.HOLLOW_SLAB, ModDecorativeBlocks.HOLLOW_FENCE, ModDecorativeBlocks.HOLLOW_FENCE_GATE, ModDecorativeBlocks.HOLLOW_DOOR, ModDecorativeBlocks.HOLLOW_TRAPDOOR, ModDecorativeBlocks.HOLLOW_BUTTON, ModDecorativeBlocks.HOLLOW_PRESSURE_PLATE, ModDecorativeBlocks.HOLLOW_SIGN, ModDecorativeBlocks.HOLLOW_HANGING_SIGN);
    }
    
    private static void recipesMetalGrinding(RecipeOutput consumer) {
        addMetalGrinding(consumer, ModDecorativeBlocks.OBSIDIAN_BRICKS, 1, Items.OBSIDIAN, RecipeCategory.BUILDING_BLOCKS).save(consumer, location("obsidian_bricks", 2));
        addMetalGrinding(consumer, ModDecorativeBlocks.CRYING_OBSIDIAN_BRICKS, 1, Items.CRYING_OBSIDIAN, RecipeCategory.BUILDING_BLOCKS).save(consumer, location("crying_obsidian_bricks", 2));
    }
    
    
    private static void sawmillWoodSet(RecipeOutput consumer, ItemLike initialWood,
                                       ItemLike stairs,
                                       ItemLike slab,
                                       ItemLike fence,
                                       ItemLike fenceGate,
                                       ItemLike door,
                                       ItemLike trapdoor,
                                       ItemLike button,
                                       ItemLike pressurePlate,
                                       ItemLike sign,
                                       ItemLike hangingSign) {
        addSawMilling(consumer, stairs, 1, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(getItemName(stairs), 1));
        addSawMilling(consumer, slab, 2, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(getItemName(slab), 1));
        addSawMilling(consumer, fence, 3, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(getItemName(fence), 1));
        addSawMilling(consumer, fenceGate, 1, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(getItemName(fenceGate), 1));
        addSawMilling(consumer, door, 1, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(getItemName(door), 1));
        addSawMilling(consumer, trapdoor, 1, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(getItemName(trapdoor), 1));
        addSawMilling(consumer, button, 8, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(getItemName(button), 1));
        addSawMilling(consumer, pressurePlate, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(getItemName(pressurePlate), 1));
        addSawMilling(consumer, sign, 4, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(getItemName(sign), 1));
        addSawMilling(consumer, hangingSign, 2, initialWood, RecipeCategory.BUILDING_BLOCKS).unlockedBy(getHasName(initialWood), InventoryChangeTrigger.TriggerInstance.hasItems(initialWood)).save(consumer, location(getItemName(hangingSign), 1));
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
    
    protected static String getItemName(ItemLike pItemLike) {
        return BuiltInRegistries.ITEM.getKey(pItemLike.asItem()).getPath();
    }
}
