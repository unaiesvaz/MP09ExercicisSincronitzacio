package com.project;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Exercici1 {
    public static void main(String[] args) {
        ParkingLot parking = new ParkingLot(2); //Al hacer esto, indicamos al constructor de ParkingLot que la capacidad es 2
        // NOTA: este valor, se puede pasar de 2 en este caso haciendo .release, eso lo controlamos nosotros 

        ExecutorService executor = Executors.newFixedThreadPool(4); // Creamos un ejecutor con 4 hilos, para que 2 puedan entrar y 2 salir a la vez, ya que tenemos 2 plazas

        Runnable coche1 = () -> { // Entra y sale coche 1 
            parking.entrar("Coche 1"); 
            parking.salir("Coche 1");
        };
        executor.submit(coche1);

        Runnable coche2 = () -> { // En esta tarea, coche2 entra y sale 
            parking.entrar("Coche 2");
            parking.salir("Coche 2");
        };
        executor.submit(coche2);

        Runnable coche3 = () -> { // Entra y sale coche 3
            parking.entrar("Coche 3"); 
            parking.salir("Coche 3");
        };
        executor.submit(coche3);

        Runnable coche4 = () -> {
            parking.entrar("Coche 4");
            parking.salir("Coche 4");
        };
        executor.submit(coche4);

        //En el output, se puede ver como se han ejecutado a la vez y no va en orden, esperan a que haya espacio para entrar
        // Al ser un codigo corto, no se ve reflejado que esperen pero se puede ver como se intercalan, osea que, esperan
        executor.shutdown();



    }
}
