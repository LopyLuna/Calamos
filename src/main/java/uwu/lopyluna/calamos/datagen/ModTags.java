package uwu.lopyluna.calamos.datagen;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import uwu.lopyluna.calamos.CalamosMod;

public class ModTags {
    public static TagKey<Item> forgeItemTag(String path) {
        return ItemTags.create(new ResourceLocation("forge", path));
    }
    public static TagKey<Block> forgeBlockTag(String path) {
        return BlockTags.create(new ResourceLocation("forge", path));
    }
    public static TagKey<Item> mcItemTag(String path) {
        return ItemTags.create(new ResourceLocation("minecraft", path));
    }
    public static TagKey<Block> mcBlockTag(String path) {
        return BlockTags.create(new ResourceLocation("minecraft", path));
    }
    public static TagKey<Item> modItemTag(String path) {
        return ItemTags.create(new ResourceLocation(CalamosMod.MODID, path));
    }
    public static TagKey<Block> modBlockTag(String path) {
        return BlockTags.create(new ResourceLocation(CalamosMod.MODID, path));
    }
}
