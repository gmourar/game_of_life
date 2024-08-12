package org.example;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LifePanel extends JPanel implements ActionListener {
    // Variáveis para armazenar as dimensões do painel e da grade.
    int xPanel;
    int yPanel;
    int size = 10;  // Tamanho de cada célula na grade.
    int xWidth;
    int yHeight;
    int[][] life;  // Matriz para o estado atual das células.
    int[][] beforeLife;  // Matriz para armazenar o estado anterior das células.
    boolean starts = true;  // Indica se o jogo está começando.

    // Construtor do painel, que define o tamanho do painel e inicializa as matrizes.
    public LifePanel(int width, int height) {
        xPanel = width;
        yPanel = height;
        xWidth = xPanel / size;
        yHeight = yPanel / size;
        life = new int[xWidth][yHeight];
        beforeLife = new int[xWidth][yHeight];
        setSize(xPanel, yPanel);
        setLayout(null);

        // Inicia um Timer que chama o método actionPerformed a cada 80 milissegundos.
        new Timer(80, this).start();
    }

    // Método responsável por desenhar o conteúdo do painel.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Define a cor de fundo do painel.
        setBackground(Color.DARK_GRAY);
        // Define a cor das células e da grade.
        g.setColor(Color.BLACK);

        // Desenha a grade.
        grid(g);
        // Preenche as células iniciais, se for a primeira execução.
        spawn(g);
        // Desenha as células vivas na tela.
        display(g);
    }

    // Método para desenhar a grade no painel.
    private void grid(Graphics g) {
        for (int i = 0; i <= xWidth; i++) {
            g.drawLine(i * size, 0, i * size, yPanel); // Desenha as colunas.
        }
        for (int i = 0; i <= yHeight; i++) {
            g.drawLine(0, i * size, xPanel, i * size); // Desenha as linhas.
        }
    }

    // Método para gerar as células iniciais aleatoriamente.
    private void spawn(Graphics g) {
        if (starts) {
            for (int x = 0; x < xWidth; x++) {
                for (int y = 0; y < yHeight; y++) {
                    // 10% de chance de uma célula nascer viva.
                    if ((int) (Math.random() * 10) == 0) {
                        beforeLife[x][y] = 1;
                    }
                }
            }
            starts = false;  // Impede que essa lógica seja executada mais de uma vez.
        }
    }

    // Método para desenhar as células vivas no painel.
    private void display(Graphics g) {
        g.setColor(Color.GREEN);
        copyArray();  // Copia o estado anterior para o estado atual.
        for (int x = 0; x < xWidth; x++) {
            for (int y = 0; y < yHeight; y++) {
                if (life[x][y] == 1) {
                    g.fillRect(x * size, y * size, size, size);  // Desenha um quadrado verde para cada célula viva.
                }
            }
        }
    }

    // Método para copiar o estado anterior para o estado atual.
    private void copyArray() {
        for (int x = 0; x < xWidth; x++) {
            for (int y = 0; y < yHeight; y++) {
                life[x][y] = beforeLife[x][y];
            }
        }
    }

    // Método para verificar quantas células vizinhas estão vivas.
    private int check(int x, int y) {
        int alive = 0;

        // Soma o estado das células vizinhas (1 se viva, 0 se morta).
        alive += life[(x + xWidth - 1) % xWidth][(y + yHeight - 1) % yHeight];
        alive += life[(x + xWidth) % xWidth][(y + yHeight - 1) % yHeight];
        alive += life[(x + xWidth + 1) % xWidth][(y + yHeight - 1) % yHeight];
        alive += life[(x + xWidth - 1) % xWidth][(y + yHeight) % yHeight];
        alive += life[(x + xWidth + 1) % xWidth][(y + yHeight) % yHeight];
        alive += life[(x + xWidth - 1) % xWidth][(y + yHeight + 1) % yHeight];
        alive += life[(x + xWidth) % xWidth][(y + yHeight + 1) % yHeight];
        alive += life[(x + xWidth + 1) % xWidth][(y + yHeight + 1) % yHeight];

        return alive;
    }

    // Método que é chamado a cada 80ms pelo Timer para atualizar o estado das células.
    public void actionPerformed(ActionEvent e) {
        int alive;
        // Percorre todas as células e decide se elas vivem ou morrem na próxima geração.
        for (int x = 0; x < xWidth; x++) {
            for (int y = 0; y < yHeight; y++) {
                alive = check(x, y);

                // Aplica as regras do Jogo da Vida:
                if (alive == 3) {
                    beforeLife[x][y] = 1;  // Nasce uma nova célula se exatamente 3 vizinhos estão vivos.
                } else if (alive == 2 && life[x][y] == 1) {
                    beforeLife[x][y] = 1;  // Mantém a célula viva se tem exatamente 2 vizinhos vivos.
                } else {
                    beforeLife[x][y] = 0;  // Caso contrário, a célula morre.
                }
            }
        }
        repaint();  // Redesenha o painel com o novo estado das células.
    }
}
