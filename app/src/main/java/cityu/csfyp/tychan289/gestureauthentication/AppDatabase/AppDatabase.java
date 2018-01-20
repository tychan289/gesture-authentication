package cityu.csfyp.tychan289.gestureauthentication.AppDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import cityu.csfyp.tychan289.gestureauthentication.dao.FrequencyXDao;
import cityu.csfyp.tychan289.gestureauthentication.dao.FrequencyYDao;
import cityu.csfyp.tychan289.gestureauthentication.dao.FrequencyZDao;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.FrequencyX;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.FrequencyY;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.FrequencyZ;

/**
 * Created by Moonviler on 20/1/18.
 */

@Database(version = 1, entities = {FrequencyX.class, FrequencyY.class, FrequencyZ.class})
public abstract class AppDatabase extends RoomDatabase{
    public abstract FrequencyXDao frequencyXDao();
    public abstract FrequencyYDao frequencyYDao();
    public abstract FrequencyZDao frequencyZDao();
}
