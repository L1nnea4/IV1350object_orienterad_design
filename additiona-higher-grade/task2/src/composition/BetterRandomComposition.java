package composition;

import java.util.Random;

/**
 * Adapts the Random class using composition.
 *
 * Instead of extending Random, this class contains
 * a Random object and delegates work to it.
 */
public class BetterRandomComposition {

    private final Random random;

    /**
     * Creates a new adapted Random object.
     */
    public BetterRandomComposition() {
        random = new Random();
    }

    /**
     * Generates a random number between the specified values.
     *
     * @param min Lowest possible value.
     * @param max Highest possible value.
     * @return Random number in the interval [min, max].
     */
    public int nextIntInRange(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Generates a random boolean and returns a text description.
     *
     * @return "YES" or "NO".
     */
    public String randomYesOrNo() {
        if (random.nextBoolean()) {
            return "YES";
        }
        return "NO";
    }
}