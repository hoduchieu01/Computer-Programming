import java.util.Scanner;

public class Test {
    // DO NOT change anything in this file.
    public static void main(String[] args) {
        // TestCases
        System.out.println("> After you implementation, the output should be same.");
        test("0O 3X 4O 4X 7X 1O 6X 5O 6O 9X", "2X 2O 3O 9O 5X 1X 8O 7O 8X 0X");
        test("9X 3O 1X 1O 8O 3X 0O 0X 5O 6X", "6O 2O 5X 9O 8X 2X 4X 4O 7O 7X");
        test("0O 1O 2O 3O 4O 5O 6O 7O 8O 9O", "0X 1X 2X 3X 4X 5X 6X 7X 8X 9X");

        // Test your own inputs
        System.out.println("Enter our inputs:");
        Scanner sc = new Scanner(System.in);
        String inputA = sc.nextLine(), inputB = sc.nextLine();
        sc.close();

        CardGameSimulator.simulateCardGame(inputA, inputB);
    }

    private static void test(String inputA, String inputB) {
        System.out.println("----- TestCase Output -----");
        CardGameSimulator.simulateCardGame(inputA, inputB);
        System.out.println("---------------------------\n");
    }
}
