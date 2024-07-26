package org.example;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LifePanel extends JPanel implements ActionListener {
    int xPanel;
    int yPanel;
    int size = 10;
    int xWidth;
    int yHeight;
    int[][] life;
    int[][] beforeLife;
    boolean starts = true;

    public LifePanel(int width, int height) {
        xPanel = width;
        yPanel = height;
        xWidth = xPanel / size;
        yHeight = yPanel / size;
        life = new int[xWidth][yHeight];
        beforeLife = new int[xWidth][yHeight];
        setSize(xPanel, yPanel);
        setLayout(null);

        new Timer(80, this).start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.DARK_GRAY);
        g.setColor(Color.BLACK);

        grid(g);
        spawn(g);
        display(g);
    }

    private void grid(Graphics g) {
        for (int i = 0; i <= xWidth; i++) {
            g.drawLine(i * size, 0, i * size, yPanel); // coluna
        }
        for (int i = 0; i <= yHeight; i++) {
            g.drawLine(0, i * size, xPanel, i * size); // linha
        }
    }

    private void spawn(Graphics g) {
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

    private void display(Graphics g) {
        g.setColor(Color.GREEN);
        copyArray();
        for (int x = 0; x < xWidth; x++) {
            for (int y = 0; y < yHeight; y++) {
                if (life[x][y] == 1) {
                    g.fillRect(x * size, y * size, size, size);
                }
            }
        }
    }

    private void copyArray() {
        for (int x = 0; x < xWidth; x++) {
            for (int y = 0; y < yHeight; y++) {
                life[x][y] = beforeLife[x][y];
            }
        }
    }

    private int check(int x, int y) {
        int alive = 0;

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

    public void actionPerformed(ActionEvent e) {
        int alive;
        for (int x = 0; x < xWidth; x++) {
            for (int y = 0; y < yHeight; y++) {
                alive = check(x, y);

                if (alive == 3) {
                    beforeLife[x][y] = 1;
                } else if (alive == 2 && life[x][y] == 1) {
                    beforeLife[x][y] = 1;
                } else {
                    beforeLife[x][y] = 0;
                }
            }
        }
        repaint();
    }
}
