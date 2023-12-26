/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.Clases;

import ec.edu.espol.TDAs.Tree;

/**
 *
 * @author HOME
 */
public class Computadora {

    public static int tiradas = 0;

    private Tree<Integer> arbol;
    private Integer[][] tablero;
    public final Integer miFICHA = 2;

    public Computadora(Tree<Integer> arbol) {
        this.arbol = arbol;
    }

    public int movDisponibles(Integer[][] tablero) {
        int mov = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == null) {
                    mov++;
                }
            }
        }
        return mov;
    }

    public int[] posVacias(Integer[][] tablero) {
        int[] indices = new int[movDisponibles(tablero)];
        int indice = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == null) {
                    indices[indice] = i * 3 + j;
                    indice++;
                }
            }
        }
        return indices;
    }

    public int movimiento(Integer[][] tablero) {
        this.tablero = tablero;
        tiradas++;

        // Inicializamos el árbol con el árbol proporcionado
        Tree<Integer> arbolActual = new Tree<>();
        arbolActual.setRoot(tablero);

        // Calculamos el mejor movimiento del árbol, desde las hojas hasta la raíz.
        int mejorMovimiento = movComputadora(arbolActual);

        // Devolvemos el mejor movimiento.
        return mejorMovimiento;
    }

    private int movComputadora(Tree<Integer> arbolActual) {

        // Si el árbol está vacío, el juego ha terminado
        if (arbolActual.isEmpty()) {
            return -1;
        }

        // Si el turno es de la computadora, buscamos el mejor movimiento
        if (arbolActual.getRoot()[0] == null) {
            // Buscamos el movimiento que da como resultado el máximo valor del árbol
            int mejorMovimiento = -1;
            int mejorValor = Integer.MIN_VALUE;
            for (int i = 0; i <arbolActual.getRootNode().getChildren().size() ; i++) {
                int valor = movComputadora(arbolActual.getRootNode().getTreeChildern(i));
                if (valor > mejorValor) {
                    mejorMovimiento = i;
                    mejorValor = valor;
                }
            }
            return mejorMovimiento;
        }

        // Si el turno no es de la computadora, buscamos el peor movimiento
        else {
            // Buscamos el movimiento que da como resultado el mínimo valor del árbol
            int peorMovimiento = -1;
            int peorValor = Integer.MAX_VALUE;
            for (int i = 0; i < arbolActual.getRootNode().getChildren().size(); i++) {
                int valor = movComputadora(arbolActual.getRootNode().getTreeChildern(i));
                if (valor < peorValor) {
                    peorMovimiento = i;
                    peorValor = valor;
                }
            }
            return peorMovimiento;
        }
    }
    
    
    
}
