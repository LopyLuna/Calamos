package uwu.lopyluna.calamos.elements;

import net.minecraft.core.registries.BuiltInRegistries;
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

@EventBusSubscriber(modid = CalamosMod.MODID)
public class ModAttributes {
    public static final HashMap<DeferredHolder<Attribute, Attribute>, UUID> UUIDS = new HashMap<>();
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(BuiltInRegistries.ATTRIBUTE, CalamosMod.MODID);
    
    /**
     * Handles the flight meter.
     */
    public static final DeferredHolder<Attribute, Attribute> MAX_FLIGHT = registerAttribute("flight.max", (id) -> new RangedAttribute("attribute.name.calamos.max_flight", 0.0F, 0.0, 1024.0).setSyncable(true), "ad9dbfff-51c3-46fb-bd12-f1bdee3a9302");
    public static final DeferredHolder<Attribute, Attribute> FLIGHT_SPEED = registerAttribute("flight.speed", (id) -> new RangedAttribute("attribute.name.calamos.flight_speed", 1.0F, 0.0, 1024.0).setSyncable(true), "229570fc-75c0-4844-8ef9-20ac6c1a1e90");
    public static final DeferredHolder<Attribute, Attribute> FLIGHT_REGENERATION = registerAttribute("flight.regeneration", (id) -> new RangedAttribute("attribute.name.calamos.flight_regeneration", 2.0F, 0.0, 1024.0).setSyncable(true), "75f4e63c-a409-4527-a1f2-05677bb697d7");

    //Mana
    public static final DeferredHolder<Attribute, Attribute> MANA_COST_REDUCTION = registerAttribute("mana.cost_reduction", (id) -> new RangedAttribute("attribute.name.calamos.mana_cost_reduction", 0.0F, 0.0, 1024.0).setSyncable(true), "3801c592-6997-4542-b028-592ee276bb1e");
    public static final DeferredHolder<Attribute, Attribute> MAX_MANA = registerAttribute("mana.max", (id) -> new RangedAttribute("attribute.name.calamos.max_mana", 100.0F, 0.0, 1024.0).setSyncable(true), "c2942744-59f0-4ded-8fab-ad78d4907ed3");
    public static final DeferredHolder<Attribute, Attribute> MANA_COOLDOWN_CAP = registerAttribute("mana.cooldown_cap", (id) -> new RangedAttribute("attribute.name.calamos.mana_cooldown_cap", 0.0F, 0.0, 1024.0).setSyncable(true), "9b0c90fa-d58c-4318-a332-ea44af3f8a69");
    public static final DeferredHolder<Attribute, Attribute> MANA_REGEN_BONUS = registerAttribute("mana.regeneration_bonus", (id) -> new RangedAttribute("attribute.name.calamos.mana_regeneration_bonus", 0.0F, 0.0, 1024.0).setSyncable(true), "6567b4dd-4eeb-4f10-af91-1ea01a3b14ea");

    //Critical Strike
    public static final DeferredHolder<Attribute, Attribute> MELEE_CRIT_CHANCE = registerAttribute("critical_strike_chance.melee", (id) -> new RangedAttribute("attribute.name.calamos.melee_critical_strike_chance", 0.04F, 0.0, 1024.0).setSyncable(true), "9ec6dddd-804a-4db6-becd-757961b8b2d7");
    public static final DeferredHolder<Attribute, Attribute> RANGED_CRIT_CHANCE = registerAttribute("critical_strike_chance.ranged", (id) -> new RangedAttribute("attribute.name.calamos.ranged_critical_strike_chance", 0.04F, 0.0, 1024.0).setSyncable(true), "fa7bc11c-8325-43ae-8831-ddbd4b9dcc7c");
    public static final DeferredHolder<Attribute, Attribute> MAGIC_CRIT_CHANCE = registerAttribute("critical_strike_chance.magic", (id) -> new RangedAttribute("attribute.name.calamos.magic_critical_strike_chance", 0.04F, 0.0, 1024.0).setSyncable(true), "86967424-e13b-44ca-b8c6-5b31131f7855");
    public static final DeferredHolder<Attribute, Attribute> GLOBAL_CRIT_CHANCE = registerAttribute("critical_strike_chance", (id) -> new RangedAttribute("attribute.name.calamos.critical_strike_chance", 0.04F, 0.0, 1024.0).setSyncable(true), "0f0a61b7-046e-4cb6-ad69-d5daa5f9d4db");

    //Damage
    public static final DeferredHolder<Attribute, Attribute> MELEE_DAMAGE = registerAttribute("damage.melee", (id) -> new RangedAttribute("attribute.name.calamos.melee_damage", 1.0F, 0.0, 1024.0).setSyncable(true), "ee7dcc87-87db-4ef5-b0b1-25ae43ca7f35");
    public static final DeferredHolder<Attribute, Attribute> RANGED_DAMAGE = registerAttribute("damage.ranged", (id) -> new RangedAttribute("attribute.name.calamos.ranged_damage", 1.0F, 0.0, 1024.0).setSyncable(true), "0890bcd4-d79e-455b-b8ba-05014a213a76");
    public static final DeferredHolder<Attribute, Attribute> MAGIC_DAMAGE = registerAttribute("damage.magic", (id) -> new RangedAttribute("attribute.name.calamos.magic_damage", 1.0F, 0.0, 1024.0).setSyncable(true), "ee07f84f-ea40-4fd0-9ecb-a767713d0128");
    public static final DeferredHolder<Attribute, Attribute> SUMMON_DAMAGE = registerAttribute("damage.summon", (id) -> new RangedAttribute("attribute.name.calamos.summon_damage", 1.0F, 0.0, 1024.0).setSyncable(true), "7dd4121e-6589-4bc0-8d42-0c6338deda54");

    //Projectile
    public static final DeferredHolder<Attribute, Attribute> PROJECTILE_KNOCKBACK = registerAttribute("projectile.knockback", (id) -> new RangedAttribute("attribute.name.calamos.projectile_knockback", 1.0F, 0.0, 1024.0).setSyncable(true), "113497c5-e526-43cb-811e-70ebbcbaf5dc");
    public static final DeferredHolder<Attribute, Attribute> PROJECTILE_VELOCITY = registerAttribute("projectile.velocity", (id) -> new RangedAttribute("attribute.name.calamos.projectile_velocity", 1.0F, 0.0, 1024.0).setSyncable(true), "deb95c2d-ae27-40db-b263-0fd964dd17de");
    public static final DeferredHolder<Attribute, Attribute> DRAW_SPEED = registerAttribute("draw_speed", (id) -> new RangedAttribute("attribute.name.calamos.draw_speed", 1.0F, 0.0, 1024.0).setSyncable(true), "6a30da25-3d8d-4031-956c-7844988a69d2");

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
        event.getTypes().forEach(e -> {
            for (DeferredHolder<Attribute, ? extends Attribute> entry : ATTRIBUTES.getEntries()) {
                event.add(e, entry);
            }
        });
    }

    public static void init(IEventBus bus) {
        ATTRIBUTES.register(bus);
    }
}
