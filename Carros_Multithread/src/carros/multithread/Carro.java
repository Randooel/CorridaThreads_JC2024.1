package carros.multithread;

public class Carro implements Runnable {
	
	//Toda a logica dos carros e da corrida ta aqui equanto a Main roda as threads
	
	private String nome; // nome do carro 
	private double velocidade; // velocidade do carro
	private double aceleracao; // aceleracao (vai ser aleatoria)
	private double tempo; 
	private double deslocamento; // saber o quanto se moveu no total
	private double deslocamentoAtual; // saber o quanto se moveu em um tempo
	private double linhaChegada = 500; // distância da linha de chegada
	
	public Carro (String nome) {
		this.nome = nome;
	}
	
	@Override
	public void run () {
		try {
			// aqui ele corre
			while (deslocamento < linhaChegada) {
				aceleracao = Math.random() * 10; // randomiza a aceleracao
				tempo = 1.0;
				
				velocidade += tempo * aceleracao;
				deslocamentoAtual = velocidade * tempo;
				deslocamento += deslocamentoAtual;
				
				System.out.printf("%s andou %.2fm e já percorreu %.2fm%n", nome, deslocamentoAtual, deslocamento);
				
				Thread.sleep(1000);
			}
			
			System.out.printf("%s alcançou a linha de chegada%n", nome); // Quando o carro termina de correr
		}
			
		catch(InterruptedException e){
			System.out.println(nome + " foi interrompido.");
		}
	}
}
