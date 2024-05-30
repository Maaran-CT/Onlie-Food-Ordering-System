package DAO;

public class MenuDAO {
    private int id, cost;
    private String itemName, time;

    // Constructor
    public MenuDAO(int id, int cost, String itemName, String time) {
        this.id = id;
        this.cost = cost;
        this.itemName = itemName;
        this.time = time;
    }

    // Getter and Setter methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
