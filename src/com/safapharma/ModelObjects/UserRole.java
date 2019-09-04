
package com.safapharma.ModelObjects;



public class UserRole {
    
    private int userId;
    private int roleId;
	private String createdAt;
	private String updatedAt;

    public UserRole(int userId, int roleId, String createdAt, String updatedAt) {
        this.userId = userId;
        this.roleId = roleId;
		this.createdAt=createdAt;
		this.updatedAt=updatedAt;
    }

    public UserRole() {
    }
        
    public int getuserId() {
        return userId;
    }

    public void setuserId(int userId) {
        this.userId = userId;
    }

    public int getroleId() {
        return roleId;
    }

    public void setroleId(int roleId) {
        this.roleId = roleId;
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
