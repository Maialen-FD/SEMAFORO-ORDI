/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import HILOS.Carro;
import HILOS.Caseta;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulacion {

    public Simulacion(int carrosIzquierda, int carrosDerecha) {
        // Crear casetas
        List<Caseta> casetas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            // Alternar entre semáforos verdes y rojos aleatoriamente
            casetas.add(new Caseta(new Random().nextBoolean())); 
        }

        // Crear el panel de la carretera
        Carro[] carros = new Carro[carrosIzquierda + carrosDerecha];
        for (int i = 0; i < carrosIzquierda; i++) {
            carros[i] = new Carro(0, new CarreteraPanel(carros, casetas), casetas.get(i % casetas.size()));
        }

        for (int i = carrosIzquierda; i < carrosIzquierda + carrosDerecha; i++) {
            carros[i] = new Carro(1, new CarreteraPanel(carros, casetas), casetas.get(i % casetas.size()));
        }

        CarreteraPanel panel = new CarreteraPanel(carros, casetas);

        // Configurar la ventana
        JFrame frame = new JFrame("Simulador de Carretera");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);

        // Iniciar los hilos de los carros de manera aleatoria
        Random random = new Random();
        List<Carro> carrosList = new ArrayList<>();
        for (Carro carro : carros) {
            carrosList.add(carro);
        }

        // Desordenar los carros aleatoriamente
        for (int i = 0; i < carrosList.size(); i++) {
            int j = random.nextInt(carrosList.size());
            Carro temp = carrosList.get(i);
            carrosList.set(i, carrosList.get(j));
            carrosList.set(j, temp);
        }

        // Iniciar los hilos de los carros de manera aleatoria
        for (Carro carro : carrosList) {
            new Thread(carro).start();
        }

        // Iniciar un hilo para cambiar aleatoriamente los semáforos
        new Thread(() -> {
            while (true) {
                try {
                    // Cambiar aleatoriamente los semáforos cada 2 segundos
                    Thread.sleep(2000);
                    for (Caseta caseta : casetas) {
                        caseta.setVerde(new Random().nextBoolean());
                    }
                    javax.swing.SwingUtilities.invokeLater(panel::repaint); // Redibujar el panel
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        int carrosIzquierda = 5; // Número de carros a la izquierda
        int carrosDerecha = 5;   // Número de carros a la derecha

        // Crear una nueva simulación pasando los parámetros
        new Simulacion(carrosIzquierda, carrosDerecha);
    }
}
