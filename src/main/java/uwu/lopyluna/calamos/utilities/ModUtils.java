package uwu.lopyluna.calamos.utilities;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Scoreboard;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.CalamosMod;

import java.util.function.Consumer;
import java.util.function.Function;

public class ModUtils {
    private static final Consumer<?> NO_ACTION = (a) -> {};

    public static ResourceLocation location(String path) {
        return new ResourceLocation(CalamosMod.MODID, path);
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
            return new ResourceLocation(CalamosMod.MODID, "item/equipment/" + material + "/" + this.getName());
        }
    }
}
