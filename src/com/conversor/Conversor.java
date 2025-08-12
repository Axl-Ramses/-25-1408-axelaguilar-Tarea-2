package com.conversor;

public class Conversor {

    public static double convertirLongitud(double valor, String origen, String destino) {
        double metros = switch (origen) {
            case "Metros" -> valor;
            case "Centímetros" -> valor / 100;
            case "Pulgadas" -> valor * 0.0254;
            case "Pies" -> valor * 0.3048;
            case "Yardas" -> valor * 0.9144;
            default -> throw new IllegalArgumentException("Unidad de longitud no válida");
        };

        return switch (destino) {
            case "Metros" -> metros;
            case "Centímetros" -> metros * 100;
            case "Pulgadas" -> metros / 0.0254;
            case "Pies" -> metros / 0.3048;
            case "Yardas" -> metros / 0.9144;
            default -> throw new IllegalArgumentException("Unidad de longitud no válida");
        };
    }

    public static double convertirPeso(double valor, String origen, String destino) {
        double kg = switch (origen) {
            case "Kilogramos" -> valor;
            case "Gramos" -> valor / 1000;
            case "Libras" -> valor * 0.453592;
            case "Onzas" -> valor * 0.0283495;
            default -> throw new IllegalArgumentException("Unidad de peso no válida");
        };

        return switch (destino) {
            case "Kilogramos" -> kg;
            case "Gramos" -> kg * 1000;
            case "Libras" -> kg / 0.453592;
            case "Onzas" -> kg / 0.0283495;
            default -> throw new IllegalArgumentException("Unidad de peso no válida");
        };
    }

    public static double convertirTemperatura(double valor, String origen, String destino) {
        double celsius = switch (origen) {
            case "Celsius" -> valor;
            case "Fahrenheit" -> (valor - 32) * 5 / 9;
            case "Kelvin" -> valor - 273.15;
            default -> throw new IllegalArgumentException("Unidad de temperatura no válida");
        };

        return switch (destino) {
            case "Celsius" -> celsius;
            case "Fahrenheit" -> celsius * 9 / 5 + 32;
            case "Kelvin" -> celsius + 273.15;
            default -> throw new IllegalArgumentException("Unidad de temperatura no válida");
        };
    }
}
