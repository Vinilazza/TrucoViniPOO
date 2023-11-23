package Truco;

import java.util.List;
import java.util.Scanner;

import Truco.Regras.*;

public class Main {
    public static void main(String[] args) {
        Jogadores jogador1 = new Jogadores("Jogador 1");
        Jogadores jogador2 = new Jogadores("Jogador 2");
        Mesa mesa = new Mesa();
        Erros erro = new Erros();
        Baralho baralho = new Baralho();
        baralho.distribuirCartas(jogador1);
        baralho.distribuirCartas(jogador2);
        Scanner sc = new Scanner(System.in);
        int contx=0,conty=0,contz=0;
        
        List<Carta> maoJogador1 = jogador1.getMao();
        List<Carta> maoJogador2 = jogador2.getMao();

        mesa.virarCarta();

        Carta cartaJogadaJogador1 = null;
        Carta cartaJogadaJogador2 = null;

        for (int i = 1; i <= 3; i++) {
            System.out.println("Turno: " + i + "/3");
            System.out.println("Ação com Jogador 1");
            System.out.println("Cartas na mesa: " + mesa.getCartasNaMesa());
            System.out.println("Manilha: "+mesa.getManilha());
            System.out.println("Suas cartas: " + maoJogador1);
            System.out.println("Qual ação tomar? \n"
            		+ "1 - Escolher uma carta para jogar \n"
            		+ "2 - Pedir TRUCO"); 
            int opc = sc.nextInt();
            
            if(opc == 1) {
            	System.out.println("Escolha uma carta para jogar (1-" + maoJogador1.size() + "): ");
            int escolhaJogador1 = sc.nextInt();
            cartaJogadaJogador1 = maoJogador1.get(escolhaJogador1 - 1);
            maoJogador1.remove(escolhaJogador1 - 1);
            mesa.adicionarCarta(cartaJogadaJogador1);
            }
            else if(opc == 2) {
            	String respostaTruco1 = mesa.truco(jogador1);

            	if (respostaTruco1 != null) {
            	    if (respostaTruco1.equals("O truco foi aceito!")) {
            	        System.out.println("Suas cartas: " + maoJogador1);
            	        System.out.println("Escolha uma carta para jogar (1-" + maoJogador1.size() + "): ");
            	        int escolhaJogador1 = sc.nextInt();
            	        cartaJogadaJogador1 = maoJogador1.get(escolhaJogador1 - 1);
            	        maoJogador1.remove(escolhaJogador1 - 1);
            	        mesa.adicionarCarta(cartaJogadaJogador1);
            	    }
            	}
            	else {
            		break;
            	}
            }
            else {
            	erro.err1();
            }
            

            System.out.println("Ação com Jogador 2");
            System.out.println("Cartas na mesa: " + mesa.getCartasNaMesa());
            System.out.println("Manilha: "+mesa.getManilha());
            System.out.println("Suas cartas: " + maoJogador2);
            System.out.println("Qual ação tomar? \n"
            		+ "1 - Escolher uma carta para jogar \n"
            		+ "2 - Pedir TRUCO"); 
            int opc2 = sc.nextInt();
            
            if(opc2 == 1) {
            	System.out.println("Escolha uma carta para jogar (1-" + maoJogador2.size() + "): ");
            int escolhaJogador2 = sc.nextInt();
            cartaJogadaJogador2 = maoJogador2.get(escolhaJogador2 - 1);
            maoJogador2.remove(escolhaJogador2 - 1);
            mesa.adicionarCarta(cartaJogadaJogador2);
            }
            else if(opc2 == 2) {
            	String respostaTruco2 = mesa.truco(jogador2);
            	if (respostaTruco2 != null) {
            	    if (respostaTruco2.equals("O truco foi aceito!")) {
            	        System.out.println("Suas cartas: " + maoJogador2);
            	        System.out.println("Escolha uma carta para jogar (1-" + maoJogador2.size() + "): ");
            	        int escolhaJogador2 = sc.nextInt();
            	        cartaJogadaJogador2 = maoJogador2.get(escolhaJogador2 - 1);
            	        maoJogador2.remove(escolhaJogador2 - 1);
            	        mesa.adicionarCarta(cartaJogadaJogador2);
            	    }
            	}	
            }
            else {
            	erro.err1();
            }
            if(cartaJogadaJogador1 != null && cartaJogadaJogador2 != null) {
            	int resultado = mesa.compararCartas(cartaJogadaJogador1, cartaJogadaJogador2);

                if (resultado > 0) {
                	contx++;
                	System.out.println(contx);
                	if(contx>=2) {
                    	System.out.println("Jogo encerrado! \n"
                    	+"Jogador 1 foi o ganhador");
                    	break;
                    }
                	mesa.removerCarta(cartaJogadaJogador1);
                	mesa.removerCarta(cartaJogadaJogador2);
                    System.out.println("Jogador 1 venceu a rodada "+i+"!");
                } else if (resultado < 0) {
                	conty++;
                	System.out.println(conty);
                	if(conty>=2) {
                    	System.out.println("Jogo encerrado! \n"
                    	+"Jogador 2 foi o ganhador");
                    	break;
                    }
                	mesa.removerCarta(cartaJogadaJogador1);
                	mesa.removerCarta(cartaJogadaJogador2);
                    System.out.println("Jogador 2 venceu a rodada "+i+"!");
                } else {
                	contz++;
                	if(contx > 0) {
              
                		System.out.println("Jogo encerrado! \n"
                            	+"Jogador 1 foi o ganhador");
                		break;
                	}
                	else if(conty > 0) {
                		System.out.println("Jogo encerrado! \n"
                            	+"Jogador 2 foi o ganhador");
                		break;
                	}
                	else {
                	contx=2;
                	conty=2;
                	mesa.removerCarta(cartaJogadaJogador1);
                	mesa.removerCarta(cartaJogadaJogador2);
                    System.out.println("Empate na rodada!");
            }
			
            	}
            }
            
            
            
            
           
            
            
            
        }

  
    }
}
