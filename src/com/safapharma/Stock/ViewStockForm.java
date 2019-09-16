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
public class ViewStockForm extends DialogForm {    
    private MainWindow manager;
        
    private FormLabel companyNameLabel;
    private FormText companyNameText;
    
    
    private FormLabel medicineNameLabel;
    private FormText medicineNameText;
    
    
    private FormLabel quantityLabel;
    private FormText quantityText;
    
    
    private FormLabel rateLabel;
    private FormText rateText;
    
    private FormLabel batchLabel;
    private FormText batchText;
    
    private FormLabel expiryLabel;
    private FormText expiryText;
    
    private FormLabel supplierNameLabel;
    private FormText supplierNameText;
    
    private FormButton closeButton;    
    
    private MedicineLotBackend medicineLotBackend;   
    
    

    public ViewStockForm(MainWindow manager,Vector selectedObject) {
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

    public ViewStockForm(MainWindow aThis, CustomerBackend customerBackend) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
    

    private void initUI() {
        
        //Set and configure layout
        getFormLabel().setText("View Stock Form");
        System.out.println("Viewing Medicine Lot Form ");
        
        companyNameLabel = new FormLabel("Company Name");
        companyNameText = new FormText();

        medicineNameLabel = new FormLabel("Medicine Name");
        medicineNameText = new FormText();        

        quantityLabel = new FormLabel("Quantity");
        quantityText = new FormText();        

        rateLabel = new FormLabel("Rate");
        rateText = new FormText();

        batchLabel = new FormLabel("Batch");
        batchText = new FormText();
        
        expiryLabel = new FormLabel("Expiry");
        expiryText = new FormText();
        
        supplierNameLabel = new FormLabel("Supplier Name");
        supplierNameText = new FormText();
        
        closeButton = new FormButton("Close");
        
        companyNameText.setEditable(false);
        medicineNameText.setEditable(false);
        quantityText.setEditable(false);
        rateText.setEditable(false);        
        batchText.setEditable(false);
        expiryText.setEditable(false);
        supplierNameText.setEditable(false);
        
//        medicineNameText.setEnabled(false);
//        batchNumText.setEnabled(false);
//        expiryText.setEnabled(false);
//        rateText.setEnabled(false);        
//        inStockText.setEnabled(false);
        
        getFormPanel().add(supplierNameLabel);
        getFormPanel().add(supplierNameText);
        getFormPanel().add(new JPanel());
        
        getFormPanel().add(companyNameLabel);
        getFormPanel().add(companyNameText);
        getFormPanel().add(new JPanel());
        
        getFormPanel().add(medicineNameLabel);
        getFormPanel().add(medicineNameText);
        getFormPanel().add(new JPanel());
        
        getFormPanel().add(batchLabel);
        getFormPanel().add(batchText);
        getFormPanel().add(new JPanel());
        
        getFormPanel().add(quantityLabel);
        getFormPanel().add(quantityText);
        getFormPanel().add(new JPanel());
        
        getFormPanel().add(rateLabel);
        getFormPanel().add(rateText);
        getFormPanel().add(new JPanel());
        
        
        getFormPanel().add(expiryLabel);
        getFormPanel().add(expiryText);
        getFormPanel().add(new JPanel());
        
        
        
        getButtonPanel().add(closeButton);
        

        this.pack();
    }

    private void addListeners() {
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                // Close the form once this is clicked.
                //TODO : Close the form once this is clicked.
                deleteScreen();
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
                manager.deleteViewStockForm();
    }
    
}
