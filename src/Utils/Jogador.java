package Utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jonas
 */
public class Jogador {

    private String nome;

    //total de jogadas / nº jogadores
    private int velocidadeMedia;

    //casas percorridas / jogadas do jogador
    private int mediaJogadas;
    
    private int qtCasasPercorridas;
    
    private String status;

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the velocidadeMedia
     */
    public int getVelocidadeMedia() {
        return velocidadeMedia;
    }

    /**
     * @param velocidadeMedia the velocidadeMedia to set
     */
    public void setVelocidadeMedia(int velocidadeMedia) {
        this.velocidadeMedia = velocidadeMedia;
    }

    /**
     * @return the mediaJogadas
     */
    public int getMediaJogadas() {
        return mediaJogadas;
    }

    /**
     * @param mediaJogadas the mediaJogadas to set
     */
    public void setMediaJogadas(int mediaJogadas) {
        this.mediaJogadas = mediaJogadas;
    }

    /**
     * @return the qtCasasPercorridas
     */
    public int getQtCasasPercorridas() {
        return qtCasasPercorridas;
    }

    /**
     * @param qtCasasPercorridas the qtCasasPercorridas to set
     */
    public void setQtCasasPercorridas(int qtCasasPercorridas) {
        this.qtCasasPercorridas = qtCasasPercorridas;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
