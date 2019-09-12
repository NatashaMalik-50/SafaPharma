/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Customer;

import com.safapharma.Main.MainWindow;
import com.safapharma.ModelObjects.Customer;
import com.safapharma.Templates.DialogForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sony
 */
public class UpdateCustomerForm extends DialogForm {

    private MainWindow manager;
    private JTable customerTable;
    private DefaultTableModel tableModel;

    private DialogForm.FormLabel nameLabel;
    private DialogForm.FormText nameText;
    private DialogForm.ErrorLabel nameErrorLabel;

    private DialogForm.FormLabel contactLabel;
    private DialogForm.FormText contactText;
    private DialogForm.ErrorLabel contactErrorLabel;

    private DialogForm.FormLabel addressLabel;
    private DialogForm.FormText addressText;
    private DialogForm.ErrorLabel addressErrorLabel;

    private DialogForm.FormButton submitButton;
    private FormButton resetButton;

    private final CustomerBackend customerBackend;

    public UpdateCustomerForm(MainWindow manager, CustomerBackend CustomerBackend) {
        this.manager = manager;
        this.customerBackend = CustomerBackend;
        initUI();
        addListeners();
    }

    public UpdateCustomerForm(MainWindow aThis, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initUI() {
        getFormLabel().setText("Update Customer");

        nameLabel = new DialogForm.FormLabel("Name");
        nameText = new DialogForm.FormText();
        nameErrorLabel = new DialogForm.ErrorLabel();

        contactLabel = new DialogForm.FormLabel("Contact Number");
        contactText = new DialogForm.FormText();
        contactErrorLabel = new DialogForm.ErrorLabel();

        addressLabel = new DialogForm.FormLabel("Address");
        addressText = new DialogForm.FormText();
        addressErrorLabel = new DialogForm.ErrorLabel();

        submitButton = new DialogForm.FormButton("Update");
        resetButton = new FormButton("Reset");

        getFormPanel().add(nameLabel);
        getFormPanel().add(nameText);
        getFormPanel().add(nameErrorLabel);

        getFormPanel().add(contactLabel);
        getFormPanel().add(contactText);
        getFormPanel().add(contactErrorLabel);

        getFormPanel().add(addressLabel);
        getFormPanel().add(addressText);
        getFormPanel().add(addressErrorLabel);
        
        
        getButtonPanel().add(submitButton);
        getButtonPanel().add(resetButton,getGBC(1));
        
        this.pack();
        hideErrorLabels();
    }

    private void addListeners() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isValid = validateInfo();
                if (isValid) {
                    try {
                        Customer customer = generateCustomer();
                        boolean isAdded = customerBackend.addCustomer(customer);
                        if (isAdded) {
                            createOptionPane("Customer Updatetd", "");
                            manager.deleteUpdateCustomerForm();
                        } else {
                            createOptionPane("Not Able to add customer! Please try again.", "Error");
                        }
                    } catch (Exception ex) {
                        createOptionPane("Database Error", "Error");
                    }
                }
            }

            private void createOptionPane(String not_Able_to_add_supplier_Please_try_again, String error) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            private Customer generateCustomer() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
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

//    private Customer generateCustomer() {
//        Customer customer = new Customer(nameText.getText(), addressText.getText(), customersinceText.getText());
//        return customer;
//    }
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
        manager.deleteUpdateCustomerForm();
    }

}
