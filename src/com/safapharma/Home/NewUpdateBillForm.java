/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Home;

import com.safapharma.Main.MainWindow;
import com.safapharma.ModelObjects.DataWithColumn;
import com.safapharma.Templates.DialogForm;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;

/**
 *
 * @author shiva
 */
public class NewUpdateBillForm extends DialogForm {
    private MainWindow manager;
    private NewUpdateBillFormBackend newUpdateBillFormBackend;
    private HomeScreenPanel homeScreenPanel;
    private DataWithColumn QuantityData;
    private DialogForm.FormLabel QuantityLabel;
    private DialogForm.FormText QuantityText;
    private DialogForm.ErrorLabel QuantityLabelError;
    private DialogForm.FormButton Btnsubmit;
    
     public NewUpdateBillForm(MainWindow manager, HomeScreenPanel homeScreenPanel,int id,int currQuantity,int maxQuantity) {
        this.manager = manager;
        this.homeScreenPanel = homeScreenPanel;
        newUpdateBillFormBackend = new NewUpdateBillFormBackend(id,currQuantity,maxQuantity);
        initUI();        
        addListeners();
    }
    @Override
    protected void deleteScreen() {
        manager.deleteNewUpdateBillForm();
    }

    private void initUI() {
        try{
            loadBillData();
        }
        catch(Exception e)
        {
            System.out.println(e);
            //System.out.println("HEllo");
        } 
        
        getFormLabel().setText("Update Bill Form");
        QuantityLabel = new FormLabel("Quantity :");
        QuantityText = new FormText();
        //QuantityText.setText(QuantityData.getDataOf(0).get(1).toString());
        int curr=newUpdateBillFormBackend.getCurrQuantity();
        QuantityText.setText(""+curr);
        //QuantityText.setAlignmentX(10);
        QuantityLabelError = new ErrorLabel();
        QuantityLabelError.setVisible(false);
        QuantityLabelError.setText("This Much Quantity Not In Stock");
        Btnsubmit = new FormButton("Submit");
        getFormPanel().add(QuantityLabel);
        getFormPanel().add(QuantityText);
        getFormPanel().add(QuantityLabelError);
        //getFormPanel().setLayout(new BoxLayout(getFormLabel(), BoxLayout.Y_AXIS));
        getButtonPanel().add(Btnsubmit);
//        getFormPanel().add(Btnsubmit);
        
                
    }
    private void loadBillData() throws Exception {
         //int id = newUpdateBillFormBackend.getMyId();
         
        //QuantityData = newUpdateBillFormBackend.setQuantityIntoDailog(id);
        //tableModel.setDataVector(null, stockData.getColumnNames());
        //tableModel.setColumnIdentifiers(column);
        //billEntriesTable.setModel(tableModel);
         //System.out.println(id);
         //System.out.println(BillData);
    }

    private void addListeners() {
            Btnsubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int MaxQuant = newUpdateBillFormBackend.getMaxQuantity();
                    
                    if(Integer.parseInt(QuantityText.getText())>MaxQuant)
                    {
                        QuantityLabelError.setVisible(true);
                    }
                    else
                    {
                        //String exactSrno =(String);
                        int qtn = Integer.parseInt(QuantityText.getText());
                        homeScreenPanel.updateQuantity(qtn,newUpdateBillFormBackend.getMyId());
                        manager.deleteNewUpdateBillForm();
                    }
                }
                catch(Exception em)
                {
                    System.out.println(em);
                }
            }
            });
    }    
}
