package calculo.de.gasto.controller;

import javax.swing.Icon;
import javax.swing.JOptionPane;

/**
 *
 * @author Neviton viana
 */
public class Excecao {
     Icon iconerro = new javax.swing.ImageIcon(getClass().getResource("/icons/erroalerta.png"));
     Icon iconaviso = new javax.swing.ImageIcon(getClass().getResource("/icons/alerta.png"));
     
    public void Error(String ex){
       
        JOptionPane.showMessageDialog(null, ex, "ERROR",JOptionPane.INFORMATION_MESSAGE,iconerro );
    }
    
    public void Aviso(String ex){
        JOptionPane.showMessageDialog(null, ex, "AVISO", JOptionPane.INFORMATION_MESSAGE, iconerro);
    }
}
