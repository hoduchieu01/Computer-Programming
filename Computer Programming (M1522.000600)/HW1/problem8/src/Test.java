import java.util.Scanner;

public class Test {
    // DO NOT change anything in this file.
    public static void main(String[] args) {
        // TestCases
        System.out.println("/***** TestCase *****/");
        System.out.println("> After you implementation, the output should be same.");

        char[][] testMatrix0 = {
                {'A', 'A', 'A'},
                {'A', 'B', 'B'},
                {'B', 'B', 'B'}};
        test(testMatrix0);

        char[][] testMatrix1 = {
                {'B', 'B', 'B', 'B', 'C'},
                {'C', 'B', 'B', 'B', 'B'},
                {'B', 'B', 'B', 'B', 'B'},
                {'C', 'C', 'C', 'C', 'C'}};
        test(testMatrix1);


        // Test your own inputs
        System.out.println("Enter your own inputs:");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        sc.nextLine();
        char[][] matrix = new char[n][m];
        for (int i = 0; i < n; i++)
            matrix[i] = sc.next().toCharArray();

        MatrixFlip.printFlippedMatrix(matrix);
    }

    private static void test(char[][] matrix) {
        System.out.println("---------- Input -----------");
        System.out.println("" + matrix.length + " " + matrix[0].length );
        for (char[] chars : matrix) {
            for (char c : chars) {
                System.out.print(c);
            }
            System.out.println();
        }
        System.out.println("---------- Output ----------");
        MatrixFlip.printFlippedMatrix(matrix);
        System.out.println("----------------------------\n");
    }
}
