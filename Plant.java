import java.time.LocalDate;
import java.util.regex.Pattern;
public class Plant {

    private long id;
    private String genusSpecies;
    private String commonName;
    enum PlantGroup {ANGIOSPERMS, GYMNOSPERMS, PTERIDOPHYTES, BRYOPHYTES}
    private LocalDate dateIntroduced;
    private Zone zone;

    public Plant(long id, String commonName, String genusSpecies, LocalDate dateIntroduced, Zone zone) {
        this.id = id;
        setGenusSpecies(genusSpecies);
        setCommonName(commonName);
        this.dateIntroduced = dateIntroduced;
        this.zone = zone;
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

    public LocalDate getDateIntroduced() {
        return dateIntroduced;
    }

    public void setDateIntroduced(LocalDate dateIntroduced) {
        this.dateIntroduced = dateIntroduced;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
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