package uwu.lopyluna.calamos.core.systems.mana;

public class ManaSegment {
    private final Style style;
    private final ManaState state;
    private final double points;

    public ManaSegment(Style style, ManaState state, double points) {
        this.style = style;
        this.state = state;
        this.points = Math.min(20, Math.max(0, points));
    }

    public enum Style {
        BLUE, PURPLE, LIGHT_BLUE, PINK
    }
}
