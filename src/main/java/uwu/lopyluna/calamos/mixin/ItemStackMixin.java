package uwu.lopyluna.calamos.mixin;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uwu.lopyluna.calamos.datagen.ModTags;
import uwu.lopyluna.calamos.elements.ModDataComponents;
import uwu.lopyluna.calamos.core.modifier.ItemModifier;

import java.util.function.BiConsumer;

@Mixin(ItemStack.class)
public class ItemStackMixin {

    @Inject(method = "getHoverName", at = @At("RETURN"), cancellable = true)
    public void itemName(CallbackInfoReturnable<Component> cir) {
        ItemStack ths = (ItemStack) (Object) this;
        if (ths.has(ModDataComponents.MODIFIER)) {
            cir.setReturnValue(ths.get(ModDataComponents.MODIFIER).modifierName(cir.getReturnValue()));
        }
    }

    @Inject(method = "inventoryTick(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/Entity;IZ)V", at = @At("HEAD"))
    public void invTick(Level level, Entity entity, int inventorySlot, boolean isCurrentItem, CallbackInfo ci) {
        ItemStack ths = (ItemStack) (Object) this;
        if (ths.has(ModDataComponents.MODIFIER)) {
            ItemModifier modifier = ths.get(ModDataComponents.MODIFIER);
            if (modifier != null && !modifier.supports(ths)) {
                ths.remove(ModDataComponents.MODIFIER);
            }
        }
    }

    @Inject(method = "forEachModifier(Lnet/minecraft/world/entity/EquipmentSlot;Ljava/util/function/BiConsumer;)V", at = @At("HEAD"))
    public void modifiedAttributes(EquipmentSlot equipmentSLot, BiConsumer<Holder<Attribute>, AttributeModifier> action, CallbackInfo ci) {
        calamos$handleForEach(EquipmentSlotGroup.bySlot(equipmentSLot), action);
    }

    @Inject(method = "forEachModifier(Lnet/minecraft/world/entity/EquipmentSlotGroup;Ljava/util/function/BiConsumer;)V", at = @At("HEAD"))
    public void modifiedAttributes(EquipmentSlotGroup slotGroup, BiConsumer<Holder<Attribute>, AttributeModifier> action, CallbackInfo ci) {
        calamos$handleForEach(slotGroup, action);
    }



    @Unique
    private void calamos$handleForEach(EquipmentSlotGroup equipmentSlot, BiConsumer<Holder<Attribute>, AttributeModifier> action) {
        ItemStack ths = (ItemStack) (Object) this;
        if (equipmentSlot == EquipmentSlotGroup.MAINHAND) {
            if (ths.has(ModDataComponents.MODIFIER)) {
                ItemModifier modifier = ths.get(ModDataComponents.MODIFIER);
                if (modifier != null && !ths.is(ModTags.modItemTag("modifiable/accessory/universal")) && modifier.supports(ths)) {
                    var attributes = ItemAttributeModifiers.builder();
                    for (var entry : modifier.modifier().value().attributeModifiers()) {
                        attributes.add(entry.attribute(), entry.modifier(), EquipmentSlotGroup.MAINHAND);
                    }
                    attributes.build().forEach(equipmentSlot, action);
                }
            }
        }
    }
}
