/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ec.edu.espol.TDAs;

import java.util.Comparator;

/**
 *
 * @author Jonanyu 11.1
 * @param <E>
 */
public interface List<E> extends Iterable<E> {

    public E removeFirst();

    E removeLast();

    public int indexOf(E element, Comparator<E> cmp);

    void clear();

    boolean add(E element);

    boolean add(int index, E element);

    boolean contains(E element, Comparator<E> comp);

    E get(int index);

    boolean isEmpty();

    E remove(int index);

    int size();

    boolean addAll(List<E> l);
    
    boolean remove(E element,Comparator<E> cmp);
}
