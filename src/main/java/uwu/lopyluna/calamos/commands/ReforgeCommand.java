package uwu.lopyluna.calamos.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.ResourceArgument;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import uwu.lopyluna.calamos.CalamosRegistries;
import uwu.lopyluna.calamos.elements.ModDataComponents;
import uwu.lopyluna.calamos.core.modifier.ItemModifier;
import uwu.lopyluna.calamos.core.modifier.Modifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReforgeCommand {
    private static final DynamicCommandExceptionType ERROR_NOT_LIVING_ENTITY = new DynamicCommandExceptionType((obj) -> Component.translatableEscape("calamos.commands.reforge.failed.entity", obj));
    private static final DynamicCommandExceptionType ERROR_NO_ITEM = new DynamicCommandExceptionType((obj) -> Component.translatableEscape("calamos.commands.reforge.failed.itemless", obj));
    private static final DynamicCommandExceptionType ERROR_INCOMPATIBLE = new DynamicCommandExceptionType((obj) -> Component.translatableEscape("calamos.commands.reforge.failed.incompatible", obj));
    private static final SimpleCommandExceptionType ERROR_NOTHING_HAPPENED = new SimpleCommandExceptionType(Component.translatable("calamos.commands.reforge.failed"));
    private static final DynamicCommandExceptionType ERROR_UNMODIFIABLE = new DynamicCommandExceptionType((obj) -> Component.translatable("calamos.commands.reforge.unmodifiable", obj));

    public ReforgeCommand() {
    }

    public static LiteralArgumentBuilder<CommandSourceStack> register(CommandBuildContext context) {
        return Commands.literal("reforge")
                .requires(cs -> cs.hasPermission(2))
                .then(Commands.argument("targets", EntityArgument.entities())
                        .executes((ctx) -> reforge(ctx.getSource(), EntityArgument.getEntities(ctx, "targets")))
                        .then((Commands.argument("modifier", ResourceArgument.resource(context, CalamosRegistries.MODIFIER))
                                .executes((ctx) -> reforge(ctx.getSource(), EntityArgument.getEntities(ctx, "targets"), ResourceArgument.getResource(ctx, "modifier", CalamosRegistries.MODIFIER))))));
    }

    private static int reforge(CommandSourceStack source, Collection<? extends Entity> targets, Holder<Modifier> p_modifier) throws CommandSyntaxException {
        Modifier modifier = p_modifier.value();
        int i = 0;
        for(Entity entity : targets) {
            if (entity instanceof LivingEntity livingentity) {
                ItemStack itemstack = livingentity.getMainHandItem();
                if (!itemstack.isEmpty()) {
                    if (modifier.supports(itemstack)) {
                        itemstack.set(ModDataComponents.MODIFIER, new ItemModifier(p_modifier, true));
                        ++i;
                    } else if (targets.size() == 1) {
                        throw ERROR_INCOMPATIBLE.create(itemstack.getItem().getName(itemstack).getString());
                    }
                } else if (targets.size() == 1) {
                    throw ERROR_NO_ITEM.create(livingentity.getName().getString());
                }
            } else if (targets.size() == 1) {
                throw ERROR_NOT_LIVING_ENTITY.create(entity.getName().getString());
            }
        }
        if (i == 0) {
            throw ERROR_NOTHING_HAPPENED.create();
        } else {
            if (targets.size() == 1) {
                source.sendSuccess(() -> Component.translatable("calamos.commands.reforge.success.single", modifier.assetName(), targets.iterator().next().getDisplayName()), true);
            } else {
                source.sendSuccess(() -> Component.translatable("calamos.commands.reforge.success.multiple", modifier.assetName(), targets.size()), true);
            }

            return i;
        }
    }

    private static int reforge(CommandSourceStack source, Collection<? extends Entity> targets) throws CommandSyntaxException {
        int i = 0;
        for(Entity entity : targets) {
            if (entity instanceof LivingEntity livingentity) {
                ItemStack itemstack = livingentity.getMainHandItem();
                if (!itemstack.isEmpty()) {
                    Holder<Modifier> p_modifier = getRandomModifier(itemstack, livingentity.getRandom());
                    if (p_modifier != null) {
                        itemstack.set(ModDataComponents.MODIFIER, new ItemModifier(p_modifier, true));
                        ++i;
                    } else if (targets.size() == 1) {
                        throw ERROR_UNMODIFIABLE.create(itemstack.getItem().getName(itemstack).getString());
                    }
                } else if (targets.size() == 1) {
                    throw ERROR_NO_ITEM.create(livingentity.getName().getString());
                }
            } else if (targets.size() == 1) {
                throw ERROR_NOT_LIVING_ENTITY.create(entity.getName().getString());
            }
        }
        if (i == 0) {
            throw ERROR_NOTHING_HAPPENED.create();
        } else {
            if (targets.size() == 1) {
                source.sendSuccess(() -> Component.translatable("calamos.commands.reforge.success.single_random", targets.iterator().next().getDisplayName()), true);
            } else {
                source.sendSuccess(() -> Component.translatable("calamos.commands.reforge.success.multiple_random", targets.size()), true);
            }

            return i;
        }
    }

    private static Holder<Modifier> getRandomModifier(ItemStack itemstack, RandomSource random) {
        List<Holder<Modifier>> modifiers = new ArrayList<>();
        for (var entry : CalamosRegistries.MODIFIER_REGISTRY.holders().toList()) {
            if (entry.value().supports(itemstack)) {
                modifiers.add(entry);
            }
        }
        return modifiers.get(random.nextInt(modifiers.size()));
    }
}
