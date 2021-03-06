package com.safapharma.ModelObjects;

public class Supplier {

    private int id;
    private String name;
    private String address;
    private String contactNo;
    private String email;
    private String createdAt;
    private String updatedAt;

    public Supplier(String name, String address, String contactNo, String email) {
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
        this.email = email;
    }

    public Supplier() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return "Supplier{" + "id=" + id + ", name=" + name + ", address=" + address + ", contactNo=" + contactNo + ", email=" + email + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
    
    
    

}
