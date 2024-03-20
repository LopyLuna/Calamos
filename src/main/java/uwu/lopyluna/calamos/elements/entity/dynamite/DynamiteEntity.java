package uwu.lopyluna.calamos.elements.entity.dynamite;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import uwu.lopyluna.calamos.elements.ModEntity;

public class DynamiteEntity extends Projectile {

    public DynamiteEntity(EntityType<DynamiteEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public DynamiteEntity(Level pLevel) {
        super(ModEntity.DYNAMITE.get(), pLevel);
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }
}
