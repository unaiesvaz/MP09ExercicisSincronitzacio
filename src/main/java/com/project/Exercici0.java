package com.project;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercici0 {
    public static void main(String[] args) {
        double[] datos = {10, 20, 30, 40, 50}; //Creamos una lista de doubles con unos datos
        double[] resultados = new double[3]; // Creamos una lista de 3 posiciones vacias

        CyclicBarrier barrier = new CyclicBarrier(3, () -> { // El 3 significa que necesita recibir 3 hilos, 3 tareas
            System.out.println("Todas las tareas han terminado, a continuacion, los resultados"); // Lo de dentro de Barrier, es lo que ejecutara una vez terminen las 3 tareas 
            System.out.println("(Tarea1)Media: " + resultados[0]);
            System.out.println("(Tarea2)Suma: " + resultados[1]);
            System.out.println("(Tarea3)Desviacion estandar: " + resultados[2]);
        });

        ExecutorService executor = Executors.newFixedThreadPool(3); // Creamos un ejecutor que tendra 3 tareas (hilos)

        //Lo que hacen las tareas es guardar el valor final de cada una en la lista de resultados 

        Runnable tareaMedia = () -> {
            double suma = 0;

            for (double dato : datos) { 
                suma += dato; 
            }

            resultados[0] = suma / datos.length; 

            try {
                barrier.await(); //Lo que hace esta linea es hacer que el resto de tareas lleguen a la misma barrera 
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            
        };
        executor.submit(tareaMedia);  // Envia la tareaMedia al ejecutor para que ocupe uno de los hilos 

        Runnable tareaSuma = () -> {
            double suma = 0;

            for (double dato : datos) {
                suma += dato;
            }

            resultados[1] = suma; // La suma de todos los datos 

            try {
                barrier.await(); //Una vez la tarea termina, espera a que el resto termine 
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };
        executor.submit(tareaSuma); // Envia la tarea a uno de los hilos 

        Runnable tareaDesviacion= () -> {
            double suma = 0;

            for (double dato : datos) {
                suma += dato;
            }

            double media = suma / datos.length; 

            double sumaDiferencias = 0;

            for (double dato : datos) {
                sumaDiferencias += Math.pow(dato - media, 2); // pow es potencia 
            }

            resultados[2] = Math.sqrt(sumaDiferencias / datos.length); // La raiz cuadrada

            try {
                barrier.await(); // Espera a que todas las tareas terminen
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };
        executor.submit(tareaDesviacion);

        executor.shutdown(); //Cerramos el ejecutor


        




    }
}
