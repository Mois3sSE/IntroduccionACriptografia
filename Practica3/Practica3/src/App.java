import graficas.*;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;



public class App {
    public static void main(String[] args) {
       JFrame frame = new JFrame("Introduction to Crypthography - 6CM1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 500);
        frame.setLocationRelativeTo(null);

        JTabbedPane pestanas = new JTabbedPane();
        pestanas.add("Pagina inicial",new ventanaPrincipal()); 
        pestanas.add("Cifrado Afin",new ventanaAfin()); 
        
        frame.add(pestanas);
        frame.setVisible(true); 
    }
}