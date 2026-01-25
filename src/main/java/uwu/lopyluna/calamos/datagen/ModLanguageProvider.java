package uwu.lopyluna.calamos.datagen;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import oshi.util.tuples.Triplet;
import uwu.lopyluna.calamos.elements.*;
import uwu.lopyluna.calamos.elements.items.equipment.modifier.Modifier;
import uwu.lopyluna.calamos.elements.tag.ModItemTags;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

import static uwu.lopyluna.calamos.CalamosMod.MODID;

class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override
    protected void addTranslations() {
        for (DeferredHolder<Item, ? extends Item> registry : ModItems.getItems()) {
            if (registry.get() instanceof BlockItem) continue;
            this.item(registry);
        }

        for (DeferredHolder<Block, ? extends Block> registry : ModBlocks.getBlocks()) {
            this.block(registry);
        }
        for (DeferredHolder<Block, ? extends Block> registry : ModDecorativeBlocks.getBlocks()) {
            this.block(registry);
        }
        for (DeferredHolder<Attribute, ? extends Attribute> registry : ModAttributes.ATTRIBUTES.getEntries()) {
            this.add(registry.get().getDescriptionId(), transform(registry.get().getDescriptionId().replace("attribute.name.calamos.", "")));
        }
        List<String> translatedModifiers = new ArrayList<>();
        for (DeferredHolder<Modifier, ? extends Modifier> registry : ModModifiers.MODIFIERS.getEntries()) {
            String langKey = registry.getId().withPath(registry.get().assetName()).toLanguageKey("modifier");
            if (!translatedModifiers.contains(langKey)) {
                this.add(langKey, transform(registry.getId().withPath(registry.get().assetName())) + " %s");
                translatedModifiers.add(langKey);
            }
        }
        /*
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
        this.item(ModItems.VOLCANITE_INGOT);

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
        //Loot bags
        this.item(ModItems.TEST_LOOTBAG);

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
        //Tab//
         */
        this.tab(ModCreativeTab.CALAMOS_TAB);
        this.tab(ModCreativeTab.DECORATION_TAB);
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

        //Enchantments
        this.enchantment(ModEnchantmentProvider.SAVING_GRACE);
        this.enchantment(ModEnchantmentProvider.FLIGHT_CHARGE);
        this.enchantment(ModEnchantmentProvider.FAST_FLIGHT);
        this.enchantment(ModEnchantmentProvider.FELLING);
        
        //Effects
        this.effect(ModEffects.PESTIS);
        
        //Tooltips
        this.string("calamos.potion.tooltip.timer", "§9Timer: §6");
        this.string("calamos.potion.tooltip.level", "§9Level: §6");
        this.string("calamos.potion.tooltip.drink_time", "§9Drink Time: §6");
        this.string("calamos.potion.tooltip.instant", "§6'Instant'");
        this.string("calamos.potion.tooltip.seconds", "§9 (Sec)");
        this.string("calamos.potion.tooltip.cooldown", "§9Cooldown: §6");
        this.string("curios.identifier.wings", "Wings");
        this.string("curios.identifier.accessory", "Accessory");

        // Curio Conditions
        this.string("calamos.tooltip.condition.above_health_percent", "When Above %s%% Health:");
        this.string("calamos.tooltip.condition.below_health_percent", "When Below %s%% Health:");
        this.string("calamos.tooltip.condition.above_health", "When Above %s Health:");
        this.string("calamos.tooltip.condition.below_health", "When Below %s Health:");
        this.string("calamos.tooltip.condition.above_attribute_percent", "When Above %s%% %s:");
        this.string("calamos.tooltip.condition.below_attribute_percent", "When Below %s%% %s:");
        this.string("calamos.tooltip.condition.above_attribute", "When Above %s %s:");
        this.string("calamos.tooltip.condition.below_attribute", "When Below %s %s:");

        this.string("calamos.tooltip.modifier_stats", "Modifier Stats:");

        this.container("hallow_workbench");
        this.container("sawmill");
        this.container("metal_grinder");
        
        for (Triplet<TagKey<Item>, Supplier<? extends Item>, String> tag : ModItemTags.ALL_TAGS) {
            ResourceLocation tagId = tag.getA().location();
            String tagNamespace = tagId.getNamespace().equals("forge") ? "c" : tagId.getNamespace();
            super.add("tag.item.%s.%s".formatted(tagNamespace, tagId.getPath().replace('/', '.')), tag.getC());
        }
        for (Triplet<TagKey<Item>, Supplier<? extends Block>, String> tag : ModItemTags.BLOCK_ITEM_TAGS) {
            ResourceLocation tagId = tag.getA().location();
            String tagNamespace = tagId.getNamespace().equals("forge") ? "c" : tagId.getNamespace();
            super.add("tag.item.%s.%s".formatted(tagNamespace, tagId.getPath().replace('/', '.')), tag.getC());
        }
        this.add("tag.item.calamos.large_planks", "Large Planks");
        this.add("tag.item.calamos.otherworld_oak_logs", "Otherworld Oak Logs");
        this.add("tag.item.calamos.twilight_logs", "Twilight Logs");
        this.add("tag.item.calamos.hollow_logs", "Hollow Logs");
        this.add("tag.item.calamos.reapers", "Reapers");
        this.add("tag.item.calamos.wings", "Wings");
        this.add("tag.item.curios.wings", "Wings");
        this.add("tag.item.curios.accessory", "Accessory");

        this.add("emi.category.calamos.sawmilling", "Sawmilling");
        this.add("emi.category.calamos.metal_grinding", "Metal Grinding");

        //Commands
        this.string("calamos.commands.reforge.failed.entity", "%s is not a valid entity for this command");
        this.string("calamos.commands.reforge.failed.itemless", "%s is not holding any item");
        this.string("calamos.commands.reforge.failed.incompatible", "%s cannot support that modifier");
        this.string("calamos.commands.reforge.failed", "Nothing changed. Targets either have no item in their hands or the modifier could not be applied");
        this.string("calamos.commands.reforge.success.single", "Applied modifier %s to %s's item");
        this.string("calamos.commands.reforge.success.multiple", "Applied modifier %s to %s entities");
        this.string("calamos.commands.reforge.success.single_random", "Applied random modifier to %s's item");
        this.string("calamos.commands.reforge.success.multiple_random", "Applied random modifier to %s entities");
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

    private void string(String key, String value) {
        super.add(key, value);
    }
    private void trimMaterial(String material) {
        String translated = transform(material) + " Material";
        super.add("trim_material.%s.%s".formatted(MODID, material), translated);
    }
    private void enchantment(ResourceKey<Enchantment> key) {
        this.string(Util.makeDescriptionId("enchantment", key.location()), transform(key.location()));
    }
    private void effect(Holder<MobEffect> holder) {
        this.add(holder, "effect");
    }
    private void attribute(Holder<Attribute> holder) {
        this.add(holder, "attribute.name");
    }
    private void container(String containerName){
        String translated = transform(containerName);
        super.add("container.%s".formatted(containerName), translated);
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
        return this.transform(id.getPath());
    }


    /**
     * Use to transform a ResourceLocation-form text into a spaced-form text.
     * e.g. example_transform_text -> Example Transform Text
     */
    private String transform(String path) {
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
