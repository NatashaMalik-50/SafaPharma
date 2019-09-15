package com.safapharma.ModelObjects;

public class MedicineLot {

    private int id;
    private int medicine_id;
    private String batchNo;
    private String expiry;
    private double rate;
    private String createdAt;
    private String updatedAt;

    public MedicineLot(int id, int medicine_id, String batchNo, String expiry, double rate, String createdAt, String updatedAt) {
        this.id = id;
        this.medicine_id = medicine_id;
        this.batchNo = batchNo;
        this.expiry = expiry;
        this.rate = rate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public MedicineLot() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(int medicine_id) {
        this.medicine_id = medicine_id;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
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

    @Override
    public String toString() {
        return "MedicineLot{" + "id=" + id + ", medicine_id=" + medicine_id + ", batchNo=" + batchNo + ", expiry=" + expiry + ", rate=" + rate + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }

}
