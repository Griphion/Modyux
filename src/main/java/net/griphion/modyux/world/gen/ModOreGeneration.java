package net.griphion.modyux.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.griphion.modyux.world.feature.ModOrePlacedFeatures;
import net.minecraft.world.gen.GenerationStep;

public class ModOreGeneration {

    /*
    Aqui se agregan los ores a la generación de biomas
     */
    public static void generateOres() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORICHALCUM_ORE.getKey().get());
    }
}
