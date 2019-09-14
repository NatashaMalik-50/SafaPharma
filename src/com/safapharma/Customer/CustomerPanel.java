/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Customer;

import com.safapharma.Helpers.DesignConstants;
import com.safapharma.Home.HomeScreenPanel;
import com.safapharma.Main.MainWindow;
import com.safapharma.ModelObjects.Customer;
import com.safapharma.ModelObjects.DataWithColumn;
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
public class CustomerPanel extends MainScreenPanel {

    private MainWindow manager;
    private JTable customerTable;
    private DefaultTableModel tableModel;
    private final CustomerBackend customerBackend;
    private TableRowSorter sorter;
    private DataWithColumn customerDataWithColumns;
    private ToolbarButton selectCustomerButton;
    private boolean viaBilling;

    public CustomerPanel(MainWindow manager, boolean viaBilling) {

        this.manager = manager;
        customerBackend = new CustomerBackend();
        this.viaBilling = viaBilling;
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
        selectCustomerButton = new ToolbarButton("Select Customer");
        if (viaBilling) {
            getToolbar().add(selectCustomerButton, 0);
            selectCustomerButton.setEnabled(false);
            removeButton.setVisible(false);
        }
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
        customerDataWithColumns = customerBackend.setCustomerInfoIntoTable();
        tableModel.setDataVector(customerDataWithColumns.getData(), customerDataWithColumns.getColumnNames());
    }

    private void setListeners() {
        //on add button click
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //create new form
                    manager.createNewOrUpdateCustomerForm(customerBackend); //the appropriate function call
                    manager.showNewOrUpdateCustomerForm();
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

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int rowIndex = customerTable.getSelectedRow();
                    int selectSNo = Integer.parseInt(customerTable.getValueAt(rowIndex, 0).toString());
                    int currentCustomerId = customerDataWithColumns.getIdBySerialNo(selectSNo);
                    Vector<Object> customer = customerDataWithColumns.getDataOf(selectSNo - 1);
                    manager.createViewCustomerForm(customerBackend, customer, currentCustomerId);
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
                    int rowIndex = customerTable.getSelectedRow();
                    int selectSNo = Integer.parseInt(customerTable.getValueAt(rowIndex, 0).toString());
                    int currentCustomerId = customerDataWithColumns.getIdBySerialNo(selectSNo);
                    Vector<Object> data = customerDataWithColumns.getDataOf(selectSNo - 1);
                    Customer customer = new Customer(currentCustomerId, (String) data.get(1), (String) data.get(2), "" + (BigDecimal) data.get(3), (String) data.get(4));
                    System.out.println("sending customer " + customer);
                    manager.createNewOrUpdateCustomerForm(customerBackend, customer, true);
                    manager.showNewOrUpdateCustomerForm();
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

                if (viaBilling) {
                    selectCustomerButton.setEnabled(true);
                }
            }

        });

        selectCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowIndex = customerTable.getSelectedRow();
                int selectSNo = Integer.parseInt(customerTable.getValueAt(rowIndex, 0).toString());
                int currentCustomerId = customerDataWithColumns.getIdBySerialNo(selectSNo);
                Vector<Object> customerData=customerDataWithColumns.getDataOf(selectSNo-1);
                Customer customer = new Customer(currentCustomerId,(String)customerData.get(1) ,(String)customerData.get(2), ""+(BigDecimal)customerData.get(3),(String)customerData.get(4));
                System.out.println("Passing customer :"+customer);
                manager.passCustomerToBilling(customer);
                manager.deleteCurrentPanel();
            }
        });
    }

    @Override
    protected void addAlerts() {
        manager.viewExpiredMedicineThroughStatusPanel();
    }
}
