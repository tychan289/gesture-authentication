package cityu.csfyp.tychan289.gestureauthentication.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import cityu.csfyp.tychan289.gestureauthentication.roomEntity.AccelDataX;

@Dao
public interface AccelDataXDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(AccelDataX object);

    @Update
    public void update(AccelDataX object);

    @Delete
    public void delete(AccelDataX object);

    @Query("SELECT * FROM AccelDataX WHERE username = :username")
    public AccelDataX getAccelData(String username);
}
