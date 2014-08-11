package Telas;

import Banco.GravaArquivo;
import Utils.Jogador;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jonas
 */
public class CadastroNovoJogador extends JFrame implements ActionListener {

    JLabel lblnome;
    JButton btngrava;
    JTextField txtnome;
    File arquivo;
    FileWriter escreve;
    
        /*
    método construtor da tela.
    */

    public CadastroNovoJogador() {
        super("Registando novo jogador");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(400, 250);
        this.setVisible(true);

        lblnome = new JLabel("Nome:");
        btngrava = new JButton("Gravar");
        btngrava.addActionListener(this);
        txtnome = new JTextField();
        setLayout(null);

        lblnome.setBounds(10, 70, 45, 40);
        btngrava.setBounds(50, 150, 75, 50);
        txtnome.setBounds(80, 70, 255, 40);

        add(lblnome);
        add(btngrava);
        add(txtnome);

    }
    
  
    /*
    Evento pra pegar o click do botão.
    */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btngrava) {
            Jogador jogador = new Jogador();
            jogador.setNome(txtnome.getText().toUpperCase());
            GravaArquivo grava = new GravaArquivo();
            grava.gravaJogadores(jogador);
            this.dispose();
            

        }
    }



    }

