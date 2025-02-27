package uwu.lopyluna.calamos.datagen.recipe;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.elements.ModBlocks;
import uwu.lopyluna.calamos.elements.ModDecorativeBlocks;
import uwu.lopyluna.calamos.elements.ModItems;

import java.util.List;
import java.util.Optional;

import static uwu.lopyluna.calamos.utilities.ModUtils.secondsToTicks;

public class FurnaceProvider {
    public static void register(RecipeOutput consumer) {
        smelting(consumer);
        blasting(consumer);
        //smoking(consumer);
    }
    private static ResourceLocation location(String path, int type) {
        String typeStr = switch (type) {case 1 -> "blasting";case 2 -> "smoking";default -> "smelting";};
        return new ResourceLocation(CalamosMod.MODID, typeStr + "/" + path);
    }
    private static void smelting(RecipeOutput consumer) {
        smeltingRecipe(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.METEORITE.asItem(), ModDecorativeBlocks.COBBLED_METEORITE.asItem(), 0.1f, secondsToTicks(10)).save(consumer, location("decoration/meteorite/block", 0));
        smeltingRecipe(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.SMOOTH_METEORITE.asItem(), ModDecorativeBlocks.METEORITE.asItem(), 0.1f, secondsToTicks(10)).save(consumer, location("decoration/meteorite/smooth", 0));
        smeltingRecipe(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.SMOOTH_DEEPSLATE.asItem(), Items.DEEPSLATE, 0.1f, secondsToTicks(10)).save(consumer, location("decoration/deepslate/smooth", 0));
    }
    
    private static void blasting(RecipeOutput consumer) {
        smeltAndBlast(RecipeCategory.MISC, ModItems.METEORITE_INGOT.get(), ModItems.RAW_METEORITE.get(), 0.65f, secondsToTicks(120), consumer, "meteorite/raw");
        smeltAndBlast(RecipeCategory.MISC, ModItems.URANIUM_INGOT.get(), ModItems.RAW_URANIUM.get(), 0.7f, secondsToTicks(10), consumer, "uranium/raw");
        smeltAndBlast(RecipeCategory.MISC, ModItems.PALLADIUM_INGOT.get(), ModItems.RAW_PALLADIUM.get(), 0.7f, secondsToTicks(10), consumer, "palladium/raw");
        blastingRecipe(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.SMOOTH_NETHERRACK.asItem(), Items.NETHERRACK, 0.1f, secondsToTicks(10)).save(consumer, location("decoration/netherrack/smooth", 1));
        smeltAndBlast(RecipeCategory.MISC, Items.COPPER_INGOT, ModBlocks.COPPER_ORE.asItem(), 0.7f, secondsToTicks(10), consumer, "copper/ore_special");
        smeltAndBlast(RecipeCategory.MISC, Items.IRON_INGOT, ModBlocks.IRON_ORE.asItem(), 0.7f, secondsToTicks(10), consumer, "iron/ore_special");
        smeltAndBlast(RecipeCategory.MISC, Items.GOLD_INGOT, ModBlocks.GOLD_ORE.asItem(), 0.7f, secondsToTicks(10), consumer, "gold/ore_special");
        smeltAndBlast(RecipeCategory.MISC, ModItems.GARNET.get(), ModBlocks.GARNET_ORE.asItem(), 1.0f, secondsToTicks(10), consumer, "garnet/ore_special");
        smeltAndBlast(RecipeCategory.MISC, ModItems.JADE.get(), ModBlocks.JADE_ORE.asItem(), 1.0f, secondsToTicks(10), consumer, "jade/ore_special");
        smeltAndBlast(RecipeCategory.MISC, ModItems.KUNZITE.get(), ModBlocks.KUNZITE_ORE.asItem(), 1.0f, secondsToTicks(10), consumer, "kunzite/ore_special");
        smeltAndBlast(RecipeCategory.MISC, ModItems.MOONSTONE.get(), ModBlocks.MOONSTONE_ORE.asItem(), 1.0f, secondsToTicks(10), consumer, "moonstone/ore_special");
        smeltAndBlast(RecipeCategory.MISC, ModItems.OPAL.get(), ModBlocks.OPAL_ORE.asItem(), 1.0f, secondsToTicks(10), consumer, "opal/ore_special");
        smeltAndBlast(RecipeCategory.MISC, ModItems.RUBY.get(), ModBlocks.RUBY_ORE.asItem(), 1.0f, secondsToTicks(10), consumer, "ruby/ore_special");
        smeltAndBlast(RecipeCategory.MISC, ModItems.SAPPHIRE.get(), ModBlocks.SAPPHIRE_ORE.asItem(), 1.0f, secondsToTicks(10), consumer, "sapphire/ore_special");
        smeltAndBlast(RecipeCategory.MISC, ModItems.SPINEL.get(), ModBlocks.SPINEL_ORE.asItem(), 1.0f, secondsToTicks(10), consumer, "spinel/ore_special");
        smeltAndBlast(RecipeCategory.MISC, ModItems.SUNSTONE.get(), ModBlocks.SUNSTONE_ORE.asItem(), 1.0f, secondsToTicks(10), consumer, "sunstone/ore_special");
        smeltAndBlast(RecipeCategory.MISC, ModItems.TANZANITE.get(), ModBlocks.TANZANITE_ORE.asItem(), 1.0f, secondsToTicks(10), consumer, "tanzanite/ore_special");
        smeltAndBlast(RecipeCategory.MISC, ModItems.TOPAZ.get(), ModBlocks.TOPAZ_ORE.asItem(), 1.0f, secondsToTicks(10), consumer, "topaz/ore_special");
        smeltAndBlast(RecipeCategory.MISC, ModItems.URANIUM_INGOT.get(), ModBlocks.URANIUM_ORE.asItem(), 0.7f, secondsToTicks(10), consumer, "uranium/ore_special");
        smeltAndBlast(RecipeCategory.MISC, ModItems.PALLADIUM_INGOT.get(), ModBlocks.PALLADIUM_ORE.asItem(), 0.7f, secondsToTicks(10), consumer, "palladium/ore_special");

    }
    
