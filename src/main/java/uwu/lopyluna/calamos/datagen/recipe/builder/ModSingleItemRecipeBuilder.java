package uwu.lopyluna.calamos.datagen.recipe.builder;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import uwu.lopyluna.calamos.elements.recipe.MetalGrindingRecipe;
import uwu.lopyluna.calamos.elements.recipe.ModSingleItemRecipe;
import uwu.lopyluna.calamos.elements.recipe.SawmillRecipe;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ModSingleItemRecipeBuilder implements RecipeBuilder {
    private final RecipeCategory category;
    private final Item result;
    private final Ingredient ingredient;
    private final int count;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    @Nullable
    private String group;
    private final ModSingleItemRecipe.Factory<?> factory;
    
    public ModSingleItemRecipeBuilder(RecipeCategory p_251425_, ModSingleItemRecipe.Factory<?> p_312361_, Ingredient p_251221_, ItemLike p_251302_, int p_250964_) {
        this.category = p_251425_;
        this.factory = p_312361_;
        this.result = p_251302_.asItem();
        this.ingredient = p_251221_;
        this.count = p_250964_;
    }
    
    public static ModSingleItemRecipeBuilder sawMilling(Ingredient pIngredient, RecipeCategory pCategory, ItemLike pResult) {
        return new ModSingleItemRecipeBuilder(pCategory, SawmillRecipe::new, pIngredient, pResult, 1);
    }
    
    public static ModSingleItemRecipeBuilder sawMilling(Ingredient pIngredient, RecipeCategory pCategory, ItemLike pResult, int pCount) {
        return new ModSingleItemRecipeBuilder(pCategory, SawmillRecipe::new, pIngredient, pResult, pCount);
    }
    
    public static ModSingleItemRecipeBuilder metalGrinding(Ingredient pIngredient, RecipeCategory pCategory, ItemLike pResult) {
        return new ModSingleItemRecipeBuilder(pCategory, MetalGrindingRecipe::new, pIngredient, pResult, 1);
    }
    
    public static ModSingleItemRecipeBuilder metalGrinding(Ingredient pIngredient, RecipeCategory pCategory, ItemLike pResult, int pCount) {
        return new ModSingleItemRecipeBuilder(pCategory, MetalGrindingRecipe::new, pIngredient, pResult, pCount);
    }
    
    public ModSingleItemRecipeBuilder unlockedBy(String pName, Criterion<?> pCriterion) {
        this.criteria.put(pName, pCriterion);
        return this;
    }
    
    public ModSingleItemRecipeBuilder group(@Nullable String pGroupName) {
        this.group = pGroupName;
        return this;
    }
    
    @Override
    public Item getResult() {
        return this.result;
    }
    
    @Override
    public void save(RecipeOutput pRecipeOutput, ResourceLocation pId) {
        this.ensureValid(pId);
        Advancement.Builder advancement$builder = pRecipeOutput.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pId))
                .rewards(AdvancementRewards.Builder.recipe(pId))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement$builder::addCriterion);
        ModSingleItemRecipe singleitemrecipe = this.factory
                .create(Objects.requireNonNullElse(this.group, ""), this.ingredient, new ItemStack(this.result, this.count));
        pRecipeOutput.accept(pId, singleitemrecipe, advancement$builder.build(pId.withPrefix("recipes/" + this.category.getFolderName() + "/")));
    }
    
    private void ensureValid(ResourceLocation pId) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + pId);
        }
    }
}
