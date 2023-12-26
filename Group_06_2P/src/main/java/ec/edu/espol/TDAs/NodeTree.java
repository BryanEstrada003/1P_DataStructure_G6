/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.TDAs;

import java.util.LinkedList;

/**
 *
 * @author angelozurita
 */
public class NodeTree<E> {
    private E content;
    private LinkedList<E> childrens;
    private int utilidad_asociada;

    public NodeTree(E content) {
        this.content = content;
        this.childrens = new LinkedList<>();
        this.utilidad_asociada = 0;
    }

    // crear constructores;
    public E getContent() {
        return content;
    }

}
