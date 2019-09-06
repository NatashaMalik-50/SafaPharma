package com.safapharma.ModelObjects;

public class Sales {

    private int id;
    private int customerId;
    private int totalQuantity;
    private double totalAmount;
    private double discount;
    private double finalAmount;
    private String doctorPrescriptionUrl;
    private String createdAt;
    private String updatedAt;

    public Sales(int id, int customerId, int totalQuantity, double totalAmount, double discount, double finalAmount, String doctorPrescriptionUrl, String createdAt, String updatedAt) {
        this.id = id;
        this.customerId = customerId;
        this.totalQuantity = totalQuantity;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.finalAmount = finalAmount;
        this.doctorPrescriptionUrl = doctorPrescriptionUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Sales() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
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

    public String getDoctorPrescriptionUrl() {
        return doctorPrescriptionUrl;
    }

    public void setDoctorPrescriptionUrl(String doctorPrescriptionUrl) {
        this.doctorPrescriptionUrl = doctorPrescriptionUrl;
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
