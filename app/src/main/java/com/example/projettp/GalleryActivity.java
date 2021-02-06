package com.example.projettp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    private Adapter adapter;
    private StaggeredGridLayoutManager manager;
    private Button btnReturn;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galery);

        Intent intentSave = getIntent();
        if (intentSave != null) {
            if (intentSave.hasExtra("url_image")) {
                url = intentSave.getStringExtra("url_image");
            }
        }


        recyclerView = findViewById(R.id.Recycler);
        manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        List<row> lst = new ArrayList<>();
        lst.add(new row(url));
        //lst.add(new row(R.drawable.first));
        //lst.add(new row(R.drawable.second));

        adapter = new Adapter(this, lst);
        recyclerView.setAdapter(adapter);

        initView();
    }

    private void initView() {
        btnReturn = (Button) findViewById(R.id.btnBack);
        btnReturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                Intent intent = new Intent(GalleryActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
