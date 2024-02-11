package uwu.lopyluna.calamos.datagen;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import uwu.lopyluna.calamos.elements.ModBlocks;
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
        dropSelf(ModBlocks.METEORITE_BRICKS.get());
        dropSelf(ModBlocks.GILDED_METEORITE_BRICKS.get());
        dropSelf(ModBlocks.METEORITE_TILES.get());
        dropSelf(ModBlocks.LARGE_METEORITE_TILE.get());
        dropSelf(ModBlocks.POLISHED_METEORITE.get());
        dropSelf(ModBlocks.CUT_METEORITE.get());
        dropSelf(ModBlocks.COBBLED_METEORITE.get());
        dropSelf(ModBlocks.SMOOTH_METEORITE.get());
        dropOther(ModBlocks.METEORITE.get(), ModBlocks.COBBLED_METEORITE.get());
        createOreDrop(ModBlocks.METEORITE_ORE.get(), ModItems.RAW_METEORITE.get());
        createCopperOreDrops(ModBlocks.COPPER_ORE.get());
    }
    
    protected void dropNamedContainer(Block block) {
        add(block, this::createNameableBlockEntityTable);
    }
    
    @Override
    protected void add(Block block, LootTable.Builder builder) {
        this.generatedLootTables.add(block);
        this.map.put(block.getLootTable(), builder);
    }
    
    
    
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return generatedLootTables;
    }
}
