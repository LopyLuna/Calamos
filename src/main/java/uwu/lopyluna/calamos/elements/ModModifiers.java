package uwu.lopyluna.calamos.elements;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.CalamosRegistries;
import uwu.lopyluna.calamos.datagen.ModTags;
import uwu.lopyluna.calamos.core.modifier.Modifier;

import java.util.function.Function;

public class ModModifiers {
    public static final DeferredRegister<Modifier> MODIFIERS = DeferredRegister.create(CalamosRegistries.MODIFIER_REGISTRY, CalamosMod.MODID);

    //              Accessories
    //Defence
    public static final DeferredHolder<Modifier, Modifier>
            ACCESSORY_HARD = create(Modifier.builder("hard").apply(accessory(), add(Attributes.ARMOR, 1))),
            ACCESSORY_GUARDING = create(Modifier.builder("guarding").apply(accessory(), add(Attributes.ARMOR, 2))),
            ACCESSORY_ARMORED = create(Modifier.builder("armored").apply(accessory(), add(Attributes.ARMOR, 3))),
            ACCESSORY_WARDING = create(Modifier.builder("warding").apply(accessory(), add(Attributes.ARMOR, 4)));
    //Critical Strike Chance
    public static final DeferredHolder<Modifier, Modifier>
            ACCESSORY_PRECISE = create(Modifier.builder("precise").apply(accessory(), multiTotal(ModAttributes.GLOBAL_CRIT_CHANCE, 0.02))),
            ACCESSORY_LUCKY = create(Modifier.builder("lucky").apply(accessory(), multiTotal(ModAttributes.GLOBAL_CRIT_CHANCE, 0.04)));
    //Damage
    public static final DeferredHolder<Modifier, Modifier>
            ACCESSORY_JAGGED = create(Modifier.builder("jagged").apply(accessory(), multiTotal(Attributes.ATTACK_DAMAGE, 0.01))),
            ACCESSORY_SPIKED = create(Modifier.builder("spiked").apply(accessory(), multiTotal(Attributes.ATTACK_DAMAGE, 0.02))),
            ACCESSORY_ANGRY = create(Modifier.builder("angry").apply(accessory(), multiTotal(Attributes.ATTACK_DAMAGE, 0.03))),
            ACCESSORY_MENACING = create(Modifier.builder("menacing").apply(accessory(), multiTotal(Attributes.ATTACK_DAMAGE, 0.04)));
    //Movement Speed
    public static final DeferredHolder<Modifier, Modifier>
            ACCESSORY_BRISK = create(Modifier.builder("brisk").apply(accessory(), multiTotal(Attributes.MOVEMENT_SPEED, 0.01))),
            ACCESSORY_FLEETING = create(Modifier.builder("fleeting").apply(accessory(), multiTotal(Attributes.MOVEMENT_SPEED, 0.02))),
            ACCESSORY_HASTY = create(Modifier.builder("hasty").apply(accessory(), multiTotal(Attributes.MOVEMENT_SPEED, 0.03))),
            ACCESSORY_QUICK = create(Modifier.builder("quick").apply(accessory(), multiTotal(Attributes.MOVEMENT_SPEED, 0.04)));
    //Attack Speed
    public static final DeferredHolder<Modifier, Modifier>
            ACCESSORY_WILD = create(Modifier.builder("wild").apply(accessory(), multiTotal(Attributes.ATTACK_SPEED, -0.01))),
            ACCESSORY_RASH = create(Modifier.builder("rash").apply(accessory(), multiTotal(Attributes.ATTACK_SPEED, -0.02))),
            ACCESSORY_INTREPID = create(Modifier.builder("intrepid").apply(accessory(), multiTotal(Attributes.ATTACK_SPEED, -0.03))),
            ACCESSORY_VIOLENT = create(Modifier.builder("violent").apply(accessory(), multiTotal(Attributes.ATTACK_SPEED, -0.04)));
    //Mana
    public static final DeferredHolder<Modifier, Modifier>
            ACCESSORY_ARCANE = create(Modifier.builder("arcane").apply(accessory(), add(ModAttributes.MAX_MANA, 20)));

