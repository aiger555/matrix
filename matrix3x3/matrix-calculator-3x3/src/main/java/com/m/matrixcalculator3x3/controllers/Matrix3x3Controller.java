package com.m.matrixcalculator3x3.controllers;


import com.m.matrixcalculator3x3.models.CalculationRequest3x3;
import com.m.matrixcalculator3x3.models.Matrix3x3;
import com.m.matrixcalculator3x3.services.Matrix3x3Service;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Matrix3x3Controller {

    private final Matrix3x3Service matrixService;

    public Matrix3x3Controller(Matrix3x3Service matrixService) {
        this.matrixService = matrixService;
    }

    @GetMapping("/")
    public String showCalculator(Model model) {
        model.addAttribute("calculationRequest", new CalculationRequest3x3());
        return "calculator3x3";
    }

    @PostMapping("/calculate")
    public String calculate(
            @Valid @ModelAttribute CalculationRequest3x3 request,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "calculator3x3";
        }

        try {
            Object calculationResult = performCalculation(request);
            model.addAttribute("result", calculationResult);
            model.addAttribute("calculationPerformed", true);
            model.addAttribute("resultType", calculationResult instanceof Matrix3x3 ? "matrix" : "scalar");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }

        model.addAttribute("calculationRequest", request);
        return "calculator3x3";
    }

    private Object performCalculation(CalculationRequest3x3 request) {
        return switch (request.getOperation()) {
            case "add" -> matrixService.add(request.getMatrixA(), request.getMatrixB());
            case "subtract" -> matrixService.subtract(request.getMatrixA(), request.getMatrixB());
            case "multiply" -> matrixService.multiply(request.getMatrixA(), request.getMatrixB());
            case "determinantA" -> matrixService.determinant(request.getMatrixA());
            case "determinantB" -> matrixService.determinant(request.getMatrixB());
            case "inverseA" -> matrixService.inverse(request.getMatrixA());
            case "inverseB" -> matrixService.inverse(request.getMatrixB());
            case "transposeA" -> matrixService.transpose(request.getMatrixA());
            case "transposeB" -> matrixService.transpose(request.getMatrixB());
            case "traceA" -> matrixService.trace(request.getMatrixA());
            case "traceB" -> matrixService.trace(request.getMatrixB());
            default -> throw new IllegalArgumentException("Unknown operation: " + request.getOperation());
        };
    }
}