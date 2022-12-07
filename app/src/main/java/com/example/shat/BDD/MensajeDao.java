package com.example.shat.BDD;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MensajeDao {
    @Query("SELECT * FROM mensaje")
    LiveData<List<Mensaje>> getAll();
    @Insert
    void insertAll(Mensaje... mensajes);
    @Delete
    void delete(Mensaje mensaje);
}
