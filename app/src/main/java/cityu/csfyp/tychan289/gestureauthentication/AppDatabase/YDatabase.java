package cityu.csfyp.tychan289.gestureauthentication.AppDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import cityu.csfyp.tychan289.gestureauthentication.dao.FrequencyYDao;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.FrequencyY;

/**
 * Created by Moonviler on 19/1/18.
 */

@Database(version = 1, entities = {FrequencyY.class})
public abstract class YDatabase extends RoomDatabase{
    public abstract FrequencyYDao frequencyYDao();
}
