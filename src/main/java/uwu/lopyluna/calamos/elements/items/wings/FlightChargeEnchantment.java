package uwu.lopyluna.calamos.elements.items.wings;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class FlightChargeEnchantment extends Enchantment {
    public FlightChargeEnchantment(Rarity pRarity, EquipmentSlot... pApplicableSlots) {
        super(pRarity, EnchantmentCategory.ARMOR, pApplicableSlots);
    }
    
    @Override
    public int getMinCost(int pEnchantmentLevel) {
        return pEnchantmentLevel * 25;
    }
    
    @Override
    public int getMaxCost(int pEnchantmentLevel) {
        return this.getMinCost(pEnchantmentLevel) + 50;
    }
    public boolean canEnchant(@NotNull ItemStack pStack) {
        super.canEnchant(pStack);
        return pStack.getItem() instanceof WingsItem;
    }
    public boolean isTreasureOnly() {
        return true;
    }
    public int getMaxLevel() {
        return 3;
    }
}
