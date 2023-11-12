
package com.mycompany.tda;

import java.util.Comparator;

public interface List<E> extends Iterable<E> {
    
    public boolean addLast(E e); // inserta el elemento e al final

    public E removeLast(); // remueve el elemento al final de la lista

    public int size();

    public boolean isEmpty();

    public void clear();
    
    boolean contains(E Element, Comparator<E> comp);  
    
    boolean add(E Element, int index); // inserta element en la posición index

    public E remove(int index); // remueve y retorna el elemento en la posición index

    public E get(int index); // retorna el elemento ubicado en la posición index

    public E set(int index, E element); // setea el element en la posición index
    
    int indexOf(E Element, Comparator<E> comp);  // retorna el indice donde se encuentra el elemento    
    
}
