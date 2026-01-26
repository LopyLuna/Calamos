package uwu.lopyluna.calamos.core.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import oshi.util.tuples.Triplet;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.elements.ModItems;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Supplier;

import static uwu.lopyluna.calamos.elements.ModBlocks.*;
import static uwu.lopyluna.calamos.elements.ModItems.*;

@SuppressWarnings({"unused"})
public class ModItemTags {
    //tag, respective item, ENG translation
    public static final Set<Triplet<TagKey<Item>, Supplier<? extends Item>, String>> ALL_TAGS = new LinkedHashSet<>();
    public static final Set<Triplet<TagKey<Item>, Supplier<? extends Block>, String>> BLOCK_ITEM_TAGS = new LinkedHashSet<>();
    public static final TagKey<Item> GEMS_GARNET = createCommon("gems/garnet", GARNET, "Garnet Gems");
    public static final TagKey<Item> GEMS_KUNZITE = createCommon("gems/kunzite", KUNZITE, "Kunzite Gems");
    public static final TagKey<Item> GEMS_MOONSTONE = createCommon("gems/moonstone", MOONSTONE, "Moonstone Gems");
    public static final TagKey<Item> GEMS_OPAL = createCommon("gems/opal", OPAL, "Opal Gems");
    public static final TagKey<Item> GEMS_RUBY = createCommon("gems/ruby", RUBY, "Ruby Gems");
    public static final TagKey<Item> GEMS_SAPPHIRE = createCommon("gems/sapphire", SAPPHIRE, "Sapphire Gems");
    public static final TagKey<Item> GEMS_SPINEL = createCommon("gems/spinel", SPINEL, "Spinel Gems");
    public static final TagKey<Item> GEMS_SUNSTONE = createCommon("gems/sunstone", SUNSTONE, "Sunstone Gems");
    public static final TagKey<Item> GEMS_TANZANITE = createCommon("gems/tanzanite", TANZANITE, "Tanzanite Gems");
    public static final TagKey<Item> GEMS_TOPAZ = createCommon("gems/topaz", TOPAZ, "Topaz Gems");
    public static final TagKey<Item> RAW_MATERIALS_METEORITE = createCommon("raw_materials/meteorite", RAW_METEORITE, "Raw Meteorite");
    public static final TagKey<Item> INGOTS_METEORITE = createCommon("ingots/meteorite", METEORITE_INGOT, "Meteorite Ingots");
    public static final TagKey<Item> INGOTS_BLOODBORE = createCommon("ingots/bloodbore", BLOODBORE_INGOT, "Bloodbore Ingots");
    public static final TagKey<Item> INGOTS_CALAMATIUM = createCommon("ingots/calamatium", CALAMATIUM_INGOT, "Calamatium Ingots");
    public static final TagKey<Item> INGOTS_ECTOLIGHT = createCommon("ingots/ectolight", ECTOLIGHT_INGOT, "Ectolight Ingots");
    public static final TagKey<Item> INGOTS_LEAD = createCommon("ingots/lead", LEAD_INGOT, "Lead Ingots");
    public static final TagKey<Item> INGOTS_MAGNETITE = createCommon("ingots/magnetite", MAGNETITE_INGOT, "Magnetite Ingots");
    public static final TagKey<Item> INGOTS_OBSTEEL = createCommon("ingots/obsteel", OBSTEEL_INGOT, "Obsteel Ingots");
    public static final TagKey<Item> INGOTS_PLATINUM = createCommon("ingots/platinum", PLATINUM_INGOT, "Platinum Ingots");
    public static final TagKey<Item> INGOTS_STARINIUM = createCommon("ingots/starinium", STARINIUM_INGOT, "Starinium Ingots");
    public static final TagKey<Item> INGOTS_TERRAULITE = createCommon("ingots/terraulite", TERRAULITE_INGOT, "Terraulite Ingots");
    public static final TagKey<Item> INGOTS_ULTIMITA = createCommon("ingots/ultimita", ULTIMITA_INGOT, "Ultimita Ingots");
    public static final TagKey<Item> INGOTS_VOLCANITE = createCommon("ingots/volcanite", VOLCANITE_INGOT, "Volcanite Ingots");
    public static final TagKey<Item> INGOTS_URANIUM = createCommon("ingots/uranium", URANIUM_INGOT, "Uranium Ingots");
    public static final TagKey<Item> INGOTS_PALLADIUM = createCommon("ingots/palladium", PALLADIUM_INGOT, "Palladium Ingots");
    public static final TagKey<Item> INGOTS_ELEGANT = createCommon("ingots/elegant", ELEGANT_BLOOM, "Elegant Ingots");
    public static final TagKey<Item> INGOTS_STELLAR = createCommon("ingots/stellar", STELLAR_INGOT, "Stellar Ingots");
    public static final TagKey<Item> RAW_MATERIALS_URANIUM = createCommon("raw_materials/uranium", RAW_URANIUM, "Raw Uranium");
    public static final TagKey<Item> STORAGE_BLOCKS_GARNET = createCommonBlockItem("storage_blocks/garnet", GARNET_BLOCK, "Garnet Storage Blocks");
    public static final TagKey<Item> STORAGE_BLOCKS_JADE = createCommonBlockItem("storage_blocks/jade", JADE_BLOCK, "Jade Storage Blocks");
    public static final TagKey<Item> STORAGE_BLOCKS_KUNZITE = createCommonBlockItem("storage_blocks/kunzite", KUNZITE_BLOCK, "Kunzite Storage Blocks");
    public static final TagKey<Item> STORAGE_BLOCKS_MOONSTONE = createCommonBlockItem("storage_blocks/moonstone", MOONSTONE_BLOCK, "Moonstone Storage Blocks");
    public static final TagKey<Item> STORAGE_BLOCKS_OPAL = createCommonBlockItem("storage_blocks/opal", OPAL_BLOCK, "Opal Storage Blocks");
    public static final TagKey<Item> STORAGE_BLOCKS_RUBY = createCommonBlockItem("storage_blocks/ruby", RUBY_BLOCK, "Ruby Storage Blocks");
    public static final TagKey<Item> STORAGE_BLOCKS_SAPPHIRE = createCommonBlockItem("storage_blocks/sapphire", SAPPHIRE_BLOCK, "Sapphire Storage Blocks");
    public static final TagKey<Item> STORAGE_BLOCKS_SPINEL = createCommonBlockItem("storage_blocks/spinel", SPINEL_BLOCK, "Spinel Storage Blocks");
    public static final TagKey<Item> STORAGE_BLOCKS_SUNSTONE = createCommonBlockItem("storage_blocks/sunstone", SUNSTONE_BLOCK, "Sunstone Storage Blocks");
    public static final TagKey<Item> STORAGE_BLOCKS_TANZANITE = createCommonBlockItem("storage_blocks/tanzanite", TANZANITE_BLOCK, "Tanzanite Storage Blocks");
    public static final TagKey<Item> STORAGE_BLOCKS_TOPAZ = createCommonBlockItem("storage_blocks/topaz", TOPAZ_BLOCK, "Topaz Storage Blocks");
    public static final TagKey<Item> STORAGE_BLOCKS_URANIUM = createCommon("storage_blocks/uranium", ModItems.URANIUM_BLOCK, "Uranium Storage Blocks");
    public static final TagKey<Item> STORAGE_BLOCKS_PALLADIUM = createCommonBlockItem("storage_blocks/palladium", PALLADIUM_BLOCK, "Palladium Storage Blocks");

