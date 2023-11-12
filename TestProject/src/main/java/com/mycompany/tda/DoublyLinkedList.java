
package com.mycompany.tda;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements List<E>{

    private DoublyNodeList<E> head;
   
    DoublyNodeList<E> getLast(){
        return head;
    }
    

    @Override
    public boolean addLast(E element) {
        if(element != null){
            DoublyNodeList<E> newNode = new DoublyNodeList<>(element);
            if(size() == 0) {
                head = newNode;
                head.setNext(head);
                head.setPrevious(head);
            }
            else{
                DoublyNodeList<E> last = head.getPrevious();
                last.setNext(newNode);
                newNode.setPrevious(last);
                newNode.setNext(head);
                head.setPrevious(newNode);
            }
            return true;
        }
        return false;
    }

    

    @Override
    public E removeLast() {
        if(isEmpty()) return null;
        DoublyNodeList<E> nodeEliminate = head.getPrevious();
        DoublyNodeList<E> node = nodeEliminate.getPrevious();
        node.setNext(head);
        head.setPrevious(node);
        
        
        return nodeEliminate.getContent();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    
    @Override
public int size() {
    if (isEmpty()) return 0;
    int counter = 0;
    DoublyNode<E> node = head;
    do {
        node = node.getNext();
        counter++;
    } while (node != head);
    return counter;
}

    @Override
    public E remove(int index) {
        if(isEmpty()) return null;
        if(index > size()-1 || index < -1) return null;
        if(index == -1) index = size()-1;
        
        DoublyNodeList<E> node = head;
        int i = 0;
        do{
            node = node.getNext();
            i++;        
        }while(node !=head && i<index);       
        node.getPrevious().setNext(node.getNext());
        node.getNext().setPrevious(node.getPrevious());
        
        return node.getContent();
    }

    @Override
    public boolean add(E element, int index) {
        if(element == null) return false;
        if(index > size()-1 || index < -1) return false;
        if(index == -1) index = size()-1;
                
        DoublyNodeList<E> new_node = new DoublyNodeList<E>(element);
        
        DoublyNodeList<E> node = head;
        
        int i = 0;
        do{
            node = node.getNext();
            i++;        
        }while(node !=head && i<index); 
        
        new_node.setPrevious(node.getPrevious());
        node.getPrevious().setNext(new_node);
        new_node.setNext(node);
        node.setPrevious(new_node);
        
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        if(isEmpty()) return null;
        return new Iterator<E>(){
            
            DoublyNodeList<E> node = head.getNext();
            
            @Override
            public boolean hasNext(){
                return node != null;
            }
            
            @Override
            public E next(){
                if(node == head){
                    node = null;
                    return head.getContent();
                }
                node = node.getNext();
                
                return node.getPrevious().getContent();
            }
        
        };
    }

    @Override
    public E get(int index) {
        if(isEmpty()) return null;
        if(index > size()-1 || index < -1) return null;
        if(index == -1) index = size()-1;
        
        DoublyNodeList<E> node = head;
        int i = 0;
        do{
            node = node.getNext();
            i++;        
        }while(node !=head && i<index); 
        
        return node.getContent();
    }
    
    @Override
    public E set(int index, E e){
        if(isEmpty()) return null;
        if(index > size()-1 || index < -1) return null;
        if(index == -1) index = size()-1;
        
        DoublyNodeList<E> node = head;
        int i = 0;
        do{
            node = node.getNext();
            i++;        
        }while(node !=head && i<index); 
        
        E replaced_element = node.getContent();
        node.setContent(e);
        
        return replaced_element;
    }
    
    @Override
    public void clear(){
        if(isEmpty()) return;
        
        head = null;
    }
    
    @Override
    public String toString(){
        if(head == null) return "[Empty List]";
        DoublyNodeList<E> node = head;
        String s = "["+node.getContent().toString();
        do{
            s += ", "+node.getContent().toString();
            node = node.getNext();        
        } while (node != head);
        return s+"]";
    }

    @Override
    public int indexOf(E e, Comparator<E> comp) {
        if(e == null) return -2;
        if(isEmpty()) return -1;
        
        DoublyNodeList<E> node = head;
        int index = 0;
        do{
            if(comp.compare(node.getContent(), e) == 0) return index;
            index++;
            node = node.getNext();      
        } while (node != head);
        return -2;
    }
    
    @Override
    public boolean contains(E e, Comparator<E> comp){
        if(isEmpty()) return false;
        DoublyNodeList<E> node = head;
        do{
            if(comp.compare(node.getContent(), e)==0) return true;
            node = node.getNext();      
        } while (node != head);
        
        return false;
    }
    
    
        
}

