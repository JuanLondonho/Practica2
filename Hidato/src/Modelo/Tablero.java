/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


/**
 *
 * @author Juan Carlos Londoño - Yeison Ballesteros
 */

import java.util.Random;

public class Tablero {
    int cont;
    Matriz [] array1;
    SolucionHidato solucionHidato = new SolucionHidato();
    SolucionParcial solucionParcial = new SolucionParcial(); //objeto de dicha clase para luego acceder a sus metodos
    int [][]matrizTablero;     //se crea la matriz por donde se recorera el tablero y se almacenara la posible solución
    int [][]opcionesPosibles;  //se crear para tenerlo como un vector de visitados para que no ingrese por donde ya pasó.
    int totalCasillas;         //se usa para almacenar el tamaño del tabler(m*n).
    int numColumnas;           //se usa para saber el número de columnas del tablero.
    int numFilas;              //se usa para saber el número de filas del tablero.
    int valor;             //se usa para ir llevando los valores a la matriz, desde 1 hasta n*m
    Matriz[] solucion;         //aquí se guardaran los posibles movimientos que tenga un casilla.
    Random x = new Random();   /*se usa para generar números ramdons entre 
                                las opciones que se encuentran disponible y moverse por esa*/
    
      
    /*
    se toman el número de filas y columnas ingreados por el usuario
    para luego crear el tamaño total del tablero.
    */
    public Tablero(int fila, int columna){
        numFilas = fila;
        numColumnas = columna;
        totalCasillas = numFilas*numColumnas;
    }
    
    
    public Tablero(){
    }

    
    /*
    se crean las matrices con los tamaños ingresados por el usuario
    y se marca la primer casilla de forma random en la matriz
    */
    public int[][] crear(){
        boolean respuesta = true;                               //valor para saber cuando se generó una instancia correcta.
        int[][] tablero;                                        //se almacenará la instancia que retorne el metodo recursivo, generarTablero.
        do{
            matrizTablero = new int[numFilas][numColumnas];     //se pide memoria para retornar la matriz que representa el tablero
            opcionesPosibles = new int[numFilas][numColumnas];  //se pide memoria para un nuevo vecor de opcionesPosibles
            int filaMatriz = x.nextInt(numFilas);               //random de la fila.
            int columnaMatriz = x.nextInt(numColumnas);         //random de la columna.
            valor = 1;                                          //se inicia en 1 el cual es el primer valor que se aplicará al tablero.
            matrizTablero[filaMatriz][columnaMatriz] = valor;   //se marca el número 1 en la matriz.
            opcionesPosibles[filaMatriz][columnaMatriz] = 1;    //se marca esta casilla como ya visitada.
            tablero = generarTablero(filaMatriz, columnaMatriz);  /* se llama al metodo el cuál retorno el 
                                                                  posible posible, o null en caso de que no genere una instancia correcta*/
            if(tablero==null){                                   /*si no se genera una instancia correcta, el llamado es igual a null
                                                                  y no se hará nada, se seguirá generando una nueva instancia */
            }else{                                              //si no retorna null, es porque la instancia que se generó, es correcta.
               respuesta = false;                               //se le indica al ciclo que termine.
            }
        }while(respuesta); //mientras no encuentra una instancia correcta, se genera de nuevo un tablero.
        return tablero;   /*se retorna el tablero ya con instancias correctas.*/
    }
    
    
    /*
    Éste metodo es el encargado de ir recorriendo casilla a casilla 
    con la ayuda de los metodos de la clase solución parcial.
    Se encarga de marcar las casillas por las que pasa y se llama recursivamente 
    con la siguiente casilla que es generada por un random entre las posibles soluciones.
    */
    private int[][] generarTablero(int fila, int columna){
        int nuevaColumna;    //nueva columna que se enviara como paramentro en el llamado recursivo
        int nuevaFila;       //nueva fila que se enviara como paramentro en el llamado recursivo
        int posible;         //se guardará el número en la posición del vector que se realizó al azar.
        solucion = solucionParcial.posiblesSoluciones(fila, columna, opcionesPosibles, numFilas, numColumnas); /*
            se hace uso de un metodo de la clase solución parcial para retornar los movimientos posibles dicha casilla
        */                         
        if(valor == totalCasillas){  //esta condición consiste en evaluar si se terminó de reccorrer el tablero.
            return matrizTablero;    //termina el llamado y retorna la matriz con la posible solucón.
        }
        else if (solucion.length!=0){                       //si no ha terminado de recorrerlo.
            valor++;                //se aumenta el valor, ya que la siguiente casilla se le ll
            posible = x.nextInt(solucion.length);     // genera un número entre 0 y el tamaño de las posibles soluciones
            nuevaFila = solucion[posible].getFilaNueva();   //se asigna la fila de la casilla almacena en la posición random del vector
            nuevaColumna = solucion[posible].getColumnaNueva(); //se asigna la columna de la casilla almacena en la posición random del vector
            opcionesPosibles[nuevaFila][nuevaColumna] = 1;      //se marca ésta casilla como visitada
            matrizTablero[nuevaFila][nuevaColumna]=valor;       //se marca la casilla a la que se va a ir con el valor correspondiente.
            return generarTablero(nuevaFila, nuevaColumna);     /*se llama recursivamente con la nueva fila 
                                                                columna que se evaluara para el siguiente movimiento.*/
        }else{
            return null;        /*retorna null en caso de que no encuentre una solución pronto, para evitar
                                que se lleve a cabo una recursión tal vez infinita, y mejor se vuelve a empezar a 
                                generar el tablero con nuevas condiciones y siguiendo la heuristica, que, en la mayoría
                                de veces se encuentra una instancia correcta.*/
        }
    }
    
