/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.viljinsky.fields;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author viljinsky
 */
class FieldPanel extends JComponent {

    public interface FieldPanelListener {

        public boolean validate(Values values) throws Exception;
    }
    
    Map<String, FieldControl> map = new HashMap<>();

    public FieldPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(12, 6, 12, 6));
    }

    private void addControl(FieldControl control) {
        Box box = Box.createHorizontalBox();
        box.add(new JLabel(control.getName()));
        box.add(Box.createHorizontalStrut(6));
        box.add(control.getComponent());
        add(box);
        add(Box.createVerticalStrut(6));
        map.put(control.getName(), control);
    }

    public void addControls(FieldControl... controls) {
        for (FieldControl control : controls) {
            if (map.containsKey(control.getName()))
                System.err.println("cntrol "+control.getName()+" already exists");
            else
                addControl(control);
        }
    }

    public void setValues(Values values) {
        for (String fieldName : values.keySet()) {
            if (map.containsKey(fieldName)) {
                map.get(fieldName).setValue(values.get(fieldName));
            }
        }
    }

    public Values getValues() {
        Values values = new Values();
        for (String fieldName : map.keySet()) {
            values.put(fieldName, map.get(fieldName).getValue());
        }
        return values;
    }

    public Integer showModal(Component parent) {
        BaseDialog dlg = new BaseDialog(parent, this);
        dlg.setVisible(true);
        return dlg.modalResult;
    }

    public Integer showModal(Component parent, FieldPanelListener validate) {
        BaseDialog baseDialog = new BaseDialog(parent, this);
        baseDialog.listener = () -> {
            Values values = getValues();
            validate.validate(values);
        };
        baseDialog.setVisible(true);
        return baseDialog.modalResult;
    }
    
}
