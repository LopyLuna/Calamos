package uwu.lopyluna.calamos.datagen;

import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.apache.commons.lang3.text.WordUtils;
import uwu.lopyluna.calamos.elements.ModBlocks;
import uwu.lopyluna.calamos.elements.ModCreativeTab;
import uwu.lopyluna.calamos.elements.ModItems;

import java.util.NoSuchElementException;

import static uwu.lopyluna.calamos.CalamosMod.MODID;

class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override
    protected void addTranslations() {
        this.item(ModItems.DEBUG_HEALTH);
        this.item(ModItems.GARNET);
        this.item(ModItems.JADE);
        this.item(ModItems.KUNZITE);
        this.item(ModItems.MOONSTONE);
        this.item(ModItems.OPAL);
        this.item(ModItems.RUBY);
        this.item(ModItems.SAPPHIRE);
        this.item(ModItems.SPINEL);
        this.item(ModItems.SUNSTONE);
        this.item(ModItems.TANZANITE);
        this.item(ModItems.TOPAZ);

        this.item(ModItems.BLOOD_ORB);
        this.item(ModItems.ECTOPLASMA);
        this.item(ModItems.BLOODBORE_INGOT);
        this.item(ModItems.TERRAULITE_INGOT);
        this.item(ModItems.CALAMATIUM_INGOT);
        this.item(ModItems.LEAD_INGOT);
        this.item(ModItems.ECTOLIGHT_INGOT);
        this.item(ModItems.MAGNETITE_INGOT);
        this.item(ModItems.OBSTEEL_INGOT);
        this.item(ModItems.STARINIUM_INGOT);
        this.item(ModItems.ULTIMITA_INGOT);
        this.item(ModItems.PLATINUM_INGOT);

        this.block(ModBlocks.STONE);
        this.block(ModBlocks.PURRASITE);
        this.block(ModBlocks.UMBRALITE);
        //Meteorite
        this.block(ModBlocks.METEORITE_ORE);
        this.block(ModBlocks.METEORITE);
        this.block(ModBlocks.METEORITE_BRICKS);
        this.block(ModBlocks.METEORITE_TILES);
        this.block(ModBlocks.GILDED_METEORITE_BRICKS);
        this.block(ModBlocks.LARGE_METEORITE_TILE);
        this.block(ModBlocks.COBBLED_METEORITE);
        this.block(ModBlocks.CUT_METEORITE);
        this.block(ModBlocks.POLISHED_METEORITE);
        this.block(ModBlocks.SMOOTH_METEORITE);
        this.item(ModItems.METEORITE_INGOT);
        this.item(ModItems.RAW_METEORITE);
        this.item(ModItems.METEORITE_REAPER);
        this.item(ModItems.METEORITE_SWORD);
        //Gems
        this.block(ModBlocks.GARNET_BLOCK);
        this.block(ModBlocks.JADE_BLOCK);
        this.block(ModBlocks.KUNZITE_BLOCK);
        this.block(ModBlocks.MOONSTONE_BLOCK);
        this.block(ModBlocks.OPAL_BLOCK);
        this.block(ModBlocks.RUBY_BLOCK);
        this.block(ModBlocks.SAPPHIRE_BLOCK);
        this.block(ModBlocks.SPINEL_BLOCK);
        this.block(ModBlocks.SUNSTONE_BLOCK);
        this.block(ModBlocks.TANZANITE_BLOCK);
        this.block(ModBlocks.TOPAZ_BLOCK);
        //Ores
        this.block(ModBlocks.COPPER_ORE);
        //--//
        this.tab(ModCreativeTab.CALAMOS_TAB);
        //Trim Materials
        this.trimMaterial("garnet");
        this.trimMaterial("jade");
        this.trimMaterial("kunzite");
        this.trimMaterial("moonstone");
        this.trimMaterial("opal");
        this.trimMaterial("ruby");
        this.trimMaterial("sapphire");
        this.trimMaterial("spinel");
        this.trimMaterial("sunstone");
        this.trimMaterial("tanzanite");
        this.trimMaterial("topaz");
    }

    private void tab(Holder<CreativeModeTab> tabHolder) {
        this.add(tabHolder, "itemGroup");
    }

    private void block(Holder<Block> blockHolder) {
        this.add(blockHolder, "block");
    }

    private void item(Holder<Item> itemHolder) {
        this.add(itemHolder, "item");
    }
    private void trimMaterial(String material) {
        String translated = WordUtils.capitalizeFully(material) + " Material";
        this.add("trim_material." + MODID + "." + material, translated);
    }
    private void add(Holder<?> holder, String type) {
        ResourceKey<?> resourceKey = holder.unwrapKey().orElseThrow(() -> new NoSuchElementException("No respective key. Check log"));
        ResourceLocation path = resourceKey.location();
        super.add(path.toLanguageKey(type), this.transform(path));
    }

    /**
     * Use to transform a ResourceLocation-form text into a spaced-form text.
     * e.g. example_transform_text -> Example Transform Text
     */
    private String transform(ResourceLocation id) {
        String path = id.getPath();
        int pathLength = path.length();
        StringBuilder stringBuilder = new StringBuilder(pathLength).append(Character.toUpperCase(path.charAt(0)));
        for (int i = 1; i < pathLength; i++) {
            char posChar = path.charAt(i);
            if (posChar == '_') {
                stringBuilder.append(' ');
            } else if (path.charAt(i - 1) == '_') {
                stringBuilder.append(Character.toUpperCase(posChar));
            } else stringBuilder.append(posChar);
        }
        return stringBuilder.toString();
    }
}
