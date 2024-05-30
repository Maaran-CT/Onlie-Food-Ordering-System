package DAO;

public class DistanceDAO {
    private String pincode;
    private String time;
    private int cost;

    public DistanceDAO(String pincode, String time, int cost) {
        this.pincode = pincode;
        this.time = time;
        this.cost = cost;
    }


    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
