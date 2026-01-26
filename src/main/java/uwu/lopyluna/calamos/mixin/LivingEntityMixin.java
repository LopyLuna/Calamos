package uwu.lopyluna.calamos.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uwu.lopyluna.calamos.core.items.equipment.armor.CalamosArmorItem;
import uwu.lopyluna.calamos.core.items.equipment.tool.CalamosTool;

import java.util.List;
import java.util.function.Consumer;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    
    @Shadow public abstract ItemStack getMainHandItem();
    
    @Inject(method = "getCurrentSwingDuration", at = @At("HEAD"), cancellable = true)
    public void calamos$getCurrentSwingDuration(CallbackInfoReturnable<Integer> cir) {
        if (this.getMainHandItem().getItem() instanceof CalamosTool tool && tool.hasSwingPose()) {
            cir.setReturnValue(6 + tool.attackTimeAddition());
        }
        //if (this.getMainHandItem().getEnchantmentLevel(ModEnchantments.FELLING.get()) > 0) {
        //    cir.setReturnValue(8);
        //}
    }

    @WrapOperation(
            method = "actuallyHurt",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/LivingEntity;getDamageAfterMagicAbsorb(Lnet/minecraft/world/damagesource/DamageSource;F)F")
    )
    private float kAlchemy$hookDamageReduction(LivingEntity entity, DamageSource damageSource, float damage, Operation<Float> original) {
        if (!damageSource.is(DamageTypeTags.BYPASSES_ARMOR))
            damage = calamos$handleDamageReduction(entity, damageSource, damage);

        return original.call(entity, damageSource, damage);
    }

    @Unique
    private float calamos$handleDamageReduction(LivingEntity victim, DamageSource damageSource, float damage) {
        final List<Consumer<Float>> attackerCallbacks = new ObjectArrayList<>();
        final List<Consumer<Float>> victimCallbacks = new ObjectArrayList<>();

        if (damageSource.getEntity() instanceof LivingEntity attacker) {
            for (ItemStack armor : victim.getArmorSlots()) {
                if (armor.getItem() instanceof CalamosArmorItem calamosArmorItem) {
                    damage = calamosArmorItem.modifyOutgoingAttackDamage(attacker, victim, damageSource, damage);
                    attackerCallbacks.add(dmg -> calamosArmorItem.afterOutgoingAttack(attacker, victim, damageSource, dmg));
                }
            }
        }

        for (ItemStack armor : victim.getArmorSlots()) {
            if (armor.getItem() instanceof CalamosArmorItem calamosArmorItem) {
                damage = calamosArmorItem.modifyIncomingAttackDamage(victim, damageSource, damage);
                victimCallbacks.add(dmg -> calamosArmorItem.afterIncomingAttack(victim, damageSource, dmg));
            }
        }

        if (damage > 0) {
            for (Consumer<Float> consumer : attackerCallbacks) {
                consumer.accept(damage);
            }

            for (Consumer<Float> consumer : victimCallbacks) {
                consumer.accept(damage);
            }
        }

        return damage;
    }

    @Inject(
            method = "hurt",
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true
    )
    private void kAlchemy$checkCancellation(DamageSource damageSource, float damage, CallbackInfoReturnable<Boolean> callback) {
        if (calamos$checkAttackCancellation((LivingEntity)(Object)this, damageSource, damage))
            callback.setReturnValue(false);
    }

    @Unique
    private boolean calamos$checkAttackCancellation(LivingEntity victim, DamageSource damageSource, float damage) {
        for (ItemStack armor : victim.getArmorSlots()) {
            if (armor.getItem() instanceof CalamosArmorItem calamosArmorItem)
                if (!calamosArmorItem.beforeIncomingAttack(victim, damageSource, damage))
                    return true;
        }

        return false;
    }
}
