package Jogo;

import Jogadores.Jogador;
import Jogadores.JogadorComputador;
import Jogadores.JogadorHumano;
import Tabuleiro.Tabuleiro;
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

    // Jogando o jogo HUMANO x HUMANO
    public void JogarXadrez(JogadorHumano jogador1, JogadorHumano jogador2) {
        System.out.println("------------------ Iniciando jogo de xadrez ------------------ ");
        tabuleiro.mostraTabuleiro();
        int contador = 0;

        while (!(isXequeMate(tabuleiro, Pecas.Cores.BRANCO)) && !(isXequeMate(tabuleiro, Pecas.Cores.PRETO))){
            // Variaveis
            // Se o número da jogada for par = Branco joga, se o número for impar preto joga
            if (contador % 2 ==0) {
                System.out.println("Turno da peça branca - Digite: Posição Inicial Posição Final ");


                // Outro ‘loop’ até a jogada estar certa - Separador precisa estar correto, jogada ser valida e a peça ser da cor valida
                String[] pos = new String[2];
                boolean boolCor = false;
                boolean boolMoviment = false;
                do {
                    String jogada = jogador1.realizarJogada();
                    // Primeiro vemos se esta correta a posicao
                    pos = separadorJogada(jogada);

                    // Segundo verificamos a cor
                    if (pos != null) {
                        boolCor = tabuleiro.getCorPeca(pos[0], Pecas.Cores.BRANCO);
                        if (!boolCor) {
                            System.out.println("Erro: A peça na posição inicial não é branca (ou a casa está vazia)");
                        }
                    }

                    if (pos != null && boolCor) {
                        boolMoviment = tabuleiro.movimentar(pos[0], pos[1]);
                    }

                } while (pos == null || !boolCor || !boolMoviment);
                verificarPromocao(tabuleiro, pos[1]);

                // Veremos se

                tabuleiro.mostraTabuleiro();

            } else {
                System.out.println("Turno da peça preta - Digite: Posição Inicial Posição Final ");


                // Outro ‘loop’ até a jogada estar certa - Separador precisa estar correto, jogada ser valida e a peça ser da cor valida
                String[] pos = new String[2];
                boolean boolCor = false;
                boolean boolMoviment = false;
                do {
                    String jogada = jogador2.realizarJogada();
                    // Primeiro vemos se esta correta a posicao
                    pos = separadorJogada(jogada);

                    if (pos != null) {
                        boolCor = tabuleiro.getCorPeca(pos[0], Pecas.Cores.PRETO);
                        if (!boolCor) {
                            System.out.println("Erro: A peça na posição inicial não é preta (ou a casa está vazia)");
                        }
                    }

                    if (pos != null && boolCor) {
                        boolMoviment = tabuleiro.movimentar(pos[0], pos[1]);
                    }


                } while (pos == null || !boolCor || !boolMoviment);
                verificarPromocao(tabuleiro, pos[1]);
                tabuleiro.mostraTabuleiro();

            }
            contador++;

            // Mostrando se há xeques
            if (isXeque(tabuleiro, Pecas.Cores.PRETO)) {
                System.out.println("Atenção - Peças pretas em xeque");
            } else if (isXeque(tabuleiro, Pecas.Cores.BRANCO)) {
                System.out.println("Atenção - Peças brancas em xeque");
            }

        }

    }

    // Jogando o jogo HUMANO x COMPUTADOR - computador inicia como PRETO
    public void JogarXadrez(JogadorHumano jogador1, JogadorComputador jogador2) {
        System.out.println("------------------ Iniciando jogo de xadrez ------------------ ");
        tabuleiro.mostraTabuleiro();
        int contador = 0;

        while (!(isXequeMate(tabuleiro, Pecas.Cores.BRANCO)) && !(isXequeMate(tabuleiro, Pecas.Cores.PRETO))){
            // Variaveis
            Scanner scanner = new Scanner(System.in);
            // Se o número da jogada for par = Branco joga, se o número for impar preto joga
            if (contador % 2 ==0) {
                System.out.println("Turno da peça branca - Digite: Posição Inicial Posição Final ");


                // Outro ‘loop’ até a jogada estar certa - Separador precisa estar correto, jogada ser valida e a peça ser da cor valida
                String[] pos = new String[2];
                boolean boolCor = false;
                boolean boolMoviment = false;
                do {
                    String jogada = jogador1.realizarJogada();
                    // Primeiro vemos se esta correta a posicao
                    pos = separadorJogada(jogada);

                    // Segundo verificamos a cor
                    if (pos != null) {
                        boolCor = tabuleiro.getCorPeca(pos[0], Pecas.Cores.BRANCO);
                        if (!boolCor) {
                            System.out.println("Erro: A peça na posição inicial não é branca (ou a casa está vazia)");
                        }
                    }

                    if (pos != null && boolCor) {
                        boolMoviment = tabuleiro.movimentar(pos[0], pos[1]);
                    }

                } while (pos == null || !boolCor || !boolMoviment);
                verificarPromocao(tabuleiro, pos[1]);

                // Veremos se

                tabuleiro.mostraTabuleiro();

            } else {
                // Computador jogando
                System.out.println("Turno da peça branca - Digite: Posição Inicial Posição Final ");


               // Verificando a jogada até estar certa
                String[] pos = new String[2];
                boolean boolCor = false;
                boolean boolMoviment = false;
                do {
                    String jogada = jogador2.realizarJogada();
                    // Primeiro vemos se esta correta a posicao
                    pos = separadorJogada(jogada);

                    // Segundo verificamos a cor
                    if (pos != null) {
                        boolCor = tabuleiro.getCorPeca(pos[0], Pecas.Cores.BRANCO);

                    }

                    if (pos != null && boolCor) {
                        boolMoviment = tabuleiro.movimentar(pos[0], pos[1]);
                    }

                } while (pos == null || !boolCor || !boolMoviment);
                verificarPromocao(tabuleiro, pos[1]);

                // Veremos se

                tabuleiro.mostraTabuleiro();


            }
            contador++;

            // Mostrando se há xeques
            if (isXeque(tabuleiro, Pecas.Cores.PRETO)) {
                System.out.println("Atenção - Peças pretas em xeque");
            } else if (isXeque(tabuleiro, Pecas.Cores.BRANCO)) {
                System.out.println("Atenção - Peças brancas em xeque");
            }

        }

    }


    public String[] separadorJogada(String jogada) {
        if (jogada.length() != 5) {
            System.out.println("Digite a jogada no padrão - Posição inicial(espaço)Posição Final");
            return null;
        }

        String pos1 = jogada.substring(0,2);
        char espaco = jogada.charAt(2);
        String pos2 = jogada.substring(3,5);


        // Verificando se a jogada faz sentido

        if (espaco != ' ' || tabuleiro.tratarJogada(pos1, pos2) == null) {
            System.out.println("Digite a jogada no padrão - Posição inicial(espaço)Posição Final");
            return null;
        }

        return new String[]{pos1, pos2};



    }

    public boolean isXeque(Tabuleiro table, Pecas.Cores cor) {
        int xaux = -1, yaux = -1;

        // Encontra a posição do Rei da cor informada
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
    // Método para verificar a promoção do peão
    public void verificarPromocao(Tabuleiro tabuleiro, String posDestino) {
        // Converte a string para as posições reais da matriz
        int linha = Character.getNumericValue(posDestino.charAt(1)) - 1;
        int coluna = tabuleiro.mapaColunas.get(posDestino.charAt(0));
        
        Pecas peca = tabuleiro.getPeca(linha, coluna);

        // Se a peça que chegou ali for um Peão, verifica a promoção
        if (peca instanceof Peao) {
            boolean promocaoBranca = (peca.getCor() == Pecas.Cores.BRANCO && linha == 7);
            boolean promocaoNegra = (peca.getCor() == Pecas.Cores.PRETO && linha == 0);

            if (promocaoBranca || promocaoNegra) {
                promoverPeao(tabuleiro, linha, coluna, peca.getCor());
            }
        }
    }

    private void promoverPeao(Tabuleiro tabuleiro, int linha, int coluna, Pecas.Cores cor) {
        System.out.println("\n*** PROMOÇÃO DO PEÃO! ***");
        System.out.println("Escolha a peça para qual deseja promover:");
        System.out.println("1 - Rainha | 2 - Torre | 3 - Bispo | 4 - Cavalo");
        System.out.print("Sua escolha: ");
        
        Scanner scanner = new Scanner(System.in);
        int escolha = scanner.nextInt();
        
        Pecas novaPeca;
        switch (escolha) {
            case 2: novaPeca = new Torre(cor); break;
            case 3: novaPeca = new Bispo(cor); break;
            case 4: novaPeca = new Cavalo(cor); break;
            default: novaPeca = new Rainha(cor); break;
        }
        
        tabuleiro.substituirPeca(linha, coluna, novaPeca);
    }


    public boolean isXequeMate(Tabuleiro table, Pecas.Cores cor) {
        // Se não está em xeque, não pode ser xeque-mate
        if (!isXeque(table, cor)) {
            return false;
        }

        // Passa por todas as posições do tabuleiro buscando peças da cor atacada
        for (int x1 = 0; x1 < 8; x1++) {
            for (int y1 = 0; y1 < 8; y1++) {
                Pecas pecaAtual = table.getPeca(x1, y1);

                // Se a peça for do jogador sob xeque
                if (!(pecaAtual instanceof Vazio) && pecaAtual.getCor() == cor) {

                    // Testa mover para todas as posições do tabuleiro (x2, y2)
                    for (int x2 = 0; x2 < 8; x2++) {
                        for (int y2 = 0; y2 < 8; y2++) {

                            if (x1 == x2 && y1 == y2) continue;

                            // Verifica se a mecânica da peça permite jogada
                            if (table.movimentoValidoSimulacao(x1, y1, x2, y2)) {

                                // Simula a jogada
                                Pecas pecaDestinoOriginal = table.getPeca(x2, y2);

                                table.substituirPeca(x2, y2, pecaAtual);
                                table.substituirPeca(x1, y1, new Vazio(Pecas.Cores.NEUTRA));

                                // Verifica se o Rei ainda continua em xeque após este movimento
                                boolean reiAindaEmXeque = isXeque(table, cor);

                                //Desfaz o movimento
                                table.substituirPeca(x1, y1, pecaAtual);
                                table.substituirPeca(x2, y2, pecaDestinoOriginal);

                                // Se ao menos um movimento tirar o Rei do xeque, cancela o xeque-mate
                                if (!reiAindaEmXeque) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }

        // Se testou todas as peças e nenhuma salvou o Rei, é xeque-mate
        System.out.println("XEQUE-MATE!");
        return true;
    }

}
