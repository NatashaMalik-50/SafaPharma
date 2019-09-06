package com.safapharma.ModelObjects;

public class StockEntry {

    private int id;
    private int billId;
    private int medicineLotId;
    private int quantity;
    private double discountPercentage;
    private double gstPercentage;
    private double amount;
    private String createdAt;
    private String updatedAt;

    public StockEntry(int id, int billId, int medicineLotId, int quantity, double discountPercentage, double gstPercentage, double amount, String createdAt, String updatedAt) {
        this.id = id;
        this.billId = billId;
        this.medicineLotId = medicineLotId;
        this.quantity = quantity;
        this.discountPercentage = discountPercentage;
        this.gstPercentage = gstPercentage;
        this.amount = amount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public StockEntry() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getMedicineLotId() {
        return medicineLotId;
    }

    public void setMedicineLotId(int medicineLotId) {
        this.medicineLotId = medicineLotId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public double getGstPercentage() {
        return gstPercentage;
    }

    public void setGstPercentage(double gstPercentage) {
        this.gstPercentage = gstPercentage;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
