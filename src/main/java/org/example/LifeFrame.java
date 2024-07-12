package org.example;
import javax.swing.JFrame;

public class LifeFrame {
    JFrame jf = new JFrame();

    public LifeFrame() {

        jf.add(new LifePanel());

        jf.setSize(1100 , 800);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main (String[] args) {
        new LifeFrame();

    }



}