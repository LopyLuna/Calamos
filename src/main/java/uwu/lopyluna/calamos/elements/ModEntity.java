package uwu.lopyluna.calamos.elements;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.elements.entity.Worm;
import uwu.lopyluna.calamos.elements.entity.WormPart;
import uwu.lopyluna.calamos.elements.entity.boone.BooneEntity;
import uwu.lopyluna.calamos.elements.entity.dynamite.DynamiteEntity;
import uwu.lopyluna.calamos.elements.entity.machina.pestis_infection.PestisPlayerEntity;
import uwu.lopyluna.calamos.elements.entity.wildfire.WildfireEntity;
import uwu.lopyluna.calamos.elements.entity.wildfire.WildfireRenderer;
import uwu.lopyluna.calamos.utilities.ModUtils;

public final class ModEntity {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = ModUtils.createRegister(Registries.ENTITY_TYPE);

    public static final DeferredHolder<EntityType<?>, EntityType<Worm>> WORM_HEAD = ENTITY_TYPES.register("worm_head",
            () -> EntityType.Builder.of((EntityType.EntityFactory<Worm>) Worm::new, MobCategory.MISC).sized(3.0F, 3.0F).build("worm_head"));

    public static final DeferredHolder<EntityType<?>, EntityType<WormPart>> WORM_PART = ENTITY_TYPES.register("worm_part",
            () -> EntityType.Builder.of((EntityType.EntityFactory<WormPart>) WormPart::new, MobCategory.MISC).sized(3.0F, 1.5F).build("worm_part"));
    
    public static final DeferredHolder<EntityType<?>, EntityType<PestisPlayerEntity>> PESTIS_PLAYER = ENTITY_TYPES.register("pestis_player",
            () -> EntityType.Builder.of((EntityType.EntityFactory<PestisPlayerEntity>) PestisPlayerEntity::new, MobCategory.CREATURE).sized(0.6F, 1.8F).build("pestis_player"));
    public static final DeferredHolder<EntityType<?>, EntityType<WildfireEntity>> WILDFIRE = ENTITY_TYPES.register("wildfire",
            () -> EntityType.Builder.of((EntityType.EntityFactory<WildfireEntity>) WildfireEntity::new, MobCategory.CREATURE).sized(0.7F * WildfireRenderer.SCALE, 1.875F * WildfireRenderer.SCALE).setTrackingRange(32).fireImmune().build("wildfire"));
    public static final DeferredHolder<EntityType<?>, EntityType<BooneEntity>> BOONE_THE_BOOM = ENTITY_TYPES.register("boone_the_boom",
            () -> EntityType.Builder.of(BooneEntity::new, MobCategory.CREATURE).sized(3.0F, 6.0F).build("boone_the_boom"));
    public static final DeferredHolder<EntityType<?>, EntityType<DynamiteEntity>> DYNAMITE = ENTITY_TYPES.register("dynamite",
            () -> EntityType.Builder.of((EntityType.EntityFactory<DynamiteEntity>)DynamiteEntity::new, MobCategory.MISC).sized(1.0F, 1.0F).build("dynamite"));

    public static void staticInit() {

    }

}
