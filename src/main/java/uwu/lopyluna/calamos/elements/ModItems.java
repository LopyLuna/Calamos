package uwu.lopyluna.calamos.elements;

import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.core.items.ParticleSpewingBlockItem;
import uwu.lopyluna.calamos.core.items.ParticleSpewingItem;
import uwu.lopyluna.calamos.core.items.accessories.BerserkerCrawItem;
import uwu.lopyluna.calamos.core.items.accessories.JarredCloudItem;
import uwu.lopyluna.calamos.core.items.equipment.armor.SpectreArmorItem;
import uwu.lopyluna.calamos.core.items.equipment.hook.HookItem;
import uwu.lopyluna.calamos.core.items.equipment.tool.CalamosReaper;
import uwu.lopyluna.calamos.core.items.equipment.tool.CalamosSword;
import uwu.lopyluna.calamos.core.items.equipment.tool.CalamosTiers;
import uwu.lopyluna.calamos.core.items.equipment.tool.VolcaniteSword;
import uwu.lopyluna.calamos.core.items.equipment.tool.arrow.irradiated.IrradiatedArrowItem;
import uwu.lopyluna.calamos.core.items.equipment.tool.base.*;
import uwu.lopyluna.calamos.core.items.equipment.wings.WingsItem;
import uwu.lopyluna.calamos.core.items.lootbags.TestLootbag;
import uwu.lopyluna.calamos.core.items.potions.HealingPotionItem;
import uwu.lopyluna.calamos.core.items.potions.RecallPotionItem;
import uwu.lopyluna.calamos.core.items.properties.BaseHeartUpgradeItem;
import uwu.lopyluna.calamos.core.items.properties.DynamiteItem;
import uwu.lopyluna.calamos.core.items.properties.HeartUpgradeItem;
import uwu.lopyluna.calamos.core.systems.health.HeartType;
import uwu.lopyluna.calamos.utilities.ModUtils;

import java.util.Collection;
import java.util.function.Supplier;


@SuppressWarnings({"unused"})
public final class ModItems {
    private static final Supplier<Item> SIMPLE_SUPPLIER = () -> new Item(new Item.Properties());
    public static final DeferredRegister.Items ITEMS = ModUtils.createRegister(DeferredRegister::createItems);

    public static final DeferredItem<Item> SPIRITUAL_BLOOD_CRYSTAL = ITEMS.register("spiritual_blood_crystal",
            () -> new BaseHeartUpgradeItem(new Item.Properties()));

    public static final DeferredItem<Item>
            GOLDEN_LIVING_BERRY = ITEMS.register("golden_living_berry",
                    () -> new HeartUpgradeItem(new Item.Properties(), HeartType.GOLDEN)),
            ENLIGHTENED_AETHER_FRUIT = ITEMS.register("enlightened_aether_fruit",
                    () -> new HeartUpgradeItem(new Item.Properties(), HeartType.ENLIGHTENED)),
            STELLATECH_PRISM_CRYSTAL = ITEMS.register("stellatech_prism_crystal",
                    () -> new HeartUpgradeItem(new Item.Properties(), HeartType.STELLATECH));

    public static final DeferredItem<Item> GARNET = registerSimple("garnet");
    public static final DeferredItem<Item> JADE = registerSimple("jade");
    public static final DeferredItem<Item> KUNZITE = registerSimple("kunzite");
    public static final DeferredItem<Item> MOONSTONE = registerSimple("moonstone");
    public static final DeferredItem<Item> OPAL = registerSimple("opal");
    public static final DeferredItem<Item> RUBY = registerSimple("ruby");
    public static final DeferredItem<Item> SAPPHIRE = registerSimple("sapphire");
    public static final DeferredItem<Item> SPINEL = registerSimple("spinel");
    public static final DeferredItem<Item> SUNSTONE = registerSimple("sunstone");
    public static final DeferredItem<Item> TANZANITE = registerSimple("tanzanite");
    public static final DeferredItem<Item> TOPAZ = registerSimple("topaz");
    public static final DeferredItem<Item> METEORITE_INGOT = registerSimple("meteorite_ingot", new Item.Properties().fireResistant());
    public static final DeferredItem<Item> RAW_METEORITE = registerSimple("raw_meteorite", new Item.Properties().fireResistant());
    public static final DeferredItem<Item> BLOOD_ORB = registerSimple("blood_orb");
    public static final DeferredItem<Item> ECTOPLASMA = registerSimple("ectoplasma");
    public static final DeferredItem<Item> WULFRUM_SCRAP = registerSimple("wulfrum_scrap");
    public static final DeferredItem<Item> ENERGY_CORE = registerSimple("energy_core");

