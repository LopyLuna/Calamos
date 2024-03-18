package uwu.lopyluna.calamos.elements;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.CalamosMod;

import java.util.HashMap;
import java.util.UUID;
import java.util.function.Function;

@Mod.EventBusSubscriber(modid = CalamosMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModAttributes {
    public static final HashMap<DeferredHolder<Attribute, Attribute>, UUID> UUIDS = new HashMap<>();
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(BuiltInRegistries.ATTRIBUTE, CalamosMod.MODID);
    
    
    /**
     * Handles the flight meter.
     */
    public static final DeferredHolder<Attribute, Attribute> FLIGHT_METER = registerAttribute("calamos.flight_meter", (id) -> new RangedAttribute("attribute.name.calamos.flight_meter", 30.0F, 0.0, 1024.0).setSyncable(true), "ad9dbfff-51c3-46fb-bd12-f1bdee3a9302");
    public static DeferredHolder<Attribute, Attribute> registerAttribute(String name, Function<String, Attribute> attribute, String uuid) {
        return registerAttribute(name, attribute, UUID.fromString(uuid));
    }
    
    public static DeferredHolder<Attribute, Attribute> registerAttribute(String name, Function<String, Attribute> attribute, UUID uuid) {
        DeferredHolder<Attribute, Attribute> registryObject = ATTRIBUTES.register(name, () -> attribute.apply(name));
        UUIDS.put(registryObject, uuid);
        return registryObject;
    }
    
    @SubscribeEvent
    public static void modifyEntityAttributes(EntityAttributeModificationEvent event) {
        event.getTypes().stream().filter(e -> e == EntityType.PLAYER).forEach(e -> {
            ATTRIBUTES.getEntries().forEach((v) -> {
                event.add(e, v.get());
            });
        });
    }
    public static void init(IEventBus bus) {
        ATTRIBUTES.register(bus);
    }
}
