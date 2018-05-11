package cityu.csfyp.tychan289.gestureauthentication.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import cityu.csfyp.tychan289.gestureauthentication.roomEntity.AccelDataX;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.AccelDataZ;

@Dao
public interface AccelDataZDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(AccelDataZ object);

    @Update
    public void update(AccelDataZ object);

    @Delete
    public void delete(AccelDataZ object);

    @Query("SELECT * FROM AccelDataZ WHERE username = :username")
    public AccelDataZ getAccelData(String username);
}
