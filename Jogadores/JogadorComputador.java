package Jogadores;

import pecas.Pecas;

import java.util.Random;

public class JogadorComputador extends Jogador implements Jogar{
    public JogadorComputador(Pecas.Cores cor) {
        super(cor);
    }

    @Override
    public String realizarJogada() {
        // Basicamente o computador ira pegar uma jogada aleatoria ate achar uma valida e ira jogar
        // Entao ele precisa pegar dois pares (coluna, linha) ate ser verdadeira
        Random random = new Random();
        int x1 = random.nextInt(7);
        int x2 = random.nextInt(7);
        int y1 = random.nextInt(7);
        int y2 = random.nextInt(7);


        return Integer.toString(x1) + Integer.toString(y1) + " " + Integer.toString(x2) + Integer.toString(y2);
    }
}
