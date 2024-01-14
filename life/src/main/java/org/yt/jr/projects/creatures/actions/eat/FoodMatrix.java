package org.yt.jr.projects.creatures.actions.eat;

import org.yt.jr.projects.creatures.Creature;
import org.yt.jr.projects.creatures.CreatureType;
import org.yt.jr.projects.utils.Config;

import java.util.HashMap;
import java.util.Map;

public class FoodMatrix {

    final public static FoodMatrix FOOD_MATRIX = new FoodMatrix();

    private Map<CreatureType, Map<CreatureType, Double>> foodMatrix = new HashMap();

    public void initFoodMatrix() {
        for (CreatureType eaterType : CreatureType.values()) {
            for (CreatureType foodType : CreatureType.values()) {
                if (eaterType == CreatureType.NONEXISTENT ||
                        foodType == CreatureType.NONEXISTENT) {
                    continue;
                }
                setProbability(eaterType, foodType, Config.CONFIG.getFoodMatrixValue(eaterType, foodType));
            }
        }
    }

    public Double getProbability(final CreatureType eaterType, final CreatureType foodType) {
        Map<CreatureType, Double> eaterMap = foodMatrix.get(eaterType);
        if (eaterMap != null) {
            Double probability;
            probability = eaterMap.get(foodType);
            if (probability != null) {
                return probability;
            }
        }
        return 0D;
    }

    public void setProbability(final CreatureType eaterType, final CreatureType foodType, final Double probability) {
        Map<CreatureType, Double> eaterMap = foodMatrix.get(eaterType);
        if (eaterMap == null) {
            eaterMap = new HashMap<>();
            foodMatrix.put(eaterType, eaterMap);
        }
        eaterMap.put(foodType, probability);
    }
}