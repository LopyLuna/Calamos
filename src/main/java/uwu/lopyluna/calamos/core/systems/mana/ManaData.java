package uwu.lopyluna.calamos.core.systems.mana;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import uwu.lopyluna.calamos.elements.ModAttachmentTypes;
import uwu.lopyluna.calamos.elements.ModAttributes;

import java.util.Optional;

public class ManaData {

    public static Codec<ManaData> CODEC = RecordCodecBuilder.create(obj -> obj.group(
            Codec.DOUBLE.optionalFieldOf("mana", 0d).forGetter(ManaData::getMana),
            Codec.INT.optionalFieldOf("manaProgress", 0).forGetter(ManaData::getManaCooldown),
            Codec.FLOAT.optionalFieldOf("appliedCooldownMultiplier", 1f).forGetter(ManaData::getAppliedCooldownMultiplier)
    ).apply(obj, ManaData::new));

    public static StreamCodec<ByteBuf, ManaData> STREAM_CODEC = ByteBufCodecs.fromCodec(ManaData.CODEC);

    private double mana;
    private int manaCooldown;
    private float appliedCooldownMultiplier = 1f;

    private boolean isDirty;

    public ManaData() {
    }

    public ManaData(double mana, int manaCooldown, float appliedCooldownMultiplier) {
        this.mana = mana;
        this.manaCooldown = manaCooldown;
        this.appliedCooldownMultiplier = appliedCooldownMultiplier;
    }

    public void tickData(LivingEntity living) {
        var attribute = living.getAttribute(ModAttributes.MAX_MANA);
        if (attribute != null) {
            if (getMana() < attribute.getValue()) {
                if (manaCooldown > 0) {
                    manaCooldown--;
                    tryCorrectCooldown(living);
                }
                if (manaCooldown <= 0) {
                    recoverMana(living, 1);
                }
            }
            if (getMana() > attribute.getValue()) {
                setMana(attribute.getValue());
            }
        }
        if (isDirty) {
            if (!living.level().isClientSide) {
                living.syncData(ModAttachmentTypes.MANA);
            }
            isDirty = false;
        }
    }

    public void recoverMana(LivingEntity entity, double amount) {
        var attribute = entity.getAttribute(ModAttributes.MAX_MANA);
        if (getMana() < attribute.getValue()) {
            var bonus = Optional.ofNullable(entity.getAttribute(ModAttributes.MANA_REGEN_BONUS)).map(AttributeInstance::getValue).orElse(0.0);
            var stationaryBonus = entity.getKnownMovement() == Vec3.ZERO ? (attribute.getValue()/3) : 0;
            var previousMana = mana;
            addMana(amount + stationaryBonus + bonus);
            if (mana > previousMana) {
                if (!(entity instanceof Player player) || !player.isCreative()) {

                }
            }
        }
        addCooldown(entity, 1f);
    }

    public void addMana(double added) {
        setMana(mana + added);
    }

    public void reduceMana(double removed) {
        setMana(mana - removed);
    }

    public void setMana(double soulWard) {
        this.mana = Math.max(soulWard, 0);
        isDirty = true;
    }

    public void tryCorrectCooldown(LivingEntity entity) {
        double newCooldown = getManaCooldown(entity) * appliedCooldownMultiplier;
        if (manaCooldown > newCooldown) {
            manaCooldown = Mth.floor(newCooldown);
            isDirty = true;
        }
    }

    public void addCooldown(LivingEntity entity, float multiplier) {
        double newCooldown = getManaCooldown(entity) * multiplier;
        if (manaCooldown < newCooldown) {
            manaCooldown = Mth.floor(newCooldown);
            appliedCooldownMultiplier = multiplier;
            isDirty = true;
        }
    }

    public double getMana() {
        return mana;
    }

    public int getManaCooldown() {
        return manaCooldown;
    }

    public float getAppliedCooldownMultiplier() {
        return appliedCooldownMultiplier;
    }

    public boolean isDepleted() {
        return mana <= 0;
    }

    public float getManaCooldown(LivingEntity entity) {
        float cooldownCap = (float) entity.getAttributeValue(ModAttributes.MANA_COOLDOWN_CAP);
        double maxMana = entity.getAttributeValue(ModAttributes.MAX_MANA);
        float cooldownDuration = (float) (0.7 * ((1-(mana/maxMana))*240+45));
        if (cooldownCap > 0 && cooldownDuration > cooldownCap) {
            cooldownDuration = cooldownCap;
        }
        return cooldownDuration;
    }
}
