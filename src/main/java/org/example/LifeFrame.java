package org.example;

import javax.swing.JFrame;

public class LifeFrame {
    // Cria um objeto JFrame para representar a janela principal do jogo.
    JFrame jf = new JFrame();

    public LifeFrame(int width, int height) {
        jf.add(new LifePanel(width, height));
        // Define o tamanho da janela
        jf.setSize(width, height);
        // Torna a janela visível.
        jf.setVisible(true);

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main (String[] args) {
        // Define valores padrão para a largura e altura da janela.
        int width = 1100;  // Valor padrão
        int height = 800;  // Valor padrão

        // Se o usuário passar largura e altura como argumentos, tenta usá-los.
        if (args.length >= 2) {
            try {
                width = Integer.parseInt(args[0]);
                height = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                // Se os valores não forem válidos, exibe uma mensagem e usa os valores padrão.
                System.out.println("Valores inválidos para largura e altura. Usando valores padrão.");
            }
        }

        // Cria uma nova instância de LifeFrame com a largura e altura especificadas.
        new LifeFrame(width, height);
    }
}
