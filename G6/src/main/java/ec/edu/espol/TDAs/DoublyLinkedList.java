/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.TDAs;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

/**
 *
 * @author Jonanyu 11.1
 * @param <E>
 */
public class DoublyLinkedList<E> implements Serializable, List<E> {

    private DoublyNode<E> head;

    public DoublyNode<E> getHead() {
        return head;
    }

    public void setHead(DoublyNode<E> head) {
        this.head = head;
    }

    public boolean addLast(E element) {
        if (element != null) {
            DoublyNode<E> newNode = new DoublyNode<>(element);
            if (size() == 0) {
                head = newNode;
                head.setNext(head);
                head.setPrevious(head);
            } else {
                DoublyNode<E> last = head.getPrevious();
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
        if (isEmpty()) {
            return null;
        }
        DoublyNode<E> nodeEliminate = head.getPrevious();
        DoublyNode<E> node = nodeEliminate.getPrevious();
        node.setNext(head);
        head.setPrevious(node);

        return nodeEliminate.getElement();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        int counter = 0;
        if (head != null) {
            DoublyNode<E> node = head;
            do {
                node = node.getNext();
                counter++;
            } while (node != head);
        }
        return counter;
    }

    @Override
    public E remove(int index) {
        if (isEmpty()) {
            return null;
        }
        if (index > size() - 1 || index < -1) {
            return null;
        }
        if (index == -1) {
            index = size() - 1;
        }

        DoublyNode<E> node = head;
        int i = 0;
        do {
            node = node.getNext();
            i++;
        } while (node != head && i < index);
        node.getPrevious().setNext(node.getNext());
        node.getNext().setPrevious(node.getPrevious());

        return node.getElement();
    }

    @Override
    public boolean add(int index, E element) {
        if (element == null) {
            return false;
        }
        if (index > size() - 1 || index < -1) {
            return false;
        }
        if (index == -1) {
            index = size() - 1;
        }

        DoublyNode<E> new_node = new DoublyNode<>(element);

        DoublyNode<E> node = head;

        int i = 0;
        do {
            node = node.getNext();
            i++;
        } while (node != head && i < index);

        new_node.setPrevious(node.getPrevious());
        node.getPrevious().setNext(new_node);
        new_node.setNext(node);
        node.setPrevious(new_node);

        return true;
    }

    @Override
    public Iterator<E> iterator() {
        if (isEmpty()) {
            return null;
        }
        return new Iterator<E>() {

            DoublyNode<E> node = head.getNext();

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                if (node == head) {
                    node = null;
                    return head.getElement();
                }
                node = node.getNext();

                return node.getPrevious().getElement();
            }

        };
    }

    @Override
    public E get(int index) {
        if (isEmpty() || index < 0 || index >= size()) {
            return null;
        }

        DoublyNode<E> node = getNodeAtIndex(index);
        return node.getElement();
    }

    public E set(int index, E e) {
        if (isEmpty()) {
            return null;
        }
        if (index > size() - 1 || index < -1) {
            return null;
        }
        if (index == -1) {
            index = size() - 1;
        }

        DoublyNode<E> node = head;
        int i = 0;
        do {
            node = node.getNext();
            i++;
        } while (node != head && i < index);

        E replaced_element = node.getElement();
        node.setElement(e);

        return replaced_element;
    }

    @Override
    public void clear() {
        if (isEmpty()) {
            return;
        }
        head = null;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[Empty List]";
        }
        DoublyNode<E> node = head;
        String s = "[" + node.getElement().toString();
        do {
            s += ", " + node.getElement().toString();
            node = node.getNext();
        } while (node != head);
        return s + "]";
    }

    @Override
    public int indexOf(E e, Comparator<E> comp) {
        if (e == null) {
            return -2;
        }
        if (isEmpty()) {
            return -1;
        }

        DoublyNode<E> node = head;
        int index = 0;
        do {
            if (comp.compare(node.getElement(), e) == 0) {
                return index;
            }
            index++;
            node = node.getNext();
        } while (node != head);
        return -2;
    }

    @Override
    public boolean contains(E element, Comparator<E> comp) {
        if (isEmpty()) {
            return false;
        }
        DoublyNode<E> node = head;
        do {
            if (comp.compare(node.getElement(), element) == 0) {
                return true;
            }
            node = node.getNext();
        } while (node != head);

        return false;
    }

    @Override
    public boolean addAll(List<E> otherList) {
        if (otherList == null || otherList.isEmpty()) {
            return false;
        }
        for (E element : otherList) {
            addLast(element);
        }
        return true;
    }

    @Override
    public boolean add(E element) {
        if (element == null) {
            return false;
        }

        DoublyNode<E> new_node = new DoublyNode<>(element);

        if (isEmpty()) {
            head = new_node;
            head.setNext(head);
            head.setPrevious(head);
        } else {
            DoublyNode<E> last = head.getPrevious();
            last.setNext(new_node);
            new_node.setPrevious(last);
            new_node.setNext(head);
            head.setPrevious(new_node);
        }
        return true;
    }

    @Override
    public boolean remove(E element, Comparator<E> cmp) {
        Iterator<E> iterator = iterator();
        int pos = 0;
        while (iterator.hasNext()) {
            E currentElement = iterator.next();
            if (cmp.compare(currentElement, element) == 0) {
                this.remove(pos);
                return true;
            }
            pos++;
        }
        return false;
    }

    public ListIterator<E> listIterator() {
        if (isEmpty()) {
            return null;
        }
        return new ListIterator<E>() {
            DoublyNode<E> cursor = head;

            @Override
            public boolean hasNext() {
                return cursor != null;
            }

            @Override
            public E next() {
                E tmp = cursor.getElement();
                cursor = cursor.getNext();
                return tmp;
            }

            @Override
            public boolean hasPrevious() {
                return cursor != null;
            }

            @Override
            public E previous() {
                E tmp = cursor.getElement();
                cursor = cursor.getPrevious();
                return tmp;
            }

            @Override
            public int nextIndex() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void set(E e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void add(E e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

        };
    }

    @Override
    public E removeFirst() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
// Método para obtener el nodo en un índice específico

    private DoublyNode<E> getNodeAtIndex(int index) {
        DoublyNode<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node;
    }

    public Iterator<E> iteratorForwardFrom(int start, int limit) {
        if (isEmpty() || start < 0 || start >= size()) {
            return null;
        }
        return new Iterator<E>() {
            DoublyNode<E> currentNode = getNodeAtIndex(start);
            int count = 0;

            @Override
            public boolean hasNext() {
                return count < limit;
            }

            @Override
            public E next() {
                E element = currentNode.getElement();
                currentNode = currentNode.getNext(); // Avanzar hacia adelante
                count++;
                return element;
            }
        };
    }

    // Método para iterar hacia atrás desde un punto específico
    public Iterator<E> iteratorBackwardFrom(int start, int limit) {
        if (isEmpty() || start < 0 || start >= size() || limit <= 0) {
            return null;
        }

        int index = start;
        while (index != (start + limit) % size()) {
            index = (index + 1) % size();
        }

        final int finalIndex = index;

        return new Iterator<E>() {
            int currentIndex = finalIndex;
            int count = 0;

            @Override
            public boolean hasNext() {
                return count < limit;
            }

            @Override
            public E next() {
                E element = getNodeAtIndex(currentIndex).getElement();
                currentIndex = (currentIndex + 1) % size();
                count++;
                return element;
            }
        };
    }

}
