package net.griphion.modyux.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.griphion.modyux.util.IPlayerData;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

public class HomeCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(CommandManager.literal("home").executes(HomeCommand::run));
    }

    private static int run(CommandContext<ServerCommandSource> context) {
        ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
        if(serverPlayer == null) return -1;

         // Utilizando la interfaz que hice para mixin y obtengo el NBT almacenado
        NbtCompound data = ((IPlayerData)serverPlayer).getData();
        int[] homeLocation = data.getIntArray("HomeLocation"); // Coordenadas X Y Z
        ServerWorld world = getServerWorldFromData(context,data);

        if(homeLocation.length != 0 && world != null) {
            // Se puede hacer más preciso si se utiliza doubles y también se puede agregar el Yaw y Pitch al NBT
            serverPlayer.teleport(world, homeLocation[0], homeLocation[1], homeLocation[2],0,0);
            context.getSource().sendFeedback(Text.of("You have returned home!"), false);
            return 1;
        }
        else {
            context.getSource().sendFeedback(Text.of("You haven't set a home yet! (Use /sethome)"), false);
            return -1;
        }
    }

    private static ServerWorld getServerWorldFromData(CommandContext<ServerCommandSource> context, NbtCompound data){
        String id = data.getString("HomeLocationWorldId");
        // Nota: No probe si esto funciona con mundos de otros mods ni tampoco con multiples mundos vanilla
        return context.getSource().getServer().getWorld(RegistryKey.of(Registry.WORLD_KEY, Identifier.tryParse(id)));
    }
}
