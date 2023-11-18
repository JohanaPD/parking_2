package com.clasesParking;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class AdminParking {
  static final int MAX_PLAZAS=20;
  private List<Coche> coches= new ArrayList<>();
  private Semaphore semaforo;
  private Coche coche=null;

    public AdminParking() {
        semaforo= new Semaphore(MAX_PLAZAS);
        for (int i = 0; i <30 ; i++) {
            char a='A';
            char b='B';
            char c='C';
            Random random = new Random();
            int num2= random.nextInt(10);
            int num3= random.nextInt(10);
            String matricula=i+" "+i+String.valueOf(num2)+num3+ (a++)+(b++)+(c++);
            Coche nuevo= new Coche(matricula, this.semaforo);
            coche=nuevo;
            coches.add(coche);
        }
        for (Coche coche: coches) {
            Thread nuevo= new Thread(coche);
            nuevo.start();
        }
        for (Coche coche:coches) {
            try {
                coche.join();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
        List<Coche> todosLosCoches=Coche.getCoches();

    }
}
