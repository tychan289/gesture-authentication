package cityu.csfyp.tychan289.gestureauthentication.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import cityu.csfyp.tychan289.gestureauthentication.roomEntity.AccelDataT2;

@Dao
public interface AccelDataT2Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(AccelDataT2 object);

    @Update
    public void update(AccelDataT2 object);

    @Delete
    public void delete(AccelDataT2 object);

    @Query("SELECT * FROM AccelDataT2 WHERE username = :username")
    public AccelDataT2 getAccelData(String username);
}
