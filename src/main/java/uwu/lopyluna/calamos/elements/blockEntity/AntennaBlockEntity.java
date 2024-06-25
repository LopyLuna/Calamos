package uwu.lopyluna.calamos.elements.blockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import uwu.lopyluna.calamos.elements.ModBlockEntities;
import uwu.lopyluna.calamos.elements.entity.entity_definitions.MachinaHusk;
import uwu.lopyluna.calamos.utilities.be.CalamosBlockEntity;

import java.util.List;

public class AntennaBlockEntity extends CalamosBlockEntity {
    AABB antennaRange = new AABB(-30, -30, -30, 30, 30, 30);
    
    public AntennaBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.MACHINA_ANTENNA.get(), pPos, pBlockState);
    }
    
    public void tick() {
        super.tick();
        if (level != null && !level.isClientSide) {
            List<LivingEntity> entities = getEntitiesInRange();
            if (entities != null) {
                for (LivingEntity entity : entities) {
                    if (entity instanceof MachinaHusk) {
                        CompoundTag data = getInfluencedEntityData(entity);
                        if (data != null) {
                            data.putBoolean("inRangeOfAntenna", true);
                            data.putDouble("antennaX", worldPosition.getX());
                            data.putDouble("antennaY", worldPosition.getY());
                            data.putDouble("antennaZ", worldPosition.getZ());
                        }
                    }
                }
            }
        }
    }
    
    CompoundTag getInfluencedEntityData(LivingEntity entity) {
        if (entity instanceof MachinaHusk) {
            return entity.getPersistentData();
        }
        return null;
    }
    boolean isEntityInRange(LivingEntity entity) {
        return antennaRange.intersects(entity.getBoundingBox());
    }
    
    List<LivingEntity> getEntitiesInRange() {
        if (level != null) {
            return level.getEntitiesOfClass(LivingEntity.class, antennaRange.move(worldPosition));
        }
        return null;
    }
    
    public AABB getAntennaRange() {
        return antennaRange;
    }
}
