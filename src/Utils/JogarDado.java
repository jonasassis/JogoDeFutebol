package Utils;

import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class JogarDado {

    public int numeroSorteado;
    String caminho;

    public JogarDado() {
    }

    /*
     Responsável por jogar o dado e mostrar na tela.
     */
    public int girarDado() {

        Random dado = new Random();
        for (int i = 0; i < 6; i++) {
            numeroSorteado = dado.nextInt(6);
            numeroSorteado++;
        }
        if (numeroSorteado == 1) {
            caminho = "C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\dado1.png";
        } else if (numeroSorteado == 2) {
            caminho = "C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\dado2.png";
        } else if (numeroSorteado == 3) {
            caminho = "C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\dado3.png";
        } else if (numeroSorteado == 4) {
            caminho = "C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\dado4.png";
        } else if (numeroSorteado == 5) {
            caminho = "C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\dado5.png";
        } else {
            caminho = "C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\dado6.png";
        }

        ImageIcon i = new ImageIcon(caminho);
        JLabel label = new JLabel(i);
        JOptionPane.showMessageDialog(null, label, "Resultado", JOptionPane.PLAIN_MESSAGE);
        return numeroSorteado;

    }

}
