package net.griphion.modyux.util;

import net.griphion.modyux.mixin.ModMinecraftClient;
import net.minecraft.client.MinecraftClient;

import java.util.concurrent.atomic.AtomicReference;

public class SlowmoManager {
    private static final AtomicReference<Float> customTPS = new AtomicReference<>(20.0F);
    public static IRenderTickCounter rtc = null;
    private static float oldTPS = 20.0F;

    public static void setCustomTPS(float value) {
        customTPS.set(value);
    }

    public static void updateTps() {
        float tps = customTPS.get();
        if (tps != oldTPS) {
            change(tps);
            oldTPS = customTPS.get();
        }
    }

    // Cambia los TPS del renderer del cliente, este método se ejecuta cada tick
    public static void change(float tps) {
        if (rtc == null) {
            ModMinecraftClient client = (ModMinecraftClient) MinecraftClient.getInstance();
            rtc = (IRenderTickCounter) client.getRenderTickCounter();
        }
        if (rtc.getTickTime() != 1000f / tps) {
            rtc.setTickTime(1000f / tps);
        }
    }
}
