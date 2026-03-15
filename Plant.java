import java.time.LocalDate;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Stores general information about a plant sold by the nursery.
 */
public class Plant {

    private static long nextId = 4902;

    private long id;
    private String genusSpecies;
    private String commonName;
    public enum PlantGroup {ANGIOSPERMS, GYMNOSPERMS, PTERIDOPHYTES, BRYOPHYTES}
    private PlantGroup plantGroup;
    private LocalDate dateIntroduced;
    private HashMap<Integer, Zone> zones;

    public static LocalDate oldestDate;
    public static LocalDate newestDate;

    public static HashMap<String, Predicate<Plant>> evaluators;

    static {
        evaluators = new HashMap<>();
        evaluators.put("most_experienced", p -> p.getDateIntroduced().equals(oldestDate));
        evaluators.put("least_experienced", p -> p.getDateIntroduced().equals(newestDate));
    }

    /**
     * Creates a Plant and assigns the next sequential id.
     *
     * @param commonName common plant name
     * @param genusSpecies scientific name
     * @param plantGroup plant group
     * @param dateIntroduced date introduced to nursery
     * @param zones zones where plant thrives
     */
    public Plant(String commonName, String genusSpecies,
                 PlantGroup plantGroup, LocalDate dateIntroduced,
                 HashMap<Integer, Zone> zones) {
        this.id = nextId++;
        setCommonName(commonName);
        setGenusSpecies(genusSpecies);
        setPlantGroup(plantGroup);
        setDateIntroduced(dateIntroduced);
        setZones(zones);
    }

    public long getId() {
        return id;
    }

    public String getGenusSpecies() {
        return genusSpecies;
    }

    public void setGenusSpecies(String genusSpecies) {
        validateGenusSpecies(genusSpecies);
        this.genusSpecies = genusSpecies;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        validateCommonName(commonName);
        this.commonName = commonName;
    }

    public PlantGroup getPlantGroup() {
        return plantGroup;
    }

    public void setPlantGroup(PlantGroup plantGroup) {
        this.plantGroup = plantGroup;
    }

    public LocalDate getDateIntroduced() {
        return dateIntroduced;
    }

    public void setDateIntroduced(LocalDate dateIntroduced) {
        this.dateIntroduced = dateIntroduced;
    }

    public HashMap<Integer, Zone> getZones() {
        return zones;
    }

    public void setZones(HashMap<Integer, Zone> zones) {
        if (zones == null) {
            this.zones = new HashMap<>();
        } else {
            this.zones = zones;
        }
    }

    @Override
    public String toString() {
        return commonName + " (" + genusSpecies + ")";
    }

    /**
     * Checks whether a plant grows in a given zone.
     *
     * @param zoneNumber zone number
     * @return true if it grows in that zone, false otherwise
     */
    public boolean growsInZone(int zoneNumber) {
        return zones.containsKey(zoneNumber);
    }

    /**
     * Validates genus/species format.
     *
     * @param genusSpecies scientific name
     */
    public static void validateGenusSpecies(String genusSpecies) {
        if (genusSpecies == null || genusSpecies.isBlank()) {
            throw new IllegalArgumentException("Genus/species cannot be empty.");
        }

        if (genusSpecies.length() < 7 || genusSpecies.length() > 39) {
            throw new IllegalArgumentException("Genus/species must be 7 to 39 characters long.");
        }

        if (!Pattern.matches("^[A-Z][a-z]+ [a-z]+$", genusSpecies)) {
            throw new IllegalArgumentException(
                    "Genus/species must be in the form 'Genus species'. Only genus starts with a capital letter."
            );
        }
    }

    /**
     * Validates common name format.
     *
     * @param commonName common name
     */
    public static void validateCommonName(String commonName) {
        if (commonName == null || commonName.isBlank()) {
            throw new IllegalArgumentException("Common name cannot be empty.");
        }

        if (!Pattern.matches("^[A-Z].*", commonName)) {
            throw new IllegalArgumentException("Common name must start with a capital letter.");
        }
    }
}