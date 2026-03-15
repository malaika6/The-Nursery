import java.util.HashMap;

/**
 * Represents a plant hardiness zone.
 */
public class Zone {

    private int zoneNumber;
    private int lowTemp;
    private int highTemp;

    public static final HashMap<Integer, Zone> zones = new HashMap<>();

    /**
     * Private constructor so zones can only be created inside this class.
     *
     * @param zoneNumber zone number
     * @param lowTemp low temperature
     * @param highTemp high temperature
     */
    private Zone(int zoneNumber, int lowTemp, int highTemp) {
        this.zoneNumber = zoneNumber;
        this.lowTemp = lowTemp;
        this.highTemp = highTemp;
    }

    static {
        zones.put(1, new Zone(1, Integer.MIN_VALUE, -50));
        zones.put(2, new Zone(2, -50, -40));
        zones.put(3, new Zone(3, -40, -30));
        zones.put(4, new Zone(4, -30, -20));
        zones.put(5, new Zone(5, -20, -10));
        zones.put(6, new Zone(6, -10, 0));
        zones.put(7, new Zone(7, 0, 10));
        zones.put(8, new Zone(8, 10, 20));
        zones.put(9, new Zone(9, 20, 30));
        zones.put(10, new Zone(10, 30, 40));
        zones.put(11, new Zone(11, 40, 50));
    }

    public int getZoneNumber() {
        return zoneNumber;
    }

    public int getLowTemp() {
        return lowTemp;
    }

    public int getHighTemp() {
        return highTemp;
    }

    public String getTemperatureRange() {
        if (zoneNumber == 1) {
            return "Below -50°F";
        }
        return lowTemp + " to " + highTemp + "°F";
    }
}