/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Stock;

import com.safapharma.Home.*;
import com.safapharma.Helpers.DesignConstants;
import com.safapharma.Main.MainWindow;
import com.safapharma.ModelObjects.DataWithColumn;
import com.safapharma.ModelObjects.Medicine;
import com.safapharma.ModelObjects.MedicineLot;
import com.safapharma.ModelObjects.StockEntry;
import com.safapharma.ModelObjects.Supplier;
import com.safapharma.Templates.CustomDefaultTableModel;
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
public class StockPanel extends MainScreenPanel {

    private MainWindow manager;
    private JTable stockTable;
    private CustomDefaultTableModel tableModel;
    private final StockBackend stockBackend;
    private TableRowSorter sorter;
    
    private Vector<MedicineLot> lotIdBatchList;
    private Vector<Supplier> supplierIdNameList;
    private Vector<Medicine> medicineIdNameList;
    private Vector<StockEntry> stockEntryList;
    
    DataWithColumn dataWithColumn;
    Vector<Object> selectedObject;
    Vector fullList;

    public StockPanel(MainWindow manager) {
        this.manager = manager;
        stockBackend = new StockBackend();
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

        tableModel = new CustomDefaultTableModel();
        stockTable = new JTable(tableModel);
        sorter = new TableRowSorter(tableModel);
        stockTable.setRowSorter(sorter);
        stockTable.getTableHeader().setResizingAllowed(false);
        stockTable.getTableHeader().setFont(DesignConstants.FONT_SIZE_14_CALIBRI_BOLD);
        stockTable.setFont(DesignConstants.FONT_SIZE_14_CALIBRI);
        stockTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        getTableScrollPane().setViewportView(stockTable);

    }
    private void setFullList(){
        fullList = dataWithColumn.getData();
    }

    private void loadData() throws Exception {
        dataWithColumn = stockBackend.setStockInfoIntoTable(stockTable, tableModel);
        tableModel.setDataVector(dataWithColumn.getData(), dataWithColumn.getColumnNames());
        setFullList();
    }
    
    private void setDataToVectors(){
//        lotIdBatchList;
//        supplierIdNameList;
//    medicineIdNameList;
//    stockEntryList;
    }
    
    private void deleteFullRow(DefaultTableModel model, JTable stockTable){
        int selectedRow = stockTable.getSelectedRow();
        model.removeRow(selectedRow);
        stockTable.getValueAt(selectedRow, 2);        
    }

    private void setListeners() {
        stockTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                enableButtons();
                int x = stockTable.getSelectedRow();
//                int y = supplierTable.getSelectedColumn();
//                
//                Object ob = tableModel.getDataVector().elementAt(supplierTable.getSelectedRow());
//                fullList = dataWithColumn.getData();
                Object ob= dataWithColumn.getDataOf(x);
                selectedObject = dataWithColumn.getDataOf(x);
                System.out.println("We are getting the selected data"+selectedObject);
            }

        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    manager.createNewStockForm();
                    manager.showNewStockForm();
                } catch (Exception ex) {
                    Logger.getLogger(StockPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Remove Button Called");                
                DefaultTableModel model = (DefaultTableModel) stockTable.getModel();
                int numRows = stockTable.getSelectedRows().length;
                for (int i = 0; i < numRows; i++) {
                    deleteFullRow(model, stockTable);
                }
            }
        });
        
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("View Stock Button Called");                          
                    manager.createViewStockForm(selectedObject);
                    manager.showViewStockForm();                  
                } catch (Exception ex) {
                    Logger.getLogger(HomeScreenPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("Update Stock Button Called");
                    manager.createUpdateStockForm(selectedObject);
                    manager.showUpdateStockForm();                  
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
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + str));
                }
            }
        });
    }
    @Override
    protected void addAlerts() {
        manager.viewExpiredMedicineThroughStatusPanel();
    }
}
