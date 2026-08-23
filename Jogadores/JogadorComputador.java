package Jogadores;

import pecas.Pecas;

import java.util.Random;

public class JogadorComputador extends Jogador implements Jogar{
    public JogadorComputador(Pecas.Cores cor) {
        super(cor);
    }

    @Override
    public String realizarJogada() {
        Random random = new Random();
        char[] colunas = {'a','b','c','d','e','f','g','h'};

        char col1 = colunas[random.nextInt(8)];
        int  lin1 = random.nextInt(8) + 1;
        char col2 = colunas[random.nextInt(8)];
        int  lin2 = random.nextInt(8) + 1;

        return "" + col1 + lin1 + " " + col2 + lin2;
    }
}
