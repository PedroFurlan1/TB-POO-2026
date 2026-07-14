import ../ValidarJogadas;
import ../Pecas;

public class Rainha implements ValidarJogadas{

     public boolean is_valid(int x1, int y1, int x2, int y2) {
        if(x1 == x2 && y1 == y2) {
            System.out.println("A peça não se moveu!");
            return false;
        }

        boolean mesmaLinha = (x1 == x2);
        boolean mesmaColuna = (y1 == y2);
        boolean diagonal = Math.abs(x2 - x1) == Math.abs(y2 - y1);
        if (!(mesmaLinha || mesmaColuna || diagonal)) {
            System.out.println("Posição inválida!");
            return false;
        }

        return true;
    }

}