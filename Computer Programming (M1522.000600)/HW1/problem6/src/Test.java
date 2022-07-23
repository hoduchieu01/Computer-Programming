import java.util.Scanner;

public class Test {
    // DO NOT change anything in this file.
    public static void main(String[] args) {
        // TestCases
        System.out.println("/***** TestCase *****/");
        System.out.println("> After you implementation, the output should be same.");
        test(5, 30);

        // Test your own inputs
        System.out.println("Enter your own inputs:");
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        sc.close();

        PrimeNumbers.printPrimeNumbers(m, n);
    }

    private static void test(int m, int n) {
        System.out.println("---------- Input -----------");
        System.out.println(m + " " + n);
        System.out.println("---------- Output ----------");
        PrimeNumbers.printPrimeNumbers(m, n);
        System.out.println("\n----------------------------\n");
    }
}
