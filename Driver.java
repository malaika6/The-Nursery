import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * Runs the nursery program.
 */
public class Driver {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Plant> plants = new ArrayList<>();

        int userZone = promptForZone(sc);
        String evaluatorChoice = promptForEvaluator(sc);

        addPlant(plants, makeTree1());
        addPlant(plants, makeTree2());
        addPlant(plants, makeFloweringPlant1());
        addPlant(plants, makeFloweringPlant2());
        addPlant(plants, makePlant(sc));

        System.out.println();
        System.out.println("--------------- Results -------------");
        System.out.println();

        displayPlants(plants, evaluatorChoice, userZone);

        sc.close();
    }

    /**
     * Prompts the user for zone number.
     *
     * @param sc scanner
     * @return valid zone number
     */
    private static int promptForZone(Scanner sc) {
        while (true) {
            try {
                System.out.println("What zone are you currently in?");
                int zone = Integer.parseInt(sc.nextLine());

                if (zone >= 1 && zone <= 11) {
                    System.out.println();
                    return zone;
                }

                System.out.println("Invalid zone. Enter a number from 1 to 11.");
            } catch (Exception e) {
                System.out.println("Invalid zone. Try again.");
            }
        }
    }

    /**
     * Prompts the user for evaluation type.
     *
     * @param sc scanner
     * @return evaluator key
     */
    private static String promptForEvaluator(Scanner sc) {
        while (true) {
            System.out.println("How should we evaluate nursery experience with plant?  [Enter 'least' or 'most']");
            String choice = sc.nextLine().trim().toLowerCase();

            if (choice.equals("least")) {
                System.out.println();
                return "least_experienced";
            }
            if (choice.equals("most")) {
                System.out.println();
                return "most_experienced";
            }

            System.out.println("Invalid choice. Enter 'least' or 'most'.");
        }
    }

    /**
     * Prompts the user for a plain Plant object.
     *
     * @param sc scanner
     * @return Plant object
     */
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
                System.out.println(e.getMessage());
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
                System.out.println(e.getMessage());
            }
        }

        System.out.println();

        while (true) {
            try {
                System.out.println("Enter when the plant was first introduced to the nursery [YYYY-MM-DD]");
                dateIntroduced = LocalDate.parse(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Invalid date. Try again.");
            }
        }

        return new Plant(
                commonName,
                genusSpecies,
                null,
                dateIntroduced,
                new HashMap<>()
        );
    }

    /**
     * Creates Bloodgood Japanese Maple.
     *
     * @return Tree object
     */
    private static Tree makeTree1() {
        HashMap<Integer, Zone> zones = new HashMap<>();
        zones.put(6, Zone.zones.get(6));
        zones.put(7, Zone.zones.get(7));
        zones.put(8, Zone.zones.get(8));

        return new Tree(
                "Bloodgood Japanese Maple",
                "Acer palmatum",
                LocalDate.of(2016, 1, 2),
                zones,
                "fast"
        );
    }

    /**
     * Creates Hemlock Tree.
     *
     * @return Tree object
     */
    private static Tree makeTree2() {
        HashMap<Integer, Zone> zones = new HashMap<>();
        zones.put(3, Zone.zones.get(3));
        zones.put(4, Zone.zones.get(4));
        zones.put(5, Zone.zones.get(5));
        zones.put(6, Zone.zones.get(6));
        zones.put(7, Zone.zones.get(7));

        return new Tree(
                "Hemlock Tree",
                "Tsuga canadensis",
                LocalDate.of(2003, 1, 23),
                zones,
                "slow"
        );
    }

    /**
     * Creates Orange Jasmine.
     *
     * @return FloweringPlant object
     */
    private static FloweringPlant makeFloweringPlant1() {
        HashMap<Integer, Zone> zones = new HashMap<>();
        zones.put(9, Zone.zones.get(9));

        return new FloweringPlant(
                "Orange Jasmine",
                "Murraya paniculata",
                LocalDate.of(2007, 3, 2),
                zones,
                "white",
                "orange-scented"
        );
    }

    /**
     * Creates Lily-of-the-Valley.
     *
     * @return FloweringPlant object
     */
    private static FloweringPlant makeFloweringPlant2() {
        HashMap<Integer, Zone> zones = new HashMap<>();
        zones.put(5, Zone.zones.get(5));
        zones.put(6, Zone.zones.get(6));
        zones.put(7, Zone.zones.get(7));
        zones.put(8, Zone.zones.get(8));

        return new FloweringPlant(
                "Lily-of-the-Valley",
                "Convallaria majalis",
                LocalDate.of(2011, 10, 5),
                zones,
                "white, pink or red",
                "delicate, fragrant"
        );
    }

    /**
     * Adds plant and updates oldest/newest dates.
     *
     * @param plants plant collection
     * @param p plant to add
     */
    private static void addPlant(ArrayList<Plant> plants, Plant p) {
        plants.add(p);

        if (Plant.oldestDate == null || p.getDateIntroduced().isBefore(Plant.oldestDate)) {
            Plant.oldestDate = p.getDateIntroduced();
        }

        if (Plant.newestDate == null || p.getDateIntroduced().isAfter(Plant.newestDate)) {
            Plant.newestDate = p.getDateIntroduced();
        }
    }

    /**
     * Displays all plants in the collection.
     *
     * @param plants plant collection
     * @param choice evaluator key
     * @param userZone user zone
     */
    private static void displayPlants(ArrayList<Plant> plants, String choice, int userZone) {
        Predicate<Plant> test = Plant.evaluators.get(choice);

        for (Plant p : plants) {
            System.out.println(p.getId());
            System.out.println(p);
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

            if (choice.equals("least_experienced")) {
                System.out.println("least experience: " + test.test(p));
            } else {
                System.out.println("most experience: " + test.test(p));
            }

            System.out.println("good for your zone: " + p.growsInZone(userZone));
            System.out.println();
        }
    }
}