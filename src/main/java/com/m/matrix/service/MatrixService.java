package com.m.matrix.service;

import com.m.matrix.model.Matrix;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
public class MatrixService {
     // basic opeations with 2 matrices
    public Matrix add(Matrix a, Matrix b) throws IllegalArgumentException {
        validateSameDimensions(a, b);

        if ("2D".equals(a.getType())) {
            double[][] result = new double[a.getRows()][a.getCols()];
            for (int i = 0; i < a.getRows(); i++) {
                for (int j = 0; j < a.getCols(); j++) {
                    result[i][j] = a.getData2D()[i][j] + b.getData2D()[i][j];
                }
            }
            return new Matrix("Result", result);
        } else {
            double[][][] result = new double[a.getDepth()][a.getRows()][a.getCols()];
            for (int k = 0; k < a.getDepth(); k++) {
                for (int i = 0; i < a.getRows(); i++) {
                    for (int j = 0; j < a.getCols(); j++) {
                        result[k][i][j] = a.getData3D()[k][i][j] + b.getData3D()[k][i][j];
                    }
                }
            }
            return new Matrix("Result", result);
        }
    }

    public Matrix subtract(Matrix a, Matrix b) throws IllegalArgumentException {
        validateSameDimensions(a, b);

        if ("2D".equals(a.getType())) {
            double[][] result = new double[a.getRows()][a.getCols()];
            for (int i = 0; i < a.getRows(); i++) {
                for (int j = 0; j < a.getCols(); j++) {
                    result[i][j] = a.getData2D()[i][j] - b.getData2D()[i][j];
                }
            }
            return new Matrix("Result", result);
        } else {
            double[][][] result = new double[a.getDepth()][a.getRows()][a.getCols()];
            for (int k = 0; k < a.getDepth(); k++) {
                for (int i = 0; i < a.getRows(); i++) {
                    for (int j = 0; j < a.getCols(); j++) {
                        result[k][i][j] = a.getData3D()[k][i][j] - b.getData3D()[k][i][j];
                    }
                }
            }
            return new Matrix("Result", result);
        }
    }

    public Matrix multiply(Matrix a, Matrix b) throws IllegalArgumentException {
        if ("2D".equals(a.getType())) {
            // Умножение матриц 2D
            if (a.getCols() != b.getRows()) {
                throw new IllegalArgumentException("Number of columns in A must equal number of rows in B");
            }

            double[][] result = new double[a.getRows()][b.getCols()];
            for (int i = 0; i < a.getRows(); i++) {
                for (int j = 0; j < b.getCols(); j++) {
                    for (int k = 0; k < a.getCols(); k++) {
                        result[i][j] += a.getData2D()[i][k] * b.getData2D()[k][j];
                    }
                }
            }
            return new Matrix("Result", result);
        } else {
            // Поэлементное умножение для 3D
            validateSameDimensions(a, b);
            double[][][] result = new double[a.getDepth()][a.getRows()][a.getCols()];
            for (int k = 0; k < a.getDepth(); k++) {
                for (int i = 0; i < a.getRows(); i++) {
                    for (int j = 0; j < a.getCols(); j++) {
                        result[k][i][j] = a.getData3D()[k][i][j] * b.getData3D()[k][i][j];
                    }
                }
            }
            return new Matrix("Result", result);
        }
    }

    public Matrix divideElementWise(Matrix a, Matrix b) throws IllegalArgumentException {
        validateSameDimensions(a, b);

        if ("2D".equals(a.getType())) {
            double[][] result = new double[a.getRows()][a.getCols()];
            for (int i = 0; i < a.getRows(); i++) {
                for (int j = 0; j < a.getCols(); j++) {
                    if (b.getData2D()[i][j] == 0) {
                        throw new IllegalArgumentException("Division by zero at position [" + i + "][" + j + "]");
                    }
                    result[i][j] = a.getData2D()[i][j] / b.getData2D()[i][j];
                }
            }
            return new Matrix("Result", result);
        } else {
            double[][][] result = new double[a.getDepth()][a.getRows()][a.getCols()];
            for (int k = 0; k < a.getDepth(); k++) {
                for (int i = 0; i < a.getRows(); i++) {
                    for (int j = 0; j < a.getCols(); j++) {
                        if (b.getData3D()[k][i][j] == 0) {
                            throw new IllegalArgumentException("Division by zero at position [" + k + "][" + i + "][" + j + "]");
                        }
                        result[k][i][j] = a.getData3D()[k][i][j] / b.getData3D()[k][i][j];
                    }
                }
            }
            return new Matrix("Result", result);
        }
    }

