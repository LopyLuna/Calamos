package uwu.lopyluna.calamos.elements;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.elements.enchantments.FastFlightEnchantment;
import uwu.lopyluna.calamos.elements.enchantments.FlightChargeEnchantment;
import uwu.lopyluna.calamos.elements.enchantments.SavingGraceEnchantment;
import uwu.lopyluna.calamos.utilities.ModUtils;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = ModUtils.createRegister(Registries.ENCHANTMENT);
    
    public static final DeferredHolder<Enchantment, Enchantment> SAVING_GRACE = ENCHANTMENTS.register("saving_grace", () -> new SavingGraceEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.CHEST));
    public static final DeferredHolder<Enchantment, Enchantment> FLIGHT_CHARGE = ENCHANTMENTS.register("flight_charge", () -> new FlightChargeEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.CHEST));
    public static final DeferredHolder<Enchantment, Enchantment> FAST_FLIGHT = ENCHANTMENTS.register("fast_flight", () -> new FastFlightEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.CHEST));
    
    public static void staticInit() {
        CalamosMod.LOGGER.info("enchanted metbal");
    }
}
