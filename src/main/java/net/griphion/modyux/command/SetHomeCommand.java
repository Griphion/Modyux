package net.griphion.modyux.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.griphion.modyux.util.IPlayerData;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class SetHomeCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(CommandManager.literal("sethome").executes(SetHomeCommand::run));
    }

    private static int run(CommandContext<ServerCommandSource> context) {
        ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
        if(serverPlayer == null) return -1;

        int[] playerLocationArr = new int[] {
                serverPlayer.getBlockX(),
                serverPlayer.getBlockY(),
                serverPlayer.getBlockZ()
        };

        // Utilizando la interfaz que hice para mixin y asigno el NBT
        var data = ((IPlayerData)serverPlayer).getData();

        data.putIntArray("HomeLocation", playerLocationArr);
        data.putString("HomeLocationWorldId", serverPlayer.getWorld().getRegistryKey().getValue().toString()); // Lo guardo para saber en que dimension esta el home

        context.getSource().sendFeedback(Text.of("Home set successfully"),false);
        return 1;
    }
}
