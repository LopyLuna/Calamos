package uwu.lopyluna.calamos.elements.entity.machina.pestis_infection;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.elements.ModEffects;
import uwu.lopyluna.calamos.elements.entity.entity_definitions.MachinaHusk;
import uwu.lopyluna.calamos.elements.entity.machina.goal.*;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;
import java.util.UUID;
@ParametersAreNonnullByDefault
public class PestisPlayerEntity extends PathfinderMob implements MachinaHusk {
    private static final EntityDataAccessor<BlockPos> ANTENNA_POS = SynchedEntityData.defineId(PestisPlayerEntity.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<Boolean> IN_RANGE_OF_ANTENNA = SynchedEntityData.defineId(PestisPlayerEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IN_RANGE_OF_MAINFRAME = SynchedEntityData.defineId(PestisPlayerEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<String> LINKED_PLAYER = SynchedEntityData.defineId(PestisPlayerEntity.class, EntityDataSerializers.STRING);
    @Nullable
    private PlayerInfo playerInfo;
    public static UUID linkedPlayer;
    public GameType linkedPlayerGameType;
    public float oBob;
    public float bob;
    public double xCloakO;
    public double yCloakO;
    public double zCloakO;
    public double xCloak;
    public double yCloak;
    public double zCloak;
    protected Vec3 deltaMovementOnPreviousTick = Vec3.ZERO;
    protected static final EntityDataAccessor<Byte> DATA_PLAYER_MODE_CUSTOMISATION = SynchedEntityData.defineId(PestisPlayerEntity.class, EntityDataSerializers.BYTE);
    public PestisPlayerEntity(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public static boolean shouldUseSlimModel() {
        if (linkedPlayer == null) {
            return false;
        }
        ClientPacketListener connection = Minecraft.getInstance().getConnection();
        if (connection == null) {
            return false;
        }
        PlayerInfo playerinfo = connection.getPlayerInfo(linkedPlayer);
        return playerinfo != null && playerinfo.getSkin().model() == PlayerSkin.Model.SLIM;
    }
    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_PLAYER_MODE_CUSTOMISATION, (byte)0);
        builder.define(ANTENNA_POS, BlockPos.ZERO);
        builder.define(IN_RANGE_OF_ANTENNA, false);
        builder.define(IN_RANGE_OF_MAINFRAME, false);
        builder.define(LINKED_PLAYER, "");
    }
    @Override
    public void tick() {
        super.tick();
        this.deltaMovementOnPreviousTick = this.getDeltaMovement();
        if (isActive()) {
            checkAntenna();
            this.moveCloak();
        } else {
            spawnSmoke();
        }
        if (getLinkedPlayer() != null) {
            Player player = level().getPlayerByUUID(getLinkedPlayer());
            if (player != null && this.getAttribute(Attributes.MAX_HEALTH) != null) {
                AttributeModifier healthModifier = new AttributeModifier(CalamosMod.asResource("health.machina_pestis_player"), player.getMaxHealth() - 100, AttributeModifier.Operation.ADD_VALUE);
                if (this.getAttribute(Attributes.MAX_HEALTH).hasModifier(CalamosMod.asResource("health.machina_pestis_player"))) {
                    return;
                }
                this.getAttribute(Attributes.MAX_HEALTH).addTransientModifier(healthModifier);
            }
            if (player != null) {
                if (!player.hasEffect(ModEffects.PESTIS)) {
                    this.kill();
                }
            }
        } else {
            this.kill();
        }
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(8, new MachinaLookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new MachinaRandomLookAroundGoal(this));
        this.addBehaviourGoals();
    }
    boolean canBreakDoors() {
        return true;
    }
    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(6, new MachinaMoveThroughVillageGoal(this, 1.0, true, 4, this::canBreakDoors));
        this.goalSelector.addGoal(7, new MachinaWaterAvoidingRandomStrollGoal(this, 1.0));
        this.targetSelector.addGoal(1, new MachinaHurtByTargetGoal(this).setAlertOthers(ZombifiedPiglin.class));
        this.targetSelector.addGoal(2, new MachinaNearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new MachinaNearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(3, new MachinaNearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 100.0F)
                .add(Attributes.KNOCKBACK_RESISTANCE, 100)
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.MOVEMENT_SPEED, 0.23F)
                .add(Attributes.ATTACK_DAMAGE, 3.0);
    }
    @Override
    public void rideTick() {
        super.rideTick();
        this.oBob = this.bob;
        this.bob = 0.0F;
    }
    @Nullable
    protected PlayerInfo getPlayerInfo() {
        if (this.playerInfo == null) {
            this.playerInfo = Minecraft.getInstance().getConnection().getPlayerInfo(linkedPlayer);
        }
        
        return this.playerInfo;
    }
    public PlayerSkin getSkin() {
        PlayerInfo playerinfo = this.getPlayerInfo();
        return playerinfo == null ? DefaultPlayerSkin.get(this.getUUID()) : playerinfo.getSkin();
    }

    public boolean isModelPartShown(PlayerModelPart pPart) {
        return (this.getEntityData().get(DATA_PLAYER_MODE_CUSTOMISATION) & pPart.getMask()) == pPart.getMask();
    }
    public Vec3 getDeltaMovementLerped(float pPatialTick) {
        return this.deltaMovementOnPreviousTick.lerp(this.getDeltaMovement(), (double)pPatialTick);
    }
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        linkedPlayer = pCompound.getUUID("LinkedPlayer");
        this.linkedPlayerGameType = GameType.byName(pCompound.getString("LinkedPlayerGameType"));
    }
    
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putUUID("LinkedPlayer", linkedPlayer);
        pCompound.putString("LinkedPlayerGameType", this.linkedPlayerGameType.getName());
    }

    @Override
    protected void dropAllDeathLoot(ServerLevel level, DamageSource pDamageSource) {
        this.captureDrops(new java.util.ArrayList<>());

        boolean flag = this.lastHurtByPlayerTime > 0;
        if (this.shouldDropLoot() && this.level().getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
            this.dropFromLootTable(pDamageSource, flag);
        }
        
        this.dropExperience(pDamageSource.getEntity());
        
        Collection<ItemEntity> drops = captureDrops(null);
        if (!net.neoforged.neoforge.common.CommonHooks.onLivingDrops(this, pDamageSource, drops, lastHurtByPlayerTime > 0))
            drops.forEach(e -> level().addFreshEntity(e));
    }
    
    private void moveCloak() {
        this.xCloakO = this.xCloak;
        this.yCloakO = this.yCloak;
        this.zCloakO = this.zCloak;
        double d0 = this.getX() - this.xCloak;
        double d1 = this.getY() - this.yCloak;
        double d2 = this.getZ() - this.zCloak;
        double d3 = 10.0;
        if (d0 > 10.0) {
            this.xCloak = this.getX();
            this.xCloakO = this.xCloak;
        }
        
        if (d2 > 10.0) {
            this.zCloak = this.getZ();
            this.zCloakO = this.zCloak;
        }
        
        if (d1 > 10.0) {
            this.yCloak = this.getY();
            this.yCloakO = this.yCloak;
        }
        
        if (d0 < -10.0) {
            this.xCloak = this.getX();
            this.xCloakO = this.xCloak;
        }
        
        if (d2 < -10.0) {
            this.zCloak = this.getZ();
            this.zCloakO = this.zCloak;
        }
        
        if (d1 < -10.0) {
            this.yCloak = this.getY();
            this.yCloakO = this.yCloak;
        }
        
        this.xCloak += d0 * 0.25;
        this.zCloak += d2 * 0.25;
        this.yCloak += d1 * 0.25;
    }
    public void setInRangeOfAntenna(boolean inRangeOfAntenna) {
        this.entityData.set(IN_RANGE_OF_ANTENNA, inRangeOfAntenna);
    }
    public void setInRangeOfMainframe(boolean inRangeOfMainframe) {
        this.entityData.set(IN_RANGE_OF_MAINFRAME, inRangeOfMainframe);
    }
    public void clearAntenna() {
        this.getPersistentData().putBoolean("inRangeOfAntenna", false);
        this.getPersistentData().putDouble("antennaX", 0);
        this.getPersistentData().putDouble("antennaY", 0);
        this.getPersistentData().putDouble("antennaZ", 0);
    }
    @Override
    public boolean inRangeOfAntenna() {
        return this.entityData.get(IN_RANGE_OF_ANTENNA);
    }
    
    @Override
    public boolean inRangeOfMainframe() {
        return this.entityData.get(IN_RANGE_OF_MAINFRAME);
    }
    
    @Override
    public boolean isMainframe() {
        return false;
    }
    
    public BlockPos getAntennaPos() {
        return this.entityData.get(ANTENNA_POS);
    }

    @Nullable
    public UUID getLinkedPlayer() {
        if (this.entityData.get(LINKED_PLAYER).isEmpty()) {
            return null;
        }
        return UUID.fromString(this.entityData.get(LINKED_PLAYER));
    }
    
    public void checkAntenna() {
        setInRangeOfAntenna(true);
    }
    public void spawnSmoke() {
        if (this.level.isClientSide) {
            this.level.addParticle(ParticleTypes.LARGE_SMOKE, this.getX(), this.getY() + 1.0D, this.getZ(), 0.0D, 0.0D, 0.0D);
        }
    }
}
