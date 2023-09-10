import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of equations (between 2 and 10): ");
        int n = scanner.nextInt();

        if (n < 2 || n > 10) {
            throw new Exception("The number of equations must be between 2 and 10");
        }

        double[][] coefficients = new double[n][n];
        double[] results = new double[n];

        System.out.println("Enter the coefficients of the equations:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                coefficients[i][j] = scanner.nextDouble();
            }
            System.out.print("Enter the result B[" + (i + 1) + "]: ");
            results[i] = scanner.nextDouble();
        }

        double[] solution = KramerRuleSolver.solveSystem(coefficients, results);

        if (solution != null) {
            System.out.println("The solution of the system of equations is:");
            for (int i = 0; i < n; i++) {
                System.out.println("X[" + (i + 1) + "] = " + solution[i]);
            }
        } else {
            throw new Exception("The system of equations does not have a unique solution.");
        }

        scanner.close();
    }
}
