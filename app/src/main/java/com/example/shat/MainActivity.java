package com.example.shat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shat.BDD.Mensaje;
import com.example.shat.BDD.RepositorioMensajes;
import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText inputMensaje;
    Button botonMensaje;
    RepositorioMensajes repositorioMensajes = new RepositorioMensajes(this);
    MensajeVM mensajeVM;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputMensaje = findViewById(R.id.inputMensaje);
        botonMensaje = findViewById(R.id.buttonEnviar);
        recyclerView = findViewById(R.id.recyclerView);
        HiloAyudante hilo = new HiloAyudante(repositorioMensajes);
        hilo.escuchar();
        botonMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputMensaje.getText().toString() != ""){
                    //TODO hiloayudante enviar metodo
                    hilo.escribir(inputMensaje.getText().toString());
                    inputMensaje.setText("");
                }
            }
        });

        MensajeAdapter mensajeAdapter = new MensajeAdapter(new ArrayList<Mensaje>(), this);
        recyclerView.setAdapter(mensajeAdapter);

        mensajeVM = new ViewModelProvider(this).get(MensajeVM.class);
        mensajeVM.build(repositorioMensajes);
        mensajeVM.getMensajes().observe(this, new Observer<List<Mensaje>>() {
            @Override
            public void onChanged(List<Mensaje> mensajes) {
                mensajeAdapter.setItems(mensajes);
            }
        });
    }
}