/*
 * Pair class for storing (int, int) in HashSet / HashMap
 *
 * Why needed?
 * Java does not have a built-in Pair for HashSet, and without overriding
 * equals() and hashCode(), duplicate pairs will be treated as different objects.
 *
 * Key Concepts:
 * 1. hashCode() → decides bucket (where to store)
 * 2. equals()   → checks if two objects are logically equal
 *
 * Rule:
 * If two objects are equal (equals() == true),
 * then their hashCode() must also be equal.
 */

import java.util.*;

class Pair {
    int x, y;

    // Constructor to initialize values
    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Override equals() to define logical equality
    @Override
    public boolean equals(Object o) {
        // Step 1: Same object reference
        if (this == o) return true;

        // Step 2: Check if object is of type Pair
        if (!(o instanceof Pair)) return false;

        // Step 3: Typecast and compare values
        Pair p = (Pair) o;
        return this.x == p.x && this.y == p.y;
    }

    // Override hashCode() to ensure same hash for same (x, y)
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

// Driver code for testing
class Main {
    public static void main(String[] args) {
        HashSet<Pair> set = new HashSet<>();

        set.add(new Pair(1, 2));
        set.add(new Pair(3, 4));
        set.add(new Pair(1, 2)); // Duplicate, will NOT be added

        System.out.println("Size of HashSet: " + set.size()); // Output: 2
    }
}
