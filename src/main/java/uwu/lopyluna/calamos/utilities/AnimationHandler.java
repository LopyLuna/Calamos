package uwu.lopyluna.calamos.utilities;

import dev.kosmx.playerAnim.api.firstPerson.FirstPersonConfiguration;
import dev.kosmx.playerAnim.api.firstPerson.FirstPersonMode;
import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.api.layered.modifier.AbstractFadeModifier;
import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import dev.kosmx.playerAnim.core.util.Ease;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationFactory;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.networking.packets.S2C.AnimationHandlerPacket;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles animations for the player. <br>
 * Author: Zeus
 */

@SuppressWarnings({"all", "unchecked", "unused"})
public class AnimationHandler {
    public static List<String> MOVEMENT_ANIMATIONS = new ArrayList<>();
    public static String currentAnimation = ""; // Only available on the client side.

    /**
     * Called from the server to play the animation for all players.
     *
     * @param player            Player that the animation should be played on.
     * @param animation         Animation string name.
     * @param renderFirstPerson Should the animation be rendered for the first person as well.
     */
    public static void playAnimationServer(Player player, String animation, boolean renderFirstPerson) {
        if (player instanceof ServerPlayer serverPlayer) {
            for (ServerPlayer player1 : serverPlayer.serverLevel().getPlayers((val) -> true)) {
                PacketDistributor.sendToPlayer(player1, new AnimationHandlerPacket(animation, player.getUUID(), renderFirstPerson));
            }
        } else {
            CalamosMod.LOGGER.warn("AnimationHandler.playAnimationServer() called from a client player!");
        }
    }

    /**
     * Plays an animation for a player, only visible from the client it got called from.
     *
     * @param player       Player that the animation should be played on.
     * @param animationKey Animation.
     */
    public static void playAnimationClient(@Nullable Player player, @Nullable KeyframeAnimation animationKey, boolean renderFirstPerson) {
        if (player == null) return;
        ModifierLayer<IAnimation> animation = (ModifierLayer<IAnimation>) PlayerAnimationAccess.getPlayerAssociatedData((AbstractClientPlayer) player).get(CalamosMod.asResource("animation"));
        if (animation != null) {
            if (animationKey == null) {
                animation.setAnimation(null);
                currentAnimation = "null";
            } else {
                KeyframeAnimation keyframeAnimation = animationKey;
                KeyframeAnimation.AnimationBuilder animationBuilder = keyframeAnimation.mutableCopy();

                if (MOVEMENT_ANIMATIONS.contains(getAnimationName(keyframeAnimation))) {
                    animationBuilder.head.pitch.setEnabled(false);
                    animationBuilder.head.yaw.setEnabled(false);
                    animationBuilder.head.roll.setEnabled(false);

                    animationBuilder.leftLeg.x.setEnabled(false);
                    animationBuilder.leftLeg.y.setEnabled(false);
                    animationBuilder.leftLeg.z.setEnabled(false);
                    animationBuilder.leftLeg.pitch.setEnabled(false);
                    animationBuilder.leftLeg.yaw.setEnabled(false);
                    animationBuilder.leftLeg.roll.setEnabled(false);

                    animationBuilder.rightLeg.x.setEnabled(false);
                    animationBuilder.rightLeg.y.setEnabled(false);
                    animationBuilder.rightLeg.z.setEnabled(false);
                    animationBuilder.rightLeg.pitch.setEnabled(false);
                    animationBuilder.rightLeg.yaw.setEnabled(false);
                    animationBuilder.rightLeg.roll.setEnabled(false);
                }

                keyframeAnimation = animationBuilder.build();
                if (renderFirstPerson) {
                    FirstPersonConfiguration firstPersonConfiguration = new FirstPersonConfiguration();
                    firstPersonConfiguration.setShowLeftArm(true);
                    firstPersonConfiguration.setShowRightArm(true);

                    animation.replaceAnimationWithFade(AbstractFadeModifier.standardFadeIn(10, Ease.CONSTANT), new KeyframeAnimationPlayer(keyframeAnimation).setFirstPersonMode(FirstPersonMode.THIRD_PERSON_MODEL).setFirstPersonConfiguration(firstPersonConfiguration));
                } else {
                    animation.replaceAnimationWithFade(AbstractFadeModifier.standardFadeIn(10, Ease.CONSTANT), new KeyframeAnimationPlayer(keyframeAnimation));
                }
                currentAnimation = getAnimationName(animationKey);
            }
        }
    }

    /**
     * Stops animation across all clients.
     *
     * @param player Given player.
     */
    public static void stopCurrentAnimation(Player player) {
        playAnimationServer(player, "null", false);
    }

    // Helper methods.

    public static KeyframeAnimation getKeyframeAnimation(String animationKey) {
        var animation = PlayerAnimationRegistry.getAnimation(CalamosMod.asResource(animationKey));
        return animation instanceof KeyframeAnimation ? (KeyframeAnimation) animation : null;
    }

    public static int getAnimationTime(KeyframeAnimation animation) {
        return animation.stopTick;
    }

    public static String getAnimationName(KeyframeAnimation animation) {
        return animation.extraData.get("name").toString();
    }

    public static boolean animationPlaying(Player player) {
        ModifierLayer<IAnimation> animation = (ModifierLayer<IAnimation>) PlayerAnimationAccess.getPlayerAssociatedData((AbstractClientPlayer) player).get(CalamosMod.asResource("animation"));
        if (animation != null) {
            return animation.isActive();
        }
        return false;
    }

    public static void addMovementAnim(String animation) {
        MOVEMENT_ANIMATIONS.add(animation);
    }


    public static void init() {

    }


    @EventBusSubscriber(modid = CalamosMod.MODID, bus = EventBusSubscriber.Bus.MOD)
    public static class RegisterAnimations {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            PlayerAnimationFactory.ANIMATION_DATA_FACTORY.registerFactory(
                    CalamosMod.asResource("animation"),
                    42,
                    RegisterAnimations::registerPlayerAnimation);
        }

        private static IAnimation registerPlayerAnimation(AbstractClientPlayer player) {
            return new ModifierLayer<>();
        }

    }

}
