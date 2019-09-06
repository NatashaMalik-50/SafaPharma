package com.safapharma.ModelObjects;

public class Medicine {

    private int id;
    private int companyId;
    private int categoryId;
    private String name;
    private String otherDetails;
    private String createdAt;
    private String updatedAt;

    public Medicine(int id, int companyId, int categoryId, String name, String otherDetails, String createdAt, String updatedAt) {
        this.id = id;
        this.companyId = companyId;
        this.categoryId = categoryId;
        this.name = name;
        this.otherDetails = otherDetails;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Medicine() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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
