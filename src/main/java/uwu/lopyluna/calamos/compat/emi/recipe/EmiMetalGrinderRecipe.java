package uwu.lopyluna.calamos.compat.emi.recipe;

import dev.emi.emi.EmiPort;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.resources.ResourceLocation;
import uwu.lopyluna.calamos.compat.emi.CalamosEmiPlugin;
import uwu.lopyluna.calamos.elements.recipe.MetalGrindingRecipe;

import java.util.List;

public class EmiMetalGrinderRecipe implements EmiRecipe {
    private final ResourceLocation id;
    private final EmiIngredient input;
    private final EmiStack output;
    
    public EmiMetalGrinderRecipe(MetalGrindingRecipe recipe) {
        this.id = EmiPort.getId(recipe);
        this.input = EmiIngredient.of(recipe.getIngredients().get(0));
        this.output = EmiStack.of(EmiPort.getOutput(recipe));
    }
    
    public EmiRecipeCategory getCategory() {
        return CalamosEmiPlugin.METAL_GRINDING;
    }
    
    public ResourceLocation getId() {
        return this.id;
    }
    
    public List<EmiIngredient> getInputs() {
        return List.of(this.input);
    }
    
    public List<EmiStack> getOutputs() {
        return List.of(this.output);
    }
    
    public int getDisplayWidth() {
        return 76;
    }
    
    public int getDisplayHeight() {
        return 18;
    }
    
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(EmiTexture.EMPTY_ARROW, 26, 1);
        widgets.addSlot(this.input, 0, 0);
        widgets.addSlot(this.output, 58, 0).recipeContext(this);
    }
}
