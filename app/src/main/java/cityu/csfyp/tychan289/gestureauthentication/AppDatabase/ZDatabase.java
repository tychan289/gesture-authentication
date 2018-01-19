package cityu.csfyp.tychan289.gestureauthentication.AppDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import cityu.csfyp.tychan289.gestureauthentication.dao.FrequencyZDao;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.FrequencyZ;

/**
 * Created by Moonviler on 19/1/18.
 */

@Database(version = 1, entities = {FrequencyZ.class})
public abstract class ZDatabase extends RoomDatabase{
    public abstract FrequencyZDao frequencyZDao();
}
