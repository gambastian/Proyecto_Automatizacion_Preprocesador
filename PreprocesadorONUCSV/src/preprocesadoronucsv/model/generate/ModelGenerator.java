/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preprocesadoronucsv.model.generate;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import preprocesadoronucsv.pojo.MetricMap;
import preprocesadoronucsv.pojo.Value;

/**
 * Clase que toma un archivo csv y segun unos paises y metricas seleccionados
 * genera un archivo valido para la gramatica.
 *
 * @author SEBASTIAN
 */
public class ModelGenerator {

    public static void generateModelOnu(List<MetricMap> metricsMap, 
            List<String> countries, List<String> metrics) {
        System.out.println("Writting .onu file...");
        // Name of the file
        Writer writer = null;

        try {
            // File to write new lines
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("C:\\Users\\SEBASTIAN\\Documents\\UNIANDES\\Automatizacion\\Proyecto\\postProcess.onu")
                    , "utf-8"));

            String line;

            writer.write("OnuCsv{\n");
            
            //series
            writer.write("  series{\n");
            boolean moreThanOne = false;
            for(MetricMap mm : metricsMap){
                for(String country : mm.getData().keySet()){
                    for(Value v : mm.getData().get(country)){
                        if(moreThanOne){
                            writer.write("      ,\n");
                        }
                        writer.write("      Serie{\n");
                        writer.write("          countrySerie \""+ country +"\"\n");
                        writer.write("          indicatorSerie \""+ mm.getMetric() +"\"\n");
                        writer.write("          value {\n");
                        writer.write("              Value {\n");    
                        writer.write("                  value \""+ v.getVal() +"\"\n");    
                        writer.write("                  year \""+ v.getYear() +"\"\n");    
                        writer.write("              }\n");
                        writer.write("          }\n");
                        writer.write("      }\n");
                        
                        moreThanOne = true;
                    }
                }
            }
            writer.write("  }\n");
            
            // countries
            writer.write("  countries{\n");
            for (int c = 0; c < countries.size(); c++) {
                if (c == 0) {
                    writer.write("      Country \"" + countries.get(c) + "\" {}\n");
                }else{
                    writer.write("      ,Country \"" + countries.get(c) + "\" {}\n");
                }
            }
            writer.write("  }\n");
            
            // metrics
            writer.write("  indicators{\n");
            for (int m = 0; m < metrics.size(); m++) {
                if (m == 0) {
                    writer.write("      Indicator \"" + metrics.get(m) + "\" {}\n");
                }else{
                    writer.write("      ,Indicator \"" + metrics.get(m) + "\" {}\n");
                }
            }
            writer.write("  }\n");
            
            writer.write("}");

            writer.close();
        } catch (Exception e) {
            System.out.println("Error while reading file line by line:"
                    + e.getMessage());
        }
        System.out.println("FINISHED");
    }
}
