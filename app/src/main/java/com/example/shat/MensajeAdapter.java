package com.example.shat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shat.BDD.Mensaje;

import java.util.ArrayList;
import java.util.List;

public class MensajeAdapter extends RecyclerView.Adapter<MensajeAdapter.ViewHolder> {
    private List<Mensaje> mensajes;
    private LayoutInflater mInflater;
    private Context context;

    public MensajeAdapter(List<Mensaje> mensajes, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mensajes = mensajes;
    }
    public int getItemCount() { return mensajes.size(); }

    @Override
    public MensajeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.mensaje, null);
        return new MensajeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MensajeAdapter.ViewHolder holder, final int position){
        holder.bindData(mensajes.get(position));
    }
    public void setItems(List<Mensaje> mensajes) { this.mensajes = mensajes;}

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mensaje, mensajeEnviado;
        LinearLayout llegado, enviado;
        ViewHolder(View itemView){
            super(itemView);
            mensaje = itemView.findViewById(R.id.mensaje);
            mensajeEnviado = itemView.findViewById(R.id.mensajeEn);
            llegado = itemView.findViewById(R.id.mensajeLlegado);
            enviado = itemView.findViewById(R.id.mensajeEnviado);
        }
        void bindData(final Mensaje msg){
            if(msg.isEnviado()){
                enviado.setVisibility(View.VISIBLE);
                llegado.setVisibility(View.INVISIBLE);
                mensajeEnviado.setText(msg.getMensaje());
            }else{
                llegado.setVisibility(View.VISIBLE);
                enviado.setVisibility(View.INVISIBLE);
                mensaje.setText(msg.getMensaje());
            }
        }
    }
}
