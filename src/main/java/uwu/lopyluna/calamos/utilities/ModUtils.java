package uwu.lopyluna.calamos.utilities;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Scoreboard;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.CalamosMod;

import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

public class ModUtils {
    private static final Consumer<?> NO_ACTION = (a) -> {};

    public static ResourceLocation location(String path) {
        return CalamosMod.asResource(path);
    }

    public static <DR extends DeferredRegister<T>, T> DR createRegister(Function<String, DR> factory) {
        return registerToBus(factory.apply(CalamosMod.MODID));
    }

    public static <T> DeferredRegister<T> createRegister(ResourceKey<Registry<T>> registry) {
        return registerToBus(DeferredRegister.create(registry, CalamosMod.MODID));
    }

    private static <DR extends DeferredRegister<T>, T> DR registerToBus(DR deferredRegister) {
        deferredRegister.register(CalamosMod.getEventBus());
        return deferredRegister;
    }

    public static PlayerTeam createTeam(Scoreboard scoreboard, String name, ChatFormatting color) {
        if (scoreboard.getTeamNames().contains(name)) {
            return scoreboard.getPlayerTeam(name);
        } else {
            PlayerTeam team = scoreboard.addPlayerTeam(name);
            team.setDisplayName(Component.literal(name));
            team.setColor(color);
            return team;
        }
    }

    public static void removeTeam(Scoreboard scoreboard, PlayerTeam team) {
        if (scoreboard.getTeamNames().contains(team.getName())) {
            scoreboard.removePlayerTeam(team);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> Consumer<T> noAction() {
        return ((Consumer<T>) NO_ACTION);
    }
    
    public static int secondsToTicks(int seconds) {
        return seconds * 20;
    }

    public static float secondsToTicks(float seconds) {
        return seconds * 20.0F;
    }
    
    public static int roundThat(Float n)
    {
        return (int)(n % 1 > 0.5 ? Math.ceil(n) : Math.floor(n));
    }
    public static float flightMeterMax = 100.0F;

    public static ResourceLocation toolTextureLoc(EquipmentType type, String material) {
        return type.toolTextureLoc(material);
    }

    public enum EquipmentType {
        HELMET,
        CHESTPLATE,
        LEGGINGS,
        BOOTS,
        SWORD,
        PICKAXE,
        AXE,
        SHOVEL,
        HOE
        ;

        public boolean isHandheld() {
            return this == SWORD || this == PICKAXE || this == AXE || this == SHOVEL || this == HOE;
        }

        public String getName() {
            return this.name().toLowerCase();
        }

        public ResourceLocation toolTextureLoc(String material) {
            return CalamosMod.asResource("item/equipment/" + material + "/" + this.getName());
        }
    }

    public static StreamCodec<FriendlyByteBuf, UUID> UUID_STREAM_CODEC = new StreamCodec<>() {
        @Override
        public UUID decode(FriendlyByteBuf buf) {
            return buf.readUUID();
        }

        @Override
        public void encode(FriendlyByteBuf buf, UUID uuid) {
            buf.writeUUID(uuid);
        }
    };

    public static StreamCodec<FriendlyByteBuf, Vec3> VEC3_STREAM_CODEC = new StreamCodec<>() {
        @Override
        public Vec3 decode(FriendlyByteBuf buf) {
            return buf.readVec3();
        }

        @Override
        public void encode(FriendlyByteBuf buf, Vec3 vec3) {
            buf.writeVec3(vec3);
        }
    };

    public static int getEnchantLevel(ItemStack stack, LevelAccessor level, ResourceKey<Enchantment> enchantmentKey) {
        var lookup = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        var enchantment = lookup.get(enchantmentKey);
        return enchantment.map(stack::getEnchantmentLevel).orElse(0);
    }
}