    public static final TagKey<Item> COPPER_TOOL_MATERIALS = createMod("copper_tool_materials", () -> Items.COPPER_INGOT, "Copper Tool Materials");
    public static final TagKey<Item> PLATINUM_TOOL_MATERIALS = createMod("platinum_tool_materials", PLATINUM_INGOT, "Platinum Tool Materials");
    public static final TagKey<Item> STARINIUM_TOOL_MATERIALS = createMod("starinium_tool_materials", STARINIUM_INGOT, "Starinium Tool Materials");
    public static final TagKey<Item> TERRAULITE_TOOL_MATERIALS = createMod("terraulite_tool_materials", TERRAULITE_INGOT, "Terraulite Tool Materials");
    public static final TagKey<Item> ULTIMITA_TOOL_MATERIALS = createMod("ultimita_tool_materials", ULTIMITA_INGOT, "Ultimita Tool Materials");
    public static final TagKey<Item> VOLCANITE_TOOL_MATERIALS = createMod("volcanite_tool_materials", VOLCANITE_INGOT, "Volcanite Tool Materials");
    public static final TagKey<Item> URANIUM_TOOL_MATERIALS = createMod("uranium_tool_materials", URANIUM_INGOT, "Uranium Tool Materials");
    public static final TagKey<Item> PALLADIUM_TOOL_MATERIALS = createMod("palladium_tool_materials", PALLADIUM_INGOT, "Palladium Tool Materials");
    public static final TagKey<Item> ELEGANT_TOOL_MATERIALS = createMod("elegant_tool_materials", ELEGANT_BLOOM, "Elegant Tool Materials");
    public static final TagKey<Item> STELLAR_TOOL_MATERIALS = createMod("stellar_tool_materials", STELLAR_INGOT, "Stellar Tool Materials");
    public static final TagKey<Item> BLOODBORE_TOOL_MATERIALS = createMod("bloodbore_tool_materials", BLOODBORE_INGOT, "Bloodbore Tool Materials");
    public static final TagKey<Item> CALAMATIUM_TOOL_MATERIALS = createMod("calamatium_tool_materials", CALAMATIUM_INGOT, "Calamatium Tool Materials");
    public static final TagKey<Item> ECTOLIGHT_TOOL_MATERIALS = createMod("ectolight_tool_materials", ECTOLIGHT_INGOT, "Ectolight Tool Materials");
    public static final TagKey<Item> LEAD_TOOL_MATERIALS = createMod("lead_tool_materials", LEAD_INGOT, "Lead Tool Materials");
    public static final TagKey<Item> MAGNETITE_TOOL_MATERIALS = createMod("magnetite_tool_materials", MAGNETITE_INGOT, "Magnetite Tool Materials");
    public static final TagKey<Item> OBSTEEL_TOOL_MATERIALS = createMod("obsteel_tool_materials", OBSTEEL_INGOT, "Obsteel Tool Materials");
    public static final TagKey<Item> METEORITE_TOOL_MATERIALS = createMod("meteorite_tool_materials", METEORITE_INGOT, "Meteorite Tool Materials");

