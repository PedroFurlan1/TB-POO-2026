package pecas.bispo;

import pecas.validar_jogadas.ValidarJogadas;
import pecas.Pecas;


public class Bispo extends Pecas implements  ValidarJogadas {

    //Construtor
    public Bispo(Pecas.Cores cor) {
        super(cor);
    }

    // Metodos
    @Override
    public boolean is_valid(int x1, int y1, int x2, int y2) {
        // Verifica se a peça se moveu ou esta na mesma posição
        if (super.samPlace(x1, y1, x2, y2)) {
            return false;
        }
        //Verifica se o movimento é diagonal
    boolean mesmadiagonal = Math.abs(x2 - x1) == Math.abs(y2 - y1);
        if (!mesmadiagonal) {
            System.out.println("Movimento inválido");
            return false;
        }
        return true;
    }

    }

    

