package uwu.lopyluna.calamos.elements.items.equipment.hook;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import uwu.lopyluna.calamos.elements.ModDataComponents;
import uwu.lopyluna.calamos.elements.entity.hook.AbstractHookEntity;

import java.util.List;

public abstract class AbstractHookItem extends Item implements ICurioItem {

    public AbstractHookItem(Properties pProperties) {
        super(pProperties.stacksTo(1));
    }

    public AbstractHookItem(Rarity rarity) {
        this(new Properties().rarity(rarity));
    }

    public abstract int getAmount();
    public abstract float getRange();

    public boolean fastThrow() {
        return false;
    }

    public abstract AbstractHookEntity getHook(ItemStack itemStack, AbstractHookItem item, Player player, Level level);

    public boolean canHook(ServerLevel level, ItemStack itemStack) {
        if (itemStack.has(ModDataComponents.HOOKS)) {
            Hooks oldHooks = itemStack.getOrDefault(ModDataComponents.HOOKS, new Hooks(List.of()));
            List<Integer> ids = oldHooks.ids();
            ids.removeIf(id -> getHook(id, level) == null);
            itemStack.set(ModDataComponents.HOOKS, new Hooks(ids));
            if (fastThrow()) return ids.size() <= getAmount();
            if (ids.isEmpty()) return true;
            return ids.stream().allMatch(id -> {
                AbstractHookEntity hookEntity = getHook(id, level);
                return hookEntity == null || hookEntity.getState() == AbstractHookEntity.HookState.HOOKED;
            });
        } else {
            itemStack.set(ModDataComponents.HOOKS, new Hooks(List.of()));
            return true;
        }
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return canEquip(slotContext, stack);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (ItemStack.isSameItem(newStack, stack)) return;
        if (slotContext.entity().level() instanceof ServerLevel level) {
            Hooks hooks = stack.getOrDefault(ModDataComponents.HOOKS, Hooks.EMPTY);
            if (hooks.hasHooks()) removeAll(stack, level);
        }
    }

    public static void removeAll(ItemStack stack, ServerLevel level) {
        Hooks oldHooks = stack.getOrDefault(ModDataComponents.HOOKS, Hooks.EMPTY);
        List<Integer> ids = oldHooks.ids();
        ids.removeIf(id -> {
            AbstractHookEntity hook = getHook(id, level);
            if (hook != null) hook.discard();
            return true;
        });
        stack.set(ModDataComponents.HOOKS, new Hooks(ids));
    }

    public static void add(ItemStack stack, int id) {
        Hooks oldHooks = stack.getOrDefault(ModDataComponents.HOOKS, Hooks.EMPTY);
        List<Integer> ids = oldHooks.ids();
        ids.add(id);
        stack.set(ModDataComponents.HOOKS, new Hooks(ids));
    }

    @Nullable
    public static AbstractHookEntity getHook(int id, ServerLevel level) {
        return level.getEntity(id) instanceof AbstractHookEntity hook ? hook : null;
    }

    public record Hooks(List<Integer> ids) {
        public static final Hooks EMPTY = new Hooks(List.of());

        public static final Codec<Hooks> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
                Codec.INT.listOf().fieldOf("ids").forGetter(Hooks::ids)).apply(instance, Hooks::new));

        public static final StreamCodec<ByteBuf, Hooks> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.INT.apply(ByteBufCodecs.list()), Hooks::ids,
                Hooks::new
        );

        public boolean hasHooks() {
            return !ids.isEmpty();
        }
    }
}
