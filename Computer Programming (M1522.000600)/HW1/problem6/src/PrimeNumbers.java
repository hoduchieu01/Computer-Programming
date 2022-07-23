public class PrimeNumbers {
    static boolean isPrime(int input){
        if (input <= 1)
            return false;

        for (int i = 2; i <= Math.sqrt(input); i ++){
            if (input % i == 0)
                return false;
        }
        return true;
    }
    public static void printPrimeNumbers(int m, int n) {
        // DO NOT change the skeleton code.
        // You can add codes anywhere you want.
        for (int i = m; i <= n; i++){
            if (isPrime(i)) {
                System.out.print(i + " ");
            }
        }
    }
}
