package pecas.torre;

import pecas.validar_jogadas.ValidarJogadas;
import pecas.Pecas;


public class Torre extends Pecas implements  ValidarJogadas {

    //Construtor
    public Torre(Pecas.Cores cor) {
        super(cor);
    }

    // Metodos
    @Override
    public boolean is_valid(int x1, int y1, int x2, int y2) {

    }



}