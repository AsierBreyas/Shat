package com.example.shat;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.widget.Toast;

import com.example.shat.BDD.Mensaje;
import com.example.shat.BDD.RepositorioMensajes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class HiloAyudante {
    Socket socket;
    BufferedWriter bw;
    BufferedReader br;
    HandlerThread hdEscuchar;
    Handler hEscuchar;
    HandlerThread hdEscribir;
    Handler hEscribir;
    RepositorioMensajes repositorioMensajes;

    public HiloAyudante(RepositorioMensajes repositorioMensajes) {
        hdEscuchar = new HandlerThread("Escuchar");
        hdEscuchar.start();
        Looper looper = hdEscuchar.getLooper();
        hEscuchar = new Handler(looper);
        hdEscribir = new HandlerThread("Escribir");
        hdEscribir.start();
        Looper looper2 = hdEscribir.getLooper();
        hEscribir = new Handler(looper2);
        hEscuchar.post(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket("10.0.2.2", 5555);
                    bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void escuchar() {
        hEscuchar.post(new Runnable() {
            @Override
            public void run() {
                String salida;
                while (true) {
                    try {
                        while ((salida = br.readLine()) != null) {
                            // TODO Cartas
                            Mensaje mensaje1 = new Mensaje(salida, false);
                            repositorioMensajes.insertar(mensaje1);
                            System.out.println(salida);
                        }
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    public void escribir(String mensaje){
        hEscribir.post(new Runnable() {
            @Override
            public void run() {
                try {
                    bw.write(mensaje);
                    bw.newLine();
                    bw.flush();
                    Mensaje mensaje1 = new Mensaje(mensaje, true);
                    repositorioMensajes.insertar(mensaje1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
