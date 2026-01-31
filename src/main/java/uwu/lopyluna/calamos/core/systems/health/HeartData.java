package uwu.lopyluna.calamos.core.systems.health;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import uwu.lopyluna.calamos.elements.ModAttachmentTypes;

public class HeartData {

    private int defaultHearts;
    private int goldenHearts;
    private int enlightenedHearts;
    private int stellatechHearts;

    private boolean isDirty;

    public HeartData() {
        this(5, 0, 0, 0); // Start with 5 default hearts
    }

    public HeartData(int defaultHearts, int goldenHearts, int enlightenedHearts, int stellatechHearts) {
        this.defaultHearts = Math.min(defaultHearts, 20);
        this.goldenHearts = Math.min(goldenHearts, 20);
        this.enlightenedHearts = Math.min(enlightenedHearts, 20);
        this.stellatechHearts = Math.min(stellatechHearts, 20);
    }

    public void tickData(LivingEntity living) {
        var attribute = living.getAttribute(Attributes.MAX_HEALTH);
        if (attribute != null) {
            float accurateHealth = calculateMaxHealth();
            if (attribute.getBaseValue() < accurateHealth || attribute.getBaseValue() > accurateHealth) {
                attribute.setBaseValue(accurateHealth);
            }
        }
        if (isDirty) {
            if (!living.level().isClientSide) {
                living.syncData(ModAttachmentTypes.HEARTS);
            }
            isDirty = false;
        }
    }

    public float calculateMaxHealth() {
        float health = 0;

        health += defaultHearts * 20;

        health += goldenHearts * 30;

        health += enlightenedHearts * 40;

        health += stellatechHearts * 50;

        return health;
    }

    public int getTotalHearts() {
        return defaultHearts + goldenHearts + enlightenedHearts + stellatechHearts;
    }

    public HeartType getHeartType(int index) {
        if (index < defaultHearts) {
            return HeartType.DEFAULT;
        } else if (index < defaultHearts + goldenHearts) {
            return HeartType.GOLDEN;
        } else if (index < defaultHearts + goldenHearts + enlightenedHearts) {
            return HeartType.ENLIGHTENED;
        } else {
            return HeartType.STELLATECH;
        }
    }

    public static int getHeartValue(HeartType type) {
        return switch (type) {
            case DEFAULT -> 20;
            case GOLDEN -> 30;
            case ENLIGHTENED -> 40;
            case STELLATECH -> 50;
        };
    }

    public boolean tryAddDefaultHeart() {
        if (defaultHearts < 20 && getTotalHearts() < 20) {
            defaultHearts++;
            isDirty = true;
            return true;
        }
        return false;
    }

    public boolean tryRemoveDefaultHeart() {
        if (defaultHearts > 0) {
            defaultHearts--;
            isDirty  = true;
            return true;
        }
        return false;
    }

    public boolean tryUpgrade(HeartType targetType) {
        return switch (targetType) {
            case GOLDEN -> {
                if (defaultHearts > 0) {
                    defaultHearts--;
                    goldenHearts++;
                    isDirty = true;
                    yield true;
                }
                yield false;
            }
            case ENLIGHTENED -> {
                if (goldenHearts > 0) {
                    goldenHearts--;
                    enlightenedHearts++;
                    isDirty = true;
                    yield true;
                }
                yield false;
            }
            case STELLATECH -> {
                if (enlightenedHearts > 0) {
                    enlightenedHearts--;
                    stellatechHearts++;
                    isDirty = true;
                    yield true;
                }
                yield false;
            }
            default -> false;
        };
    }

    public boolean tryDowngrade(HeartType targetType) {
        return switch (targetType) {
            case GOLDEN -> {
                if (goldenHearts > 0) {
                    goldenHearts--;
                    defaultHearts++;
                    isDirty = true;
                    yield true;
                }
                yield false;
            }
            case ENLIGHTENED -> {
                if (enlightenedHearts > 0) {
                    enlightenedHearts--;
                    goldenHearts++;
                    isDirty = true;
                    yield true;
                }
                yield false;
            }
            case STELLATECH -> {
                if (stellatechHearts > 0) {
                    stellatechHearts--;
                    enlightenedHearts++;
                    isDirty = true;
                    yield true;
                }
                yield false;
            }
            default -> false;
        };
    }

    public boolean canUpgrade(HeartType targetType) {
        return switch (targetType) {
            case GOLDEN -> defaultHearts > 0;
            case ENLIGHTENED -> goldenHearts > 0;
            case STELLATECH -> enlightenedHearts > 0;
            default -> false;
        };
    }

    public boolean canDowngrade(HeartType targetType) {
        return switch (targetType) {
            case GOLDEN -> goldenHearts > 0;
            case ENLIGHTENED -> enlightenedHearts > 0;
            case STELLATECH -> stellatechHearts > 0;
            default -> false;
        };
    }

    public boolean canAddDefaultHeart() {
        return defaultHearts < 20 && getTotalHearts() < 20;
    }

    public boolean canRemoveDefaultHeart() {
        return defaultHearts > 0 && getTotalHearts() <= 20;
    }

    public int getDefaultHearts() { return defaultHearts; }
    public int getGoldenHearts() { return goldenHearts; }
    public int getEnlightenedHearts() { return enlightenedHearts; }
    public int getStellatechHearts() { return stellatechHearts; }

    public static final Codec<HeartData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("default").orElse(5).forGetter(HeartData::getDefaultHearts),
                    Codec.INT.fieldOf("golden").orElse(0).forGetter(HeartData::getGoldenHearts),
                    Codec.INT.fieldOf("enlightened").orElse(0).forGetter(HeartData::getEnlightenedHearts),
                    Codec.INT.fieldOf("stellatech").orElse(0).forGetter(HeartData::getStellatechHearts)
            ).apply(instance, HeartData::new)
    );

    public static StreamCodec<ByteBuf, HeartData> STREAM_CODEC = ByteBufCodecs.fromCodec(HeartData.CODEC);
}
