package com.example.shat;

import android.os.Handler;
import android.os.HandlerThread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Conexion {
    private final String Ip;
    private final int Puerto;

    private final MainActivity mainActivity;

    private HandlerThread htEnvioMensaje;
    private HandlerThread htRecibirMensaje;
    private HandlerThread htConexion;
    private Handler hunderEnvio;
    private Handler hunderRecibir;
    private Handler hunderConexion;
    private Handler hunderPrincipal;

    private Socket socket;
    private BufferedReader br;
    private BufferedWriter bw;

    public Conexion(String ip, int puerto, MainActivity mainActivity) {
        Ip = ip;
        Puerto = puerto;
        this.mainActivity = mainActivity;

        hunderPrincipal = new Handler();

        htEnvioMensaje = new HandlerThread("Envio");
        hunderEnvio = new Handler(htEnvioMensaje.getLooper());

        htRecibirMensaje = new HandlerThread("Recibir");
        hunderRecibir = new Handler(htRecibirMensaje.getLooper());

        htConexion = new HandlerThread("Conexion");
        hunderConexion = new Handler(htConexion.getLooper());
    }
    public void iniciarConexion(){
        hunderConexion.post(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket(Ip, Puerto);
                    br = new BufferedReader(new InputStreamReader( socket.getInputStream()));
                    bw = new BufferedWriter(new OutputStreamWriter( socket.getOutputStream()));
                    recibirMensaje();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void recibirMensaje(){
        hunderRecibir.post(new Runnable(){
            @Override
            public void run(){
                String linea;
                try{
                    while((linea = br.readLine()) != null){
                        hunderPrincipal.post(new Runnable() {
                            @Override
                            public void run() {
                                //TODO mostrar mensaje
                            }
                        });
                    }
                }catch(IOException e){

                }
            }
        });
    }
    public void enviarMensaje(String msj){
        hunderEnvio.post(new Runnable() {
            @Override
            public void run() {
                try {
                    bw.write(msj);
                    bw.newLine();
                    bw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
