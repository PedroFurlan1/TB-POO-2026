package Tabuleiro;

import pecas.Pecas;
import java.util.*;
import pecas.cavalo.Cavalo;
import pecas.peao.Peao;
import pecas.bispo.Bispo;
import pecas.torre.Torre;
import pecas.rainha.Rainha;
import pecas.rei.Rei;
import pecas.vazio.Vazio;

public class Tabuleiro {
    // Atributos
    private Pecas[][] pecas;
    private final int tam = 8;
    public final Map<Character, Integer> mapaColunas = Map.of(
            'a', 0, 'b', 1, 'c', 2, 'd', 3, 'e', 4, 'f', 5, 'g', 6, 'h', 7
    );

    // Constutor (inicializa o tabuleiro)
    public Tabuleiro() {
        pecas = new Pecas[tam][tam];
        // Inicializando todas as peças

        // Brancas
        int i = 0, j = tam - 1;
        while (i<j) {
            if(i == 0){
                pecas[0][i] = new Torre(Pecas.Cores.BRANCO);
                pecas[0][j] = new Torre(Pecas.Cores.BRANCO);
            }
            if (i == 1) {
                pecas[0][i] = new Cavalo(Pecas.Cores.BRANCO);
                pecas[0][j] = new Cavalo(Pecas.Cores.BRANCO);
            }
            if (i == 2) {
                pecas[0][i] = new Bispo(Pecas.Cores.BRANCO);
                pecas[0][j] = new Bispo(Pecas.Cores.BRANCO);
            }
            i++; j--;
        }
        // Rei e rainha
        pecas[0][i] = new Rei(Pecas.Cores.BRANCO);
        pecas[0][j] = new Rainha(Pecas.Cores.BRANCO);
        // Peoes
        i = 0;
        for (; i < tam; i++) {
            pecas[1][i] = new Peao(Pecas.Cores.BRANCO);
        };

        // Pretas
         i = 0; j = tam - 1;
        while (i<j) {
            if(i == 0){
                pecas[7][i] = new Torre(Pecas.Cores.PRETO);
                pecas[7][j] = new Torre(Pecas.Cores.PRETO);
            }
            if (i == 1) {
                pecas[7][i] = new Cavalo(Pecas.Cores.PRETO);
                pecas[7][j] = new Cavalo(Pecas.Cores.PRETO);
            }
            if (i == 2) {
                pecas[7][i] = new Bispo(Pecas.Cores.PRETO);
                pecas[7][j] = new Bispo(Pecas.Cores.PRETO);
            }
            i++; j--;
        }
        // Rei e rainha
        pecas[7][i] = new Rei(Pecas.Cores.PRETO);
        pecas[7][j] = new Rainha(Pecas.Cores.PRETO);
        // Peoes
        i = 0;
        for (; i < tam; i++) pecas[6][i] = new Peao(Pecas.Cores.PRETO);

        // Vazias
        i = 2; j = 0;
        for (;i <= 5; i++) {
            for (j = 0;j < tam; j++) {
                pecas[i][j] = new Vazio(Pecas.Cores.NEUTRA);
            }
        }
    }


    // Metodos

    // Responsavel pelas jogadas de movimentacao
    public boolean movimentar(String pos1, String pos2) {
        // Primeiro precisamos pegar os valores das posicoes
        int[] vetor = tratarJogada(pos1, pos2);
        if (vetor == null) return false;

        int x1 = vetor[0] - 1; int y1 = vetor[1]; int x2 = vetor[2] - 1; int y2 = vetor[3];

        // Primeiro vamos pegar a peça na posicao incial
        Pecas pecaAux = pecas[x1][y1];


        // Verificamos se a peca e valida
        if (!(pecas[x1][y1].is_valid(x1, y1, x2, y2))) {
            System.out.println("Movimento invalido para essa peça");
            return false;
        }

        // Precisamos verficar se e uma ação de comer - Peao é o unico que come diferente - caso a parte para verificar se é valida ainda
        if (!(pecas[x2][y2] instanceof Vazio)) {
            // Verificando se e da mesma cor
            if (pecas[x2][y2].getCor() == pecas[x1][y1].getCor()) {
                System.out.println("Erro: É possivel apenas comer peças de cores diferentes");
                return false;
            }


            // Verificando se tem alguma peça no caminho
            if ((pecas[x1][y1] instanceof Rainha || pecas[x1][y1] instanceof Torre || pecas[x1][y1] instanceof Bispo) && !(caminhoLimpo(x1, y1, x2, y2))) {
                System.out.println("Erro: Há peças no caminho");
                return false;
            }

            // Ação de comer - peao come diferentes
            if (!(pecas[x1][y1] instanceof Peao)) {
                pecas[x2][y2] = pecas[x1][y1];
                pecas[x1][y1] = new Vazio(Pecas.Cores.NEUTRA);

            } else {
                if (y1 == y2) {
                    System.out.println("O peão não pode comer para frente");
                    return false;

                } else {
                    pecas[x2][y2] = pecas[x1][y1];
                    pecas[x1][y1] = new Vazio(Pecas.Cores.NEUTRA);
                }
            }

        } else {
            // Verificando se peao esta andando corretamente
            if (pecas[x1][y1] instanceof Peao && (y1 - y2) != 0) {
                System.out.println("Erro: O peao pode apenas andar para frente");
                return false;
            }

            if ((pecas[x1][y1] instanceof Rainha || pecas[x1][y1] instanceof Torre || pecas[x1][y1] instanceof Bispo) && !(caminhoLimpo(x1, y1, x2, y2))) {
                System.out.println("Erro: Há peças no caminho");
                return false;
            }

            // Trocamos o vazio por ela
            pecas[x2][y2] = pecas[x1][y1];
            pecas[x1][y1] = new Vazio(Pecas.Cores.NEUTRA);

        }
        if (pecaAux instanceof Peao ) ((Peao) pecaAux).incrementarMovimento();

        return true;
    }

