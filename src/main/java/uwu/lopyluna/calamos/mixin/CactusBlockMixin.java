package uwu.lopyluna.calamos.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings({"all"})
@Mixin(net.minecraft.world.level.block.CactusBlock.class)
public class CactusBlockMixin {
//Why not make this :3

    @Final
    int hurtTime = 0;

    @Inject(method = "Lnet/minecraft/world/level/block/CactusBlock;entityInside(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/Entity;)V", at = @At("HEAD"), cancellable = true)
    public void calamos$entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity, CallbackInfo ci) {
        if (pEntity.isAlive() && hurtTime > 0) {
            --hurtTime;
        } else if (pEntity.isAlive() && hurtTime == 0) {
            pEntity.playSound(SoundEvents.THORNS_HIT);
            hurtTime = 15;
        }
    }

}
