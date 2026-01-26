package uwu.lopyluna.calamos.core.items.equipment.wings;

import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import uwu.lopyluna.calamos.elements.CalamosKeys;
import uwu.lopyluna.calamos.elements.ModAttachmentTypes;
import uwu.lopyluna.calamos.elements.ModAttributes;

public class WingsHandler {

    public static void playerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        var level = player.level();
        if (player.getAttributeValue(ModAttributes.MAX_FLIGHT) == 0 && !player.hasData(ModAttachmentTypes.WINGS)) {
            return;
        }
        var data = player.getData(ModAttachmentTypes.WINGS);
        if (!level.isClientSide) {
            data.tickData(player);
        }
        wingFunction(player, data);
    }

    private static void wingFunction(Player player, WingsData wings) {
        boolean onGround = player.onGround();
        if (!onGround) {
            if (wings.isDepleted()) {
                if (CalamosKeys.boost.isPressed()) {
                    glidingMovement(player);
                }
            } else {
                if (CalamosKeys.boost.isPressed()) {
                    wings.reduceFlight(0.1);
                    boostUpMovement(player);
                } else if (!player.isCrouching()) {
                    wings.reduceFlight(0.025f);
                    glidingMovement(player);
                } else {
                    boostHoriztonalMovement(player);
                }
            }
            player.resetFallDistance();
        }
        player.setData(ModAttachmentTypes.WINGS, wings);
    }

    private static void boostUpMovement(Player player) {
        Vec3 vec3 = player.getDeltaMovement();
        player.setDeltaMovement(vec3.add(0, 1.5 * (vec3.y <= 0.3F ? vec3.y <= -0.1F ? vec3.y <= -0.2F ? vec3.y <= -0.3F ? vec3.y <= -0.5F ? 0.5 : 0.25 : 0.2 : 0.15 : 0.1 : 0), 0));
        boostHoriztonalMovement(player);
    }

    public static void glidingMovement(Player player) {
        Vec3 vec3 = player.getDeltaMovement();
        player.setDeltaMovement(divide(vec3,1, vec3.y <= -0.25F ? 1.5F : 1, 1));
        boostHoriztonalMovement(player);
    }

    public static void boostHoriztonalMovement(Player player) {
        Vec3 vec3 = player.getDeltaMovement();
        float flightSpeed = (float) player.getAttributeValue(ModAttributes.FLIGHT_SPEED);
        player.move(MoverType.SELF, vec3.multiply(flightSpeed, 0, flightSpeed));
    }

    public static Vec3 divide(Vec3 vec3, double pFactorX, double pFactorY, double pFactorZ) {return new Vec3(vec3.x / pFactorX, vec3.y / pFactorY, vec3.z / pFactorZ);}

}
