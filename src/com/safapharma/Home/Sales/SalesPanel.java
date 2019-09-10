/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Home.Sales;

import com.safapharma.Helpers.DesignConstants;
import com.safapharma.Home.HomeScreenPanel;
import com.safapharma.Main.MainWindow;
import com.safapharma.ModelObjects.DataWithColumn;
import com.safapharma.Templates.CustomDefaultTableModel;
import com.safapharma.Templates.MainScreenPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.SwingWorker;

/**
 *
 * @author akshit
 */
public class SalesPanel extends MainScreenPanel {

    private MainWindow manager;
    private JTable salesTable;
    private CustomDefaultTableModel tableModel;
    private final SalesBackend salesBackend;

    public SalesPanel(MainWindow manager) {
        this.manager = manager;
        salesBackend = new SalesBackend();
        initUI();
        setListeners();
    }

    private void initUI() {
        tableModel = new CustomDefaultTableModel();
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                loadData();
                return null;
            }
        }.execute();

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
    }
}
