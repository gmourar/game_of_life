package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LifeFrame extends JFrame {
    private LifePanel lifePanel;

    public LifeFrame(int width, int height) {
        setTitle("Game of Life");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Cria o painel principal da simulação
        lifePanel = new LifePanel(width, height);
        add(lifePanel, BorderLayout.CENTER);

        // Adiciona controles de interface
        addControls();

        setVisible(true);
    }

    // Adiciona botões de controle e sliders
    private void addControls() {
        JPanel controlPanel = new JPanel();

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lifePanel.start();
            }
        });

        JButton pauseButton = new JButton("Pause");
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lifePanel.pause();
            }
        });

        JButton restartButton = new JButton("Reiniciar");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lifePanel.restart();
            }
        });

        JLabel speedLabel = new JLabel("velocidade:");
        JSlider speedSlider = new JSlider(10, 1000, 80);
        speedSlider.addChangeListener(e -> lifePanel.setSpeed(speedSlider.getValue()));

        JLabel sizeLabel = new JLabel("Tamanho:");
        JSlider sizeSlider = new JSlider(5, 50, 10);
        sizeSlider.addChangeListener(e -> lifePanel.setSize(sizeSlider.getValue()));

        controlPanel.add(startButton);
        controlPanel.add(pauseButton);
        controlPanel.add(restartButton);
        controlPanel.add(speedLabel);
        controlPanel.add(speedSlider);
        controlPanel.add(sizeLabel);
        controlPanel.add(sizeSlider);

        add(controlPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        int width = args.length > 0 ? Integer.parseInt(args[0]) : 800;
        int height = args.length > 1 ? Integer.parseInt(args[1]) : 600;
        new LifeFrame(width, height);
    }
}
