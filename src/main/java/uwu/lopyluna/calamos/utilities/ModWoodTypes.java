package uwu.lopyluna.calamos.utilities;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import uwu.lopyluna.calamos.CalamosMod;

public class ModWoodTypes {
    private static String id = CalamosMod.MODID;
    public static final WoodType OTHERWORLD_OAK = WoodType.register(new WoodType(id + "otherworld_oak", BlockSetType.OAK));
    public static final WoodType TWILIGHT = WoodType.register(new WoodType(id + "twilight", BlockSetType.OAK));
}
