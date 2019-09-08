/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Home.Supplier;

import com.safapharma.Main.MainWindow;
import com.safapharma.ModelObjects.Supplier;
import com.safapharma.Templates.DialogForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Natasha Malik
 */
public class NewSupplierForm extends DialogForm {

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
    private FormLabel emailLabel;
    private FormText emailText;
    private ErrorLabel emailErrorLabel;
    private FormButton submitButton;
    private FormButton resetButton;
    private SupplierBackend supplierBackend;

    public NewSupplierForm(MainWindow manager, SupplierBackend supplierBackend) {
        this.manager = manager;
        this.supplierBackend = supplierBackend;
        initUI();
        addListeners();
    }

    private void initUI() {
        getFormLabel().setText("Add Supplier");

        nameLabel = new FormLabel("Name");
        nameText = new FormText();
        nameErrorLabel = new ErrorLabel();

        contactLabel = new FormLabel("Contact No.");
        contactText = new FormText();
        contactErrorLabel = new ErrorLabel();

        addressLabel = new FormLabel("Address");
        addressText = new FormText();
        addressErrorLabel = new ErrorLabel();

        emailLabel = new FormLabel("Email");
        emailText = new FormText();
        emailErrorLabel = new ErrorLabel();

        submitButton = new FormButton("Submit");
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
        getFormPanel().add(submitButton);
        getFormPanel().add(resetButton);

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
                        Supplier supplier = generateSupplier();
                        boolean isAdded = supplierBackend.addSupplier(supplier);
                        if (isAdded) {
                            createOptionPane("Supplier Added", "");
                            manager.deleteNewSupplierForm();
                        } else {
                            createOptionPane("Not Able to add supplier! Please try again.", "Error");
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

    private Supplier generateSupplier() {
        Supplier supplier = new Supplier(nameText.getText(), addressText.getText(), contactText.getText(), emailText.getText());
        return supplier;
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

}
