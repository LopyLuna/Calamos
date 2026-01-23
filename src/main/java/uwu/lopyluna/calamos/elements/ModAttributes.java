package uwu.lopyluna.calamos.elements;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.CalamosMod;

import java.util.HashMap;
import java.util.UUID;
import java.util.function.Function;

@EventBusSubscriber(modid = CalamosMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModAttributes {
    public static final HashMap<DeferredHolder<Attribute, Attribute>, UUID> UUIDS = new HashMap<>();
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(BuiltInRegistries.ATTRIBUTE, CalamosMod.MODID);
    
    /**
     * Handles the flight meter.
     */
    public static final DeferredHolder<Attribute, Attribute> FLIGHT_METER = registerAttribute("calamos.flight_meter", (id) -> new RangedAttribute("attribute.name.calamos.flight_meter", 30.0F, 0.0, 1024.0).setSyncable(true), "ad9dbfff-51c3-46fb-bd12-f1bdee3a9302");
    public static final DeferredHolder<Attribute, Attribute> SUMMON_DAMAGE = registerAttribute("calamos.summon_damage", (id) -> new RangedAttribute("attribute.name.calamos.summon_damage", 0.0F, 0.0, 1024.0).setSyncable(true), "7dd4121e-6589-4bc0-8d42-0c6338deda54");
    public static final DeferredHolder<Attribute, Attribute> CRIT_CHANCE = registerAttribute("calamos.critical_strike_chance", (id) -> new RangedAttribute("attribute.name.calamos.critical_strike_chance", 0.0F, 0.0, 1024.0).setSyncable(true), "9ec6dddd-804a-4db6-becd-757961b8b2d7");
    public static final DeferredHolder<Attribute, Attribute> MANA_COST_REDUCTION = registerAttribute("calamos.mana_cost_reduction", (id) -> new RangedAttribute("attribute.name.calamos.mana_cost_reduction", 0.0F, 0.0, 1024.0).setSyncable(true), "3801c592-6997-4542-b028-592ee276bb1e");
    public static final DeferredHolder<Attribute, Attribute> MAX_MANA = registerAttribute("calamos.max_mana", (id) -> new RangedAttribute("attribute.name.calamos.max_mana", 100.0F, 0.0, 1024.0).setSyncable(true), "c2942744-59f0-4ded-8fab-ad78d4907ed3");

    //Mostly Cosmetic
    public static final DeferredHolder<Attribute, Attribute> MAGIC_DAMAGE = registerAttribute("calamos.magic_damage", (id) -> new RangedAttribute("attribute.name.calamos.magic_damage", 0.0F, 0.0, 1024.0).setSyncable(true), "ee07f84f-ea40-4fd0-9ecb-a767713d0128");

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
                event.add(e, v);
            });
        });
    }

    public static void init(IEventBus bus) {
        ATTRIBUTES.register(bus);
    }
}
