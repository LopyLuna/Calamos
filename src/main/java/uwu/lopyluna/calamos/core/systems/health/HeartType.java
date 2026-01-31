package uwu.lopyluna.calamos.core.systems.health;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public enum HeartType {
    DEFAULT("default"),
    GOLDEN("golden"),
    ENLIGHTENED("enlightened"),
    STELLATECH("stellatech");

    private final String id;

    HeartType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
