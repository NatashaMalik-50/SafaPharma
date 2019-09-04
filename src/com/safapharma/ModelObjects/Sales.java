
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
	

    public Sales(int id, int customerId, int totalQuantity, double totalAmount, double discount, double finalAmount, String , String createdAt, String updatedAt) {
        this.id = id;
        this.customerId = customerId;
		this.totalQuantity=totalQuantity;
		this.totalAmount=totalAmount;
		this.discount=discount;
		this.finalAmount=finalAmount;
		this.doctorPrescriptionUrl=doctorPrescriptionUrl;
		this.createdAt=createdAt;
		this.updatedAt=updatedAt;
		
    }

    public Sales() {
    }
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getcustomerId() {
        return customerId;
    }

    public void setcustomerId(int customerId) {
        this.customerId = customerId;
    }
	
	public int customerId() {
        return customerId;
    }

    public void settotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
	
	public double gettotalAmount() {
        return totalAmount;
    }

    public void settotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
	
	public double getdiscount() {
        return discount;
    }

    public void setdiscount(double discount) {
        this.discount = discount;
    }
	
	public double getfinalAmount() {
        return finalAmount;
    }

    public void setfinal_discount(double finalAmount) {
        this.finalAmount = finalAmount;
    }
	
	public String getdoctorPrescriptionUrl() {
        return doctorPrescriptionUrl;
    }

    public void setdoctorPrescriptionUrl(String doctorPrescriptionUrl) {
        this.doctorPrescriptionUrl = doctorPrescriptionUrl;
    }
	
	public String getcreatedAt() {
        return createdAt;
    }

    public void setcreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
	
	public String getupdatedAt() {
        return updatedAt;
    }

    public void setupdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
       
}
