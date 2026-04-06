// ✅ 1. Square Root

// Use:
// Math.sqrt(x)
// Returns double
// Computes √x

// Example:
double result = Math.sqrt(25);
System.out.println(result);  // 5.0


// ✅ 2. Cube Root

// Use:
// Math.cbrt(x)
// Returns double
// Computes ∛x

// Example:
double result2 = Math.cbrt(27);
System.out.println(result2);  // 3.0


// ⚠️ Important Notes
// Both return double, even if input is integer.
// If you want int, you need casting:
int x = (int)Math.sqrt(25);  // 5


// 💡 Extra (General root)

// For any nth root:
// Math.pow(x, 1.0/n)

// Example:
double fourthRoot = Math.pow(16, 1.0/4);  // 2.0
