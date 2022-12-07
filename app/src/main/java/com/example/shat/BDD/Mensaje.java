package com.example.shat.BDD;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Mensaje {
    @PrimaryKey
    public int id;
    @ColumnInfo(name="mensaje")
    public String mensaje;
    @ColumnInfo(name="enviado")
    public boolean enviado;

    public Mensaje(String mensaje, boolean enviado) {
        this.mensaje = mensaje;
        this.enviado = enviado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isEnviado() {
        return enviado;
    }

    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }
}
