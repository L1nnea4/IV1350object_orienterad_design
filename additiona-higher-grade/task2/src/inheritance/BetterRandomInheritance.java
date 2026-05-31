package inheritance;

import java.util.Random;

/**
 * Adapts the Random class using inheritance.
 *
 * This class extends Random and adds a new method
 * that generates a random number inside a specified range.
 */

// ska ta bort denna fil och hela addtional giher grade mapp
//  la additional higher grade filer i samma src som repair electric bike uppgift
public class BetterRandomInheritance extends Random {

    /**
     * Generates a random number between the specified values.
     *
     * @param min Lowest possible value.
     * @param max Highest possible value.
     * @return Random number in the interval [min, max].
     */
    public int nextIntInRange(int min, int max) {
        return nextInt(max - min + 1) + min;
    }

    /**
     * Generates a random boolean and returns a text description.
     *
     * @return "YES" or "NO".
     */
    public String randomYesOrNo() {
        if (nextBoolean()) {
            return "YES";
        }
        return "NO";
    }
}