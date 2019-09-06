package com.safapharma.ModelObjects;

public class AlertType {

    private int id;
    private String type;
    private int level;
    private String createdAt;
    private String updatedAt;

    public AlertType(int id, String type, int level, String createdAt, String updatedAt) {
        this.id = id;
        this.type = type;
        this.level = level;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public AlertType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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
