/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package GUI;

import javax.swing.*;

public class InicioGUI {
    public InicioGUI() {
        int carrosIzquierda = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántos carros de izquierda a derecha?"));
        int carrosDerecha = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántos carros de derecha a izquierda?"));

        new Simulacion(carrosIzquierda, carrosDerecha);
    }
}
