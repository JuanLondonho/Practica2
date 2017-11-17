/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Matriz;
import Modelo.MiRender;
import Modelo.Tablero;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juan Carlos Londoño - Yeison Ballesteros
 */
public class ControladorHidato {

    private int[][] v;
    private Matriz pistas;
    private Matriz[] pistasArreglo;
    private ArrayList<String> datos;
    double tiempo_inicio;
    double tiempo_final;
    
    public static int[] pintarPistas;

    public int calcularDificultad(String dificultad) {
        int nivelDificultad = 2;
        if (dificultad.equals("Fácil")) {
            nivelDificultad = 1;
        } else if (dificultad.equals("Difícil")) {
            nivelDificultad = 3;
        }
        return nivelDificultad;

    }

    public void crearTablero(JTextField txtNumeroFilas,
            JTextField txtNumeroColumnas, String cbxDificultad,
            JTable tblTablero, JButton btnSolucion, JComboBox cbxFormato, JButton btnGuardar) {
        tiempo_inicio = System.currentTimeMillis(); //se calcula el tiempo donde se inicia la creación del tablero
        try {  //acá se obtienen todos los datos ingresados por el usuario y se valida según se requiere el funcionamiento
            int dificultad = calcularDificultad(cbxDificultad);
            int numeroFilas = Integer.parseInt(txtNumeroFilas.getText());
            int numeroColumnas = Integer.parseInt(txtNumeroColumnas.getText());
            int numeroPistas = (numeroColumnas - 1) * (dificultad) / 2;   //se describe el número de pistas mediante esta formula, si la dificultad es mayor, se le marcarán mas pistas
            if (numeroFilas > numeroColumnas) {                        //con el fin de hacerle el camino más restringido. Para aplicar la formula se tomará el que sea mayor
                numeroPistas = (numeroFilas - 1) * dificultad / 2;       //entre el número de filas y número de columnas.
            }
            datos = new ArrayList<>();
            datos.add(numeroFilas + " " + numeroColumnas + " " + dificultad + " " + numeroPistas);
            if (numeroFilas > 3 && numeroFilas < 91 && numeroColumnas > 3 && numeroColumnas < 91) {  //se define un rango mínimo y máximo del tablero.
                if (dificultad == 3 && (numeroFilas < 7 || numeroColumnas < 7)) {
                    JOptionPane.showMessageDialog(null, "No se puede configurar esta dificultad \n"
                            + "con el tamaño indicado, minimo 7x7");
                } else {
                    Tablero tablero = new Tablero(numeroFilas, numeroColumnas); //se crea el tablero con el tamaño indicado por el usuario
                    v = tablero.crear(); //                                     //se genera la matriz que corresponderá al tablero.
                    mostrarMatriz(v, numeroFilas, numeroColumnas, dificultad, tblTablero, numeroPistas); // acá se muestra el tablero generado al usuario.
                    btnSolucion.setVisible(true);       //Se hacen visibles los botones que serán de utilidad para los demás requisitos.
                    btnGuardar.setVisible(true);
                    cbxFormato.setVisible(true);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Se encuentra fuera del rango");
            }
        } catch (HeadlessException | NumberFormatException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Campos vacíos");
        }
    }

    public void mostrarMatriz(int matriz[][], int m, int n, int dificultad, JTable tblTablero, int numPistas) {
        pintarPistas = null;
        MiRender miRender = new MiRender(m, n);
        tblTablero.setDefaultRenderer(Object.class, miRender);
        int contador = 0;
        int filaAleatoria;
        int columnaAleatoria;
        Random x = new Random();
        DefaultTableModel model = (DefaultTableModel) tblTablero.getModel(); //se obtiene el modelo de laa tabla
        model.setColumnCount(n);              //se le asigna el número de columnas ingresadas por el usuario
        model.setRowCount(m);                 //se le asigna el número de filas ingresadas por el usuario
        for (int i = 0; i < matriz.length; i++) { //Limpiar tabla
            for (int j = 0; j < matriz[0].length; j++) {
                tblTablero.setValueAt("", i, j);
            }
        }
        for (int i = 0; i < matriz.length; i++) { // LLenar tabla con los nuevos datos
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j] == m * n || matriz[i][j] == 1) { //se pregunta por el primero y el último para marcarlos dentro del tablero
                    tblTablero.setValueAt(matriz[i][j], i, j);  // se le asigna a laa tabla el valor correspondiente a esaa fila y columna
                    datos.add((i) + " " + (j) + " " + matriz[i][j]); // se le suma 1 al almacenarlo en el txt para que no quede desde el 0 si no desde el 1 y sea mas agradabale ala vista
                } else if (contador < numPistas) {            //asigno el número de pistas de forma aleatoria con valores dentro de la matriz.
                    filaAleatoria = x.nextInt(m);
                    columnaAleatoria = x.nextInt(n);
                    if (matriz[filaAleatoria][columnaAleatoria] != m * n && matriz[filaAleatoria][columnaAleatoria] != 1) {
                        tblTablero.setValueAt(matriz[filaAleatoria][columnaAleatoria], filaAleatoria, columnaAleatoria);
                        datos.add((filaAleatoria) + " " + (columnaAleatoria) + " " + matriz[filaAleatoria][columnaAleatoria]); //se guarda ésta pistaa en el array que será el txt
                        contador++;
                    }
                }
            }
        }
        tiempo_final = System.currentTimeMillis(); //se calcula el tiempo final, es decir, cuando termina de realizar todo el proceso
        double tiempo_total = (tiempo_final - tiempo_inicio) / 1000;        //se calcula el tiempo total en segundos
        datos.add(tiempo_total + " seconds"); //se almacena de ultimo el dato del tiempo en segundos dentro del arraay para luego pasar al txt
    }

    public int[][] getV() {
        return v;
    }

    public void advertenciaStackOverFlow() {
        String advertencia = "El generador de tableros garantiza instancias correctas en\n"
                + "cualquier ordenador de hasta 50x50, en adelante, la generación\n"
                + "de instancias correctas dependerá del ordenar y de el tamaño\n"
                + "de la pila que se le asigne a java. ";
        JOptionPane.showMessageDialog(null, advertencia);
    }

    public ArrayList<String> getDatos() {
        return datos;
    }

    public boolean mostrarMatriz(JTable tablero, ArrayList<String> arrayDatos) {
        try{
            if(arrayDatos == null){
                return false;
            }
            DefaultTableModel model = (DefaultTableModel) tablero.getModel();
            String str = arrayDatos.get(0);
            String[] datos = str.split(" ");
            int m = Integer.parseInt(datos[0]);
            int n = Integer.parseInt(datos[1]);



            this.datos = new ArrayList<>();
            this.datos.add(m +" "+ n +" "+ datos[2] +" "+ datos[3]);
            pintarPistas = new int[Integer.parseInt(datos[3]+2)];
            v = new int[Integer.parseInt(datos[0])][Integer.parseInt(datos[1])];
            pistasArreglo = new Matriz[Integer.parseInt(datos[3]+2)];



            ControladorArrayList.ordenarPorDato(arrayDatos);

            model.setRowCount(Integer.parseInt(datos[0]));
            model.setColumnCount(Integer.parseInt(datos[1]));
            for (int i = 0; i < Integer.parseInt(datos[0]); i++) { //Limpiar tabla
                for (int j = 0; j < Integer.parseInt(datos[1]); j++) {
                    tablero.setValueAt("", i, j);
                    v[i][j] = 0;
                }
            }

            for (int i = 1; i < arrayDatos.size() - 1; i++) {
                str = arrayDatos.get(i);
                datos = str.split(" ");

                pistas = new Matriz();
                pistas.setColumnaNueva(Integer.parseInt(datos[1]));
                pistas.setFilaNueva(Integer.parseInt(datos[0]));
                pistas.setDato(Integer.parseInt(datos[2]));
                pintarPistas[i-1] = Integer.parseInt(datos[2]);
                pistasArreglo[i-1] = pistas;
                tablero.setValueAt(Integer.parseInt(datos[2]), Integer.parseInt(datos[0]), Integer.parseInt(datos[1]));
                v[Integer.parseInt(datos[0])][Integer.parseInt(datos[1])] = Integer.parseInt(datos[2]);
            }

            MiRender miRender = new MiRender(m, n);
            tablero.setDefaultRenderer(Object.class, miRender);
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "El formato no es compatible o está mal estructurado");
            return false;
        }
        
    }
    
    public void resolverHidato(JTable tablero, JButton botonGuardar, JComboBox cbxOpcion){
        Tablero tbl = new Tablero();
        tiempo_inicio = System.currentTimeMillis();
        int[][]solucion = tbl.solucionar(v, pistasArreglo);
        tiempo_final = System.currentTimeMillis();
        double tiempo_total = (tiempo_final - tiempo_inicio) / 1000;
        
        if (solucion != null){
            for(int i = 0; i < solucion.length; i++){
                for(int j = 0 ; j < solucion[0].length; j++){
                    tablero.setValueAt(solucion[i][j], i, j);
                    datos.add(i+" "+ j +" "+solucion[i][j]);
                }
            }
            datos.add(tiempo_total+"Seconds");
            botonGuardar.setVisible(true);
            cbxOpcion.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "El hidato no tiene solución");
        }
    }

    public Matriz[] getPistasArreglo() {
        return pistasArreglo;
    }

}
