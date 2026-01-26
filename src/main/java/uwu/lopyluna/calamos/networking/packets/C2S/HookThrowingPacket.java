package uwu.lopyluna.calamos.networking.packets.C2S;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.elements.ModDataComponents;
import uwu.lopyluna.calamos.core.entity.hook.AbstractHookEntity;
import uwu.lopyluna.calamos.core.items.equipment.hook.AbstractHookItem;
import uwu.lopyluna.calamos.utilities.CuriosUtil;

public record HookThrowingPacket(boolean aBool, float x, float y) implements CustomPacketPayload {
    public static final Type<HookThrowingPacket> TYPE = new Type<>(CalamosMod.asResource("hook_throw"));

    public static final StreamCodec<FriendlyByteBuf, HookThrowingPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, HookThrowingPacket::aBool,
            ByteBufCodecs.FLOAT, HookThrowingPacket::x,
            ByteBufCodecs.FLOAT, HookThrowingPacket::y,
            HookThrowingPacket::new
    );

    public void handle(IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            if (player instanceof ServerPlayer serverPlayer) {
                ServerLevel level = serverPlayer.serverLevel();
                CuriosUtil.getSlot(serverPlayer, "hook", 0).ifPresent(stack -> {
                    if (stack.getItem() instanceof AbstractHookItem item && item.canHook(level, stack)) {
                        AbstractHookEntity hook = item.getHook(stack, item, player, level);
                        AbstractHookItem.Hooks hooks = stack.getOrDefault(ModDataComponents.HOOKS, AbstractHookItem.Hooks.EMPTY);
                        if (hooks.hasHooks()) {
                            AbstractHookItem.removeAll(stack, level);
                            AbstractHookItem.add(stack, hook.getId());
                        }
                        if (this.aBool()) {
                            hook.shootFromRotation(player, this.x, this.y, 0.0F, 0.1F, 0.5F);
                            level.addFreshEntity(hook);
                        } else {
                            Entity entity = level.getEntity(hook.getId());
                            if (entity instanceof AbstractHookEntity hookEntity) {
                                hookEntity.setHookState(AbstractHookEntity.HookState.PULL);
                            }
                        }
                    }
                });
            }
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
