package uwu.lopyluna.calamos.elements.items.accessories;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import uwu.lopyluna.calamos.elements.CalamosKeys;

public class JarredCloudItem extends Item implements ICurioItem {

    public JarredCloudItem() {
        super(new Item.Properties().stacksTo(1));
    }

    private boolean shouldBoostUp(Player player) {
        float f = player.fallDistance;
        return f > 0.5f;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        if (livingEntity instanceof Player player) {
            CompoundTag tag = player.getPersistentData();
            if (player.onGround()) {
                setDoubleJumped(player, false);
            } else {
                if (CalamosKeys.boost.isPressed()) {
                    boostUp(player);
                }
            }
        }
    }

    public void boostUp(Player player) {
        if (hasDoubleJumped(player) || !shouldBoostUp(player)) {
            return;
        }
        float jumpPower = 0.42F + player.getJumpBoostPower();
        setDoubleJumped(player, true);
        player.setDeltaMovement(player.getDeltaMovement().add(0.0, jumpPower * 2.5, 0.0));
        createCloudParticles(player);
    }

    public void createCloudParticles(Player player) {
        //create a solid disc of particles at the player's feet where the boost key was pressed
        //the code beneath just makes a ring of particles
        //for (int i = 0; i < 360; i += 10) {
        //    double x = Math.cos(Math.toRadians(i));
        //    double z = Math.sin(Math.toRadians(i));
        //    player.level.addParticle(ParticleTypes.CLOUD, player.getX() + x, player.getY(), player.getZ() + z, 0.0, 0.0, 0.0);
        //}
        for (int i = 0; i < 360; i += 10) {
            for (int j = 0; j < 360; j += 10) {
                double x = Math.cos(Math.toRadians(i)) * Math.sin(Math.toRadians(j));
                double y = Math.cos(Math.toRadians(j));
                double z = Math.sin(Math.toRadians(i)) * Math.sin(Math.toRadians(j));
                //the further out the particles are, cancel their spawning
                if (Math.sqrt(x * x + y * y + z * z) > 1.0) {
                    continue;
                }
                player.level.addParticle(ParticleTypes.CLOUD, player.getX() + x, player.getY() + y, player.getZ() + z, 0.0, 0.0, 0.0);
            }
        }
    }

    public static boolean hasDoubleJumped(Player player) {
        CompoundTag tag = player.getPersistentData();
        return tag.contains("JarredCloud_used") && tag.getBoolean("JarredCloud_used");
    }

    public static void setDoubleJumped(Player player, boolean value) {
        CompoundTag tag = player.getPersistentData();
        tag.putBoolean("JarredCloud_used", value);
    }
}
