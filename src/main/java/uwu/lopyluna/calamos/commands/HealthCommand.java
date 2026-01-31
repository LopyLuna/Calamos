package uwu.lopyluna.calamos.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.server.command.EnumArgument;
import uwu.lopyluna.calamos.core.systems.health.HeartData;
import uwu.lopyluna.calamos.core.systems.health.HeartType;
import uwu.lopyluna.calamos.elements.ModAttachmentTypes;

import java.util.Collection;

public class HealthCommand {

    public static LiteralArgumentBuilder<CommandSourceStack> register() {
        return Commands.literal("health")
                .requires(cs -> cs.hasPermission(2))
                .then(Commands.argument("targets", EntityArgument.players())
                        .then((Commands.argument("heart", EnumArgument.enumArgument(HeartType.class))
                                .then(Commands.argument("operation", EnumArgument.enumArgument(Operation.class))
                                .executes((ctx) -> alterHearts(ctx.getSource(), EntityArgument.getPlayers(ctx, "targets"), ctx.getArgument("heart", HeartType.class), ctx.getArgument("operation", Operation.class), 1))
                                .then(Commands.argument("amount", IntegerArgumentType.integer(1))
                                .executes((ctx) -> alterHearts(ctx.getSource(), EntityArgument.getPlayers(ctx, "targets"), ctx.getArgument("heart", HeartType.class), ctx.getArgument("operation", Operation.class), IntegerArgumentType.getInteger(ctx, "amount"))))))));
    }

    private static int alterHearts(CommandSourceStack source, Collection<ServerPlayer> targets, HeartType heart, Operation operation, int amount) {
        for(ServerPlayer target : targets) {
            HeartData heartData = target.getData(ModAttachmentTypes.HEARTS);
            if (heart == HeartType.DEFAULT) {
                switch (operation) {
                    case ADD: {
                        for (int i = 1; i < amount; i++) {
                            if (heartData.canAddDefaultHeart()) {
                                heartData.tryAddDefaultHeart();
                            } else break;
                        }
                        break;
                    }
                    case SUBTRACT: {
                        for (int i = 1; i < amount; i++) {
                            if (heartData.canRemoveDefaultHeart()) {
                                heartData.tryRemoveDefaultHeart();
                            } else break;
                        }
                        break;
                    }
                }
            } else switch (operation) {
                case ADD: {
                    for (int i = 1; i < amount; i++) {
                        if (heartData.canUpgrade(heart)) {
                            heartData.tryUpgrade(heart);
                        } else break;
                    }
                    break;
                }
                case SUBTRACT: {
                    for (int i = 1; i < amount; i++) {
                        if (heartData.canDowngrade(heart)) {
                            heartData.tryDowngrade(heart);
                        } else break;
                    }
                    break;
                }
            }
        }
        return 0;
    }


    private enum Operation {
        ADD,SUBTRACT
    }
}
