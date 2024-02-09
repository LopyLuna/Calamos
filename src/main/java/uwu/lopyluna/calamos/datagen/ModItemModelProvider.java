package uwu.lopyluna.calamos.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import uwu.lopyluna.calamos.elements.ModItems;

import java.util.function.Supplier;

public class ModItemModelProvider extends ItemModelProvider {
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
}
