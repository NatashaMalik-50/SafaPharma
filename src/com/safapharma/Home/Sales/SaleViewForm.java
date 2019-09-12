/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Home.Sales;

import com.safapharma.Helpers.DesignConstants;
import com.safapharma.Main.MainWindow;
import com.safapharma.ModelObjects.DataWithColumn;
import com.safapharma.Templates.DialogForm;
import java.awt.GridLayout;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author akshit
 */
public class SaleViewForm extends DialogForm{
    
    private SalesBackend salesBackend;
    private MainWindow manager;
    private DefaultTableModel tableModel;
    private JTable viewSalesTable;
    private JScrollPane scrollPane;
    private JPanel aggregatePanel, customerDetailPanel;
    private JLabel aggregateLabel, customerNameLabel;
    private JTextField aggregateTextField, customerNameField;

    final private int sale_id;
    final private String customerName;
    public SaleViewForm(MainWindow manager, int id, String customerName) {
        this.manager = manager;
        this.sale_id = id;
        this.customerName = customerName;
        this.remove(getFormLabel());
        initUI();
    }

    private void initUI() {
        
        salesBackend = new SalesBackend();
        
        aggregatePanel = new JPanel();
        customerDetailPanel = new JPanel();
        
        aggregateLabel = new JLabel("Total Amount");
        customerNameLabel = new JLabel("Customer Name");
                
        aggregateTextField = new JTextField();
        aggregateTextField.setEditable(false);
         
        customerNameField = new JTextField();
        customerNameField.setEditable(false);
        
        aggregatePanel.setLayout(new GridLayout(1, 3));
        customerDetailPanel.setLayout(new GridLayout(1, 3));
        
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };
        scrollPane = new JScrollPane();
        viewSalesTable = new JTable(tableModel);
        
        new SwingWorker<Void, Void>() {
        
            @Override
            protected Void doInBackground() throws Exception {
                loadData();
                return null;
            }
           
        }.execute();
                
               
        viewSalesTable.getTableHeader().setResizingAllowed(false);
        viewSalesTable.getTableHeader().setFont(DesignConstants.FONT_SIZE_14_CALIBRI_BOLD);
        viewSalesTable.setFont(DesignConstants.FONT_SIZE_14_CALIBRI);
        viewSalesTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        viewSalesTable.setAutoCreateRowSorter(true);
      
        aggregatePanel.add(aggregateLabel);
        aggregatePanel.add(aggregateTextField);
        
        customerDetailPanel.add(customerNameLabel);
        customerDetailPanel.add(customerNameField);           
        customerNameField.setText(customerName);
        customerNameField.setFont(DesignConstants.FONT_SIZE_18_CALIBRI_BOLD);
        
        scrollPane.setViewportView(viewSalesTable);
        
        getFormPanel().add(customerDetailPanel);
        getFormPanel().setLayout(new BoxLayout(getFormPanel(), BoxLayout.Y_AXIS));
        getFormPanel().add(scrollPane);
        getFormPanel().add(aggregatePanel);
        this.pack();
    }

       private void loadData() throws Exception {
        DataWithColumn dataWithColumn = salesBackend.setSaleItemsInfoIntoTable(viewSalesTable, tableModel, sale_id);
        tableModel.setDataVector(dataWithColumn.getData(), dataWithColumn.getColumnNames());
        getSum();
    }
          
        public void getSum()
    {
        float sum1 = 0;
        for(int i = 0; i < viewSalesTable.getRowCount(); i++)
        { 
            try{
                sum1 = sum1 + Float.parseFloat(viewSalesTable.getValueAt(i, 4).toString());
             }
            catch(Exception e){
               java.util.logging.Logger.getLogger(SaleViewForm.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
                }
        }    
         aggregateTextField.setText(Float.toString(sum1));
         aggregateTextField.setHorizontalAlignment(JTextField.CENTER);
    }

    @Override
    protected void deleteScreen() {
        manager.deleteSaleViewForm();
    }
}