    private static TagKey<Item> createCommon(String name, Supplier<? extends Item> value, String engTranslation) {
        return create(ResourceLocation.fromNamespaceAndPath("c", name), value, engTranslation);
    }

    private static TagKey<Item> createMod(String name, Supplier<? extends Item> value, String engTranslation) {
        return create(CalamosMod.asResource(name), value, engTranslation);
    }
    
    private static TagKey<Item> createCommonBlockItem(String name, Supplier<? extends Block> value, String engTranslation) {
        return createBlockItem(ResourceLocation.fromNamespaceAndPath("c", name), value, engTranslation);
    }
    private static TagKey<Item> createModBlockItem(String name, Supplier<? extends Block> value, String engTranslation) {
        return createBlockItem(CalamosMod.asResource(name), value, engTranslation);
    }

    private static TagKey<Item> create(ResourceLocation name, Supplier<? extends Item> value, String engTranslation) {
        TagKey<Item> tag = ItemTags.create(name);
        ALL_TAGS.add(new Triplet<>(tag, value, engTranslation));
        return tag;
    }
    
    private static TagKey<Item> createBlockItem(ResourceLocation name, Supplier<? extends Block> value, String engTranslation) {
        TagKey<Item> tag = ItemTags.create(name);
        BLOCK_ITEM_TAGS.add(new Triplet<>(tag, value, engTranslation));
        return tag;
    }

    public static void staticInit() {}
}
