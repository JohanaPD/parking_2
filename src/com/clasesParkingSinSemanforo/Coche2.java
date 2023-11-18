package com.clasesParking;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Coche extends Thread {
    private String matricula;
    private boolean aparcado;
    private Semaphore semaforo;
    private static int MAX_VUELTAS_COCHE = 15;
    private static ArrayList<Coche> coches = new ArrayList<>();

    public Coche(String matricula, Semaphore semaforo) {
        this.matricula = matricula;
        this.semaforo = semaforo;
        coches.add(this);
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public boolean isAparcado() {
        return aparcado;
    }

    public void setAparcado(boolean aparcado) {
        this.aparcado = aparcado;
    }

    public static void setCoches(ArrayList<Coche> coches) {
        Coche.coches = coches;
    }

    public static ArrayList<Coche> getCoches() {
        return coches;
    }



    @Override
    public String toString() {
        return "Coche{" +
                "matricula='" + matricula + '\'' +
                ", aparcado=" + aparcado +
                '}';
    }

    public void run() {
        boolean plazaEncontrada;
        boolean salirDelParking;
        int vuelta = 0;
        while (vuelta < MAX_VUELTAS_COCHE) {
            try {
                if (!this.aparcado) {
                    System.out.println("El coche " + matricula + " Está buscando plaza de las " + this.semaforo.availablePermits() + " Disponibles ");
                    plazaEncontrada = ThreadLocalRandom.current().nextDouble(1) >= 0.5;
                    if (plazaEncontrada) {
                        if (this.semaforo.availablePermits() != 0) {
                            this.semaforo.acquire();
                            this.aparcado = true;
                            System.out.println("El coche " + matricula + " encontro plaza de las plaza de las " + this.semaforo.availablePermits() + " Disponibles ");
                        }
                    } else {
                        System.out.println("El coche " + matricula + " no ha encontrado plaza de" + this.semaforo.availablePermits() + " plaza de las disponibles ");
                    }
                } else {
                    salirDelParking = ThreadLocalRandom.current().nextDouble(1) >= 0.25;
                    if (salirDelParking) {
                        this.semaforo.release();
                        this.aparcado = false;
                        System.out.println("El coche " + matricula + " está saliendo del parking, tenemos " + this.semaforo.availablePermits() + " plaza de las disponibles ");
                    }
                }
            } catch (InterruptedException e) {

            } finally {
                vuelta++;
            }
        }
    }
}
