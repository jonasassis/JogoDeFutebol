package Telas;

import Utils.CalculoRanking;
import Utils.Jogador;
import Utils.JogarDado;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jonas
 */
public class Tabuleiro extends JFrame implements Runnable, ActionListener {

    /*
     Cria e instancia todos os objetos e variáveis utilizadas.
     */
    public JButton[][] btosTab = new JButton[17][27];
    JPanel panelTab;
    Thread executar = new Thread(this);

    public String jogador1, jogador2;
    public int gols;
    int posicaoJogador1 = 0;
    int posicaoJogador2 = 0;
    int posicaoBola = 0;
    int ultrapassou60 = 0;
    int vezJogador;
    int golsJogador1 = 0;
    int golsJogador2 = 0;
    int qtJogadas = 0;
    int casasPercorridasJogador1 = 0;
    int casasPercorridasJogador2 = 0;

    Tabuleiro(int vezJogador) {
        super("Jogo de Futebol");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1350, 700);
        configuraPanel();
        this.setVisible(true);
        btosTab[5][13].addActionListener(this);
        this.vezJogador = vezJogador;
        executar.start();
    }

    /*
     Configurções iniciais do tabuleiro, gerando o tabuleiro e colorindo.
     */
    private void configuraPanel() {
        panelTab = new JPanel(new GridLayout(17, 27)); // crio o JPanel que servira de tabuleiro;   
        panelTab.setBackground(Color.green);
        geraPrototipoTabuleiro();
        this.add(panelTab); // adiciono ele na janela;  
    }

    /*
     gera o tabuleiro, todos os botoes, colori os mesmos e coloca textos nos mesmos.
     */
    private void geraPrototipoTabuleiro() {

        for (int i = 0; i < btosTab.length; i++) { // loop que percorre as linhas do tabuleiro  
            for (int j = 0; j < btosTab[i].length; j++) { //loop que percorre as colunas do tabuleiro  
                btosTab[i][j] = new JButton("");
                btosTab[i][j].setBackground(Color.green);
                btosTab[i][j].setFont(new Font(btosTab[i][j].getFont().getName(), Font.BOLD, btosTab[i][j].getFont().getSize()));
                panelTab.add(btosTab[i][j]);
            }

        }

        //placares
        btosTab[2][5].setBackground(Color.blue);
        btosTab[2][5].setText("PL");
        btosTab[2][5].setForeground(Color.white);
        btosTab[2][6].setBackground(Color.blue);
        btosTab[2][6].setText("AC");
        btosTab[2][6].setForeground(Color.white);
        btosTab[2][7].setBackground(Color.blue);
        btosTab[2][7].setText("AR");
        btosTab[2][7].setForeground(Color.white);

        btosTab[2][19].setBackground(Color.blue);
        btosTab[2][19].setText("PL");
        btosTab[2][19].setForeground(Color.white);
        btosTab[2][20].setBackground(Color.blue);
        btosTab[2][20].setText("AC");
        btosTab[2][20].setForeground(Color.white);
        btosTab[2][21].setBackground(Color.blue);
        btosTab[2][21].setText("AR");
        btosTab[2][21].setForeground(Color.white);

        //parte de cima esquerda
        btosTab[0][0].setBackground(Color.yellow);
        btosTab[0][0].setToolTipText("ESCANTEIO: JOGA UM DADO UMA VEZ E SE TIRAR 6, MARCA GOL.");
        btosTab[0][0].setText("24");
        btosTab[0][1].setBackground(Color.white);
        btosTab[0][1].setText("23");
        btosTab[0][2].setBackground(Color.white);
        btosTab[0][2].setText("22");
        btosTab[0][3].setBackground(Color.yellow);
        btosTab[0][3].setToolTipText("FORA DE JOGO: RECUA 10 CASAS.");
        btosTab[0][3].setText("21");
        btosTab[0][4].setBackground(Color.white);
        btosTab[0][4].setText("20");
        btosTab[0][5].setBackground(Color.white);
        btosTab[0][5].setText("19");
        btosTab[0][6].setBackground(Color.yellow);
        btosTab[0][6].setToolTipText("RASTEIRA: JOGA UM DADO 3 VEZES, E SE TIRAR NUMERO PAR, MARCA GOL.");
        btosTab[0][6].setText("18");
        btosTab[0][7].setBackground(Color.white);
        btosTab[0][7].setText("17");
        btosTab[0][8].setBackground(Color.white);
        btosTab[0][8].setText("16");
        btosTab[0][9].setBackground(Color.white);
        btosTab[0][9].setText("15");
        btosTab[0][10].setBackground(Color.white);
        btosTab[0][10].setText("14");
        btosTab[0][11].setBackground(Color.white);
        btosTab[0][11].setText("13");
        btosTab[0][12].setBackground(Color.yellow);
        btosTab[0][12].setToolTipText("MÃO: JOGA DOIS DADOS, E SE "
                + "TIRAR NÚMEROS IGUAIS NOS 2, MARCA GOL; CASO CONTRÁRIO PASSA VEZ.");
        btosTab[0][12].setText("12");

        //parte de cima direita
        btosTab[0][14].setBackground(Color.yellow);
        btosTab[0][14].setToolTipText("MÃO: JOGA DOIS DADOS, E SE "
                + "TIRAR NÚMEROS IGUAIS NOS 2, MARCA GOL; CASO CONTRÁRIO PASSA VEZ.");
        btosTab[0][14].setText("36");
        btosTab[0][15].setBackground(Color.white);
        btosTab[0][15].setText("37");
        btosTab[0][16].setBackground(Color.white);
        btosTab[0][16].setText("38");
        btosTab[0][17].setBackground(Color.yellow);
        btosTab[0][17].setToolTipText("BOLA FORA: JOGA OUTRA VEZ.");
        btosTab[0][17].setText("39");
        btosTab[0][18].setBackground(Color.white);
        btosTab[0][18].setText("40");
        btosTab[0][19].setBackground(Color.white);
        btosTab[0][19].setText("41");
        btosTab[0][20].setBackground(Color.yellow);
        btosTab[0][20].setToolTipText("JOGO VIOLENTO: JOGA UM DADO 3 VEZES, E SE TIRAR NUMERO PAR, MARCA GOL.");
        btosTab[0][20].setText("42");
        btosTab[0][21].setBackground(Color.white);
        btosTab[0][21].setText("43");
        btosTab[0][22].setBackground(Color.white);
        btosTab[0][22].setText("44");
        btosTab[0][23].setBackground(Color.yellow);
        btosTab[0][23].setToolTipText("FORA DE JOGO: RECUA 10 CASAS.");
        btosTab[0][23].setText("45");
        btosTab[0][24].setBackground(Color.white);
        btosTab[0][24].setText("46");
        btosTab[0][25].setBackground(Color.white);
        btosTab[0][25].setText("47");
        btosTab[0][26].setBackground(Color.yellow);
        btosTab[0][26].setToolTipText("ESCANTEIO: JOGA UM DADO UMA VEZ E SE TIRAR 6, MARCA GOL.");
        btosTab[0][26].setText("48");

        //parte de baixo esquerda
        btosTab[16][0].setBackground(Color.yellow);
        btosTab[16][0].setToolTipText("ESCANTEIO: JOGA UM DADO UMA VEZ E SE TIRAR 6, MARCA GOL.");
        btosTab[16][0].setText("48");
        btosTab[16][1].setBackground(Color.white);
        btosTab[16][1].setText("47");
        btosTab[16][2].setBackground(Color.white);
        btosTab[16][2].setText("46");
        btosTab[16][3].setBackground(Color.yellow);
        btosTab[16][3].setToolTipText("FORA DE JOGO: RECUA 10 CASAS.");
        btosTab[16][3].setText("45");
        btosTab[16][4].setBackground(Color.white);
        btosTab[16][4].setText("44");
        btosTab[16][5].setBackground(Color.white);
        btosTab[16][5].setText("43");
        btosTab[16][6].setBackground(Color.yellow);
        btosTab[16][6].setToolTipText("JOGO VIOLENTO: JOGA UM DADO 3 VEZES, E SE TIRAR NUMERO PAR, MARCA GOL.");
        btosTab[16][6].setText("42");
        btosTab[16][7].setBackground(Color.white);
        btosTab[16][7].setText("41");
        btosTab[16][8].setBackground(Color.white);
        btosTab[16][8].setText("40");
        btosTab[16][9].setBackground(Color.yellow);
        btosTab[16][9].setToolTipText("BOLA FORA: JOGA OUTRA VEZ.");
        btosTab[16][9].setText("39");
        btosTab[16][10].setBackground(Color.white);
        btosTab[16][10].setText("38");
        btosTab[16][11].setBackground(Color.white);
        btosTab[16][11].setText("37");
        btosTab[16][12].setBackground(Color.yellow);
        btosTab[16][12].setToolTipText("MÃO: JOGA DOIS DADOS, E SE "
                + "TIRAR NÚMEROS IGUAIS NOS 2, MARCA GOL; CASO CONTRÁRIO PASSA VEZ.");
        btosTab[16][12].setText("36");

        //parte de baixo direita
        btosTab[16][14].setBackground(Color.yellow);
        btosTab[16][14].setToolTipText("MÃO: JOGA DOIS DADOS, E SE "
                + "TIRAR NÚMEROS IGUAIS NOS 2, MARCA GOL; CASO CONTRÁRIO PASSA VEZ.");
        btosTab[16][14].setText("12");
        btosTab[16][15].setBackground(Color.white);
        btosTab[16][15].setText("13");
        btosTab[16][16].setBackground(Color.white);
        btosTab[16][16].setText("14");
        btosTab[16][17].setBackground(Color.white);
        btosTab[16][17].setText("15");
        btosTab[16][18].setBackground(Color.white);
        btosTab[16][18].setText("16");
        btosTab[16][19].setBackground(Color.white);
        btosTab[16][19].setText("17");
        btosTab[16][20].setBackground(Color.yellow);
        btosTab[16][20].setToolTipText("RASTEIRA: JOGA UM DADO 3 VEZES, E SE TIRAR NUMERO PAR, MARCA GOL.");
        btosTab[16][20].setText("18");
        btosTab[16][21].setBackground(Color.white);
        btosTab[16][21].setText("19");
        btosTab[16][22].setBackground(Color.white);
        btosTab[16][22].setText("20");
        btosTab[16][23].setBackground(Color.yellow);
        btosTab[16][23].setToolTipText("FORA DE JOGO: RECUA 10 CASAS.");
        btosTab[16][23].setText("21");
        btosTab[16][24].setBackground(Color.white);
        btosTab[16][24].setText("22");
        btosTab[16][25].setBackground(Color.white);
        btosTab[16][25].setText("23");
        btosTab[16][26].setBackground(Color.yellow);
        btosTab[16][26].setToolTipText("ESCANTEIO: JOGA UM DADO UMA VEZ E SE TIRAR 6, MARCA GOL.");
        btosTab[16][26].setText("24");

        //gol
        btosTab[8][0].setBackground(Color.red);
        btosTab[8][0].setToolTipText("GOOOOOOOOOL!!!!");
        btosTab[8][0].setText("60");
        btosTab[8][26].setBackground(Color.red);
        btosTab[8][26].setToolTipText("GOOOOOOOOOL!!!!");
        btosTab[8][26].setText("60");

        //linha do meio
        //esquerda
        btosTab[8][13].setBackground(Color.black);
        btosTab[8][13].setText("0");
        btosTab[8][12].setBackground(Color.white);
        btosTab[8][12].setText("1");
        btosTab[8][11].setBackground(Color.white);
        btosTab[8][11].setText("2");
        btosTab[8][10].setBackground(Color.white);
        btosTab[8][10].setText("3");
        btosTab[8][9].setBackground(Color.white);
        btosTab[8][9].setText("4");
        btosTab[8][8].setBackground(Color.white);
        btosTab[8][8].setText("5");
        btosTab[8][7].setBackground(Color.white);
        btosTab[8][7].setText("6");
        btosTab[8][1].setBackground(Color.CYAN);
        btosTab[8][1].setToolTipText("DEFESA: RECUA 10 CASAS.");
        btosTab[8][1].setText("59");
        btosTab[8][2].setBackground(Color.white);
        btosTab[8][2].setText("58");
        btosTab[8][3].setBackground(Color.white);
        btosTab[8][3].setText("57");
        btosTab[8][4].setBackground(Color.white);
        btosTab[8][4].setText("56");
        btosTab[8][5].setBackground(Color.white);
        btosTab[8][5].setText("55");

        //direita
        btosTab[8][14].setBackground(Color.white);
        btosTab[8][14].setText("1");
        btosTab[8][15].setBackground(Color.white);
        btosTab[8][15].setText("2");
        btosTab[8][16].setBackground(Color.white);
        btosTab[8][16].setText("3");
        btosTab[8][17].setBackground(Color.white);
        btosTab[8][17].setText("4");
        btosTab[8][18].setBackground(Color.white);
        btosTab[8][18].setText("5");
        btosTab[8][19].setBackground(Color.white);
        btosTab[8][19].setText("6");
        btosTab[8][25].setBackground(Color.CYAN);
        btosTab[8][25].setToolTipText("DEFESA: RECUA 10 CASAS.");
        btosTab[8][25].setText("59");
        btosTab[8][24].setBackground(Color.white);
        btosTab[8][24].setText("58");
        btosTab[8][23].setBackground(Color.white);
        btosTab[8][23].setText("57");
        btosTab[8][22].setBackground(Color.white);
        btosTab[8][22].setText("56");
        btosTab[8][21].setBackground(Color.white);
        btosTab[8][21].setText("55");

        //lado direito meio do meio baixo
        btosTab[10][5].setBackground(Color.white);
        btosTab[10][5].setText("54");
        btosTab[11][4].setBackground(Color.yellow);
        btosTab[11][4].setToolTipText("FALTA: JOGA DOIS DADOS E SE A SOMA FOR SUPERIOR A 6, MARCA GOL.");
        btosTab[11][4].setText("53");
        btosTab[12][3].setBackground(Color.yellow);
        btosTab[12][3].setToolTipText("PENALTI: VAI PARA CASA 55, JOGA O DADO 2 VEZES, E SE TIRAR 5 MARCA GOL.");
        btosTab[12][3].setText("52");
        btosTab[13][2].setBackground(Color.white);
        btosTab[13][2].setText("51");
        btosTab[14][1].setBackground(Color.white);
        btosTab[14][1].setText("50");
        btosTab[15][0].setBackground(Color.white);
        btosTab[15][0].setText("49");

        //lado esquerdo meio do meio em cima
        btosTab[1][26].setBackground(Color.white);
        btosTab[1][26].setText("49");
        btosTab[2][25].setBackground(Color.white);
        btosTab[2][25].setText("50");
        btosTab[3][24].setBackground(Color.white);
        btosTab[3][24].setText("51");
        btosTab[4][23].setBackground(Color.yellow);
        btosTab[4][23].setToolTipText("PENALTI: VAI PARA CASA 55, JOGA O DADO 2 VEZES, E SE TIRAR 5 MARCA GOL.");
        btosTab[4][23].setText("52");
        btosTab[5][22].setBackground(Color.yellow);
        btosTab[5][22].setToolTipText("FALTA: JOGA DOIS DADOS E SE A SOMA FOR SUPERIOR A 6, MARCA GOL.");
        btosTab[5][22].setText("53");
        btosTab[6][21].setBackground(Color.white);
        btosTab[6][21].setText("54");

        //lado direito meio do meio cima
        btosTab[6][5].setBackground(Color.white);
        btosTab[6][5].setText("30");
        btosTab[5][4].setBackground(Color.yellow);
        btosTab[5][4].setToolTipText("FALTA: JOGA DOIS DADOS E SE A SOMA FOR SUPERIOR A 6, MARCA GOL.");
        btosTab[5][4].setText("29");
        btosTab[4][3].setBackground(Color.yellow);
        btosTab[4][3].setToolTipText("PENALTI: VAI PARA CASA 55, JOGA O DADO 2 VEZES, E SE TIRAR 5 MARCA GOL.");
        btosTab[4][3].setText("28");
        btosTab[3][2].setBackground(Color.white);
        btosTab[3][2].setText("27");
        btosTab[2][1].setBackground(Color.white);
        btosTab[2][1].setText("26");
        btosTab[1][0].setBackground(Color.white);
        btosTab[1][0].setText("25");

        //lado esquerdo meio do meio em baixo
        btosTab[15][26].setBackground(Color.white);
        btosTab[15][26].setText("25");
        btosTab[14][25].setBackground(Color.white);
        btosTab[14][25].setText("26");
        btosTab[13][24].setBackground(Color.white);
        btosTab[13][24].setText("27");
        btosTab[12][23].setBackground(Color.yellow);
        btosTab[12][23].setToolTipText("PENALTI: VAI PARA CASA 55, JOGA O DADO 2 VEZES, E SE TIRAR 5 MARCA GOL.");
        btosTab[12][23].setText("28");
        btosTab[11][22].setBackground(Color.yellow);
        btosTab[11][22].setToolTipText("FALTA: JOGA DOIS DADOS E SE A SOMA FOR SUPERIOR A 6, MARCA GOL.");
        btosTab[11][22].setText("29");
        btosTab[10][21].setBackground(Color.white);
        btosTab[10][21].setText("30");

        //lado esquerdo meio do meio em cima
        btosTab[2][12].setBackground(Color.white);
        btosTab[2][12].setText("11");
        btosTab[3][11].setBackground(Color.white);
        btosTab[3][11].setText("10");
        btosTab[4][10].setBackground(Color.white);
        btosTab[4][10].setText("9");
        btosTab[5][9].setBackground(Color.white);
        btosTab[5][9].setText("8");
        btosTab[6][8].setBackground(Color.white);
        btosTab[6][8].setText("7");

        //lado direito meio do meio em baixo
        btosTab[14][14].setBackground(Color.white);
        btosTab[14][14].setText("11");
        btosTab[13][15].setBackground(Color.white);
        btosTab[13][15].setText("10");
        btosTab[12][16].setBackground(Color.white);
        btosTab[12][16].setText("9");
        btosTab[11][17].setBackground(Color.white);
        btosTab[11][17].setText("8");
        btosTab[10][18].setBackground(Color.white);
        btosTab[10][18].setText("7");

        //lado direito meio do meio em cima
        btosTab[6][18].setBackground(Color.white);
        btosTab[6][18].setText("31");
        btosTab[5][17].setBackground(Color.white);
        btosTab[5][17].setText("32");
        btosTab[4][16].setBackground(Color.white);
        btosTab[4][16].setText("33");
        btosTab[3][15].setBackground(Color.white);
        btosTab[3][15].setText("34");
        btosTab[2][14].setBackground(Color.white);
        btosTab[2][14].setText("35");

        //lado esquerdo meio do meio em baixo
        btosTab[10][8].setBackground(Color.white);
        btosTab[10][8].setText("31");
        btosTab[11][9].setBackground(Color.white);
        btosTab[11][9].setText("32");
        btosTab[12][10].setBackground(Color.white);
        btosTab[12][10].setText("33");
        btosTab[13][11].setBackground(Color.white);
        btosTab[13][11].setText("34");
        btosTab[14][12].setBackground(Color.white);
        btosTab[14][12].setText("35");

        //dado
        btosTab[4][12].setBackground(Color.DARK_GRAY);
        btosTab[4][13].setBackground(Color.DARK_GRAY);
        btosTab[4][14].setBackground(Color.DARK_GRAY);
        btosTab[5][12].setBackground(Color.DARK_GRAY);
        btosTab[5][13].setBackground(Color.red);
        btosTab[5][14].setBackground(Color.DARK_GRAY);
        btosTab[6][12].setBackground(Color.DARK_GRAY);
        btosTab[6][13].setBackground(Color.DARK_GRAY);
        btosTab[6][14].setBackground(Color.DARK_GRAY);

    }

    /*
     Evento de fechar o formulário.
     */
    public void windowClosing(WindowEvent e) {
        this.dispose();
    }

    /*
     Evento do botão de jogar o dado.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btosTab[5][13]) {

            int meioTabuleiro = 0;
            int inicioTabuleiro = 0;
            pintarTabuleiro();
            qtJogadas++;

            pintarColoridos();
            JogarDado dado = new JogarDado();
            int aux;
            aux = dado.girarDado();

            /*
             Calcula a posição do jogador, vê a posição da bola, vê se o jogo passou do 60, que é o
             limite do tabuleiro
             */
            if (vezJogador == 1) {
                posicaoJogador1 = aux + posicaoJogador2;
                posicaoBola = posicaoJogador1;
                casasPercorridasJogador1++;
                if (61 <= posicaoBola) {
                    ultrapassou60 = posicaoBola - 60;
                    posicaoBola = 60 - ultrapassou60;
                    posicaoJogador1 = posicaoBola;
                }
            } else {
                posicaoJogador2 = aux + posicaoJogador1;
                posicaoBola = posicaoJogador2;
                casasPercorridasJogador2++;
                if (61 <= posicaoBola) {
                    ultrapassou60 = posicaoBola - 60;
                    posicaoBola = 60 - ultrapassou60;
                    posicaoJogador2 = posicaoBola;
                }

            }

            /*
             Garante que o jogador 1 só joga do lado direito do tabuleiro e
             que o jogador 2 só joga do lado esquerdo
             */
            if (vezJogador == 1) {
                meioTabuleiro = 27;
                inicioTabuleiro = 14;

            } else {
                meioTabuleiro = 13;
                inicioTabuleiro = 0;

            }

            /*
             Repinta o tabuleiro, seguindo as alterações que já foram feitas.
             */
            for (int i = 0; i < 17; i++) {
                for (int j = 0; j < 27; j++) {
                    if ("".equals(btosTab[i][j].getText())
                            || "PL".equals(btosTab[i][j].getText())
                            || "AC".equals(btosTab[i][j].getText())
                            || "AR".equals(btosTab[i][j].getText())
                            || "J2".equals(btosTab[i][j].getText())
                            || "J1".equals(btosTab[i][j].getText())) {
                    } else {
                        if (Integer.parseInt(btosTab[i][j].getText()) != posicaoBola
                                && btosTab[i][j].getBackground() != Color.yellow
                                && btosTab[i][j].getBackground() != Color.red
                                && btosTab[i][j].getBackground() != Color.cyan) {
                            pintarTabuleiro();
                        }
                    }
                }
            }
            /*
             Pinta o botão da nova posição da bola.
             */

            for (int i = 0; i < 17; i++) {
                for (int j = inicioTabuleiro; j < meioTabuleiro; j++) {
                    if ("".equals(btosTab[i][j].getText())
                            || "PL".equals(btosTab[i][j].getText())
                            || "AC".equals(btosTab[i][j].getText())
                            || "AR".equals(btosTab[i][j].getText())
                            || "J2".equals(btosTab[i][j].getText())
                            || "J1".equals(btosTab[i][j].getText())) {

                    } else {
                        if (Integer.parseInt(btosTab[i][j].getText()) == posicaoBola) {
                            pintarTabuleiro(btosTab[i][j]);
                        }
                    }
                }

            }

            /*
             Essa seguencia de IFS, vêm quando cada jogador cai em alguma casa
             que tem punições ou auxilios.
             O 12 é quando é mão e assim por diante
             */
            if (posicaoBola == 12) {
                ImageIcon i = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\mao.png");
                JLabel label = new JLabel(i);
                JOptionPane.showMessageDialog(null, label, "MÃO", JOptionPane.PLAIN_MESSAGE);

                int valor1;
                int valor2;

                JOptionPane.showMessageDialog(null, "Neste momento, os dados serão jogados automaticamente 2 vezes!");

                JogarDado JogarDado = new JogarDado();
                valor1 = JogarDado.girarDado();
                valor2 = JogarDado.girarDado();

                if (vezJogador == 1) {

                    if (valor1 == valor2) {

                        pintarTabuleiro();
                        ImageIcon i2 = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\bola.png");
                        JLabel label2 = new JLabel(i2);
                        JOptionPane.showMessageDialog(null, label2, "GOOOL, TÁ LA DENTRO " + jogador1, JOptionPane.PLAIN_MESSAGE);
                        golsJogador1++;
                        posicaoJogador1 = 0;
                        posicaoJogador2 = 0;
                        posicaoBola = 0;
                        pintarColoridos();
                        pintarTabuleiro(btosTab[8][13]);

                    }
                    vezJogador = 2;
                } else {
                    if (valor1 == valor2) {

                        pintarTabuleiro();
                        ImageIcon i2 = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\bola.png");
                        JLabel label2 = new JLabel(i2);
                        JOptionPane.showMessageDialog(null, label2, "GOOOL, TÁ LA DENTRO " + jogador2, JOptionPane.PLAIN_MESSAGE);
                        golsJogador2++;
                        posicaoJogador1 = 0;
                        posicaoJogador2 = 0;
                        posicaoBola = 0;
                        pintarColoridos();
                        pintarTabuleiro(btosTab[8][13]);

                    }
                    vezJogador = 1;
                }
            } else if (posicaoBola == 18) {
                ImageIcon i = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\rasteira.png");
                JLabel label = new JLabel(i);
                JOptionPane.showMessageDialog(null, label, "RASTEIRA", JOptionPane.PLAIN_MESSAGE);
                int valor1;
                int valor2;
                int valor3;

                JOptionPane.showMessageDialog(null, "Neste momento, os dados serão jogados automaticamente 3 vezes!");

                JogarDado JogarDado = new JogarDado();
                valor1 = JogarDado.girarDado();
                valor2 = JogarDado.girarDado();
                valor3 = JogarDado.girarDado();

                if (vezJogador == 1) {

                    if (valor1 % 2 == 0 || valor2 % 2 == 0 || valor3 % 2 == 0) {

                        pintarTabuleiro();
                        ImageIcon i2 = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\bola.png");
                        JLabel label2 = new JLabel(i2);
                        JOptionPane.showMessageDialog(null, label2, "GOOOL, TÁ LA DENTRO " + jogador1, JOptionPane.PLAIN_MESSAGE);
                        golsJogador1++;
                        posicaoJogador1 = 0;
                        posicaoJogador2 = 0;
                        posicaoBola = 0;
                        pintarColoridos();
                        pintarTabuleiro(btosTab[8][13]);

                    }
                    vezJogador = 2;
                } else {
                    if (valor1 % 2 == 0 || valor2 % 2 == 0 || valor3 % 2 == 0) {

                        pintarTabuleiro();
                        ImageIcon i2 = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\bola.png");
                        JLabel label2 = new JLabel(i2);
                        JOptionPane.showMessageDialog(null, label2, "GOOOL, TÁ LA DENTRO " + jogador2, JOptionPane.PLAIN_MESSAGE);
                        golsJogador1++;
                        posicaoJogador1 = 0;
                        posicaoJogador2 = 0;
                        posicaoBola = 0;
                        pintarColoridos();
                        pintarTabuleiro(btosTab[8][13]);

                    }
                    vezJogador = 1;
                }
            } else if (posicaoBola == 21) {
                ImageIcon i = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\foraJogo.png");
                JLabel label = new JLabel(i);
                JOptionPane.showMessageDialog(null, label, "FORA DE JOGO", JOptionPane.PLAIN_MESSAGE);

                if (vezJogador == 1) {
                    btosTab[0][3].setBackground(Color.yellow);

                    btosTab[2][12].setBackground(Color.black);
                    posicaoJogador1 = 11;
                    vezJogador = 2;
                } else {
                    btosTab[16][23].setBackground(Color.yellow);
                    btosTab[14][14].setBackground(Color.black);
                    posicaoJogador2 = 11;
                    vezJogador = 1;
                }
                posicaoBola = 11;

            } else if (posicaoBola == 24) {
                ImageIcon i = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\escanteio.png");
                JLabel label = new JLabel(i);
                JOptionPane.showMessageDialog(null, label, "ESCANTEIO", JOptionPane.PLAIN_MESSAGE);
                int valor1;

                JOptionPane.showMessageDialog(null, "Neste momento, o dado será jogado automaticamente");

                JogarDado JogarDado = new JogarDado();
                valor1 = JogarDado.girarDado();

                if (vezJogador == 1) {

                    if (valor1 == 6) {

                        pintarTabuleiro();
                        ImageIcon i2 = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\bola.png");
                        JLabel label2 = new JLabel(i2);
                        JOptionPane.showMessageDialog(null, label2, "GOOOL, TÁ LA DENTRO " + jogador1, JOptionPane.PLAIN_MESSAGE);
                        golsJogador1++;
                        posicaoJogador1 = 0;
                        posicaoJogador2 = 0;
                        posicaoBola = 0;
                        pintarColoridos();
                        pintarTabuleiro(btosTab[8][13]);

                    }
                    vezJogador = 2;
                } else {
                    if (valor1 == 6) {

                        pintarTabuleiro();
                        ImageIcon i2 = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\bola.png");
                        JLabel label2 = new JLabel(i2);
                        JOptionPane.showMessageDialog(null, label2, "GOOOL, TÁ LA DENTRO " + jogador2, JOptionPane.PLAIN_MESSAGE);
                        golsJogador2++;
                        posicaoJogador1 = 0;
                        posicaoJogador2 = 0;
                        posicaoBola = 0;
                        pintarColoridos();
                        pintarTabuleiro(btosTab[8][13]);

                    }
                    vezJogador = 1;
                }
            } else if (posicaoBola == 28) {
                ImageIcon i = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\penalti.png");
                JLabel label = new JLabel(i);
                JOptionPane.showMessageDialog(null, label, "PENALTI", JOptionPane.PLAIN_MESSAGE);

                pintarTabuleiro();

                int valor1;
                int valor2;

                JOptionPane.showMessageDialog(null, "Neste momento, os dados serão jogados automaticamente 2 vezes!");

                JogarDado JogarDado = new JogarDado();
                valor1 = JogarDado.girarDado();
                valor2 = JogarDado.girarDado();

                if (vezJogador == 1) {

                    if (valor1 == 5 || valor2 == 5) {

                        pintarTabuleiro();
                        ImageIcon i2 = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\bola.png");
                        JLabel label2 = new JLabel(i2);
                        JOptionPane.showMessageDialog(null, label2, "GOOOL, TÁ LA DENTRO " + jogador1, JOptionPane.PLAIN_MESSAGE);
                        golsJogador1++;
                        posicaoJogador1 = 0;
                        posicaoJogador2 = 0;
                        posicaoBola = 0;
                        pintarColoridos();
                        pintarTabuleiro(btosTab[8][13]);

                    }
                    pintarTabuleiro(btosTab[8][5]);
                    vezJogador = 2;
                } else {
                    if (valor1 == 5 || valor2 == 5) {

                        pintarTabuleiro();
                        ImageIcon i2 = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\bola.png");
                        JLabel label2 = new JLabel(i2);
                        JOptionPane.showMessageDialog(null, label2, "GOOOL, TÁ LA DENTRO " + jogador2, JOptionPane.PLAIN_MESSAGE);
                        golsJogador2++;
                        posicaoJogador1 = 0;
                        posicaoJogador2 = 0;
                        posicaoBola = 0;
                        pintarColoridos();
                        pintarTabuleiro(btosTab[8][13]);

                    }
                    pintarTabuleiro(btosTab[8][21]);
                    vezJogador = 1;
                }
            } else if (posicaoBola == 29) {
                ImageIcon i = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\falta.png");
                JLabel label = new JLabel(i);
                JOptionPane.showMessageDialog(null, label, "FALTA", JOptionPane.PLAIN_MESSAGE);
                int valor1;
                int valor2;

                JOptionPane.showMessageDialog(null, "Neste momento, os dados serão jogados automaticamente 2 vezes!");

                JogarDado JogarDado = new JogarDado();
                valor1 = JogarDado.girarDado();
                valor2 = JogarDado.girarDado();

                if (vezJogador == 1) {

                    if (valor1 + valor2 > 6) {

                        pintarTabuleiro();
                        ImageIcon i2 = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\bola.png");
                        JLabel label2 = new JLabel(i2);
                        JOptionPane.showMessageDialog(null, label2, "GOOOL, TÁ LA DENTRO " + jogador1, JOptionPane.PLAIN_MESSAGE);
                        golsJogador1++;
                        posicaoJogador1 = 0;
                        posicaoJogador2 = 0;
                        posicaoBola = 0;
                        pintarColoridos();
                        pintarTabuleiro(btosTab[8][13]);

                    }
                    vezJogador = 2;
                } else {
                    if (valor1 + valor2 > 6) {

                        pintarTabuleiro();
                        ImageIcon i2 = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\bola.png");
                        JLabel label2 = new JLabel(i2);
                        JOptionPane.showMessageDialog(null, label2, "GOOOL, TÁ LA DENTRO " + jogador2, JOptionPane.PLAIN_MESSAGE);
                        golsJogador2++;
                        posicaoJogador1 = 0;
                        posicaoJogador2 = 0;
                        posicaoBola = 0;
                        pintarColoridos();
                        pintarTabuleiro(btosTab[8][13]);

                    }
                    vezJogador = 1;
                }
            } else if (posicaoBola == 36) {
                ImageIcon i = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\mao.png");
                JLabel label = new JLabel(i);
                JOptionPane.showMessageDialog(null, label, "MÃO", JOptionPane.PLAIN_MESSAGE);
                int valor1;
                int valor2;

                JOptionPane.showMessageDialog(null, "Neste momento, os dados serão jogados automaticamente 2 vezes!");

                JogarDado JogarDado = new JogarDado();
                valor1 = JogarDado.girarDado();
                valor2 = JogarDado.girarDado();

                if (vezJogador == 1) {

                    if (valor1 == valor2) {

                        pintarTabuleiro();
                        ImageIcon i2 = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\bola.png");
                        JLabel label2 = new JLabel(i2);
                        JOptionPane.showMessageDialog(null, label2, "GOOOL, TÁ LA DENTRO " + jogador1, JOptionPane.PLAIN_MESSAGE);
                        golsJogador1++;
                        posicaoJogador1 = 0;
                        posicaoJogador2 = 0;
                        posicaoBola = 0;
                        pintarColoridos();
                        pintarTabuleiro(btosTab[8][13]);

                    }
                    vezJogador = 2;
                } else {
                    if (valor1 == valor2) {

                        pintarTabuleiro();
                        ImageIcon i2 = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\bola.png");
                        JLabel label2 = new JLabel(i2);
                        JOptionPane.showMessageDialog(null, label2, "GOOOL, TÁ LA DENTRO " + jogador2, JOptionPane.PLAIN_MESSAGE);
                        golsJogador2++;
                        posicaoJogador1 = 0;
                        posicaoJogador2 = 0;
                        posicaoBola = 0;
                        pintarColoridos();
                        pintarTabuleiro(btosTab[8][13]);

                    }
                    vezJogador = 1;
                }
            } else if (posicaoBola == 39) {
                ImageIcon i = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\bolaFora.png");
                JLabel label = new JLabel(i);
                JOptionPane.showMessageDialog(null, label, "BOLA FORA", JOptionPane.PLAIN_MESSAGE);
                if (vezJogador == 1) {
                    vezJogador = 1;
                } else {
                    vezJogador = 2;
                }

            } else if (posicaoBola == 42) {
                ImageIcon i = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\jogoViolento.png");
                JLabel label = new JLabel(i);
                JOptionPane.showMessageDialog(null, label, "JOGO VIOLENTO", JOptionPane.PLAIN_MESSAGE);
                int valor1;
                int valor2;
                int valor3;

                JOptionPane.showMessageDialog(null, "Neste momento, os dados serão jogados automaticamente 3 vezes!");

                JogarDado JogarDado = new JogarDado();
                valor1 = JogarDado.girarDado();
                valor2 = JogarDado.girarDado();
                valor3 = JogarDado.girarDado();

                if (vezJogador == 1) {

                    if (valor1 % 2 == 0 || valor2 % 2 == 0 || valor3 % 2 == 0) {

                        pintarTabuleiro();
                        ImageIcon i2 = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\bola.png");
                        JLabel label2 = new JLabel(i2);
                        JOptionPane.showMessageDialog(null, label2, "GOOOL, TÁ LA DENTRO " + jogador1, JOptionPane.PLAIN_MESSAGE);
                        golsJogador1++;
                        posicaoJogador1 = 0;
                        posicaoJogador2 = 0;
                        posicaoBola = 0;
                        pintarColoridos();
                        pintarTabuleiro(btosTab[8][13]);

                    }
                    vezJogador = 2;
                } else {
                    if (valor1 % 2 == 0 || valor2 % 2 == 0 || valor3 % 2 == 0) {

                        pintarTabuleiro();
                        ImageIcon i2 = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\bola.png");
                        JLabel label2 = new JLabel(i2);
                        JOptionPane.showMessageDialog(null, label2, "GOOOL, TÁ LA DENTRO " + jogador2, JOptionPane.PLAIN_MESSAGE);
                        golsJogador1++;
                        posicaoJogador1 = 0;
                        posicaoJogador2 = 0;
                        posicaoBola = 0;
                        pintarColoridos();
                        pintarTabuleiro(btosTab[8][13]);

                    }
                    vezJogador = 1;
                }
            } else if (posicaoBola == 45) {
                ImageIcon i = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\foraJogo.png");
                JLabel label = new JLabel(i);
                JOptionPane.showMessageDialog(null, label, "FORA DE JOGO", JOptionPane.PLAIN_MESSAGE);

                if (vezJogador == 1) {
                    pintarTabuleiro(btosTab[14][12]);
                    posicaoJogador1 = 35;
                    vezJogador = 2;
                } else {
                    pintarTabuleiro(btosTab[2][14]);
                    posicaoJogador2 = 35;
                    vezJogador = 1;
                }
                pintarColoridos();

                posicaoBola = 35;
            } else if (posicaoBola == 48) {
                ImageIcon i = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\escanteio.png");
                JLabel label = new JLabel(i);
                JOptionPane.showMessageDialog(null, label, "ESCANTEIO", JOptionPane.PLAIN_MESSAGE);
                int valor1;

                JOptionPane.showMessageDialog(null, "Neste momento, o dado será jogado automaticamente");

                JogarDado JogarDado = new JogarDado();
                valor1 = JogarDado.girarDado();

                if (vezJogador == 1) {

                    if (valor1 == 6) {

                        pintarTabuleiro();
                        ImageIcon i2 = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\bola.png");
                        JLabel label2 = new JLabel(i2);
                        JOptionPane.showMessageDialog(null, label2, "GOOOL, TÁ LA DENTRO " + jogador1, JOptionPane.PLAIN_MESSAGE);
                        golsJogador1++;
                        posicaoJogador1 = 0;
                        posicaoJogador2 = 0;
                        posicaoBola = 0;
                        pintarColoridos();
                        pintarTabuleiro(btosTab[8][13]);

                    }
                    vezJogador = 2;
                } else {
                    if (valor1 == 6) {

                        pintarTabuleiro();
                        ImageIcon i2 = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\bola.png");
                        JLabel label2 = new JLabel(i2);
                        JOptionPane.showMessageDialog(null, label2, "GOOOL, TÁ LA DENTRO " + jogador2, JOptionPane.PLAIN_MESSAGE);
                        golsJogador2++;
                        posicaoJogador1 = 0;
                        posicaoJogador2 = 0;
                        posicaoBola = 0;
                        pintarColoridos();
                        pintarTabuleiro(btosTab[8][13]);

                    }
                    vezJogador = 1;
                }
            } else if (posicaoBola == 52) {
                ImageIcon i = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\penalti.png");
                JLabel label = new JLabel(i);
                JOptionPane.showMessageDialog(null, label, "PENALTI", JOptionPane.PLAIN_MESSAGE);

                pintarTabuleiro();

                int valor1;
                int valor2;

                JOptionPane.showMessageDialog(null, "Neste momento, os dados serão jogados automaticamente 2 vezes!");

                JogarDado JogarDado = new JogarDado();
                valor1 = JogarDado.girarDado();
                valor2 = JogarDado.girarDado();

                if (vezJogador == 1) {

                    if (valor1 == 5 || valor2 == 5) {

                        pintarTabuleiro();
                        ImageIcon i2 = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\bola.png");
                        JLabel label2 = new JLabel(i2);
                        JOptionPane.showMessageDialog(null, label2, "GOOOL, TÁ LA DENTRO " + jogador1, JOptionPane.PLAIN_MESSAGE);
                        golsJogador1++;
                        posicaoJogador1 = 0;
                        posicaoJogador2 = 0;
                        posicaoBola = 0;
                        pintarColoridos();
                        pintarTabuleiro(btosTab[8][13]);

                    }
                    pintarTabuleiro(btosTab[8][5]);
                    vezJogador = 2;
                } else {
                    if (valor1 == 5 || valor2 == 5) {

                        pintarTabuleiro();
                        ImageIcon i2 = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\bola.png");
                        JLabel label2 = new JLabel(i2);
                        JOptionPane.showMessageDialog(null, label2, "GOOOL, TÁ LA DENTRO " + jogador2, JOptionPane.PLAIN_MESSAGE);
                        golsJogador2++;
                        posicaoJogador1 = 0;
                        posicaoJogador2 = 0;
                        posicaoBola = 0;
                        pintarColoridos();
                        pintarTabuleiro(btosTab[8][13]);

                    }
                    pintarTabuleiro(btosTab[8][21]);
                    vezJogador = 1;
                }
            } else if (posicaoBola == 53) {
                ImageIcon i = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\falta.png");
                JLabel label = new JLabel(i);
                JOptionPane.showMessageDialog(null, label, "FALTA", JOptionPane.PLAIN_MESSAGE);
                int valor1;
                int valor2;

                JOptionPane.showMessageDialog(null, "Neste momento, os dados serão jogados automaticamente 2 vezes!");

                JogarDado JogarDado = new JogarDado();
                valor1 = JogarDado.girarDado();
                valor2 = JogarDado.girarDado();

                if (vezJogador == 1) {

                    if (valor1 + valor2 > 6) {

                        pintarTabuleiro();
                        ImageIcon i2 = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\bola.png");
                        JLabel label2 = new JLabel(i2);
                        JOptionPane.showMessageDialog(null, label2, "GOOOL, TÁ LA DENTRO " + jogador1, JOptionPane.PLAIN_MESSAGE);
                        golsJogador1++;
                        posicaoJogador1 = 0;
                        posicaoJogador2 = 0;
                        posicaoBola = 0;
                        pintarColoridos();
                        pintarTabuleiro(btosTab[8][13]);

                    }
                    vezJogador = 2;
                } else {
                    if (valor1 + valor2 > 6) {

                        pintarTabuleiro();
                        ImageIcon i2 = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\bola.png");
                        JLabel label2 = new JLabel(i2);
                        JOptionPane.showMessageDialog(null, label2, "GOOOL, TÁ LA DENTRO " + jogador2, JOptionPane.PLAIN_MESSAGE);
                        golsJogador2++;
                        posicaoJogador1 = 0;
                        posicaoJogador2 = 0;
                        posicaoBola = 0;
                        pintarColoridos();
                        pintarTabuleiro(btosTab[8][13]);

                    }
                    vezJogador = 1;
                }
            } else if (posicaoBola == 59) {
                ImageIcon i = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\defesa.png");
                JLabel label = new JLabel(i);
                JOptionPane.showMessageDialog(null, label, "DEFESA", JOptionPane.PLAIN_MESSAGE);

                pintarTabuleiro();

                if (vezJogador == 2) {
                    posicaoJogador2 = 49;
                    vezJogador = 1;
                    pintarTabuleiro(btosTab[1][26]);

                } else {
                    posicaoJogador1 = 49;
                    vezJogador = 2;
                    pintarTabuleiro(btosTab[15][0]);

                }

                pintarColoridos();
                posicaoBola = 49;

            } else if (posicaoBola == 60) {

                pintarTabuleiro();

                if (vezJogador == 1) {
                    ImageIcon i = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\bola.png");
                    JLabel label = new JLabel(i);
                    JOptionPane.showMessageDialog(null, label, "GOOOL, TÁ LA DENTRO " + jogador1, JOptionPane.PLAIN_MESSAGE);
                    golsJogador1++;
                    vezJogador = 2;

                } else {
                    ImageIcon i = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\bola.png");
                    JLabel label = new JLabel(i);
                    JOptionPane.showMessageDialog(null, label, "GOOOL, TÁ LA DENTRO " + jogador2, JOptionPane.PLAIN_MESSAGE);
                    golsJogador2++;
                    vezJogador = 1;
                }

                posicaoJogador1 = 0;
                posicaoJogador2 = 0;
                posicaoBola = 0;
                ultrapassou60 = 0;
                pintarColoridos();
                pintarTabuleiro(btosTab[8][13]);

            } else {

                if (vezJogador == 1) {
                    vezJogador = 2;
                } else {
                    vezJogador = 1;
                }

            }
        }
    }

    /*
     Atualiza o placar do jogo.
     */
    public void MostraGolsJogadores() {

        btosTab[3][20].setText("" + golsJogador2);
        btosTab[3][20].setForeground(Color.white);
        btosTab[3][20].setBackground(Color.blue);
        btosTab[3][6].setBackground(Color.blue);
        btosTab[3][6].setText("" + golsJogador1);
        btosTab[3][6].setForeground(Color.white);

    }

    /*
     Atualiza a vez do jogador.
     */
    public void MostraPosicaoJogador(int vezJogador) {
        btosTab[4][12].setText("J" + vezJogador);
        btosTab[4][12].setForeground(Color.white);

        btosTab[4][13].setText("J" + vezJogador);
        btosTab[4][13].setForeground(Color.white);

        btosTab[4][14].setText("J" + vezJogador);
        btosTab[4][14].setForeground(Color.white);

        btosTab[5][12].setText("J" + vezJogador);
        btosTab[5][12].setForeground(Color.white);

        btosTab[5][14].setText("J" + vezJogador);
        btosTab[5][14].setForeground(Color.white);

        btosTab[6][12].setText("J" + vezJogador);
        btosTab[6][12].setForeground(Color.white);

        btosTab[6][13].setText("J" + vezJogador);
        btosTab[6][13].setForeground(Color.white);

        btosTab[6][14].setText("J" + vezJogador);
        btosTab[6][14].setForeground(Color.white);
    }

    /*
     pinta o a posição atual da bola.
     */
    public void pintarTabuleiro(JButton botao) {

        botao.setBackground(Color.black);

    }
    /*
     Pinta o tabuleiro, casas de cores brancas.
     */

    public void pintarTabuleiro() {
        //parte de cima esquerda

        btosTab[8][13].setBackground(Color.white);
        btosTab[0][1].setBackground(Color.white);
        btosTab[0][1].setText("23");
        btosTab[0][2].setBackground(Color.white);
        btosTab[0][2].setText("22");
        btosTab[0][4].setBackground(Color.white);
        btosTab[0][4].setText("20");
        btosTab[0][5].setBackground(Color.white);
        btosTab[0][5].setText("19");
        btosTab[0][7].setBackground(Color.white);
        btosTab[0][7].setText("17");
        btosTab[0][8].setBackground(Color.white);
        btosTab[0][8].setText("16");
        btosTab[0][9].setBackground(Color.white);
        btosTab[0][9].setText("15");
        btosTab[0][10].setBackground(Color.white);
        btosTab[0][10].setText("14");
        btosTab[0][11].setBackground(Color.white);
        btosTab[0][11].setText("13");

        //parte de cima direita
        btosTab[0][15].setBackground(Color.white);
        btosTab[0][15].setText("37");
        btosTab[0][16].setBackground(Color.white);
        btosTab[0][17].setText("39");
        btosTab[0][18].setBackground(Color.white);
        btosTab[0][18].setText("40");
        btosTab[0][19].setBackground(Color.white);
        btosTab[0][19].setText("41");
        btosTab[0][21].setBackground(Color.white);
        btosTab[0][21].setText("43");
        btosTab[0][22].setBackground(Color.white);
        btosTab[0][22].setText("44");
        btosTab[0][24].setBackground(Color.white);
        btosTab[0][24].setText("46");
        btosTab[0][25].setBackground(Color.white);
        btosTab[0][25].setText("47");

        //parte de baixo esquerda
        btosTab[16][1].setBackground(Color.white);
        btosTab[16][1].setText("47");
        btosTab[16][2].setBackground(Color.white);
        btosTab[16][2].setText("46");
        btosTab[16][4].setBackground(Color.white);
        btosTab[16][4].setText("44");
        btosTab[16][5].setBackground(Color.white);
        btosTab[16][5].setText("43");
        btosTab[16][7].setBackground(Color.white);
        btosTab[16][7].setText("41");
        btosTab[16][8].setBackground(Color.white);
        btosTab[16][8].setText("40");
        btosTab[16][10].setBackground(Color.white);
        btosTab[16][10].setText("38");
        btosTab[16][11].setBackground(Color.white);
        btosTab[16][11].setText("37");

        //parte de baixo direita
        btosTab[16][15].setBackground(Color.white);
        btosTab[16][15].setText("13");
        btosTab[16][16].setBackground(Color.white);
        btosTab[16][16].setText("14");
        btosTab[16][17].setBackground(Color.white);
        btosTab[16][17].setText("15");
        btosTab[16][18].setBackground(Color.white);
        btosTab[16][18].setText("16");
        btosTab[16][19].setBackground(Color.white);
        btosTab[16][19].setText("17");
        btosTab[16][21].setBackground(Color.white);
        btosTab[16][21].setText("19");
        btosTab[16][22].setBackground(Color.white);
        btosTab[16][22].setText("20");
        btosTab[16][24].setBackground(Color.white);
        btosTab[16][24].setText("22");
        btosTab[16][25].setBackground(Color.white);
        btosTab[16][25].setText("23");

        //linha do meio
        //esquerda
        btosTab[8][12].setBackground(Color.white);
        btosTab[8][12].setText("1");
        btosTab[8][11].setBackground(Color.white);
        btosTab[8][11].setText("2");
        btosTab[8][10].setBackground(Color.white);
        btosTab[8][10].setText("3");
        btosTab[8][9].setBackground(Color.white);
        btosTab[8][9].setText("4");
        btosTab[8][8].setBackground(Color.white);
        btosTab[8][8].setText("5");
        btosTab[8][7].setBackground(Color.white);
        btosTab[8][7].setText("6");
        btosTab[8][2].setBackground(Color.white);
        btosTab[8][2].setText("58");
        btosTab[8][3].setBackground(Color.white);
        btosTab[8][3].setText("57");
        btosTab[8][4].setBackground(Color.white);
        btosTab[8][4].setText("56");
        btosTab[8][5].setBackground(Color.white);
        btosTab[8][5].setText("55");

        //direita
        btosTab[8][14].setBackground(Color.white);
        btosTab[8][14].setText("1");
        btosTab[8][15].setBackground(Color.white);
        btosTab[8][15].setText("2");
        btosTab[8][16].setBackground(Color.white);
        btosTab[8][16].setText("3");
        btosTab[8][17].setBackground(Color.white);
        btosTab[8][17].setText("4");
        btosTab[8][18].setBackground(Color.white);
        btosTab[8][18].setText("5");
        btosTab[8][19].setBackground(Color.white);
        btosTab[8][19].setText("6");
        btosTab[8][24].setBackground(Color.white);
        btosTab[8][24].setText("58");
        btosTab[8][23].setBackground(Color.white);
        btosTab[8][23].setText("57");
        btosTab[8][22].setBackground(Color.white);
        btosTab[8][22].setText("56");
        btosTab[8][21].setBackground(Color.white);
        btosTab[8][21].setText("55");

        //lado direito meio do meio baixo
        btosTab[10][5].setBackground(Color.white);
        btosTab[10][5].setText("54");
        btosTab[13][2].setBackground(Color.white);
        btosTab[13][2].setText("51");
        btosTab[14][1].setBackground(Color.white);
        btosTab[14][1].setText("50");
        btosTab[15][0].setBackground(Color.white);
        btosTab[15][0].setText("49");

        //lado esquerdo meio do meio em cima
        btosTab[1][26].setBackground(Color.white);
        btosTab[1][26].setText("49");
        btosTab[2][25].setBackground(Color.white);
        btosTab[2][25].setText("50");
        btosTab[3][24].setBackground(Color.white);
        btosTab[3][24].setText("51");
        btosTab[6][21].setBackground(Color.white);
        btosTab[6][21].setText("54");

        //lado direito meio do meio cima
        btosTab[6][5].setBackground(Color.white);
        btosTab[6][5].setText("30");
        btosTab[3][2].setBackground(Color.white);
        btosTab[3][2].setText("27");
        btosTab[2][1].setBackground(Color.white);
        btosTab[2][1].setText("26");
        btosTab[1][0].setBackground(Color.white);
        btosTab[1][0].setText("25");

        //lado esquerdo meio do meio em baixo
        btosTab[15][26].setBackground(Color.white);
        btosTab[15][26].setText("25");
        btosTab[14][25].setBackground(Color.white);
        btosTab[14][25].setText("26");
        btosTab[13][24].setBackground(Color.white);
        btosTab[13][24].setText("27");
        btosTab[10][21].setBackground(Color.white);
        btosTab[10][21].setText("30");

        //lado esquerdo meio do meio em cima
        btosTab[2][12].setBackground(Color.white);
        btosTab[2][12].setText("11");
        btosTab[3][11].setBackground(Color.white);
        btosTab[3][11].setText("10");
        btosTab[4][10].setBackground(Color.white);
        btosTab[4][10].setText("9");
        btosTab[5][9].setBackground(Color.white);
        btosTab[5][9].setText("8");
        btosTab[6][8].setBackground(Color.white);
        btosTab[6][8].setText("7");

        //lado direito meio do meio em baixo
        btosTab[14][14].setBackground(Color.white);
        btosTab[14][14].setText("11");
        btosTab[13][15].setBackground(Color.white);
        btosTab[13][15].setText("10");
        btosTab[12][16].setBackground(Color.white);
        btosTab[12][16].setText("9");
        btosTab[11][17].setBackground(Color.white);
        btosTab[11][17].setText("8");
        btosTab[10][18].setBackground(Color.white);
        btosTab[10][18].setText("7");

        //lado direito meio do meio em cima
        btosTab[6][18].setBackground(Color.white);
        btosTab[6][18].setText("31");
        btosTab[5][17].setBackground(Color.white);
        btosTab[5][17].setText("32");
        btosTab[4][16].setBackground(Color.white);
        btosTab[4][16].setText("33");
        btosTab[3][15].setBackground(Color.white);
        btosTab[3][15].setText("34");
        btosTab[2][14].setBackground(Color.white);
        btosTab[2][14].setText("35");

        //lado esquerdo meio do meio em baixo
        btosTab[10][8].setBackground(Color.white);
        btosTab[10][8].setText("31");
        btosTab[11][9].setBackground(Color.white);
        btosTab[11][9].setText("32");
        btosTab[12][10].setBackground(Color.white);
        btosTab[12][10].setText("33");
        btosTab[13][11].setBackground(Color.white);
        btosTab[13][11].setText("34");
        btosTab[14][12].setBackground(Color.white);
        btosTab[14][12].setText("35");

    }

    /*
     Pinta o tabuleiro, casas de cores coloridas.
     */
    public void pintarColoridos() {
        btosTab[0][0].setBackground(Color.yellow);
        btosTab[0][3].setBackground(Color.yellow);
        btosTab[0][6].setBackground(Color.yellow);
        btosTab[0][12].setBackground(Color.yellow);
        btosTab[0][14].setBackground(Color.yellow);
        btosTab[0][17].setBackground(Color.yellow);
        btosTab[0][20].setBackground(Color.yellow);
        btosTab[0][23].setBackground(Color.yellow);
        btosTab[0][26].setBackground(Color.yellow);
        btosTab[16][0].setBackground(Color.yellow);
        btosTab[16][3].setBackground(Color.yellow);
        btosTab[16][6].setBackground(Color.yellow);
        btosTab[16][9].setBackground(Color.yellow);
        btosTab[16][12].setBackground(Color.yellow);
        btosTab[16][14].setBackground(Color.yellow);
        btosTab[16][20].setBackground(Color.yellow);
        btosTab[16][23].setBackground(Color.yellow);
        btosTab[16][26].setBackground(Color.yellow);
        btosTab[8][0].setBackground(Color.red);
        btosTab[8][26].setBackground(Color.red);
        btosTab[8][1].setBackground(Color.CYAN);
        btosTab[8][25].setBackground(Color.CYAN);
        btosTab[11][4].setBackground(Color.yellow);
        btosTab[12][3].setBackground(Color.yellow);
        btosTab[4][23].setBackground(Color.yellow);
        btosTab[5][22].setBackground(Color.yellow);
        btosTab[5][4].setBackground(Color.yellow);
        btosTab[4][3].setBackground(Color.yellow);
        btosTab[12][23].setBackground(Color.yellow);

    }

    /*
     Vê se a quantidade de gols foi alcançada e assim determina o final do jogo.
     */
    public int verCampeao() {

        if (golsJogador1 == gols) {
            ImageIcon i = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\fimdejogo.png");
            JLabel label = new JLabel(i);
            JOptionPane.showMessageDialog(null, label, jogador1 + " VENCEU!", JOptionPane.PLAIN_MESSAGE);
            this.dispose();

            Jogador Jogador11 = new Jogador();
            Jogador11.setNome(this.jogador1);
            Jogador11.setStatus("VITÓRIA");

            Jogador Jogador22 = new Jogador();
            Jogador22.setNome(this.jogador2);
            Jogador22.setStatus("DERROTA");

            CalculoRanking calculo = new CalculoRanking(Jogador11, Jogador22, qtJogadas, 2, casasPercorridasJogador1, casasPercorridasJogador2);

            gols = 30;
            return 0;

        } else if (golsJogador2 == gols) {
            ImageIcon i = new ImageIcon("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\JogoDeFutebol\\imagens\\fimdejogo.png");
            JLabel label = new JLabel(i);
            JOptionPane.showMessageDialog(null, label, jogador2 + " VENCEU!", JOptionPane.PLAIN_MESSAGE);
            this.dispose();

            Jogador Jogador11 = new Jogador();
            Jogador11.setNome(this.jogador1);
            Jogador11.setStatus("DERROTA");

            Jogador Jogador22 = new Jogador();
            Jogador22.setNome(this.jogador2);
            Jogador22.setStatus("VITÓRIA");

            CalculoRanking calculo = new CalculoRanking(Jogador11, Jogador22, qtJogadas, 2, casasPercorridasJogador1, casasPercorridasJogador2);

            gols = 30;
            return 0;

        } else {
            return 1;
        }

    }

    /*
     Thread, fica atualizando algumas situações, como mostrar os gols do jogadores.
     a posição do mesmo
     e fica vendo se alguém foi campeão
     */
    public void run() {

        for (;;) {
            MostraPosicaoJogador(vezJogador);
            MostraGolsJogadores();
            if (verCampeao() == 0) {
                break;
            }

        }

    }
}
