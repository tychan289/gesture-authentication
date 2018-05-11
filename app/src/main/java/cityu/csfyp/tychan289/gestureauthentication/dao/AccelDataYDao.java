package cityu.csfyp.tychan289.gestureauthentication.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import cityu.csfyp.tychan289.gestureauthentication.roomEntity.AccelDataX;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.AccelDataY;

@Dao
public interface AccelDataYDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(AccelDataY object);

    @Update
    public void update(AccelDataY object);

    @Delete
    public void delete(AccelDataY object);

    @Query("SELECT * FROM AccelDataY WHERE username = :username")
    public AccelDataY getAccelData(String username);
}
