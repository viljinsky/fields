/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.viljinsky.fields;

import java.util.HashMap;

/**
 *
 * @author viljinsky
 */

class Values extends HashMap<String, Object>{

    public Values() {
    }

    public Values(String key,Object value) {
        put(key, value);
    }
    
    public Values(Values values,String... keys){
        for (String key:keys){
            if (values.containsKey(key))
                put(key, values.get(key));
            else
                put(key,null);
        }
    }
    
    public boolean compare(Values values){
        if (values==null) return false;
        if (values == this) return true;
        for (String key:keySet()){
            if (!values.containsKey(key))
                return false;
            if (get(key)==null && values.get(key)!=null)
                return false;
            if (!get(key).equals(values.get(key)))
                return false;
        }
        return true;
    }
    
    public String getString(String key){
        if (!containsKey(key)) return null;
        if(get(key)==null) return null;
        return get(key).toString();
    }
    
    public Integer getInteger(String key){
        if (!containsKey(key)) return null;
        if (get(key)==null) return null;
        return (Integer)get(key);
    }
    
    public Double getDouble(String key){
        if (!containsKey(key)) return null;
        if (get(key)==null) return null;
        return (Double)get(key);        
    }
        
}


public class Main {
    public static void main(String[] args){
        FieldPanel panel = new FieldPanel();
        panel.addControls(
                new IntegerField("Field7"),
                new TextField("Field6"),
                new TextField("Field7"),
                new TextField("Field1"),
                new TextField("Field2"),
                new TextField("Field3"),
                new DoubleField("Field4"),
                new TextField("Field5")
        );
        Values values = new Values();
        values.put("Field1", "поле1");
        values.put("Field3", "поле3");
        values.put("Field4", 123.4);
        panel.setValues(values);
        
        int retVal  = panel.showModal(null,new FieldPanel.FieldPanelListener() {

            @Override
            public boolean validate(Values values) throws Exception{
//                if (values.get("Field7")==null){
//                    throw new Exception("Нужно ввести поле 7");
//                }
                Integer n = values.getInteger("Field7");
                Double d = values.getDouble("Field4");
                System.out.println(n+"  "+ d);
                return true;
            }
        });
        System.out.println(retVal);
        if (retVal == BaseDialog.RESULT_OK){
            System.out.println(panel.getValues());
            
        }
        System.exit(0);
        
    }
}
