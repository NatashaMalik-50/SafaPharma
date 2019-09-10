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
import java.util.Date;
/**
 *
 * @author sony
 */
public class NewCustomerForm extends DialogForm {

    private MainWindow manager;
    private FormLabel nameLabel;
    private FormText nameText;
    private ErrorLabel nameErrorLabel;
    private FormLabel contactLabel;
    private FormText contactText;
    private ErrorLabel contactErrorLabel;
    private FormLabel addressLabel;
    private FormText addressText;
    private ErrorLabel addressErrorLabel;
    private FormLabel customerentryLabel;
    private FormText customerentryText;
    private ErrorLabel customerentryErrorLabel;
    private FormButton submitButton;
    private FormButton resetButton;
    private final CustomerBackend customerBackend;
    

    public NewCustomerForm(MainWindow manager, CustomerBackend CustomerBackend) {
        this.manager = manager;
        this.customerBackend = CustomerBackend;
        initUI();
        addListeners();
    }

    public NewCustomerForm(MainWindow aThis, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initUI() {
        getFormLabel().setText("Add Customer");
        
        nameLabel = new FormLabel("Name");
        nameText = new FormText();
        nameErrorLabel = new ErrorLabel();

        contactLabel = new FormLabel("Contact Number");
        contactText = new FormText();
        contactErrorLabel = new ErrorLabel();

        addressLabel = new FormLabel("Address");
        addressText = new FormText();
        addressErrorLabel = new ErrorLabel();

        customerentryLabel = new FormLabel("Customer Entry Date");
        customerentryText = new FormText();
        customerentryErrorLabel = new ErrorLabel();

        submitButton = new FormButton("Submit");
        resetButton = new FormButton("Reset");
        
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
                            createOptionPane("Customer Added", "");
                            manager.deleteNewCustomerForm();
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
        manager.deleteNewCustomerForm();
    }

    

}

