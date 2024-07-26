package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LifePanel extends JPanel implements ActionListener {
    int xPanel;
    int yPanel;
    int size = 10; // Tamanho de cada célula
    int xWidth;
    int yHeight;
    int[][] life;
    int[][] beforeLife;
    boolean starts = true;
    Timer timer;

    public LifePanel(int width, int height) {
        xPanel = width;
        yPanel = height;
        xWidth = xPanel / size;
        yHeight = yPanel / size;
        life = new int[xWidth][yHeight];
        beforeLife = new int[xWidth][yHeight];
        setSize(xPanel, yPanel);
        setLayout(null);

        // Inicializa o timer com intervalo de 80ms
        timer = new Timer(80, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.DARK_GRAY);
        g.setColor(Color.BLACK);

        // Desenha a grade
        grid(g);

        // Preenche o array inicial se for a primeira execução
        spawn();

        // Exibe as células
        display(g);
    }

    // Desenha a grade no painel
    private void grid(Graphics g) {
        for (int i = 0; i <= xWidth; i++) {
            g.drawLine(i * size, 0, i * size, yPanel);
        }
        for (int i = 0; i <= yHeight; i++) {
            g.drawLine(0, i * size, xPanel, i * size);
        }
    }

    // Popula o array inicial com células vivas aleatórias
    private void spawn() {
        if (starts) {
            for (int x = 0; x < xWidth; x++) {
                for (int y = 0; y < yHeight; y++) {
                    if ((int) (Math.random() * 10) == 0) {
                        beforeLife[x][y] = 1;
                    }
                }
            }
            starts = false;
        }
    }

    // Exibe as células no painel
    private void display(Graphics g) {
        copyArray();
        for (int x = 0; x < xWidth; x++) {
            for (int y = 0; y < yHeight; y++) {
                if (life[x][y] == 1) {
                    g.setColor(Color.GREEN); // Células vivas normais
                    g.fillRect(x * size, y * size, size, size);
                }
            }
        }
    }

    // Copia o estado atual das células para a matriz de "antes"
    private void copyArray() {
        for (int x = 0; x < xWidth; x++) {
            for (int y = 0; y < yHeight; y++) {
                life[x][y] = beforeLife[x][y];
            }
        }
    }

    // Verifica o número de vizinhos vivos ao redor de uma célula
    private int check(int x, int y) {
        int alive = 0;

        // Considera os vizinhos ao redor (com wrapping de borda)
        int[] neighbors = {-1, 0, 1};
        for (int dx : neighbors) {
            for (int dy : neighbors) {
                if (dx != 0 || dy != 0) {
                    int nx = (x + dx + xWidth) % xWidth;
                    int ny = (y + dy + yHeight) % yHeight;
                    alive += life[nx][ny];
                }
            }
        }

        return alive;
    }

    // Atualiza o estado das células com base nas regras do jogo
    @Override
    public void actionPerformed(ActionEvent e) {
        int alive;
        for (int x = 0; x < xWidth; x++) {
            for (int y = 0; y < yHeight; y++) {
                alive = check(x, y);

                if (alive == 3) {
                    beforeLife[x][y] = 1; // Célula nasce
                } else if (alive == 2 && life[x][y] == 1) {
                    beforeLife[x][y] = 1; // Célula sobrevive
                } else {
                    beforeLife[x][y] = 0; // Célula morre
                }
            }
        }
        repaint();
    }

    // Inicia a simulação
    public void start() {
        starts = true;
        timer.start();
    }

    // Pausa a simulação
    public void pause() {
        timer.stop();
    }

    // Reinicia a simulação
    public void restart() {
        starts = true;
        for (int x = 0; x < xWidth; x++) {
            for (int y = 0; y < yHeight; y++) {
                life[x][y] = 0;
                beforeLife[x][y] = 0;
            }
        }
        timer.start();
    }

    // Define a velocidade da simulação
    public void setSpeed(int delay) {
        timer.setDelay(delay);
    }

    // Altera o tamanho das células
    public void setSize(int newSize) {
        size = newSize;
        xWidth = xPanel / size;
        yHeight = yPanel / size;
        life = new int[xWidth][yHeight];
        beforeLife = new int[xWidth][yHeight];
        starts = true;
    }
}
