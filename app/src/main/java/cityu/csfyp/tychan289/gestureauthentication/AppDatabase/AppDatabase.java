package cityu.csfyp.tychan289.gestureauthentication.AppDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import cityu.csfyp.tychan289.gestureauthentication.dao.AccelDataT1Dao;
import cityu.csfyp.tychan289.gestureauthentication.dao.AccelDataT2Dao;
import cityu.csfyp.tychan289.gestureauthentication.dao.AccelDataT3Dao;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.AccelDataT1;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.AccelDataT2;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.AccelDataT3;
import cityu.csfyp.tychan289.gestureauthentication.AppDatabase.DataConvertor;

/**
 * Created by Moonviler
 */
@TypeConverters(DataConvertor.class)
@Database(version = 1, entities = {AccelDataT1.class, AccelDataT2.class, AccelDataT3.class}, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AccelDataT1Dao accelDataT1Dao();

    public abstract AccelDataT2Dao accelDataT2Dao();

    public abstract AccelDataT3Dao accelDataT3Dao();
}
