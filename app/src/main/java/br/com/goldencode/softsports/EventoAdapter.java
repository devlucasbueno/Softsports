package br.com.goldencode.softsports;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.EventoViewHolder> {

    private ArrayList<Evento> mEventoArrayList;

    public static class EventoViewHolder extends RecyclerView.ViewHolder{

        LinearLayout linearLayout;
        public AppCompatTextView titulo;
        public AppCompatTextView esporte;
        public ImageButton imgButton;

        public EventoViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.tvTituloEvento);
            esporte = itemView.findViewById(R.id.tvEsporte);
            imgButton = itemView.findViewById(R.id.btnSetaPraBaixo);
            linearLayout = itemView.findViewById(R.id.layoutEsconder);

            imgButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (linearLayout.getVisibility() == View.GONE){
                        linearLayout.setVisibility(View.VISIBLE);
                        imgButton.setImageResource(R.drawable.ic_arrow_to_top);
                    } else {
                        linearLayout.setVisibility(View.GONE);
                        imgButton.setImageResource(R.drawable.ic_arrow_to_bottom);
                    }
                }
            });

        }
    }

    public EventoAdapter (ArrayList<Evento> eventoArrayList){

        mEventoArrayList = eventoArrayList;

    }

    @NonNull
    @Override
    public EventoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_evento, viewGroup, false);
        EventoViewHolder evh = new EventoViewHolder(v);

        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull EventoViewHolder holder, int i) {

        Evento evento = mEventoArrayList.get(i);

        holder.titulo.setText(evento.getNomeEvento());
        holder.esporte.setText(evento.getEsporte());

    }

    @Override
    public int getItemCount() {
        return mEventoArrayList.size();
    }

}
