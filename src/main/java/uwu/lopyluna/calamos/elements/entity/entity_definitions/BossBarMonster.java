package uwu.lopyluna.calamos.elements.entity.entity_definitions;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import uwu.lopyluna.calamos.utilities.CalamosBossEvent;

import javax.annotation.Nullable;

public class BossBarMonster extends Monster {
    BossEvent.BossBarColor pColor;
    BossEvent.BossBarOverlay pOverlay;

    protected BossBarMonster(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    private final ServerBossEvent bossEvent = new CalamosBossEvent(
            this
    );

    @Override
    public void setCustomName(@Nullable Component pName) {
        super.setCustomName(pName);
        this.bossEvent.setName(this.getDisplayName());
    }
    @Override
    public void startSeenByPlayer(ServerPlayer pPlayer) {
        super.startSeenByPlayer(pPlayer);
        this.bossEvent.addPlayer(pPlayer);
    }
    @Override
    public void stopSeenByPlayer(ServerPlayer pPlayer) {
        super.stopSeenByPlayer(pPlayer);
        this.bossEvent.removePlayer(pPlayer);
    }
    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();

        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }

    public BossEvent.BossBarColor bossBarColor() {
        return BossEvent.BossBarColor.PURPLE;
    }
    public BossEvent.BossBarOverlay bossBarOverlay() {
        return BossEvent.BossBarOverlay.PROGRESS;
    }
}
