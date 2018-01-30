package cityu.csfyp.tychan289.gestureauthentication.roomEntity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Moonviler
 */

@Entity(indices = {@Index(value = {"username"}, unique = true)})
public class FrequencyX extends Frequency{
    @PrimaryKey(autoGenerate = true)
    public int id;
}
