import java.time.LocalDate;
import java.util.HashMap;

/**
 * Represents a flowering plant.
 */
public class FloweringPlant extends Plant {

    private String flowerColors;
    private String features;

    /**
     * Creates a FloweringPlant object.
     *
     * @param commonName common name
     * @param genusSpecies scientific name
     * @param dateIntroduced date introduced
     * @param zones growing zones
     * @param flowerColors flower colors
     * @param features extra features
     */
    public FloweringPlant(String commonName, String genusSpecies,
                          LocalDate dateIntroduced, HashMap<Integer, Zone> zones,
                          String flowerColors, String features) {

        super(commonName, genusSpecies, PlantGroup.ANGIOSPERMS, dateIntroduced, zones);
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