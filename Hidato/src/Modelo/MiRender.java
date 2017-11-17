/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.Color;
import java.awt.Component;
import java.util.Arrays;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import Controlador.ControladorHidato;
/**
 *
 * @author Juan Carlos Londoño - Yeison Ballesteros
 */
public class MiRender extends DefaultTableCellRenderer{
    private int fila;
    private int columna;
    private int tamaño;
    
    /*
    en el constructor se pide fila y columna, para conocer el tamaño
    de la matriz y marcar el último número en el tablero.
    */
    public MiRender(int fila, int columna){
        this.columna = columna;
        this.fila = fila;
        tamaño = fila*columna;
        
    }
    
    

    /*
    Éste metodo se encarga de marcar las casillas según las condiciones 
    que se le indiquen y los colores que se le indiquen,
    cuando éste se llama, se evalúa casilla a casilla toda la tabla.
    */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int fila, int columna ){
        
        JLabel l = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, fila, columna);
        
        if(Controlador.ControladorHidato.pintarPistas == null){
           if( value != null && !value.equals("")){ //se pregunta si la casilla es distinta de vacía.
                if(value.equals(1) || value.equals(tamaño)){ // este if detecta el primer y último número para marcarlos
                    l.setBackground(Color.blue);
                    l.setForeground(Color.WHITE);
                    l.setHorizontalAlignment(CENTER);
                    return l;
                }else{      // si es un número y no es ni el inicial ni el final, se procede a marcar como pista
                    l.setBackground(Color.LIGHT_GRAY);
                    l.setForeground(Color.BLACK);
                    l.setHorizontalAlignment(CENTER);
                    return l;
                }
            }else {
               l.setBackground(Color.WHITE);
                l.setForeground(Color.BLACK);
                l.setHorizontalAlignment(CENTER);
                return l;
           }
        }else{
           if( value != null && !value.equals("")){
                if(contiene((int)value)){
                    if(value.equals(1) || value.equals(tamaño)){ // este if detecta el primer y último número para marcarlos
                        l.setBackground(Color.blue);
                        l.setForeground(Color.WHITE);
                        l.setHorizontalAlignment(CENTER);
                        return l;
                    }else{      // si es un número y no es ni el inicial ni el final, se procede a marcar como pista
                        l.setBackground(Color.LIGHT_GRAY);
                        l.setForeground(Color.BLACK);
                        l.setHorizontalAlignment(CENTER);
                        return l;
                    }
                }else{
                    l.setBackground(Color.WHITE);
                    l.setForeground(Color.BLACK);
                    l.setHorizontalAlignment(CENTER);
                    return l;
                }
           }else{
                l.setBackground(Color.WHITE);
                l.setForeground(Color.BLACK);
                l.setHorizontalAlignment(CENTER);
                return l;
           }
       }
    }
    
    public boolean contiene(int a){
        for(int i = 0; i < ControladorHidato.pintarPistas.length; i++){
            if(a == ControladorHidato.pintarPistas[i]){
                return true;
            }
        }
        return false;
    }
}
