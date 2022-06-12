package net.griphion.modyux.mixin;

import net.griphion.modyux.util.IRenderTickCounter;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(RenderTickCounter.class)
public class ModRenderTickCounter implements IRenderTickCounter {
    @Final
    @Mutable
    @Shadow
    private float tickTime; // Render TPS (NO afecta al Minecraft Server, es solo del lado del cliente)

    public float getTickTime() {
        return tickTime;
    }

    public void setTickTime(float tickTime) {
        this.tickTime = tickTime;
    }
}
