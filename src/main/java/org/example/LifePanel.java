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
    int[][] life = new int [xPanel/size][yPanel/size];
    int [][] beforeLife =  new int[xPanel/size][yPanel/size];
    boolean starts = true;

    public LifePanel(int width, int height) {
        xPanel = width;
        yPanel = height;
        life = new int[xPanel / size][yPanel / size];
        setSize(xPanel, yPanel);
        setLayout(null);

        new Timer (80 , this).start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.DARK_GRAY);
        g.setColor(Color.BLACK);

        grid(g);
        spawn(g);
        display(g);
    }

    private void grid (Graphics g){
        for (int i = 0; i < life.length; i++) {
            g.drawLine( 0, i * size, xPanel , i * size ); // linha
            g.drawLine(i * size, 0 , i * size, yPanel); // coluna
        }
    }

    private void spawn(Graphics g){
        if (starts){
            for (int x = 0; x < life.length; x++){
                for (int y = 0; y < (yPanel / size ); y++){
                    if ((int)(Math.random() * 10) == 0){
                        beforeLife[x][y] = 1;
                    }
                }

            }
            starts = false;
        }
    }

    private void display (Graphics g){
        g.setColor(Color.GREEN);
        copyArray();
        for (int x = 0; x < life.length; x++){
            for (int y = 0; y < (yPanel / size ); y++){
                if (life[x][y] == 1){
                    g.fillRect(x * size , y * size , size, size);
                }
            }

        }
    }

    private void copyArray(){
        for (int x = 0; x < life.length; x++){
            for (int y = 0; y < (yPanel / size ); y++){
               life[x][y] = beforeLife[x][y];
            }

        }
    }

    private int check(int x , int y){
        int alive = 0;
        alive += life[x - 1][y - 1];
        alive += life[x][y - 1];
        alive += life[x + 1][y - 1];
        alive += life[x - 1][y];
        alive += life[x + 1][y];
        alive += life[x - 1][y + 1];
        alive += life[x][y + 1];
        alive += life[x + 1][y + 1];

        return alive;
    }

    public void actionPerformed (ActionEvent e){
        int alive;
        for (int x = 0; x < life.length; x++){
            for (int y = 0; y < (yPanel / size ); y++){
                alive = check(x, y);

            if (alive == 3){
                beforeLife[x][y] = 1;
            }
            else if( alive == 2 && life[x][y] == 1) {
                beforeLife[x][y] = 1;
            }
            else {
                beforeLife[x][y] = 0;
            }


            }
        }


        repaint();
    }
}
