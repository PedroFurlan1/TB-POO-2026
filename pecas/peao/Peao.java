package pecas.peao;

import pecas.Pecas;
import pecas.validar_jogadas.ValidarJogadas;

public class Peao extends Pecas implements  ValidarJogadas {
    private int numMovimento;
    private boolean enPassant;

    //Construtor
    public Peao(Pecas.Cores cor) {
        super(cor);
        numMovimento = 0;
        enPassant = false;
    }

    @Override
    public Pecas copiar() {
        Peao copia = new Peao(this.cor);
        copia.numMovimento = this.numMovimento;
        copia.enPassant = this.enPassant;
        return copia;
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

    public void setEnPassant(boolean enPassant) {
        this.enPassant = enPassant;
    }

    public boolean getEnPassant() {
        return enPassant;
    }


}