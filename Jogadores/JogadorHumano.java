package Jogadores;

import pecas.Pecas;

import java.util.Scanner;

public class JogadorHumano extends Jogador implements Jogar{
    private Scanner scanner;

    public JogadorHumano(Pecas.Cores cor) {
        super(cor);
        this.scanner = new Scanner(System.in);
    }


    @Override
    public String realizarJogada() {

        return scanner.nextLine();
    }
}
