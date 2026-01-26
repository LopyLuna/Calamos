package uwu.lopyluna.calamos.core.entity.orb;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;

public class AbstractOrbEntity extends Projectile {
    protected AbstractOrbEntity(EntityType<? extends Projectile> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }
}
