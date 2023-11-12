/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.TDAs;

/**
 *
 * @author Jonanyu 11.1
 * @param <E>
 */
public class DoublyNode<E> {
    private E element;
    private DoublyNode<E> next;
    private DoublyNode<E> previous;
    
    public DoublyNode(){
        next = null;
        previous = null;
    }
    
    public DoublyNode(E content){
        this.element=content;
        next = null;
        previous = null;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public DoublyNode<E> getNext() {
        return next;
    }

    public void setNext(DoublyNode<E> next) {
        this.next = next;
    }

    public DoublyNode<E> getPrevious() {
        return previous;
    }

    public void setPrevious(DoublyNode<E> previous) {
        this.previous = previous;
    }

    
    
    
}