    //Ingot
    public static final DeferredItem<Item> BLOODBORE_INGOT = registerSimple("bloodbore_ingot");
    public static final DeferredItem<Item> CALAMATIUM_INGOT = registerSimple("calamatium_ingot");
    public static final DeferredItem<Item> ECTOLIGHT_INGOT = registerSimple("ectolight_ingot");
    public static final DeferredItem<Item> LEAD_INGOT = registerSimple("lead_ingot");
    public static final DeferredItem<Item> MAGNETITE_INGOT = registerSimple("magnetite_ingot");
    public static final DeferredItem<Item> OBSTEEL_INGOT = registerSimple("obsteel_ingot");
    public static final DeferredItem<Item> PLATINUM_INGOT = registerSimple("platinum_ingot");
    public static final DeferredItem<Item> STARINIUM_INGOT = registerSimple("starinium_ingot");
    public static final DeferredItem<Item> TERRAULITE_INGOT = registerSimple("terraulite_ingot");
    public static final DeferredItem<Item> ULTIMITA_INGOT = registerSimple("ultimita_ingot");
    public static final DeferredItem<Item> VOLCANITE_INGOT = registerSimple("volcanite_ingot");
    public static final DeferredItem<Item> STELLAR_INGOT = registerSimple("stellar_ingot");
    public static final DeferredItem<Item> ELEGANT_BLOOM = registerSimple("elegant_bloom");
    public static final DeferredItem<Item> URANIUM_INGOT = register("uranium_ingot", () -> new ParticleSpewingItem(new Item.Properties(), ParticleTypes.GLOW, ParticleTypes.HAPPY_VILLAGER));
    public static final DeferredItem<Item> RAW_URANIUM = register("raw_uranium", () -> new ParticleSpewingItem(new Item.Properties(), ParticleTypes.GLOW, ParticleTypes.HAPPY_VILLAGER));
    public static final DeferredItem<Item> URANIUM_NUGGET = register("uranium_nugget", () -> new ParticleSpewingItem(new Item.Properties(), ParticleTypes.GLOW, ParticleTypes.HAPPY_VILLAGER));
    public static final DeferredItem<Item> PALLADIUM_INGOT = registerSimple("palladium_ingot");
    public static final DeferredItem<Item> RAW_PALLADIUM = registerSimple("raw_palladium");
    public static final DeferredItem<Item> PALLADIUM_NUGGET = registerSimple("palladium_nugget");

