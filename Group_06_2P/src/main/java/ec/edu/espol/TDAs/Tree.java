/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.TDAs;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class Tree {

    private TreeNode root;

    public Tree() {
        this.root = null;
    }

    public Tree(int[][] m1) {
        TreeNode matriz = new TreeNode<>(m1);
        this.root = matriz;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public int[][] getRoot() {
        return root.getContent();
    }

    public TreeNode getRootNode() {
        return this.root;
    }

    public void setRoot(int[][] matriz) {
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
        // this.setRoot();
    }

    public void addChildren(Tree children) {
        if (this.root != null) {
            this.root.addChildrenNode(children);
        }
    }

    public int counthijos() {
        if (this.root == null) {
            return 0;
        }

        int count = 1;

        if (this.isLeaf()) {
            return 1;
        }

        for (Tree child : this.getChildrens()) {
            count += child.counthijos();
        }

        return count;
    }

    public List<Tree> getChildrens() {
        return this.root.getChildren();
    }

    public int countNodes() {
        int nodes = 0;
        Queue<Tree> q = new ArrayDeque<Tree>();
        if (!this.isEmpty()) {
            q.offer(this);
            while (!q.isEmpty()) {
                Tree tree = q.poll();
                if (!tree.isLeaf()) {
                    nodes++;
                    for (Tree t : tree.getChildrens()) {
                        q.offer(t);
                    }
                }
            }
        }
        return nodes;
    }
}
