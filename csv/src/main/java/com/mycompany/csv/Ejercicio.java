/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csv;




import java.io.*;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;





public class Ejercicio {
    
     public static void main(String[] args){
         
         Scanner sc = new Scanner(System.in);
         
         System.out.println("¿Que archivo quieres leer?");
              
         String archivo = sc.nextLine();
         
        leerArchivo(archivo);
        copiaTexto(archivo, archivo +"_histograma.csv");  
     }
    
     
     public static void leerArchivo(String origen){
         
         try(var fr = new BufferedReader(new FileReader(origen));)
         {
             String s;
                               
         while((s=fr.readLine())!=null){
             System.out.println(s);
             s=s.toUpperCase();  
         }
     }   catch (FileNotFoundException ex) {
             Logger.getLogger(Ejercicio.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(Ejercicio.class.getName()).log(Level.SEVERE, null, ex);
         }
    
    }
     
     public static void copiaTexto(String origen,String destino)
    {
      
        String[] HEADERS = { "palabra", "cantidad"};
          HashMap<String, Integer> words = new HashMap<String, Integer>();
        try( var fr = new BufferedReader(new FileReader(origen));
                  FileWriter out = new FileWriter(destino);
            CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT .withHeader(HEADERS)))
        {
            String s;
            int i = 0;
   
             while((s=fr.readLine())!=null){
             s = s.replace("\"", "");  
             s = s.replace("'", "");   
             String[] palabras = s.split("[\\s,.:()?!¿¡-]");
             for(String p: palabras){
                p=p.toLowerCase();  
                
                if(p.length() > 2){
                    System.out.println(p);
                    String palabaCandidata = p;
                    
                if (words.containsKey(palabaCandidata)){
                    words.computeIfPresent(palabaCandidata, (k,v)->v+1);
                }else{
                 words.computeIfAbsent(palabaCandidata, k->1);
                }
                }
            }             
         }
              for(String j : words.keySet()){
            System.out.println("Palabra: "+j+" Cantidad: "+words.get(j));
            printer.printRecord(j, words.get(j));
        }
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    }