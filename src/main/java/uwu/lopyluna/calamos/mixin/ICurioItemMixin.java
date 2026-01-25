package uwu.lopyluna.calamos.mixin;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import uwu.lopyluna.calamos.elements.ModDataComponents;
import uwu.lopyluna.calamos.elements.items.equipment.modifier.ItemModifier;

@Mixin(ICurioItem.class)
public interface ICurioItemMixin {

    @Inject(method = "getAttributeModifiers(Ltop/theillusivec4/curios/api/SlotContext;Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/world/item/ItemStack;)Lcom/google/common/collect/Multimap;", at = @At("HEAD"), cancellable = true)
    private void calamos$modifierAttributes(SlotContext slotContext, ResourceLocation id, ItemStack stack, CallbackInfoReturnable<Multimap<Holder<Attribute>, AttributeModifier>> cir) {
        if (stack.has(ModDataComponents.MODIFIER)) {
            ItemModifier modifier = stack.get(ModDataComponents.MODIFIER);
            if (modifier != null && modifier.supports(stack)) {
                var existingAttributes = cir.getReturnValue();
                if (existingAttributes == null) existingAttributes = LinkedHashMultimap.create();
                for (var entry : modifier.modifier().value().attributeModifiers()) {
                    existingAttributes.put(entry.attribute(), entry.modifier());
                }
                cir.setReturnValue(existingAttributes);
            }
        }
    }
}