    public int[][] solucionar(int[][] matrizSolucion, Matriz[] array){
        cont = 1;
        array1 = array;
        matrizTablero = matrizSolucion;
        opcionesPosibles = new int[matrizTablero.length][matrizTablero[0].length];
        valor = 1;
        int filaInicio=0;
        int columnaInicio=0;
        for(int i = 0; i < matrizTablero.length; i++){
            for (int j = 0; j < matrizTablero[0].length; j++){
                if(matrizTablero[i][j]!=0){
                    opcionesPosibles[i][j]=1;
                    if(matrizTablero[i][j]==1){
                        filaInicio = i;
                        columnaInicio = j;
                    }
                }else{
                    opcionesPosibles[i][j]=0;
                }
            }
        }
        return solucionarHidato(filaInicio, columnaInicio, array1[cont]);
    }
    
    public int[][] solucionarHidato(int filaActual, int columnaActual, Matriz pista){
        int nuevaColumna;    
        int nuevaFila;
        
        if(valor+1 == pista.getDato()){
                if((valor+1) == ((opcionesPosibles.length)*(opcionesPosibles[0].length))){
                    return matrizTablero;
                }else{
                    cont++;
                    valor++;
                    solucionarHidato(pista.getFilaNueva(), pista.getColumnaNueva(), array1[cont]);
                    valor--;
                    cont--;
                    return matrizTablero;
                }
        }
        
        Matriz[] soluciones = solucionHidato.posiblesSoluciones(filaActual, columnaActual, opcionesPosibles, pista, valor+1);
        for (Matriz solucion1 : soluciones) {
            valor++;
            nuevaFila = solucion1.getFilaNueva();
            nuevaColumna = solucion1.getColumnaNueva();
            opcionesPosibles[nuevaFila][nuevaColumna] = 1;
            solucionarHidato(nuevaFila, nuevaColumna, pista);
            matrizTablero[nuevaFila][nuevaColumna] = valor;
            valor--;
            if(terminar(opcionesPosibles)){
                return matrizTablero;
            }
            opcionesPosibles[nuevaFila][nuevaColumna] = 0;
        }
        
        return null;
    }
    
    public boolean terminar(int[][] visitados){
        for(int i = 0; i<visitados.length; i++){
            for(int j = 0; j< visitados[0].length; j++){
                if(visitados[i][j]==0){
                    return false;
                }
            }
        }
        return true;
    }
}

