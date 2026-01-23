package uwu.lopyluna.calamos.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import uwu.lopyluna.calamos.client.model.item.CalamosArmorModel;

import java.util.function.Supplier;

public class ArmorClientItemExtensions implements IClientItemExtensions {
    private final Supplier<CalamosArmorModel> model;

    public ArmorClientItemExtensions(Supplier<CalamosArmorModel> model) {
        this.model = model;
    }

    @Override
    public @NotNull CalamosArmorModel getHumanoidArmorModel(LivingEntity entity, @NotNull ItemStack itemStack, @NotNull EquipmentSlot armorSlot, @NotNull HumanoidModel _default) {
        float pTicks = (float) (Minecraft.getInstance().getFrameTimeNs() / 20000000000L);
        float f = Mth.rotLerp(pTicks, entity.yBodyRotO, entity.yBodyRot);
        float f1 = Mth.rotLerp(pTicks, entity.yHeadRotO, entity.yHeadRot);
        float netHeadYaw = f1 - f;
        float netHeadPitch = Mth.lerp(pTicks, entity.xRotO, entity.getXRot());
        CalamosArmorModel model = this.model.get();
        model.slot = armorSlot;
        model.copyFromDefault(_default);
        model.setupAnim(entity, entity.walkAnimation.position(), entity.walkAnimation.speed(), entity.tickCount + pTicks, netHeadYaw, netHeadPitch);
        return model;
    }
}
