/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.TDAs;

import java.util.Iterator;

/**
 *
 * @author Jonanyu 11.1
 * @param <E>
 */
public class CircleLinkedList<E> implements List<E> {

    private Node<E> first;

    public CircleLinkedList(Node<E> first) {
        this.first = null;
    }

    @Override
    public boolean addFirst(E element) {
        Node<E> newNode = new Node<E>(element);
        if (element == null) {
            return false;
        }

        if (isEmpty()) {

            this.first = newNode;
        } else {
            newNode.setNext(this.first);
            newNode.setPrevious(this.first.getPrevious());
            this.first.getPrevious().setNext(newNode);
            this.first.setPrevious(newNode);
            this.first = newNode;

        }

        return false;
    }

    @Override
    public boolean contains(E element) {
        if (element == null || first == null) {
            return false;
        }
        Node<E> current = first;
        do {
            if (current.getElement().equals(element)) {
                return true;
            }
            current = current.getNext();
        } while (current != first);

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addLast(E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    public E get(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int indexOf(E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isEmpty() {
        return this.first == null;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || isEmpty()) {
            throw new IndexOutOfBoundsException("NO se puede");
        }

        if (index == 0) {
            return removeFirst();
        }

        int count = 0;
        Node<E> current = first;

        while (count < index) {
            current = current.getNext();
            count++;

            if (current == first) {
                throw new IndexOutOfBoundsException("No se puede");
            }
        }

        E removedElement = current.getElement();

        current.getPrevious().setNext(current.getNext());
        current.getNext().setPrevious(current.getPrevious());

        if (current == first) {
            first = current.getNext();
        }

        return removedElement;
    }

    @Override
    public boolean remove(E element) {
        if (element == null || isEmpty()) {
            return false;
        }

        Node<E> current = first;

        do {
            if (current.getElement().equals(element)) {
                current.getPrevious().setNext(current.getNext());
                current.getNext().setPrevious(current.getPrevious());

                if (current == first) {
                    first = current.getNext();
                }
                return true; 
            }

            current = current.getNext();
        } while (current != first);
        return false; 
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addAll(List<E> l) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E removeFirst() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E removeLast() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
