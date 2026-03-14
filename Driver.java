import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * Runs the nursery program.
 */
public class Driver {

    public static LocalDate oldestDate;
    public static LocalDate newestDate;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Plant> plants = new ArrayList<>();

        System.out.println("What zone are you currently in?");
        int userZone = Integer.parseInt(sc.nextLine());

        System.out.println();

        System.out.println("How should we evaluate nursery experience with plant?  [Enter 'least' or 'most']");
        String choice = sc.nextLine().trim().toLowerCase();

        String evaluatorChoice;
        if (choice.equals("least")) {
            evaluatorChoice = "least_experienced";
        } else {
            evaluatorChoice = "most_experienced";
        }

        System.out.println();

        plants.add(makeTree1());
        plants.add(makeTree2());
        plants.add(makeFloweringPlant1());
        plants.add(makeFloweringPlant2());

        Plant plasticPlant = makePlant(sc);
        plants.add(plasticPlant);

        findDates(plants);

        System.out.println();
        System.out.println("--------------- Results -------------");
        System.out.println();

        displayPlants(plants, evaluatorChoice, userZone);

        sc.close();
    }

    private static Plant makePlant(Scanner sc) {

        String commonName;
        String genusSpecies;
        LocalDate dateIntroduced;

        while (true) {
            try {

                System.out.println("Enter the common name of the plant");
                commonName = sc.nextLine();

                Plant.validateCommonName(commonName);

                break;

            } catch (Exception e) {
                System.out.println("Invalid name. Try again.");
            }
        }

        System.out.println();

        while (true) {
            try {

                System.out.println("Enter the scientific name of the plant (make one up!)");
                genusSpecies = sc.nextLine();

                Plant.validateGenusSpecies(genusSpecies);

                break;

            } catch (Exception e) {
                System.out.println("Invalid genus/species. Try again.");
            }
        }

        System.out.println();

        while (true) {
            try {

                System.out.println("Enter when the plant was first introduced to the nursery [YYYY-MM-DD]");
                dateIntroduced = LocalDate.parse(sc.nextLine());

                break;

            } catch (Exception e) {
                System.out.println("Invalid date.");
            }
        }

        return new Plant(
                commonName,
                genusSpecies,
                null,
                dateIntroduced,
                makePlasticPlantZones()
        );
    }

    private static HashMap<Integer, Zone> makePlasticPlantZones() {

        HashMap<Integer, Zone> zones = new HashMap<>();

        zones.put(7, Zone.zones.get(7));
        zones.put(8, Zone.zones.get(8));

        return zones;
    }

    private static Tree makeTree1() {

        HashMap<Integer, Zone> zones = new HashMap<>();

        zones.put(6, Zone.zones.get(6));
        zones.put(7, Zone.zones.get(7));
        zones.put(8, Zone.zones.get(8));

        return new Tree(
                "Acer palmatum",
                "Bloodgood Japanese Maple",
                LocalDate.of(2016, 1, 2),
                zones,
                "fast"
        );
    }

    private static Tree makeTree2() {

        HashMap<Integer, Zone> zones = new HashMap<>();

        zones.put(3, Zone.zones.get(3));
        zones.put(4, Zone.zones.get(4));
        zones.put(5, Zone.zones.get(5));
        zones.put(6, Zone.zones.get(6));

        return new Tree(
                "Tsuga canadensis",
                "Hemlock Tree",
                LocalDate.of(2003, 1, 23),
                zones,
                "slow"
        );
    }

    private static FloweringPlant makeFloweringPlant1() {

        HashMap<Integer, Zone> zones = new HashMap<>();

        zones.put(9, Zone.zones.get(9));

        return new FloweringPlant(
                "Murraya paniculata",
                "Orange Jasmine",
                LocalDate.of(2007, 3, 2),
                zones,
                "white",
                "orange-scented"
        );
    }

    private static FloweringPlant makeFloweringPlant2() {

        HashMap<Integer, Zone> zones = new HashMap<>();

        zones.put(5, Zone.zones.get(5));
        zones.put(6, Zone.zones.get(6));
        zones.put(7, Zone.zones.get(7));
        zones.put(8, Zone.zones.get(8));

        return new FloweringPlant(
                "Convallaria majalis",
                "Lily-of-the-Valley",
                LocalDate.of(2011, 10, 5),
                zones,
                "white, pink or red",
                "delicate, fragrant"
        );
    }

    private static void findDates(ArrayList<Plant> plants) {

        oldestDate = plants.get(0).getDateIntroduced();
        newestDate = plants.get(0).getDateIntroduced();

        for (Plant p : plants) {

            if (p.getDateIntroduced().isBefore(oldestDate)) {
                oldestDate = p.getDateIntroduced();
            }

            if (p.getDateIntroduced().isAfter(newestDate)) {
                newestDate = p.getDateIntroduced();
            }
        }
    }

    private static void displayPlants(ArrayList<Plant> plants, String choice, int userZone) {

        Predicate<Plant> test = Plant.evaluators.get(choice);

        for (Plant p : plants) {

            System.out.println(p.getId());
            System.out.println(p.toString());
            System.out.println(p.getClass().getSimpleName());
            System.out.println("introduced on " + p.getDateIntroduced());

            if (p instanceof Tree) {
                Tree t = (Tree) p;
                System.out.println("a " + t.getGrowthSpeed() + " growing tree");
            }

            if (p instanceof FloweringPlant) {
                FloweringPlant f = (FloweringPlant) p;
                System.out.println(f.getFeatures() + " plant with " + f.getFlowerColors() + " colors");
            }

            if (test != null) {

                if (choice.equals("least_experienced")) {
                    System.out.println("least experience: " + test.test(p));
                } else {
                    System.out.println("most experience: " + test.test(p));
                }
            }

            System.out.println("good for your zone: " + p.growsInZone(userZone));
            System.out.println();
        }
    }
}