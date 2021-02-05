package com.example.projettp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView imageView;
    Button btnRotateImage,btnResizeImage,btnFitImage,btnGallery;
    int i =0;
    String width, height;
    boolean stop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

        String url = "https://i.imgur.com/DvpvklR.png";
        imageView = (ImageView) findViewById(R.id.imageView);
        Picasso mPicasso = Picasso.with(this);
        mPicasso.setIndicatorsEnabled(true);
        mPicasso.with(this).load(url).into(imageView);

        btnRotateImage = (Button) findViewById(R.id.btnRotate);
        btnResizeImage = (Button) findViewById(R.id.btnResize);
        btnFitImage = (Button) findViewById(R.id.btnFit);
        btnGallery = (Button) findViewById(R.id.btnGallery);


        btnRotateImage.setOnClickListener(this);
        btnResizeImage.setOnClickListener(this);
        btnFitImage.setOnClickListener(this);
        btnGallery.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRotate:
                rotate();
                break;
            case R.id.btnResize:
                dialogBoxResize();
                resize();
                break;
            case R.id.btnFit:
                fit();
                break;
            case R.id.btnGallery:
                Toast.makeText(getApplicationContext(), "Ca marche", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void rotate(){
        if (i == 0){
            Picasso.with(this).load("https://i.imgur.com/DvpvklR.png").rotate(90f).into(imageView);
            i++;
        } else if (i == 1){
            Picasso.with(this).load("https://i.imgur.com/DvpvklR.png").rotate(180f).into(imageView);
            i++;
        } else if (i == 2){
            Picasso.with(this).load("https://i.imgur.com/DvpvklR.png").rotate(270f).into(imageView);
            i++;
        } else if (i == 3){
            Picasso.with(this).load("https://i.imgur.com/DvpvklR.png").rotate(360f).into(imageView);
            i = 0;
        }
    }

    private void resize(){
        if(width != null && height != null && !stop){

            String[] splitArray = null;
            splitArray = width.split(":");
            int w = Integer.parseInt(splitArray[1]);

            splitArray = height.split(":");
            int h = Integer.parseInt(splitArray[1]);

            if (i == 0) {
                Picasso.with(this).load("https://i.imgur.com/DvpvklR.png").resize(w,h).into(imageView);
            }else if (i == 1){
                Picasso.with(this).load("https://i.imgur.com/DvpvklR.png").rotate(90f).resize(w,h).into(imageView);
            }else if (i == 2){
                Picasso.with(this).load("https://i.imgur.com/DvpvklR.png").rotate(180f).resize(w,h).into(imageView);
            }else if (i == 3){
                Picasso.with(this).load("https://i.imgur.com/DvpvklR.png").rotate(270f).resize(w,h).into(imageView);
            }
        }
    }

    private void dialogBoxResize(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Comment souhaitez vous recadrer l'image ?");

        LinearLayout lila1= new LinearLayout(this);
        lila1.setOrientation(LinearLayout.VERTICAL); //1 is for vertical orientation
        final EditText input = new EditText(this);
        final EditText input1 = new EditText(this);
        lila1.addView(input);
        lila1.addView(input1);
        builder.setView(lila1);

        input.setText("Hauteur (pixels):");
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        //builder.setView(input);

        input1.setText("Largeur (pixels):");
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input1.setInputType(InputType.TYPE_CLASS_TEXT);
        //builder.setView(input2);

        // Set up the buttons
        builder.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                height = input.getText().toString();
                width = input1.getText().toString();

            }
        });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                stop = true;
            }
        });

        builder.show();
    }

    private void fit(){
        if (i == 0) {
            Picasso.with(this).load("https://i.imgur.com/DvpvklR.png").fit().into(imageView);
        }else if (i == 1){
            Picasso.with(this).load("https://i.imgur.com/DvpvklR.png").rotate(90f).fit().into(imageView);
        }else if (i == 2){
            Picasso.with(this).load("https://i.imgur.com/DvpvklR.png").rotate(180f).fit().into(imageView);
        }else if (i == 3){
            Picasso.with(this).load("https://i.imgur.com/DvpvklR.png").rotate(270f).fit().into(imageView);
        }
        Toast.makeText(getApplicationContext(), "Fit", Toast.LENGTH_SHORT).show();
    }
}