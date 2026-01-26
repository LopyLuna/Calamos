package uwu.lopyluna.calamos.core.rage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.commands.arguments.selector.EntitySelectorParser;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import uwu.lopyluna.calamos.datagen.ModTags;
import uwu.lopyluna.calamos.elements.ModAttributes;

import java.util.List;

public class RageData {

    public static Codec<RageData> CODEC = RecordCodecBuilder.create(obj -> obj.group(
            Codec.DOUBLE.optionalFieldOf("rage", 0D).forGetter(RageData::getRage)
    ).apply(obj, RageData::new));

    public static StreamCodec<ByteBuf, RageData> STREAM_CODEC = ByteBufCodecs.fromCodec(RageData.CODEC);

    private double rage;

    public RageData() {
    }

    public RageData(double rage) {
        this.rage = rage;
    }

    public void tickData(LivingEntity living) {
        var attribute = living.getAttribute(ModAttributes.MAX_MANA);
    }

    private float enemyDistance(LivingEntity entity) {
        Vec3 position = entity.position();
        AABB range = entity.getBoundingBox().inflate(50);
        List<Entity> entities = entity.level().getEntities(entity, range, e -> testEntity(entity, e));
        EntitySelectorParser.ORDER_NEAREST.accept(position, entities);
        return entities.getFirst().distanceTo(entity);
    }

    private boolean testEntity(LivingEntity source, Entity entity) {
        if (!(entity instanceof LivingEntity)) return false;
        if (!entity.isAlive()) return false;
        if (entity.getType().is(ModTags.modEntityTag("rage_blacklist"))) return false;
        if (entity.isAlliedTo(source)) return false;
        return entity.getType().getCategory() == MobCategory.MONSTER;
    }

    public double getRage() {
        return rage;
    }
}
