package uwu.lopyluna.calamos.datagen;

import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import uwu.lopyluna.calamos.elements.ModItems;
import uwu.lopyluna.calamos.utilities.ModUtils;

import java.util.function.Supplier;

class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        this.basicItem(ModItems.DEBUG_HEALTH);
        this.basicItem(ModItems.GARNET);
        this.basicItem(ModItems.JADE);
        this.basicItem(ModItems.KUNZITE);
        this.basicItem(ModItems.MOONSTONE);
        this.basicItem(ModItems.OPAL);
        this.basicItem(ModItems.RUBY);
        this.basicItem(ModItems.SAPPHIRE);
        this.basicItem(ModItems.SPINEL);
        this.basicItem(ModItems.SUNSTONE);
        this.basicItem(ModItems.TANZANITE);
        this.basicItem(ModItems.TOPAZ);
    }

    private void basicItem(Supplier<? extends Item> item) {
        super.basicItem(item.get());
    }

    private void handheld32(DeferredHolder<Item, ? extends Item> item) {
        handheld(item, 32);
    }

    private void handheld48(DeferredHolder<Item, ? extends Item> item) {
        handheld(item, 48);
    }

    private void handheld64(DeferredHolder<Item, ? extends Item> item) {
        handheld(item, 64);
    }

    private void handheld(DeferredHolder<Item, ? extends Item> item, int x) {
        ResourceLocation name = item.getKey().location().withPrefix("item/");
        super.withExistingParent(name.toString(), ModUtils.location("item/templates/handheld%sx".formatted(x)))
                .texture("layer0", name);
    }
}
