package uwu.lopyluna.calamos.elements.entity.entity_definitions;

public interface MachinaHusk {
    boolean inRangeOfAntenna();
    boolean inRangeOfMainframe();
    boolean isMainframe();
    default boolean isActive() {
        return inRangeOfAntenna() || inRangeOfMainframe();
    };
    double getAntennaX();
    double getAntennaY();
    double getAntennaZ();
}
