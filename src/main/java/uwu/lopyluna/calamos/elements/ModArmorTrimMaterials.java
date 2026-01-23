package uwu.lopyluna.calamos.elements;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import uwu.lopyluna.calamos.CalamosMod;

import java.util.Map;
import java.util.TreeMap;

public final class ModArmorTrimMaterials {
    public static final Map<ResourceKey<TrimMaterial>, TrimMaterial> MATERIALS = new TreeMap<>();
    public static final ResourceKey<TrimMaterial> GARNET = registryKey("garnet", ModItems.GARNET, 0xf33e35, 0.61F);
    public static final ResourceKey<TrimMaterial> JADE = registryKey("jade", ModItems.JADE, 0xa4c7a9, 0.62F);
    public static final ResourceKey<TrimMaterial> KUNZITE = registryKey("kunzite", ModItems.KUNZITE, 0xffafc2, 0.63F);
    public static final ResourceKey<TrimMaterial> MOONSTONE = registryKey("moonstone", ModItems.MOONSTONE, 0x9dc6be, 0.64F);
    public static final ResourceKey<TrimMaterial> RUBY = registryKey("ruby", ModItems.RUBY, 0xffd4c4, 0.66F);
    public static final ResourceKey<TrimMaterial> SAPPHIRE = registryKey("sapphire", ModItems.SAPPHIRE, 0xc6e8f2, 0.67F);
    public static final ResourceKey<TrimMaterial> SPINEL = registryKey("spinel", ModItems.SPINEL, 0xff8797, 0.68F);
    public static final ResourceKey<TrimMaterial> SUNSTONE = registryKey("sunstone", ModItems.SUNSTONE, 0xffd699, 0.69F);
    public static final ResourceKey<TrimMaterial> TANZANITE = registryKey("tanzanite", ModItems.TANZANITE, 0x8672d2, 0.71F);
    public static final ResourceKey<TrimMaterial> TOPAZ = registryKey("topaz", ModItems.TOPAZ, 0xfffeb3, 0.72F);

    private static ResourceKey<TrimMaterial> registryKey(String name, Holder<Item> ingredient, int color, float modelIndex) {
        return registryKey(name, ingredient, color, modelIndex, Map.of());
    }

    private static ResourceKey<TrimMaterial> registryKey(String name, Holder<Item> ingredient, int color, float modelIndex, Map<Holder<ArmorMaterial>, String> overrideArmorMaterials) {
        ResourceKey<TrimMaterial> key = ResourceKey.create(Registries.TRIM_MATERIAL, CalamosMod.asResource(name));
        MATERIALS.put(key, new TrimMaterial(name, ingredient, modelIndex, overrideArmorMaterials, Component.translatable(key.location().toLanguageKey("trim_material")).withColor(color)));
        return key;
    }
}
