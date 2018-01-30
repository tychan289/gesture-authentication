package cityu.csfyp.tychan289.gestureauthentication.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import cityu.csfyp.tychan289.gestureauthentication.roomEntity.FrequencyY;

/**
 * Created by Moonviler
 */

@Dao
public interface FrequencyYDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(FrequencyY object);

    @Update
    public void update(FrequencyY object);

    @Delete
    public void delete(FrequencyY object);

    @Query("SELECT * FROM FrequencyY WHERE username = :username")
    public FrequencyY getFrequencyY(String username);
}
