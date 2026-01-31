package uwu.lopyluna.calamos.core.systems.health;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.Util;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.elements.ModAttachmentTypes;

public class HeartHandler implements LayeredDraw.Layer {
    public static final HeartHandler INSTANCE = new HeartHandler();

    private static final int HEARTS_PER_ROW = 10;
    private static final int SPRITE_SIZE = 9;
    private static final int HEART_SPACING = 0;

    private final RandomSource random = RandomSource.create();
    private int tickCount;
    private int lastHealth;
    private long lastHealthTime;
    private long healthBlinkTime;

    public void onStartTick() {
        this.tickCount++;
    }

    public static void entityTick(EntityTickEvent.Pre event) {
        if (event.getEntity() instanceof LivingEntity living) {
            var level = living.level();
            if (!level.isClientSide) {
                if (living.getAttributeValue(Attributes.MAX_HEALTH) == 0 && !living.hasData(ModAttachmentTypes.HEARTS)) {
                    return;
                }
                var data = living.getData(ModAttachmentTypes.HEARTS);
                data.tickData(living);
            }
        }
    }

    public static void playerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        HeartData heartData = player.getData(ModAttachmentTypes.HEARTS);

        float maxHealth = heartData.calculateMaxHealth();

        var attribute = player.getAttribute(Attributes.MAX_HEALTH);

        boolean flag = attribute.getBaseValue() < maxHealth || attribute.getBaseValue() > maxHealth;

        if (flag) {
            attribute.setBaseValue(maxHealth);
            player.setHealth(maxHealth);
        }
    }

    public static void playerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            HeartData original = event.getOriginal().getData(ModAttachmentTypes.HEARTS);
            event.getEntity().setData(ModAttachmentTypes.HEARTS, original);
        }
    }

    public void renderPlayerHealth(GuiGraphics guiGraphics, int posX, int posY, Player player, ProfilerFiller profiler) {
        profiler.push("health");
        RenderSystem.enableBlend();
        int currentHealth = Mth.ceil(player.getHealth());
        boolean blink = this.healthBlinkTime > (long) this.tickCount && (this.healthBlinkTime - (long) this.tickCount) / 3L % 2L == 1L;
        long millis = Util.getMillis();

        if (currentHealth < this.lastHealth && player.invulnerableTime > 0) {
            this.lastHealthTime = millis;
            this.healthBlinkTime = this.tickCount + 20;
        } else if (currentHealth > this.lastHealth && player.invulnerableTime > 0) {
            this.lastHealthTime = millis;
            this.healthBlinkTime = this.tickCount + 10;
        }

        if (millis - this.lastHealthTime > 1000L) {
            this.lastHealthTime = millis;
        }

        this.lastHealth = currentHealth;
        this.random.setSeed(this.tickCount * 312871L);

        HeartData heartData = player.getData(ModAttachmentTypes.HEARTS);
        this.renderHearts(guiGraphics, posX, posY, heartData, currentHealth, player.level().getLevelData().isHardcore(), blink);
        RenderSystem.disableBlend();
        profiler.pop();
    }

    public void renderHearts(GuiGraphics graphics, int screenWidth, int screenHeight, HeartData heartData, float currentHealth, boolean hardcore, boolean blinking) {
        int totalHearts = heartData.getTotalHearts();
        if (totalHearts == 0) return;

        int rows = totalHearts <= HEARTS_PER_ROW ? 1 : 2;
        int heartsFirstRow = Math.min(totalHearts, HEARTS_PER_ROW);
        int heartsSecondRow = totalHearts > HEARTS_PER_ROW ? totalHearts - HEARTS_PER_ROW : 0;
        int startX = (screenWidth / 2 - (HEARTS_PER_ROW * (SPRITE_SIZE + HEART_SPACING)) / 2) + 169;
        int startY = screenHeight - (rows * SPRITE_SIZE) + 7;
        float remainingHealth = currentHealth;

        for (int row = 0; row < rows; row++) {
            int heartsThisRow = row == 0 ? heartsFirstRow : heartsSecondRow;

            for (int col = 0; col < heartsThisRow; col++) {
                int heartIndex = row * HEARTS_PER_ROW + col;
                int x = startX + col * (SPRITE_SIZE + HEART_SPACING);
                int y = startY + row * SPRITE_SIZE;
                renderHeart(graphics, x, y, heartIndex, heartData, remainingHealth, hardcore, blinking);
                HeartType type = heartData.getHeartType(heartIndex);
                int heartValue = HeartData.getHeartValue(type);
                remainingHealth -= Math.min(remainingHealth, heartValue);
            }
        }
    }

    private void renderHeart(GuiGraphics graphics, int x, int y, int heartIndex, HeartData heartData, float remainingHealth, boolean hardcore, boolean blinking) {
        HeartType type = heartData.getHeartType(heartIndex);
        int heartValue = HeartData.getHeartValue(type);
        float heartFill = Math.min(remainingHealth, heartValue);
        ResourceLocation textureSheet = CalamosMod.asResource("textures/gui/hud.png");
        graphics.blit(textureSheet, x, y, getHeartX(heartFill, heartValue, blinking), getHeartY(type, hardcore), SPRITE_SIZE, SPRITE_SIZE);
    }

    private static int getHeartX(float fill, int maxValue, boolean blinking) {
        float percentage = fill / maxValue;
        int offset = 0;
        if (percentage <= 0.8) offset = 9;
        if (percentage <= 0.7) offset = 18;
        if (percentage <= 0.6) offset = 27;
        if (percentage <= 0.5) offset = 36;
        if (percentage <= 0.4) offset = 45;
        if (percentage <= 0.3) offset = 54;
        if (percentage <= 0.2) offset = 63;
        if (percentage <= 0.1) offset = 72;
        if (percentage <= 0.0) offset = 81;
        if (blinking) offset += 90;
        return offset;
    }

    private static int getHeartY(HeartType type, boolean hardcore) {
        int offset = 0;
        switch (type) {
            case GOLDEN -> offset = 9;
            case ENLIGHTENED -> offset = 18;
            case STELLATECH -> offset = 27;
        }
        if (hardcore) offset += 36;
        return offset;
    }

    @Override
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        if (Minecraft.getInstance().getCameraEntity() instanceof Player player) {
            int posX = guiGraphics.guiWidth() / 2 - 91;
            int posY = guiGraphics.guiHeight() - Minecraft.getInstance().gui.leftHeight;
            renderPlayerHealth(guiGraphics, posX, posY, player, Minecraft.getInstance().getProfiler());
        }
    }
}
