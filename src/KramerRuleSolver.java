public class KramerRuleSolver {

    public static double[] solveSystem(double[][] coefficients, double[] results) {
        int n = results.length;
        double[] solution = new double[n];

        double mainDeterminant = calculateDeterminant(coefficients);

        if (mainDeterminant == 0) {
            return null; // The system does not have a unique solution
        }

        for (int i = 0; i < n; i++) {
            double[][] modifiedCoefficients = copyCoefficients(coefficients);
            for (int j = 0; j < n; j++) {
                modifiedCoefficients[j][i] = results[j];
            }
            double determinantSubstituted = calculateDeterminant(modifiedCoefficients);
            solution[i] = determinantSubstituted / mainDeterminant;
        }

        return solution;
    }

    public static double calculateDeterminant(double[][] matrix) {
        int n = matrix.length;

        if (n == 2) {
            return (matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]);
        }

        double determinant = 0;
        int sign = 1;
        for (int j = 0; j < n; j++) {
            determinant += sign * matrix[0][j] * calculateDeterminant(subMatrix(matrix, 0, j));
            sign = -sign;
        }

        return determinant;
    }

    public static double[][] subMatrix(double[][] matrix, int rowToRemove, int colToRemove) {
        int n = matrix.length - 1;
        double[][] submatrix = new double[n][n];
        int rowIndex = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (i == rowToRemove) {
                continue;
            }
            int colIndex = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                if (j == colToRemove) {
                    continue;
                }
                submatrix[rowIndex][colIndex] = matrix[i][j];
                colIndex++;
            }
            rowIndex++;
        }
        return submatrix;
    }

    public static double[][] copyCoefficients(double[][] coefficients) {
        int n = coefficients.length;
        double[][] copy = new double[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(coefficients[i], 0, copy[i], 0, n);
        }
        return copy;
    }
}
