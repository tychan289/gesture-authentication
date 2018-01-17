package cityu.csfyp.tychan289.gestureauthentication.roomEntity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Moonviler on 17/1/18.
 */

@Entity(indices = {@Index(value = {"username"}, unique = true)})
public class FrequencyZ {
    @PrimaryKey
    public int id;

    public String username;
    public int class_0;
    public int class_1;
    public int class_2;
    public int class_3;
    public int class_4;
    public int class_5;
    public int class_6;
    public int class_7;
    public int class_8;
    public int class_9;
}
