package uwu.lopyluna.calamos.elements;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.core.blockEntity.ModHangingBlockEntity;
import uwu.lopyluna.calamos.core.blockEntity.ModSignBlockEntity;
import uwu.lopyluna.calamos.utilities.ModUtils;

public final class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BE_TYPES = ModUtils.createRegister(Registries.BLOCK_ENTITY_TYPE);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ModSignBlockEntity>> MOD_SIGN =
            BE_TYPES.register("mod_sign", () ->
                    BlockEntityType.Builder.of(ModSignBlockEntity::new,
                            ModDecorativeBlocks.OTHERWORLD_OAK_SIGN.get(), ModDecorativeBlocks.OTHERWORLD_OAK_WALL_SIGN.get(),
                            ModDecorativeBlocks.TWILIGHT_SIGN.get(), ModDecorativeBlocks.TWILIGHT_WALL_SIGN.get(),
                            ModDecorativeBlocks.HOLLOW_SIGN.get(), ModDecorativeBlocks.HOLLOW_WALL_SIGN.get()
                    ).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ModHangingBlockEntity>> MOD_HANGING_SIGN =
            BE_TYPES.register("mod_hanging_sign", () ->
                    BlockEntityType.Builder.of(ModHangingBlockEntity::new,
                            ModDecorativeBlocks.OTHERWORLD_OAK_HANGING_SIGN.get(), ModDecorativeBlocks.OTHERWORLD_OAK_WALL_HANGING_SIGN.get(),
                            ModDecorativeBlocks.TWILIGHT_HANGING_SIGN.get(), ModDecorativeBlocks.TWILIGHT_WALL_HANGING_SIGN.get(),
                            ModDecorativeBlocks.HOLLOW_HANGING_SIGN.get(), ModDecorativeBlocks.HOLLOW_WALL_HANGING_SIGN.get()
                    ).build(null));

    public static void staticInit() {}
}
