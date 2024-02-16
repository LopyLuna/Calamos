package uwu.lopyluna.calamos.elements.entity.machina.pestis_infection;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import uwu.lopyluna.calamos.elements.ModEffects;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;
import java.util.UUID;
@ParametersAreNonnullByDefault
public class PestisPlayerEntity extends PathfinderMob {
    
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
    protected static final EntityDataAccessor<Byte> DATA_PLAYER_MODE_CUSTOMISATION = SynchedEntityData.defineId(PestisPlayerEntity.class, EntityDataSerializers.BYTE);
    public PestisPlayerEntity(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public static boolean shouldUseSlimModel() {
        if (linkedPlayer == null) {
            return false;
        }
        return Minecraft.getInstance().getConnection().getPlayerInfo(linkedPlayer).getSkin().model() == PlayerSkin.Model.SLIM;
    }
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_PLAYER_MODE_CUSTOMISATION, (byte)0);
    }
    @Override
    public void tick() {
        super.tick();
        this.moveCloak();
        if (linkedPlayer != null) {
            Player player = level().getPlayerByUUID(linkedPlayer);
            if (player != null && this.getAttribute(Attributes.MAX_HEALTH) != null) {
                AttributeModifier healthModifier = new AttributeModifier(UUID.fromString("0a68c468-2e5c-45f4-89cb-84835cb05444"), "Pestis Player Health Multiplier", player.getMaxHealth() - 100, AttributeModifier.Operation.ADDITION);
                if (this.getAttribute(Attributes.MAX_HEALTH).hasModifier(healthModifier)) {
                    return;
                }
                this.getAttribute(Attributes.MAX_HEALTH).addTransientModifier(healthModifier);
            }
            if (player != null) {
                if (!player.hasEffect(ModEffects.PESTIS.get())) {
                    this.kill();
                }
            }
        } else {
            this.kill();
        }
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.addBehaviourGoals();
    }
    boolean canBreakDoors() {
        return true;
    }
    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(6, new MoveThroughVillageGoal(this, 1.0, true, 4, this::canBreakDoors));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers(ZombifiedPiglin.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
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
    protected void dropAllDeathLoot(DamageSource pDamageSource) {
        Entity entity = pDamageSource.getEntity();
        
        int i = net.neoforged.neoforge.common.CommonHooks.getLootingLevel(this, entity, pDamageSource);
        this.captureDrops(new java.util.ArrayList<>());
        
        boolean flag = this.lastHurtByPlayerTime > 0;
        if (this.shouldDropLoot() && this.level().getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
            this.dropFromLootTable(pDamageSource, flag);
        }
        
        this.dropExperience();
        
        Collection<ItemEntity> drops = captureDrops(null);
        if (!net.neoforged.neoforge.common.CommonHooks.onLivingDrops(this, pDamageSource, drops, i, lastHurtByPlayerTime > 0))
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
}
