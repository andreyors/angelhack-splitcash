package com.angelhack.hackalone.splitcash;

public class ActivityItem {
    private String contactName;
    private String type;
    private String amount;
    private String date;
    private boolean is_active;

    public String getContactName() {
        return contactName;
    }

    public String getType() {
        return type;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public boolean isActive() { return is_active; }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setIsActive(boolean is_active) { this.is_active = is_active; }

    public ActivityItem(String contactName, String type, String amount, String date, boolean is_active){
        this.contactName = contactName;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.is_active = is_active;
    }
}
