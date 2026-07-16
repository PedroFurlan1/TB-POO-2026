package pecas;

public class Pecas {
    // Atributos
    protected Cores cor;

    // ENUM de cores
    public enum Cores {
        PRETO,
        BRANCO
    };


    // Inicializando o objeto pecas - Quando for inicializar o tabuleiro usar Pecas.Cores.PRETO/BRANCO
    public Pecas(Cores cor) {
        this.cor = cor;
    }

    // Metodos

    //1. Metodo para ver se a peça esta na mesma posição quando se mover
    public boolean samPlace(int x1, int y1, int x2, int y2) {
        if (x1 == x2 && y1 == y2) {
            System.out.println("A peça não se moveu!");
            return true;
        }
        return false;
    }
}