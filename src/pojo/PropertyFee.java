package pojo;

import java.util.Date;

/**
 * 物业费用实体类
 */
public class PropertyFee {
    private int id;
    private String title;
    private String content;
    private double amount;
    private Date dueDate;
    private String status;
    private int residentId;

    public PropertyFee() {}

    public PropertyFee(int id, String title, String content, double amount, Date dueDate, String status, int residentId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.amount = amount;
        this.dueDate = dueDate;
        this.status = status;
        this.residentId = residentId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getResidentId() {
        return residentId;
    }

    public void setResidentId(int residentId) {
        this.residentId = residentId;
    }
} 