package startup;

import composition.BetterRandomComposition;
import inheritance.BetterRandomInheritance;

/**
 * Demonstrates adaptation of java.util.Random
 * using both inheritance and composition.
 */
public class Main {

    /**
     * Runs the demonstration program.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {

        System.out.println("=== INHERITANCE ADAPTATION ===");

        BetterRandomInheritance inheritanceRandom =
                new BetterRandomInheritance();

        System.out.println(
                "Random number between 1 and 10: "
                + inheritanceRandom.nextIntInRange(1, 10));

        System.out.println(
                "Random answer: "
                + inheritanceRandom.randomYesOrNo());



        System.out.println();
        System.out.println("=== COMPOSITION ADAPTATION ===");

        BetterRandomComposition compositionRandom =
                new BetterRandomComposition();

        System.out.println(
                "Random number between 1 and 10: "
                + compositionRandom.nextIntInRange(1, 10));

        System.out.println(
                "Random answer: "
                + compositionRandom.randomYesOrNo());
    }
}