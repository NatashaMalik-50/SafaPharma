package com.safapharma.ModelObjects;

public class SaleEntry {

    private int id;
    private int saleId;
    private int stockEntryId;
    private double rate;
    private int quantity;
    private double discount;
    private double finalAmount;
    private boolean isReturnable;
    private String createdAt;
    private String updatedAt;

    public SaleEntry(int id, int saleId, int stockEntryId, double rate, int quantity, double discount, double finalAmount, boolean isReturnable, String createdAt, String updatedAt) {
        this.id = id;
        this.saleId = saleId;
        this.stockEntryId = stockEntryId;
        this.rate = rate;
        this.quantity = quantity;
        this.discount = discount;
        this.finalAmount = finalAmount;
        this.isReturnable = isReturnable;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public SaleEntry() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getStockEntryId() {
        return stockEntryId;
    }

    public void setStockEntryId(int stockEntryId) {
        this.stockEntryId = stockEntryId;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
    }

    public boolean isIsReturnable() {
        return isReturnable;
    }

    public void setIsReturnable(boolean isReturnable) {
        this.isReturnable = isReturnable;
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
