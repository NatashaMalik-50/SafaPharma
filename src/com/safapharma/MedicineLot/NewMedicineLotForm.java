/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.MedicineLot;

import com.safapharma.Main.MainWindow;
import com.safapharma.Templates.DialogForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

/**
 *
 * @author Karan
 */
public class NewMedicineLotForm extends DialogForm {
    
    private MainWindow manager;
    
    private FormLabel medicineIdLabel;
    private JComboBox medicineIdCombo;
    private ErrorLabel medicineIdErrorLabel;
    
    private FormLabel batchNumLabel;
    private FormText batchNumText;
    private ErrorLabel batchNumErrorLabel;
    
    private FormLabel expiryLabel;
    private FormText expiryText;
    private ErrorLabel expiryErrorLabel;
    
    private FormLabel rateLabel;
    private FormText rateText;
    private ErrorLabel rateErrorLabel;
    
    private FormButton submitButton;
    private FormButton resetButton;
    private MedicineLotBackend medicineLotBackend;
    

    public NewMedicineLotForm(MainWindow manager, MedicineLotBackend medicineLotBackend) {
        this.manager = manager;
        this.medicineLotBackend = medicineLotBackend;
        initUI();
        addListeners();
    }

    private void initUI() {
        getFormLabel().setText("Add MedicineLotForm");
        System.out.println("Creating and Adding Medicine Lot Form ");

        String[] someString = { "Bird", "Cat", "Dog", "Rabbit" };
        
        medicineIdLabel = new FormLabel("Medicine Id");
        medicineIdCombo = new JComboBox(someString);
        medicineIdErrorLabel = new ErrorLabel();

        batchNumLabel = new FormLabel("Batch Number");
        batchNumText = new FormText();
        batchNumErrorLabel = new ErrorLabel();

        expiryLabel = new FormLabel("Expiry");
        expiryText = new FormText();
        expiryErrorLabel = new ErrorLabel();

        rateLabel = new FormLabel("Rate");
        rateText = new FormText();
        rateErrorLabel = new ErrorLabel();

        submitButton = new FormButton("Submit");
        resetButton = new FormButton("Reset");
        
        getFormPanel().add(medicineIdLabel);
        getFormPanel().add(medicineIdCombo);
        getFormPanel().add(medicineIdErrorLabel);
        
        getFormPanel().add(batchNumLabel);
        getFormPanel().add(batchNumText);
        getFormPanel().add(batchNumErrorLabel);
        
        getFormPanel().add(expiryLabel);
        getFormPanel().add(expiryText);
        getFormPanel().add(expiryErrorLabel);
        
        getFormPanel().add(rateLabel);
        getFormPanel().add(rateText);
        getFormPanel().add(rateErrorLabel);
        
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
//                        Supplier supplier = generateSupplier();
//                        boolean isAdded = medicineLotBackend.addSupplier(supplier);
//                        if (isAdded) {
//                            createOptionPane("Supplier Added", "");
//                            manager.deleteNewSupplierForm();
//                        } else {
//                            createOptionPane("Not Able to add supplier! Please try again.", "Error");
//                        }
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
//        if (nameText.getText().length() < 3) {
//            medicineIdErrorLabel.setErrorText("Name must be atleast of length 3");
//            nameErrorLabel.setVisible(true);
//            isValid = false;
//        }
//        if (nameText.getText().length() > 45) {
//            nameErrorLabel.setErrorText("Name can be maximum of length 45");
//            nameErrorLabel.setVisible(true);
//            isValid = false;
//        }
//        if (contactText.getText().length() != 10) {
//            contactErrorLabel.setErrorText("Contact no. must be of 10 digits");
//            contactErrorLabel.setVisible(true);
//            isValid = false;
//        }
//        if (addressText.getText().length() < 8 || addressText.getText().length() > 45) {
//            addressErrorLabel.setErrorText("Address length should be between 8 and 45.");
//            addressErrorLabel.setVisible(true);
//            isValid = false;
//        }
//        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
//        Matcher matcher = pattern.matcher(emailText.getText());
//        boolean isPatternMatched = matcher.matches();
//        if (!isPatternMatched) {
//            emailErrorLabel.setErrorText("Invalid email address");
//            emailErrorLabel.setVisible(true);
//            isValid = false;
//        }
//        if (emailText.getText().length() > 60) {
//            emailErrorLabel.setErrorText("Email address can be maximum of length 60");
//            emailErrorLabel.setVisible(true);
//            isValid = false;
//        if (nameText.getText().length() < 3) {
//            medicineIdErrorLabel.setErrorText("Name must be atleast of length 3");
//            nameErrorLabel.setVisible(true);
//            isValid = false;
//        }
//        if (nameText.getText().length() > 45) {
//            nameErrorLabel.setErrorText("Name can be maximum of length 45");
//            nameErrorLabel.setVisible(true);
//            isValid = false;
//        }
//        if (contactText.getText().length() != 10) {
//            contactErrorLabel.setErrorText("Contact no. must be of 10 digits");
//            contactErrorLabel.setVisible(true);
//            isValid = false;
//        }
//        if (addressText.getText().length() < 8 || addressText.getText().length() > 45) {
//            addressErrorLabel.setErrorText("Address length should be between 8 and 45.");
//            addressErrorLabel.setVisible(true);
//            isValid = false;
//        }
//        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
//        Matcher matcher = pattern.matcher(emailText.getText());
//        boolean isPatternMatched = matcher.matches();
//        if (!isPatternMatched) {
//            emailErrorLabel.setErrorText("Invalid email address");
//            emailErrorLabel.setVisible(true);
//            isValid = false;
//        }
//        if (emailText.getText().length() > 60) {
//            emailErrorLabel.setErrorText("Email address can be maximum of length 60");
//            emailErrorLabel.setVisible(true);
//            isValid = false;
//        }

        return isValid;
    }

//    private Supplier generateSupplier() {
//        Supplier supplier = new Supplier(nameText.getText(), addressText.getText(), contactText.getText(), emailText.getText());
//        return supplier;
//    }

    private void hideErrorLabels() {
        medicineIdErrorLabel.setVisible(false);
        batchNumErrorLabel.setVisible(false);
        expiryErrorLabel.setVisible(false);
        rateErrorLabel.setVisible(false);
    }

    private void resetErrorLabels() {
        medicineIdErrorLabel.setErrorText("");
        batchNumErrorLabel.setErrorText("");
        expiryErrorLabel.setErrorText("");
        rateErrorLabel.setErrorText("");
        hideErrorLabels();
    }

    @Override
    protected void deleteScreen() {
        manager.deleteNewMedicineLotForm();
    }

    
}
