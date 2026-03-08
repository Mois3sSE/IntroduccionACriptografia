package Visuales;
import javax.swing.*;
import java.awt.*;

public class panelnicial extends JPanel {
    panelnicial() {
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel noPractica = new JLabel("Practica 2");
        noPractica.setFont(new Font("Arial", Font.BOLD, 18));
        JLabel tituloPractica = new JLabel("Función Criptográfica con archivos .txt");
        tituloPractica.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel profesora = new JLabel("Profesora: Cortez Duarte Nidia Asunción");
        JLabel etiquetaAutoresJLabel = new JLabel("Autores:");
        JLabel autores = new JLabel("<html>Arevalo Villanueva Eduardo<br>"+ 
        "Diaz Presas Angel Aaron<br>Sandoval Espinoza Moises </html>");
        
        JLabel grupo = new JLabel("Grupo: 6CM1");

        noPractica.setAlignmentX(Component.CENTER_ALIGNMENT);
        tituloPractica.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaAutoresJLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        profesora.setAlignmentX(Component.CENTER_ALIGNMENT);
        autores.setAlignmentX(Component.CENTER_ALIGNMENT);
        autores.setVerticalAlignment(JLabel.CENTER);
        autores.setHorizontalAlignment(SwingConstants.CENTER);
        grupo.setAlignmentX(Component.CENTER_ALIGNMENT);

        Box.createVerticalStrut(50);
        this.add(Box.createVerticalStrut(15));
        this.add(noPractica);
        this.add(Box.createVerticalStrut(15));
        this.add(tituloPractica);
        this.add(Box.createVerticalStrut(25));
        this.add(profesora);
        this.add(Box.createVerticalStrut(25));
        this.add(etiquetaAutoresJLabel);
        this.add(Box.createVerticalStrut(25));
        this.add(autores);
        this.add(Box.createVerticalStrut(25));
        this.add(grupo);
        this.add(Box.createVerticalStrut(25));
    }
}
