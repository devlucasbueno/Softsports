package br.com.goldencode.softsports;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.EventoViewHolder> {

    private Context mContext;
    private Cursor mCursor;


    public EventoAdapter(Context context, Cursor cursor) {

        mContext = context;
        mCursor = cursor;

    }

    public static class EventoViewHolder extends RecyclerView.ViewHolder{

        LinearLayout linearLayout;
        public ImageButton imgButton;

        public AppCompatTextView titulo;
        public AppCompatTextView esporte;
        public AppCompatTextView local;
        public AppCompatTextView dataEvento;
        public AppCompatTextView hrInicio;
        public AppCompatTextView hrTermino;
        public AppCompatTextView descricao;


        public EventoViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.tvTituloEvento);
            esporte = itemView.findViewById(R.id.tvEsporte);
            local = itemView.findViewById(R.id.tvLocalEvento);
            dataEvento = itemView.findViewById(R.id.tvDataDoEvento);
            hrInicio = itemView.findViewById(R.id.tvHoraInicio);
            hrTermino = itemView.findViewById(R.id.tvHoraTermino);
            descricao = itemView.findViewById(R.id.tvDescricao);

            //Onclick botões
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

    @NonNull
    @Override
    public EventoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_evento, viewGroup, false);
        EventoViewHolder evh = new EventoViewHolder(v);

        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull EventoViewHolder holder, int i) {

        if (!mCursor.move(i)){
            return;
        }

        String titulo = mCursor.getString(mCursor.getColumnIndex(EventoContract.EventoEntry.TITULO_EVENTO));
        String esporte = mCursor.getString(mCursor.getColumnIndex(EventoContract.EventoEntry.ESPORTE));
        String local = mCursor.getString(mCursor.getColumnIndex(EventoContract.EventoEntry.LOCAL));
        String dataEvento = mCursor.getString(mCursor.getColumnIndex(EventoContract.EventoEntry.DT_EVENTO));
        String hrInicio = mCursor.getString(mCursor.getColumnIndex(EventoContract.EventoEntry.HR_INICIO));
        String hrTermino = mCursor.getString(mCursor.getColumnIndex(EventoContract.EventoEntry.HR_TERMINO));
        String descricao = mCursor.getString(mCursor.getColumnIndex(EventoContract.EventoEntry.DESCRICAO));

        holder.titulo.setText(titulo);
        holder.esporte.setText(esporte);
        holder.local.setText(local);
        holder.dataEvento.setText(dataEvento);
        holder.hrInicio.setText(hrInicio);
        holder.hrTermino.setText(hrTermino);
        holder.descricao.setText(descricao);

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor){

        if (mCursor != null){
            mCursor.close();
        }

        mCursor = newCursor;

        if (newCursor != null){
            notifyDataSetChanged();
        }

    }
}
