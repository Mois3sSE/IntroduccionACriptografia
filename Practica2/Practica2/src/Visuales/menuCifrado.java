package Visuales;

import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import Funcionalidad.*;

public class menuCifrado extends JPanel {
    menuCifrado(){
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel rutasTxt = new JPanel();
        rutasTxt.setBackground(Color.LIGHT_GRAY);
        rutasTxt.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel labelRutaTxt = new JLabel("Ruta del archivo txt:");
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


        JButton botonCifrar = new JButton("Cifrar");
        botonCifrar.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createVerticalStrut(30));
        this.add(rutasTxt);
        this.add(llave);
        this.add(Box.createVerticalStrut(35));
        this.add(botonCifrar);

            botonExaminarTxt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt"));
                    int result = fileChooser.showOpenDialog(menuCifrado.this);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        rutaTxt.setText(fileChooser.getSelectedFile().getAbsolutePath());
                    }
                }
            });

            botonCifrar.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    String rutaArchivo = rutaTxt.getText();
                    String llave = campoLlave.getText();
                    if (rutaArchivo.isEmpty() || llave.isEmpty()) {
                        JOptionPane.showMessageDialog(menuCifrado.this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        byte[] mensaje = manejoArchivos.leerTxt(rutaArchivo);
                        SecretKey llaveHash = motorCriptografico.hashDeLlave(campoLlave.getText());
                        GCMParameterSpec parametrosCifrador = new GCMParameterSpec(128, motorCriptografico.creacionVector());
                        byte[] mensajeCifrado = motorCriptografico.cifrarMensaje(llaveHash, parametrosCifrador, mensaje);
                        manejoArchivos.crearTxt(rutaArchivo, mensajeCifrado, parametrosCifrador.getIV());
                        JOptionPane.showMessageDialog(menuCifrado.this, "Mensaje cifrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(menuCifrado.this, "Error al cifrar el mensaje: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    
                }
            });

    }

}
