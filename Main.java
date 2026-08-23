import Jogadores.JogadorComputador;
import Jogadores.JogadorHumano;
import Jogo.JogoXadrez;
import Tabuleiro.Tabuleiro;
import pecas.Pecas;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Vamos inicar um jogo de xadrez
        int op = 0;
        do {
            System.out.println("------------------------ Iniciando Jogo de Xadrez -------------------------");
            System.out.println("Opção 1: Humano x Humano / Opção 2: Humano x Maquina");
            Scanner scanner = new Scanner(System.in);
            op = scanner.nextInt();

        } while (op != 1 && op != 2);

        if (op == 1) {
            // Criar Jogo Humano x Humano
            JogadorHumano jogador1 = new JogadorHumano(Pecas.Cores.PRETO);
            JogadorHumano jogador2 = new JogadorHumano(Pecas.Cores.BRANCO);
            Tabuleiro tabuleiro = new Tabuleiro();
            JogoXadrez jogo = new JogoXadrez(jogador1, jogador2, tabuleiro);
            jogo.JogarXadrez(jogador1, jogador2);

        } else {
            // Criar Jogo Humano x Maquina
            JogadorHumano jogador1 = new JogadorHumano(Pecas.Cores.BRANCO);
            JogadorComputador jogadorComputador = new JogadorComputador(Pecas.Cores.PRETO);
            Tabuleiro tabuleiro = new Tabuleiro();
            JogoXadrez jogo = new JogoXadrez(jogador1, jogadorComputador, tabuleiro);
            jogo.JogarXadrez(jogador1, jogadorComputador);

        }



    }
}
