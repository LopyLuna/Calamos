package uwu.lopyluna.calamos.elements.items.wings;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import uwu.lopyluna.calamos.elements.CalamosKeys;
import uwu.lopyluna.calamos.elements.ModEnchantments;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class WingsItem extends Item implements Equipable {
    public WingsItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_ELYTRA;
    }
    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.CHEST;
    }
    private boolean isWearingWings(Player player) {
        return player.getInventory().armor.get(2).getItem() == this;
    }
    private boolean isOnGround(Player player) {
        return player.onGround();
    }
    private boolean canBoostUp(Player player) {
        return getFlightMeter(player) > 0.0f;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        return this.swapWithEquipmentSlot(this, pLevel, pPlayer, pHand);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int itemSlot, boolean isSelected) {
        super.inventoryTick(stack, level, entity, itemSlot, isSelected);
        boolean hasSavingGrace = stack.getEnchantmentLevel(ModEnchantments.SAVING_GRACE.get()) > 0;
        if (entity instanceof Player player && isWearingWings(player)) {
            BlockPos pos = player.blockPosition();
            boolean hasMaxFlightMeter = getFlightMeter(player) >= getMaxFlightMeter(player);
            if (!isOnGround(player) && CalamosKeys.boost.isPressed() && canBoostUp(player)) {
                decreaseFlightMeter(player,0.2f);
                boostUp(player);
            }
            if (isOnGround(player) && !hasMaxFlightMeter) {
                replenishFlightMeter(player, getReplenishRate(stack));
            }
            if (!canBoostUp(player) && !isOnGround(player)) {
                player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 1, 0, false, false, false));
                getMovement(player);
            }
            if (!isOnGround(player) && !level.getBlockState(pos.below(2)).isAir() && hasSavingGrace && player.fallDistance > 3.0f) {
                decreaseFlightMeter(player, 4.0f - stack.getEnchantmentLevel(ModEnchantments.SAVING_GRACE.get()));
                player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 5, 0, false, false, false));
                getMovement(player);
            }
        }
    }
    public float getReplenishRate(ItemStack stack) {
        float defaultReplenishRate = 0.2f;
        boolean hasFlightCharge = stack.getEnchantmentLevel(ModEnchantments.FLIGHT_CHARGE.get()) > 0;
        if (hasFlightCharge)
            return switch (stack.getEnchantmentLevel(ModEnchantments.FLIGHT_CHARGE.get())) {
                case 1 -> defaultReplenishRate * ((float) 25 / 100);
                case 2 -> defaultReplenishRate * ((float) 50 / 100);
                case 3 -> defaultReplenishRate * ((float) 75 / 100);
                default -> defaultReplenishRate;
            };
        return defaultReplenishRate;
    }

    public void boostUp(Player player) {
        Vec3 vec3 = player.getDeltaMovement();
        player.setDeltaMovement(vec3.x, (double)0.42F + player.getJumpBoostPower(), vec3.z);
        getMovement(player);
    }
    public void getMovement(Player player) {
        Vec3 vec3 = player.getDeltaMovement();
        Vec3 forward = player.getLookAngle();
        Vec3 delta = forward.multiply(0.15, 1, 0.15).add(forward.multiply(1.5, 1 ,1.5).subtract(vec3.x, 0, vec3.z).multiply(0.1, 1, 0.1));
        player.setDeltaMovement(vec3.add(delta.x, 0, delta.z));

    }
    public void decreaseFlightMeter(Player player, float amount) {
        if (getFlightMeter(player) > 0.0f)
            setFlightMeter(player, getFlightMeter(player) - amount);
    }
    public void replenishFlightMeter(Player player, float amount) {
        if (getFlightMeter(player) < 30.0f)
            setFlightMeter(player, getFlightMeter(player) + amount);
    }
    public void setFlightMeter(Player player, float amount) {
        CompoundTag tag = player.getPersistentData();
        if (!tag.contains("flight_meter"))
            tag.putFloat("flight_meter", getMaxFlightMeter(player));
        if (tag.getFloat("flight_meter") > getMaxFlightMeter(player))
            tag.putFloat("flight_meter", getMaxFlightMeter(player));
        tag.putFloat("flight_meter", amount);
    }
    public float getFlightMeter(Player player) {
        CompoundTag tag = player.getPersistentData();
        if (!tag.contains("flight_meter"))
            tag.putFloat("flight_meter", getMaxFlightMeter(player));
        return tag.getFloat("flight_meter");
    }
    public float getMaxFlightMeter(Player player) {
        CompoundTag tag = player.getPersistentData();
        return tag.getFloat("max_flight_meter");
    }
}
