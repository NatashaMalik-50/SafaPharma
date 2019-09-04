package com.safapharma.ModelObjects;

public class Company {

    private int id;
    private String name;
    private String otherDetails;
    private String createdAt;
    private String updatedAt;

    public Company(int id, String name, String otherDetails, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
        this.otherDetails = otherDetails;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Company() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
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
