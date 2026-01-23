package uwu.lopyluna.calamos.compat.emi;

import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.compat.emi.recipe.EmiMetalGrinderRecipe;
import uwu.lopyluna.calamos.compat.emi.recipe.EmiSawmillRecipe;
import uwu.lopyluna.calamos.elements.ModBlocks;
import uwu.lopyluna.calamos.elements.ModRecipes;
import uwu.lopyluna.calamos.elements.recipe.MetalGrindingRecipe;
import uwu.lopyluna.calamos.elements.recipe.SawmillRecipe;

@EmiEntrypoint
public class CalamosEmiPlugin implements EmiPlugin {
    public static final EmiStack SAWMILL = EmiStack.of(ModBlocks.SAWMILL);
    public static final EmiStack METAL_GRINDER = EmiStack.of(ModBlocks.METAL_GRINDER);
    public static final EmiRecipeCategory SAW_MILLING
            = new EmiRecipeCategory(CalamosMod.asResource("sawmilling"), SAWMILL);
    public static final EmiRecipeCategory METAL_GRINDING
            = new EmiRecipeCategory(CalamosMod.asResource("metal_grinding"), METAL_GRINDER);
    
    public CalamosEmiPlugin() {
    }
    
    @Override
    public void register(EmiRegistry registry) {
        // Tell EMI to add a tab for your category
        registry.addCategory(SAW_MILLING);
        registry.addCategory(METAL_GRINDING);
        
        // Add all the workstations your category uses
        registry.addWorkstation(SAW_MILLING, SAWMILL);
        registry.addWorkstation(METAL_GRINDING, METAL_GRINDER);
        
        RecipeManager manager = registry.getRecipeManager();
        
        // Use vanilla's concept of your recipes and pass them to your EmiRecipe representation
        for (RecipeHolder<SawmillRecipe> recipe : manager.getAllRecipesFor(ModRecipes.SAW_MILLING.get())) {
            registry.addRecipe(new EmiSawmillRecipe(recipe.value()));
        }
        for (RecipeHolder<MetalGrindingRecipe> recipe : manager.getAllRecipesFor(ModRecipes.METAL_GRINDING.get())) {
            registry.addRecipe(new EmiMetalGrinderRecipe(recipe.value()));
        }
    }
}
