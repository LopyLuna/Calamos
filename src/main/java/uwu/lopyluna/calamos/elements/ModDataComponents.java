package uwu.lopyluna.calamos.elements;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.core.items.equipment.hook.AbstractHookItem;
import uwu.lopyluna.calamos.core.modifier.ItemModifier;
import uwu.lopyluna.calamos.utilities.ModUtils;

public class ModDataComponents {
    public static final DeferredRegister<DataComponentType<?>> COMPONENTS = ModUtils.createRegister(Registries.DATA_COMPONENT_TYPE);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<AbstractHookItem.Hooks>> HOOKS = COMPONENTS.register("hooks", () -> DataComponentType.<AbstractHookItem.Hooks>builder().persistent(AbstractHookItem.Hooks.CODEC).networkSynchronized(AbstractHookItem.Hooks.STREAM_CODEC).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Float>> EXPLOSION_POWER = COMPONENTS.register("explosion_power", () -> DataComponentType.<Float>builder().persistent(Codec.FLOAT).networkSynchronized(ByteBufCodecs.FLOAT).build());

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ItemModifier>> MODIFIER = COMPONENTS.register("modifier", () -> DataComponentType.<ItemModifier>builder().persistent(ItemModifier.CODEC).networkSynchronized(ItemModifier.STREAM_CODEC).build());

    public static void staticInit() {}
}
