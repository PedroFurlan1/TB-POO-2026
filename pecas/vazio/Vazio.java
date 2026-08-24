package pecas.vazio;

import pecas.Pecas;

public class Vazio extends Pecas{

    // Construtor
    public Vazio(Pecas.Cores cor) {
        super(cor); // A cor sera setada como branco
    }

    @Override
    public Pecas copiar() {
        return new Vazio(this.cor);
    }

}