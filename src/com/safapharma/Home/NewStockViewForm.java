/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Home;

import com.safapharma.Main.MainWindow;
import com.safapharma.Templates.DialogForm;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Natasha Malik
 */
public class NewStockViewForm extends DialogForm {

    private MainWindow manager;
    private FormLabel nameLabel;
    private FormText nameText;
    private FormLabel contactLabel;
    private FormText contactText;
    private FormButton submitButton;
    private FormButton resetButton;

    public NewStockViewForm(MainWindow manager) {
        this.manager = manager;
        initUI();
        addListeners();
    }

    private void initUI() {
        getFormLabel().setText("Create New Stock View");
        nameLabel = new FormLabel("Name");
        nameText = new FormText();
        contactLabel = new FormLabel("Contact No.");
        contactText = new FormText();
        submitButton = new FormButton("Submit");
        resetButton = new FormButton("Reset");

        getFormPanel().add(nameLabel);
        getFormPanel().add(nameText);
        getFormPanel().add(contactLabel);
        getFormPanel().add(contactText);
        getFormPanel().add(submitButton);
        getFormPanel().add(resetButton);
        this.pack();
        
    }
    
    private void addListeners(){
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

    @Override
    protected void deleteScreen() {
        manager.deleteNewStockViewForm();
    }

}
