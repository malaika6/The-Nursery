import java.util.HashMap;

public class Zone {

    private int zoneNumber;
    private String temperatureRange;
    public static HashMap<Integer, Zone> zones = new HashMap<>();

    public Zone(int zoneNumber, String temperatureRange) {
        this.zoneNumber = zoneNumber;
        this.temperatureRange = temperatureRange;
    }

    static {
        zones.put(1, new Zone(1, "below -50°F"));
        zones.put(2, new Zone(2, "-50 to -40°F"));
        zones.put(3, new Zone(3, "-40 to -30°F"));
        zones.put(4, new Zone(4, "-30 to -20°F"));
        zones.put(5, new Zone(5, "-20 to -10°F"));
        zones.put(6, new Zone(6, "-10 to 0°F"));
        zones.put(7, new Zone(7, "0 to 10°F"));
        zones.put(8, new Zone(8, "10 to 20°F"));
        zones.put(9, new Zone(9, "20 to 30°F"));
        zones.put(10, new Zone(10, "30 to 40°F"));
        zones.put(11, new Zone(11, "40 to 50°F"));
    }

    public int getZoneNumber() {
        return zoneNumber;
    }

    public void setZoneNumber(int zoneNumber) {
        this.zoneNumber = zoneNumber;
    }

    public String getTemperatureRange() {
        return temperatureRange;
    }

    public void setTemperatureRange(String temperatureRange) {
        this.temperatureRange = temperatureRange;
    }
}