package Visuales;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Introduction to Crypthography - 6CM1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        JTabbedPane pestanas = new JTabbedPane();

        pestanas.addTab("Pagina Inicial", new panelnicial());
        pestanas.addTab("Cifrado", new menuCifrado());
        pestanas.addTab("Descifrado", new menuDescifrado());

        frame.add(pestanas);
        frame.setVisible(true);
    }
}
