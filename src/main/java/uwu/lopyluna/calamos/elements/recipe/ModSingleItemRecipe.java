package uwu.lopyluna.calamos.elements.recipe;

import com.mojang.datafixers.Products;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

import java.util.Objects;

public abstract class ModSingleItemRecipe implements Recipe<SingleRecipeInput> {
    protected final Ingredient ingredient;
    protected final ItemStack result;
    private final RecipeType<?> type;
    private final RecipeSerializer<?> serializer;
    protected final String group;

    public ModSingleItemRecipe(RecipeType<?> pType, RecipeSerializer<?> pSerializer, String pGroup, Ingredient pIngredient, ItemStack pResult) {
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
    public ItemStack getResultItem(HolderLookup.Provider lookup) {
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
    public ItemStack assemble(SingleRecipeInput p_345857_, HolderLookup.Provider p_335463_) {
        return this.result.copy();
    }

    public interface Factory<T extends ModSingleItemRecipe> {
        T create(String var1, Ingredient var2, ItemStack var3);
    }

    public static class Serializer<T extends ModSingleItemRecipe> implements RecipeSerializer<T> {
        final Factory<T> factory;
        private final MapCodec<T> codec;
        private final StreamCodec<RegistryFriendlyByteBuf, T> streamCodec;
        
        public Serializer(Factory<T> factory) {
            this.factory = factory;
            this.codec = RecordCodecBuilder.mapCodec((instance) -> {
                Products.P3<RecordCodecBuilder.Mu<T>, String, Ingredient, ItemStack> codec = instance.group(
                        Codec.STRING.optionalFieldOf("group", "").forGetter((ser) -> ser.group),
                        Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter((ser) -> ser.ingredient),
                        ItemStack.STRICT_CODEC.fieldOf("result").forGetter((ser) -> ser.result)
                );
                Objects.requireNonNull(factory);
                return codec.apply(instance, factory::create);
            });
            Objects.requireNonNull(factory);
            this.streamCodec = StreamCodec.composite(
                    ByteBufCodecs.STRING_UTF8, (recipe) -> recipe.group,
                    Ingredient.CONTENTS_STREAM_CODEC, (recipe) -> recipe.ingredient,
                    ItemStack.STREAM_CODEC, (recipe) -> recipe.result,
                    factory::create);
        }

        public MapCodec<T> codec() {
            return this.codec;
        }

        public StreamCodec<RegistryFriendlyByteBuf, T> streamCodec() {
            return this.streamCodec;
        }
    }
}
