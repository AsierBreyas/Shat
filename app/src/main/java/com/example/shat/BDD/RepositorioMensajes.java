package com.example.shat.BDD;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RepositorioMensajes {
    private final LiveData<List<Mensaje>> rMensajes = null;
    public final MensajeDao rMensajesDao;

    public RepositorioMensajes(Context context){
        AppDatabase db = AppDatabase.getInstance(context);
        rMensajesDao = db.mensajeDao();
    }
    public LiveData<List<Mensaje>> getMensajes(){
        AppDatabase.dbExecutor.execute(
                () -> rMensajesDao.getAll()
        );
        return rMensajes;
    }
    public void insertar(Mensaje mensaje){
        AppDatabase.dbExecutor.execute(
                () -> rMensajesDao.insertAll(mensaje)
        );
    }
}
