package com.example.shat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.shat.BDD.Mensaje;
import com.example.shat.BDD.RepositorioMensajes;

import java.util.List;

public class MensajeVM extends ViewModel {
    private RepositorioMensajes repositorioMensajes;

    public void build(RepositorioMensajes repositorioMensajes) {
        this.repositorioMensajes = repositorioMensajes;
    }
    public LiveData<List<Mensaje>> getMensajes(){
        return repositorioMensajes.getMensajes();
    }
}
