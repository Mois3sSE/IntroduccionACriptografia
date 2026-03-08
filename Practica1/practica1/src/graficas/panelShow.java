package graficas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.filechooser.FileNameExtensionFilter;
import funcionalidad.showMetodos;

public class panelShow extends JPanel {
    private boolean validarArchivo(String ruta, String extension) {
        if (ruta == null || ruta.isEmpty()) {
            return false;
        }
        File archivo = new File(ruta);
        if (!archivo.exists() || archivo.isDirectory()) {
            return false;
        }
        String rutaMinuscula = ruta.toLowerCase();
        String extensionEsperada = extension.toLowerCase();

        return rutaMinuscula.endsWith(extensionEsperada);
    }

    panelShow() {
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel panelCifradoBmp = new JPanel();
        panelCifradoBmp.setBackground(Color.LIGHT_GRAY);
        panelCifradoBmp.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel labelRutaCifradoBmp = new JLabel("Ruta del archivo bmp:");
        JTextField rutaCifradoBmp = new JTextField(20);
        JButton botonExaminarCifradoBmp = new JButton("Examinar");
        panelCifradoBmp.add(labelRutaCifradoBmp);
        panelCifradoBmp.add(rutaCifradoBmp);
        panelCifradoBmp.add(botonExaminarCifradoBmp);

        JButton botonDescifrar = new JButton("Descifrar");
        botonDescifrar.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createVerticalStrut(100));
        this.add(panelCifradoBmp);
        this.add(Box.createVerticalStrut(35));
        this.add(botonDescifrar);

        botonExaminarCifradoBmp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser buscador = new JFileChooser();
                buscador.setFileFilter(new FileNameExtensionFilter("Archivos Bitmap *.bmp", "bmp"));
                int result = buscador.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    rutaCifradoBmp.setText(buscador.getSelectedFile().getAbsolutePath());
                }
            }

        });

        botonDescifrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                showMetodos show = new showMetodos();

                if (!validarArchivo(rutaCifradoBmp.getText(), "bmp")) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un archivo de imagen válido (.bmp)",
                            "Archivo no válido", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    BufferedImage imagen = show.leerBmp(rutaCifradoBmp.getText());
                    show.descifrarMensaje(imagen);
                    show.imprimirMensaje(rutaCifradoBmp.getText());

                    rutaCifradoBmp.setText("");
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(null, "No se encontró algún mensaje oculto.", "Error", JOptionPane.ERROR_MESSAGE);

                } 
                
                catch (Exception ex) {

                    ex.printStackTrace();
                }
            }

        });

    }
}
