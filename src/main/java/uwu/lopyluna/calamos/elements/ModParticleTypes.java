package uwu.lopyluna.calamos.elements;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.client.particle.GlowParticleOptions;

public class ModParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, CalamosMod.MODID);

    public static final DeferredHolder<ParticleType<?>, ParticleType<GlowParticleOptions>> GLOW = registerParticle("glow", false, GlowParticleOptions.CODEC, GlowParticleOptions.STREAM_CODEC);

    public static <T extends ParticleOptions> DeferredHolder<ParticleType<?>, ParticleType<T>> registerParticle(String name, boolean overrideLimiter, MapCodec<T> codec, StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec) {
        return PARTICLES.register(name, () -> new ParticleType<T>(overrideLimiter) {
            @Override
            public MapCodec<T> codec() {
                return codec;
            }

            @Override
            public StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec() {
                return streamCodec;
            }
        });
    }

    public static void init(IEventBus bus) {
        PARTICLES.register(bus);
    }
}
