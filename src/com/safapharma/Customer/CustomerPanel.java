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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Natasha Malik
 */
public class CustomerPanel extends MainScreenPanel {

    private MainWindow manager;
    private JTable customerTable;
    private DefaultTableModel tableModel;
    private final CustomerBackend customerBackend;
    private TableRowSorter sorter;
    private DataWithColumn dataWithColumn;
    Vector<Object> selectedObject;

    public CustomerPanel(MainWindow manager) {

        this.manager = manager;
        customerBackend = new CustomerBackend();
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
        addButton.setText("Add Customer");
        viewButton.setText("View Customer");
        updateButton.setText("Update Customer");
        removeButton.setText("Remove Customer");
        
        tableModel = new DefaultTableModel();
        customerTable = new JTable(tableModel);
        sorter = new TableRowSorter(tableModel);
        customerTable.setRowSorter(sorter);
        customerTable.getTableHeader().setResizingAllowed(false);
        customerTable.getTableHeader().setFont(DesignConstants.FONT_SIZE_14_CALIBRI_BOLD);
        customerTable.setFont(DesignConstants.FONT_SIZE_14_CALIBRI);
        customerTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        getTableScrollPane().setViewportView(customerTable);

    }

    private void loadData() throws Exception {
  dataWithColumn = customerBackend.setCustomerInfoIntoTable(customerTable, tableModel);
        tableModel.setDataVector(dataWithColumn.getData(), dataWithColumn.getColumnNames());
    }

    private void setListeners() {
        customerTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                enableButtons();
//                int x=customerTable.getSelectedRow();
                
                //Object ob=dataWithColumn.getDataOf(x);
//                selectedObject=dataWithColumn.getDataOf(x);
                
            }

        });

        //on add button click
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //create new form
                    manager.createNewCustomerForm(customerBackend); //the appropriate function call
                    manager.showNewCustomerForm();
                } catch (Exception ex) {
                    Logger.getLogger(HomeScreenPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        searchBox.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(searchBox.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(searchBox.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(searchBox.getText());
            }

            public void search(String str) {
                if (str.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" +str));
                }
            }
        });

        
        
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //create new form
         int selectedRowIdx = customerTable.getSelectedRow();
         int rowIdx = (Integer)tableModel.getValueAt(selectedRowIdx, 0);
         Vector<Object> obj = dataWithColumn.getDataOf(rowIdx);      
                    int id = dataWithColumn.getIdOf(rowIdx);
                    manager.createViewCustomerForm(customerBackend,obj,id); //the appropriate function call
                    manager.showViewCustomerForm();
                } catch (Exception ex) {
                    Logger.getLogger(HomeScreenPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        );
          
        
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //create new form
                    manager.createUpdateCustomerForm(customerBackend); //the appropriate function call
                    manager.showUpdateCustomerForm();
                } catch (Exception ex) {
                    Logger.getLogger(HomeScreenPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        );
        
        
          customerTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                enableUpdateButtons();
                enableRemoveButtons();
                enableViewButtons();
            }

        });
          
          

    }
}
