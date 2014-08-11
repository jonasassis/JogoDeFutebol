/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco;

import Telas.CadastroNovoJogador;
import Utils.Jogador;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Jonas
 */
public class GravaArquivo {

    JLabel lblnome;
    JButton btngrava;
    JTextField txtnome;
    File arquivo;
    FileWriter escreve;

    public GravaArquivo() {

    }
    
    /*
    Função para gravar os jogadores no arquivo jogador.txt.
    */

    public void gravaJogadores(Jogador jogador) {

        arquivo = new File("jogador.txt");

        if (arquivo.exists() == false) {
            try {
                arquivo.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(CadastroNovoJogador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            escreve = new FileWriter("jogador.txt", true);
            escreve.write(jogador.getNome() + "\r\n");
            escreve.close();
            JOptionPane.showMessageDialog(null, "Jogador salvo!");
        } catch (IOException ex) {
            Logger.getLogger(CadastroNovoJogador.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Houve algum erro!");
        }
    }
    
    /*
    Função para gravar o ranking.
    */

    public void gravaRanking(Jogador jogador) {

        arquivo = new File("ranking.txt");

        if (arquivo.exists() == false) {
            try {
                arquivo.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(CadastroNovoJogador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            escreve = new FileWriter("ranking.txt", true);
            escreve.write(jogador.getNome() + "#" + jogador.getMediaJogadas() 
                    + "#" + jogador.getVelocidadeMedia() + "#" + jogador.getQtCasasPercorridas()
                    + "#" + jogador.getStatus()+ "#\r\n");
            escreve.close();
        } catch (IOException ex) {
            Logger.getLogger(CadastroNovoJogador.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Houve algum erro!");
        }
    }
}
