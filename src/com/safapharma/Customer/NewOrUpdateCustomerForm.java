/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Customer;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.safapharma.Main.MainWindow;
import com.safapharma.ModelObjects.Customer;
import com.safapharma.Templates.DialogForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author sony
 */
public class NewOrUpdateCustomerForm extends DialogForm {

    private MainWindow manager;
    private JTable customerTable;
    private DefaultTableModel tableModel;

    private FormLabel nameLabel;
    private FormText nameText;
    private ErrorLabel nameErrorLabel;

    private FormLabel contactLabel;
    private FormText contactText;
    private ErrorLabel contactErrorLabel;

    private FormLabel addressLabel;
    private FormText addressText;
    private ErrorLabel addressErrorLabel;

    private FormLabel emailLabel;
    private FormText emailText;
    private ErrorLabel emailErrorLabel;

    private FormButton submitButton;
    private FormButton resetButton;
    private final CustomerBackend customerBackend;
    private boolean isUpdateForm;
    private Customer currentCustomer;

    public NewOrUpdateCustomerForm(MainWindow manager, CustomerBackend CustomerBackend) {
        this.manager = manager;
        this.customerBackend = CustomerBackend;
        this.isUpdateForm = false;
        this.currentCustomer = null;
        initUI();
        addListeners();
    }

    public NewOrUpdateCustomerForm(MainWindow manager, CustomerBackend customerBackend, boolean isUpdateForm, Customer customer) {
        this.manager = manager;
        this.customerBackend = customerBackend;
        this.isUpdateForm = isUpdateForm;
        this.currentCustomer = customer;
        initUI();
        addListeners();
    }

    private void initUI() {
        if (isUpdateForm) {
            getFormLabel().setText("Update Customer");
        } else {
            getFormLabel().setText("Add Customer");
        }

        nameLabel = new FormLabel("Name");
        nameText = new FormText();
        nameErrorLabel = new ErrorLabel();

        nameLabel = new FormLabel("Name");
        nameText = new FormText();
        nameErrorLabel = new ErrorLabel();

        contactLabel = new FormLabel("Contact Number");
        contactText = new FormText();
        contactErrorLabel = new ErrorLabel();

        addressLabel = new FormLabel("Address");
        addressText = new FormText();
        addressErrorLabel = new ErrorLabel();

        emailLabel = new FormLabel("Email");
        emailText = new FormText();
        emailErrorLabel = new ErrorLabel();

        if (isUpdateForm) {
            submitButton = new FormButton("Update");
        } else {
            submitButton = new FormButton("Submit");
        }

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

        getFormPanel().add(emailLabel);
        getFormPanel().add(emailText);
        getFormPanel().add(emailErrorLabel);

        getButtonPanel().add(submitButton);
        getButtonPanel().add(resetButton, getGBC(1));

        this.pack();
        hideErrorLabels();
        if (currentCustomer != null) {
            setFields();
        }
    }

    private void addListeners() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isValid = validateInfo();
                if (isValid) {
                    try {
                        Customer customer = generateCustomer();
                        if (isUpdateForm) {
                            boolean isAdded = customerBackend.updateCustomer(customer);
                            if (isAdded) {
                                createOptionPane("Customer Updated", "");
                                manager.deleteNewOrUpdateCustomerForm();
                            } else {
                                createOptionPane("Not Able to update customer! Please try again.", "Error");
                            }

                        } else {
                            boolean isAdded = customerBackend.addCustomer(customer);
                            if (isAdded) {
                                createOptionPane("Customer Added", "");
                                manager.deleteNewOrUpdateCustomerForm();
                            } else {
                                createOptionPane("Not Able to add customer! Please try again.", "Error");
                            }
                        }
                    } catch (Exception ex) {
                        createOptionPane("Database Error", "Error");
                    }
                }
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFields();
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

        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(emailText.getText());
        boolean isPatternMatched = matcher.matches();
        if (!isPatternMatched) {
            emailErrorLabel.setErrorText("Invalid email address");
            emailErrorLabel.setVisible(true);
            isValid = false;
        }
        if (emailText.getText().length() > 60) {
            emailErrorLabel.setErrorText("Email address can be maximum of length 60");
            emailErrorLabel.setVisible(true);
            isValid = false;
        }

        return isValid;
    }

    private Customer generateCustomer() {
        if(isUpdateForm){
            currentCustomer.setName(nameText.getText());
            currentCustomer.setAddress(addressText.getText());
            currentCustomer.setContactNo(contactText.getText());
            currentCustomer.setEmail(emailText.getText());
            return currentCustomer;
        }
        Customer customer = new Customer(nameText.getText(), addressText.getText(), contactText.getText(), emailText.getText());
        return customer;
    }

    private void hideErrorLabels() {
        nameErrorLabel.setVisible(false);
        addressErrorLabel.setVisible(false);
        contactErrorLabel.setVisible(false);
        emailErrorLabel.setVisible(false);
    }

    private void resetErrorLabels() {
        nameErrorLabel.setErrorText("");
        addressErrorLabel.setErrorText("");
        contactErrorLabel.setErrorText("");
        emailErrorLabel.setErrorText("");
        hideErrorLabels();
    }

    private void resetFields() {
        if (isUpdateForm) {
            setFields();
        } else {
            nameText.setText("");
            contactText.setText("");
            addressText.setText("");
            emailText.setText("");
        }
    }

    private void setFields() {
        if (currentCustomer != null) {
            nameText.setText(currentCustomer.getName());
            addressText.setText(currentCustomer.getAddress());
            contactText.setText(currentCustomer.getContactNo());
            emailText.setText(currentCustomer.getEmail());
        }
    }

    @Override
    protected void deleteScreen() {
        manager.deleteNewOrUpdateCustomerForm();
    }

}
