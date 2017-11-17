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
public class SolucionParcial {
    
    /* 
    EL metodo solucionIndividual se encarga de, dada una fila, una columna,
    unas opciones posibles y el tamaño de la matriz (NumColumnas y NumFilas)
    se calcule la siguiente posición que tiene menos movimientos posibles,
    creando un vector de los movimientos posibles y retornando su tamaño.
    Éste metodo es usando como heurística para un mejor desempeño del algoritmo.
    */
    
    private int solucionIndividual(int filaActual, int columnaActual, int [][]opcionesPosibles, int numColumnas, int numFilas){
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
        if(posibleFila >= 0 && posibleColumna < numColumnas){
            if(opcionesPosibles[posibleFila][posibleColumna]!=1){
                contador++;
            }
        }
        
        //Se repite proceso de verificación 1 con nuevas Posibles filas y columnas.
        posibleFila = filaActual+1;
        posibleColumna = columnaActual;
        if(posibleFila < numFilas){
            if(opcionesPosibles[posibleFila][posibleColumna]!=1){
                contador++;
            }
        }
        
        //Se repite proceso de verificación 1 con nuevas Posibles filas y columnas.
        posibleFila = filaActual+1;
        posibleColumna = columnaActual+1;
        if(posibleFila < numFilas && posibleColumna < numColumnas){
            if(opcionesPosibles[posibleFila][posibleColumna]!=1){
                contador++;
            }
        }
        
        //Se repite proceso de verificación 1 con nuevas Posibles filas y columnas.
        posibleFila = filaActual+1;
        posibleColumna = columnaActual-1;
        if(posibleFila < numFilas && posibleColumna >= 0){
            if(opcionesPosibles[posibleFila][posibleColumna]!=1){
                contador++;
            }
        }
        
        //Se repite proceso de verificación 1 con nuevas Posibles filas y columnas.
        posibleFila = filaActual;
        posibleColumna = columnaActual+1;
        if(posibleColumna < numColumnas){
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
    
    
    /* 
    El metodo posiblesSoluciones se encarga dada una fila, una columna, unas opciones posibles
    y el tamaño de la matriz(NumFilas y NumColumnas), de almacenar en un vector, sólo los movimientos que, a su vez, tengan menos movimientos posibles,
    haciendo uso del metodo anterior (solucionIndividual) para determinar cuales son estos movimientos, para al final retornarlos en un vector llamado soluciones.
    */
    public Matriz[] posiblesSoluciones(int filaActual, int columnaActual, int [][]opcionesPosibles, int numFilas, int numColumnas){
        Matriz[] soluciones;                    //vector donde se retornaran las posibles soluciones
        Matriz [] auxiliar = new Matriz[8];     //vector auxiliar donde se almacenaran de manera parcial las posibles soluciones, siendo 8 el máximo de soluciones posibles.
        int posibleFila;                        
        int posibleColumna;
        int contador = 0;                       //En este caso, contador lleva la cuenta de los datos con menos soluciones posibles.
        int menor =20;                          /*almacenará el menor número de movimientos posibles, el cúal se inicializa en un 
                                                 número mayor a 8 para que el primer dato, siempre sea tomado.*/
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
            if(opcionesPosibles[posibleFila][posibleColumna]!=1){   //se verifica que no haya sido visitado anteriormente.
                posibleMenor = solucionIndividual(posibleFila, posibleColumna, opcionesPosibles, numColumnas, numFilas); //se calcula los movimientos posibles que tendrá la casilla analizada
                if(posibleMenor<menor){                             //se verifica si esa cantidad de movimientos es menor a la que se tiene actualmente como menor.
                    contador=0;                                     //si es menor, significa que los que se habían almacenado, no se tendran en cuenta y se sobreescribirán.
                    menor = posibleMenor;                           //a menor se le asigna su nuevo valor.
                    Matriz dato = new Matriz();                     //se crea el objeto de tipo matriz. (Atributos fila y columna)
                    dato.setFilaNueva(posibleFila);                 //se le cambia la fila por la analizada actualmente.
                    dato.setColumnaNueva(posibleColumna);           //se le cambia la columna por la analizada actualmente.
                    auxiliar[contador] = dato;                      //se agrega en el auxiliar en la posición 0, ya que son nuevos datos menores.
                    contador++;                                     //se aumenta para asegurarse de no sobreescribir las menores con iguales posibles movimientos.
                }else if(posibleMenor==menor){                      //por el contrario si el número de movimientos es igual al actualmente menor, se agregará en el vector auxiliar en la siguiente posición
                    Matriz dato = new Matriz();                     //se crea el objeto de tipo matriz. (Atributos fila y columna)
                    dato.setFilaNueva(posibleFila);                 //se le cambia la fila por la analizada actualmente.
                    dato.setColumnaNueva(posibleColumna);           //se le cambia la columna por la analizada actualmente.
                    auxiliar[contador] = dato;                      //se agrega en el auxiliar en la posición siguiente para no sobreescribir valores iguales ya existentes.
                    contador++;                                     //se aumenta para asegurarse de no sobreescribir las menores con iguales posibles movimientos.
                }
            }
        }
        //Termina proceso de verificación 2
        
        
        //Se repite proceso de verificación 2 con nuevas Posibles filas y columnas.
        posibleFila = filaActual-1;
        posibleColumna = columnaActual-1;
        if(posibleFila >= 0 && posibleColumna >= 0){
            if(opcionesPosibles[posibleFila][posibleColumna]!=1){
                posibleMenor = solucionIndividual(posibleFila, posibleColumna, opcionesPosibles, numColumnas, numFilas);
                if(posibleMenor<menor){
                    contador=0;
                    menor = posibleMenor;
                    Matriz dato = new Matriz();
                    dato.setFilaNueva(posibleFila);
                    dato.setColumnaNueva(posibleColumna);
                    auxiliar[contador] = dato;
                    contador++;
                }else if(posibleMenor==menor){
                    Matriz dato = new Matriz();
                    dato.setFilaNueva(posibleFila);
                    dato.setColumnaNueva(posibleColumna);
                    auxiliar[contador] = dato;
                    contador++;
                }
            }
        }
        
        //Se repite proceso de verificación 2 con nuevas Posibles filas y columnas.
        posibleFila = filaActual-1;
        posibleColumna = columnaActual+1;
        if(posibleFila >= 0 && posibleColumna < numColumnas){
            if(opcionesPosibles[posibleFila][posibleColumna]!=1){
                posibleMenor = solucionIndividual(posibleFila, posibleColumna, opcionesPosibles, numColumnas, numFilas);
                if(posibleMenor<menor){
                    contador=0;
                    menor = posibleMenor;
                    Matriz dato = new Matriz();
                    dato.setFilaNueva(posibleFila);
                    dato.setColumnaNueva(posibleColumna);
                    auxiliar[contador] = dato;
                    contador++;
                }else if(posibleMenor==menor){
                    Matriz dato = new Matriz();
                    dato.setFilaNueva(posibleFila);
                    dato.setColumnaNueva(posibleColumna);
                    auxiliar[contador] = dato;
                    contador++;
                }
            }
        }
        
        //Se repite proceso de verificación 2 con nuevas Posibles filas y columnas.
        posibleFila = filaActual+1;
        posibleColumna = columnaActual;
        if(posibleFila < numFilas){
            if(opcionesPosibles[posibleFila][posibleColumna]!=1){
                posibleMenor = solucionIndividual(posibleFila, posibleColumna, opcionesPosibles, numColumnas, numFilas);
                if(posibleMenor<menor){
                    contador=0;
                    menor = posibleMenor;
                    Matriz dato = new Matriz();
                    dato.setFilaNueva(posibleFila);
                    dato.setColumnaNueva(posibleColumna);
                    auxiliar[contador] = dato;
                    contador++;
                }else if(posibleMenor==menor){
                    Matriz dato = new Matriz();
                    dato.setFilaNueva(posibleFila);
                    dato.setColumnaNueva(posibleColumna);
                    auxiliar[contador] = dato;
                    contador++;
                }
            }
        }
        
        //Se repite proceso de verificación 2 con nuevas Posibles filas y columnas.
        posibleFila = filaActual+1;
        posibleColumna = columnaActual+1;
        if(posibleFila < numFilas && posibleColumna < numColumnas){
            if(opcionesPosibles[posibleFila][posibleColumna]!=1){
                posibleMenor = solucionIndividual(posibleFila, posibleColumna, opcionesPosibles, numColumnas, numFilas);
                if(posibleMenor<menor){
                    contador=0;
                    menor = posibleMenor;
                    Matriz dato = new Matriz();
                    dato.setFilaNueva(posibleFila);
                    dato.setColumnaNueva(posibleColumna);
                    auxiliar[contador] = dato;
                    contador++;
                }else if(posibleMenor==menor){
                    Matriz dato = new Matriz();
                    dato.setFilaNueva(posibleFila);
                    dato.setColumnaNueva(posibleColumna);
                    auxiliar[contador] = dato;
                    contador++;
                }
            }
        }
        
        //Se repite proceso de verificación 2 con nuevas Posibles filas y columnas.
        posibleFila = filaActual+1;
        posibleColumna = columnaActual-1;
        if(posibleFila < numFilas && posibleColumna >= 0){
            if(opcionesPosibles[posibleFila][posibleColumna]!=1){
                posibleMenor = solucionIndividual(posibleFila, posibleColumna, opcionesPosibles, numColumnas, numFilas);
                if(posibleMenor<menor){
                    contador=0;
                    menor = posibleMenor;
                    Matriz dato = new Matriz();
                    dato.setFilaNueva(posibleFila);
                    dato.setColumnaNueva(posibleColumna);
                    auxiliar[contador] = dato;
                    contador++;
                }else if(posibleMenor==menor){
                    Matriz dato = new Matriz();
                    dato.setFilaNueva(posibleFila);
                    dato.setColumnaNueva(posibleColumna);
                    auxiliar[contador] = dato;
                    contador++;
                }
            }
        }
        
        //Se repite proceso de verificación 2 con nuevas Posibles filas y columnas.
        posibleFila = filaActual;
        posibleColumna = columnaActual+1;
        if(posibleColumna < numColumnas){
            if(opcionesPosibles[posibleFila][posibleColumna]!=1){
                posibleMenor = solucionIndividual(posibleFila, posibleColumna, opcionesPosibles, numColumnas, numFilas);
                if(posibleMenor<menor){
                    contador=0;
                    menor = posibleMenor;
                    Matriz dato = new Matriz();
                    dato.setFilaNueva(posibleFila);
                    dato.setColumnaNueva(posibleColumna);
                    auxiliar[contador] = dato;
                    contador++;
                }else if(posibleMenor==menor){
                    Matriz dato = new Matriz();
                    dato.setFilaNueva(posibleFila);
                    dato.setColumnaNueva(posibleColumna);
                    auxiliar[contador] = dato;
                    contador++;
                }
            }
        }
        
        //Se repite proceso de verificación 2 con nuevas Posibles filas y columnas.
        posibleFila = filaActual;
        posibleColumna = columnaActual-1;
        if(posibleColumna >= 0){
            if(opcionesPosibles[posibleFila][posibleColumna]!=1){
                posibleMenor = solucionIndividual(posibleFila, posibleColumna, opcionesPosibles, numColumnas, numFilas);
                if(posibleMenor<menor){
                    contador=0;
                    menor = posibleMenor;
                    Matriz dato = new Matriz();
                    dato.setFilaNueva(posibleFila);
                    dato.setColumnaNueva(posibleColumna);
                    auxiliar[contador] = dato;
                    contador++;
                }else if(posibleMenor==menor){
                    Matriz dato = new Matriz();
                    dato.setFilaNueva(posibleFila);
                    dato.setColumnaNueva(posibleColumna);
                    auxiliar[contador] = dato;
                    contador++;
                }
            }
        }
        
        soluciones = new Matriz[contador];            //se pide memoria para el vector Solucion con el tamaño de la información verdaderamente importante.
        for(int i = 0; i<contador; i++){               //se recorre desde 0 hasta el número de datos que nos interesan
            soluciones[i] = auxiliar[i];                //se realiza una copia solo de los datos que nos interesan dentro del vector auxiliar.
        }
        
        return soluciones;                              //se retorna el vector con las soluciones posibles.
      
    }
}
