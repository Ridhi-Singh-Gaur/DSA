import java.util.*;

class SieveIJStyle {

    public static boolean[] sieve(int n) {

        boolean[] isPrime = new boolean[n + 1];

        // Step 1: Assume all numbers are prime
        Arrays.fill(isPrime, true);

        // 0 and 1 are not prime
        isPrime[0] = false;
        if (n >= 1) isPrime[1] = false;

        // Step 2: Outer loop till sqrt(n)
        for (int i = 2; i * i <= n; i++) {

            if (isPrime[i]) {

                // Inner loop using i * j (your style)
                for (int j = 2; i * j <= n; j++) {
                    isPrime[i * j] = false;
                }
            }
        }

        return isPrime;
    }

    // ================= TEST =================
    public static void main(String[] args) {
        int n = 50;

        boolean[] primes = sieve(n);

        System.out.print("Primes up to " + n + ": ");
        for (int i = 2; i <= n; i++) {
            if (primes[i]) {
                System.out.print(i + " ");
            }
        }
    }
}
