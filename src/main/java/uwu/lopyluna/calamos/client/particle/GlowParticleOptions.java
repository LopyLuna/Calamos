package uwu.lopyluna.calamos.client.particle;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;
import uwu.lopyluna.calamos.elements.ModParticleTypes;
import uwu.lopyluna.calamos.utilities.ModUtils;

public class GlowParticleOptions implements ParticleOptions {
    protected final Vector3f color;
    protected final Vec3 motion;
    protected final float scale;
    protected final int lifetime;

    public static Vector3f SPECTRE_LIFESTEAL = new Vector3f(235.0F / 255.0F, 243.0F / 255.0F, 249.0F / 255.0F);

    public static final MapCodec<GlowParticleOptions> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            ExtraCodecs.VECTOR3F.fieldOf("color").forGetter((glow) -> glow.color),
            Vec3.CODEC.fieldOf("motion").forGetter((glow) -> glow.motion),
            Codec.FLOAT.fieldOf("scale").forGetter((glow) -> glow.scale),
            Codec.INT.fieldOf("lifetime").forGetter((glow) -> glow.lifetime)
    ).apply(instance, GlowParticleOptions::new));

    public static final StreamCodec<FriendlyByteBuf, GlowParticleOptions> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VECTOR3F, (glow) -> glow.color,
            ModUtils.VEC3_STREAM_CODEC, (glow) -> glow.motion,
            ByteBufCodecs.FLOAT, (glow) -> glow.scale,
            ByteBufCodecs.INT, (glow) -> glow.lifetime,
            GlowParticleOptions::new
    );

    public GlowParticleOptions(Vector3f pColor, Vec3 pMotion, float pScale, int plifetime) {
        this.color = pColor;
        this.motion = pMotion;
        this.scale = pScale;
        this.lifetime = plifetime;
    }

    public GlowParticleOptions(Vector3f pColor, Vec3 pMotion, float pScale) {
        this(pColor, pMotion, pScale, -1);
    }

    public GlowParticleOptions(Vector3f pColor, float pScale, int plifetime) {
        this(pColor, Vec3.ZERO, pScale, plifetime);
    }

    public GlowParticleOptions(Vector3f pColor, float pScale) {
        this(pColor, Vec3.ZERO, pScale);
    }

    public Vector3f getColor() {
        return this.color;
    }

    public Vec3 getMotion() {
        return this.motion;
    }

    public float getScale() {
        return this.scale;
    }

    public int getLifetime() {
        return this.lifetime;
    }

    @Override
    public ParticleType<?> getType() {
        return ModParticleTypes.GLOW.get();
    }
}
