package uwu.lopyluna.calamos.elements.tag;

import com.google.common.collect.Sets;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import oshi.util.tuples.Triplet;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Supplier;

import static uwu.lopyluna.calamos.elements.ModItems.*;
public class ModItemTags {
    //tag, respective item, ENG translation
    public static final Set<Triplet<TagKey<Item>, Supplier<? extends Item>, String>> ALL_TAGS = new LinkedHashSet<>();
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
    public static final TagKey<Item> INGOTS_STARINIUM = createForge("ingots/starinium", PLATINUM_INGOT, "Starinium Ingots");
    public static final TagKey<Item> INGOTS_TERRAULITE = createForge("ingots/terraulite", TERRAULITE_INGOT, "Terraulite Ingots");
    public static final TagKey<Item> INGOTS_ULTIMITA = createForge("ingots/ultimita", ULTIMITA_INGOT, "Ultimita Ingots");
    private static TagKey<Item> createForge(String name, Supplier<? extends Item> value, String engTranslation) {
        return create(new ResourceLocation("forge", name), value, engTranslation);
    }

    private static TagKey<Item> create(ResourceLocation name, Supplier<? extends Item> value, String engTranslation) {
        TagKey<Item> tag = ItemTags.create(name);
        ALL_TAGS.add(new Triplet<>(tag, value, engTranslation));
        return tag;
    }

    public static void staticInit() {}
}
