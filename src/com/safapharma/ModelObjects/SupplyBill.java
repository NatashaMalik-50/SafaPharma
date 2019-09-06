package com.safapharma.ModelObjects;

public class SupplyBill {

    private int id;
    private String billNo;
    private int supplierId;
    private String billDate;
    private double amount;
    private double discount;
    private double tax;
    private double netAmount;
    private String createdAt;
    private String updatedAt;

    public SupplyBill(int id, String billNo, int supplierId, String billDate, double amount, double discount, double tax, double netAmount, String createdAt, String updatedAt) {
        this.id = id;
        this.billNo = billNo;
        this.supplierId = supplierId;
        this.billDate = billDate;
        this.amount = amount;
        this.discount = discount;
        this.tax = tax;
        this.netAmount = netAmount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public SupplyBill() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(double netAmount) {
        this.netAmount = netAmount;
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
