public class SquareTable {
    public static void printSquareTable(int n) {
        // DO NOT change the skeleton code.
        // You can add codes anywhere you want.
        for(int i = 1; i * i <= n; i++){
            printOneSquare(i, i * i);
        }
    }

    private static void printOneSquare(int a, int b) {
        System.out.printf("%d times %d = %d\n", a, a, b);
    }
}