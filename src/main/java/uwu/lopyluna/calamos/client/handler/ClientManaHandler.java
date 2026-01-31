package uwu.lopyluna.calamos.client.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import uwu.lopyluna.calamos.core.systems.mana.ManaData;
import uwu.lopyluna.calamos.core.systems.mana.ManaSegment;
import uwu.lopyluna.calamos.core.systems.mana.ManaState;
import uwu.lopyluna.calamos.elements.ModAttachmentTypes;
import uwu.lopyluna.calamos.elements.ModAttributes;

import java.util.List;
import java.util.function.Function;

public class ClientManaHandler {

    public static double oldMana;
    public static float displayedMana;

    public static void tick(ClientTickEvent event) {
        var player = Minecraft.getInstance().player;
        if (player != null) {
            var data = player.getData(ModAttachmentTypes.MANA);
            double max = player.getAttributeValue(ModAttributes.MAX_MANA);
            double currentMana = data.getMana();
            oldMana = currentMana;
            displayedMana = Mth.lerp(0.2f, displayedMana, (float) currentMana);
        }
    }

    private static int getMana(Player player, List<ManaSegment> segments) {
        AttributeInstance manaAttr = player.getAttribute(ModAttributes.MAX_MANA);
        if (manaAttr == null) {
            return 0;
        }
        ManaData manaData = player.getData(ModAttachmentTypes.MANA);
        double mana = manaData.getMana();
        double empty = manaAttr.getValue() - mana;
        double toProcess = mana;
        Function<Double, ManaSegment> emptySeg = (d) -> new ManaSegment(ManaSegment.Style.BLUE, ManaState.EMPTY, d);
        while (toProcess > empty) {
            double points = 20;
            ManaState state = ManaState.FULL;
            if (toProcess < 20 && toProcess >= 10) {

            }
            segments.add(new ManaSegment(ManaSegment.Style.BLUE, ManaState.FULL, 20));
            toProcess -= 20;
        }
        return 0;
    }
}
