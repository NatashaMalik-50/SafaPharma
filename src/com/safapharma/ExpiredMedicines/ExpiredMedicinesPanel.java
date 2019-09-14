/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.ExpiredMedicines;

import com.safapharma.Helpers.DesignConstants;
import com.safapharma.Main.MainWindow;
import com.safapharma.ModelObjects.DataWithColumn;
import com.safapharma.Templates.CustomDefaultTableModel;
import com.safapharma.Templates.MainScreenPanel;
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
public class ExpiredMedicinesPanel extends MainScreenPanel{
    
    private MainWindow manager;
    private ExpiredMedicinesBackend expiredMedicinesBackend;
    private JTable expiredMedicinesTable;
    private CustomDefaultTableModel tableModel;
    private TableRowSorter sorter;
    
    public ExpiredMedicinesPanel(MainWindow manager){
        this.manager = manager;
        expiredMedicinesBackend = new ExpiredMedicinesBackend();
        initUI();
        addListeners();
    }
    public void initUI(){
        hideAllButtons();
         new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                loadData();
                return null;
            }
        }.execute();

        tableModel = new CustomDefaultTableModel();
        expiredMedicinesTable = new JTable(tableModel);
        sorter = new TableRowSorter(tableModel);
        expiredMedicinesTable.setRowSorter(sorter);
        expiredMedicinesTable.getTableHeader().setResizingAllowed(false);
        expiredMedicinesTable.getTableHeader().setFont(DesignConstants.FONT_SIZE_14_CALIBRI_BOLD);
        expiredMedicinesTable.setFont(DesignConstants.FONT_SIZE_14_CALIBRI);
        expiredMedicinesTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        getTableScrollPane().setViewportView(expiredMedicinesTable);
        
    }
    private void loadData() throws Exception {
        DataWithColumn expiredDataWithColumn = expiredMedicinesBackend.getAllExpiredMedicinesDetails();
        if (expiredDataWithColumn != null) {
            tableModel.setDataVector(expiredDataWithColumn.getData(), expiredDataWithColumn.getColumnNames());
        }
    }
    private void addListeners(){
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
    }
    
}
