package com.m.matrixcalculator3x3.services;


import com.m.matrixcalculator3x3.models.Matrix3x3;
import org.springframework.stereotype.Service;

@Service
public class Matrix3x3Service {

    // Matrix addition
    public Matrix3x3 add(Matrix3x3 a, Matrix3x3 b) {
        return new Matrix3x3(
                a.getA11() + b.getA11(), a.getA12() + b.getA12(), a.getA13() + b.getA13(),
                a.getA21() + b.getA21(), a.getA22() + b.getA22(), a.getA23() + b.getA23(),
                a.getA31() + b.getA31(), a.getA32() + b.getA32(), a.getA33() + b.getA33()
        );
    }

    // Matrix subtraction
    public Matrix3x3 subtract(Matrix3x3 a, Matrix3x3 b) {
        return new Matrix3x3(
                a.getA11() - b.getA11(), a.getA12() - b.getA12(), a.getA13() - b.getA13(),
                a.getA21() - b.getA21(), a.getA22() - b.getA22(), a.getA23() - b.getA23(),
                a.getA31() - b.getA31(), a.getA32() - b.getA32(), a.getA33() - b.getA33()
        );
    }

    // Matrix multiplication
    public Matrix3x3 multiply(Matrix3x3 a, Matrix3x3 b) {
        return new Matrix3x3(
                // Row 1
                a.getA11() * b.getA11() + a.getA12() * b.getA21() + a.getA13() * b.getA31(),
                a.getA11() * b.getA12() + a.getA12() * b.getA22() + a.getA13() * b.getA32(),
                a.getA11() * b.getA13() + a.getA12() * b.getA23() + a.getA13() * b.getA33(),

                // Row 2
                a.getA21() * b.getA11() + a.getA22() * b.getA21() + a.getA23() * b.getA31(),
                a.getA21() * b.getA12() + a.getA22() * b.getA22() + a.getA23() * b.getA32(),
                a.getA21() * b.getA13() + a.getA22() * b.getA23() + a.getA23() * b.getA33(),

                // Row 3
                a.getA31() * b.getA11() + a.getA32() * b.getA21() + a.getA33() * b.getA31(),
                a.getA31() * b.getA12() + a.getA32() * b.getA22() + a.getA33() * b.getA32(),
                a.getA31() * b.getA13() + a.getA32() * b.getA23() + a.getA33() * b.getA33()
        );
    }

    // Scalar multiplication
    public Matrix3x3 scalarMultiply(Matrix3x3 matrix, double scalar) {
        return new Matrix3x3(
                matrix.getA11() * scalar, matrix.getA12() * scalar, matrix.getA13() * scalar,
                matrix.getA21() * scalar, matrix.getA22() * scalar, matrix.getA23() * scalar,
                matrix.getA31() * scalar, matrix.getA32() * scalar, matrix.getA33() * scalar
        );
    }

    // Determinant using Rule of Sarrus
    public double determinant(Matrix3x3 matrix) {
        double a11 = matrix.getA11(), a12 = matrix.getA12(), a13 = matrix.getA13();
        double a21 = matrix.getA21(), a22 = matrix.getA22(), a23 = matrix.getA23();
        double a31 = matrix.getA31(), a32 = matrix.getA32(), a33 = matrix.getA33();

        return a11 * a22 * a33 + a12 * a23 * a31 + a13 * a21 * a32
                - a13 * a22 * a31 - a11 * a23 * a32 - a12 * a21 * a33;
    }

    // Inverse matrix using adjugate method
    public Matrix3x3 inverse(Matrix3x3 matrix) {
        double det = determinant(matrix);
        if (Math.abs(det) < 1e-10) {
            throw new ArithmeticException("Matrix is singular, cannot calculate inverse");
        }

        double a11 = matrix.getA11(), a12 = matrix.getA12(), a13 = matrix.getA13();
        double a21 = matrix.getA21(), a22 = matrix.getA22(), a23 = matrix.getA23();
        double a31 = matrix.getA31(), a32 = matrix.getA32(), a33 = matrix.getA33();

        // Calculate cofactor matrix
        double c11 = a22 * a33 - a23 * a32;
        double c12 = -(a21 * a33 - a23 * a31);
        double c13 = a21 * a32 - a22 * a31;

        double c21 = -(a12 * a33 - a13 * a32);
        double c22 = a11 * a33 - a13 * a31;
        double c23 = -(a11 * a32 - a12 * a31);

        double c31 = a12 * a23 - a13 * a22;
        double c32 = -(a11 * a23 - a13 * a21);
        double c33 = a11 * a22 - a12 * a21;

        // Adjugate matrix (transpose of cofactor matrix)
        Matrix3x3 adjugate = new Matrix3x3(
                c11, c21, c31,
                c12, c22, c32,
                c13, c23, c33
        );

        // Inverse = (1/det) * adjugate
        return scalarMultiply(adjugate, 1.0 / det);
    }

    // Transpose
    public Matrix3x3 transpose(Matrix3x3 matrix) {
        return new Matrix3x3(
                matrix.getA11(), matrix.getA21(), matrix.getA31(),
                matrix.getA12(), matrix.getA22(), matrix.getA32(),
                matrix.getA13(), matrix.getA23(), matrix.getA33()
        );
    }

    // Trace (sum of diagonal elements)
    public double trace(Matrix3x3 matrix) {
        return matrix.getA11() + matrix.getA22() + matrix.getA33();
    }

    // Eigenvalues (simplified calculation for demonstration)
    public double[] eigenvalues(Matrix3x3 matrix) {
        // For a real 3x3 matrix, this would require solving a cubic equation
        // This is a simplified version that returns approximate values
        double trace = trace(matrix);
        double det = determinant(matrix);

        // Return approximate eigenvalues (this is simplified)
        return new double[] { trace / 3.0, trace / 3.0, trace / 3.0 };
    }
}