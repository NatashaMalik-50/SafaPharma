/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Customer;

import com.safapharma.Helpers.DesignConstants;
import com.safapharma.Main.MainWindow;
import com.safapharma.ModelObjects.DataWithColumn;
import com.safapharma.Templates.DialogForm;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

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
    private DialogForm.ErrorLabel nameErrorLabel;

    private DialogForm.FormLabel contactLabel;
    private DialogForm.FormText contactText;
    private DialogForm.ErrorLabel contactErrorLabel;

    private DialogForm.FormLabel addressLabel;
    private DialogForm.FormText addressText;
    private DialogForm.ErrorLabel addressErrorLabel;

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
        tableModel = new DefaultTableModel();
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
        nameErrorLabel = new DialogForm.ErrorLabel();

        contactLabel = new DialogForm.FormLabel("Contact Number");
        contactText = new DialogForm.FormText();
        contactErrorLabel = new DialogForm.ErrorLabel();

        addressLabel = new DialogForm.FormLabel("Address");
        addressText = new DialogForm.FormText();
        addressErrorLabel = new DialogForm.ErrorLabel();

        viewPanel.add(nameLabel);
        viewPanel.add(nameText);
        viewPanel.add(nameErrorLabel);

        viewPanel.add(contactLabel);
        viewPanel.add(contactText);
        viewPanel.add(contactErrorLabel);

        viewPanel.add(addressLabel);
        viewPanel.add(addressText);
        viewPanel.add(addressErrorLabel);

        nameText.setText(currentCustomer.get(1).toString());
        addressText.setText(currentCustomer.get(2).toString());
        contactText.setText(currentCustomer.get(3).toString());

        //getFormPanel().add(submitButton);
        //getFormPanel().add(resetButton);
        getFormPanel().add(viewPanel);
//        scrollPane.add(customerTable);
        getFormPanel().add(scrollPane);
        this.pack();
        hideErrorLabels();
    }

    private void loadData() throws Exception {
        System.out.println("in load data");
        DataWithColumn dataWithColumn = customerBackend.setSelectedInfoIntoTable(selectedId);
        if (dataWithColumn != null) {
            tableModel.setDataVector(dataWithColumn.getData(), dataWithColumn.getColumnNames());
            System.out.println("value --- " + dataWithColumn.getData());
        }
    }

    private boolean validateInfo() {
        resetErrorLabels();
        boolean isValid = true;
        if (nameText.getText().length() < 3) {
            nameErrorLabel.setErrorText("Name must be atleast of length 3");
            nameErrorLabel.setVisible(true);
            isValid = false;
        }
        if (nameText.getText().length() > 45) {
            nameErrorLabel.setErrorText("Name can be maximum of length 45");
            nameErrorLabel.setVisible(true);
            isValid = false;
        }
        if (contactText.getText().length() != 10) {
            contactErrorLabel.setErrorText("Contact no. must be of 10 digits");
            contactErrorLabel.setVisible(true);
            isValid = false;
        }
        if (addressText.getText().length() < 8 || addressText.getText().length() > 45) {
            addressErrorLabel.setErrorText("Address length should be between 8 and 45.");
            addressErrorLabel.setVisible(true);
            isValid = false;
        }
        return isValid;
    }

    private void hideErrorLabels() {
        nameErrorLabel.setVisible(false);
        addressErrorLabel.setVisible(false);
        contactErrorLabel.setVisible(false);
    }

    private void resetErrorLabels() {
        nameErrorLabel.setErrorText("");
        addressErrorLabel.setErrorText("");
        contactErrorLabel.setErrorText("");
        hideErrorLabels();
    }

    @Override
    protected void deleteScreen() {
        manager.deleteViewCustomerForm();
    }

}
