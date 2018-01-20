package cityu.csfyp.tychan289.gestureauthentication;

import java.util.ArrayList;

import cityu.csfyp.tychan289.gestureauthentication.roomEntity.Frequency;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.FrequencyX;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.FrequencyY;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.FrequencyZ;

/**
 * Created by Moonviler on 18/1/18.
 */

public class Classification {

    //range constant for defining class
    private static final int upper_x = 2;
    private static final int lower_x = -2;
    private static final int upper_y = 2;
    private static final int lower_y = -2;
    private static final int upper_z = 2;
    private static final int lower_z = -2;
    private static final int numOfClass = 20;

    public static Frequency classify(ArrayList<Double> list, char dataType, String username) {
        double temp;
        int segment = 0;
        int range;

        //s = (data + lower limit) / (range / numOfClass)
        switch (dataType) {
            case 'x':
                FrequencyX fx = new FrequencyX();
                range = upper_x + Math.abs(lower_x);
                for (Double data : list) {
                    temp = 0;
                    temp = data + Math.abs(lower_x); //Shift lower limit to 0 from negative numbers
                    temp = temp / range * numOfClass;

                    if (temp > numOfClass) {
                        temp = numOfClass - 1;
                    }
                    if (temp < 0) {
                        temp = 0;
                    }
                    segment = (int) temp;
                    fx.add(segment);
                }
                fx.setUsername(username);
                return fx;
            case 'y':
                FrequencyY fy = new FrequencyY();
                range = upper_y + Math.abs(lower_y);
                for (Double data : list) {
                    temp = 0;
                    temp = data + Math.abs(lower_y); //Shift lower limit to 0 from negative numbers
                    temp = temp / range * numOfClass;

                    if (temp > numOfClass) {
                        temp = numOfClass - 1;
                    }
                    if (temp < 0) {
                        temp = 0;
                    }
                    segment = (int) temp;
                    fy.add(segment);
                }
                fy.setUsername(username);
                return fy;
            case 'z':
                FrequencyZ fz = new FrequencyZ();
                range = upper_z + Math.abs(lower_z);
                for (Double data : list) {
                    temp = 0;
                    temp = data + Math.abs(lower_z); //Shift lower limit to 0 from negative numbers
                    temp = temp / range * numOfClass;

                    if (temp > numOfClass) {
                        temp = numOfClass - 1;
                    }
                    if (temp < 0) {
                        temp = 0;
                    }
                    segment = (int) temp;
                    fz.add(segment);
                }
                fz.setUsername(username);
                return fz;
        }
        return new Frequency();
    }

    public static Frequency classify(ArrayList<Double> list, char dataType, String username, Frequency previous) {
        Frequency f = classify(list, dataType, username);
        f = Frequency.average(f, previous);
        return f;
    }
}
