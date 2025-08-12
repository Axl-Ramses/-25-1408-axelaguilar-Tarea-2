----
25-1408-progra2-Conversor Unidades
----
Este proyecto es un Conversor de Unidades que permite transformar valores entre diferentes tipos de medidas, incluyendo longitud, peso y temperatura. 
Cuenta con una interfaz grafica intuitiva similar a una calculadora, desarrollada en javafx, que facilita la seleccion de categorias,
 unidades de origen y destinno y muestra el resultado de la conversion. 

 ----
 Funcionalidades 
 ----
 *conversion entre undadesd de longitud ( metros, centimetros, pulgadas, pies, yardas)
 *conversion entre unidades de peso (kilogramos, gramos, libras, onzas)
 *conversion entre unidades de temperatura (Celsius, Fahrenheit, kelvin)
 *interfaz grafica con selecciondinamica de categorias y unidades
 *Validaccion basica de entrada para evitar errores en la conversion.
 *Resultado mostrando de manera clara y en tiempo real tras presionar el boton "Convertir"

 -----
 Estructura del proyecto 
 -----
 *Main.java: Clase principal que inicia la aplicacion yc arga lainterfaz FXML.
 *ConversorController.java:Controlador que maneha la logica de la interfaz,incluyendo el manejo de eventos y la conversiond de unidades.
 *Conversor.fxml: Archivo FXML que define la estructura visual de la interfaz de usuario.
 *Resources/: Carpeta que contien recuross adicionales como el archivo FXML

 ----
 Tecnologias Usadas
 ----
*Java 23
*Javafx-24.0.2
*Scene builder para diseño visial del fxml 

----
Uso de la aplicacion
----
1.Selecciona la categoria de unidades (longitud, peso, temperatura)
2.Escoge la unidad de orgin Ej. metros
3.Escoge la unidad de destino Ej. pulgadas
4.Ingresa el valor numero que deseas Convertir
5.Presion el boton de "Convertir"
6.Visualiza el resultado en la eitqueta "Resultado"

----
Consideraciones
----
*La interfaz esta diseñada para ser sencilla y facil de usar, pero podria mejorarse a futuro. 

-----
Autor: Axel Aguilar
Clase: 1408
-----





