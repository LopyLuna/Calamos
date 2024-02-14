package uwu.lopyluna.calamos.elements;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.utilities.ModUtils;

public final class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BE_TYPES = ModUtils.createRegister(Registries.BLOCK_ENTITY_TYPE);
}
