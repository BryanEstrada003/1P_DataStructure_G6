/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.TDAs;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author Jonanyu 11.1
 */
public class ArrayList<E> implements Serializable, List<E> {

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
        for (int i = 0; i <= this.effectiveSize - 1; i++) {
            this.elements[i] = null;
        }
        this.effectiveSize = 0;
    }

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
    public int indexOf(E element, Comparator<E> cmp) {
        for (int i = 0; i < effectiveSize; i++) {
            if (element == null ? elements[i] == null : cmp.compare(element, elements[i]) == 0) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean addAll(List<E> l) {
        if (l == null) {
            return false;
        }
        int total_a_tener = effectiveSize + l.size();
        int añadir_capacity = total_a_tener / 100;
        if (total_a_tener > MAX_SIZE) {
            for (int i = 0; i < (añadir_capacity + 1); i++) {
                addCapacity();
            }
        }
        for (int u = 0; u < l.size(); u++) {
            elements[effectiveSize] = l.get(u);
            effectiveSize++;
        }
        return true;
    }

    @Override
    public E removeFirst() {
        E elementRemove = this.elements[0];
        this.remove(0);
        return elementRemove;
    }

    @Override
    public boolean add(E element) {
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
    public boolean remove(E element, Comparator<E> cmp) {
        for (int i = 0; i <= this.effectiveSize - 1; i++) {
            if (cmp.compare(this.elements[i], element) == 0) {
                this.remove(i);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(E e, Comparator<E> comp) {
        if (e == null) {
            return false;
        }
        for (E element : this.elements) {
            if (comp.compare(element, e) == 0) {
                return true;
            }
        }
        return false;
    }

    public List<E> subList(int from, int to) {

        ArrayList<E> array = new ArrayList();
        for (int i = 0; i <= this.effectiveSize - 1; i++) {
            if (i >= from && i <= to && to <= this.effectiveSize - 1) {
                array.add(get(i));
            }
        }
        return array;
    }


    public List<E> removeFirstNElements(int n) {
        ArrayList<E> array = new ArrayList();
        for (int i = 0; i <= this.effectiveSize - 1; i++) {
            if (i >= n) {
                array.add(get(i));
            }
        }
        return array;
    }
    
    public void rotate(int k) {
        List<E> array = this.subList(0, (this.effectiveSize - k) - 1);
        List<E> array2 = this.subList((this.effectiveSize - k), this.effectiveSize - 1);
        for (int i = array2.size() - 1; i >= 0; i--) {
            array.add(array2.get(i));
        }
        for (int i = 0; i < array.size(); i++) {
            this.elements[i] = array.get(i);
        }
    }
}
