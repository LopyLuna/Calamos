package uwu.lopyluna.calamos;

import com.mojang.serialization.Lifecycle;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;
import uwu.lopyluna.calamos.core.modifier.Modifier;

public class CalamosRegistries {

    public static ResourceKey<Registry<Modifier>> MODIFIER = createRegistryKey("modifiers");
    public static final Registry<Modifier> MODIFIER_REGISTRY = makeSyncedRegistry(MODIFIER);

    private static <T> ResourceKey<Registry<T>> createRegistryKey(String name) {
        return ResourceKey.createRegistryKey(CalamosMod.asResource(name));
    }

    private static <T> Registry<T> makeSyncedRegistry(ResourceKey<Registry<T>> registryKey) {
        return new RegistryBuilder<>(registryKey).sync(true).create();
    }

    private static <T> Registry<T> makeRegistry(ResourceKey<Registry<T>> registryKey) {
        return new RegistryBuilder<>(registryKey).create();
    }
    private static <T> Registry<T> registerSimpleWithIntrusiveHolders(ResourceKey<? extends Registry<T>> registryKey) {
        return new MappedRegistry<>(registryKey, Lifecycle.stable(), true);
    }

    @EventBusSubscriber(modid = CalamosMod.MODID, bus = EventBusSubscriber.Bus.MOD)
    public static class Register {

        @SubscribeEvent
        public static void newDatapackRegistry(DataPackRegistryEvent.NewRegistry event) {
        }

        @SubscribeEvent
        public static void newRegistry(NewRegistryEvent event) {
            event.register(MODIFIER_REGISTRY);
        }
    }
}
