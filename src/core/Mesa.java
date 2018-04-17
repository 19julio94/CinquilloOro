package core;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
* Representa la mesa de juego, donde los jugadores colocan las cartas en cada turno.
* Estructura: Se utilizará un array estático de dobles colas (Deque), una para cada palo
* Funcionalidad: insertar la carta en su lugar correcto automáticamente, visualizar, etc
*/

public class Mesa {
    
    private static ArrayList<ArrayDeque> palos;
    private static ArrayDeque<Integer> oros, copas, bastos, espadas;
    
    public Mesa() {
        palos = new ArrayList<>();
        
        oros = new ArrayDeque<>();
        copas = new ArrayDeque<>();
        bastos = new ArrayDeque<>();
        espadas = new ArrayDeque<>();
        
        palos.add(oros);
        palos.add(copas);
        palos.add(bastos);
        palos.add(espadas);
    }
    
    public void insertarCarta(Carta carta) {
        int palo = -1;
        
        if(carta.getPalo().equalsIgnoreCase("oros")) palo = 0;
        else if(carta.getPalo().equalsIgnoreCase("copas")) palo = 1;
        else if(carta.getPalo().equalsIgnoreCase("bastos")) palo = 2;
        else if(carta.getPalo().equalsIgnoreCase("espadas")) palo = 3;
        
        if(carta.getNumero() > 5)
            palos.get(palo).addLast(carta.getNumero());
        else{
            palos.get(palo).addFirst(carta.getNumero());
        }
    }
    
    public boolean isEmpty() {
        return oros.isEmpty() && copas.isEmpty() && bastos.isEmpty() && espadas.isEmpty();
    }
    
    public ArrayList<Carta> posiblesJugadas() {
        ArrayList<Carta> posibles = new ArrayList<>();
        
        if(isEmpty())
            posibles.add(new Carta("oros", 5));
        else{
            for(short i = 0; i < 4; i++) {
                String palo = null;
                switch(i) {
                    case 0: palo = "oros"; break;
                    case 1: palo = "copas"; break;
                    case 2: palo = "bastos"; break;
                    case 3: palo = "espadas"; break;
                }
                
                //Si el palo esta vacio,añade el 5 de ese palo si es posible
                if(palos.get(i).isEmpty())
                    posibles.add(new Carta(palo, 5));
                else {
                    if(!palos.get(i).getFirst().equals(1)) {
                        int num = ((int) palos.get(i).getFirst()- 1) ;
                        posibles.add(new Carta(palo, num));
                    }
                    if(!palos.get(i).getLast().equals(12)) {
                        int num = ((int) palos.get(i).getLast()+ 1) ;
                        posibles.add(new Carta(palo, num));
                    }
                }
            }
        }
                    
        return posibles;
    }
    public void mostrar() {
       
        System.out.println("  _____   _____   _____   _____ ");
        System.out.println(" |     | |     | |     | |     |");
        System.out.println(" |  O  | |  C  | |  B  | |  E  |");
        System.out.println(" |_____| |_____| |_____| |_____|");
        System.out.println("");
        System.out.println("  _____   _____   _____   _____ ");
        System.out.println(" |     | |     | |     | |     |");
       
        for(int i = 0; i < 13; i++) {
            System.out.print(" |  ");
           
            for(short j = 0; j < 4; j++) {
                if(palos.get(j).contains(i)) {
                if(i < 10)
                    System.out.print(i);
                else
                    System.out.print("\b" + i);
            }
            else
                System.out.print(" ");
           
            if(j == 3)
                System.out.println("  |");
            else
                System.out.print("  | |  ");
            }
           
        }
 
        System.out.println(" |     | |     | |     | |     |");
        System.out.println(" |_____| |_____| |_____| |_____|");
        System.out.println("");
    }
}
