public class MatrixFlip {
    public static void printFlippedMatrix(char[][] matrix) {
		// DO NOT change the skeleton code.
		// You can add codes anywhere you want.

        // Flips Upside Down
        for (int i = 0; i < matrix.length / 2; i++){
            for (int j = 0; j < matrix[i].length; j++){
                char temp_vertically = matrix[i][j];
                matrix[i][j] = matrix[matrix.length - 1 - i][j];
                matrix[matrix.length - 1 - i][j] = temp_vertically;
            }
        }

        // Flips left to right
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length / 2; j++){
                char temp_horizontally = matrix[i][j];
                matrix[i][j] = matrix[i][matrix[i].length - 1 - j];
                matrix[i][matrix[i].length - 1 - j] = temp_horizontally;
            }
        }

        // Print Matrix
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
}
