package cityu.csfyp.tychan289.gestureauthentication.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import cityu.csfyp.tychan289.gestureauthentication.roomEntity.AccelDataT1;

@Dao
public interface AccelDataT1Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(AccelDataT1 object);

    @Update
    public void update(AccelDataT1 object);

    @Delete
    public void delete(AccelDataT1 object);

    @Query("SELECT * FROM AccelDataT1 WHERE username = :username")
    public AccelDataT1 getAccelData(String username);
}
