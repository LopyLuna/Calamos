package uwu.lopyluna.calamos.elements;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.utilities.ModUtils;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = ModUtils.createRegister(Registries.ENTITY_TYPE);
}
