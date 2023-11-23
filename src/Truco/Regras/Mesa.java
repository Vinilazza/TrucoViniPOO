package Truco.Regras;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Mesa extends Baralho {
    private List<Carta> cartasNaMesa;
    private Pontuacao pontuacao;
    private Carta cartaVirada;
    private int manilha;
    private boolean trucoAumentado;  // Indica se o truco foi aumentado
    private int valorTruco;  // Valor atual do truco

    public Mesa() {
        this.cartasNaMesa = new ArrayList<>();
        this.inicializarBaralho();
        this.embaralhar();  
        this.pontuacao = new Pontuacao();
        this.trucoAumentado = false;
        this.valorTruco = 1;  
   
    }

    public void adicionarCarta(Carta carta) {
        cartasNaMesa.add(carta);
    }
    public void removerCarta(Carta carta) {
    	cartasNaMesa.remove(carta);
    }

    public void virarCarta() {
        List<Carta> cartasDoBaralho = super.getCartas(); 

        if (!cartasDoBaralho.isEmpty()) {
            int indiceAleatorio = new Random().nextInt(cartasDoBaralho.size());
            cartaVirada = cartasDoBaralho.get(indiceAleatorio);
            System.out.println("Carta virada: " + cartaVirada);
        }
    }
    
    public List<Carta> getCartasNaMesa() {
        return cartasNaMesa;
    }

    public int getManilha() {    	
    	int c = cartaVirada.getValor();
            int manilha=c+1;
            if(c == 12) {
            	manilha = 1;
            }
            else if(c == 7) {
            	manilha = 10;
            }
    	return manilha;
    }
    
    public String truco(Jogadores jogador) {
        Scanner scanner = new Scanner(System.in);
        int opcaoTruco = 1;
        
        if (opcaoTruco == 1) {
            System.out.println(jogador + " pediu TRUCO!\n"
                    + "Você aceita o truco? (1 para aceitar | 0 para correr)");
            int respostaOponente = scanner.nextInt();

            if (respostaOponente == 1) {
                if (valorTruco + 3 <= 12) {
                    trucoAumentado = true;
                    valorTruco += 3;
                    String rs = "O truco foi aceito!";
                    System.out.println("O truco foi aceito! Valor do truco: " + valorTruco);
                    return rs;
                } else {
                    // Caso o valor do truco ultrapasse 12, defina o valorTruco para 12
                    valorTruco = 12;
                    String rs = "O truco foi aceito!";
                    System.out.println("O truco foi aceito! Valor do truco atingiu o máximo: " + valorTruco);
                    return rs;
                }
            } else {
                // Adicione os pontos ao jogador que trucou
                pontuacao.incrementarPontosJogador1(valorTruco);
                String rs = jogador + " correu! Você ganhou " + valorTruco + " pontos!";
                System.out.println(rs);
                return rs;
            }
        } else {
            System.out.println("Você optou por não trucar.");
            return null;
        }
    }

    

    public int compararCartas(Carta carta1, Carta carta2) {
        if (!isManilha(carta1) && !isManilha(carta2)) {
            if (carta1.getValor() == 1 && carta2.getValor() == 2) {
                return -1; // Carta 2 é melhor
            } 
            else if (carta1.getValor() == 2 && carta2.getValor() == 3) {
                return -1; // Carta 2 é melhor
            }
            else if (carta1.getValor() == 3 && carta2.getValor() == 3) {
                return 0; // Empate
            } 
            else if (carta2.getValor() == 1 && carta1.getValor() == 2) {
                return 1; // Carta 1 é melhor
            }
            else if (carta2.getValor() == 2 && carta1.getValor() == 3) {
                return 1; // Carta 1 é melhor
            } 
            else if (carta2.getValor() == 3 && carta1.getValor() == 3) {
                return 0; // Empate
            } 
            else if(carta1.getValor() == 1 && carta2.getValor() > 3) {
            	return 1;
            }
            else if(carta1.getValor() == 2 && carta2.getValor() > 3) {
            	return 1;
            }
            else if(carta1.getValor() == 3 && carta2.getValor() > 3) {
            	return 1;
            }
            else if(carta2.getValor() == 1 && carta1.getValor() > 3) {
            	return -1;
            }
            else if(carta2.getValor() == 2 && carta1.getValor() > 3) {
            	return -1;
            }
            else if(carta2.getValor() == 3 && carta1.getValor() > 3) {
            	return -1;
            }
            else if ((carta1.getValor()) > (carta2.getValor())) {
                return 1;
            } else if ((carta2.getValor()) > (carta1.getValor())) {
                return -1;
            }
        } else if (isManilha(carta1) && isManilha(carta2)) {
            // Se ambas são manilhas, compara diretamente os naipes
            String naipeManilhaCarta1 = carta1.getNaipe();
            String naipeManilhaCarta2 = carta2.getNaipe();
            
            return compararNaipes(naipeManilhaCarta1, naipeManilhaCarta2);
        } else if (isManilha(carta1)) {
            return 1; // Carta 1 é melhor (por ser manilha)
        } else {
            return -1; // Carta 2 é melhor (por ser manilha)
        }

        return 0; // Empate padrão
    }

    private boolean isManilha(Carta carta) {
        return carta.getValor() == getManilha();
    }

    private int compararNaipes(String naipe1, String naipe2) {
    	if (naipe1 == "Ouros" && naipe2 == "Espadas") {
    		return -1;
    	}
    	else if (naipe1 == "Espadas" && naipe2 == "Copas") {
    		return -1;
    	}
    	else if(naipe1 == "Copas" && naipe2 == "Paus") {
    		return -1;
    	}
    	else if(naipe2 == "Ouros" && naipe1 == "Espadas") {
    		return -1;
    	}
    	else if(naipe2 == "Espadas" && naipe1 == "Ouros") {
    		return 1;
    	}
    	else if (naipe1 == "Ouros" && naipe2 == "Paus") {
    		return -1;
    	}
     	else if (naipe1 == "Ouros" && naipe2 == "Copas") {
    		return -1;
    	}
     	else if (naipe1 == "Espadas" && naipe2 == "Paus") {
    		return -1;
    	}
     	else if (naipe1 == "Espadas" && naipe2 == "Copas") {
    		return -1;
    	}
     	else if (naipe1 == "Espadas" && naipe2 == "Ouros") {
    		return 1;
    	}
     	else if (naipe1 == "Copas" && naipe2 == "Ouros") {
    		return 1;
    	}
     	else if (naipe1 == "Copas" && naipe2 == "Espada") {
    		return 1;
    	}
     	else if (naipe1 == "Copas" && naipe2 == "Paus") {
    		return -1;
    	}
     	else if (naipe1 == "Paus" && naipe2 == "Espadas") {
    		return 1;
    	}
     	else if (naipe1 == "Paus" && naipe2 == "Copas") {
    		return 1;
    	}
     	else if (naipe1 == "Paus" && naipe2 == "Ouros") {
    		return 1;
    	}
    	else {
    		return 0;
    	}
       
    }



}
