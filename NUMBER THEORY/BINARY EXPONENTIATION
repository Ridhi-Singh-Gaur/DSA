class PowerFunctions {

    // ================= RECURSIVE =================
    // Binary Exponentiation (Optimized Recursive)
    // Time Complexity: O(log b)
    // Reason: Har step mein exponent half ho raha hai → b → b/2 → b/4 ...

    long powerRecursive(long a, long b) {
        if (b == 0) {
            return 1;
        }
        // IMPORTANT: ek hi baar recursive call karna (optimization)
        long half = powerRecursive(a, b / 2);
        long ans = half * half;
        // Agar exponent odd hai
        if (b % 2 == 1) {
            ans *= a;
        }
        return ans;
    }


    // ================= ITERATIVE =================
    // Binary Exponentiation (Iterative)
    // Time Complexity: O(log b)
    // Reason: b ke bits ke according loop chalta hai (har step mein b right shift hota hai)

    long powerIterative(long a, long b) {
        long res = 1;

        while (b > 0) {

            // Agar last bit 1 hai (odd exponent)
            if ((b & 1) == 1) {
                res *= a;
            }

            // Square the base
            a *= a;

            // Right shift (divide by 2)
            b >>= 1;
        }

        return res;
    }
}


//MOST USED CP VERSION IN WHICH MOD IS USED:
long power(long a, long b, long mod) {
    long res = 1;
    a %= mod;

    while (b > 0) {
        if ((b & 1) == 1) {
            res = (res * a) % mod;
        }
        a = (a * a) % mod;
        b >>= 1;
    }
    return res;
}
