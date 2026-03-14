import java.time.LocalDate;
import java.util.HashMap;

/**
 * Flowering plant subclass.
 */
public class FloweringPlant extends Plant {

    private String flowerColors;
    private String features;

    public FloweringPlant(String genusSpecies,
                          String commonName,
                          LocalDate dateIntroduced,
                          HashMap<Integer, Zone> zones,
                          String flowerColors,
                          String features) {

        super(commonName,
                genusSpecies,
                Plant.PlantGroup.ANGIOSPERMS,
                dateIntroduced,
                zones);

        this.flowerColors = flowerColors;
        this.features = features;
    }

    public String getFlowerColors() {
        return flowerColors;
    }

    public void setFlowerColors(String flowerColors) {
        this.flowerColors = flowerColors;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }
}