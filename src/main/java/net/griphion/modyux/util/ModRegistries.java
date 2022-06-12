package net.griphion.modyux.util;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.griphion.modyux.command.HomeCommand;
import net.griphion.modyux.command.SetHomeCommand;
import net.griphion.modyux.command.SlowmoTestCommand;
import net.griphion.modyux.command.StopSlowmoTestCommand;

public class ModRegistries {
    public static void registerExtraModStuff() {
        registerCommands();
    }

    private static void registerCommands() {
        CommandRegistrationCallback.EVENT.register(SetHomeCommand::register);
        CommandRegistrationCallback.EVENT.register(HomeCommand::register);
        CommandRegistrationCallback.EVENT.register(SlowmoTestCommand::register);
        CommandRegistrationCallback.EVENT.register(StopSlowmoTestCommand::register);
    }
}
