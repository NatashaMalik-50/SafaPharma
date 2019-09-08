/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Home.Supplier;

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
public class SupplierPanel extends MainScreenPanel {
    
     private MainWindow manager;
    private JTable supplierTable;
    private DefaultTableModel tableModel;
    private final SupplierBackend supplierBackend;
    private TableRowSorter sorter;

    public SupplierPanel(MainWindow manager) {
        this.manager = manager;
        supplierBackend = new SupplierBackend();
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
        supplierTable = new JTable(tableModel);
        sorter = new TableRowSorter(tableModel);
        supplierTable.setRowSorter(sorter);
        supplierTable.getTableHeader().setResizingAllowed(false);
        supplierTable.getTableHeader().setFont(DesignConstants.FONT_SIZE_14_CALIBRI_BOLD);
        supplierTable.setFont(DesignConstants.FONT_SIZE_14_CALIBRI);
        supplierTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        getTableScrollPane().setViewportView(supplierTable);

    }

    private void loadData() throws Exception {
        DataWithColumn dataWithColumn = supplierBackend.setSupplierInfoIntoTable(supplierTable, tableModel);
        tableModel.setDataVector(dataWithColumn.getData(), dataWithColumn.getColumnNames());

    }

    private void setListeners() {
        supplierTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                enableButtons();
            }

        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    manager.createNewSupplierForm(supplierBackend);
                    manager.showNewSupplierForm();
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

    
}
