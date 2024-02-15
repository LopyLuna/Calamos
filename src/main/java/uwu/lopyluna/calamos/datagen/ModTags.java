package uwu.lopyluna.calamos.datagen;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import uwu.lopyluna.calamos.CalamosMod;

public class ModTags {
    public static TagKey<Item> forgeItemTag(String path) {
        return ItemTags.create(new ResourceLocation("forge", path));
    }
    public static TagKey<Item> modItemTag(String path) {
        return ItemTags.create(new ResourceLocation(CalamosMod.MODID, path));
    }
}
