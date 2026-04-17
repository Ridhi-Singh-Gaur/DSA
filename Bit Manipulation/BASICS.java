//📌 Decimal to Binary Conversion
//🔹 Logic

//To convert a decimal number to binary:

//Divide the number by 2
//Store the remainder (0 or 1)
//Repeat until the number becomes 0
//Reverse the remainders → that’s your binary number
// Decimal to Binary Conversion
    public static String convertToBinary(int n) {
        // Edge case
        if (n == 0) return "0";

        StringBuilder binary = new StringBuilder();

        // Repeated division by 2
        while (n > 0) {
            int remainder = n % 2;
            binary.append(remainder);
            n = n / 2;
        }

        // Reverse to get correct order
        return binary.reverse().toString();
    }

}
