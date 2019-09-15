/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Home.Supplier;

import com.safapharma.Helpers.Constants;
import com.safapharma.Helpers.DesignConstants;
import com.safapharma.Home.HomeScreenPanel;
import com.safapharma.Main.MainWindow;
import com.safapharma.ModelObjects.DataWithColumn;
import com.safapharma.ModelObjects.Supplier;
import com.safapharma.Templates.CustomDefaultTableModel;
import com.safapharma.Templates.MainScreenPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
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
public class SupplierPanel extends MainScreenPanel {

    private MainWindow manager;
    private JTable supplierTable;
    private DefaultTableModel tableModel;
    private final SupplierBackend supplierBackend;
    private TableRowSorter sorter;
    private DataWithColumn supplierDataWithColumn;
    private int currentlyUpdatingSupplierSno;

    public SupplierPanel(MainWindow manager) {
        this.manager = manager;
        supplierBackend = new SupplierBackend();
        this.currentlyUpdatingSupplierSno = Constants.INVALID;
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
        this.supplierDataWithColumn = supplierBackend.setSupplierInfoIntoTable();
        if (supplierDataWithColumn != null) {
            tableModel.setDataVector(supplierDataWithColumn.getData(), supplierDataWithColumn.getColumnNames());
        }
    }

    private void setListeners() {
        supplierTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                enableButtons();
                enableUpdateButtons();
                enableRemoveButtons();
                enableViewButtons();
            }

        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    manager.createNewOrUpdateSupplierForm(supplierBackend, false, null, false);
                    manager.showNewOrUpdateSupplierForm();
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

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowIndex = supplierTable.getSelectedRow();
                int selectSNo = Integer.parseInt(supplierTable.getValueAt(rowIndex, 0).toString());
                int currentId = supplierDataWithColumn.getIdBySerialNo(selectSNo);
                Vector<Object> data = supplierDataWithColumn.getDataOf(selectSNo - 1);
                Supplier supplier = new Supplier((String) data.get(1), (String) data.get(2), "" + (BigDecimal) data.get(3), (String) data.get(4));
                supplier.setId(currentId);
                currentlyUpdatingSupplierSno = selectSNo;
                System.out.println("supplier found: " + supplier +" with current serial no. as  "+currentlyUpdatingSupplierSno);
                try {
                    manager.createNewOrUpdateSupplierForm(supplierBackend, true, supplier, false);
                    manager.showNewOrUpdateSupplierForm();
                } catch (Exception ex) {
                    Logger.getLogger(SupplierPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void receiveNewSupplier(Supplier supplier) {
        System.out.println("Received new supplier as : " + supplier);
        DefaultTableModel model = (DefaultTableModel) supplierTable.getModel();
        Vector<Object> newSupplierRow = new Vector<>();
        newSupplierRow.add(supplierDataWithColumn.getLastKey() + 1);
        newSupplierRow.add(supplier.getName());
        newSupplierRow.add(supplier.getAddress());
        newSupplierRow.add(supplier.getContactNo());
        newSupplierRow.add(supplier.getEmail());
        model.addRow(newSupplierRow);
        supplierDataWithColumn.addData(newSupplierRow);
        supplierDataWithColumn.addIdData(supplier.getId());
    }

    public void receiveUpdatedSupplier(Supplier supplier) {
        System.out.println("Received updated supplier as : " + supplier);
        try {
            Vector<Object> updatedSupplierRow = new Vector<>();
            updatedSupplierRow.add(currentlyUpdatingSupplierSno);
            updatedSupplierRow.add(supplier.getName());
            updatedSupplierRow.add(supplier.getAddress());
            updatedSupplierRow.add(supplier.getContactNo());
            updatedSupplierRow.add(supplier.getEmail());

            System.out.println("New Row updating at index : " + (currentlyUpdatingSupplierSno - 1) + " as " + updatedSupplierRow.toString());
            supplierDataWithColumn.updateData(currentlyUpdatingSupplierSno - 1, updatedSupplierRow);

            DefaultTableModel model = (DefaultTableModel) supplierTable.getModel();
            model.setDataVector(supplierDataWithColumn.getData(), supplierDataWithColumn.getColumnNames());
        } catch (Exception e) {
            System.out.println("Error found here : " + e);
        }
    }

    @Override
    protected void addAlerts() {
        manager.viewExpiredMedicineThroughStatusPanel();
    }

}
