package cityu.csfyp.tychan289.gestureauthentication.AppDatabase;

import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import java.util.ArrayList;
import java.util.Arrays;

public class DataConvertor {
    @TypeConverter
    public String fromArrayListDouble(ArrayList<Double> arrayList) {
        StringBuilder builder = new StringBuilder();
        // Append all Integers in StringBuilder to the StringBuilder.
        for (double value : arrayList) {
            builder.append(value);
            builder.append(":");
        }
        // Remove last delimiter with setLength.
        builder.setLength(builder.length() - 1);
        return builder.toString();
    }

    @TypeConverter
    public ArrayList<Double> toArrayListDouble(String s) {
        ArrayList<Double> dList = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>(Arrays.asList(s.split(":")));
        for (String i : list) {
            dList.add(Double.parseDouble(i));
        }
        return dList;
    }
}
