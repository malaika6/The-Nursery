import java.time.LocalDate;
import java.util.HashMap;

/**
 * Tree subclass of Plant.
 */
public class Tree extends Plant {

    private String growthSpeed;

    public Tree(String genusSpecies, String commonName,
                LocalDate dateIntroduced,
                HashMap<Integer, Zone> zones,
                String growthSpeed) {

        super(commonName, genusSpecies,
                Plant.PlantGroup.GYMNOSPERMS,
                dateIntroduced,
                zones);

        this.growthSpeed = growthSpeed;
    }

    public String getGrowthSpeed() {
        return growthSpeed;
    }

    public void setGrowthSpeed(String growthSpeed) {
        this.growthSpeed = growthSpeed;
    }
}