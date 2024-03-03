package uwu.lopyluna.calamos.elements.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
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
    public static final TagKey<Item> GEMS_GARNET = createForge("gems/garnet", GARNET, "Garnet Gems");
    public static final TagKey<Item> GEMS_KUNZITE = createForge("gems/kunzite", KUNZITE, "Kunzite Gems");
    public static final TagKey<Item> GEMS_MOONSTONE = createForge("gems/moonstone", MOONSTONE, "Moonstone Gems");
    public static final TagKey<Item> GEMS_OPAL = createForge("gems/opal", OPAL, "Opal Gems");
    public static final TagKey<Item> GEMS_RUBY = createForge("gems/ruby", RUBY, "Ruby Gems");
    public static final TagKey<Item> GEMS_SAPPHIRE = createForge("gems/sapphire", SAPPHIRE, "Sapphire Gems");
    public static final TagKey<Item> GEMS_SPINEL = createForge("gems/spinel", SPINEL, "Spinel Gems");
    public static final TagKey<Item> GEMS_SUNSTONE = createForge("gems/sunstone", SUNSTONE, "Sunstone Gems");
    public static final TagKey<Item> GEMS_TANZANITE = createForge("gems/tanzanite", TANZANITE, "Tanzanite Gems");
    public static final TagKey<Item> GEMS_TOPAZ = createForge("gems/topaz", TOPAZ, "Topaz Gems");
    public static final TagKey<Item> RAW_MATERIALS_METEORITE = createForge("raw_materials/meteorite", RAW_METEORITE, "Raw Meteorite");
    public static final TagKey<Item> INGOTS_METEORITE = createForge("ingots/meteorite", METEORITE_INGOT, "Meteorite Ingots");
    public static final TagKey<Item> INGOTS_BLOODBORE = createForge("ingots/bloodbore", BLOODBORE_INGOT, "Bloodbore Ingots");
    public static final TagKey<Item> INGOTS_CALAMATIUM = createForge("ingots/calamatium", CALAMATIUM_INGOT, "Calamatium Ingots");
    public static final TagKey<Item> INGOTS_ECTOLIGHT = createForge("ingots/ectolight", ECTOLIGHT_INGOT, "Ectolight Ingots");
    public static final TagKey<Item> INGOTS_LEAD = createForge("ingots/lead", LEAD_INGOT, "Lead Ingots");
    public static final TagKey<Item> INGOTS_MAGNETITE = createForge("ingots/magnetite", MAGNETITE_INGOT, "Magnetite Ingots");
    public static final TagKey<Item> INGOTS_OBSTEEL = createForge("ingots/obsteel", OBSTEEL_INGOT, "Obsteel Ingots");
    public static final TagKey<Item> INGOTS_PLATINUM = createForge("ingots/platinum", PLATINUM_INGOT, "Platinum Ingots");
    public static final TagKey<Item> INGOTS_STARINIUM = createForge("ingots/starinium", STARINIUM_INGOT, "Starinium Ingots");
    public static final TagKey<Item> INGOTS_TERRAULITE = createForge("ingots/terraulite", TERRAULITE_INGOT, "Terraulite Ingots");
    public static final TagKey<Item> INGOTS_ULTIMITA = createForge("ingots/ultimita", ULTIMITA_INGOT, "Ultimita Ingots");
    public static final TagKey<Item> INGOTS_VOLCANITE = createForge("ingots/volcanite", VOLCANITE_INGOT, "Volcanite Ingots");
    public static final TagKey<Item> INGOTS_URANIUM = createForge("ingots/uranium", URANIUM_INGOT, "Uranium Ingots");
    public static final TagKey<Item> RAW_MATERIALS_URANIUM = createForge("raw_materials/uranium", RAW_URANIUM, "Raw Uranium");
    public static final TagKey<Item> STORAGE_BLOCKS_GARNET = createForgeBlockItem("storage_blocks/garnet", GARNET_BLOCK, "Garnet Storage Blocks");
    public static final TagKey<Item> STORAGE_BLOCKS_JADE = createForgeBlockItem("storage_blocks/jade", JADE_BLOCK, "Jade Storage Blocks");
    public static final TagKey<Item> STORAGE_BLOCKS_KUNZITE = createForgeBlockItem("storage_blocks/kunzite", KUNZITE_BLOCK, "Kunzite Storage Blocks");
    public static final TagKey<Item> STORAGE_BLOCKS_MOONSTONE = createForgeBlockItem("storage_blocks/moonstone", MOONSTONE_BLOCK, "Moonstone Storage Blocks");
    public static final TagKey<Item> STORAGE_BLOCKS_OPAL = createForgeBlockItem("storage_blocks/opal", OPAL_BLOCK, "Opal Storage Blocks");
    public static final TagKey<Item> STORAGE_BLOCKS_RUBY = createForgeBlockItem("storage_blocks/ruby", RUBY_BLOCK, "Ruby Storage Blocks");
    public static final TagKey<Item> STORAGE_BLOCKS_SAPPHIRE = createForgeBlockItem("storage_blocks/sapphire", SAPPHIRE_BLOCK, "Sapphire Storage Blocks");
    public static final TagKey<Item> STORAGE_BLOCKS_SPINEL = createForgeBlockItem("storage_blocks/spinel", SPINEL_BLOCK, "Spinel Storage Blocks");
    public static final TagKey<Item> STORAGE_BLOCKS_SUNSTONE = createForgeBlockItem("storage_blocks/sunstone", SUNSTONE_BLOCK, "Sunstone Storage Blocks");
    public static final TagKey<Item> STORAGE_BLOCKS_TANZANITE = createForgeBlockItem("storage_blocks/tanzanite", TANZANITE_BLOCK, "Tanzanite Storage Blocks");
    public static final TagKey<Item> STORAGE_BLOCKS_TOPAZ = createForgeBlockItem("storage_blocks/topaz", TOPAZ_BLOCK, "Topaz Storage Blocks");
    public static final TagKey<Item> STORAGE_BLOCKS_URANIUM = createForge("storage_blocks/uranium", ModItems.URANIUM_BLOCK, "Uranium Storage Blocks");
    private static TagKey<Item> createForge(String name, Supplier<? extends Item> value, String engTranslation) {
        return create(new ResourceLocation("forge", name), value, engTranslation);
    }
    
    private static TagKey<Item> createForgeBlockItem(String name, Supplier<? extends Block> value, String engTranslation) {
        return createBlockItem(new ResourceLocation("forge", name), value, engTranslation);
    }
    private static TagKey<Item> createModBlockItem(String name, Supplier<? extends Block> value, String engTranslation) {
        return createBlockItem(new ResourceLocation(CalamosMod.MODID, name), value, engTranslation);
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
