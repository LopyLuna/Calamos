package uwu.lopyluna.calamos.datagen;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import uwu.lopyluna.calamos.CalamosMod;

public class ModTags {
    // Item
    public static TagKey<Item> commonItemTag(String path) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", path));
    }
    public static TagKey<Item> mcItemTag(String path) {
        return ItemTags.create(ResourceLocation.withDefaultNamespace(path));
    }
    public static TagKey<Item> modItemTag(String path) {
        return ItemTags.create(CalamosMod.asResource(path));
    }
    public static TagKey<Item> curiosItemTag(String path) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("curios", path));
    }
    // Block
    public static TagKey<Block> commonBlockTag(String path) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", path));
    }
    public static TagKey<Block> mcBlockTag(String path) {
        return BlockTags.create(ResourceLocation.withDefaultNamespace(path));
    }
    public static TagKey<Block> modBlockTag(String path) {
        return BlockTags.create(CalamosMod.asResource(path));
    }
    // Damage
    public static TagKey<DamageType> commonDamageTag(String path) {
        return TagKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath("c", path));
    }
    public static TagKey<DamageType> mcDamageTag(String path) {
        return TagKey.create(Registries.DAMAGE_TYPE, ResourceLocation.withDefaultNamespace(path));
    }
    public static TagKey<DamageType> modDamageTag(String path) {
        return TagKey.create(Registries.DAMAGE_TYPE, CalamosMod.asResource(path));
    }
    // Entity
    public static TagKey<EntityType<?>> commonEntityTag(String path) {
        return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath("c", path));
    }
    public static TagKey<EntityType<?>> mcEntityTag(String path) {
        return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace(path));
    }
    public static TagKey<EntityType<?>> modEntityTag(String path) {
        return TagKey.create(Registries.ENTITY_TYPE, CalamosMod.asResource(path));
    }
}