    // Verifica se as jogadas estão dentros dos conformes do tabuleiro
    public int[] tratarJogada(String pos1, String pos2) {
        // Vertifica o tamanho
        if (pos1.length() != 2 || pos2.length() != 2) {
            System.out.println("Erro: Tamanho de pos incial ou final errado");
            return null;
        }

        // Vamos tratar a string
        char col1 = pos1.charAt(0);
        char lin1 = pos1.charAt(1);
        char col2 = pos2.charAt(0);
        char lin2 = pos2.charAt(1);

        // Verificando os tipos
        if (!(Character.isDigit(lin1)) || !(Character.isDigit(lin2))) {
            System.out.println("Erro: linhas devem ser digitos");
            return null;
        }

        if ((Character.isDigit(col1)) || (Character.isDigit(col2))) {
            System.out.println("Erro: linhas devem ser caracteres");
            return null;
        }

        int x1 = Character.getNumericValue(lin1);
        int x2 = Character.getNumericValue(lin2);

        // Verificando o limite
        if ((x1 <1 || x1 > 8) || (x2 <1 || x2 > 8)) {
            System.out.println("Erro: O valor da linha deve ser de 1 a 8");
            return null;
        }

        // Verificando chaves
        if (!(mapaColunas.containsKey(col1)) || !(mapaColunas.containsKey(col2))) {
            System.out.println("Erro: O valor da coluna deve ser a, b, c, d, e, f, h ou i");
            return null;
        }

        // Verificando a outra entrada
        int y1 = mapaColunas.get(col1);
        int y2 = mapaColunas.get(col2);

        // Verificando se saiu do lugar
        if (x1 == x2 && y1 == y2) {
            System.out.println("Erro: A peça não saiu do lugar");
            return null;
        }

        // Retornando


        return new int[]{x1, y1, x2, y2};


    }

    // Verificando se o caminho esta limpo para pecas - Rainha, Torre e Bispo
    public boolean caminhoLimpo(int x1, int y1, int x2, int y2) {
        int deltaX = Integer.compare(x2, x1);
        int deltaY = Integer.compare(y2, y1);

        int x = x1 + deltaX;
        int y = y1 + deltaY;

        while (x != x2 || y != y2) {
            if (!(pecas[x][y] instanceof Vazio)) return false;
            x += deltaX;
            y += deltaY;
        }

        return true;
    }


    // Mostra tabuleiro
    public void mostraTabuleiro() {
        //Criando e printando o vetor com as letras que estarão nas colunas
        char[] letras = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        System.out.print(" \t");
        for (int k = 0; k<8; k++) {
            System.out.printf("%c\t", letras[k]);
        }
        //Laços aninhados que printarão o tabuleiro
        for (int i = 0; i< 8; i++) {
            System.out.println();                 //Espaço entre as linhas
            System.out.printf("%d\t", i);         //Printando os números das linhas
            for(int j = 0; j< 8; j++) {
                if (pecas[i][j] instanceof Vazio) {
                    System.out.print(".\t");                    //Printando . caso o espaço esteja vazio
                    continue;
                }
                switch(pecas[i][j].getCor()) {                     //Switch para separar as peças pretas das brancas
                    case PRETO:
                        switch (pecas[i][j]) {                     //Switch para printar de acordo com o tipo da peça
                            case Rei rei -> System.out.print("♚\t");
                            case Rainha rainha -> System.out.print("♛\t");
                            case Torre torre -> System.out.print("♜\t");
                            case Bispo bispo -> System.out.print("♝\t");
                            case Cavalo cavalo -> System.out.print("♞\t");
                            case null, default -> System.out.print("♟\t");
                        }
                        break;
                    case BRANCO:
                        switch (pecas[i][j]) {                      //Switch para printar de acordo com o tipo da peça
                            case Rei rei -> System.out.print("♔\t");
                            case Rainha rainha -> System.out.print("♕\t");
                            case Torre torre -> System.out.print("♖\t");
                            case Bispo bispo -> System.out.print("♗\t");
                            case Cavalo cavalo -> System.out.print("♘\t");
                            case null, default -> System.out.print("♙\t");

                        }
                        break;
            }

            }
            System.out.println();
        }
    }

    public boolean conferirColuna(Character pos) {
        return mapaColunas.containsKey(pos);
    }

    public Boolean getCorPeca(String pos, Pecas.Cores cor) {
        Pecas pecasAux = pecas[Character.getNumericValue(pos.charAt(0))][Character.getNumericValue(pos.charAt(1))];
        return (pecasAux.getCor() == cor);
    }

    public Pecas getPeca(int i, int j) {
        return this.pecas[i][j];
    }

// Método para a Promoção do Peão
    public void substituirPeca(int linha, int coluna, Pecas novaPeca) {
        this.pecas[linha][coluna] = novaPeca;
    }
}