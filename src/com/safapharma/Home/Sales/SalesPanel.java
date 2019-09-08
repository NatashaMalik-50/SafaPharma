/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Home.Sales;

import com.safapharma.Helpers.DesignConstants;
import com.safapharma.Home.HomeScreenBackend;
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
 * @author akshit
 */
public class SalesPanel extends MainScreenPanel{
       
    private MainWindow manager;
    private JTable salesTable;
    private DefaultTableModel tableModel;
    private final SalesBackend salesBackend;

    public SalesPanel(MainWindow manager) {
        this.manager = manager;
        salesBackend = new SalesBackend();
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
        salesTable = new JTable(tableModel);
        salesTable.getTableHeader().setResizingAllowed(false);
        salesTable.getTableHeader().setFont(DesignConstants.FONT_SIZE_14_CALIBRI_BOLD);
        salesTable.setFont(DesignConstants.FONT_SIZE_14_CALIBRI);
        salesTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        salesTable.setAutoCreateRowSorter(true);
        getTableScrollPane().setViewportView(salesTable);

    }
       private void loadData() throws Exception {
        DataWithColumn dataWithColumn = salesBackend.setSaleInfoIntoTable(salesTable, tableModel);
        tableModel.setDataVector(dataWithColumn.getData(), dataWithColumn.getColumnNames());
    }

       private void setListeners() {
        salesTable.addMouseListener(new MouseAdapter() {
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
                    manager.createNewStockViewForm(); //the appropriate function call
                    manager.showNewStockViewForm();
                } catch (Exception ex) {
                    Logger.getLogger(HomeScreenPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
