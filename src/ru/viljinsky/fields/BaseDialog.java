/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.viljinsky.fields;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author viljinsky
 */
class BaseDialog extends JDialog {
    public static final int RESULT_NONE = 0;
    public static final int RESULT_OK = 1;
    public static final int RESULT_CANCEL = 2;
    
    int modalResult = RESULT_NONE;
    
    interface DialogListener{
        public void onEnter() throws Exception;
    }
    
    DialogListener listener ;
    
    JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    Action actionOK = new AbstractAction("OK") {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (listener!=null){
                try{
                    listener.onEnter();
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                    return;
                }
            }
            modalResult =RESULT_OK;
            setVisible(false);
        }
    };
    Action actionCancel = new AbstractAction("Отмена") {
        @Override
        public void actionPerformed(ActionEvent e) {
            modalResult = RESULT_CANCEL;
            setVisible(false);
        }
    };

    public BaseDialog(Component parent, JComponent component) {
        setModal(true);
        setMinimumSize(new Dimension(400, 200));
        add(component);
        add(buttons, BorderLayout.PAGE_END);
        buttons.add(new JButton(actionOK));
        buttons.add(new JButton(actionCancel));
        pack();
        setLocationRelativeTo(component);
    }
    
}
