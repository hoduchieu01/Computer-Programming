import java.util.Scanner;

public class Test {
    // DO NOT change anything in this file.
    public static void main(String[] args) {
        // TestCases
        System.out.println("/***** TestCase *****/");
        System.out.println("> After you implementation, the output should be same.");
        test("bbbaAabaccHadAHbcbHHAdcbbeHaH");

        // Test your own inputs
        System.out.println("Enter your own inputs:");
        Scanner sc = new Scanner(System.in);
        String inputString = sc.nextLine();
        sc.close();

        CharacterCounter.countCharacter(inputString);
    }

    private static void test(String inputString) {
        System.out.println("---------- Input -----------");
        System.out.println(inputString);
        System.out.println("---------- Output ----------");
        CharacterCounter.countCharacter(inputString);
        System.out.println("\n----------------------------\n");
    }
}
