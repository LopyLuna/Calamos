package uwu.lopyluna.calamos.utilities;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import uwu.lopyluna.calamos.elements.entity.entity_definitions.BossBarMonster;
import uwu.lopyluna.calamos.networking.CalamosMessages;
import uwu.lopyluna.calamos.networking.packets.S2C.UpdateBossBarPacket;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CalamosBossEvent extends ServerBossEvent {
    private final BossBarMonster entity;

    public CalamosBossEvent(BossBarMonster entity) {
        super(entity.getDisplayName(), entity.bossBarColor(), entity.bossBarOverlay());
        this.entity = entity;
    }

    @Override
    public void addPlayer(ServerPlayer pPlayer) {
        CalamosMessages.sendToPlayer(new UpdateBossBarPacket(this.getId(), entity), pPlayer);
        super.addPlayer(pPlayer);
    }

    @Override
    public void removePlayer(ServerPlayer pPlayer) {
        CalamosMessages.sendToPlayer(new UpdateBossBarPacket(this.getId(), entity), pPlayer);
        super.removePlayer(pPlayer);
    }
}
