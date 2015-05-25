/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preprocesadoronucsv.pojo;

import java.util.List;
import java.util.Map;

/**
 * Objeto java que almacena los datos para una metrica
 * 
 * @author SEBASTIAN
 */
public class MetricMap {
    
    private String metric;
    private Map<String, List<Value>> data;

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public Map<String, List<Value>> getData() {
        return data;
    }

    public void setData(Map<String, List<Value>> data) {
        this.data = data;
    }
}
