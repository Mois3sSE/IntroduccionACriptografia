package Visuales;

import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import Funcionalidad.*;
import java.awt.*;
import java.awt.event.*;

public class menuDescifrado extends JPanel{
    menuDescifrado(){
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel rutasTxt = new JPanel();
        rutasTxt.setBackground(Color.LIGHT_GRAY);
        rutasTxt.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel labelRutaTxt = new JLabel("Ruta del archivo txt a descifrar:");
        JTextField rutaTxt = new JTextField(20);
        JButton botonExaminarTxt = new JButton("Examinar");
        rutasTxt.add(labelRutaTxt);
        rutasTxt.add(rutaTxt);
        rutasTxt.add(botonExaminarTxt);

        JPanel llave = new JPanel();
        llave.setBackground(Color.LIGHT_GRAY);
        llave.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel labelLlave = new JLabel("Llave:");
        JTextField campoLlave = new JTextField(20);
        llave.add(Box.createHorizontalStrut(40));
        llave.add(labelLlave);
        llave.add(Box.createHorizontalStrut(30));
        llave.add(campoLlave);


        JButton botonDescifrar = new JButton("Descifrar");
        botonDescifrar.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createVerticalStrut(30));
        this.add(rutasTxt);
        this.add(llave);
        this.add(Box.createVerticalStrut(35));
        this.add(botonDescifrar);

        botonExaminarTxt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt"));
                int result = fileChooser.showOpenDialog(menuDescifrado.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    rutaTxt.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        botonDescifrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String rutaArchivo = rutaTxt.getText();
                System.out.println("Ruta del archivo a descifrar: " + rutaArchivo);
                if (rutaArchivo.isEmpty() || campoLlave.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(menuDescifrado.this, "Por favor, rellene todos los campos de texto.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    SecretKey llaveHash = motorCriptografico.hashDeLlave(campoLlave.getText());
                    GCMParameterSpec parametrosDescifrador = new GCMParameterSpec(128, manejoArchivos.leerCifradoV(rutaArchivo));
                    byte[] mensajeDescifrado = motorCriptografico.descifrarMensaje(llaveHash, parametrosDescifrador, manejoArchivos.leerCifradoM(rutaArchivo));
                    manejoArchivos.crearTxt(rutaArchivo, mensajeDescifrado);
                } catch (Exception ex) {
                        JOptionPane.showMessageDialog(menuDescifrado.this, "Error al descifrar el mensaje: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            
            }
        });
    }
    
}
