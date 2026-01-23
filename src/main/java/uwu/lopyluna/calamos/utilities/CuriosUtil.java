package uwu.lopyluna.calamos.utilities;

import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;

public class CuriosUtil {

    public static List<SlotResult> findAllCurios(ICuriosItemHandler inventory, LivingEntity entity, Item item) {
        return findAllCurios(inventory, entity, stack -> stack.getItem() == item);
    }

    public static List<SlotResult> findAllCurios(ICuriosItemHandler inventory, LivingEntity entity, Predicate<ItemStack> filter) {
        List<SlotResult> result = new ArrayList<>();
        Map<String, ICurioStacksHandler> curios = inventory.getCurios();

        for (String id : curios.keySet()) {
            ICurioStacksHandler stacksHandler = curios.get(id);
            IDynamicStackHandler stackHandler = stacksHandler.getStacks();
            IDynamicStackHandler cosmeticStackHandler = stacksHandler.getCosmeticStacks();

            for (int i = 0; i < stackHandler.getSlots(); i++) {
                ItemStack stack = stackHandler.getStackInSlot(i);

                if (!stack.isEmpty() && filter.test(stack)) {
                    NonNullList<Boolean> renderStates = stacksHandler.getRenders();
                    result.add(new SlotResult(new SlotContext(id, entity, i, false,
                            renderStates.size() > i && renderStates.get(i)), stack));
                }
            }
            for (int i = 0; i < cosmeticStackHandler.getSlots(); i++) {
                ItemStack stack = cosmeticStackHandler.getStackInSlot(i);

                if (!stack.isEmpty() && filter.test(stack)) {
                    NonNullList<Boolean> renderStates = stacksHandler.getRenders();
                    result.add(new SlotResult(new SlotContext(id, entity, i, true,
                            renderStates.size() > i && renderStates.get(i)), stack));
                }
            }
        }
        return result;
    }

    public static Optional<SlotResult> getEquippedCurio(LivingEntity entity, Predicate<ItemStack> predicate) {
        return CuriosApi.getCuriosInventory(entity).flatMap((iCuriosItemHandler) -> iCuriosItemHandler.findFirstCurio(predicate));
    }

    public static Optional<SlotResult> getEquippedCurio(LivingEntity entity, Item curio) {
        return CuriosApi.getCuriosInventory(entity).flatMap((iCuriosItemHandler) -> iCuriosItemHandler.findFirstCurio((stack) -> stack.getItem() == curio));
    }

    public static boolean hasCurioEquipped(LivingEntity entity, Item curio) {
        return getEquippedCurio(entity, curio).isPresent();
    }

    public static boolean hasCurioEquipped(LivingEntity entity, Class<? extends ICurioItem> curio) {
        return getEquippedCurio(entity, stack -> curio.isInstance(stack.getItem())).isPresent();
    }

    public static Optional<ItemStack> getSlot(LivingEntity living, String id, int index) {
        AtomicReference<Optional<ItemStack>> atomic = new AtomicReference<>(Optional.empty());
        CuriosApi.getCuriosInventory(living).ifPresent(handler -> {
            Map<String, ICurioStacksHandler> curios = handler.getCurios();
            ICurioStacksHandler stacksHandler = curios.get(id);
            if (stacksHandler != null) {
                IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                if (index < stackHandler.getSlots()) {
                    ItemStack stack = stackHandler.getStackInSlot(index);
                    if (!stack.isEmpty()) atomic.set(Optional.of(stack));
                }
            }
        });
        return atomic.get();
    }
}