    // with 1 matrix

    public Matrix transpose(Matrix matrix) {
        if ("2D".equals(matrix.getType())) {
            double[][] result = new double[matrix.getCols()][matrix.getRows()];
            for (int i = 0; i < matrix.getRows(); i++) {
                for (int j = 0; j < matrix.getCols(); j++) {
                    result[j][i] = matrix.getData2D()[i][j];
                }
            }
            return new Matrix(matrix.getName() + "_Transposed", result);
        } else {
            // Для 3D транспонируем каждый слой
            double[][][] result = new double[matrix.getDepth()][matrix.getCols()][matrix.getRows()];
            for (int k = 0; k < matrix.getDepth(); k++) {
                for (int i = 0; i < matrix.getRows(); i++) {
                    for (int j = 0; j < matrix.getCols(); j++) {
                        result[k][j][i] = matrix.getData3D()[k][i][j];
                    }
                }
            }
            return new Matrix(matrix.getName() + "_Transposed", result);
        }
    }

    public double determinant(Matrix matrix) throws IllegalArgumentException {
        if (!"2D".equals(matrix.getType())) {
            throw new IllegalArgumentException("Determinant is only defined for 2D matrices");
        }
        if (matrix.getRows() != matrix.getCols()) {
            throw new IllegalArgumentException("Matrix must be square for determinant calculation");
        }

        return calculateDeterminant(matrix.getData2D());
    }

    private double calculateDeterminant(double[][] matrix) {
        int n = matrix.length;

        // Базовые случаи
        if (n == 1) return matrix[0][0];
        if (n == 2) return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];

