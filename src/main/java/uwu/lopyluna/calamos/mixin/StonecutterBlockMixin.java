package uwu.lopyluna.calamos.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;


@SuppressWarnings({"all"})
@Mixin(net.minecraft.world.level.block.StonecutterBlock.class)
public class StonecutterBlockMixin {
//Why not make this :3

    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (pEntity instanceof LivingEntity livingEntity) {
            var prot = pLevel.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.PROTECTION);
            int i = EnchantmentHelper.getEnchantmentLevel(prot, livingEntity);
            if (!(i > 0)) {
                pEntity.hurt(pLevel.damageSources().generic(), 1.5F);
            }
        }
    }
}
