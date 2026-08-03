package Jogo;

import Jogadores.Jogador;
import Jogadores.JogadorComputador;
import Jogadores.JogadorHumano;

public class JogoXadrez {
    // Atributos
    private Jogador jogador1;
    private Jogador jogador2;

    // Construtores
    public JogoXadrez(JogadorHumano jh, JogadorHumano jh1){
        this.jogador1 = jh;
        this.jogador2 = jh1;
    }

    public JogoXadrez(JogadorHumano jh, JogadorComputador jc){
        this.jogador1 = jh;
        this.jogador2 = jc;
    }

}
