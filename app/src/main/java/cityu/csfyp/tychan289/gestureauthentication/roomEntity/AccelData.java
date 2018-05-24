package cityu.csfyp.tychan289.gestureauthentication.roomEntity;

import java.util.ArrayList;

import cityu.csfyp.tychan289.gestureauthentication.AppDatabase.DataConvertor;

public class AccelData {
    public AccelData(String username, ArrayList<Double> data_x, ArrayList<Double> data_z, ArrayList<Double> data_t) {
        this.username = username;
        this.data_x = data_x;
        this.data_z = data_z;
        this.data_t = data_t;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Double> getData_x() {
        return data_x;
    }

    public void setData_x(ArrayList<Double> data_x) {
        this.data_x = data_x;
    }

    public ArrayList<Double> getData_z() {
        return data_z;
    }

    public void setData_z(ArrayList<Double> data_z) {
        this.data_z = data_z;
    }

    public ArrayList<Double> getData_t() {
        return data_t;
    }

    public void setData_t(ArrayList<Double> data_t) {
        this.data_t = data_t;
    }

    private String username;
    private ArrayList<Double> data_x;
    private ArrayList<Double> data_z;
    private ArrayList<Double> data_t;
}