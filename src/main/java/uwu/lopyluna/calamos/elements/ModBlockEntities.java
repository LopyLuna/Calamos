package uwu.lopyluna.calamos.elements;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.elements.block.SevenBySevenCraftingTableBlock;
import uwu.lopyluna.calamos.elements.blockEntities.SevenBySevenCraftingTableBE;
import uwu.lopyluna.calamos.utilities.ModUtils;

import java.util.Set;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BE_TYPES = ModUtils.createRegister(Registries.BLOCK_ENTITY_TYPE);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SevenBySevenCraftingTableBE>> SEVEN_BY_SEVEN_CRAFTING_TABLE_BE = BE_TYPES.register("seven_by_seven_crafting_table_be",
            () -> BlockEntityType.Builder.of(SevenBySevenCraftingTableBE::new, ModBlocks.SEVEN_BY_SEVEN_CRAFTING_TABLE.get()).build(null));
}
