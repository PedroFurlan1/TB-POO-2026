package pecas.rei;

import pecas.Pecas;
import pecas.validar_jogadas.ValidarJogadas;


public class Rei extends Pecas implements  ValidarJogadas {
    private boolean cheque;

    //Construtor
    public Rei(Pecas.Cores cor) {
        super(cor);
        cheque = false;
    }

    // Metodos
    @Override
    public boolean is_valid(int x1, int y1, int x2, int y2) {
        if(super.samPlace(x1, y1, x2, y2)) return false;

        boolean roque = false;

        if (Math.abs(x2 - x1) == 2 && y1 == y2) roque = true;

        if (roque == true)
        {
            // Aqui colocar as coisas do roque
        }

        else if (Math.abs(x2 - x1) > 1 || Math.abs(y2 - y1) > 1 && roque == false) return false;
        
        return true;
    }

    public boolean getCheque() {
        return cheque;
    }
}