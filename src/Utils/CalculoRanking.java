/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Banco.GravaArquivo;

/**
 *
 * @author Jonas
 */
public class CalculoRanking {

    /*
     Recebe variáveis para alguns calculos.
     */
    public CalculoRanking(Jogador jogador1, Jogador jogador2,
            int qtJogadas, int qtJogadores, int casasPercorridas1,
            int casasPercorridas2) {

        Calcular(jogador1, jogador2, qtJogadas, qtJogadores, casasPercorridas1, casasPercorridas2);
    }

    /*
     Calcula de forma encapsulada alguns atributos
     do ranking de cada jogador.
     como a velocidade média, a quantidade 
     de casas percorridas e a média de jogadas do jogo.
     */
    private void Calcular(Jogador jogador1, Jogador jogador2,
            int qtJogadas, int qtJogadores, int casasPercorridas1,
            int casasPercorridas2) {

        int mediaJogadas = qtJogadas / qtJogadores;
        int velocidadeMedia1 = casasPercorridas1 / casasPercorridas1;
        int velocidadeMedia2 = casasPercorridas2 / casasPercorridas2;

        jogador1.setMediaJogadas(mediaJogadas);
        jogador1.setQtCasasPercorridas(casasPercorridas1);
        jogador1.setVelocidadeMedia(velocidadeMedia1);

        jogador2.setMediaJogadas(mediaJogadas);
        jogador2.setQtCasasPercorridas(casasPercorridas1);
        jogador2.setVelocidadeMedia(velocidadeMedia2);

        GravaArquivo grava = new GravaArquivo();
        grava.gravaRanking(jogador1);
        grava.gravaRanking(jogador2);

    }

}
