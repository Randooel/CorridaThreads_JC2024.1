package carros.multithread;

import java.util.List; // Interface do Java para ordenar a chegada dos carros
import java.util.concurrent.Semaphore; // Garante acesso sincronizado à lista de chegada

public class Carro implements Runnable {

    private String nome; // Nome do carro 
    private double velocidade; // Velocidade do carro
    private double aceleracao; // Aceleração (vai ser aleatória)
    private double tempo; 
    private double deslocamento; // Saber o quanto se moveu no total
    private double deslocamentoAtual; // Saber o quanto se moveu em um tempo
    private double linhaChegada = 500; // Distância da linha de chegada

    private List<String> ordemChegada; // Lista de chegada dos carros
    private Semaphore sem; // Semáforo para permitir apenas uma thread por vez

    // Construtor que aceita nome, lista de chegada e semáforo
    public Carro(String nome, List<String> ordemChegada, Semaphore sem) {
        this.nome = nome;
        this.ordemChegada = ordemChegada;
        this.sem = sem;
    }

    @Override
    public void run() {
        try {
            // Aqui o carro corre
            while (deslocamento < linhaChegada) {
                aceleracao = Math.random() * 10; // Randomiza a aceleração
                tempo = 1.0;

                velocidade += tempo * aceleracao;
                deslocamentoAtual = velocidade * tempo;
                deslocamento += deslocamentoAtual;

                System.out.printf("%s andou %.2fm e já percorreu %.2fm%n", nome, deslocamentoAtual, deslocamento);

                Thread.sleep(1000);
            }

            System.out.printf("%s alcançou a linha de chegada%n", nome); // Quando o carro termina de correr

            // Região crítica: adicionar à lista de chegada
            try {
                sem.acquire(); // Adquire o semáforo
                ordemChegada.add(nome); // Adiciona o nome do carro na lista
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                sem.release(); // Libera o semáforo
            }
        } catch (InterruptedException e) {
            System.out.println(nome + " foi interrompido.");
        }
    }
}
