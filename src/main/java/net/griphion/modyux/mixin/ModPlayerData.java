package net.griphion.modyux.mixin;

import net.griphion.modyux.util.IPlayerData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class ModPlayerData implements IPlayerData {
    //Inyecto datos
    private NbtCompound data;

    // Inyecto al método writeCustomDataToNbt para que persista los datos
    @Inject(at = @At("HEAD"), method = "writeCustomDataToNbt") // At Head para que lo inyecte al principio del método
    protected void writeData(NbtCompound nbt, CallbackInfo ci) {
        if(data != null)
            nbt.put("modyux.data", data);
    }

    // Inyecto al método readCustomDataFromNbt para obtener los datos
    @Inject(at = @At("HEAD"), method = "readCustomDataFromNbt")
    protected void readData(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("modyux.data", 10))
            data = nbt.getCompound("modyux.data");
    }

    @Override
    public NbtCompound getData() {
        if(this.data == null) {
            this.data = new NbtCompound();
        }
        return data;
    }
}
