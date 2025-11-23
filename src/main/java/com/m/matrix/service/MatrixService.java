package com.m.matrix.service;


import com.m.matrix.model.Matrix;
import org.springframework.stereotype.Service;

@Service
public class MatrixService {

    // Matrix addition
    public Matrix add(Matrix a, Matrix b) {
        return new Matrix(
                a.getA11() + b.getA11(),
                a.getA12() + b.getA12(),
                a.getA21() + b.getA21(),
                a.getA22() + b.getA22()
        );
    }

    // Matrix subtraction
    public Matrix subtract(Matrix a, Matrix b) {
        return new Matrix(
                a.getA11() - b.getA11(),
                a.getA12() - b.getA12(),
                a.getA21() - b.getA21(),
                a.getA22() - b.getA22()
        );
    }

    // Matrix multiplication
    public Matrix multiply(Matrix a, Matrix b) {
        return new Matrix(
                a.getA11() * b.getA11() + a.getA12() * b.getA21(),
                a.getA11() * b.getA12() + a.getA12() * b.getA22(),
                a.getA21() * b.getA11() + a.getA22() * b.getA21(),
                a.getA21() * b.getA12() + a.getA22() * b.getA22()
        );
    }

    // Scalar multiplication
    public Matrix scalarMultiply(Matrix matrix, double scalar) {
        return new Matrix(
                matrix.getA11() * scalar,
                matrix.getA12() * scalar,
                matrix.getA21() * scalar,
                matrix.getA22() * scalar
        );
    }

    // Determinant
    public double determinant(Matrix matrix) {
        return matrix.getA11() * matrix.getA22() - matrix.getA12() * matrix.getA21();
    }

    // Inverse matrix
    public Matrix inverse(Matrix matrix) {
        double det = determinant(matrix);
        if (det == 0) {
            throw new ArithmeticException("Matrix is singular, cannot calculate inverse");
        }

        return new Matrix(
                matrix.getA22() / det,
                -matrix.getA12() / det,
                -matrix.getA21() / det,
                matrix.getA11() / det
        );
    }

    // Transpose
    public Matrix transpose(Matrix matrix) {
        return new Matrix(
                matrix.getA11(),
                matrix.getA21(),
                matrix.getA12(),
                matrix.getA22()
        );
    }
}