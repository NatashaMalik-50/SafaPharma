/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Customer;

import com.safapharma.Helpers.DesignConstants;
import com.safapharma.Home.HomeScreenPanel;
import com.safapharma.Main.MainWindow;
import com.safapharma.ModelObjects.DataWithColumn;
import com.safapharma.Templates.MainScreenPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Natasha Malik
 */
public class CustomerPanel extends MainScreenPanel{
    private MainWindow manager;
    private JTable customerTable;
    private DefaultTableModel tableModel;
    private final CustomerBackend CustomerBackend;

    public CustomerPanel(MainWindow manager) {
        
        this.manager = manager;
        CustomerBackend = new CustomerBackend();
        initUI();
        setListeners();
        
    }

    private void initUI() {

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                loadData();
                return null;
            }
        }.execute();

        tableModel = new DefaultTableModel();
        customerTable = new JTable(tableModel);
        customerTable.getTableHeader().setResizingAllowed(false);
        customerTable.getTableHeader().setFont(DesignConstants.FONT_SIZE_14_CALIBRI_BOLD);
        customerTable.setFont(DesignConstants.FONT_SIZE_14_CALIBRI);
        customerTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        customerTable.setAutoCreateRowSorter(true);
        getTableScrollPane().setViewportView(customerTable);

    }

    private void loadData() throws Exception {
        DataWithColumn dataWithColumn = CustomerBackend.setCustomerInfoIntoTable(customerTable, tableModel);
        tableModel.setDataVector(dataWithColumn.getData(), dataWithColumn.getColumnNames());
    }

    private void setListeners() {
        customerTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                enableButtons();
            }

        });
        
        //on add button click
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //create new form
                    manager.createNewCustomerForm(CustomerBackend); //the appropriate function call
                    manager.showNewCustomerForm();
                } catch (Exception ex) {
                    Logger.getLogger(HomeScreenPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    
    }}