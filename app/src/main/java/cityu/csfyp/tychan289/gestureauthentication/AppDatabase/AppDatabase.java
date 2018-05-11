package cityu.csfyp.tychan289.gestureauthentication.AppDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import cityu.csfyp.tychan289.gestureauthentication.dao.AccelDataXDao;
import cityu.csfyp.tychan289.gestureauthentication.dao.AccelDataYDao;
import cityu.csfyp.tychan289.gestureauthentication.dao.AccelDataZDao;
import cityu.csfyp.tychan289.gestureauthentication.dao.FrequencyXDao;
import cityu.csfyp.tychan289.gestureauthentication.dao.FrequencyYDao;
import cityu.csfyp.tychan289.gestureauthentication.dao.FrequencyZDao;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.AccelDataX;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.AccelDataY;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.AccelDataZ;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.FrequencyX;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.FrequencyY;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.FrequencyZ;

/**
 * Created by Moonviler
 */

@Database(version = 1, entities = {FrequencyX.class, FrequencyY.class, FrequencyZ.class, AccelDataX.class, AccelDataY.class, AccelDataZ.class})
public abstract class AppDatabase extends RoomDatabase{
//    public abstract FrequencyXDao frequencyXDao();
//    public abstract FrequencyYDao frequencyYDao();
//    public abstract FrequencyZDao frequencyZDao();
    public abstract AccelDataXDao accelDataXDao();
    public abstract AccelDataYDao accelDataYDao();
    public abstract AccelDataZDao accelDataZDao();
}
