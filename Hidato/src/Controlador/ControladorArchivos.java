/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.Container;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Juan Carlos Londoño - Yeison Ballesteros
 */
public class ControladorArchivos {
    
    /*
    *este metodo guardar, se encarga de revisar en que formato se pide y
    *según la que se cumpla se llevará acabo el proceso de guardar
    *donde el usuario lo indique.
    */
    public void guardar(JTable tblTablero, String formato, ControladorHidato ctrlHidato) throws IOException{
        int seleccion;                  //guardará la ruta que seleccionó el usuario
        if(formato.equalsIgnoreCase("png")){    //se pregunta por el tipo de formato que eligió el usuario.
            JFileChooser jfc;                   //se usará para almacenar la carpeta que se seleccione
            File f;                             //será el nuevo fichero que crearemos        
            String ruta;                        //obtiene la ruta donde se almacenará el fiechero
            String nombre;                      //almacena el nombre que se le desea poner al archivo

            jfc = new JFileChooser();
            seleccion = jfc.showSaveDialog(null);   //guarda la elección que hizo el usuario en la ventana

            if (seleccion == JFileChooser.APPROVE_OPTION) { //
                f = jfc.getCurrentDirectory();              //se obtiene el directorio actual
                ruta = f.getPath();                         //del directorio se obtiene la ruta.
                nombre = jfc.getSelectedFile().getName();   //guarda el nombre que escribió el usuario para el aarchivo
                f = new File(ruta+"/" + nombre + ".png");   //se crea un nuevo archivo con los valores antes adquiridos
                if (f.exists()) {                           //pregunta si esta carpeta ya existe en esa misma ruta
                    seleccion = JOptionPane.showConfirmDialog(null, "El archivo \"" + nombre + "\" ya existe, ¿Desea sobreescribirlo?");
                    if (seleccion != JOptionPane.OK_OPTION) {
                        return;
                    }
                }
                Container c = tblTablero;       //se crea un contenedor  al cual se le asigna un componente(La tabla) 
                BufferedImage im = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_ARGB); //se crea una imagen con las medidas de la tabla
                c.paint(im.getGraphics());          //se pinta la imagen en el componente antes declarado
                ImageIO.write(im, "PNG", new File(ruta+"/"+nombre+".png"));  //se guarda la imagen en la ruta especificada
                JOptionPane.showMessageDialog(null, "Se guardó con éxito");
                }
                
        }else if(formato.equalsIgnoreCase("txt")){ //se pregunta si la opción que seleccionó el usuario es txt y se realiza prácticamente lo anterior
            JFileChooser jfc;
            File f;
            String ruta;
            String nombre;

            jfc = new JFileChooser();
            seleccion = jfc.showSaveDialog(null);

            if (seleccion == JFileChooser.APPROVE_OPTION) {
                f = jfc.getCurrentDirectory();
                ruta = f.getPath();
                nombre = jfc.getSelectedFile().getName();
                //se envía como parametro un array(ctrlHidato.getDatos()) en el cual se almacenaron los datos pedidos.     
                crearArchivo(nombre, ruta, ctrlHidato.getDatos()); 
                JOptionPane.showMessageDialog(null, "Se guardó con éxito");
            }
        }else {
            JOptionPane.showMessageDialog(null, "Elija un formato");
        }
    }
   
    
    public static boolean existe(File f) {
        return (f.exists());
    }
    
    
    public static void crearArchivo(String nombre, String ruta, ArrayList<String> contenido) { //se crea el archivo con los datos pedidos en la práctica.
        BufferedWriter bw;
        FileWriter fw;
        String fileName;
        File f;

        bw = null;
        fw = null;
        fileName = ruta + "/" + nombre+".txt";
        f = new File(fileName);

        // Controlar si el nombre del archivo seleccionado ya existe
        if (f.exists()) {
            int seleccion;
            seleccion = JOptionPane.showConfirmDialog(null, "El archivo \"" + nombre + "\" ya existe, ¿Desea sobreescribirlo?");
            if (seleccion != JOptionPane.OK_OPTION) {
                return;
            }
        }

        try {
            fw = new FileWriter(fileName);
            bw = new BufferedWriter(fw);
                
            for (String str : contenido) { // se recorre el array para escribir en el archivo los datos que se encuentran en él.
                bw.write(str + "\n");
            }

        } catch (IOException e) {
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
                
            } catch (IOException ex) {
            }
        }
    }
    
    public static ArrayList<String> cargarArchivo() {
        JFileChooser jfc;
        int seleccion;

        jfc = new JFileChooser();
        seleccion = jfc.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            try {
                File file = jfc.getSelectedFile();
                return cargarDatos(file);
            } catch (IOException ex) {
                Logger.getLogger(ControladorArchivos.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }
        return null;
    }
    
    public static ArrayList<String> cargarDatos(File inputFile) throws IOException {
        if (inputFile == null) {
            throw new IOException("No se seleccionó un archivo");
        }

        ArrayList<String> datos;
        BufferedReader reader;
        String cadena;

        datos = new ArrayList<>();

        if (inputFile.exists()) { // if (file != null)
            reader = new BufferedReader(new FileReader(inputFile));

            while ((cadena = reader.readLine()) != null) {
                datos.add(cadena);
            }

            reader.close();
        }
        return datos;
    }
   

}
