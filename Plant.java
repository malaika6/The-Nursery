import java.time.LocalDate;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Plant {

    private static long nextId = 4902;

    private long id;
    private String genusSpecies;
    private String commonName;
    enum PlantGroup {ANGIOSPERMS, GYMNOSPERMS, PTERIDOPHYTES, BRYOPHYTES}
    private PlantGroup plantGroup;
    private LocalDate dateIntroduced;
    private HashMap<Integer, Zone> zones;

    public static HashMap<String, Predicate<Plant>> evaluators = new HashMap<>();

    static {
        evaluators.put("most_experienced",
                p -> p.getDateIntroduced().equals(Driver.oldestDate));

        evaluators.put("least_experienced",
                p -> p.getDateIntroduced().equals(Driver.newestDate));
    }

    public Plant(String commonName, String genusSpecies, PlantGroup plantGroup,
                 LocalDate dateIntroduced, HashMap<Integer, Zone> zones) {
        this.id = nextId;
        nextId++;
        setGenusSpecies(genusSpecies);
        setCommonName(commonName);
        setPlantGroup(plantGroup);
        this.dateIntroduced = dateIntroduced;
        this.zones = zones;
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
        this.zones = zones;
    }

    @Override
    public String toString() {
        return commonName + " (" + genusSpecies + ")";
    }

    public boolean growsInZone(int zoneNumber) {
        return zones.containsKey(zoneNumber);
    }

    public static void validateGenusSpecies(String genusSpecies) {
        if (genusSpecies == null || genusSpecies.isBlank()) {
            throw new IllegalArgumentException("Genus and species name must not be empty.");
        }

        if (genusSpecies.length() < 7 || genusSpecies.length() > 39) {
            throw new IllegalArgumentException("Genus and species name must be between 7 and 39 characters.");
        }

        if (!Pattern.matches("^[A-Z][a-zA-Z-]* [a-zA-Z- ]+$", genusSpecies)) {
            throw new IllegalArgumentException(
                "Genus/species must be like 'Genus species'. Genus starts with one capital letter."
            );
        }
    }

    public static void validateCommonName(String commonName) {
        if (commonName == null || commonName.isBlank()) {
            throw new IllegalArgumentException("Common name cannot be empty.");
        }

        if (!Pattern.matches("^[A-Z][a-zA-Z'\\- ]*$", commonName)) {
            throw new IllegalArgumentException("Common name must start with a capital letter.");
        }
    }
}
    }

    @Override
    public String toString() {
        return commonName + " (" + genusSpecies + ")";
    }
    public boolean growsInZone(int zoneNumber) {
    return zone.getZoneNumber() == zoneNumber;

     }
     public static void validateGenusSpecies(String genusSpecies) {
    if (genusSpecies == null || genusSpecies.isBlank()) {
        throw new IllegalArgumentException("Genus and species name must not be  empty.");
    }

    if (genusSpecies.length() < 7 || genusSpecies.length() > 39) {
        throw new IllegalArgumentException("Genus and species name must be between 7 to 39 characters.");
    }

    if (!Pattern.matches("^[A-Z][a-z]+ [a-z]+$", genusSpecies)) {
        throw new IllegalArgumentException(
            "Genus/species must be like 'Genus species'. Genus starts with one capital letter, species must be lowercase."
        );
    }
}

public static void validateCommonName(String commonName) {
    if (commonName == null || commonName.isBlank()) {
        throw new IllegalArgumentException("Common name cannot be empty.");
    }

    if (!Pattern.matches("^[A-Z].*", commonName)) {
        throw new IllegalArgumentException("Common name must start with a capital letter.");
    }
}
}
