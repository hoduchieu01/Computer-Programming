public class FibonacciNumbers {
    public static void printFibonacciNumbers(int n) {
        // DO NOT change the skeleton code.
        // You can add codes anywhere you want.
        int fib1 = 0;
        int fib2 = 1;
        if (n < 1) return; // input (1 <= n <= 40)
        int sum = 0;
        for (int i = 1; i <= n-1; i++) {
            System.out.print(fib1 + " ");
            int nextFib = fib1 + fib2;
            sum += fib1;
            fib1 = fib2;
            fib2 = nextFib;
        }
        // processing no space behind the first line of the output.
        System.out.print(fib1);
        sum += fib1;
        System.out.println();
        if (sum <= 99999) {
            System.out.print("sum = " + sum);
        }
        else {
            // System.out.println("sum = " + sum); Test
            sum = sum % 100000;
            System.out.printf("last five digits of sum = " + "%05d", sum);
        }
    }
}