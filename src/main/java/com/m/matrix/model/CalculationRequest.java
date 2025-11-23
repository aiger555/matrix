package com.m.matrix.model;


public class CalculationRequest {
    private Matrix matrixA;
    private Matrix matrixB;
    private String operation;

    // Constructors
    public CalculationRequest() {}

    public CalculationRequest(Matrix matrixA, Matrix matrixB, String operation) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.operation = operation;
    }

    // Getters and Setters
    public Matrix getMatrixA() { return matrixA; }
    public void setMatrixA(Matrix matrixA) { this.matrixA = matrixA; }

    public Matrix getMatrixB() { return matrixB; }
    public void setMatrixB(Matrix matrixB) { this.matrixB = matrixB; }

    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }
}