package com.m.matrix.controller;


import com.m.matrix.model.CalculationRequest;
import com.m.matrix.model.Matrix;
import com.m.matrix.service.MatrixService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MatrixController {

    private final MatrixService matrixService;

    public MatrixController(MatrixService matrixService) {
        this.matrixService = matrixService;
    }

    @GetMapping("/")
    public String showCalculator(Model model) {
        model.addAttribute("calculationRequest", new CalculationRequest());
        return "calculator";
    }

    @PostMapping("/calculate")
    public String calculate(
            @Valid @ModelAttribute CalculationRequest request,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "calculator";
        }

        try {
            Matrix resultMatrix = performCalculation(request);
            model.addAttribute("result", resultMatrix);
            model.addAttribute("calculationPerformed", true);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }

        model.addAttribute("calculationRequest", request);
        return "calculator";
    }

    private Matrix performCalculation(CalculationRequest request) {
        return switch (request.getOperation()) {
            case "add" -> matrixService.add(request.getMatrixA(), request.getMatrixB());
            case "subtract" -> matrixService.subtract(request.getMatrixA(), request.getMatrixB());
            case "multiply" -> matrixService.multiply(request.getMatrixA(), request.getMatrixB());
            case "determinantA" -> Matrix.fromArray(new double[][]{
                    {matrixService.determinant(request.getMatrixA()), 0.0},
                    {0.0, 0.0}
            });
            case "determinantB" -> Matrix.fromArray(new double[][]{
                    {matrixService.determinant(request.getMatrixB()), 0.0},
                    {0.0, 0.0}
            });
            case "inverseA" -> matrixService.inverse(request.getMatrixA());
            case "inverseB" -> matrixService.inverse(request.getMatrixB());
            case "transposeA" -> matrixService.transpose(request.getMatrixA());
            case "transposeB" -> matrixService.transpose(request.getMatrixB());
            default -> throw new IllegalArgumentException("Unknown operation: " + request.getOperation());
        };
    }
}