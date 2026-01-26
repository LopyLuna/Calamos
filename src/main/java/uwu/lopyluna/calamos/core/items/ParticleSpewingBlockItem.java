package uwu.lopyluna.calamos.core.items;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;

public class ParticleSpewingBlockItem extends BlockItem {
    private final ParticleOptions mainParticle;
    private final ParticleOptions bonusParticle;
    public ParticleSpewingBlockItem(Block pBlock, Properties pProperties, ParticleOptions mainParticle, ParticleOptions bonusParticle) {
        super(pBlock, pProperties);
        this.mainParticle = mainParticle;
        this.bonusParticle = bonusParticle;
    }
    
    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        Level world = entity.level();
        Vec3 pos = entity.position();
        CompoundTag persistentData = entity.getPersistentData();
        if (world.isClientSide) {
            if (world.random.nextFloat() < getIdleParticleChance(entity)) {
                Vec3 ppos = offsetRandomly(pos, world.random, .5f);
                Vec3 motion = offsetRandomly(ppos, world.random, 1);
                world.addParticle(bonusParticle, ppos.x, pos.y + 0.5, ppos.z, 0, -.1f, 0);
                world.addParticle(mainParticle, pos.x, pos.y + 0.5, pos.z, motion.x, motion.y, motion.z);
            }
            if (entity.isSilent() && !persistentData.getBoolean("PlayEffects")) {
                Vec3 basemotion = new Vec3(0, 1, 0);
                world.addParticle(ParticleTypes.FLASH, pos.x, pos.y, pos.z, 0, 0, 0);
                for (int i = 0; i < 20; i++) {
                    Vec3 motion = offsetRandomly(basemotion, world.random, 1);
                    world.addParticle(bonusParticle, pos.x, pos.y, pos.z, motion.x, motion.y, motion.z);
                    world.addParticle(mainParticle, pos.x, pos.y, pos.z, motion.x, motion.y, motion.z);
                }
                persistentData.putBoolean("PlayEffects", true);
            }
            return false;
        }
        return false;
    }
    protected float getIdleParticleChance(ItemEntity entity) {
        return Mth.clamp(entity.getItem()
                .getCount() - 10, 5, 100) / 64f;
    }
    
    public static Vec3 offsetRandomly(Vec3 vec, RandomSource r, float radius) {
        return new Vec3(vec.x + (r.nextFloat() - .5f) * 2 * radius, vec.y + (r.nextFloat() - .5f) * 2 * radius,
                vec.z + (r.nextFloat() - .5f) * 2 * radius);
    }
}
