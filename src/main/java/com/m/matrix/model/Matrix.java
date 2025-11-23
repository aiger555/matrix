package com.m.matrix.model;

import jakarta.validation.constraints.NotNull;

public class Matrix {
    @NotNull
    private Double a11;
    @NotNull
    private Double a12;
    @NotNull
    private Double a21;
    @NotNull
    private Double a22;

    // Constructors
    public Matrix() {}

    public Matrix(Double a11, Double a12, Double a21, Double a22) {
        this.a11 = a11;
        this.a12 = a12;
        this.a21 = a21;
        this.a22 = a22;
    }

    // Getters and Setters
    public Double getA11() { return a11; }
    public void setA11(Double a11) { this.a11 = a11; }

    public Double getA12() { return a12; }
    public void setA12(Double a12) { this.a12 = a12; }

    public Double getA21() { return a21; }
    public void setA21(Double a21) { this.a21 = a21; }

    public Double getA22() { return a22; }
    public void setA22(Double a22) { this.a22 = a22; }

    // Utility method to get as 2D array
    public double[][] toArray() {
        return new double[][] {
                {a11, a12},
                {a21, a22}
        };
    }

    // Create matrix from 2D array
    public static Matrix fromArray(double[][] array) {
        if (array.length != 2 || array[0].length != 2) {
            throw new IllegalArgumentException("Array must be 2x2");
        }
        return new Matrix(array[0][0], array[0][1], array[1][0], array[1][1]);
    }
}