    public static final DeferredItem<Item> METEORITE_REAPER = register("meteorite_reaper", () -> new CalamosReaper(CalamosTiers.METEORITE, 3, -2.4F, 5, true, new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> METEORITE_SWORD = register("meteorite_sword", () -> new CalamosSword(CalamosTiers.METEORITE, 3, -2.4F, true, new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> VOLCANITE_SWORD = register("volcanite_sword", () -> new VolcaniteSword(new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> TEST_LOOTBAG = register("test_lootbag", () -> new TestLootbag(new Item.Properties().fireResistant()));

    //HOOKS
    public static final DeferredItem<HookItem>
            GRAPPLING_HOOK = registerHook("grappling_hook", 1, 18.75f, 0),
            AMETHYST_HOOK = registerHook("amethyst_hook", 1, 18.75f, 1),
            TOPAZ_HOOK = registerHook("topaz_hook", 1, 20.625f, 2),
            SAPPHIRE_HOOK = registerHook("sapphire_hook", 1, 22.5f, 3),
            EMERALD_HOOK = registerHook("emerald_hook", 1, 24.375f, 4),
            RUBY_HOOK = registerHook("ruby_hook", 1, 26.25f, 5),
            DIAMOND_HOOK = registerHook("diamond_hook", 1, 29.125f, 6);

    //SIGNS
    public static final DeferredItem<Item> OTHERWORLD_OAK_SIGN = register("otherworld_oak_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), ModDecorativeBlocks.OTHERWORLD_OAK_SIGN.get(), ModDecorativeBlocks.OTHERWORLD_OAK_WALL_SIGN.get()));
    public static final DeferredItem<Item> OTHERWORLD_OAK_HANGING_SIGN = register("otherworld_oak_hanging_sign",
            () -> new HangingSignItem(ModDecorativeBlocks.OTHERWORLD_OAK_HANGING_SIGN.get(), ModDecorativeBlocks.OTHERWORLD_OAK_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final DeferredItem<Item> TWILIGHT_SIGN = register("twilight_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), ModDecorativeBlocks.TWILIGHT_SIGN.get(), ModDecorativeBlocks.TWILIGHT_WALL_SIGN.get()));
    public static final DeferredItem<Item> TWILIGHT_HANGING_SIGN = register("twilight_hanging_sign",
            () -> new HangingSignItem(ModDecorativeBlocks.TWILIGHT_HANGING_SIGN.get(), ModDecorativeBlocks.TWILIGHT_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final DeferredItem<Item> HOLLOW_SIGN = register("hollow_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), ModDecorativeBlocks.HOLLOW_SIGN.get(), ModDecorativeBlocks.HOLLOW_WALL_SIGN.get()));
    public static final DeferredItem<Item> HOLLOW_HANGING_SIGN = register("hollow_hanging_sign",
            () -> new HangingSignItem(ModDecorativeBlocks.HOLLOW_HANGING_SIGN.get(), ModDecorativeBlocks.HOLLOW_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    //PROJECTILES
    public static final DeferredItem<Item> DYNAMITE = register("dynamite", () -> new DynamiteItem(new Item.Properties().stacksTo(16)));
    public static final DeferredItem<Item> IRRADIATED_ARROW = register("irradiated_arrow", () -> new IrradiatedArrowItem(new Item.Properties().stacksTo(64)));

    //CURIOS ITEMS
    public static final DeferredItem<Item> BERSERKER_CRAW = register("berserker_craw", BerserkerCrawItem::new);
    public static final DeferredItem<Item> WINGS = register("wings", WingsItem::new);
    public static final DeferredItem<Item> JARRED_CLOUD = register("jarred_cloud", JarredCloudItem::new);

    //ARMOR
    public static final DeferredItem<SpectreArmorItem> SPECTRE_ROBE = register("spectre_robe", () -> SpectreArmorItem.armor(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<SpectreArmorItem> SPECTRE_PANTS = register("spectre_pants", () -> SpectreArmorItem.armor(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<SpectreArmorItem> SPECTRE_MASK = register("spectre_mask", () -> SpectreArmorItem.helmet(new Item.Properties(), false));
    public static final DeferredItem<SpectreArmorItem> SPECTRE_HOOD = register("spectre_hood", () -> SpectreArmorItem.helmet(new Item.Properties(), true));
    //TOOLS
    public static final DeferredItem<Item> COPPER_SWORD = register("copper_sword", () -> new CalamosSwordItem(CalamosTiers.COPPER, 2, -2.4F, new Item.Properties()));
    public static final DeferredItem<Item> COPPER_AXE = register("copper_axe", () -> new CalamosAxeItem(CalamosTiers.COPPER, 4.0F, -3.0F, new Item.Properties()));
    public static final DeferredItem<Item> COPPER_SHOVEL = register("copper_shovel", () -> new CalamosShovelItem(CalamosTiers.COPPER, 1.0F, -3.0F, new Item.Properties()));
    public static final DeferredItem<Item> COPPER_HOE = register("copper_hoe", () -> new CalamosHoeItem(CalamosTiers.COPPER, -1, 0.0F, new Item.Properties()));
    public static final DeferredItem<Item> COPPER_PICKAXE = register("copper_pickaxe", () -> new CalamosPickaxeItem(CalamosTiers.COPPER, 1, -2.8F, new Item.Properties()));

    public static final DeferredItem<Item> PLATINUM_SWORD = register("platinum_sword", () -> new CalamosSwordItem(CalamosTiers.PLATINUM, 4, -2.4F, new Item.Properties()));
    public static final DeferredItem<Item> PLATINUM_AXE = register("platinum_axe", () -> new CalamosAxeItem(CalamosTiers.PLATINUM, 5.5F, -3.0F, new Item.Properties()));
    public static final DeferredItem<Item> PLATINUM_SHOVEL = register("platinum_shovel", () -> new CalamosShovelItem(CalamosTiers.PLATINUM, 1.5F, -3.0F, new Item.Properties()));
    public static final DeferredItem<Item> PLATINUM_HOE = register("platinum_hoe", () -> new CalamosHoeItem(CalamosTiers.PLATINUM, -1, 0.0F, new Item.Properties()));
    public static final DeferredItem<Item> PLATINUM_PICKAXE = register("platinum_pickaxe", () -> new CalamosPickaxeItem(CalamosTiers.PLATINUM, 1, -2.8F, new Item.Properties()));


    //SPAWN EGGS

    //BOSSES
    public static final DeferredItem<Item> BOONE_THE_BOOM_SPAWN_EGG = register("boone_the_boom_spawn_egg", () ->
            new SpawnEggItem(ModEntity.BOONE_THE_BOOM.get(), 11013646, 15658718, new Item.Properties()));
    public static final DeferredItem<Item> WILDFIRE_SPAWN_EGG = register("wildfire_spawn_egg", () ->
            new SpawnEggItem(ModEntity.WILDFIRE.get(), 16167425, 11013646, new Item.Properties()));
    public static final DeferredItem<Item> EYE_SPAWN_EGG = register("eye_spawn_egg", () ->
            new SpawnEggItem(ModEntity.EYE.get(), 11013646, 16167425, new Item.Properties()));

    //MINI BOSSES

    //MOBS

    //PEACEFUL MOBS



    //POTIONS

    //HEALING POTIONS
    public static int healingPotionCooldown = 80 * 20;
    public static int recoveringPotionCooldown = 40 * 20;
    public static float recoveringDivisionFactor = 0.75f;

    public static int weakHealingFactor = 25;
    public static int healingFactor = 75;
    public static int duplexHealingFactor = 125;
    public static int enhancedHealingFactor = 250;
    public static int sublimeHealingFactor = 400;

    public static final DeferredItem<Item> WEAK_HEALING_POTION = register("weak_healing_potion", () ->
            new HealingPotionItem(weakHealingFactor, healingPotionCooldown, 0, new Item.Properties()));
    public static final DeferredItem<Item> WEAK_RECOVERING_POTION = register("weak_recovering_potion", () ->
            new HealingPotionItem((int) ((float) weakHealingFactor * recoveringDivisionFactor), recoveringPotionCooldown, 0, new Item.Properties()));

    public static final DeferredItem<Item> HEALING_POTION = register("healing_potion", () ->
            new HealingPotionItem(healingFactor, healingPotionCooldown, 0, new Item.Properties()));
    public static final DeferredItem<Item> RECOVERING_POTION = register("recovering_potion", () ->
            new HealingPotionItem((int) ((float) healingFactor * recoveringDivisionFactor), recoveringPotionCooldown, 0, new Item.Properties()));

    public static final DeferredItem<Item> DUPLEX_HEALING_POTION = register("duplex_healing_potion", () ->
            new HealingPotionItem(duplexHealingFactor, healingPotionCooldown, 0, new Item.Properties()));
    public static final DeferredItem<Item> DUPLEX_RECOVERING_POTION = register("duplex_recovering_potion", () ->
            new HealingPotionItem((int) ((float) duplexHealingFactor * recoveringDivisionFactor), recoveringPotionCooldown, 0, new Item.Properties()));

    public static final DeferredItem<Item> ENHANCED_HEALING_POTION = register("enhanced_healing_potion", () ->
            new HealingPotionItem(enhancedHealingFactor, healingPotionCooldown, 0, new Item.Properties()));
    public static final DeferredItem<Item> ENHANCED_RECOVERING_POTION = register("enhanced_recovering_potion", () ->
            new HealingPotionItem((int) ((float) enhancedHealingFactor * recoveringDivisionFactor), recoveringPotionCooldown, 0, new Item.Properties()));

    public static final DeferredItem<Item> SUBLIME_HEALING_POTION = register("sublime_healing_potion", () ->
            new HealingPotionItem(sublimeHealingFactor, healingPotionCooldown, 0, new Item.Properties()));
    public static final DeferredItem<Item> SUBLIME_RECOVERING_POTION = register("sublime_recovering_potion", () ->
            new HealingPotionItem((int) ((float) sublimeHealingFactor * recoveringDivisionFactor), recoveringPotionCooldown, 0, new Item.Properties()));

    public static final DeferredItem<Item> RECALL_POTION = register("recall_potion", () ->
            new RecallPotionItem(healingPotionCooldown, 0, new Item.Properties()));
    
    public static final DeferredItem<Item> URANIUM_BLOCK = register("uranium_block", () -> new ParticleSpewingBlockItem(ModBlocks.URANIUM_BLOCK.get(), new Item.Properties(), ParticleTypes.GLOW, ParticleTypes.HAPPY_VILLAGER));
    public static final DeferredItem<Item> RAW_URANIUM_BLOCK = register("raw_uranium_block", () -> new ParticleSpewingBlockItem(ModBlocks.RAW_URANIUM_BLOCK.get(), new Item.Properties(), ParticleTypes.GLOW, ParticleTypes.HAPPY_VILLAGER));
    
    private static DeferredItem<Item> registerSimple(String name, Item.Properties itemProperties) {
        return register(name, () -> new Item(itemProperties));
    }

    private static DeferredItem<Item> registerSimple(String name) {
        return register(name, SIMPLE_SUPPLIER);
    }

    private static DeferredItem<HookItem> registerHook(String name, int amount, float range, int variant) {
        return register(name, () -> new HookItem(amount, range, variant));
    }

    private static <T extends Item> DeferredItem<T> register(String id, Supplier<T> pIProp) {
        return ITEMS.register(id.toLowerCase(), pIProp);
    }

    public static void staticInit() {
        CalamosMod.LOGGER.info("metbal");
    }

    //private-package so block register class can use
    static void registerBlockItem(Holder<Block> blockHolder) {
        registerBlockItem(blockHolder, new Item.Properties());
    }

    //private-package so block register class can use
    static void registerBlockItem(Holder<Block> blockHolder, Item.Properties properties) {
        ITEMS.registerSimpleBlockItem(blockHolder, properties);
    }
    static void registerSpecialBlockItem(Holder<Block> blockHolder, Supplier<? extends BlockItem> sup) {
        ITEMS.register(blockHolder.unwrapKey().get().toString(), sup);
    }

    static void registerSimpleItem(String name) {
        ITEMS.registerSimpleItem(name);
    }

    public static Collection<DeferredHolder<Item, ? extends Item>> getItems() {
        return ITEMS.getEntries();
    }
}
