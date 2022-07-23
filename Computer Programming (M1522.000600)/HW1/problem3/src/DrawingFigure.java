public class DrawingFigure {
    public static void drawFigure(int n) {
        // DO NOT change the skeleton code.
        // You can add codes anywhere you want.

        int size = n * 4 + 1;
        n = n + 1;

        int space = (n - 1) ;
        int space1 = (n - 1);
        for (int k = 1; k <= n - 1; k++) {
            for (int i = 1; i <= space * 2; i++)
                System.out.print(" ");
            space--;
            for (int i = 1; i <= (2 * k - 1) * 2; i++) {
                if (i % 2 == 1)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            for (int i = 1; i <= space1 * 2 - 1; i++)
                System.out.print(" ");
            space1--;
            System.out.println();
        }

        // the longest row
        for (int k = 1; k <= size; k ++){
            if (k % 2 == 1) System.out.print ("*");
            else System.out.print(" ");
        }
        System.out.println();

        space = 1;
        space1 = 1;
        for (int k = 1; k <= n - 1; k++) {
            for (int j = 1; j <= space * 2; j++)
                System.out.print(" ");
            space++;
            for (int j = 1; j <= (2 * (n - k) - 1) * 2; j++){
                if (j % 2 == 1)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            for (int j = 1; j <= space1 * 2 - 1; j++)
                System.out.print(" ");
            space1++;
            System.out.println();
        }
    }
}