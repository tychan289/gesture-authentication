package cityu.csfyp.tychan289.gestureauthentication.roomEntity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;

/**
 * Created by Moonviler on 28/03/18.
 */

@Entity(indices = {@Index(value = {"username"}, unique = true)})
public class AccelDataT2 extends AccelData {
    public AccelDataT2(String username, ArrayList<Double> data_x, ArrayList<Double> data_z, ArrayList<Double> data_t) {
        super(username, data_x, data_z, data_t);
    }

    @PrimaryKey(autoGenerate = true)
    public int id;
}
