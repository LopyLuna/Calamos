package uwu.lopyluna.calamos.datagen;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import uwu.lopyluna.calamos.elements.ModBlocks;
import uwu.lopyluna.calamos.elements.ModDecorativeBlocks;
import uwu.lopyluna.calamos.elements.ModItems;

import java.util.HashSet;
import java.util.Set;

public class ModBlockLootProvider extends BlockLootSubProvider {
    private final Set<Block> generatedLootTables = new HashSet<>();
    
    public ModBlockLootProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }
    @Override
    protected void generate() {
        dropSelf(ModBlocks.GARNET_BLOCK.get(),
                ModBlocks.JADE_BLOCK.get(),
                ModBlocks.KUNZITE_BLOCK.get(),
                ModBlocks.MOONSTONE_BLOCK.get(),
                ModBlocks.OPAL_BLOCK.get(),
                ModBlocks.RUBY_BLOCK.get(),
                ModBlocks.SAPPHIRE_BLOCK.get(),
                ModBlocks.SPINEL_BLOCK.get(),
                ModBlocks.SUNSTONE_BLOCK.get(),
                ModBlocks.TANZANITE_BLOCK.get(),
                ModBlocks.TOPAZ_BLOCK.get()
                );
        createOreDrop(ModBlocks.GARNET_ORE.get(), ModItems.GARNET.get());
        createOreDrop(ModBlocks.JADE_ORE.get(), ModItems.JADE.get());
        createOreDrop(ModBlocks.KUNZITE_ORE.get(), ModItems.KUNZITE.get());
        createOreDrop(ModBlocks.MOONSTONE_ORE.get(), ModItems.MOONSTONE.get());
        createOreDrop(ModBlocks.OPAL_ORE.get(), ModItems.OPAL.get());
        createOreDrop(ModBlocks.RUBY_ORE.get(), ModItems.RUBY.get());
        createOreDrop(ModBlocks.SAPPHIRE_ORE.get(), ModItems.SAPPHIRE.get());
        createOreDrop(ModBlocks.SPINEL_ORE.get(), ModItems.SPINEL.get());
        createOreDrop(ModBlocks.SUNSTONE_ORE.get(), ModItems.SUNSTONE.get());
        createOreDrop(ModBlocks.TANZANITE_ORE.get(), ModItems.TANZANITE.get());
        createOreDrop(ModBlocks.TOPAZ_ORE.get(), ModItems.TOPAZ.get());
        
        dropSelf(ModBlocks.UMBRALITE.get(),
                ModBlocks.PURRASITE.get()
        );
        dropSelf(
                ModBlocks.URANIUM_BLOCK.get(),
                ModBlocks.RAW_URANIUM_BLOCK.get(),
                ModBlocks.PALLADIUM_BLOCK.get(),
                ModBlocks.RAW_PALLADIUM_BLOCK.get()
        );
        createOreDrop(ModBlocks.URANIUM_ORE.get(), ModItems.RAW_URANIUM.get());
        createOreDrop(ModBlocks.PALLADIUM_ORE.get(), ModItems.RAW_PALLADIUM.get());
        
        dropSelf(ModDecorativeBlocks.METEORITE_BRICKS.get());
        dropSelf(ModDecorativeBlocks.GILDED_METEORITE_BRICKS.get());
        dropSelf(ModDecorativeBlocks.METEORITE_TILES.get());
        dropSelf(ModDecorativeBlocks.LARGE_METEORITE_TILE.get());
        dropSelf(ModDecorativeBlocks.POLISHED_METEORITE.get());
        dropSelf(ModDecorativeBlocks.CUT_METEORITE.get());
        dropSelf(ModDecorativeBlocks.COBBLED_METEORITE.get());
        dropSelf(ModDecorativeBlocks.SMOOTH_METEORITE.get());
        dropSelf(ModDecorativeBlocks.METEORITE_WALL.get());
        dropSelf(ModDecorativeBlocks.SMOOTH_METEORITE_WALL.get());
        dropSelf(ModDecorativeBlocks.POLISHED_METEORITE_WALL.get());
        dropSelf(ModDecorativeBlocks.METEORITE_STAIRS.get());
        dropSelf(ModDecorativeBlocks.SMOOTH_METEORITE_STAIRS.get());
        dropSelf(ModDecorativeBlocks.POLISHED_METEORITE_STAIRS.get());
        dropSelf(ModDecorativeBlocks.METEORITE_SLAB.get());
        dropSelf(ModDecorativeBlocks.SMOOTH_METEORITE_SLAB.get());
        dropSelf(ModDecorativeBlocks.POLISHED_METEORITE_SLAB.get());
        dropOther(ModDecorativeBlocks.METEORITE.get(), ModDecorativeBlocks.COBBLED_METEORITE.get());
        createOreDrop(ModBlocks.METEORITE_ORE.get(), ModItems.RAW_METEORITE.get());
        createCopperOreDrops(ModBlocks.COPPER_ORE.get());
        
        dropSelf(ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE.get());
        dropSelf(ModDecorativeBlocks.SMOOTH_SOUL_SANDSTONE.get());
        dropSelf(ModDecorativeBlocks.CUT_SOUL_SANDSTONE.get());
        dropSelf(ModDecorativeBlocks.CHISELED_SOUL_SANDSTONE.get());
        dropSelf(ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE_STAIRS.get());
        dropSelf(ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE_SLAB.get());
        dropSelf(ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE_WALL.get());
        createSingleItemTableWithSilkTouch(ModDecorativeBlocks.COBBLED_SOUL_SANDSTONE.get(), ModDecorativeBlocks.SOUL_SANDSTONE.get());
        
        dropSelf(ModDecorativeBlocks.COBBLED_SANDSTONE.get());
        dropSelf(ModDecorativeBlocks.COBBLED_SANDSTONE_STAIRS.get());
        dropSelf(ModDecorativeBlocks.COBBLED_SANDSTONE_SLAB.get());
        dropSelf(ModDecorativeBlocks.COBBLED_SANDSTONE_WALL.get());
        
        dropSelf(
                ModBlocks.UNSTABLE_ULTIMITA_TNT.get(),
                ModBlocks.ULTIMITA_TNT.get(),
                ModBlocks.METAL_GRINDER.get(),
                ModBlocks.SAWMILL.get()
        );
        
        dropSelf(ModDecorativeBlocks.LARGE_ACACIA_PLANKS.get(),
                ModDecorativeBlocks.LARGE_ACACIA_SLAB.get(),
                ModDecorativeBlocks.LARGE_ACACIA_STAIRS.get(),
                ModDecorativeBlocks.LARGE_BIRCH_PLANKS.get(),
                ModDecorativeBlocks.LARGE_BIRCH_SLAB.get(),
                ModDecorativeBlocks.LARGE_BIRCH_STAIRS.get(),
                ModDecorativeBlocks.LARGE_BAMBOO_PLANKS.get(),
                ModDecorativeBlocks.LARGE_BAMBOO_SLAB.get(),
                ModDecorativeBlocks.LARGE_BAMBOO_STAIRS.get(),
                ModDecorativeBlocks.LARGE_CRIMSON_PLANKS.get(),
                ModDecorativeBlocks.LARGE_CRIMSON_SLAB.get(),
                ModDecorativeBlocks.LARGE_CRIMSON_STAIRS.get(),
                ModDecorativeBlocks.LARGE_DARK_OAK_PLANKS.get(),
                ModDecorativeBlocks.LARGE_DARK_OAK_SLAB.get(),
                ModDecorativeBlocks.LARGE_DARK_OAK_STAIRS.get(),
                ModDecorativeBlocks.LARGE_JUNGLE_PLANKS.get(),
                ModDecorativeBlocks.LARGE_JUNGLE_SLAB.get(),
                ModDecorativeBlocks.LARGE_JUNGLE_STAIRS.get(),
                ModDecorativeBlocks.LARGE_OAK_PLANKS.get(),
                ModDecorativeBlocks.LARGE_OAK_SLAB.get(),
                ModDecorativeBlocks.LARGE_OAK_STAIRS.get(),
                ModDecorativeBlocks.LARGE_SPRUCE_PLANKS.get(),
                ModDecorativeBlocks.LARGE_SPRUCE_SLAB.get(),
                ModDecorativeBlocks.LARGE_SPRUCE_STAIRS.get(),
                ModDecorativeBlocks.LARGE_WARPED_PLANKS.get(),
                ModDecorativeBlocks.LARGE_WARPED_SLAB.get(),
                ModDecorativeBlocks.LARGE_WARPED_STAIRS.get()
        );
        
        
        dropSelf(ModDecorativeBlocks.OTHERWORLD_OAK_LOG.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_WOOD.get(),
                ModDecorativeBlocks.STRIPPED_OTHERWORLD_OAK_LOG.get(),
                ModDecorativeBlocks.STRIPPED_OTHERWORLD_OAK_WOOD.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_PLANKS.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_SLAB.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_STAIRS.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_FENCE.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_FENCE_GATE.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_DOOR.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_TRAPDOOR.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_BUTTON.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_PRESSURE_PLATE.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_SIGN.get(),
                ModDecorativeBlocks.OTHERWORLD_OAK_WALL_SIGN.get()
                );
        dropSelf(ModDecorativeBlocks.TWILIGHT_LOG.get(),
                ModDecorativeBlocks.TWILIGHT_WOOD.get(),
                ModDecorativeBlocks.STRIPPED_TWILIGHT_LOG.get(),
                ModDecorativeBlocks.STRIPPED_TWILIGHT_WOOD.get(),
                ModDecorativeBlocks.TWILIGHT_PLANKS.get(),
                ModDecorativeBlocks.TWILIGHT_SLAB.get(),
                ModDecorativeBlocks.TWILIGHT_STAIRS.get(),
                ModDecorativeBlocks.TWILIGHT_FENCE.get(),
                ModDecorativeBlocks.TWILIGHT_FENCE_GATE.get(),
                ModDecorativeBlocks.TWILIGHT_DOOR.get(),
                ModDecorativeBlocks.TWILIGHT_TRAPDOOR.get(),
                ModDecorativeBlocks.TWILIGHT_BUTTON.get(),
                ModDecorativeBlocks.TWILIGHT_PRESSURE_PLATE.get(),
                ModDecorativeBlocks.TWILIGHT_SIGN.get(),
                ModDecorativeBlocks.TWILIGHT_WALL_SIGN.get()
        );
        dropSelf(ModDecorativeBlocks.HOLLOW_LOG.get(),
                ModDecorativeBlocks.HOLLOW_WOOD.get(),
                ModDecorativeBlocks.STRIPPED_HOLLOW_LOG.get(),
                ModDecorativeBlocks.STRIPPED_HOLLOW_WOOD.get(),
                ModDecorativeBlocks.HOLLOW_PLANKS.get(),
                ModDecorativeBlocks.HOLLOW_SLAB.get(),
                ModDecorativeBlocks.HOLLOW_STAIRS.get(),
                ModDecorativeBlocks.HOLLOW_FENCE.get(),
                ModDecorativeBlocks.HOLLOW_FENCE_GATE.get(),
                ModDecorativeBlocks.HOLLOW_DOOR.get(),
                ModDecorativeBlocks.HOLLOW_TRAPDOOR.get(),
                ModDecorativeBlocks.HOLLOW_BUTTON.get(),
                ModDecorativeBlocks.HOLLOW_PRESSURE_PLATE.get(),
                ModDecorativeBlocks.HOLLOW_SIGN.get(),
                ModDecorativeBlocks.HOLLOW_WALL_SIGN.get()
        );
        
        dropSelf(ModDecorativeBlocks.MUD_TILES.get(),
                ModDecorativeBlocks.ENDSTONE_TILES.get(),
                ModDecorativeBlocks.LARGE_BRICKS.get(),
                ModDecorativeBlocks.POLISHED_NETHERRACK.get(),
                ModDecorativeBlocks.NETHERRACK_BRICKS.get(),
                ModDecorativeBlocks.SMOOTH_NETHERRACK.get(),
                ModDecorativeBlocks.OBSIDIAN_BRICKS.get(),
                ModDecorativeBlocks.CRYING_OBSIDIAN_BRICKS.get(),
                ModDecorativeBlocks.SMOOTH_DEEPSLATE.get(),
                ModDecorativeBlocks.STONE_TILES.get(),
                ModDecorativeBlocks.POLISHED_STONE.get(),
                ModDecorativeBlocks.SMOOTH_COBBLESTONE.get(),
                ModDecorativeBlocks.COBBLESTONE_BRICK_WALL.get(),
                ModDecorativeBlocks.COBBLESTONE_BRICKS.get(),
                ModDecorativeBlocks.COBBLESTONE_BRICK_SLAB.get(),
                ModDecorativeBlocks.COBBLESTONE_BRICK_STAIRS.get(),
                ModDecorativeBlocks.CHISELED_RED_NETHER_BRICKS.get(),
                ModDecorativeBlocks.CHISELED_BONE.get(),
                ModDecorativeBlocks.BONE_BRICKS.get(),
                ModDecorativeBlocks.BLACKSTONE_TILES.get(),
                ModDecorativeBlocks.CUT_BASALT.get(),
                ModDecorativeBlocks.BASALT_BRICKS.get(),
                ModDecorativeBlocks.POLISHED_AMETHYST.get(),
                ModDecorativeBlocks.AMETHYST_BRICKS.get(),
                ModDecorativeBlocks.CUT_AMETHYST.get()
                );
    }
    
    protected void dropNamedContainer(Block block) {
        add(block, this::createNameableBlockEntityTable);
    }
    
    protected void dropSelf(Block... block) {
        for (Block b : block) {
                dropSelf(b);
        }
    }
    
    @Override
    protected void add(Block block, LootTable.Builder builder) {
        this.generatedLootTables.add(block);
        this.map.put(block.getLootTable(), builder);
    }
    protected void otherWhenSilkTouch(Block pBlock, Block pOther) {
        this.add(pBlock, createSilkTouchOnlyTable(pOther));
    }
    
    
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return generatedLootTables;
    }
}
