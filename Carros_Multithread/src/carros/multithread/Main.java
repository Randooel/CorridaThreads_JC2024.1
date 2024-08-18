package carros.multithread;

// Grupo: Vitor, Leonardo, João Carlos, Cauê

public class Main {
	
	public static void main(String args[]) {
		
		Thread[] carros = new Thread[5]; // Threads dos carros
	
		// Inicia as threads dos carro
		for (int i = 0; i < carros.length; i++) {
            carros[i] = new Thread(new Carro("Carro_" + (i + 1))); //aqui ele nomeia os carros com a numeracao
            carros[i].start(); //incia a thread aqui
        }
		
		for (Thread carro : carros) {
	            try {
	                carro.join(); // A função join() espera que as threads terminem antes de encerrar o programa.
	            } 
	            
	            catch (InterruptedException e) {
	                e.printStackTrace(); // Caso dê errado kkkkkkkk
	            }
	    }
		
		System.out.println("Corrida terminada!"); // Printa a mensagem quando acaba a corrida

	}
}
