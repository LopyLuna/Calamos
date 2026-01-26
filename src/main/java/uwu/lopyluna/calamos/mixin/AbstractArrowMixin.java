package uwu.lopyluna.calamos.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uwu.lopyluna.calamos.core.modifier.ModifierHelper;

import javax.annotation.Nullable;

@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin extends Projectile {

    @Shadow
    @Nullable
    private ItemStack firedFromWeapon;

    protected AbstractArrowMixin(EntityType<? extends Projectile> p_37248_, Level p_37249_) {
        super(p_37248_, p_37249_);
    }

    @ModifyExpressionValue(method = "doKnockback(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/damagesource/DamageSource;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;modifyKnockback(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;F)F"))
    private float modifierKnockback(float original) {
        var owner = this.getOwner();
        if (owner instanceof LivingEntity living) {
            ModifierHelper modifiers = ModifierHelper.create(living);
            return modifiers.modifyProjectileKnockback(original, this.firedFromWeapon);
        }
        return original;
    }
}
