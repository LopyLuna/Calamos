package uwu.lopyluna.calamos.elements.entity.entity_definitions;

import net.minecraft.world.entity.Entity;

public interface MachinaHusk {
    boolean inRangeOfAntenna();
    boolean inRangeOfMainframe();
    boolean isMainframe();
    default boolean isActive() {
        return inRangeOfAntenna() || inRangeOfMainframe();
    };
}
