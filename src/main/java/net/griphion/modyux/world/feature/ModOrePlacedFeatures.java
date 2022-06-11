package net.griphion.modyux.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class ModOrePlacedFeatures {

    //------------------------------
    //* Ore register PlacedFeature *
    //------------------------------

    public static final RegistryEntry<PlacedFeature> ORICHALCUM_ORE =
            PlacedFeatures.register("orichalcum_ore", ModOreConfiguredFeatures.ORICHALCUM_ORE,
                    ModOrePlacedFeatures.modifiersWithCount(8,
                            HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(50))));


    //--------------------
    //* Modifier methods *
    //--------------------

    public static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    /**
     * @param count Cantidad de menas por Chunk
     * @param heightModifier Vea funcionamiento de PlacementModifier
     */
    public static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }

    /**
     * @param chance Probabilidad de que se genere el ore por Chunk
     * @param heightModifier Vea funcionamiento de PlacementModifier
     */
    public static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
    }
}
