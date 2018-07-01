package cityu.csfyp.tychan289.gestureauthentication;

import java.util.ArrayList;

import cityu.csfyp.tychan289.gestureauthentication.roomEntity.AccelData;

public class DirectionAnalysingUnit {
    public static String run(AccelData accelData) {
        StringBuilder directions = new StringBuilder();
        int temp_int = 0;
        boolean left = false;
        boolean right = false;
        boolean top = false;
        boolean bottom = false;
        ArrayList<Double> data_x = accelData.getData_x();
        ArrayList<Double> data_z = accelData.getData_z();
        ArrayList<Double> data_t = accelData.getData_t();

        for (int i = 0; i < data_t.size() - 2; i++) {
            double temp = data_t.get(i);
            if (temp > 0.85) {
                if (i - 2 >= 0) {
                    if (temp < data_t.get(i - 1) || temp < data_t.get(i - 2)) { //Skip if previous 2 is larger
                        continue;
                    }
                }
                if (temp < data_t.get(i + 1) || temp < data_t.get(i + 2)) { //Skip if next 2 is larger
                    continue;
                } else {
                    double temp_x = data_x.get(i);
                    double temp_z = data_z.get(i);
                    if (temp_x > 0.7) {
                        right = true;
                    } else if (temp_x < -0.7) {
                        left = true;
                    }
                    if (temp_z > 0.7) {
                        top = true;
                    } else if (temp_z < -0.7) {
                        bottom = true;
                    }

                    //Cancel out counter directions
                    if ((left && right) || (top && bottom)) {
                        if (left && right) {
                            left = false;
                            right = false;
                        } else {
                            top = false;
                            bottom = false;
                        }
                    }

                    if (left) { // 1 4 7
                        if (top && temp_int != 1) {
                            directions.append(1);
                            temp_int = 1;
                        } else if (bottom && temp_int != 7) {
                            directions.append(7);
                            temp_int = 7;
                        } else if (temp_int != 4) {
                            directions.append(4);
                            temp_int = 4;
                        }
                    } else if (right) { // 3 6 9
                        if (top && temp_int != 3) {
                            directions.append(3);
                            temp_int = 3;
                        } else if (bottom && temp_int != 9) {
                            directions.append(9);
                            temp_int = 9;
                        } else if (temp_int != 6) {
                            directions.append(6);
                            temp_int = 6;
                        }
                    } else if (top && temp_int != 2) {
                        directions.append(2);
                        temp_int = 2;
                    } else if (bottom && temp_int != 8) {
                        directions.append(8);
                        temp_int = 8;
                    }
                }
            }
        }
        return directions.toString();
    }
}
