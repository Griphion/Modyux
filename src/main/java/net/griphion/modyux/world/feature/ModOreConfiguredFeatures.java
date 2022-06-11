package net.griphion.modyux.world.feature;

import net.griphion.modyux.Modyux;
import net.griphion.modyux.block.ModBlocks;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;

import java.util.List;

public class ModOreConfiguredFeatures {

    //----------------------------------
    //* Ore register ConfiguredFeature *
    //----------------------------------
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORICHALCUM_ORE = ConfiguredFeatures.register(
            "orichalcum_ore", Feature.ORE,
            new OreFeatureConfig(List.of( // Lista de a que bloques va a reemplazar para poder generarse
                    OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, ModBlocks.ORICHALCUM_ORE.getDefaultState()),
                    OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.ORICHALCUM_ORE.getDefaultState())),
                    8)); // Tamaño de la mena

    public static void registerOreConfiguredFeatures() {
        Modyux.LOGGER.info("Registering ore configured features for Modyux");
    }
}
