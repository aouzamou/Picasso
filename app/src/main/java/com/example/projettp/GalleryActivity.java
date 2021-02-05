package com.example.projettp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private Adapter adapter;
    private StaggeredGridLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galery);

        recyclerView = findViewById(R.id.test);
        manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        List<row> lst = new ArrayList<>();
        lst.add(new row(R.drawable.first));
        lst.add(new row(R.drawable.second));

        adapter = new Adapter(this, lst);
        recyclerView.setAdapter(adapter);
    }
}
