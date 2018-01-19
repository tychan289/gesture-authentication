package cityu.csfyp.tychan289.gestureauthentication.AppDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import cityu.csfyp.tychan289.gestureauthentication.dao.FrequencyXDao;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.FrequencyX;

/**
 * Created by Moonviler on 19/1/18.
 */

@Database(version = 1, entities = {FrequencyX.class})
public abstract class XDatabase extends RoomDatabase {
    public abstract FrequencyXDao frequencyXDao();
}
