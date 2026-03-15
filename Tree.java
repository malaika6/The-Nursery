import java.time.LocalDate;
import java.util.HashMap;

/**
 * Represents a tree plant.
 */
public class Tree extends Plant {

    private String growthSpeed;

    /**
     * Creates a Tree object.
     *
     * @param commonName common name
     * @param genusSpecies scientific name
     * @param dateIntroduced date introduced
     * @param zones growing zones
     * @param growthSpeed growth speed
     */
    public Tree(String commonName, String genusSpecies,
                LocalDate dateIntroduced, HashMap<Integer, Zone> zones,
                String growthSpeed) {

        super(commonName, genusSpecies, PlantGroup.GYMNOSPERMS, dateIntroduced, zones);
        this.growthSpeed = growthSpeed;
    }

    public String getGrowthSpeed() {
        return growthSpeed;
    }

    public void setGrowthSpeed(String growthSpeed) {
        this.growthSpeed = growthSpeed;
    }
}