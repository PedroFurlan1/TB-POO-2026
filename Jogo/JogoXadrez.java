package Jogo;

import Jogadores.Jogador;
import Jogadores.JogadorComputador;
import Jogadores.JogadorHumano;
import Tabuleiro.Tabuleiro;
import com.sun.jdi.event.StepEvent;
import pecas.Pecas;
import pecas.cavalo.Cavalo;
import pecas.peao.Peao;
import pecas.bispo.Bispo;
import pecas.torre.Torre;
import pecas.rainha.Rainha;
import pecas.rei.Rei;
import pecas.vazio.Vazio;

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

    public boolean isCheck(Tabuleiro table, Pecas.Cores cor) {
        int xaux = -1, yaux = -1;

        // 1. Encontra a posição do Rei da cor informada
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Pecas pecaAtual = table.getPeca(i, j);
                if (pecaAtual instanceof Rei && pecaAtual.getCor() == cor) {
                    xaux = i;
                    yaux = j;
                    i = 8; // Encerra a busca ao achar o Rei
                    break;
                }
            }
        }

        if (xaux == -1 || yaux == -1) return false;

        // Verifica se alguma peça inimiga ameaça o Rei
        for (int k = 0; k < 8; k++) {
            for (int w = 0; w < 8; w++) {
                Pecas temp = table.getPeca(k, w);

                if (!(temp instanceof Vazio) && temp.getCor() != cor) {

                    // Se for Peão, só está em xeque se for captura em diagonal
                    if (temp instanceof Peao) {
                        int direcao = (temp.getCor() == Pecas.Cores.BRANCO) ? 1 : -1;

                        // Verifica se o Rei está exatamente no destino da diagonal do peão
                        if (xaux - k == direcao && Math.abs(yaux - w) == 1) {
                            return true;
                        }
                    }
                    // Demais peças usam o is_valid
                    else if (temp.is_valid(k, w, xaux, yaux)) {
                        boolean precisaCaminhoLimpo = (temp instanceof Rainha || temp instanceof Torre || temp instanceof Bispo);

                        if (!precisaCaminhoLimpo || table.caminhoLimpo(k, w, xaux, yaux)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
