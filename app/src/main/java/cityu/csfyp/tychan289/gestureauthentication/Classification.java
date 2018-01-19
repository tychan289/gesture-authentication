package cityu.csfyp.tychan289.gestureauthentication;

import java.util.ArrayList;

import cityu.csfyp.tychan289.gestureauthentication.roomEntity.Frequency;

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

    public static Frequency classify(ArrayList<Double> list, char dataType) {
        Frequency object = new Frequency();
        double temp;
        int segment = 0;
        int range;

        //s = (data + lower limit) / (range / numOfClass)
        switch (dataType) {
            case 'x':
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
                    object.add(segment);
                }
                break;
            case 'y':
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
                    object.add(segment);
                }
                break;
            case 'z':
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
                    object.add(segment);
                }
                break;
        }
        return object;
    }

    public static Frequency classify(ArrayList<Double> list, char dataType, Frequency previous) {
        Frequency object = classify(list, dataType);
        object = Frequency.average(object, previous);
        return object;
    }
}
