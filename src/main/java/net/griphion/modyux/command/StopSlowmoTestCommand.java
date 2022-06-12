package net.griphion.modyux.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.griphion.modyux.util.SlowmoManager;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class StopSlowmoTestCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(CommandManager.literal("stopslowmo").executes(StopSlowmoTestCommand::run));
    }

    private static int run(CommandContext<ServerCommandSource> context) {
        SlowmoManager.setCustomTPS(20f);
        return 1;
    }
}
