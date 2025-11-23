package com.m.matrixcalculator3x3.models;


import jakarta.validation.constraints.NotNull;

public class Matrix3x3 {
    @NotNull
    private Double a11;
    @NotNull
    private Double a12;
    @NotNull
    private Double a13;
    @NotNull
    private Double a21;
    @NotNull
    private Double a22;
    @NotNull
    private Double a23;
    @NotNull
    private Double a31;
    @NotNull
    private Double a32;
    @NotNull
    private Double a33;

    // Constructors
    public Matrix3x3() {}

    public Matrix3x3(Double a11, Double a12, Double a13,
                     Double a21, Double a22, Double a23,
                     Double a31, Double a32, Double a33) {
        this.a11 = a11;
        this.a12 = a12;
        this.a13 = a13;
        this.a21 = a21;
        this.a22 = a22;
        this.a23 = a23;
        this.a31 = a31;
        this.a32 = a32;
        this.a33 = a33;
    }

    // Getters and Setters
    public Double getA11() { return a11; }
    public void setA11(Double a11) { this.a11 = a11; }

    public Double getA12() { return a12; }
    public void setA12(Double a12) { this.a12 = a12; }

    public Double getA13() { return a13; }
    public void setA13(Double a13) { this.a13 = a13; }

    public Double getA21() { return a21; }
    public void setA21(Double a21) { this.a21 = a21; }

    public Double getA22() { return a22; }
    public void setA22(Double a22) { this.a22 = a22; }

    public Double getA23() { return a23; }
    public void setA23(Double a23) { this.a23 = a23; }

    public Double getA31() { return a31; }
    public void setA31(Double a31) { this.a31 = a31; }

    public Double getA32() { return a32; }
    public void setA32(Double a32) { this.a32 = a32; }

    public Double getA33() { return a33; }
    public void setA33(Double a33) { this.a33 = a33; }

    // Utility method to get as 2D array
    public double[][] toArray() {
        return new double[][] {
                {a11, a12, a13},
                {a21, a22, a23},
                {a31, a32, a33}
        };
    }

    // Create matrix from 2D array
    public static Matrix3x3 fromArray(double[][] array) {
        if (array.length != 3 || array[0].length != 3) {
            throw new IllegalArgumentException("Array must be 3x3");
        }
        return new Matrix3x3(
                array[0][0], array[0][1], array[0][2],
                array[1][0], array[1][1], array[1][2],
                array[2][0], array[2][1], array[2][2]
        );
    }
}