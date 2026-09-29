package com.project;

import java.util.concurrent.Semaphore;

public class ParkingLot {
    private final Semaphore semaphore; //Creamos el semaforo, que es como un contador de plazas

    public ParkingLot(int capacidad) { //Constructor
        semaphore = new Semaphore(capacidad); // El constructor se encarga de decirle al semaforo las plazas que hay
    }

    public void entrar(String coche) { //Metodo para "Entrar al parking", restar 1 permiso al semaforo
        try {
            System.out.println(coche + " Intentando entrar...");

            semaphore.acquire(); //Lo que hace es restar 1 al semaforo, si no tiene "plazas" disponibles, espera a que las haya y se para la tarea en este punto

            System.out.println(coche + " Ya ha entrado");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void salir(String coche) {
        semaphore.release(); // Release lo que hace es sumar 1 plaza disponible, suma 1 permiso al semaforo
        System.out.println(coche + " Ha salido del parking"); 
    }
}
