package graficas;

import matematicas.aritmetica;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ventanaAfin extends JPanel {

    private JSpinner spinAlfa, spinBeta, spinN;
    private JLabel lblMcdInfo, lblInversoInfo, lblFormulaCifrado, lblFormulaDescifrado;
    private JButton btnGenerar;

    public ventanaAfin() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

        panelCentral.add(crearPanelParametros());
        panelCentral.add(Box.createVerticalStrut(20));

        panelCentral.add(crearPanelInfoMatematica());
        panelCentral.add(Box.createVerticalStrut(20));

        this.add(panelCentral, BorderLayout.CENTER);

        this.add(crearPanelBotones(), BorderLayout.SOUTH);

        configurarEventos();
    }

    private JPanel crearPanelParametros() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel.setBorder(new TitledBorder("Configuración de Claves"));

        spinAlfa = new JSpinner(new SpinnerNumberModel(3, 0, 1000000, 1));
        spinBeta = new JSpinner(new SpinnerNumberModel(8, 0, 1000000, 1));
        spinN = new JSpinner(new SpinnerNumberModel(26, 0, 1000000, 1));

        Dimension tamanoSpinner = new Dimension(70, 25);
        spinAlfa.setPreferredSize(tamanoSpinner);
        spinBeta.setPreferredSize(tamanoSpinner);
        spinN.setPreferredSize(tamanoSpinner);

        panel.add(new JLabel("Alfa (\u03B1):"));
        panel.add(spinAlfa);
        
        panel.add(new JLabel("Beta (\u03B2):"));
        panel.add(spinBeta);
        
        panel.add(new JLabel("Módulo (n):"));
        panel.add(spinN);

        return panel;
    }

    private JPanel crearPanelInfoMatematica() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 15));
        panel.setBorder(new TitledBorder("Validación Matemática y Funciones"));

        lblMcdInfo = new JLabel("MCD(\u03B1, n): -");
        lblInversoInfo = new JLabel("\u03B1\u207B\u00B9 mod n: -");
        lblFormulaCifrado = new JLabel("C \u2261 (\u03B1*p + \u03B2) mod n");
        lblFormulaDescifrado = new JLabel("p \u2261 \u03B1\u207B\u00B9*(C - \u03B2) mod n");

        Font fontInfo = new Font("Monospaced", Font.BOLD, 16);
        lblMcdInfo.setFont(fontInfo);
        lblInversoInfo.setFont(fontInfo);
        lblFormulaCifrado.setFont(fontInfo);
        lblFormulaDescifrado.setFont(fontInfo);

        panel.add(lblMcdInfo);
        panel.add(lblInversoInfo);
        panel.add(lblFormulaCifrado);
        panel.add(lblFormulaDescifrado);

        return panel;
    }

    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        btnGenerar = new JButton("Mostrar Funciones");
        btnGenerar.setFont(new Font("Arial", Font.BOLD, 14));
        
        panel.add(btnGenerar);
        
        return panel;
    }

    private void configurarEventos() {
        btnGenerar.addActionListener(e -> procesarAccion());
    }

    private void procesarAccion() {
        try {
            int alfa = (int) spinAlfa.getValue();
            int beta = (int) spinBeta.getValue();
            int n = (int) spinN.getValue();

            int mcd = aritmetica.calcularMCD(alfa, n);
            lblMcdInfo.setText("MCD(" + alfa + ", " + n + "): " + mcd);

            if (mcd != 1) {
                lblMcdInfo.setForeground(Color.RED);
                lblInversoInfo.setText("\u03B1\u207B\u00B9 mod n: ERROR");
                lblInversoInfo.setForeground(Color.RED);
                lblFormulaCifrado.setText("C = Invalido");
                lblFormulaDescifrado.setText("p = Invalido");
                
                JOptionPane.showMessageDialog(this,
                        "Alfa y n deben ser coprimos. MCD actual = " + mcd,
                        "Error de Clave", JOptionPane.ERROR_MESSAGE);
                return;
            }
            lblMcdInfo.setForeground(new Color(0, 100, 0));
            lblInversoInfo.setForeground(new Color(0, 100, 0));

            int alfaInv = aritmetica.inversoMultiplicativo(alfa, n); 
            
            lblInversoInfo.setText("\u03B1\u207B\u00B9 mod n: " + alfaInv);
            lblFormulaCifrado.setText("C \u2261 (" + alfa + "*p + " + beta + ") mod " + n);
            lblFormulaDescifrado.setText("p \u2261 " + alfaInv + "*(C - " + beta + ") mod " + n);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}