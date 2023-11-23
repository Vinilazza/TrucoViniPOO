package Truco.Regras;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Jogadores {
    private String nome;
    private List<Carta> mao;

    public Jogadores(String nome) {
        this.nome = nome;
        this.mao = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<Carta> getMao() {
        return mao;
    }

    public void receberCarta(Carta carta) {
        mao.add(carta);
    }

    @Override
    public String toString() {
        return nome;
    }
}
