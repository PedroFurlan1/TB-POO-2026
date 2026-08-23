package pecas.rei;

import pecas.Pecas;
import pecas.validar_jogadas.ValidarJogadas;


public class Rei extends Pecas implements  ValidarJogadas {
    private boolean roqueCurto;
    private boolean roqueLongo;
    private boolean roqueou;
    private boolean moveu;

    //Construtor
    public Rei(Pecas.Cores cor) {
        super(cor);
        roqueCurto = false;
        roqueLongo = false;
        roqueou = false;
        moveu = false;
    }

    // Metodos
    @Override
    public boolean is_valid(int x1, int y1, int x2, int y2) {
        if(super.samPlace(x1, y1, x2, y2)) return false;

        if (x1 == x2 && y2 - y1 == 2) {
            if (roqueCurto) roqueou = true;
            else System.out.println("Roque curto não permitido!");

            return roqueou;
        }

        else if (x1 == x2 && y2 - y1 == -2) {
            if (roqueLongo) roqueou = true;
            else System.out.println("Roque longo não permitido!");

            return roqueou;
        }


        else if (Math.abs(x2 - x1) > 1 || Math.abs(y2 - y1) > 1) return false;
        
        return true;
    }

    public void setMoveu(boolean moveu) {
        this.moveu = moveu;
    }

    public boolean getMoveu() {
        return moveu;
    }

    public void setRoqueCurto(boolean roqueCurto) {
        this.roqueCurto = roqueCurto;
    }

    public void setRoqueLongo(boolean roqueLongo) {
        this.roqueLongo = roqueLongo;
    }

    public boolean getRoqueou() {
        return roqueou;
    }

    public void setRoqueou(boolean roqueou) {
        this.roqueou = roqueou;
    }

}