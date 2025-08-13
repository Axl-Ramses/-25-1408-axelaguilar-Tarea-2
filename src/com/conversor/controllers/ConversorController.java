package com.conversor.controllers;

import com.conversor.Conversor;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ConversorController {

    @FXML private ComboBox<String> cmbCategoria;
    @FXML private ComboBox<String> cmbUnidadOrigen;
    @FXML private ComboBox<String> cmbUnidadDestino;
    @FXML private TextField txtValor;
    @FXML private Label lblResultado;
    @FXML private TextArea txtHistorial;

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
            default -> {}
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
                case "Longitud" -> Conversor.convertirLongitud(valor, origen, destino);
                case "Peso" -> Conversor.convertirPeso(valor, origen, destino);
                case "Temperatura" -> Conversor.convertirTemperatura(valor, origen, destino);
                default -> throw new IllegalArgumentException("Categoría no válida");
            };

            String textoResultado = String.format("%s %s = %s %s",
                    formatearNumero(valor), origen, formatearNumero(resultado), destino);
            lblResultado.setText(textoResultado);

            // Añadir resultado al historial
            txtHistorial.appendText(textoResultado + "\n");

        } catch (NumberFormatException e) {
            lblResultado.setText("Ingrese un número válido");
        } catch (IllegalArgumentException e) {
            lblResultado.setText(e.getMessage());
        }
    }

    private String formatearNumero(double numero) {
        if (numero == (long) numero) {
            return String.format("%d", (long) numero);
        } else {
            // Redondear a 4 decimales y eliminar ceros innecesarios
            String formatted = String.format("%.4f", numero).replaceAll("0*$", "");
            // Eliminar el punto decimal si no hay decimales después de eliminar ceros
            return formatted.endsWith(".") ? formatted.substring(0, formatted.length() - 1) : formatted;
        }
    }

    @FXML
    private void limpiarCampos() {
        txtValor.clear();
        lblResultado.setText("Resultado");
        cmbCategoria.getSelectionModel().clearSelection();
        cmbUnidadOrigen.getItems().clear();
        cmbUnidadDestino.getItems().clear();
    }

    @FXML
    private void limpiarHistorial() {
        txtHistorial.clear();
    }
}
