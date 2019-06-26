package br.com.goldencode.softsports;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentEventosCriados extends Fragment {

    private Cursor getAllItems(SQLiteDatabase mDatabase){
        return mDatabase.query(
                EventoContract.EventoEntry.TABELA_EVENTO,
                null,
                null,
                null,
                null,
                null,
                EventoContract.EventoEntry.TIMESTAMP + " DESC"
        );
    }

    View view;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    public FragmentEventosCriados() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.eventos_criados_fragment, container, false);

        Softsports dbHelper = new Softsports(getActivity());
        SQLiteDatabase mDatabase = dbHelper.getWritableDatabase();

        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new EventoAdapter(getActivity(), getAllItems(mDatabase));
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }
}
