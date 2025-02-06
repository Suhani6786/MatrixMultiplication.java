import java.io.*;
import java.util.Scanner;

public class MatrixMultiplication {

    // Method to read a matrix from a file
    public static int[][] readMatrixFromFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        int rows = 0;
        int cols = 0;

        // First, we count the number of rows and columns in the matrix
        while ((line = br.readLine()) != null) {
            rows++;
            String[] tokens = line.split("\\s+");
            cols = tokens.length;
        }
        br.close();

        // Now, create a matrix with the correct dimensions and read the values
        int[][] matrix = new int[rows][cols];
        br = new BufferedReader(new FileReader(filename));
        int rowIndex = 0;

        while ((line = br.readLine()) != null) {
            String[] tokens = line.split("\\s+");
            for (int i = 0; i < tokens.length; i++) {
                matrix[rowIndex][i] = Integer.parseInt(tokens[i]);
            }
            rowIndex++;
        }
        br.close();

        return matrix;
    }

    // Method to write a matrix to a file
    public static void writeMatrixToFile(int[][] matrix, String filename) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                bw.write(matrix[i][j] + (j < matrix[i].length - 1 ? " " : ""));
            }
            bw.newLine();
        }
        bw.close();
    }

    // Method to multiply two matrices
    public static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {
        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int rows2 = matrix2.length;
        int cols2 = matrix2[0].length;

        // Initialize the result matrix
        int[][] result = new int[rows1][cols2];

        // Perform matrix multiplication
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                for (int k = 0; k < cols1; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    // Method to generate a random matrix of size n x n
    public static int[][] generateRandomMatrix(int n) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = (int) (Math.random() * 10); // Random values between 0 and 9
            }
        }
        return matrix;
    }

    // Main method to run the program
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            int[][] matrix1 = null;
            int[][] matrix2 = null;

            if (args.length == 2) {
                // Case 1: Read two matrices from files
                matrix1 = readMatrixFromFile(args[0]);
                matrix2 = readMatrixFromFile(args[1]);
            } else {
                // Case 2: Generate two random square matrices
                System.out.print("Enter a matrix size (integer): ");
                int n = scanner.nextInt();
                matrix1 = generateRandomMatrix(n);
                matrix2 = generateRandomMatrix(n);

                // Write the random matrices to files
                writeMatrixToFile(matrix1, "matrix1.txt");
                writeMatrixToFile(matrix2, "matrix2.txt");
            }

            // Perform matrix multiplication
            int[][] result = multiplyMatrices(matrix1, matrix2);

            // Write result to a file
            writeMatrixToFile(result, "matrix3.txt");

            System.out.println("Matrix multiplication complete. Result written to matrix3.txt.");

        } catch (IOException e) {
            System.err.println("File error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Input error: Invalid number format.");
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}



