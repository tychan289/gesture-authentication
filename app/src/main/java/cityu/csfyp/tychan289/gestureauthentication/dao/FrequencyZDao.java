package cityu.csfyp.tychan289.gestureauthentication.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import cityu.csfyp.tychan289.gestureauthentication.roomEntity.FrequencyX;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.FrequencyZ;

/**
 * Created by Moonviler on 17/1/18.
 */

@Dao
public interface FrequencyZDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(FrequencyZ object);

    @Update
    public void update(FrequencyZ object);

    @Delete
    public void delete(FrequencyZ object);

    @Query("SELECT * FROM FrequencyZ WHERE username = :username")
    public FrequencyZ getFrequencyZ(String username);
}
