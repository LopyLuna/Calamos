package uwu.lopyluna.calamos.core.entity.hook;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.VariantHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import uwu.lopyluna.calamos.elements.ModEntity;
import uwu.lopyluna.calamos.core.items.equipment.hook.AbstractHookItem;

import java.util.function.IntFunction;

public class HookEntity extends AbstractHookEntity implements VariantHolder<HookEntity.Variant> {
    private static final EntityDataAccessor<Integer> DATA_VARIANT = SynchedEntityData.defineId(HookEntity.class, EntityDataSerializers.INT);

    public HookEntity(EntityType<HookEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public HookEntity(AbstractHookItem item, Player player, Level level, Variant variant) {
        super(ModEntity.HOOK.get(), item, player, level);
        setVariant(variant);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_VARIANT, 0);
    }

    @Override
    public void setVariant(Variant pVariant) {
        entityData.set(DATA_VARIANT, pVariant.id);
    }

    @Override
    public @NotNull Variant getVariant() {
        return Variant.byId(entityData.get(DATA_VARIANT));
    }

    public enum Variant implements StringRepresentable {
        GRAPPLING(0, "grappling"),
        AMETHYST(1, "amethyst"),
        TOPAZ(2, "topaz"),
        SAPPHIRE(3, "sapphire"),
        EMERALD(4, "emerald"),
        RUBY(5, "ruby"),
        DIAMOND(6, "diamond");

        private static final IntFunction<Variant> BY_ID = ByIdMap.continuous(Variant::getId, values(), ByIdMap.OutOfBoundsStrategy.CLAMP);
        final int id;
        private final String name;

        Variant(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public static Variant byId(int pId) {
            return BY_ID.apply(pId);
        }

        @Override
        public @NotNull String getSerializedName() {
            return name;
        }
    }
}
