package uwu.lopyluna.calamos.utilities;

import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import uwu.lopyluna.calamos.core.entity.entity_definitions.BossBarMonster;
import uwu.lopyluna.calamos.networking.packets.S2C.UpdateBossBarPacket;

public class CalamosBossEvent extends ServerBossEvent {
    private final BossBarMonster entity;

    public CalamosBossEvent(BossBarMonster entity) {
        super(entity.getDisplayName(), entity.bossBarColor(), entity.bossBarOverlay());
        this.entity = entity;
    }

    @Override
    public void addPlayer(ServerPlayer pPlayer) {
        PacketDistributor.sendToPlayer(pPlayer, UpdateBossBarPacket.create(this.getId(), entity));
        super.addPlayer(pPlayer);
    }

    @Override
    public void removePlayer(ServerPlayer pPlayer) {
        PacketDistributor.sendToPlayer(pPlayer, UpdateBossBarPacket.create(this.getId(), entity));
        super.removePlayer(pPlayer);
    }
}
