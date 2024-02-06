package uwu.lopyluna.calamos;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(CalamosMod.MODID)
public class CalamosMod {
    private static CalamosMod INSTANCE;
    public static final String MODID = "calamos";
    private static final Logger LOGGER = LogUtils.getLogger();
    private final IEventBus modEventBus;
    public CalamosMod(IEventBus modEventBus) {
        this.modEventBus = modEventBus;
        INSTANCE = this;
    }

    public static IEventBus getEventBus() {
        return INSTANCE.modEventBus;
    }

}
