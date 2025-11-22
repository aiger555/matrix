package com.m.matrix.model;

import java.util.Arrays;

public class Matrix {
    private String name;
    private double[][] data2D;
    private double[][][] data3D;
    private String type; // "2D" или "3D"
    private int rows;
    private int cols;
    private int depth;

    // Конструкторы
    public Matrix() {}

    public Matrix(String name, double[][] data) {
        this.name = name;
        this.data2D = data;
        this.type = "2D";
        this.rows = data.length;
        this.cols = data[0].length;
    }

    public Matrix(String name, double[][][] data) {
        this.name = name;
        this.data3D = data;
        this.type = "3D";
        this.depth = data.length;
        this.rows = data[0].length;
        this.cols = data[0][0].length;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double[][] getData2D() { return data2D; }
    public void setData2D(double[][] data2D) {
        this.data2D = data2D;
        this.type = "2D";
        this.rows = data2D.length;
        this.cols = data2D[0].length;
    }

    public double[][][] getData3D() { return data3D; }
    public void setData3D(double[][][] data3D) {
        this.data3D = data3D;
        this.type = "3D";
        this.depth = data3D.length;
        this.rows = data3D[0].length;
        this.cols = data3D[0][0].length;
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public int getDepth() { return depth; }

    @Override
    public String toString() {
        if ("2D".equals(type)) {
            return Arrays.deepToString(data2D);
        } else {
            return Arrays.deepToString(data3D);
        }
    }
}