package cityu.csfyp.tychan289.gestureauthentication.roomEntity;

import java.util.ArrayList;
import java.util.Arrays;

public class AccelData {
    public AccelData(String username, String data) {
        this.username = username;
        this.data = data;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private String username;
    private String data;

}