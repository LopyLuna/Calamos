package uwu.lopyluna.calamos.core.items.equipment.damage;

import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import uwu.lopyluna.calamos.datagen.ModTags;
import uwu.lopyluna.calamos.elements.ModAttributes;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MultiDamageHandler {

    private final RegistryAccess registryAccess;

    @Nullable
    private Entity causingEntity;
    @Nullable
    private Entity directEntity;
    @Nullable
    private Vec3 damageSourcePosition;

    private final Map<ResourceKey<DamageType>, Float> initialSources = new HashMap<>();
    private final Map<ResourceKey<DamageType>, Float> modifiedSources = new HashMap<>();

    public MultiDamageHandler(RegistryAccess registryAccess) {
        this.registryAccess = registryAccess;
    }

    public void setDirectEntity(@Nullable Entity directEntity) {
        this.directEntity = directEntity;
    }

    public void setCausingEntity(@Nullable Entity causingEntity) {
        this.causingEntity = causingEntity;
    }

    public void setDamageSourcePosition(@Nullable Vec3 damageSourcePosition) {
        this.damageSourcePosition = damageSourcePosition;
    }

    public void addDamageSource(ResourceKey<DamageType> type, float damage) {
        this.initialSources.put(type, damage);
    }

    @Nullable
    public Entity getDirectEntity() {
        return this.directEntity;
    }

    @Nullable
    public Entity getEntity() {
        return this.causingEntity;
    }

    @Nullable
    public ItemStack getWeaponItem() {
        return this.directEntity != null ? this.directEntity.getWeaponItem() : null;
    }

    @Nullable
    public Vec3 getSourcePosition() {
        if (this.damageSourcePosition != null) {
            return this.damageSourcePosition;
        } else {
            return this.directEntity != null ? this.directEntity.position() : null;
        }
    }

    @Nullable
    public Vec3 sourcePositionRaw() {
        return this.damageSourcePosition;
    }

    private Holder<DamageType> getType(ResourceKey<DamageType> type) {
        var lookup = this.registryAccess.lookupOrThrow(Registries.DAMAGE_TYPE);
        return lookup.getOrThrow(type);
    }

    private boolean isOf(ResourceKey<DamageType> type, TagKey<DamageType> tagKey) {
        return getType(type).is(tagKey);
    }

    private Map<Holder<Attribute>,TagKey<DamageType>> damagesMap = Map.of(
            ModAttributes.MELEE_DAMAGE, ModTags.modDamageTag("melee"),
            ModAttributes.RANGED_DAMAGE, ModTags.modDamageTag("ranged"),
            ModAttributes.MAGIC_DAMAGE, ModTags.modDamageTag("magic"),
            ModAttributes.SUMMON_DAMAGE, ModTags.modDamageTag("summon")
    );

    public float getModifiedDamage(ResourceKey<DamageType> type) {
        float damage = this.initialSources.get(type);
        if (this.causingEntity instanceof LivingEntity living) {
            for (var damages : damagesMap.entrySet()) {
                double modifier = living.getAttributeValue(damages.getKey());
                if (isOf(type, damages.getValue())) {
                    damage *= (float) modifier;
                }
            }
        }
        return damage;
    }

    public Map<ResourceKey<DamageType>, Float> getModifiedSources() {
        return this.initialSources.keySet().stream().collect(Collectors.toMap(key -> key, this::getModifiedDamage));
    }

    public void damage() {

    }
}
