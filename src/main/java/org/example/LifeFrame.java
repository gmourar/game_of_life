package org.example;

import javax.swing.JFrame;

public class LifeFrame {
    JFrame jf = new JFrame();

    public LifeFrame(int width, int height) {
        jf.add(new LifePanel(width, height));
        jf.setSize(width, height);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main (String[] args) {
        int width = 1100;  // Valor padrão
        int height = 800;  // Valor padrão

        if (args.length >= 2) {
            try {
                width = Integer.parseInt(args[0]);
                height = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Valores inválidos para largura e altura. Usando valores padrão.");
            }
        }

        new LifeFrame(width, height);
    }
}
