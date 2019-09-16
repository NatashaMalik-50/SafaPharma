/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Stock;

import com.safapharma.MedicineLot.*;
import com.safapharma.Customer.CustomerBackend;
import com.safapharma.Main.MainWindow;
import com.safapharma.Templates.DialogForm;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 *
 * @author Karan
 */
public class UpdateStockForm extends DialogForm {    
    private MainWindow manager;
        
    private DialogForm.FormLabel companyNameLabel;
    private DialogForm.FormText companyNameText;
    
    
    private DialogForm.FormLabel medicineNameLabel;
    private DialogForm.FormText medicineNameText;
    
    
    private DialogForm.FormLabel quantityLabel;
    private DialogForm.FormText quantityText;
    
    
    private DialogForm.FormLabel rateLabel;
    private DialogForm.FormText rateText;
    
    private DialogForm.FormLabel batchLabel;
    private DialogForm.FormText batchText;
    
    private DialogForm.FormLabel expiryLabel;
    private DialogForm.FormText expiryText;
    
    private DialogForm.FormLabel supplierNameLabel;
    private DialogForm.FormText supplierNameText;
    
    private DialogForm.FormButton updateButton;
    
    private MedicineLotBackend medicineLotBackend;   
    
    

    public UpdateStockForm(MainWindow manager,Vector selectedObject) {
        this.manager = manager;
        this.medicineLotBackend = medicineLotBackend;
        initUI();        
        addListeners();
        
        System.out.println(selectedObject);
        System.out.println("View object clicked");
        
        companyNameText.setText(selectedObject.get(1).toString());
        medicineNameText.setText(selectedObject.get(2).toString());
        quantityText.setText(selectedObject.get(3).toString());
        rateText.setText(selectedObject.get(4).toString());
        batchText.setText(selectedObject.get(5).toString());
        expiryText.setText(selectedObject.get(6).toString());
        supplierNameText.setText(selectedObject.get(7).toString());
        
        
    }  

    private void initUI() {
        
        //Set and configure layout
        getFormLabel().setText("Update Stock Form");
        System.out.println("Viewing Medicine Lot Form ");
        
        companyNameLabel = new DialogForm.FormLabel("Company Name");
        companyNameText = new DialogForm.FormText();

        medicineNameLabel = new DialogForm.FormLabel("Medicine Name");
        medicineNameText = new DialogForm.FormText();        

        quantityLabel = new DialogForm.FormLabel("Quantity");
        quantityText = new DialogForm.FormText();        

        rateLabel = new DialogForm.FormLabel("Rate");
        rateText = new DialogForm.FormText();

        batchLabel = new DialogForm.FormLabel("Batch");
        batchText = new DialogForm.FormText();
        
        expiryLabel = new DialogForm.FormLabel("Expiry");
        expiryText = new DialogForm.FormText();
        
        supplierNameLabel = new DialogForm.FormLabel("Supplier Name");
        supplierNameText = new DialogForm.FormText();
        
        updateButton = new DialogForm.FormButton("Update Stock");
        
//        companyNameText.setEditable(false);
//        medicineNameText.setEditable(false);
//        quantityText.setEditable(false);
//        rateText.setEditable(false);        
//        batchText.setEditable(false);
//        expiryText.setEditable(false);
//        supplierNameText.setEditable(false);
        
//        medicineNameText.setEnabled(false);
//        batchNumText.setEnabled(false);
//        expiryText.setEnabled(false);
//        rateText.setEnabled(false);        
//        inStockText.setEnabled(false);
        
        getFormPanel().add(companyNameLabel);
        getFormPanel().add(companyNameText);
        getFormPanel().add(new JPanel());
        
        getFormPanel().add(medicineNameLabel);
        getFormPanel().add(medicineNameText);
        getFormPanel().add(new JPanel());
        
        getFormPanel().add(quantityLabel);
        getFormPanel().add(quantityText);
        getFormPanel().add(new JPanel());
        
        getFormPanel().add(rateLabel);
        getFormPanel().add(rateText);
        getFormPanel().add(new JPanel());
        
        getFormPanel().add(batchLabel);
        getFormPanel().add(batchText);
        getFormPanel().add(new JPanel());
        
        getFormPanel().add(expiryLabel);
        getFormPanel().add(expiryText);
        getFormPanel().add(new JPanel());
        
        getFormPanel().add(supplierNameLabel);
        getFormPanel().add(supplierNameText);
        getFormPanel().add(new JPanel());
        
        getButtonPanel().add(updateButton);
        getFormPanel().add(new JPanel());

        this.pack();
    }

    private void addListeners() {
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                // Close the form once this is clicked.
                //TODO : Close the form once this is clicked.
                
            }
        });
    }

private void checkInStock(){
    try{
        //Get stock from dao. If stock exists :        
        boolean stockExists = true;
        if(stockExists){
            displayTable();
        }        
    }
    catch(Exception e){
        e.printStackTrace();
    }
}

private void displayTable(){
    // Code to display the table in the view button.

}

    @Override
    protected void deleteScreen() {
                manager.deleteViewMedicineLotForm();
    }
    
}
