/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HILOS;

import GUI.CarreteraPanel;

public class Carro implements Runnable {
    private final int carril;
    private final CarreteraPanel panel;
    private final Caseta caseta;
    private int columna = 0;

    public Carro(int carril, CarreteraPanel panel, Caseta caseta) {
        this.carril = carril;
        this.panel = panel;
        this.caseta = caseta;
    }

    @Override
    public void run() {
        while (columna < 20) { // Los carros avanzan hasta la columna 20
            // Verificar si el semáforo está verde y si el carro puede avanzar
            if (caseta.isVerde()) {
                // Mover el carro en el panel (actualizar su posición)
                panel.moverCarro(carril, columna);

                // Usar invokeLater para actualizar la interfaz de manera segura
                javax.swing.SwingUtilities.invokeLater(() -> {
                    panel.repaint(); // Redibujar el panel con la nueva posición
                });

                try {
                    Thread.sleep(200); // Pausa de 200 ms para el movimiento
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Limpiar la posición anterior del carro
                panel.limpiarCarro(carril, columna);

                columna++;
            } else {
                // Si el semáforo está rojo, el carro se detiene
                try {
                    Thread.sleep(500); // Espera de 500 ms si el semáforo está rojo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Una vez que el carro ha pasado, se verifica si todos los carros han pasado
        synchronized (panel) {
            boolean todosPasaron = true;
            for (Carro carro : panel.getCarros()) {
                if (carro.getColumna() < 20) {
                    todosPasaron = false;
                    break;
                }
            }
            if (todosPasaron) {
                System.exit(0); // Terminar el programa cuando todos los carros hayan pasado
            }
        }
    }

    public int getColumna() {
        return columna;
    }
}
