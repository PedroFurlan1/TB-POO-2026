package pecas.peao;

import pecas.validar_jogadas.ValidarJogadas;
import pecas.Pecas;

import static java.lang.Math.abs;


public class Peao extends Pecas implements  ValidarJogadas {
    private int numMovimento;

    //Construtor
    public Peao(Pecas.Cores cor) {
        super(cor);
    }

    // Metodos
    public boolean is_valid(int x1, int y1, int x2, int y2) {
        // Primeiro verificamos se a peça se moveu
        if (super.samPlace(x1, y1, x2, y2)) return false;

        // Agora validaremos a jogada
        // Movimento para preças brancas e movimento para frente | movimento para preças brancas e pretas de comer | movimento para peças brancas e pretas pularem 2 casas
        if (super.cor == Cores.BRANCO && (((x1-x2) == 0 && (y1-y2) == -1) || ((y1-y2) == -1) && ((x1-x2) == -1 || (x1-x2) == 1))) return true;
        else if (super.cor == Cores.PRETO && (((x1-x2) == 0 && (y1-y2) == 1) || ((y1-y2) == 1) && ((x1-x2) == -1 || (x1-x2) == 1))) return true;
        else if (super.cor == Cores.BRANCO && ((this.numMovimento == 0) && (x1-x2) == 0) && (y1-y2) == -2) return true;
        else if (super.cor == Cores.PRETO && ((this.numMovimento == 0) && (x1-x2) == 0) && (y1-y2) == 2) return true;

        return false;
    }

    public void incrementarMovimento () {this.numMovimento++;}


}