package uwu.lopyluna.calamos.elements.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import uwu.lopyluna.calamos.elements.ModBlocks;
import uwu.lopyluna.calamos.elements.ModRecipes;

public class SawmillRecipe extends SingleItemRecipe {
    public final Ingredient ingredient;
    public final ItemStack result;
    public final RecipeType<?> type;
    public final RecipeSerializer<?> serializer;
    public final String group;

    public SawmillRecipe(RecipeType<?> pType, RecipeSerializer<?> pSerializer, String pGroup, Ingredient pIngredient, ItemStack pResult) {
        super(pType, pSerializer, pGroup, pIngredient, pResult);
        this.type = pType;
        this.serializer = pSerializer;
        this.group = pGroup;
        this.ingredient = pIngredient;
        this.result = pResult;
    }

    @Override
    public RecipeType<?> getType() {
        return this.type;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return this.serializer;
    }

    /**
     * Recipes with equal group are combined into one button in the recipe book
     */
    @Override
    public String getGroup() {
        return this.group;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return this.result;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();
        nonnulllist.add(this.ingredient);
        return nonnulllist;
    }

    /**
     * Used to determine if this recipe can fit in a grid of the given width/height
     */
    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public boolean matches(Container pContainer, Level pLevel) {
        return false;
    }

    @Override
    public ItemStack assemble(Container pContainer, RegistryAccess pRegistryAccess) {
        return this.result.copy();
    }

    public interface Factory<T extends SingleItemRecipe> {
        T create(String pGroup, Ingredient pIngredient, ItemStack pResult);
    }

    public static class Serializer implements RecipeSerializer<SawmillRecipe> {
        final SawmillRecipe.Factory<SawmillRecipe> factory;
        private final Codec<SawmillRecipe> codec;

        protected Serializer(SawmillRecipe.Factory<SawmillRecipe> pFactory) {
            this.factory = pFactory;
            this.codec = RecordCodecBuilder.create(
                    p_311738_ -> p_311738_.group(
                                    ExtraCodecs.strictOptionalField(Codec.STRING, "group", "").forGetter(p_300947_ -> p_300947_.group),
                                    Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(p_301068_ -> p_301068_.ingredient),
                                    ItemStack.RESULT_CODEC.forGetter(p_302316_ -> p_302316_.result)
                            )
                            .apply(p_311738_, pFactory::create)
            );
        }

        @Override
        public Codec<SawmillRecipe> codec() {
            return this.codec;
        }

        public SawmillRecipe fromNetwork(FriendlyByteBuf pBuffer) {
            String s = pBuffer.readUtf();
            Ingredient ingredient = Ingredient.fromNetwork(pBuffer);
            ItemStack itemstack = pBuffer.readItem();
            return this.factory.create(s, ingredient, itemstack);
        }

        public void toNetwork(FriendlyByteBuf pBuffer, SawmillRecipe pRecipe) {
            pBuffer.writeUtf(pRecipe.group);
            pRecipe.ingredient.toNetwork(pBuffer);
            pBuffer.writeItem(pRecipe.result);
        }
    }
}
