package Modelo;

/**
 *
 * @author Juan Carlos Londoño - Yeison Ballesteros
 */
public class Matriz {
    private int filaNueva;
    private int columnaNueva;
    private int dato;
    public Matriz(){
        
    }

    public int getDato(){  
        return dato;
    }

    /*
    Éste metodo se encarga de recorrer
    una matriz daba y almacenrla un String
    para luego ser mostrada en un JOptionPane,
    es usado para mostrar la posible solución.
     */
    public void setDato(int dato) {  
        this.dato = dato;
    }

    public String imprimirMatriz(int[][] matriz) {
        String mostrarMatriz = "";
        for(int i = 0; i < matriz.length; i++){
            for(int j = 0; j<matriz[0].length; j++){
                if(matriz[i][j]<=9){
                    mostrarMatriz+= matriz[i][j]+"         ";  //Los espacios evita que cuando un número sea de 2, 3 o 4 digitos
                }else if(matriz[i][j]<=99){                  //se corran los demás y se vea muy desordenado.
                    mostrarMatriz+= matriz[i][j]+"       ";
                }else if (matriz[i][j]<=999){
                    mostrarMatriz+= matriz[i][j]+"     ";
                }else {
                    mostrarMatriz+= matriz[i][j]+"   ";
                }
            }
            mostrarMatriz+="\n"; //Salto de linea cada que termine unaa columna
        }
        return mostrarMatriz;
    }

    public int getFilaNueva() {
        return filaNueva;
    }

    public void setFilaNueva(int filaNueva) {
        this.filaNueva = filaNueva;
    }

    public int getColumnaNueva() {
        return columnaNueva;
    }

    public void setColumnaNueva(int columnaNueva) {
        this.columnaNueva = columnaNueva;
    }
    
}
