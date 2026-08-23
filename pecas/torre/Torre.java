package pecas.torre;

import pecas.Pecas;
import pecas.validar_jogadas.ValidarJogadas;


public class Torre extends Pecas implements  ValidarJogadas {
    private boolean moveu;

    //Construtor
    public Torre(Pecas.Cores cor) {
        super(cor);

        moveu = false;
    }

    // Metodos
    @Override
    public boolean is_valid(int x1, int y1, int x2, int y2) {
        if(super.samPlace(x1, y1, x2, y2)) return false;

        if (Math.abs(y2 - y1) >= 1 && Math.abs(x2 - x1) >= 1) return false;

        return true;
    }

    public void setMoveu(boolean moveu) {
        this.moveu = moveu;
    }

    public boolean getMoveu() {
        return moveu;
    }
}