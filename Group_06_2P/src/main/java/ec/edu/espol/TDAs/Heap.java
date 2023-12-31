/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.TDAs;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author angelozurita
 */
public class Heap<E> implements Iterable<E>{
    private Comparator<E> comp;
    private E[] arreglo;
    private int MAX= 100;
    private int effectiveSize;
    private boolean isMax;

    public Heap(int tmax, boolean isMax) {
        this.arreglo = (E[]) new Object[tmax];
        this.effectiveSize = 0;
        this.isMax = isMax;
    }

    public Heap(Comparator<E> comp,E[] arreglo, boolean isMax) {
        this.comp = comp;
        this.arreglo = (E[]) new Object[MAX];
        for(int i = 0 ; i<arreglo.length ; i++){
            this.arreglo[i] = arreglo[i];
        }
        this.effectiveSize = arreglo.length ;
        this.isMax = isMax;
    }
    
    public Heap(Comparator<E> comp,List<E> arreglo, boolean isMax) {
        this.comp = comp;
        this.arreglo = (E[]) new Object[MAX];
        for(int i = 0 ; i<arreglo.size(); i++){
            this.arreglo[i] = arreglo.get(i);
        }
        this.effectiveSize = arreglo.size();
        this.isMax = isMax;
        this.makeHeap();
    }
    public int posIzq(int i){
        int valor = (i*2)+1; 
        if(valor > effectiveSize || i < 0){
            return -1;
        }
        else{
            return valor;
        }
    }
    public int posDer(int i){
        int valor = (i*2)+2; 
        if(valor > effectiveSize || i < 0){
            return -1;
        }
        else{
            return valor;
        }
    }
    public int posPadre(int i){
        int valor = (i-1)/2;
        if(valor > effectiveSize || i==0){
            return -1;
        }
        else{
            return valor;
        }
    }
    public boolean isEmpty(){
        return effectiveSize == 0;
    }
    
    // tengo que recibir el indice 
    public void ajustar(int posnodo){
        // solo si cumple esta condición se hace el metodo
        if(posnodo < effectiveSize && posnodo>=0){
            if(isMax){
                int posMayor = posnodo;
                int posIzq = posIzq(posnodo);
                int posDer = posDer(posnodo);
                
                if(posIzq>=0 && posIzq<effectiveSize && comp.compare(arreglo[posIzq],arreglo[posMayor]) > 0){
                    posMayor = posIzq;
                }
                if(posDer>=0 && posDer<effectiveSize && comp.compare(arreglo[posDer],arreglo[posMayor]) > 0){
                        posMayor = posDer;
                }
                
                if(posMayor != posnodo){
                   intercambiar(posnodo,posMayor);
                   ajustar(posMayor);
                   
                }
            }
            else{
                int posMenor = posnodo;
                int posIzq = posIzq(posnodo);
                int posDer = posDer(posnodo);
                
                if(posIzq>=0 && posIzq<effectiveSize && comp.compare(arreglo[posIzq],arreglo[posMenor]) < 0){
                    posMenor = posIzq;
                }
                if(posDer>=0 && posDer<effectiveSize && comp.compare(arreglo[posDer],arreglo[posMenor]) < 0){
                    posMenor = posDer;
                }
                
                if(posMenor != posnodo){
                   intercambiar(posnodo,posMenor);
                   ajustar(posMenor);
                }
             }  
        }  
    } 
    private void intercambiar(int posnodo, int posMayor) {
        E content = arreglo[posnodo];
        arreglo[posnodo] = arreglo[posMayor];
        arreglo[posMayor] = content;
    }
    
    public void makeHeap(){
        int valor = (this.effectiveSize/2) -1;
        for(int i = valor ; i >= 0 ; i--){
            ajustar(i);
        }
    }
    
    //desencolar
    public E desencolar(){
        if(!this.isEmpty()){
            E[] newarreglo = (E[]) new Object[effectiveSize-1];
            E value = this.arreglo[0];
            effectiveSize--;
            // el ultimo indice originalmente ahora es igual al effectiveSize porque ya reste uno
            intercambiar(0,effectiveSize);
            for(int i = 0 ; i< newarreglo.length ; i++){
                newarreglo[i] = this.arreglo[i];
            }
            this.arreglo = newarreglo;
            ajustar(0);
            return value;
        }   
        return null;
    }
    // revisar
    public void encolar(E value){
        if(value != null){
            if(effectiveSize+ 1 > MAX){
                addCapacity();
            }            
            effectiveSize ++ ; 
            this.arreglo[effectiveSize-1] = value;
            int i_value = effectiveSize-1;
            while(comp.compare(this.arreglo[i_value], this.arreglo[posPadre(i_value)]) > 0 ){
                ajustar(posPadre(i_value));
                i_value = posPadre(i_value); 
            }
        }
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < effectiveSize;
            }

            @Override
            public E next() {
                E element = arreglo[cursor];
                cursor++;
                return element;
            }
        };
        return it;
    }    

    private void addCapacity() {
        MAX *= 2;
        E[] arreglonew = (E[]) new Object[MAX];
        for(int i = 0 ; i<this.arreglo.length ; i++){
            arreglonew[i] = this.arreglo[i];
        }
        this.arreglo = arreglonew; 
    }
    
    public E getLast(){
        return arreglo[effectiveSize-1];
    }

    
}
