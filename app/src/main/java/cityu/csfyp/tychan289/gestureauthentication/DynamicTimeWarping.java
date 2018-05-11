package cityu.csfyp.tychan289.gestureauthentication;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Moonviler on 26/03/18.
 */

public class DynamicTimeWarping {
    public static double run(ArrayList<Double> template, ArrayList<Double> sample) {
        final int length_t = template.size();
        final int length_s = sample.size();
        //window_size = Math.max(window_size, Math.abs(length_t - length_s));

        //Check if input size is zero
        if (length_t == 0 || length_s == 0) {
            Log.e("ERROR", "Input vector for DTW is zero");
            return 0.0;
        }

        //Calculate the difference matrix
        double[][] dist_l = new double[length_t][length_s];

        for (int i = 0; i < length_t; i++) {
            for (int j = 0; j < length_s; j++) {
                dist_l[i][j] = Math.abs(template.get(i) - sample.get(j));
            }
        }

        //Calculate the distance matrix
        double[][] dist_g = new double[length_t][length_s];
        dist_g[0][0] = dist_l[0][0]; //First

        //First row
        for (int i = 1; i < length_t; i++) {
            dist_g[i][0] = dist_l[i][0] + dist_g[i - 1][0];
        }

        //First column
        for (int j = 1; j < length_s; j++) {
            dist_g[0][j] = dist_l[0][j] + dist_g[0][j - 1];
        }

        //Remaining
        for (int i = 1; i < length_t; i++) {
            for (int j = 1; j < length_s; j++) {
                dist_g[i][j] = Math.min(Math.min(dist_g[i - 1][j], dist_g[i][j - 1]), dist_g[i - 1][j - 1]) + dist_l[i][j];
            }
        }

        //Add all the minimum values
        int i = length_t - 1;
        int j = length_s - 1;
        double result = dist_g[i + 1][j + 1];

        while (i > 0 && j > 0) {
            double backward = dist_g[i - 1][j];
            double downward = dist_g[i][j - 1];
            double diagonal = dist_g[i - 1][j - 1];

            //if diagonal equals to or smaller than any
            if (diagonal <= backward) {
                if (diagonal <= downward) {
                    result += diagonal;
                    i--;
                    j--;
                } else {
                    result += downward;
                    j--;
                }
            } else if (diagonal <= downward) {
                if (diagonal <= backward) {
                    result += diagonal;
                    i--;
                    j--;
                } else {
                    result += backward;
                    i--;
                }
            } else {
                if (backward <= downward){
                    result += backward;
                    i--;
                } else {
                    result += downward;
                    j--;
                }
            }
        }
        return result;
    }
}
