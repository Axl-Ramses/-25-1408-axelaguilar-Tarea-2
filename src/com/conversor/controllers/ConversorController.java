package com.conversor.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ConversorController {

    @FXML private ComboBox<String> cmbCategoria;
    @FXML private ComboBox<String> cmbUnidadOrigen;
    @FXML private ComboBox<String> cmbUnidadDestino;
    @FXML private TextField txtValor;
    @FXML private Label lblResultado;

    @FXML
    public void initialize() {
        cmbCategoria.getItems().addAll("Longitud", "Peso", "Temperatura");
        cmbCategoria.setOnAction(e -> cargarUnidades());

        cmbUnidadOrigen.setPromptText("Unidad origen");
        cmbUnidadDestino.setPromptText("Unidad destino");
    }

    private void cargarUnidades() {
        cmbUnidadOrigen.getItems().clear();
        cmbUnidadDestino.getItems().clear();

        switch (cmbCategoria.getValue()) {
            case "Longitud" -> cmbUnidadOrigen.getItems().addAll("Metros", "Centímetros", "Pulgadas", "Pies", "Yardas");
            case "Peso" -> cmbUnidadOrigen.getItems().addAll("Kilogramos", "Gramos", "Libras", "Onzas");
            case "Temperatura" -> cmbUnidadOrigen.getItems().addAll("Celsius", "Fahrenheit", "Kelvin");
        }

        cmbUnidadDestino.getItems().addAll(cmbUnidadOrigen.getItems());
    }

    @FXML
    private void convertir() {
        try {
            double valor = Double.parseDouble(txtValor.getText());
            String categoria = cmbCategoria.getValue();
            String origen = cmbUnidadOrigen.getValue();
            String destino = cmbUnidadDestino.getValue();

            if (categoria == null || origen == null || destino == null) {
                lblResultado.setText("Complete todos los campos");
                return;
            }

            if (origen.equals(destino)) {
                lblResultado.setText("Las unidades deben ser distintas");
                return;
            }

            double resultado = switch (categoria) {
                case "Longitud" -> convertirLongitud(valor, origen, destino);
                case "Peso" -> convertirPeso(valor, origen, destino);
                case "Temperatura" -> convertirTemperatura(valor, origen, destino);
                default -> throw new IllegalArgumentException("Categoría no válida");
            };

            lblResultado.setText(String.format("%.4f %s", resultado, destino));
        } catch (NumberFormatException e) {
            lblResultado.setText("Ingrese un número válido");
        }
    }

    private double convertirLongitud(double valor, String origen, String destino) {
        double metros = switch (origen) {
            case "Metros" -> valor;
            case "Centímetros" -> valor / 100.0;
            case "Pulgadas" -> valor * 0.0254;
            case "Pies" -> valor * 0.3048;
            case "Yardas" -> valor * 0.9144;
            default -> valor;
        };

        return switch (destino) {
            case "Metros" -> metros;
            case "Centímetros" -> metros * 100.0;
            case "Pulgadas" -> metros / 0.0254;
            case "Pies" -> metros / 0.3048;
            case "Yardas" -> metros / 0.9144;
            default -> metros;
        };
    }

    private double convertirPeso(double valor, String origen, String destino) {
        double kg = switch (origen) {
            case "Kilogramos" -> valor;
            case "Gramos" -> valor / 1000.0;
            case "Libras" -> valor * 0.453592;
            case "Onzas" -> valor * 0.0283495;
            default -> valor;
        };

        return switch (destino) {
            case "Kilogramos" -> kg;
            case "Gramos" -> kg * 1000.0;
            case "Libras" -> kg / 0.453592;
            case "Onzas" -> kg / 0.0283495;
            default -> kg;
        };
    }

    private double convertirTemperatura(double valor, String origen, String destino) {
        double celsius = switch (origen) {
            case "Celsius" -> valor;
            case "Fahrenheit" -> (valor - 32) * 5 / 9;
            case "Kelvin" -> valor - 273.15;
            default -> valor;
        };

        return switch (destino) {
            case "Celsius" -> celsius;
            case "Fahrenheit" -> celsius * 9 / 5 + 32;
            case "Kelvin" -> celsius + 273.15;
            default -> celsius;
        };
    }
}
