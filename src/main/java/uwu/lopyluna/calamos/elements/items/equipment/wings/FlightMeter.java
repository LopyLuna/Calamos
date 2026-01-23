package uwu.lopyluna.calamos.elements.items.equipment.wings;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import uwu.lopyluna.calamos.datagen.ModEnchantmentProvider;
import uwu.lopyluna.calamos.elements.CalamosKeys;
import uwu.lopyluna.calamos.utilities.ModUtils;

public class FlightMeter {

    private float replenishRate = 2.0f;

    private boolean isOnGround(Player player) {
        return player.onGround();
    }
    private boolean canBoostUp(Player player) {
        return getFlightMeter(player) > 0.0f;
    }

    public void wingFunction(Player player, ItemStack stack, Level level) {
        int savingGrace = ModUtils.getEnchantLevel(stack, level, ModEnchantmentProvider.SAVING_GRACE);
        boolean hasSavingGrace = savingGrace > 0;
        BlockPos pos = player.blockPosition();
        boolean hasMaxFlightMeter = getFlightMeter(player) >= getMaxFlightMeter(player);
        if (!isOnGround(player)) {
            player.resetFallDistance();
        }
        if (!isOnGround(player) && CalamosKeys.boost.isPressed() && canBoostUp(player)) {
            decreaseFlightMeter(player,0.1f);
            boostUpMovement(player, stack);
        }
        if (isOnGround(player) && !hasMaxFlightMeter) {
            replenishFlightMeter(player, getReplenishRate(stack, level));
        }
        if (!isOnGround(player) && !level.getBlockState(pos.below(2)).isAir() && hasSavingGrace && player.fallDistance > 3.0f) {
            decreaseFlightMeter(player, 4.0f - savingGrace);
        }
        if (!canBoostUp(player) && !isOnGround(player) && CalamosKeys.boost.isPressed()) {
            glidingMovement(player, stack);
        } else if (canBoostUp(player) && !isOnGround(player) && !CalamosKeys.boost.isPressed() && !player.isCrouching()) {
            decreaseFlightMeter(player,0.025f);
            glidingMovement(player, stack);
        } else if (canBoostUp(player) && !isOnGround(player) && !CalamosKeys.boost.isPressed() && player.isCrouching()) {
            boostHoriztonalMovement(player, stack);
        }

    }


    public float getReplenishRate(ItemStack stack, Level level) {
        int flightCharge = ModUtils.getEnchantLevel(stack, level, ModEnchantmentProvider.FLIGHT_CHARGE);
        boolean hasFlightCharge = flightCharge > 0;
        if (hasFlightCharge)
            return switch (flightCharge) {
                case 1 -> replenishRate * ((float) 25 / 100);
                case 2 -> replenishRate * ((float) 50 / 100);
                case 3 -> replenishRate * ((float) 75 / 100);
                default -> replenishRate;
            };
        return replenishRate;
    }

    public void boostUpMovement(Player player, ItemStack stack) {
        Vec3 vec3 = player.getDeltaMovement();
        player.setDeltaMovement(vec3.add(0, 1.5 * (vec3.y <= 0.3F ? vec3.y <= -0.1F ? vec3.y <= -0.2F ? vec3.y <= -0.3F ? vec3.y <= -0.5F ? 0.5 : 0.25 : 0.2 : 0.15 : 0.1 : 0), 0));
        boostHoriztonalMovement(player, stack);
    }

    public void glidingMovement(Player player, ItemStack stack) {
        Vec3 vec3 = player.getDeltaMovement();
        player.setDeltaMovement(divide(vec3,1, vec3.y <= -0.25F ? 1.5F : 1, 1));
        boostHoriztonalMovement(player, stack);
    }

    public void boostHoriztonalMovement(Player player, ItemStack stack) {
        Vec3 vec3 = player.getDeltaMovement();
        player.move(MoverType.SELF, vec3.multiply(defaultMovementRate(stack, player.level()), 0, defaultMovementRate(stack, player.level())));
    }

    public float defaultMovementRate(ItemStack stack, Level level) {
        int fastFlight = ModUtils.getEnchantLevel(stack, level, ModEnchantmentProvider.FAST_FLIGHT);
        float defaultMovementRate = 1.1F;
        boolean hasFastFlight = fastFlight > 0;
        if (hasFastFlight)
            return switch (fastFlight) {
                case 1 -> defaultMovementRate * 1.1F;
                case 2 -> defaultMovementRate * 1.2F;
                case 3 -> defaultMovementRate * 1.3F;
                default -> defaultMovementRate;
            };
        return defaultMovementRate;
    }

    public void decreaseFlightMeter(Player player, float amount) {
        if (getFlightMeter(player) > 0.0f)
            setFlightMeter(player, getFlightMeter(player) - amount);
    }
    public void replenishFlightMeter(Player player, float amount) {
        if (getFlightMeter(player) < 30.0f)
            setFlightMeter(player, getFlightMeter(player) + amount);
    }

    public void setFlightMeter(Player player, float amount) {
        CompoundTag tag = player.getPersistentData();
        if (!tag.contains("flight_meter"))
            tag.putFloat("flight_meter", getMaxFlightMeter(player));
        if (tag.getFloat("flight_meter") > getMaxFlightMeter(player))
            tag.putFloat("flight_meter", getMaxFlightMeter(player));
        tag.putFloat("flight_meter", amount);
    }
    public static float getFlightMeter(Player player) {
        CompoundTag tag = player.getPersistentData();
        if (!tag.contains("flight_meter"))
            tag.putFloat("flight_meter", getMaxFlightMeter(player));
        return tag.getFloat("flight_meter");
    }
    public static float getMaxFlightMeter(Player player) {
        CompoundTag tag = player.getPersistentData();
        return tag.getFloat("max_flight_meter");
    }

    public Vec3 divide(Vec3 vec3, double pFactorX, double pFactorY, double pFactorZ) {return new Vec3(vec3.x / pFactorX, vec3.y / pFactorY, vec3.z / pFactorZ);}

}
