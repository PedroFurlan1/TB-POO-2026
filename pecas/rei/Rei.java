package pecas.rei;

import pecas.validar_jogadas.ValidarJogadas;
import pecas.Pecas;


public class Rei extends Pecas implements  ValidarJogadas {

    //Construtor
    public Rei(Pecas.Cores cor) {
        super(cor);
    }

    // Metodos
    @Override
    public boolean is_valid(int x1, int y1, int x2, int y2) {

    }



}