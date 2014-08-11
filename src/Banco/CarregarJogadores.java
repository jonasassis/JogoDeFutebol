package Banco;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jonas
 */
public class CarregarJogadores {

    public CarregarJogadores() {

    }
    
    /*
    Função para carregar os jogadores no combobox, onde escolhe quem irá jogar.
    */
    public void Carregar(JComboBox cbJogador1, JComboBox cbJogador2) {
        
        try {
            FileReader arquivo = new FileReader("jogador.txt");
            BufferedReader lerArquivo = new BufferedReader(arquivo);

            
            String linha = lerArquivo.readLine();
            
            cbJogador1.addItem(linha);
            cbJogador2.addItem(linha);

            while (linha != null) {

                linha = lerArquivo.readLine();
                cbJogador1.addItem(linha);
                cbJogador2.addItem(linha);
            }
            
            cbJogador1.removeItemAt(cbJogador1.getItemCount() - 1);
            cbJogador2.removeItemAt(cbJogador2.getItemCount() - 1);

        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

    }

}
