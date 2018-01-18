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
    private static final int numOfClass = 10;

    public static Frequency classify(ArrayList<Double> list, char dataType) {
        Frequency object = new Frequency();
        double temp = 0;
        int segment = 0;

        switch (dataType) {
            case 'x':
                for (Double data : list) {
                    temp = data + Math.abs(lower_x);
                    temp = temp / (upper_x + Math.abs(lower_x));
                    temp = temp / ((upper_x + Math.abs(lower_x)) / numOfClass);
//                    temp = ((data + Math.abs(lower_x)) / (upper_x + Math.abs(lower_x))) / ((upper_x + Math.abs(lower_x)) / numOfClass);
                    if (temp > upper_x) {
                        temp = numOfClass - 1;
                    }
                    if (temp < lower_x) {
                        temp = 0;
                    }
                    segment = (int) temp;
                    object.add(segment);
                }
            case 'y':
                for (Double data : list) {
                    temp = ((data + Math.abs(lower_y)) / (upper_y + Math.abs(lower_y))) / ((upper_y + Math.abs(lower_y)) / numOfClass);
                    if (temp > upper_y) {
                        temp = numOfClass - 1;
                    }
                    if (temp < lower_y) {
                        temp = 0;
                    }
                    segment = (int) temp;
                    object.add(segment);
                }
            case 'z':
                for (Double data : list) {
                    temp = ((data + Math.abs(lower_y)) / (upper_z + Math.abs(lower_z))) / ((upper_z + Math.abs(lower_z)) / numOfClass);
                    if (temp > upper_z) {
                        temp = numOfClass - 1;
                    }
                    if (temp < lower_z) {
                        temp = 0;
                    }
                    segment = (int) temp;
                    object.add(segment);
                }
        }
        return object;
    }

    public static Frequency classify(ArrayList<Double> list, char dataType, Frequency previous) {
        Frequency object = classify(list, dataType);
        object = Frequency.average(object, previous);
        return object;
    }
}
