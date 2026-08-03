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
    @Override
    public boolean is_valid(int x1, int y1, int x2, int y2) {
        if (super.samPlace(x1, y1, x2, y2)) return false;

        int direcao = (super.cor == Cores.BRANCO) ? 1 : -1;

        boolean andouReto  = (y1 == y2) && (x2 - x1 == direcao);
        boolean andouDuplo = (y1 == y2) && (numMovimento == 0) && (x2 - x1 == 2 * direcao);
        boolean capturou   = (Math.abs(y2 - y1) == 1) && (x2 - x1 == direcao);

        return andouReto || andouDuplo || capturou;
    }

    public void incrementarMovimento () {this.numMovimento++;}


}