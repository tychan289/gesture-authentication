package cityu.csfyp.tychan289.gestureauthentication.roomEntity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;

/**
 * Created by Moonviler on 28/03/18.
 */

@Entity(indices = {@Index(value = {"username"}, unique = true)})
public class AccelDataY extends AccelData {
    public AccelDataY(String username, String data) {
        super(username, data);
    }

    @PrimaryKey(autoGenerate = true)
    public int id;
}
