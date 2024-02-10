package uwu.lopyluna.calamos.datagen;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import uwu.lopyluna.calamos.CalamosMod;

public class ModTags {
    public static TagKey<Item> forgeItemTag(String path) {
        return TagKey.create(BuiltInRegistries.ITEM.key(), new ResourceLocation("forge", path));
    }
    public static TagKey<Item> mcItemTag(String path) {
        return TagKey.create(BuiltInRegistries.ITEM.key(), new ResourceLocation("minecraft", path));
    }
    public static TagKey<Item> modItemTag(String path) {
        return TagKey.create(BuiltInRegistries.ITEM.key(), new ResourceLocation(CalamosMod.MODID, path));
    }
}
