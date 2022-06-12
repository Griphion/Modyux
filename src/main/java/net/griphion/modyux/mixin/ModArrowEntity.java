package net.griphion.modyux.mixin;

import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArrowEntity.class)
public abstract class ModArrowEntity {
    Vec3d playerPos;
    boolean track = true;
    boolean animationStopped = false;

    @Inject(at = @At("HEAD"), method = "initFromStack")
    public void initFromStack(CallbackInfo ci){
        ArrowEntity arrow = (ArrowEntity) (Object) this;
        if(arrow.getOwner() instanceof ServerPlayerEntity player){
            initAnimation(player);
        }
    }

    @Inject(at = @At("HEAD"), method = "tick")
    public void tick(CallbackInfo ci){
        ArrowEntity arrow = (ArrowEntity) (Object) this;
        if(arrow.getOwner() instanceof ServerPlayerEntity player){
            if(arrow.shake > 0) track = false;
            if(track) {
                followArrowAnimation(player,arrow);
            } else if (!animationStopped){
                stopAnimation(player);
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "onHit")
    public void onHit(CallbackInfo ci){
        ArrowEntity arrow = (ArrowEntity) (Object) this;
        if(arrow.getOwner() instanceof ServerPlayerEntity player){
            track = false;
            stopAnimation(player);
        }
    }

    private void initAnimation(ServerPlayerEntity player) {
        player.changeGameMode(GameMode.SPECTATOR); //TODO Ver si se puede eliminar la cabeza del modo spectator.
        player.setInvisible(true);
        playerPos = player.getPos();
    }

    private void stopAnimation(ServerPlayerEntity player) {
        animationStopped = true;
        player.changeGameMode(player.interactionManager.getPreviousGameMode());
        player.setInvisible(false);
        player.teleport(playerPos.x,playerPos.y,playerPos.z);
    }

    private void followArrowAnimation(ServerPlayerEntity player, ArrowEntity arrow) {
        player.teleport(arrow.getX(),arrow.getY(),arrow.getZ());
    }
}
