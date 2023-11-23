package Truco.Regras;

public class Pontuacao {
    private int pontosJogador1;
    private int pontosJogador2;

    public Pontuacao() {
        this.pontosJogador1 = 0;
        this.pontosJogador2 = 0;
    }

    public void incrementarPontosJogador1(int pontos) {
        pontosJogador1 += pontos;
    }

    public void incrementarPontosJogador2(int pontos) {
        pontosJogador2 += pontos;
    }

    public void exibirPontuacao() {
        System.out.println("Pontuação:");
        System.out.println("Jogador 1: " + pontosJogador1 + " pontos");
        System.out.println("Jogador 2: " + pontosJogador2 + " pontos");
    }

}
