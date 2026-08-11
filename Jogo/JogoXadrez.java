package Jogo;

import Jogadores.Jogador;
import Jogadores.JogadorComputador;
import Jogadores.JogadorHumano;
import Tabuleiro.Tabuleiro;
import com.sun.jdi.event.StepEvent;
import pecas.Pecas;

import java.util.Scanner;

public class JogoXadrez {
    // Atributos
    private Jogador jogador1;
    private Jogador jogador2;
    private Tabuleiro tabuleiro;
    // Construtores
    public JogoXadrez(JogadorHumano jh, JogadorHumano jh1, Tabuleiro tabuleiro){
        this.jogador1 = jh;
        this.jogador2 = jh1;
        this.tabuleiro = tabuleiro;
    }

    public JogoXadrez(JogadorHumano jh, JogadorComputador jc, Tabuleiro tabuleiro){
        this.jogador1 = jh;
        this.jogador2 = jc;
        this.tabuleiro = tabuleiro;
    }

    // Jogando o jogo
    public void JogarXadrez() {
        System.out.println("------------------ Iniciando jogo de xadrez ------------------ ");
        tabuleiro.mostraTabuleiro();
        int contador = 0;

        while (true/*Verificador de xeque-mate || marcador de desistencia || empate*/){
            // Variaveis
            Scanner scanner = new Scanner(System.in);


            // Se o número da jogada for par = Branco joga, se o número for impar preto joga
            if (contador % 2 ==0) {
                System.out.println("Turno da peça branca - Digite: Posição Inicial Posição Final ");
                String jogada = scanner.nextLine();

                // Outro ‘loop’ até a jogada estar certa - Separador precisa estar correto, jogada ser valida e a peça ser da cor valida
                String[] pos = new String[2];
                boolean boolCor = false;
                boolean boolMoviment = false;
                do {
                    // Primeiro vemos se esta correta a posicao
                    pos = separadorJogada(jogada);

                    // Segundo verificamos a cor
                    if (pos != null) boolCor = tabuleiro.getCorPeca(pos[0], Pecas.Cores.BRANCO);

                    if (pos != null && boolCor) boolMoviment = tabuleiro.movimentar(pos[0], pos[1]);
                    // Terceiro movimentamos vendo caso seja valida



                } while (pos == null || !boolCor || !boolMoviment);

            } else {
                System.out.println("Turno da peça branca - Digite: Posição Inicial Posição Final ");
                String jogada = scanner.nextLine();

                // Outro ‘loop’ até a jogada estar certa - Separador precisa estar correto, jogada ser valida e a peça ser da cor valida
                String[] pos = new String[2];
                boolean boolCor = false;
                boolean boolMoviment = false;
                do {
                    // Primeiro vemos se esta correta a posicao
                    pos = separadorJogada(jogada);

                    // Segundo verificamos a cor
                    if (pos != null) boolCor = tabuleiro.getCorPeca(pos[0], Pecas.Cores.PRETO);

                    if (pos != null && boolCor) boolMoviment = tabuleiro.movimentar(pos[0], pos[1]);
                    // Terceiro movimentamos vendo caso seja valida



                } while (pos == null || !boolCor || !boolMoviment);

            }
            contador++;
        }

    }

    public String[] separadorJogada(String jogada) {
        if (jogada.length() > 5) {
            System.out.println("Digite a jogada no padrão - Posição inicial(espaço)Posição Final");
            return null;
        }

        String pos1 = jogada.substring(0,2);
        char espaco = jogada.charAt(2);
        String pos2 = jogada.substring(3,5);


        // Verificando se a jogada faz sentido

        if (espaco != ' ' || tabuleiro.tratarJogada(pos1, pos2) != null) {
            System.out.println("Digite a jogada no padrão - Posição inicial(espaço)Posição Final");
            return null;
        }

        return new String[]{pos1, pos2};



    }
}
