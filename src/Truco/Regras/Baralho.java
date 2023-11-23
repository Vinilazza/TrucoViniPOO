package Truco.Regras;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baralho {
    protected List<Carta> cartas;

    public Baralho() {
        cartas = new ArrayList<>();
        inicializarBaralho();
        embaralhar();
    }

    protected void inicializarBaralho() {
        String[] naipes = {"Espadas", "Copas", "Paus", "Ouros"};

        for (String naipe : naipes) {
            for (int valor = 1; valor <= 7; valor++) {
            		cartas.add(new Carta(valor, naipe));
            }
            for(int valor = 10; valor <= 12; valor++) {
            	cartas.add(new Carta(valor, naipe));
            }
        }
    }
    
    public void distribuirCartas(Jogadores jogador) {
        for (int i = 0; i < 3; i++) {  
            jogador.receberCarta(cartas.remove(0));
        }
    }

    protected void embaralhar() {
        Collections.shuffle(cartas);
    }

    public List<Carta> getCartas() {
        return cartas;
    }

	public Baralho(List<Carta> cartas) {
		super();
		this.cartas = cartas;
	}
	

    
}
