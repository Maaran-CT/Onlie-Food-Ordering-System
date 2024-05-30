package DAO;

public class Bill {
    private int billId, deliveryCost, foodCost, totalCost;
    private String loginId, food, foodTime, deliverTime, bookTime, totalTime, status;

    public Bill(int billId, String loginId, String food, int deliveryCost, int foodCost, String foodTime, String deliverTime, String bookTime, String totalTime, int totalCost, String status) {
        this.billId = billId;
        this.loginId = loginId;
        this.food = food;
        this.deliveryCost = deliveryCost;
        this.foodCost = foodCost;
        this.foodTime = foodTime;
        this.deliverTime = deliverTime;
        this.bookTime = bookTime;
        this.totalTime = totalTime;
        this.totalCost = totalCost;
        this.status = status;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(int deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public int getFoodCost() {
        return foodCost;
    }

    public void setFoodCost(int foodCost) {
        this.foodCost = foodCost;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getFoodTime() {
        return foodTime;
    }

    public void setFoodTime(String foodTime) {
        this.foodTime = foodTime;
    }

    public String getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(String deliverTime) {
        this.deliverTime = deliverTime;
    }

    public String getBookTime() {
        return bookTime;
    }

    public void setBookTime(String bookTime) {
        this.bookTime = bookTime;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
