/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Home;

import com.safapharma.Helpers.DesignConstants;
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
    private JTable stockTable;
    private DefaultTableModel tableModel;
    private final HomeScreenBackend homeScreenBackend;
    private TableRowSorter sorter;

    public HomeScreenPanel(MainWindow manager) {
        this.manager = manager;
        homeScreenBackend = new HomeScreenBackend();
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
        stockTable = new JTable(tableModel);
        sorter = new TableRowSorter(tableModel);
        stockTable.setRowSorter(sorter);
        stockTable.getTableHeader().setResizingAllowed(false);
        stockTable.getTableHeader().setFont(DesignConstants.FONT_SIZE_14_CALIBRI_BOLD);
        stockTable.setFont(DesignConstants.FONT_SIZE_14_CALIBRI);
        stockTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        getTableScrollPane().setViewportView(stockTable);

    }

    private DataWithColumn loadData() throws Exception {
        DataWithColumn dataWithColumn = homeScreenBackend.setStockInfoIntoTable(stockTable, tableModel);
        //tableModel.setDataVector(dataWithColumn.getData(), dataWithColumn.getColumnNames());
        
        return dataWithColumn;
    }

    private void setListeners() {
        stockTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                enableAddButtons();
                enableUpdateButtons();
                enableRemoveButtons();
                enableViewButtons();
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

    /*
    To get selected row, do stockTable.getSelectedRow();
    to access cell value, do tableModel.getValueAt(selectedRowIndex, columnIdx);
     */
}
