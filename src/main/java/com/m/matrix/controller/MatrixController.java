package com.m.matrix.controller;

import com.m.matrix.model.Matrix;
import com.m.matrix.service.MatrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MatrixController {

    @Autowired
    private MatrixService matrixService;



    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("matrixA", new Matrix());
        model.addAttribute("matrixB", new Matrix());
        model.addAttribute("matrixC", new Matrix());
        model.addAttribute("matrixD", new Matrix());
        return "index";
    }

    @PostMapping("/calculate/binary")
    @ResponseBody
    public Map<String, Object> calculateBinary(@RequestParam String operation,
                                               @ModelAttribute Matrix matrixA,
                                               @ModelAttribute Matrix matrixB) {
        Map<String, Object> response = new HashMap<>();
        try {
            Matrix result = matrixService.performOperation(matrixA, matrixB, operation);
            response.put("success", true);
            response.put("result", result);
            response.put("type", result.getType());
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
        }
        return response;
    }

    @PostMapping("/calculate/single")
    @ResponseBody
    public Map<String, Object> calculateSingle(@RequestParam String operation,
                                               @ModelAttribute Matrix matrix) {
        Map<String, Object> response = new HashMap<>();
        try {
            Object result = matrixService.performSingleOperation(matrix, operation);
            response.put("success", true);
            if (result instanceof Matrix) {
                response.put("result", result);
                response.put("type", ((Matrix) result).getType());
            } else {
                response.put("result", result);
                response.put("type", "scalar");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
        }
        return response;
    }

    @PostMapping("/calculate/triple")
    @ResponseBody
    public Map<String, Object> calculateTriple(@RequestParam String operation,
                                               @ModelAttribute Matrix matrixA,
                                               @ModelAttribute Matrix matrixB,
                                               @ModelAttribute Matrix matrixC) {
        Map<String, Object> response = new HashMap<>();
        try {
            Matrix result;
            if ("tripleProduct".equals(operation)) {
                result = matrixService.tripleProduct(matrixA, matrixB, matrixC);
            } else if ("tripleAddition".equals(operation)) {
                result = matrixService.tripleAddition(matrixA, matrixB, matrixC);
            } else {
                throw new IllegalArgumentException("Unknown triple operation: " + operation);
            }
            response.put("success", true);
            response.put("result", result);
            response.put("type", result.getType());
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
        }
        return response;
    }

    @PostMapping("/calculate/quadruple")
    @ResponseBody
    public Map<String, Object> calculateQuadruple(@RequestParam String operation,
                                                  @ModelAttribute Matrix matrixA,
                                                  @ModelAttribute Matrix matrixB,
                                                  @ModelAttribute Matrix matrixC,
                                                  @ModelAttribute Matrix matrixD,
                                                  @RequestParam(defaultValue = "1.0") double w1,
                                                  @RequestParam(defaultValue = "1.0") double w2,
                                                  @RequestParam(defaultValue = "1.0") double w3,
                                                  @RequestParam(defaultValue = "1.0") double w4) {
        Map<String, Object> response = new HashMap<>();
        try {
            Matrix result;
            if ("quadrupleProduct".equals(operation)) {
                result = matrixService.quadrupleProduct(matrixA, matrixB, matrixC, matrixD);
            } else if ("weightedSum".equals(operation)) {
                result = matrixService.weightedSum(matrixA, matrixB, matrixC, matrixD, w1, w2, w3, w4);
            } else {
                throw new IllegalArgumentException("Unknown quadruple operation: " + operation);
            }
            response.put("success", true);
            response.put("result", result);
            response.put("type", result.getType());
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
        }
        return response;
    }

    @PostMapping("/scalar/multiply")
    @ResponseBody
    public Map<String, Object> scalarMultiply(@ModelAttribute Matrix matrix,
                                              @RequestParam double scalar) {
        Map<String, Object> response = new HashMap<>();
        try {
            Matrix result = matrixService.scalarMultiply(matrix, scalar);
            response.put("success", true);
            response.put("result", result);
            response.put("type", result.getType());
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
        }
        return response;
    }

    @PostMapping("/generate/matrix")
    @ResponseBody
    public Map<String, Object> generateMatrix(@RequestParam String type,
                                              @RequestParam int rows,
                                              @RequestParam int cols,
                                              @RequestParam(defaultValue = "1") int depth) {
        Map<String, Object> response = new HashMap<>();
        try {
            Matrix result;
            switch (type) {
                case "identity":
                    if (rows != cols) throw new IllegalArgumentException("Identity matrix must be square");
                    result = matrixService.identity(rows);
                    break;
                case "zeros":
                    result = matrixService.zeros(rows, cols);
                    break;
                case "ones":
                    result = matrixService.ones(rows, cols);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown matrix type: " + type);
            }
            response.put("success", true);
            response.put("result", result);
            response.put("type", result.getType());
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
        }
        return response;
    }
}