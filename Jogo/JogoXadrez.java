package Jogo;

import Jogadores.Jogador;
import Jogadores.JogadorComputador;
import Jogadores.JogadorHumano;
import Tabuleiro.Tabuleiro;
import java.util.Random;
import java.util.Scanner;
import pecas.Pecas;
import pecas.bispo.Bispo;
import pecas.cavalo.Cavalo;
import pecas.peao.Peao;
import pecas.rainha.Rainha;
import pecas.rei.Rei;
import pecas.torre.Torre;
import pecas.vazio.Vazio;

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
                System.out.println("Turno da peça branca - Digite: Posição Inicial Posição Final(ou 'desistir') ");


                // Outro ‘loop’ até a jogada estar certa - Separador precisa estar correto, jogada ser valida e a peça ser da cor valida
                String[] pos = new String[2];
                boolean boolCor = false;
                boolean boolMoviment = false;
                do {
                    String jogada = jogador1.realizarJogada();
                    if (jogada.equalsIgnoreCase("desistir") || jogada.equalsIgnoreCase("ff")) {
                        System.out.println("\nO jogador de peças BRANCAS desistiu da partida!");
                        System.out.println("Fim de Jogo: Vitoria das peças PRETAS por desistência.");
                        return; // Encerra o método e o jogo imediatamente
                    }
                    // Primeiro vemos se esta correta a posicao
                    pos = separadorJogada(jogada);

                    if (pos == null) System.out.println("Formato de jogada errada - Use posInicial posFinal");

                    // Segundo verificamos a cor
                    if (pos != null) {
                        boolCor = tabuleiro.getCorPeca(pos[0], Pecas.Cores.BRANCO);
                        if (!boolCor) {
                            System.out.println("Erro: A peça na posição inicial não é branca (ou a casa está vazia)");
                        }
                    }

                    if (pos != null && boolCor) {

                        // Agora verificamos se a jogada deixa em xeque ou se a jogada permanece em xeque
                        // Vamos criar um tabulueiro auxiliar para simular a jogada
                        Tabuleiro tabAux = new Tabuleiro(tabuleiro);
                        boolMoviment = tabAux.movimentar(pos[0], pos[1]);

                        // Verifica o movimento
                        if (!boolMoviment) System.out.println("Movimento invalido - Jogue novamente");
                        else {
                            boolean xequeAtual = isXeque(tabAux, Pecas.Cores.BRANCO);
                            if (xequeAtual) {
                                System.out.println("Movimento invalido - O rei nao pode estar em xeque");
                                boolMoviment = false;
                            } else {
                                tabuleiro.movimentar(pos[0], pos[1]);
                            }
                        }
                    }

                } while (pos == null || !boolCor || !boolMoviment);
                verificarPromocao(tabuleiro, pos[1], jogador1);

                atualizarRoque(Pecas.Cores.BRANCO);
                atualizarRoque(Pecas.Cores.PRETO);

                realizarRoque(Pecas.Cores.BRANCO);

                // Veremos se

                tabuleiro.mostraTabuleiro();

            } else {
                System.out.println("Turno da peça preta - Digite: Posição Inicial Posição Final (ou 'desistir') ");


                // Outro ‘loop’ até a jogada estar certa - Separador precisa estar correto, jogada ser valida e a peça ser da cor valida
                String[] pos = new String[2];
                boolean boolCor = false;
                boolean boolMoviment = false;
                do {
                    String jogada = jogador2.realizarJogada();
                    if (jogada.equalsIgnoreCase("desistir") || jogada.equalsIgnoreCase("ff")) {
                        System.out.println("\nO jogador de peças PRETAS desistiu da partida!");
                        System.out.println("Fim de Jogo: Vitoria das peças BRANCAS por desistência.");
                        return; // Encerra o método e o jogo imediatamente
                    }
                    // Primeiro vemos se esta correta a posicao
                    pos = separadorJogada(jogada);

                    if (pos == null) System.out.println("Formato de jogada errada - Use posInicial posFinal");

                    if (pos != null) {
                        boolCor = tabuleiro.getCorPeca(pos[0], Pecas.Cores.PRETO);
                        if (!boolCor) {
                            System.out.println("Erro: A peça na posição inicial não é preta (ou a casa está vazia)");
                        }
                    }

                    if (pos != null && boolCor) {

                        // Agora verificamos se a jogada deixa em xeque ou se a jogada permanece em xeque
                        // Vamos criar um tabulueiro auxiliar para simular a jogada
                        Tabuleiro tabAux = new Tabuleiro(tabuleiro);
                        boolMoviment = tabAux.movimentar(pos[0], pos[1]);

                        // Verifica o movimento
                        if (!boolMoviment) System.out.println("Movimento invalido - Jogue novamente");
                        else {
                            boolean xequeAtual = isXeque(tabAux, Pecas.Cores.PRETO);
                            if (xequeAtual) {
                                System.out.println("Movimento invalido - O rei nao pode estar em xeque");
                                boolMoviment = false;
                            } else {
                                tabuleiro.movimentar(pos[0], pos[1]);
                            }
                        }
                    }

                } while (pos == null || !boolCor || !boolMoviment);
                verificarPromocao(tabuleiro, pos[1], jogador2);

                atualizarRoque(Pecas.Cores.BRANCO);
                atualizarRoque(Pecas.Cores.PRETO);

                realizarRoque(Pecas.Cores.PRETO);
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
            // Se o número da jogada for par = Branco joga, se o número for impar preto joga
            if (contador % 2 ==0) {
                System.out.println("Turno da peça branca - Digite: Posição Inicial Posição Final (ou 'desistir') ");


                // Outro ‘loop’ até a jogada estar certa - Separador precisa estar correto, jogada ser valida e a peça ser da cor valida
                String[] pos = new String[2];
                boolean boolCor = false;
                boolean boolMoviment = false;

                do {
                    String jogada = jogador1.realizarJogada();
                    if (jogada.equalsIgnoreCase("desistir") || jogada.equalsIgnoreCase("ff")) {
                        System.out.println("\nO jogador de peças BRANCAS desistiu da partida!");
                        System.out.println("Fim de Jogo: Vitoria das peças PRETAS por desistência.");
                        return; // Encerra o método e o jogo imediatamente
                    }
                    // Primeiro vemos se esta correta a posicao
                    pos = separadorJogada(jogada);

                    if (pos == null) System.out.println("Formato de jogada errada - Use posInicial posFinal");

                    // Segundo verificamos a cor
                    if (pos != null) {
                        boolCor = tabuleiro.getCorPeca(pos[0], Pecas.Cores.BRANCO);
                        if (!boolCor) {
                            System.out.println("Erro: A peça na posição inicial não é branca (ou a casa está vazia)");
                        }
                    }

                    if (pos != null && boolCor) {

                        // Agora verificamos se a jogada deixa em xeque ou se a jogada permanece em xeque
                        // Vamos criar um tabulueiro auxiliar para simular a jogada
                        Tabuleiro tabAux = new Tabuleiro(tabuleiro);
                        boolMoviment = tabAux.movimentar(pos[0], pos[1]);

                        // Verifica o movimento
                        if (!boolMoviment) System.out.println("Movimento invalido - Jogue novamente");
                        else {
                            boolean xequeAtual = isXeque(tabAux, Pecas.Cores.BRANCO);
                            if (xequeAtual) {
                                System.out.println("Movimento invalido - O rei nao pode estar em xeque");
                                boolMoviment = false;
                            } else {
                                tabuleiro.movimentar(pos[0], pos[1]);
                            }
                        }
                    }


                } while (pos == null || !boolCor || !boolMoviment);
                verificarPromocao(tabuleiro, pos[1], jogador1);

                atualizarRoque(Pecas.Cores.BRANCO);
                atualizarRoque(Pecas.Cores.PRETO);

                realizarRoque(Pecas.Cores.BRANCO);

                // Veremos se

                tabuleiro.mostraTabuleiro();

            } else {
                // Computador jogando


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
                        boolCor = tabuleiro.getCorPeca(pos[0], Pecas.Cores.PRETO);

                    }

                    if (pos != null && boolCor) {

                        // Agora verificamos se a jogada deixa em xeque ou se a jogada permanece em xeque
                        // Vamos criar um tabulueiro auxiliar para simular a jogada
                        Tabuleiro tabAux = new Tabuleiro(tabuleiro);
                        boolMoviment = tabAux.movimentar(pos[0], pos[1]);

                        // Verifica o movimento

                        if (boolMoviment) {
                            boolean xequeAtual = isXeque(tabAux, Pecas.Cores.PRETO);
                            if (xequeAtual) {
                                System.out.println("Movimento invalido - O rei nao pode estar em xeque");
                                boolMoviment = false;
                            } else {
                                tabuleiro.movimentar(pos[0], pos[1]);
                            }
                        }
                    }



                } while (pos == null || !boolCor || !boolMoviment);
                verificarPromocao(tabuleiro, pos[1], jogador2);

                atualizarRoque(Pecas.Cores.BRANCO);
                atualizarRoque(Pecas.Cores.PRETO);

                realizarRoque(Pecas.Cores.PRETO);

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

            return null;
        }

        String pos1 = jogada.substring(0,2);
        char espaco = jogada.charAt(2);
        String pos2 = jogada.substring(3,5);


        // Verificando se a jogada faz sentido

        if (espaco != ' ' || tabuleiro.tratarJogada(pos1, pos2) == null) {

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

    // Atualizar condições de roque dos reis
    public void atualizarRoque(Pecas.Cores cor) {
        int linha;

        Rei rei;
        Torre torre_1 = null, torre_2 = null;

        boolean roqueLongo = true, roqueCurto = true;

        if (cor == Pecas.Cores.BRANCO) 
            linha = 0;
        else 
            linha = 7;

        System.out.println(tabuleiro.getPeca(linha, 4));

        // Se o rei não estiver no lugar dele, nem testar o resto
        if (!(tabuleiro.getPeca(linha, 4) instanceof Rei)) return;

        rei = (Rei)tabuleiro.getPeca(linha, 4);

        // Se o rei tiver se movido, roque impossível
        if (rei.getMoveu()) {
            rei.setRoqueCurto(false);
            rei.setRoqueLongo(false);

            return;
        }

        // Se as torres não estiverem em seus lugares, roque impossível
        if (!(tabuleiro.getPeca(linha, 0) instanceof Torre)) {
            roqueLongo = false;
        }

        else torre_1 = (Torre)tabuleiro.getPeca(linha, 0);

        if (!(tabuleiro.getPeca(linha, 7) instanceof Torre)) {
            roqueCurto = false;
        }

        else torre_2 = (Torre)tabuleiro.getPeca(linha, 7);

        // Se as torres não tiverem se movido, roque impossível
        if (torre_1 != null && torre_1.getMoveu()) roqueLongo = false;
        if (torre_2 != null && torre_2.getMoveu()) roqueCurto = false;

        // Se houver peças entre o rei e a torre, roque impossível
        if (!(tabuleiro.getPeca(linha, 3) instanceof Vazio) ||
            !(tabuleiro.getPeca(linha, 2) instanceof Vazio) ||
            !(tabuleiro.getPeca(linha, 1) instanceof Vazio))  {
            roqueLongo = false;
        }

        if (!(tabuleiro.getPeca(linha, 5) instanceof Vazio) ||
            !(tabuleiro.getPeca(linha, 6) instanceof Vazio)) {
            roqueCurto = false;
        }

        // Se o rei ficar em xeque em posições adjacentes ao roque, roque impossível

        Pecas pecaAux1 = tabuleiro.getPeca(linha, 2);
        Pecas pecaAux2 = tabuleiro.getPeca(linha, 3);

        tabuleiro.substituirPeca(linha, 2, rei);
        tabuleiro.substituirPeca(linha, 3, rei);

        if (isXeque(tabuleiro, cor)) {
            rei.setRoqueLongo(false);
        }

        tabuleiro.substituirPeca(linha, 2, pecaAux1);
        tabuleiro.substituirPeca(linha, 3, pecaAux2);

        pecaAux1 = tabuleiro.getPeca(linha, 5);
        pecaAux2 = tabuleiro.getPeca(linha, 6);

        tabuleiro.substituirPeca(linha, 5, rei);
        tabuleiro.substituirPeca(linha, 6, rei);

        if (isXeque(tabuleiro, cor)) {
            rei.setRoqueCurto(false);
        }

        tabuleiro.substituirPeca(linha, 5, pecaAux1);
        tabuleiro.substituirPeca(linha, 6, pecaAux2);

        rei.setRoqueLongo(roqueLongo);
        rei.setRoqueCurto(roqueCurto);
    }

    public void realizarRoque(Pecas.Cores cor) {
        int linha;

        if (cor == Pecas.Cores.BRANCO) linha = 0;
        else linha = 7;

        Pecas possivel_roque_1 = tabuleiro.getPeca(linha, 2);
        Pecas possivel_roque_2 = tabuleiro.getPeca(linha, 6);

        if (possivel_roque_1 instanceof Rei && possivel_roque_1.getCor() == cor) {
            Rei roque_1 = (Rei)possivel_roque_1;
            
            if (roque_1.getRoqueou()) {
                tabuleiro.substituirPeca(linha, 3, tabuleiro.getPeca(linha, 0));
                tabuleiro.substituirPeca(linha, 0, new Vazio(Pecas.Cores.NEUTRA));
                roque_1.setRoqueou(false);
            }
        }

        else if (possivel_roque_2 instanceof Rei && possivel_roque_2.getCor() == cor) {
            Rei roque_2 = (Rei)possivel_roque_2;
            
            if (roque_2.getRoqueou()) {
                tabuleiro.substituirPeca(linha, 5, tabuleiro.getPeca(linha, 7));
                tabuleiro.substituirPeca(linha, 7, new Vazio(Pecas.Cores.NEUTRA));
                roque_2.setRoqueou(false);
            }
        }
    }

    // Método para verificar a promoção do peão
    public void verificarPromocao(Tabuleiro tabuleiro, String posDestino, Jogador jogadorAtual) {
        int linha = Character.getNumericValue(posDestino.charAt(1)) - 1;
        int coluna = tabuleiro.mapaColunas.get(posDestino.charAt(0));

        Pecas peca = tabuleiro.getPeca(linha, coluna);

        if (peca instanceof Peao) {
            boolean promocaoBranca = (peca.getCor() == Pecas.Cores.BRANCO && linha == 7);
            boolean promocaoNegra = (peca.getCor() == Pecas.Cores.PRETO && linha == 0);

            if (promocaoBranca || promocaoNegra) {
                promoverPeao(tabuleiro, linha, coluna, peca.getCor(), jogadorAtual);
            }
        }
    }

    private void promoverPeao(Tabuleiro tabuleiro, int linha, int coluna, Pecas.Cores cor, Jogador jogadorAtual) {
        Pecas novaPeca;

        if (jogadorAtual instanceof JogadorComputador) {
            // Computador escolhe sozinho, sem pedir input
            System.out.println("\n PROMOÇÃO DO PEÃO (Computador) ");
            Random random = new Random();
            int escolha = random.nextInt(4) + 1; // 1 a 4

            switch (escolha) {
                case 2: novaPeca = new Torre(cor); break;
                case 3: novaPeca = new Bispo(cor); break;
                case 4: novaPeca = new Cavalo(cor); break;
                default: novaPeca = new Rainha(cor); break;
            }
            System.out.println("Computador promoveu o peão para: " + novaPeca.getClass().getSimpleName());

        } else {
            System.out.println("\n PROMOÇÃO DO PEÃO! ");
            System.out.println("Escolha a peça para qual deseja promover:");
            System.out.println("1 - Rainha | 2 - Torre | 3 - Bispo | 4 - Cavalo");
            System.out.print("Sua escolha: ");

            Scanner scanner = new Scanner(System.in);
            int escolha = scanner.nextInt();

            switch (escolha) {
                case 2: novaPeca = new Torre(cor); break;
                case 3: novaPeca = new Bispo(cor); break;
                case 4: novaPeca = new Cavalo(cor); break;
                default: novaPeca = new Rainha(cor); break;
            }
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
