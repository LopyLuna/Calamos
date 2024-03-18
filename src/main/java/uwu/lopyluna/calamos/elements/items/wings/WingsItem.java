package uwu.lopyluna.calamos.elements.items.wings;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import uwu.lopyluna.calamos.elements.CalamosKeys;
import uwu.lopyluna.calamos.elements.ModEnchantments;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class WingsItem extends Item implements Equipable, ICurioItem {
    public static final EquipmentSlot SLOT = EquipmentSlot.CHEST;
    public static SlotContext SLOTC;
    public WingsItem() {
        super(new Item.Properties().stacksTo(1));
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
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
    @Nullable
    public static WingsItem getWornBy(Entity entity) {
        if (!(entity instanceof LivingEntity livingEntity)) {
            return null;
        }
        if (!(livingEntity.getItemBySlot(SLOT).getItem() instanceof WingsItem item)) {
            return null;
        }
        return item;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        Entity entity = slotContext.entity();
        Level level = entity.level();
        if (entity instanceof Player player) {
            wingFunction(player, stack, level);
        }
        SLOTC = slotContext;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int itemSlot, boolean isSelected) {
        super.inventoryTick(stack, level, entity, itemSlot, isSelected);
        if (entity instanceof Player player && isWearingWings(player)) {
            wingFunction(player, stack, level);
        }
    }

    public void wingFunction(Player player, ItemStack stack, Level level) {
        boolean hasSavingGrace = stack.getEnchantmentLevel(ModEnchantments.SAVING_GRACE.get()) > 0;
        BlockPos pos = player.blockPosition();
        boolean hasMaxFlightMeter = getFlightMeter(player) >= getMaxFlightMeter(player);
        if (!isOnGround(player)) {
            player.resetFallDistance();
        }
        if (!isOnGround(player) && CalamosKeys.boost.isPressed() && canBoostUp(player)) {
            decreaseFlightMeter(player,0.1f);
            boostUpMovement(player, stack);
        }
        if (isOnGround(player) && !hasMaxFlightMeter) {
            replenishFlightMeter(player, getReplenishRate(stack));
        }
        if (!isOnGround(player) && !level.getBlockState(pos.below(2)).isAir() && hasSavingGrace && player.fallDistance > 3.0f) {
            decreaseFlightMeter(player, 4.0f - stack.getEnchantmentLevel(ModEnchantments.SAVING_GRACE.get()));
        }
        if (!canBoostUp(player) && !isOnGround(player) && CalamosKeys.boost.isPressed()) {
            glidingMovement(player, stack);
        } else if (canBoostUp(player) && !isOnGround(player) && !CalamosKeys.boost.isPressed() && !player.isCrouching()) {
            decreaseFlightMeter(player,0.025f);
            glidingMovement(player, stack);
        } else if (canBoostUp(player) && !isOnGround(player) && !CalamosKeys.boost.isPressed() && player.isCrouching()) {
            boostHoriztonalMovement(player, stack);
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
    public void boostUpMovement(Player player, ItemStack stack) {
        Vec3 vec3 = player.getDeltaMovement();
        player.setDeltaMovement(vec3.add(0, 1.5 * (vec3.y <= 0.3F ? vec3.y <= -0.1F ? vec3.y <= -0.2F ? vec3.y <= -0.3F ? vec3.y <= -0.5F ? 0.5 : 0.25 : 0.2 : 0.15 : 0.1 : 0), 0));
        boostHoriztonalMovement(player, stack);
    }

    public void glidingMovement(Player player, ItemStack stack) {
        Vec3 vec3 = player.getDeltaMovement();
        player.setDeltaMovement(divide(vec3,1, vec3.y <= -0.25F ? 1.5F : 1, 1));
        boostHoriztonalMovement(player, stack);
    }

    public void boostHoriztonalMovement(Player player, ItemStack stack) {
        Vec3 vec3 = player.getDeltaMovement();
        player.move(MoverType.SELF, vec3.multiply(defaultMovementRate(stack), 0, defaultMovementRate(stack)));
    }

    public float defaultMovementRate(ItemStack stack) {
        float defaultMovementRate = 1.1F;
        boolean hasFastFlight = stack.getEnchantmentLevel(ModEnchantments.FAST_FLIGHT.get()) > 0;
        if (hasFastFlight)
            return switch (stack.getEnchantmentLevel(ModEnchantments.FAST_FLIGHT.get())) {
                case 1 -> defaultMovementRate * 1.1F;
                case 2 -> defaultMovementRate * 1.2F;
                case 3 -> defaultMovementRate * 1.3F;
                default -> defaultMovementRate;
            };
        return defaultMovementRate;
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
    public static float getFlightMeter(Player player) {
        CompoundTag tag = player.getPersistentData();
        if (!tag.contains("flight_meter"))
            tag.putFloat("flight_meter", getMaxFlightMeter(player));
        return tag.getFloat("flight_meter");
    }
    public static float getMaxFlightMeter(Player player) {
        CompoundTag tag = player.getPersistentData();
        return tag.getFloat("max_flight_meter");
    }

    public Vec3 divide(Vec3 vec3, double pFactorX, double pFactorY, double pFactorZ) {return new Vec3(vec3.x / pFactorX, vec3.y / pFactorY, vec3.z / pFactorZ);}
}
