package cityu.csfyp.tychan289.gestureauthentication.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import cityu.csfyp.tychan289.gestureauthentication.roomEntity.FrequencyX;

/**
 * Created by Moonviler on 17/1/18.
 */

@Dao
public interface FrequencyXDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(FrequencyX object);

    @Update
    public void update(FrequencyX object);

    @Delete
    public void delete(FrequencyX object);

    @Query("SELECT * FROM FrequencyX WHERE username = :username")
    public FrequencyX getFrequencyX(String username);
}
