import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        // TestCases
        System.out.println("/***** TestCase *****/");
        System.out.println("> After you implement CardGameSimulator, the output should be same.");
        test(5);

        // Test your own inputs
        System.out.println("Enter your own inputs:");
        Scanner sc = new Scanner(System.in);
        int inputNumber = sc.nextInt();
        sc.close();

        FibonacciNumbers.printFibonacciNumbers(inputNumber);
    }

    private static void test(int inputNumber) {
        System.out.println("---------- Input -----------");
        System.out.println(inputNumber);
        System.out.println("---------- Output ----------");
        FibonacciNumbers.printFibonacciNumbers(inputNumber);
        System.out.println("\n----------------------------\n");
    }
}
