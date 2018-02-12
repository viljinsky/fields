/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.viljinsky.fields;

import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 *
 * @author viljinsky
 */
public interface FieldControl{
    public void setValue(Object value);
    public Object getValue();
    public JComponent getComponent();
    public String getName();
}


class IntegerField extends JTextField implements FieldControl{

    public IntegerField(String name) {
        setMaximumSize(new Dimension(Integer.MAX_VALUE,24));
        setName(name);
    }

    @Override
    public void setValue(Object value) {
        if (value == null)
            setText("");
        else 
            setText(value.toString());
    }

    @Override
    public Object getValue() {
        if (getText().trim().isEmpty())
            return null;
        return Integer.valueOf(getText().trim());
    }

    @Override
    public JComponent getComponent() {
        return this;
    }
}

class DoubleField extends JTextField implements FieldControl{

    public DoubleField(String name) {
        setMaximumSize(new Dimension(Integer.MAX_VALUE,24));
        setName(name);
    }
        
    @Override
    public void setValue(Object value) {
        if (value==null)
            setText("");
        setText(value.toString());
    }

    @Override
    public Object getValue() {
        if (getText().trim().isEmpty())
            return null;
        return Double.valueOf(getText());        
    }

    @Override
    public JComponent getComponent() {
        return this;
    }
}

class TextField extends JTextField implements FieldControl{

    public TextField(String FieldName) {
        setMaximumSize(new Dimension(Integer.MAX_VALUE,24));
        setName(FieldName);
    }
    
    @Override
    public void setValue(Object value) {
        if (value != null)
            setText(value.toString());
    }

    @Override
    public Object getValue() {
        if (getText().trim().isEmpty())
            return null;
        return getText();
    }

    @Override
    public JComponent getComponent() {
        return this;
    }
}