    //              Weapons
    //Universal
    public static final DeferredHolder<Modifier, Modifier>
            WEAPON_BROKEN = create(Modifier.builder("broken")
                    .apply(universalWeapon(-0.3, 0, -0.2))),
            WEAPON_SHODDY = create(Modifier.builder("shoddy")
                    .apply(universalWeapon(-0.1, 0, -0.15))),
            WEAPON_WEAK = create(Modifier.builder("weak")
                    .apply(universalWeapon(0, 0, -0.2))),
            WEAPON_DAMAGED = create(Modifier.builder("damaged")
                    .apply(universalWeapon(-0.15, 0, 0))),
            WEAPON_KEEN = create(Modifier.builder("keen")
                    .apply(universalWeapon(0, 0.03, 0))),
            WEAPON_FORCEFUL = create(Modifier.builder("forceful")
                    .apply(universalWeapon(0, 0, 0.15))),
            WEAPON_HURTFUL = create(Modifier.builder("hurtful")
                    .apply(universalWeapon(0.1, 0, 0))),
            WEAPON_STRONG = create(Modifier.builder("strong")
                    .apply(universalWeapon(0, 0, 0.15))),
            WEAPON_RUTHLESS = create(Modifier.builder("ruthless")
                    .apply(universalWeapon(0.18, 0, -0.1))),
            WEAPON_ZEALOUS = create(Modifier.builder("zealous")
                    .apply(universalWeapon(0, 0.05, 0))),
            WEAPON_SUPERIOR = create(Modifier.builder("superior")
                    .apply(universalWeapon(0.1, 0.03, 0.1))),
            WEAPON_UNPLEASANT = create(Modifier.builder("unpleasant")
                    .apply(universalWeapon(0.05, 0, 0.15))),
            WEAPON_GODLY = create(Modifier.builder("godly")
                    .apply(universalWeapon(0.15, 0.05, 0.15))),
            WEAPON_DEMONIC = create(Modifier.builder("demonic")
                    .apply(universalWeapon(0.15, 0.05, 0)));
    //Common
    public static final DeferredHolder<Modifier, Modifier>
            WEAPON_QUICK = create(Modifier.builder("quick")
                    .apply(commonWeapon(0, 0.1, 0, 0))),
            WEAPON_DEADLY = create(Modifier.builder("deadly")
                    .apply(commonWeapon(0.1, 0.1, 0, 0))),
            WEAPON_AGILE = create(Modifier.builder("agile")
                    .apply(commonWeapon(0, 0.1, 0.03, 0))),
            WEAPON_NIMBLE = create(Modifier.builder("nimble")
                    .apply(commonWeapon(0, 0.05, 0, 0))),
            WEAPON_MURDEROUS = create(Modifier.builder("murderous")
                    .apply(commonWeapon(0.07, 0.06, 0.03, 0))),
            WEAPON_SLOW = create(Modifier.builder("slow")
                    .apply(commonWeapon(0, -0.15, 0, 0))),
            WEAPON_SLUGGISH = create(Modifier.builder("sluggish")
                    .apply(commonWeapon(0, -0.2, 0, 0))),
            WEAPON_LAZY = create(Modifier.builder("lazy")
                    .apply(commonWeapon(0, -0.08, 0, 0))),
            WEAPON_ANNOYING = create(Modifier.builder("annoying")
                    .apply(commonWeapon(-0.2, -0.15, 0, 0))),
            WEAPON_NASTY = create(Modifier.builder("nasty")
                    .apply(commonWeapon(0.05, 0.1, 0.02, -0.1)));
    //Melee
    public static final DeferredHolder<Modifier, Modifier>
            WEAPON_DANGEROUS = create(Modifier.builder("dangerous")
                    .apply(meleeWeapon(0.05, 0, 0.02, 0.05, 0))),
            WEAPON_SAVAGE = create(Modifier.builder("savage")
                    .apply(meleeWeapon(0.1, 0, 0, 0.1, 0.1))),
            WEAPON_SHARP = create(Modifier.builder("sharp")
                    .apply(meleeWeapon(0.15, 0, 0, 0, 0))),
            WEAPON_POINTY = create(Modifier.builder("pointy")
                    .apply(meleeWeapon(0.1, 0, 0, 0, 0))),
            WEAPON_TINY = create(Modifier.builder("tiny")
                    .apply(meleeWeapon(0, 0, 0, -0.18, 0))),
            WEAPON_TERRIBLE = create(Modifier.builder("terrible")
                    .apply(meleeWeapon(-0.15, 0, 0, -0.13, -0.15))),
            WEAPON_SMALL = create(Modifier.builder("small")
                    .apply(meleeWeapon(0, 0, 0, -0.1, 0))),
            WEAPON_DULL = create(Modifier.builder("dull")
                    .apply(meleeWeapon(-0.15, 0, 0, 0, 0))),
            WEAPON_UNHAPPY = create(Modifier.builder("unhappy")
                    .apply(meleeWeapon(0, -0.1, 0, 0,0))),
            WEAPON_BULKY = create(Modifier.builder("bulky")
                    .apply(meleeWeapon(0.05, -0.15, 0, 0.1, 0.1))),
            WEAPON_SHAMEFUL = create(Modifier.builder("shameful")
                    .apply(meleeWeapon(-0.1, 0, 0, 0.1, -0.2))),
            WEAPON_HEAVY = create(Modifier.builder("heavy")
                    .apply(meleeWeapon(0, -0.1, 0, 0, 0.15))),
            WEAPON_LIGHT = create(Modifier.builder("light")
                    .apply(meleeWeapon(0, 0.15, 0, 0, -0.1))),
            WEAPON_LEGENDARY = create(Modifier.builder("legendary")
                    .apply(meleeWeapon(0.15, 0.1, 0.05, 0.1, 0.15)));
    //Ranged
    public static final DeferredHolder<Modifier, Modifier>
            RANGED_SIGHTED = create(Modifier.builder("sighted")
                    .apply(rangedWeapon(0.1, 0, 0.03, 0, 0))),
            RANGED_RAPID = create(Modifier.builder("rapid")
                    .apply(rangedWeapon(0, 0.15, 0, 0.1, 0))),
            RANGED_HASTY = create(Modifier.builder("hasty")
                    .apply(rangedWeapon(0, 0.1, 0, 0.15, 0))),
            RANGED_INTIMIDATING = create(Modifier.builder("intimidating")
                    .apply(rangedWeapon(0, 0, 0, 0.05, 0.15))),
            RANGED_DEADLY = create(Modifier.builder("deadly")
                    .apply(rangedWeapon(0.1, 0.05, 0.02, 0.05, 0.05))),
            RANGED_STAUNCH = create(Modifier.builder("staunch")
                    .apply(rangedWeapon(0.1, 0, 0, 0, 0.15))),
            RANGED_AWFUL = create(Modifier.builder("awful")
                    .apply(rangedWeapon(-0.15, 0, 0, -0.1, -0.1))),
            RANGED_LETHARGIC = create(Modifier.builder("lethargic")
                    .apply(rangedWeapon(0, -0.15, 0, -0.1, 0))),
            RANGED_AWKWARD = create(Modifier.builder("awkward")
                    .apply(rangedWeapon(0, -0.1, 0, 0, -0.2))),
            RANGED_POWERFUL = create(Modifier.builder("powerful")
                    .apply(rangedWeapon(0.15, -0.1, 0.01, 0, 0))),
            RANGED_FRENZYING = create(Modifier.builder("frenzying")
                    .apply(rangedWeapon(-0.15, 0.15, 0, 0, 0))),
            RANGED_UNREAL = create(Modifier.builder("unreal")
                    .apply(rangedWeapon(0.15, 0.1, 0.05, 0.1, 0.15)));
    //Magic
    public static final DeferredHolder<Modifier, Modifier>
            MAGIC_MYSTIC = create(Modifier.builder("mystic")
                    .apply(magicWeapon(0.1, 0, 0, 0.15, 0))),
            MAGIC_ADEPT = create(Modifier.builder("adept")
                    .apply(magicWeapon(0, 0, 0, 0.15, 0))),
            MAGIC_MASTERFUL = create(Modifier.builder("masterful")
                    .apply(magicWeapon(0.15, 0, 0, 0.15, 0))),
            MAGIC_INEPT = create(Modifier.builder("inept")
                    .apply(magicWeapon(0, 0, 0, -0.1, 0))),
            MAGIC_IGNORANT = create(Modifier.builder("ignorant")
                    .apply(magicWeapon(-0.1, 0, 0, -0.2, 0))),
            MAGIC_DERANGED = create(Modifier.builder("deranged")
                    .apply(magicWeapon(-0.1, 0, 0, 0, -0.1))),
            MAGIC_INTENSE = create(Modifier.builder("intense")
                    .apply(magicWeapon(0.1, 0, 0, 0.15, 0))),
            MAGIC_TABOO = create(Modifier.builder("taboo")
                    .apply(magicWeapon(0, 0.1, 0, -0.1, 0.1))),
            MAGIC_CELESTIAL = create(Modifier.builder("celestial")
                    .apply(magicWeapon(0.1, -0.1, 0, 0.1, 0.1))),
            MAGIC_FURIOUS = create(Modifier.builder("furious")
                    .apply(magicWeapon(0.15, 0, 0, -0.2, 0.15))),
            MAGIC_MANIC = create(Modifier.builder("manic")
                    .apply(magicWeapon(-0.1, 0.1, 0, 0.1, 0))),
            MAGIC_MYTHICAL = create(Modifier.builder("mythical")
                    .apply(magicWeapon(0.15, 0.1, 0.05, 0.1, 0.15)));

