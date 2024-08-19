package carros.multithread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {

        List<String> ordemChegada = new ArrayList<>(); // Lista para armazenar a ordem de chegada dos carros
        Semaphore semaforo = new Semaphore(1); // Semáforo para sincronizar o acesso à lista

        Thread[] carros = new Thread[15]; // Threads dos carros

        // Inicia as threads dos carros
        for (int i = 0; i < carros.length; i++) {
            // Passa a lista de ordem de chegada e o semáforo para cada carro
            carros[i] = new Thread(new Carro("Carro_" + (i + 1), ordemChegada, semaforo)); 
            carros[i].start(); // Inicia a thread aqui
        }

        for (Thread carro : carros) {
            try {
                carro.join(); // Espera todas as threads terminarem antes de encerrar o programa
            } catch (InterruptedException e) {
                e.printStackTrace(); // Caso dê errado
            }
        }

        // Exibe a ordem de chegada em ordem correta
        System.out.println("Corrida terminada! Ordem de chegada:");
        for (String nome : ordemChegada) {
            System.out.println(nome);
        }
    }
}
