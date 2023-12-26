/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.TDAs;

public class Tree<E> {

    private TreeNode<E> root;

    public Tree() {
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public E[][] getRoot() {
        return root.getContent();
    }

    public TreeNode getRootNode() {
        return this.root;
    }

    public void setRoot(E[][] matriz) {
        if (this.root == null) {
            this.root = new TreeNode<>(matriz);
        } else {
            this.root.setContent(matriz);
        }
    }

    public boolean isLeaf() {
        return this.root.getChildren().isEmpty();
    }

    public void tableroEmpty() {
        Integer[][] initialBoard = new Integer[3][3];

        for (Integer[] initialBoard1 : initialBoard) {
            for (int j = 0; j < initialBoard1.length; j++) {
                initialBoard1[j] = -1; 
            }
        }
//        this.setRoot();
    }
}
