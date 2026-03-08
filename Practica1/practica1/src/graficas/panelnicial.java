package graficas;

import javax.swing.*;
import java.awt.*;

public class panelnicial extends JPanel {
    panelnicial() {
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel noPractica = new JLabel("Practica 1");
        noPractica.setFont(new Font("Arial", Font.BOLD, 18));
        JLabel tituloPractica = new JLabel("Manipulación a nivel de bits Txt");
        tituloPractica.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel profesora = new JLabel("Profesora: Cortez Duarte Nidia Asunción");
        JLabel autor = new JLabel("Autor: Sandoval Espinoza Moises");
        JLabel grupo = new JLabel("Grupo: 6CM1");

        noPractica.setAlignmentX(Component.CENTER_ALIGNMENT);
        tituloPractica.setAlignmentX(Component.CENTER_ALIGNMENT);
        profesora.setAlignmentX(Component.CENTER_ALIGNMENT);
        autor.setAlignmentX(Component.CENTER_ALIGNMENT);
        grupo.setAlignmentX(Component.CENTER_ALIGNMENT);

        Box.createVerticalStrut(50);
        this.add(Box.createVerticalStrut(15));
        this.add(noPractica);
        this.add(Box.createVerticalStrut(15));
        this.add(tituloPractica);
        this.add(Box.createVerticalStrut(25));
        this.add(profesora);
        this.add(Box.createVerticalStrut(25));
        this.add(autor);
        this.add(Box.createVerticalStrut(25));
        this.add(grupo);
        this.add(Box.createVerticalStrut(25));
    }
}
