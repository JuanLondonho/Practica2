/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author juanclg
 */
public class SolucionHidato {
    private int solucionIndividual(int filaActual, int columnaActual, int [][]opcionesPosibles){
        int posibleFila;
        int posibleColumna;
        int contador = 0; // se usa para llevar una cuenta de los movimientos posibles.
        
        
        
        /*"Proceso de verificación 1"
        se encarga de evualuar los posibles movimientos
        dentro del tablero, dónde en el peor de los casos  
        serían las 8 casillas posibles que lo rodean.*/
        
        //Inicia proceso de verificación 1 para una posible fila y columna
        posibleFila = filaActual-1;                                 //calcula uno de los posibles movimientos dentro del tablero.
        posibleColumna = columnaActual;                             // 
        if(posibleFila >= 0){                                       // se verifica que no se salga del tamaño del tablero.
            if(opcionesPosibles[posibleFila][posibleColumna]!=1){   // Se verifica que no haya sido visitada anteriormente.
                contador++;                                         // se cuenta como movimiento posible.
            }
        }
        //Termina proceso de verificación 1 para ésta posible casilla
        
        
        //Se repite proceso de verificación 1 con nuevas Posibles filas y columnas.
        posibleFila = filaActual-1;
        posibleColumna = columnaActual-1;
        if(posibleFila >= 0 && posibleColumna >= 0){
            if(opcionesPosibles[posibleFila][posibleColumna]!=1){
                contador++;
            }
        }
        
        //Se repite proceso de verificación 1 con nuevas Posibles filas y columnas.
        posibleFila = filaActual-1;
        posibleColumna = columnaActual+1;
        if(posibleFila >= 0 && posibleColumna < opcionesPosibles[0].length){
            if(opcionesPosibles[posibleFila][posibleColumna]!=1){
                contador++;
            }
        }
        
        //Se repite proceso de verificación 1 con nuevas Posibles filas y columnas.
        posibleFila = filaActual+1;
        posibleColumna = columnaActual;
        if(posibleFila < opcionesPosibles.length){
            if(opcionesPosibles[posibleFila][posibleColumna]!=1){
                contador++;
            }
        }
        
        //Se repite proceso de verificación 1 con nuevas Posibles filas y columnas.
        posibleFila = filaActual+1;
        posibleColumna = columnaActual+1;
        if(posibleFila < opcionesPosibles.length && posibleColumna < opcionesPosibles[0].length){
            if(opcionesPosibles[posibleFila][posibleColumna]!=1){
                contador++;
            }
        }
        
        //Se repite proceso de verificación 1 con nuevas Posibles filas y columnas.
        posibleFila = filaActual+1;
        posibleColumna = columnaActual-1;
        if(posibleFila < opcionesPosibles.length && posibleColumna >= 0){
            if(opcionesPosibles[posibleFila][posibleColumna]!=1){
                contador++;
            }
        }
        
        //Se repite proceso de verificación 1 con nuevas Posibles filas y columnas.
        posibleFila = filaActual;
        posibleColumna = columnaActual+1;
        if(posibleColumna < opcionesPosibles[0].length){
            if(opcionesPosibles[posibleFila][posibleColumna]!=1){
                contador++;       
            }
        }
        
        //Se repite proceso de verificación 1 con nuevas Posibles filas y columnas.
        posibleFila = filaActual;
        posibleColumna = columnaActual-1;
        if(posibleColumna >= 0){
            if(opcionesPosibles[posibleFila][posibleColumna]!=1){
                contador++;
            }
        }
        
        return contador;   // retorna el número de movimientos posibles para la fila y columna que se han ingresado como paramentros.        
        
    }
    
    
    public Matriz[] posiblesSoluciones(int filaActual, int columnaActual, int [][]opcionesPosibles, Matriz pista, int valorActual){
        Matriz[] soluciones;                    //vector donde se retornaran las posibles soluciones
        Matriz [] auxiliar = new Matriz[8];     //vector auxiliar donde se almacenaran de manera parcial las posibles soluciones, siendo 8 el máximo de soluciones posibles.
        int posibleFila;                        
        int posibleColumna;
        int contador = 0;                       //En este caso, contador lleva la cuenta de los datos con menos soluciones posibles.
        int posibleMenor;                       //Almacenará el número de movimientos posibles de cada casilla, haciendolo candidato a posible menor
        
        
        
        
        /*"Proceso de verificación 2"
        se encarga de evualuar los posibles movimientos
        dentro del tablero, dónde sólo se tendran en cuenta  
        las casillas que tengan menos movimientos posibles, y a su vez
        serán almacenados en un un vector que se retornará con estos movimientos posibles.
        */
        
        //Inicia proceso de verificación 2
        posibleFila = filaActual-1;                                 //calcula uno de los posibles movimientos dentro del tablero.    
        posibleColumna = columnaActual;                             //   
        if(posibleFila >= 0){                                       //se verifica que no se salga del tamaño del tablero.
            if(opcionesPosibles[posibleFila][posibleColumna]!=1 &&
                                             igualdad((diferencia(posibleFila, pista.getFilaNueva())),
                                            (diferencia(posibleColumna, pista.getColumnaNueva()))) <= diferencia(pista.getDato(), valorActual)){   //se verifica que no haya sido visitado anteriormente.
                posibleMenor = solucionIndividual(posibleFila, posibleColumna, opcionesPosibles); //se calcula los movimientos posibles que tendrá la casilla analizada
                
                    Matriz dato = new Matriz();                     //se crea el objeto de tipo matriz. (Atributos fila y columna)
                    dato.setFilaNueva(posibleFila);                 //se le cambia la fila por la analizada actualmente.
                    dato.setColumnaNueva(posibleColumna);
                    dato.setDato(posibleMenor);                     //se le cambia la columna por la analizada actualmente.
                    auxiliar[contador] = dato;                      //se agrega en el auxiliar en la posición 0, ya que son nuevos datos menores.
                    contador++;
                
            }
        }
        //Termina proceso de verificación 2
        
        
        //Se repite proceso de verificación 2 con nuevas Posibles filas y columnas.
        posibleFila = filaActual-1;
        posibleColumna = columnaActual-1;
        if(posibleFila >= 0 && posibleColumna >= 0){
            if(opcionesPosibles[posibleFila][posibleColumna]!=1 &&
                                            igualdad((diferencia(posibleFila, pista.getFilaNueva())),
                                            (diferencia(posibleColumna, pista.getColumnaNueva()))) <= diferencia(pista.getDato(), valorActual)){
                posibleMenor = solucionIndividual(posibleFila, posibleColumna, opcionesPosibles);
                Matriz dato = new Matriz();
                dato.setFilaNueva(posibleFila);
                dato.setColumnaNueva(posibleColumna);
                dato.setDato(posibleMenor);
                auxiliar[contador] = dato;
                contador++;
                
            }
        }
        
        //Se repite proceso de verificación 2 con nuevas Posibles filas y columnas.
        posibleFila = filaActual-1;
        posibleColumna = columnaActual+1;
        if(posibleFila >= 0 && posibleColumna < opcionesPosibles[0].length){
            if(opcionesPosibles[posibleFila][posibleColumna]!=1 &&
                                             igualdad((diferencia(posibleFila, pista.getFilaNueva())),
                                            (diferencia(posibleColumna, pista.getColumnaNueva()))) <= diferencia(pista.getDato(), valorActual)){
                posibleMenor = solucionIndividual(posibleFila, posibleColumna, opcionesPosibles);
                Matriz dato = new Matriz();
                dato.setFilaNueva(posibleFila);
                dato.setColumnaNueva(posibleColumna);
                dato.setDato(posibleMenor);
                auxiliar[contador] = dato;
                contador++;
            }
        }
        
        //Se repite proceso de verificación 2 con nuevas Posibles filas y columnas.
        posibleFila = filaActual+1;
        posibleColumna = columnaActual;
        if(posibleFila < opcionesPosibles.length){
            if(opcionesPosibles[posibleFila][posibleColumna]!=1 &&
                                            igualdad((diferencia(posibleFila, pista.getFilaNueva())),
                                            (diferencia(posibleColumna, pista.getColumnaNueva()))) <= diferencia(pista.getDato(), valorActual)){
                posibleMenor = solucionIndividual(posibleFila, posibleColumna, opcionesPosibles);
                Matriz dato = new Matriz();
                dato.setFilaNueva(posibleFila);
                dato.setColumnaNueva(posibleColumna);
                dato.setDato(posibleMenor);
                auxiliar[contador] = dato;
                contador++;
                
            }
        }
        
        //Se repite proceso de verificación 2 con nuevas Posibles filas y columnas.
        posibleFila = filaActual+1;
        posibleColumna = columnaActual+1;
        if(posibleFila < opcionesPosibles.length && posibleColumna < opcionesPosibles[0].length){
            if(opcionesPosibles[posibleFila][posibleColumna]!=1 &&
                                             igualdad((diferencia(posibleFila, pista.getFilaNueva())),
                                            (diferencia(posibleColumna, pista.getColumnaNueva()))) <= diferencia(pista.getDato(), valorActual)){
                posibleMenor = solucionIndividual(posibleFila, posibleColumna, opcionesPosibles);
                Matriz dato = new Matriz();
                dato.setFilaNueva(posibleFila);
                dato.setColumnaNueva(posibleColumna);
                dato.setDato(posibleMenor);
                auxiliar[contador] = dato;
                contador++;
            }
        }
        
        //Se repite proceso de verificación 2 con nuevas Posibles filas y columnas.
        posibleFila = filaActual+1;
        posibleColumna = columnaActual-1;
        if(posibleFila < opcionesPosibles.length && posibleColumna >= 0){
            if(opcionesPosibles[posibleFila][posibleColumna]!=1 &&
                                           igualdad((diferencia(posibleFila, pista.getFilaNueva())),
                                            (diferencia(posibleColumna, pista.getColumnaNueva()))) <= diferencia(pista.getDato(), valorActual)){
                posibleMenor = solucionIndividual(posibleFila, posibleColumna, opcionesPosibles);
                Matriz dato = new Matriz();
                dato.setFilaNueva(posibleFila);
                dato.setColumnaNueva(posibleColumna);
                dato.setDato(posibleMenor);
                auxiliar[contador] = dato;
                contador++;
                
            }
        }
        
        //Se repite proceso de verificación 2 con nuevas Posibles filas y columnas.
        posibleFila = filaActual;
        posibleColumna = columnaActual+1;
        if(posibleColumna < opcionesPosibles[0].length){
            if(opcionesPosibles[posibleFila][posibleColumna]!=1 &&
                                             igualdad((diferencia(posibleFila, pista.getFilaNueva())),
                                            (diferencia(posibleColumna, pista.getColumnaNueva()))) <= diferencia(pista.getDato(), valorActual)){
                posibleMenor = solucionIndividual(posibleFila, posibleColumna, opcionesPosibles);
                Matriz dato = new Matriz();
                dato.setFilaNueva(posibleFila);
                dato.setColumnaNueva(posibleColumna);
                dato.setDato(posibleMenor);
                auxiliar[contador] = dato;
                contador++;
            }
        }
        
        //Se repite proceso de verificación 2 con nuevas Posibles filas y columnas.
        posibleFila = filaActual;
        posibleColumna = columnaActual-1;
        if(posibleColumna >= 0){
            if(opcionesPosibles[posibleFila][posibleColumna]!=1 &&
                                            igualdad((diferencia(posibleFila, pista.getFilaNueva())),
                                            (diferencia(posibleColumna, pista.getColumnaNueva()))) <= diferencia(pista.getDato(), valorActual)){
                posibleMenor = solucionIndividual(posibleFila, posibleColumna, opcionesPosibles);
                Matriz dato = new Matriz();
                dato.setFilaNueva(posibleFila);
                dato.setColumnaNueva(posibleColumna);
                dato.setDato(posibleMenor);
                auxiliar[contador] = dato;
                contador++;
            }
        }
        
        soluciones = new Matriz[contador];            //se pide memoria para el vector Solucion con el tamaño de la información verdaderamente importante.
        for(int i = 0; i<contador; i++){               //se recorre desde 0 hasta el número de datos que nos interesan
            soluciones[i] = auxiliar[i];                //se realiza una copia solo de los datos que nos interesan dentro del vector auxiliar.
        }
        soluciones = Controlador.ControladorArrayList.ordenarPorDato(soluciones);
        return soluciones;                         //se retorna el vector con las soluciones posibles.
      
    }
    
    public int diferencia(int a, int b){
        int cont = 0;
        if(a < b){
           for(int i = a+1; i < b; i++){
               cont++;
            }
           
           return cont;
        }else{
            if(b < a){
            for(int i = b+1; i < a; i++){
                cont++;
            }
            return cont;
            }else{
                return 0;
            }
        }
    }
        
    public int igualdad(int a, int b){
        if(a == b) return a;
        return a+b;
    }
}
