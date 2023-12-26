/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.TDAs;

import java.util.LinkedList;
import java.util.List;

public class TreeNode<E> {
    private int[][] matriz;
    private List<Tree> children;

    public TreeNode(int[][] matriz) {
        this.matriz = matriz;
        this.children = new LinkedList<>();
    }

    public int[][] getContent() {
        return this.matriz;
    }

    public void setContent(int[][] matriz) {
        this.matriz = matriz;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }

    public Tree getTreeChildern(int i) {
        return children.get(i);
    }

    public void addChildrenNode(Tree children) {
        this.children.add(children);
    }

}
