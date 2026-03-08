package graficas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.filechooser.FileNameExtensionFilter;
import funcionalidad.hideMetodos;

public class panelHide extends JPanel {
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

    panelHide() {
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

        JPanel rutasBmp = new JPanel();
        rutasBmp.setBackground(Color.LIGHT_GRAY);
        rutasBmp.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel labelRutaBmp = new JLabel("Ruta del archivo bmp:");
        JTextField rutaBmp = new JTextField(20);
        JButton botonExaminarBmp = new JButton("Examinar");
        rutasBmp.add(labelRutaBmp);
        rutasBmp.add(rutaBmp);
        rutasBmp.add(botonExaminarBmp);

        JButton botonCifrar = new JButton("Cifrar");
        botonCifrar.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createVerticalStrut(30));
        this.add(rutasTxt);
        this.add(rutasBmp);
        this.add(Box.createVerticalStrut(35));
        this.add(botonCifrar);

        botonExaminarTxt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser buscador = new JFileChooser();
                buscador.setFileFilter(new FileNameExtensionFilter("Archivos de texto *.txt", "txt"));
                int result = buscador.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    rutaTxt.setText(buscador.getSelectedFile().getAbsolutePath());
                }
            }

        });

        botonExaminarBmp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser buscador = new JFileChooser();
                buscador.setFileFilter(new FileNameExtensionFilter("Archivos Bitmap *.bmp", "bmp"));
                int result = buscador.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    rutaBmp.setText(buscador.getSelectedFile().getAbsolutePath());
                }
            }

        });

        botonCifrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideMetodos hide = new hideMetodos();

                if (!validarArchivo(rutaTxt.getText(), "txt")) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un archivo de texto válido (.txt)",
                            "Archivo no válido", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!validarArchivo(rutaBmp.getText(), "bmp")) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un archivo de imagen válido (.bmp)",
                            "Archivo no válido", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    byte[] mensaje = hide.leerTxt(rutaTxt.getText());
                    BufferedImage imagen = hide.leerBmp(rutaBmp.getText());
                    hide.cifrarMensaje(mensaje, imagen);
                    hide.guardarBmp(imagen, rutaBmp.getText());

                } catch (Exception ex) {

                    ex.printStackTrace();
                }
                rutaBmp.setText("");
                rutaTxt.setText("");
            }

        });
    }
}
