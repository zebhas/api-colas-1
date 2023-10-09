/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxpq;

import java.util.NoSuchElementException;

/**
 *
 * @author Guest
 */
public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq; // Arreglo para almacenar el árbol binario completo
    private int n;    // Número de elementos en la cola de prioridad

    public MaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
        n = 0;
    }

    public MaxPQ() {
        this(1);
    }

    public MaxPQ(Key[] a) {
        n = a.length;
        pq = (Key[]) new Comparable[n + 1];
        for (int i = 0; i < n; i++) {
            pq[i + 1] = a[i];
        }
        for (int k = n / 2; k >= 1; k--) {
            sink(k);
        }
    }

    public void insert(Key v) {
        if (n == pq.length - 1) {
            resize(2 * pq.length);
        }
        pq[++n] = v;
        swim(n);
    }
   
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("La cola de prioridad está vacía.");
        }
        return pq[1];
    }

    public Key delMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("La cola de prioridad está vacía.");
        }
        Key max = pq[1];
        exch(1, n--);
        pq[n + 1] = null; // Evitar referencias fantasma
        sink(1);
        if (n > 0 && n == (pq.length - 1) / 4) {
            resize(pq.length / 2);
        }
        return max;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void swim(int k) {
    while (k > 1 && less(k / 2, k)) {
        exch(k, k / 2);
        k = k / 2;
    }
}

private void sink(int k) {
    while (2 * k <= n) {
        int j = 2 * k;
        if (j < n && less(j, j + 1)) {
            j++;
        }
        if (!less(k, j)) {
            break;
        }
        exch(k, j);
        k = j;
    }
}

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) > 0;
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private void resize(int capacity) {
        Key[] temp = (Key[]) new Comparable[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 1; i <= n; i++) {
            sb.append(pq[i]);
            if (i < n) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
