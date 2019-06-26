package br.com.goldencode.softsports;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

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
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        Softsports dbHelper = new Softsports(getActivity());
        SQLiteDatabase mDatabase = dbHelper.getWritableDatabase();

        mRecyclerView = view.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        ((LinearLayoutManager) mLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        mAdapter = new EventoAdapter(getActivity(), getAllItems(mDatabase));

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        return view;

    }

}
