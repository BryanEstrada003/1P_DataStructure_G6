/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ec.edu.espol.TDAs;

/**
 *
 * @author Jonanyu 11.1
 * @param <E>
 */
public interface List<E> extends Iterable<E> {

    boolean addFirst(E element);

    boolean addLast(E element);

    public E removeFirst();

    E removeLast();

    E getFirst();

    E getLast();

    void clear();

    boolean add(int index, E element);

    boolean insert(int index, E element);

    boolean contains(E element);

    E get(int index);

    int indexOf(E element);

    boolean isEmpty();

    E remove(int index);

    boolean remove(E element);

    E set(int index, E element);

    int size();

    public boolean addAll(List<E> l);
}
