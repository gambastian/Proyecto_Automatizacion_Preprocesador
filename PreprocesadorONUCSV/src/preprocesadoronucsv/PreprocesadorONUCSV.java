/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preprocesadoronucsv;

import com.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author cd.montanez10
 */
public class PreprocesadorONUCSV {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
        
        
        /*CSVReader reader;
        String [] nextLine=null;
        int campo = 0;
        reader = new CSVReader(new InputStreamReader(new FileInputStream("C:\\ONU_Data.csv"), "UTF-8"),',', '\"', 0);
                
        try {
            while ((nextLine = reader.readNext()) != null) {
                
                for (campo = 0; campo <nextLine.length; campo++){
                    System.out.println(nextLine[campo]);
                }   
            }
        } catch (IOException ex) {
            Logger.getLogger(PreprocesadorONUCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
               
        try {
            reader.close();
        }catch (IOException e) {
           reader = null;
        }*/
        int seleccion=0;
        JFileChooser fileChooser = new JFileChooser();
        if (args.length==0){
            FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV", "csv");
            fileChooser.setFileFilter(filter);
            seleccion = fileChooser.showOpenDialog(null);
        }
        
        if (seleccion == JFileChooser.APPROVE_OPTION || args.length>0){
            File archivo;
            if (args.length>0){
                    archivo = new File(args[0]);
            } else{
                    archivo = fileChooser.getSelectedFile();
            }
            FileWriter fw = null;
            PrintWriter pw = null;
            
            FileReader fr = null;
            BufferedReader br = null;

            try {
                fw = new FileWriter(archivo.getPath()+".temp");
                pw = new PrintWriter(fw);
                fr = new FileReader (archivo);
                br = new BufferedReader(fr);

                // Lectura del archivo
                String linea;
                
                
                while((linea=br.readLine())!=null){
                    linea=remove1(linea);
                    pw.println(linea);
                }
                
            }
            catch(Exception e){
                e.printStackTrace();
            }finally{
                //Cerramos el archivo
                try{                    
                    if( null != fr ){   
                    fr.close();     
                    }
                    if (null != fw)
                    fw.close();
                }catch (Exception e2){ 
                    e2.printStackTrace();
                }
                File realName = new File(archivo.getPath());
                realName.delete(); // remove the old file
                File nuevo = new File(archivo.getPath()+".temp");
                nuevo.renameTo(realName);
                
                JOptionPane.showMessageDialog(
                    null,
                    "Preprocesado con éxito");
            }
   
        }
    }
 
    /**
    * Función que elimina acentos y caracteres especiales de
    * una cadena de texto.
    * @param input
    * @return cadena de texto limpia de acentos y caracteres especiales.
    */
   public static String remove1(String input) {
       // Cadena de caracteres original a sustituir.
       String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
       // Cadena de caracteres ASCII que reemplazarán los originales.
       String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
       String output = input;
       for (int i=0; i<original.length(); i++) {
           // Reemplazamos los caracteres especiales.
           output = output.replace(original.charAt(i), ascii.charAt(i));
       }//for i
       return output;
   }
    
}
