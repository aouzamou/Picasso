package com.example.projettp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        String lstUrl = sharedPreferences.getString("ListeUrl", "");

        List<String> list = new ArrayList<>();
        if (lstUrl != ""){
            String[] items = lstUrl.split(",");
            for(int i=0; i < items.length; i++){
                list.add(items[i]);
            }
        }

        Intent intentSave = getIntent();
        if (intentSave != null) {
            if (intentSave.hasExtra("url_image")) {
                url = intentSave.getStringExtra("url_image");
                list.add(url);
            }
        }

        recyclerView = findViewById(R.id.Recycler);
        manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        //Charger preference et instancier la liste en parcourant la chaine
        List<row> lst = new ArrayList<>();
        for(int i=0; i < list.size(); i++){
            lst.add(new row(list.get(i)));
        }

        adapter = new Adapter(this, lst);
        recyclerView.setAdapter(adapter);

        initView();

        StringBuilder csvList2 = new StringBuilder();
        for(String s : list){
            csvList2.append(s);
            csvList2.append(",");
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ListeUrl", csvList2.toString());
        editor.apply();
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
