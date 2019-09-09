/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Home;

import com.safapharma.Helpers.DesignConstants;
import com.safapharma.Main.MainWindow;
import com.safapharma.Templates.DialogForm;
import java.awt.Frame;
import com.safapharma.Home.HomeScreenPanel;
import com.safapharma.ModelObjects.DataWithColumn;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Natasha Malik
 */
public class NewStockViewForm extends DialogForm{
    
    HomeScreenPanel ob;
    private MainWindow manager;
    private JTable stockTable;
    private FormLabel nameLabel;
    private FormText nameText;
    private FormLabel contactLabel;
    private FormText contactText;
    private FormButton submitButton;
    private FormButton resetButton;
    private DefaultTableModel tableModel;
    private final NewStockViewFormBackend newStockViewFormBackend;

        

    public NewStockViewForm(MainWindow manager) {
        this.manager = manager;
        newStockViewFormBackend = new NewStockViewFormBackend();
        initUI();
        //addListeners();
    }

    private void initUI() {
       // getFormLabel().setText("Create New Stock View");
        /*nameLabel = new FormLabel("Name");
        nameText = new FormText();
        contactLabel = new FormLabel("Contact No.");
        contactText = new FormText();*/
        submitButton = new FormButton("Submit");
        resetButton = new FormButton("Reset");
        
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                loadData();
                return null;
            }
        }.execute();
        getFormLabel().setLayout(new GridLayout(0, 2));
        /*getFormPanel().add(nameLabel);
        getFormPanel().add(nameText);
        getFormPanel().add(contactLabel);
        getFormPanel().add(contactText);*/
        
        getFormLabel().add(submitButton);
        getFormLabel().add(resetButton);
        
        tableModel = new DefaultTableModel();
        stockTable = new JTable(tableModel);
        stockTable.getTableHeader().setResizingAllowed(false);
        stockTable.getTableHeader().setFont(DesignConstants.FONT_SIZE_14_CALIBRI_BOLD);
        stockTable.setFont(DesignConstants.FONT_SIZE_14_CALIBRI);
        stockTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        stockTable.setAutoCreateRowSorter(true);
        getFormPanel().setLayout(new GridLayout(0, 1));
        //getFormPanel().
        getFormPanel().add(stockTable);
        this.pack();
        
    }
    private DataWithColumn loadData() throws Exception {
        DataWithColumn dataWithColumn = newStockViewFormBackend.setStockInfoIntoTable(stockTable, tableModel);
        tableModel.setDataVector(dataWithColumn.getData(), dataWithColumn.getColumnNames());
        
        return dataWithColumn;
    }
    /*private void addListeners(){
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
    }*/

}
