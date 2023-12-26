/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.TDAs;

import java.util.LinkedList;
import java.util.List;

public class TreeNode<E> {
    private E  matriz[][];
    private List<Tree<E>> children;

    public TreeNode(E[][] matriz) {
        this.matriz = matriz;
        this.children = new LinkedList<>();
    }

    public E[][]  getContent() {
        return this.matriz;
    }

    public void setContent(E[][] matriz) {
        this.matriz = matriz;
    }

    public List<Tree<E>> getChildren() {
        return children;
    }

    public void setChildren(List<Tree<E>> children) {
        this.children = children;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                sb.append(matriz[i][j] != null ? matriz[i][j].toString() : " ");
                if (j < matriz[i].length - 1) {
                    sb.append(" | ");
                }
            }
            if (i < matriz.length - 1) {
                sb.append("\n---------\n");
            }
        }
        return sb.toString();
    }

    public Tree<E> getTreeChildern(int i) {
        return children.get(i);
    }
}