    public static DeferredHolder<Modifier, Modifier> create(Modifier.Builder builder) {
        return builder.register(MODIFIERS::register);
    }

    private static Function<Modifier.Builder, Modifier.Builder> accessory() {
        return builder -> builder
                .prefix("accessory/")
                .supportTag(ModTags.modItemTag("modifiable/accessory/universal"));
    }

    private static Function<Modifier.Builder, Modifier.Builder> weapon(String group) {
        return builder -> builder
                .prefix("weapon/")
                .supportTag(ModTags.modItemTag("modifiable/weapon/" + group));
    }

    private static Function<Modifier.Builder, Modifier.Builder> typedWeapon(String group) {
        return builder -> builder
                .prefix(group + "/")
                .supportTag(ModTags.modItemTag("modifiable/weapon/" + group));
    }

    private static Function<Modifier.Builder, Modifier.Builder> add(Holder<Attribute> attribute, double amount) {
        if (amount == 0) return builder -> builder;
        return builder -> builder.add(attribute, amount, AttributeModifier.Operation.ADD_VALUE);
    }

    private static Function<Modifier.Builder, Modifier.Builder> multiTotal(Holder<Attribute> attribute, double amount) {
        if (amount == 0) return builder -> builder;
        return builder -> builder.add(attribute, amount, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }

    private static Function<Modifier.Builder, Modifier.Builder> multiBase(Holder<Attribute> attribute, double amount) {
        if (amount == 0) return builder -> builder;
        return builder -> builder.add(attribute, amount, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    }

    private static Function<Modifier.Builder, Modifier.Builder> universalWeapon(double damage, double crit, double knockback) {
        return builder ->
                builder.apply(weapon("universal"), multiTotal(Attributes.ATTACK_DAMAGE, damage), multiTotal(ModAttributes.GLOBAL_CRIT_CHANCE, crit), multiTotal(Attributes.ATTACK_KNOCKBACK, knockback));
    }

    private static Function<Modifier.Builder, Modifier.Builder> commonWeapon(double damage, double speed, double crit, double knockback) {
        return builder ->
                builder.apply(weapon("common"),
                        multiTotal(Attributes.ATTACK_DAMAGE, damage),
                        multiTotal(Attributes.BLOCK_BREAK_SPEED, speed),
                        multiTotal(Attributes.ATTACK_SPEED, speed),
                        add(ModAttributes.GLOBAL_CRIT_CHANCE, crit),
                        multiTotal(Attributes.ATTACK_KNOCKBACK, knockback)
                );
    }

    private static Function<Modifier.Builder, Modifier.Builder> meleeWeapon(double damage, double speed, double crit, double range, double knockback) {
        return builder ->
                builder.apply(weapon("melee"),
                        multiTotal(Attributes.ATTACK_DAMAGE, damage),
                        multiTotal(Attributes.BLOCK_BREAK_SPEED, speed),
                        multiTotal(Attributes.ATTACK_SPEED, speed),
                        add(ModAttributes.MELEE_CRIT_CHANCE, crit),
                        multiTotal(Attributes.ENTITY_INTERACTION_RANGE, range),
                        multiTotal(Attributes.BLOCK_INTERACTION_RANGE, range),
                        multiTotal(Attributes.ATTACK_KNOCKBACK, knockback)
                );
    }
    private static Function<Modifier.Builder, Modifier.Builder> rangedWeapon(double damage, double speed, double crit, double velocity, double knockback) {
        return builder ->
                builder.apply(typedWeapon("ranged"),
                        multiTotal(ModAttributes.RANGED_DAMAGE, damage),
                        add(ModAttributes.DRAW_SPEED, speed),
                        add(ModAttributes.RANGED_CRIT_CHANCE, crit),
                        multiTotal(ModAttributes.PROJECTILE_VELOCITY, velocity),
                        multiTotal(ModAttributes.PROJECTILE_KNOCKBACK, knockback)
                );
    }
    private static Function<Modifier.Builder, Modifier.Builder> magicWeapon(double damage, double speed, double crit, double manaCost, double knockback) {
        return builder ->
                builder.apply(typedWeapon("magic"),
                        multiTotal(ModAttributes.MAGIC_DAMAGE, damage),
                        add(ModAttributes.DRAW_SPEED, speed),
                        add(ModAttributes.MAGIC_CRIT_CHANCE, crit),
                        multiTotal(ModAttributes.MANA_COST_REDUCTION, manaCost),
                        multiTotal(Attributes.ATTACK_KNOCKBACK, knockback)
                );
    }

    public static void init(IEventBus bus) {
        MODIFIERS.register(bus);
    }
}
