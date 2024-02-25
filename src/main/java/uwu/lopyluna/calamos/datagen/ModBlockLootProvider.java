package uwu.lopyluna.calamos.datagen;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
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
        dropSelf(ModBlocks.GARNET_BLOCK.get());
        dropSelf(ModBlocks.JADE_BLOCK.get());
        dropSelf(ModBlocks.KUNZITE_BLOCK.get());
        dropSelf(ModBlocks.MOONSTONE_BLOCK.get());
        dropSelf(ModBlocks.OPAL_BLOCK.get());
        dropSelf(ModBlocks.RUBY_BLOCK.get());
        dropSelf(ModBlocks.SAPPHIRE_BLOCK.get());
        dropSelf(ModBlocks.SPINEL_BLOCK.get());
        dropSelf(ModBlocks.SUNSTONE_BLOCK.get());
        dropSelf(ModBlocks.TANZANITE_BLOCK.get());
        dropSelf(ModBlocks.TOPAZ_BLOCK.get());
        dropSelf(ModBlocks.UMBRALITE.get());
        dropSelf(ModBlocks.PURRASITE.get());
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
    }
    
    protected void dropNamedContainer(Block block) {
        add(block, this::createNameableBlockEntityTable);
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
