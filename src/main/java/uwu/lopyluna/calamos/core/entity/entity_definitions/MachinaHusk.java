package uwu.lopyluna.calamos.core.entity.entity_definitions;

public interface MachinaHusk {
    boolean inRangeOfAntenna();
    boolean inRangeOfMainframe();
    boolean isMainframe();
    default boolean isActive() {
        return inRangeOfAntenna() || inRangeOfMainframe();
    };
}
