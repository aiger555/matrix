package com.m.matrixcalculator3x3.models;


public class CalculationRequest3x3 {
    private Matrix3x3 matrixA;
    private Matrix3x3 matrixB;
    private String operation;

    // Constructors
    public CalculationRequest3x3() {}

    public CalculationRequest3x3(Matrix3x3 matrixA, Matrix3x3 matrixB, String operation) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.operation = operation;
    }

    // Getters and Setters
    public Matrix3x3 getMatrixA() { return matrixA; }
    public void setMatrixA(Matrix3x3 matrixA) { this.matrixA = matrixA; }

    public Matrix3x3 getMatrixB() { return matrixB; }
    public void setMatrixB(Matrix3x3 matrixB) { this.matrixB = matrixB; }

    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }
}