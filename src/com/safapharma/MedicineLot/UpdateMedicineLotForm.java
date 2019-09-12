/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.MedicineLot;

import com.safapharma.Main.MainWindow;
import com.safapharma.Templates.DialogForm;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 *
 * @author Karan
 */
public class UpdateMedicineLotForm extends DialogForm {
    
    private MainWindow manager;
        
    private DialogForm.FormLabel medicineNameLabel;
    private JComboBox medicineNameCombo;
    private DialogForm.ErrorLabel medicineNameErrorLabel;
    
    
    private DialogForm.FormLabel batchNumLabel;
    private DialogForm.FormText batchNumText;
    private DialogForm.ErrorLabel batchNumErrorLabel;
    
    private DialogForm.FormLabel expiryLabel;
    private DialogForm.FormText expiryText;
    private DialogForm.ErrorLabel expiryErrorLabel;
    
    private DialogForm.FormLabel rateLabel;
    private DialogForm.FormText rateText;
    private DialogForm.ErrorLabel rateErrorLabel;
    
    private DialogForm.FormButton submitButton;
    private DialogForm.FormButton resetButton;
    
    private MedicineLotBackend medicineLotBackend;    
    
    

    public UpdateMedicineLotForm(MainWindow manager, MedicineLotBackend medicineLotBackend) {
        this.manager = manager;
        this.medicineLotBackend = medicineLotBackend;
        initUI();        
        addListeners();
    }
    
    private void setComboBox(JComboBox mbox){
        mbox.setEditable(false);    // It is false by default
    }
    

    private void initUI() {
        
        //Set and configure layout
        getFormLabel().setText("Update MedicineLotForm");
        
        System.out.println("Updating Medicine Lot Form ");

        String[] someString = { "Acetaminophen", "Adderall", "Amitriptyline", "Amlodipine" };
        medicineNameLabel = new DialogForm.FormLabel("Medicine Name");
        medicineNameCombo = new JComboBox(someString);        
        medicineNameErrorLabel = new DialogForm.ErrorLabel();
        
        setComboBox(medicineNameCombo);

        batchNumLabel = new DialogForm.FormLabel("Batch Number");
        batchNumText = new DialogForm.FormText();
        batchNumErrorLabel = new DialogForm.ErrorLabel();

        expiryLabel = new DialogForm.FormLabel("Expiry");
        expiryText = new DialogForm.FormText();
        expiryErrorLabel = new DialogForm.ErrorLabel();

        rateLabel = new DialogForm.FormLabel("Rate");
        rateText = new DialogForm.FormText();
        rateErrorLabel = new DialogForm.ErrorLabel();
        
        submitButton = new DialogForm.FormButton("Submit");
        resetButton = new DialogForm.FormButton("Reset");
        
        getFormPanel().add(medicineNameLabel);
        getFormPanel().add(medicineNameCombo);        
        getFormPanel().add(medicineNameErrorLabel);        
        
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
        getFormPanel().add(new JPanel());
        

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
                        // Update the content provided in the form.
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
    
    private boolean isEmpty(String str){
        boolean checker = false;
        if(str.equals("") || str.equals(null))
            checker = true;
        
        return checker;    
    }
    
    
    private boolean validateInfo() {
        resetErrorLabels();
        boolean isValid = true;
        try{
        
            /*Checking if the respective textfields are empty or not. */
            if(medicineNameCombo.getSelectedItem().toString().isEmpty()){
                isValid = false;
                medicineNameErrorLabel.setText("Incorrect Medicine Name");
            }
            if(batchNumText.getText().isEmpty()){
                isValid = false;
                batchNumErrorLabel.setText("Incorrect batch number");
            }
                
            if(expiryText.getText().isEmpty()){
                isValid = false;
                expiryErrorLabel.setText("Incorrect Expiry ");
            }
            if(rateText.getText().isEmpty()){
                isValid = false;
                rateErrorLabel.setText("Incorrect Rate");
            }
            if(isValid){
                double num = Double.parseDouble(rateText.getText());
                if(num<=0){
                    isValid = false;
                }
            }
            
            if(isValid){
               resetErrorLabels();
            }
            else{
                 showErrorLabels();
            }
            
            return isValid;
        }
        catch(Exception e){
            e.printStackTrace();
            showErrorLabels();
            return false;
        }
    }

//    private Supplier generateSupplier() {
//        Supplier supplier = new Supplier(nameText.getText(), addressText.getText(), contactText.getText(), emailText.getText());
//        return supplier;
//    }

    private void hideErrorLabels() {
        medicineNameErrorLabel.setVisible(false);
        batchNumErrorLabel.setVisible(false);
        expiryErrorLabel.setVisible(false);
        rateErrorLabel.setVisible(false);
    }
    
    private void showErrorLabels() {
        medicineNameErrorLabel.setVisible(true);
        batchNumErrorLabel.setVisible(true);
        expiryErrorLabel.setVisible(true);
        rateErrorLabel.setVisible(true);
    }

    private void resetErrorLabels() {
        medicineNameErrorLabel.setErrorText("");
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