        double det = 0;
        for (int j = 0; j < n; j++) {
            double[][] minor = getMinor(matrix, 0, j);
            det += Math.pow(-1, j) * matrix[0][j] * calculateDeterminant(minor);
        }
        return det;
    }

    private double[][] getMinor(double[][] matrix, int row, int col) {
        int n = matrix.length;
        double[][] minor = new double[n-1][n-1];
        int r = 0;
        for (int i = 0; i < n; i++) {
            if (i == row) continue;
            int c = 0;
            for (int j = 0; j < n; j++) {
                if (j == col) continue;
                minor[r][c++] = matrix[i][j];
            }
            r++;
        }
        return minor;
    }

    public Matrix lowerTriangular(Matrix matrix) throws IllegalArgumentException {
        if (!"2D".equals(matrix.getType())) {
            throw new IllegalArgumentException("Triangular forms are only defined for 2D matrices");
        }
        if (matrix.getRows() != matrix.getCols()) {
            throw new IllegalArgumentException("Matrix must be square for triangular form");
        }

        double[][] result = new double[matrix.getRows()][matrix.getCols()];
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                result[i][j] = (j <= i) ? matrix.getData2D()[i][j] : 0;
            }
        }
        return new Matrix(matrix.getName() + "_LowerTriangular", result);
    }

    public Matrix upperTriangular(Matrix matrix) throws IllegalArgumentException {
        if (!"2D".equals(matrix.getType())) {
            throw new IllegalArgumentException("Triangular forms are only defined for 2D matrices");
        }
        if (matrix.getRows() != matrix.getCols()) {
            throw new IllegalArgumentException("Matrix must be square for triangular form");
        }

        double[][] result = new double[matrix.getRows()][matrix.getCols()];
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                result[i][j] = (j >= i) ? matrix.getData2D()[i][j] : 0;
            }
        }
        return new Matrix(matrix.getName() + "_UpperTriangular", result);
    }

    public Matrix inverse(Matrix matrix) throws IllegalArgumentException {
        if (!"2D".equals(matrix.getType())) {
            throw new IllegalArgumentException("Inverse is only defined for 2D matrices");
        }
        if (matrix.getRows() != matrix.getCols()) {
            throw new IllegalArgumentException("Matrix must be square for inverse");
        }

        double det = determinant(matrix);
        if (det == 0) {
            throw new IllegalArgumentException("Matrix is singular, cannot compute inverse");
        }

        int n = matrix.getRows();
        double[][] result = new double[n][n];

        // Для матриц 2x2 и 3x3 используем прямые формулы для точности
        if (n == 2) {
            double a = matrix.getData2D()[0][0], b = matrix.getData2D()[0][1];
            double c = matrix.getData2D()[1][0], d = matrix.getData2D()[1][1];
            result[0][0] = d / det;
            result[0][1] = -b / det;
            result[1][0] = -c / det;
            result[1][1] = a / det;
        } else {
            // Общий случай через алгебраические дополнения
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    double[][] minor = getMinor(matrix.getData2D(), i, j);
                    result[j][i] = Math.pow(-1, i + j) * calculateDeterminant(minor) / det;
                }
            }
        }
        return new Matrix(matrix.getName() + "_Inverse", result);
    }

    public double trace(Matrix matrix) throws IllegalArgumentException {
        if (!"2D".equals(matrix.getType())) {
            throw new IllegalArgumentException("Trace is only defined for 2D matrices");
        }
        if (matrix.getRows() != matrix.getCols()) {
            throw new IllegalArgumentException("Matrix must be square for trace");
        }

        double trace = 0;
        for (int i = 0; i < matrix.getRows(); i++) {
            trace += matrix.getData2D()[i][i];
        }
        return trace;
    }

    public Matrix scalarMultiply(Matrix matrix, double scalar) {
        if ("2D".equals(matrix.getType())) {
            double[][] result = new double[matrix.getRows()][matrix.getCols()];
            for (int i = 0; i < matrix.getRows(); i++) {
                for (int j = 0; j < matrix.getCols(); j++) {
                    result[i][j] = matrix.getData2D()[i][j] * scalar;
                }
            }
            return new Matrix(matrix.getName() + "_Scaled", result);
        } else {
            double[][][] result = new double[matrix.getDepth()][matrix.getRows()][matrix.getCols()];
            for (int k = 0; k < matrix.getDepth(); k++) {
                for (int i = 0; i < matrix.getRows(); i++) {
                    for (int j = 0; j < matrix.getCols(); j++) {
                        result[k][i][j] = matrix.getData3D()[k][i][j] * scalar;
                    }
                }
            }
            return new Matrix(matrix.getName() + "_Scaled", result);
        }
    }

    public Matrix identity(int size) {
        double[][] result = new double[size][size];
        for (int i = 0; i < size; i++) {
            result[i][i] = 1;
        }
        return new Matrix("Identity", result);
    }

    public Matrix zeros(int rows, int cols) {
        return new Matrix("Zeros", new double[rows][cols]);
    }

    public Matrix ones(int rows, int cols) {
        double[][] result = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = 1;
            }
        }
        return new Matrix("Ones", result);
    }

    // with 3 matrices

    public Matrix tripleProduct(Matrix a, Matrix b, Matrix c) throws IllegalArgumentException {
        Matrix ab = multiply(a, b);
        return multiply(ab, c);
    }

    public Matrix tripleAddition(Matrix a, Matrix b, Matrix c) throws IllegalArgumentException {
        validateSameDimensions(a, b);
        validateSameDimensions(a, c);

        if ("2D".equals(a.getType())) {
            double[][] result = new double[a.getRows()][a.getCols()];
            for (int i = 0; i < a.getRows(); i++) {
                for (int j = 0; j < a.getCols(); j++) {
                    result[i][j] = a.getData2D()[i][j] + b.getData2D()[i][j] + c.getData2D()[i][j];
                }
            }
            return new Matrix("Triple_Sum", result);
        } else {
            double[][][] result = new double[a.getDepth()][a.getRows()][a.getCols()];
            for (int k = 0; k < a.getDepth(); k++) {
                for (int i = 0; i < a.getRows(); i++) {
                    for (int j = 0; j < a.getCols(); j++) {
                        result[k][i][j] = a.getData3D()[k][i][j] + b.getData3D()[k][i][j] + c.getData3D()[k][i][j];
                    }
                }
            }
            return new Matrix("Triple_Sum", result);
        }
    }

    //with 4 matrices
    public Matrix quadrupleProduct(Matrix a, Matrix b, Matrix c, Matrix d) throws IllegalArgumentException {
        Matrix ab = multiply(a, b);
        Matrix abc = multiply(ab, c);
        return multiply(abc, d);
    }

    public Matrix weightedSum(Matrix a, Matrix b, Matrix c, Matrix d,
                              double w1, double w2, double w3, double w4) throws IllegalArgumentException {
        validateSameDimensions(a, b);
        validateSameDimensions(a, c);
        validateSameDimensions(a, d);

        if ("2D".equals(a.getType())) {
            double[][] result = new double[a.getRows()][a.getCols()];
            for (int i = 0; i < a.getRows(); i++) {
                for (int j = 0; j < a.getCols(); j++) {
                    result[i][j] = w1 * a.getData2D()[i][j] + w2 * b.getData2D()[i][j] +
                            w3 * c.getData2D()[i][j] + w4 * d.getData2D()[i][j];
                }
            }
            return new Matrix("Weighted_Sum", result);
        } else {
            double[][][] result = new double[a.getDepth()][a.getRows()][a.getCols()];
            for (int k = 0; k < a.getDepth(); k++) {
                for (int i = 0; i < a.getRows(); i++) {
                    for (int j = 0; j < a.getCols(); j++) {
                        result[k][i][j] = w1 * a.getData3D()[k][i][j] + w2 * b.getData3D()[k][i][j] +
                                w3 * c.getData3D()[k][i][j] + w4 * d.getData3D()[k][i][j];
                    }
                }
            }
            return new Matrix("Weighted_Sum", result);
        }
    }

    //other methods

    private void validateSameDimensions(Matrix a, Matrix b) throws IllegalArgumentException {
        if (!a.getType().equals(b.getType())) {
            throw new IllegalArgumentException("Matrices must be of same type");
        }

        if ("2D".equals(a.getType())) {
            if (a.getRows() != b.getRows() || a.getCols() != b.getCols()) {
                throw new IllegalArgumentException("Matrices must have same dimensions");
            }
        } else {
            if (a.getDepth() != b.getDepth() || a.getRows() != b.getRows() || a.getCols() != b.getCols()) {
                throw new IllegalArgumentException("3D Matrices must have same dimensions");
            }
        }
    }

    public Matrix performOperation(Matrix a, Matrix b, String operation) throws IllegalArgumentException {
        switch (operation) {
            case "add": return add(a, b);
            case "subtract": return subtract(a, b);
            case "multiply": return multiply(a, b);
            case "divide": return divideElementWise(a, b);
            default: throw new IllegalArgumentException("Unknown operation: " + operation);
        }
    }

    public Object performSingleOperation(Matrix matrix, String operation) throws IllegalArgumentException {
        switch (operation) {
            case "transpose": return transpose(matrix);
            case "determinant": return determinant(matrix);
            case "lowerTriangular": return lowerTriangular(matrix);
            case "upperTriangular": return upperTriangular(matrix);
            case "inverse": return inverse(matrix);
            case "trace": return trace(matrix);
            default: throw new IllegalArgumentException("Unknown operation: " + operation);
        }
    }
}
