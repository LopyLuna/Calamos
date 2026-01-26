package uwu.lopyluna.calamos.core.items.equipment.wings;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import uwu.lopyluna.calamos.elements.ModAttachmentTypes;
import uwu.lopyluna.calamos.elements.ModAttributes;

public class WingsData {

    public static Codec<WingsData> CODEC = RecordCodecBuilder.create(obj -> obj.group(
            Codec.DOUBLE.optionalFieldOf("flight", 0d).forGetter(WingsData::getFlight),
            Codec.INT.optionalFieldOf("flightProgress", 0).forGetter(WingsData::getFlightCooldown),
            Codec.FLOAT.optionalFieldOf("appliedCooldownMultiplier", 1f).forGetter(WingsData::getAppliedCooldownMultiplier)
    ).apply(obj, WingsData::new));

    public static StreamCodec<ByteBuf, WingsData> STREAM_CODEC = ByteBufCodecs.fromCodec(WingsData.CODEC);

    private double flight;
    private int flightCooldown;
    private float appliedCooldownMultiplier = 1f;

    private boolean isDirty;

    public WingsData() {
    }

    public WingsData(double flight, int flightCooldown, float appliedCooldownMultiplier) {
        this.flight = flight;
        this.flightCooldown = flightCooldown;
        this.appliedCooldownMultiplier = appliedCooldownMultiplier;
    }

    public void tickData(LivingEntity living) {
        var attribute = living.getAttribute(ModAttributes.MAX_FLIGHT);
        if (attribute != null) {
            if (getFlight() < attribute.getValue() && living.onGround()) {
                if (flightCooldown > 0) {
                    flightCooldown--;
                    tryCorrectCooldown(living);
                }
                if (flightCooldown <= 0) {
                    recoverFlight(living, living.getAttributeValue(ModAttributes.FLIGHT_REGENERATION));
                }
            }
            if (getFlight() > attribute.getValue()) {
                setFlight(attribute.getValue());
            }
        }
        if (isDirty) {
            if (!living.level().isClientSide) {
                living.syncData(ModAttachmentTypes.WINGS);
            }
            isDirty = false;
        }
    }

    public void recoverFlight(LivingEntity entity, double amount) {
        var attribute = entity.getAttribute(ModAttributes.MAX_FLIGHT);
        if (getFlight() < attribute.getValue()) {
            boolean onGround = entity.onGround();
            if (onGround) addFlight(amount);
        }
        addCooldown(entity, 1f);
    }

    public void addFlight(double added) {
        setFlight(flight + added);
    }

    public void reduceFlight(double removed) {
        setFlight(flight - removed);
    }

    public void setFlight(double soulWard) {
        this.flight = Math.max(soulWard, 0);
        isDirty = true;
    }

    public void tryCorrectCooldown(LivingEntity entity) {
        double newCooldown = getFlightCooldown(entity) * appliedCooldownMultiplier;
        if (flightCooldown > newCooldown) {
            flightCooldown = Mth.floor(newCooldown);
            isDirty = true;
        }
    }

    public void addCooldown(LivingEntity entity, float multiplier) {
        double newCooldown = getFlightCooldown(entity) * multiplier;
        if (flightCooldown < newCooldown) {
            flightCooldown = Mth.floor(newCooldown);
            appliedCooldownMultiplier = multiplier;
            isDirty = true;
        }
    }

    public double getFlight() {
        return flight;
    }

    public int getFlightCooldown() {
        return flightCooldown;
    }

    public float getAppliedCooldownMultiplier() {
        return appliedCooldownMultiplier;
    }

    public boolean isDepleted() {
        return flight <= 0;
    }

    public float getFlightCooldown(LivingEntity entity) {
        double maxFlight = entity.getAttributeValue(ModAttributes.MAX_FLIGHT);
        return (float) (0.7 * ((1-(flight/maxFlight))*120+22.5));
    }
}
