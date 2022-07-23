import java.util.Scanner;

public class Test {
    // DO NOT change anything in this file.
    public static void main(String[] args) {
        // TestCases
        System.out.println("/***** TestCase *****/");
        System.out.println("> After you implementation, the output should be same.");
        test("927", "281", "350");

        // Test your own inputs
        System.out.println("Enter your own inputs:");
        Scanner sc = new Scanner(System.in);
        String str0 = sc.nextLine(), str1 = sc.nextLine(), str2 = sc.nextLine();
        sc.close();

        NumberCounter.countNumbers(str0, str1, str2);
    }

    private static void test(String str0, String str1, String str2) {
        System.out.println("---------- Input -----------");
        System.out.println(str0);
        System.out.println(str1);
        System.out.println(str2);
        System.out.println("---------- Output ----------");
        NumberCounter.countNumbers(str0, str1, str2);
        System.out.println("\n----------------------------\n");
    }
}
