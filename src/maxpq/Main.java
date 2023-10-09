/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxpq;

import java.util.NoSuchElementException;
import maxpq.MaxPQ;

public class Main {
    public static void main(String[] args) {
        MaxPQ<Integer> maxPQ = new MaxPQ<>();

        maxPQ.insert(10);
        maxPQ.insert(5);
        maxPQ.insert(12);
        maxPQ.insert(3);
        maxPQ.insert(8);

        System.out.println("Cola de Prioridad: " + maxPQ);

        try {
            while (!maxPQ.isEmpty()) {
                System.out.println("Máximo elemento: " + maxPQ.max());
                System.out.println("Elemento eliminado: " + maxPQ.delMax());
                System.out.println("Cola de Prioridad actual: " + maxPQ);
            }

            System.out.println("¿La cola de prioridad está vacía? " + maxPQ.isEmpty());
        } catch (NoSuchElementException e) {
            System.out.println("Excepción: " + e.getMessage());
        }
    }
}