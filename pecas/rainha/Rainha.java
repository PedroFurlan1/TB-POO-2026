package pecas.rainha;

import pecas.validar_jogadas.ValidarJogadas;
import pecas.Pecas;

public class Rainha extends Pecas implements ValidarJogadas{

    //Construtor
    public Rainha(Pecas.Cores cor) {
        super(cor);
    }

    // Metodos
    @Override
     public boolean is_valid(int x1, int y1, int x2, int y2) {
        //Verifica se a peça se moveu
         if(super.samPlace(x1, y1, x2, y2)) return false;

        //Verifica se está na mesma linha, mesma coluna ou mesma diagonal
        boolean mesmaLinha = (x1 == x2);
        boolean mesmaColuna = (y1 == y2);
        boolean mesmaDiagonal = Math.abs(x2 - x1) == Math.abs(y2 - y1);
        if (!(mesmaLinha || mesmaColuna || mesmaDiagonal)) {

            return false;
        }

        return true;
    }

}