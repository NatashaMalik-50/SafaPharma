/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Customer;

import com.safapharma.Helpers.DesignConstants;
import com.safapharma.Main.MainWindow;
import com.safapharma.ModelObjects.DataWithColumn;
import com.safapharma.Templates.CustomDefaultTableModel;
import com.safapharma.Templates.DialogForm;
import com.safapharma.Templates.DummyPanel;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import javax.swing.JLabel;

/**
 *
 * @author sony
 */
public class ViewCustomerForm extends DialogForm {

    private MainWindow manager;
    private JTable salesTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private JPanel viewPanel;
    private DialogForm.FormLabel nameLabel;
    private DialogForm.FormText nameText;

    private DialogForm.FormLabel contactLabel;
    private DialogForm.FormText contactText;

    private DialogForm.FormLabel addressLabel;
    private DialogForm.FormText addressText;
    
    private FormLabel customerBillLabel;

    private final CustomerBackend customerBackend;
    private final ViewCustomerForm thisForm;
    private int selectedId;
    private Vector currentCustomer;

    public ViewCustomerForm(MainWindow manager, CustomerBackend customerBackend, Vector selectedObject, int id) {
        this.thisForm = this;
        this.manager = manager;
        this.customerBackend = customerBackend;
        this.currentCustomer = selectedObject;
        this.selectedId = id;
        initUI();
        addListener();
        System.out.println(selectedObject);

    }

//    public ViewCustomerForm(MainWindow aThis, int id) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    private void initUI() {

        viewPanel = new JPanel();
        viewPanel.setLayout(new GridLayout(0, 3));
        scrollPane = new JScrollPane();
        tableModel = new CustomDefaultTableModel();
        salesTable = new JTable(tableModel);
        salesTable.getTableHeader().setResizingAllowed(false);
        salesTable.getTableHeader().setFont(DesignConstants.FONT_SIZE_14_CALIBRI_BOLD);
        getFormPanel().setLayout(new BoxLayout(getFormPanel(), BoxLayout.Y_AXIS));
        salesTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        salesTable.setAutoCreateRowSorter(true);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                loadData();
                System.out.println(" after load data");
                thisForm.pack();
                return null;
            }
        }.execute();

        getFormLabel().setText("View Customer");
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(salesTable);

        nameLabel = new DialogForm.FormLabel("Name");
        nameText = new DialogForm.FormText();

        contactLabel = new DialogForm.FormLabel("Contact Number");
        contactText = new DialogForm.FormText();

        addressLabel = new DialogForm.FormLabel("Address");
        addressText = new DialogForm.FormText();        
        
        customerBillLabel = new FormLabel("Customer Bills");
        customerBillLabel.setHorizontalAlignment(JLabel.CENTER);

        viewPanel.add(nameLabel);
        viewPanel.add(nameText);
        viewPanel.add(new DummyPanel());

        viewPanel.add(contactLabel);
        viewPanel.add(contactText);
        viewPanel.add(new DummyPanel());

        viewPanel.add(addressLabel);
        viewPanel.add(addressText);
        viewPanel.add(new DummyPanel());
        
        viewPanel.add(new DummyPanel());
        viewPanel.add(customerBillLabel);
        viewPanel.add(new DummyPanel());

        nameText.setText(currentCustomer.get(1).toString());
        addressText.setText(currentCustomer.get(2).toString());
        contactText.setText(currentCustomer.get(3).toString());
        

        getFormPanel().add(viewPanel);
        getFormPanel().add(scrollPane);
        this.pack();
        disableTextFields();
    }

    private void loadData() throws Exception {
        DataWithColumn dataWithColumn = customerBackend.setSelectedInfoIntoTable(selectedId);
        if (dataWithColumn != null) {
            tableModel.setDataVector(dataWithColumn.getData(), dataWithColumn.getColumnNames());
        }
    }  

    private void disableTextFields() {
        nameText.setEditable(false);
        contactText.setEditable(false);
        addressText.setEditable(false);
    }

    @Override
    protected void deleteScreen() {
        manager.deleteViewCustomerForm();
    }

}