    private static void smoking(RecipeOutput consumer) {
    
    }
    
    
    
    public static void smeltAndBlast(RecipeCategory category, Item result, ItemLike input, float xp, int time, RecipeOutput consumer, String recipeId) {
        int blastTime = time / 2;
        String inputName = input.asItem().toString().split(":")[1];
        String resultName = result.toString().split(":")[1];
        smeltingRecipe(category, result, input, xp, time).save(consumer, location(recipeId, 0));
        blastingRecipe(category, result, input, xp, blastTime).save(consumer, location(recipeId, 1));
    }

    public static void smeltAndSmoke(RecipeCategory category, Item result, ItemLike input, float xp, int time, RecipeOutput consumer) {
        int smokeTime = time / 2;
        String inputName = input.asItem().toString().split(":")[1];
        String resultName = result.toString().split(":")[1];
        smeltingRecipe(category, result, input, xp, time).save(consumer, location(resultName + "_from_" + inputName, 0));
        smokingRecipe(category, result, input, xp, smokeTime).save(consumer, location(resultName + "_from_" + inputName, 2));
    }

    public static void smeltAndBlast(RecipeCategory category, Item result, float xp, int time, RecipeOutput consumer, ItemLike... ingredients) {
        int blastTime = time / 2;
        Ingredient ingredient = Ingredient.of(ingredients);
        String resultName = result.toString().split(":")[1];
        smeltingRecipe(category, result, xp, time, ingredients).save(consumer, location(resultName + "_from_ingredients", 0));
        blastingRecipe(category, result, xp, blastTime, ingredients).save(consumer, location(resultName + "_from_ingredients", 1));
    }

    public static void smeltAndSmoke(RecipeCategory category, Item result, float xp, int time, RecipeOutput consumer, ItemLike... ingredients) {
        int smokeTime = time / 2;
        Ingredient ingredient = Ingredient.of(ingredients);
        String resultName = result.toString().split(":")[1];
        smeltingRecipe(category, result, xp, time, ingredients).save(consumer, location(resultName + "_from_ingredients", 0));
        smokingRecipe(category, result, xp, smokeTime, ingredients).save(consumer, location(resultName + "_from_ingredients", 2));
    }
    
    public static SimpleCookingRecipeBuilder smeltingRecipe(RecipeCategory category, Item result, ItemLike input, float xp, int time) {
        String inputName = input.asItem().toString().split(":")[1];
        return SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), category, result, xp, time).unlockedBy("has_" + inputName, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    
    public static SimpleCookingRecipeBuilder blastingRecipe(RecipeCategory category, Item result, ItemLike input, float xp, int time) {
        String inputName = input.asItem().toString().split(":")[1];
        return SimpleCookingRecipeBuilder.blasting(Ingredient.of(input), category, result, xp, time).unlockedBy("has_" + inputName, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }
    
    public static SimpleCookingRecipeBuilder smokingRecipe(RecipeCategory category, Item result, ItemLike input, float xp, int time) {
        String inputName = input.asItem().toString().split(":")[1];
        return SimpleCookingRecipeBuilder.smoking(Ingredient.of(input), category, result, xp, time).unlockedBy("has_" + inputName, InventoryChangeTrigger.TriggerInstance.hasItems(input));
    }

    public static SimpleCookingRecipeBuilder smeltingRecipe(RecipeCategory category, Item result, float xp, int time, ItemLike... ingredients) {
        Ingredient ingredient = Ingredient.of(ingredients);
        return SimpleCookingRecipeBuilder.smelting(ingredient, category, result, xp, time).unlockedBy("has_ingredients", InventoryChangeTrigger.TriggerInstance.hasItems(ingredients));
    }

    public static SimpleCookingRecipeBuilder blastingRecipe(RecipeCategory category, Item result, float xp, int time, ItemLike... ingredients) {
        Ingredient ingredient = Ingredient.of(ingredients);
        return SimpleCookingRecipeBuilder.blasting(ingredient, category, result, xp, time).unlockedBy("has_ingredients", InventoryChangeTrigger.TriggerInstance.hasItems(ingredients));
    }

    public static SimpleCookingRecipeBuilder smokingRecipe(RecipeCategory category, Item result, float xp, int time, ItemLike... ingredients) {
        Ingredient ingredient = Ingredient.of(ingredients);
        return SimpleCookingRecipeBuilder.smoking(ingredient, category, result, xp, time).unlockedBy("has_ingredients", InventoryChangeTrigger.TriggerInstance.hasItems(ingredients));
    }
}
