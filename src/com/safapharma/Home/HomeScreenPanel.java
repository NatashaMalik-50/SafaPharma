/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Home;

import com.safapharma.Helpers.DesignConstants;
import com.safapharma.Main.MainWindow;
import com.safapharma.ModelObjects.DataWithColumn;
import com.safapharma.Templates.CustomDefaultTableModel;
import com.safapharma.Templates.MainScreenPanel;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
public class HomeScreenPanel extends MainScreenPanel {

    private MainWindow manager;
    private JTable billEntriesTable;
    private DefaultTableModel tableModel;
    private final HomeScreenBackend homeScreenBackend;
    private TableRowSorter sorter;
    private DataWithColumn stockData;
    private final HomeScreenPanel thisHome;

    public HomeScreenPanel(MainWindow manager) {
        this.manager = manager;
        this.thisHome = this;
        homeScreenBackend = new HomeScreenBackend();
        initUI();
        setListeners();
    }

    private void initUI() {

        

        tableModel = new CustomDefaultTableModel();
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                loadStockData();
                return null;
            }
        }.execute();
        billEntriesTable = new JTable(tableModel);
        sorter = new TableRowSorter(tableModel);
        billEntriesTable.setRowSorter(sorter);
        billEntriesTable.getTableHeader().setResizingAllowed(false);
        billEntriesTable.getTableHeader().setFont(DesignConstants.FONT_SIZE_14_CALIBRI_BOLD);
        billEntriesTable.setFont(DesignConstants.FONT_SIZE_14_CALIBRI);
        billEntriesTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        getTableScrollPane().setViewportView(billEntriesTable);
        

    }

    private void loadStockData() throws Exception {
        stockData = homeScreenBackend.setStockInfoIntoTable();             
        tableModel.setDataVector(null, stockData.getColumnNames());
    }

    private void setListeners() {
//        billEntriesTable.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                enableAddButtons();
//                enableUpdateButtons();
//                enableRemoveButtons();
//                enableViewButtons();
//            }
//
//        });

        //on add button click
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(stockData==null){
                        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        loadStockData();
                        setCursor(Cursor.getDefaultCursor());
                    }
                    manager.createNewStockViewForm(thisHome);
                    manager.showNewStockViewForm();
                } catch (Exception ex) {
                    Logger.getLogger(HomeScreenPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                   int id = billEntriesTable.getSelectedRow();
                   System.out.println(id);
                   //billEntriesTable.remove(id);
                   if(billEntriesTable.getSelectedRow()>=0)
                   {
                    DefaultTableModel model = (DefaultTableModel) billEntriesTable.getModel();
                    model.removeRow(id);
                    if(billEntriesTable.getRowCount()>=0)
                    {
                       disableRemoveButtons();
                       disableUpdateButtons();
                       disableViewButtons();
                    }
                   }
                   else
                   {
                       disableRemoveButtons();
                       disableUpdateButtons();
                       disableViewButtons();
                   }
                } catch (Exception ex) {
                    Logger.getLogger(HomeScreenPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        billEntriesTable.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e)
            {
                enableRemoveButtons();
                enableUpdateButtons();
                enableViewButtons();
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
    }
    
    public DataWithColumn getStockData() {
        return stockData;
    }
    
    public void addRow(int rowIndex){
        System.out.println("found row : "+rowIndex+ " with value"+stockData.getDataOf(rowIndex)+ " --with id-- "+stockData.getIdData());
        DefaultTableModel model = (DefaultTableModel) billEntriesTable.getModel();
        model.addRow(stockData.getDataOf(rowIndex));
        
        
    }
}
