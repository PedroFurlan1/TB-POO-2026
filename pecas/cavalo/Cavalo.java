package pecas.cavalo;

import pecas.validar_jogadas.ValidarJogadas;
import pecas.Pecas;


public class Cavalo extends Pecas implements  ValidarJogadas {
    //Construtor
    public Cavalo(Pecas.Cores cor) {
        super(cor);
    }

    @Override
    public Pecas copiar() {
        return new Cavalo(this.cor);
    }


    // Metodos
    @Override
    public boolean is_valid(int x1, int y1, int x2, int y2) {
        if(super.samPlace(x1, y1, x2, y2)) return false;

        if ((Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2)) == 5) return true;
        else return false;

    }



}