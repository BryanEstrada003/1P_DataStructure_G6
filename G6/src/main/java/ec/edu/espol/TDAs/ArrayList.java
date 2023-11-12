/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.TDAs;

import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author Jonanyu 11.1
 */
public class ArrayList<E> implements List<E> {

    private E[] elements;
    private int MAX_SIZE = 100;
    private int effectiveSize;

    public ArrayList() {
        elements = (E[]) new Object[MAX_SIZE];
        effectiveSize = 0;
    }

    @Override
    public int size() {
        return effectiveSize;
    }

    @Override
    public boolean isEmpty() {
        return this.effectiveSize == this.MAX_SIZE;
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addFirst(E element) {
        if (element == null) {
            return false;
        }

        if (isFull()) {
            addCapacity();
        }

        for (int i = this.effectiveSize - 1; i >= 0; i--) {
            elements[i + 1] = elements[i];
        }

        this.elements[0] = element;
        this.effectiveSize++;
        return true;
    }

    @Override
    public boolean addLast(E element) {
        if (element == null) {
            return false;
        }
        if (isFull()) {
            addCapacity();
        }
        elements[effectiveSize++] = element;
        return true;
    }

    @Override
    public ArrayList<E> removeFirstNElements(int n) {
        if (n <= 0 || n >= effectiveSize) {
            return this;
        }

        for (int i = 0; i < effectiveSize - n; i++) {
            elements[i] = elements[i + n];
        }
        effectiveSize -= n;

        return this;
    }

    @Override
    public E removeLast() {
        E[] elementos = (E[]) new Object[MAX_SIZE];
        for (int i = 0; i < this.effectiveSize - 1; i++) {
            elementos[i] = this.elements[i];
        }
        this.effectiveSize--;
        E elem = this.elements[this.effectiveSize - 1];
        this.elements = elementos;
        return elem;
    }

    @Override
    public boolean add(int index, E element) {
        if (index < 0 || index > effectiveSize) {

            return false;
        }
        if (isFull()) {
            addCapacity();
        }
        for (int i = effectiveSize; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        effectiveSize++;
        return true;
    }

    @Override
    public E remove(int index) {
        E element = null;
        E[] elementos = (E[]) new Object[MAX_SIZE];
        for (int i = 0; i <= this.effectiveSize - 1; i++) {
            if (index != i) {
                elementos[i] = this.elements[i];
            } else {
                element = this.elements[index];
            }
        }
        this.effectiveSize--;
        this.elements = elementos;
        return element;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= effectiveSize) {
            return null;
        }
        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void addCapacity() {
        MAX_SIZE = MAX_SIZE * 2;
        E[] newElements = (E[]) new Object[MAX_SIZE];
        for (int i = 0; i < effectiveSize; i++) {
            newElements[i] = elements[i];
        }
        this.elements = newElements;
    }

    private boolean isFull() {
        return effectiveSize == MAX_SIZE;
    }

    @Override
    public String toString() {
        StringBuilder cadena = new StringBuilder();
        cadena.append("[");
        for (int i = 0; i <= this.effectiveSize - 1; i++) {
            if (i == this.effectiveSize - 1) {
                cadena.append(this.elements[i]);
            } else {
                cadena.append(this.elements[i]);
                cadena.append(",");
            }
        }
        cadena.append("]");
        return cadena.toString();
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < effectiveSize;
            }

            @Override
            public E next() {
                E element = elements[cursor];
                cursor++;
                return element;
            }
        };
        return it;
    }

    @Override
    public E getFirst() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E getLast() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean insert(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean contains(E e, Comparator<E> comp) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int indexOf(E element, Comparator<E> cmp) {
        for (int i = 0; i < effectiveSize; i++) {
            if (element == null ? elements[i] == null : cmp.compare(element, elements[i]) == 0) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean remove(E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addAll(List<E> l)  {
        if(l == null) {
            return false;
        }
        int total_a_tener = effectiveSize + l.size();
        int añadir_capacity = total_a_tener/100 ;
        if (total_a_tener > MAX_SIZE){
            for (int i = 0 ; i < (añadir_capacity + 1) ; i++ ){
                addCapacity();   
            }
        }
        for (int u = 0; u < l.size() ; u++){
            elements[effectiveSize] = l.get(u) ;
            effectiveSize ++;
        }
        return true;
    }

    @Override
    public E removeFirst() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int indexOf(E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
