/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import HILOS.Carro;
import HILOS.Caseta;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CarreteraPanel extends JPanel {
    private final int[][] carretera;
    private final List<Caseta> casetas;
    private final Carro[] carros;

    public CarreteraPanel(Carro[] carros, List<Caseta> casetas) {
        this.carros = carros;
        this.casetas = casetas;
        this.carretera = new int[5][20]; // 5 carriles, 20 columnas
        setPreferredSize(new Dimension(800, 400));
    }

    public synchronized void moverCarro(int carril, int columna) {
        carretera[carril][columna] = 1;
        actualizar();
    }

    public synchronized void limpiarCarro(int carril, int columna) {
        carretera[carril][columna] = 0;
        actualizar();
    }

    public synchronized void actualizar() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar la carretera
        g.setColor(Color.GRAY);
        g.fillRect(0, 50, getWidth(), 300);

        // Dibujar carriles
        g.setColor(Color.WHITE);
        for (int i = 0; i <= 5; i++) {
            g.drawLine(0, 50 + i * 50, getWidth(), 50 + i * 50);
        }

        // Dibujar casetas y semáforos
        for (int i = 0; i < casetas.size(); i++) {
            Caseta caseta = casetas.get(i);
            // Caseta
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(350, 60 + i * 60, 50, 40);

            // Semáforo
            g.setColor(caseta.isVerde() ? Color.GREEN : Color.RED);
            g.fillOval(410, 60 + i * 60, 20, 20);
        }

        // Dibujar carros
        g.setColor(Color.BLUE);
        for (int carril = 0; carril < carretera.length; carril++) {
            for (int col = 0; col < carretera[0].length; col++) {
                if (carretera[carril][col] == 1) {
                    g.fillOval(col * 30, 70 + carril * 50, 20, 20);
                }
            }
        }
    }

    public Carro[] getCarros() {
        return carros;
    }
}
