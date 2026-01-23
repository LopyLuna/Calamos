package uwu.lopyluna.calamos.client.handler;

import net.minecraft.client.Camera;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;
import uwu.lopyluna.calamos.elements.CalamosKeys;
import uwu.lopyluna.calamos.elements.ModDataComponents;
import uwu.lopyluna.calamos.elements.entity.hook.AbstractHookEntity;
import uwu.lopyluna.calamos.elements.items.equipment.hook.AbstractHookItem;
import uwu.lopyluna.calamos.networking.packets.C2S.HookThrowingPacket;
import uwu.lopyluna.calamos.utilities.CuriosUtil;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class HookHandler {
    private static final Vec3 LEFT_VEC = new Vec3(0.0, -0.008, 0.0);
    private static final Vec3 RIGHT_VEC = new Vec3(0.0, 0.008, 0.0);
    private static boolean aBoolean = true;

    public static void handle(Camera camera, LocalPlayer localPlayer) {
        CuriosUtil.getSlot(localPlayer, "hook", 0).ifPresent(stack -> {
            if (CalamosKeys.hook.isPressed()) {
                float rotX = camera.getXRot();
                float rotY = camera.getYRot();
                PacketDistributor.sendToServer(new HookThrowingPacket(aBoolean, rotX, rotY));
            }
            if (stack.has(ModDataComponents.HOOKS)) {
                AbstractHookItem.Hooks hooks = stack.getOrDefault(ModDataComponents.HOOKS, new AbstractHookItem.Hooks(List.of()));
                hooks.ids().forEach(id -> {
                    aBoolean = localPlayer.level().getEntity(id)==null;
                    if (localPlayer.level().getEntity(id) instanceof AbstractHookEntity hookEntity) {
                        if (hookEntity.getState() == AbstractHookEntity.HookState.HOOKED){
                            Vec3 subtract = hookEntity.position().subtract(localPlayer.position());
                            Vec3 deltaMovement = localPlayer.getDeltaMovement();
                            if (localPlayer.input.up) {
                                localPlayer.setDeltaMovement(deltaMovement.add(Vec3.directionFromRotation(localPlayer.getXRot(), localPlayer.getYRot()).scale(0.05)));
                            }
                            if (localPlayer.input.leftImpulse != 0.0) {
                                localPlayer.setDeltaMovement(deltaMovement.add(subtract.cross(localPlayer.input.left ? LEFT_VEC : RIGHT_VEC)));
                            }
                            if (localPlayer.input.jumping) {
                                localPlayer.setDeltaMovement(deltaMovement.add(0, 0.2, 0));
                            }
                            if (localPlayer.input.shiftKeyDown) {
                                localPlayer.setDeltaMovement(deltaMovement.add(0, -0.3, 0));
                            }
                            if (subtract.lengthSqr() < 1.0) {
                                Vec3 motion = localPlayer.getDeltaMovement().scale(0.05);
                                localPlayer.setDeltaMovement(motion.x, 0.0, motion.z);
                            } else {
                                Vec3 motion = subtract.normalize().scale(0.13);
                                localPlayer.setDeltaMovement(localPlayer.getDeltaMovement().scale(0.96).add(motion));
                            }
                        }
                    }
                });
            }
        });
    }
}
