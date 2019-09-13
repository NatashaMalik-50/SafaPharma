/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Stock;

import com.safapharma.Main.MainWindow;
import com.safapharma.MedicineLot.MedicineLotBackend;
import com.safapharma.Templates.DialogForm;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author Natasha Malik
 */
public class NewStockForm extends DialogForm {

    private MainWindow manager;
    private JPanel upperPanel;
    private JPanel lowerPanel;
    
    private MedicineLotBackend medicineLotBackend;

    public NewStockForm(MainWindow manager) {
        this.manager = manager;
        initUI();
        addListeners();
    }

    private void initUI() {
        getFormLabel().setText("Add New Stock");
        
        medicineLotBackend = new MedicineLotBackend();
        
        
        upperPanel = new JPanel(new FlowLayout());
        
        
        lowerPanel = new JPanel(new FlowLayout());
        
        
        getFormPanel().add(upperPanel);
        getFormPanel().add(lowerPanel);
        
        this.pack();

    }

    private void addListeners() {
        
    }

    @Override
    protected void deleteScreen() {
        manager.deleteNewStockForm();
    }

}
