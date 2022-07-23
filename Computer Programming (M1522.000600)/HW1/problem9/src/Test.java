import java.util.Scanner;

public class Test {
    // DO NOT change anything in this file.
    public static void main(String[] args) {
        // TestCases
        System.out.println("/***** TestCase *****/");
        System.out.println("> After you implementation, the output should be same.");
        test("2/3 + 3/4");
        test("6/9 - 2");
        test("1/2 + 1/2");

        // Test your own inputs
        System.out.println("Enter your own inputs:");
        Scanner sc = new Scanner(System.in);
        String inputString = sc.nextLine();
        sc.close();

        FractionalNumberCalculator.printCalculationResult(inputString);
    }

    private static void test(String inputString) {
        System.out.println("---------- Input -----------");
        System.out.println(inputString);
        System.out.println("---------- Output ----------");
        FractionalNumberCalculator.printCalculationResult(inputString);
        System.out.println("----------------------------\n");
    }
}
