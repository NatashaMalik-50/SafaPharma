package com.safapharma.ModelObjects;

public class Alerts {

    private int id;
    private int stockEntryId;
    private int alertType;
    private String startDate;
    private int repeat;
    private String endDate;
    private String createdAt;
    private String updatedAt;

    public Alerts(int id, int stockEntryId, int alertType, String startDate, int repeat, String endDate, String createdAt, String updatedAt) {
        this.id = id;
        this.stockEntryId = stockEntryId;
        this.alertType = alertType;
        this.startDate = startDate;
        this.repeat = repeat;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Alerts() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStockEntryId() {
        return stockEntryId;
    }

    public void setStockEntryId(int stockEntryId) {
        this.stockEntryId = stockEntryId;
    }

    public int getAlertType() {
        return alertType;
    }

    public void setAlertType(int alertType) {
        this.alertType = alertType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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
