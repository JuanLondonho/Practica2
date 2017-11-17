/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Matriz;
import java.util.ArrayList;

/**
 *
 * @author juanclg
 */
public class ControladorArrayList {
    
    public static void ordenarPorDato(ArrayList<String> lista){
        String temp;
        
        for (int i = 1; i < lista.size()-1; i++){
            for (int j = 2; j < lista.size() - i; j++){
                String[] split_j = lista.get(j).split(" ");
                String[] split_j1 = lista.get(j-1).split(" ");
                
                if (Integer.parseInt(split_j1[2]) > Integer.parseInt(split_j[2])){
                    temp = lista.get(j-1);
                    lista.set(j-1, lista.get(j));
                    lista.set(j, temp);
                }else if(Integer.parseInt(split_j1[2]) == Integer.parseInt(split_j[2])){
                    lista.remove(j);
                }
            }
        }
    }
    
    public static Matriz[] ordenarPorDato(Matriz[] lista){
        String temp;
        
        for (int i = 0; i < lista.length - 1; i++){
            for (int j = 0; j < lista.length - 1; j++){
                if(lista[j].getDato() > lista[j+1].getDato()){
                    Matriz aux = lista[j+1];
                    lista[j+1] = lista[j];
                    lista[j] = aux;
                      
                }
            }
        }
        
        return lista;
    }
    
}
