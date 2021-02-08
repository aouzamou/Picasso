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
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // Définition des variables globales
    private ImageView imageView;
    private Button btnRotateImage,btnResizeImage,btnFitImage,btnGallery,btnSaveImage,btnValiderUrl;
    private int i =0;
    private String width, height;
    private boolean stop,centerCrop,centerInside;
    private EditText inputValue;
    private Picasso mPicasso;

    //On create: se déclenche au lancement de l'activité
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Accueil");
        setContentView(R.layout.activity_main);

        initView();

    }

    //Initialisation des variables
    private void initView() {
        mPicasso = Picasso.with(this);
        mPicasso.setIndicatorsEnabled(true);

        inputValue = (EditText) findViewById(R.id.url_txt);
        imageView = (ImageView) findViewById(R.id.imageView);
        btnRotateImage = (Button) findViewById(R.id.btnRotate);
        btnResizeImage = (Button) findViewById(R.id.btnResize);
        btnFitImage = (Button) findViewById(R.id.btnFit);
        btnGallery = (Button) findViewById(R.id.btnGallery);
        btnSaveImage = (Button) findViewById(R.id.btnSave);
        btnValiderUrl = (Button) findViewById(R.id.valider_btn);

        btnRotateImage.setOnClickListener(this);
        btnResizeImage.setOnClickListener(this);
        btnFitImage.setOnClickListener(this);
        btnGallery.setOnClickListener(this);
        btnSaveImage.setOnClickListener(this);
        btnValiderUrl.setOnClickListener(this);

        stop = false;
        centerCrop = false;
        centerInside = false;
    }

    //Déclenchement lors du clic sur un bouton
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.valider_btn:
                mPicasso.with(this).load(String.valueOf(inputValue.getText())).into(imageView);
                break;
            case R.id.btnRotate:
                rotate();
                break;
            case R.id.btnResize:
                dialogBoxResize();
                break;
            case R.id.btnFit:
                fit();
                break;
            case R.id.btnGallery:
                //Permet de passer à l'activité galerie
                Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
                startActivity(intent);
                break;
            case R.id.btnSave:
                //Permet de passer à l'activité galerie pour sauvegarder l'image en passant l'url de l'image
                Intent intentSave = new Intent(MainActivity.this, GalleryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url_image", String.valueOf(inputValue.getText()));
                intentSave.putExtras(bundle);
                startActivity(intentSave);
                break;
        }
    }

    //Permet de pivoter l'image de 90° vers la droite
    private void rotate(){
        if (i == 0){
            mPicasso.with(this).load(String.valueOf(inputValue.getText())).rotate(90f).into(imageView);
            i++;
        } else if (i == 1){
            mPicasso.with(this).load(String.valueOf(inputValue.getText())).rotate(180f).into(imageView);
            i++;
        } else if (i == 2){
            mPicasso.with(this).load(String.valueOf(inputValue.getText())).rotate(270f).into(imageView);
            i++;
        } else if (i == 3){
            mPicasso.with(this).load(String.valueOf(inputValue.getText())).rotate(360f).into(imageView);
            i = 0;
        }
    }

    // Permet de redimensionner l'image selon certains paramètres
    private void resize(){
        if(width != null && height != null && !stop){

            String[] splitArray = null;
            splitArray = width.split(":");
            int w = Integer.parseInt(splitArray[1]);

            splitArray = height.split(":");
            int h = Integer.parseInt(splitArray[1]);

            if (i == 0) {
                if(!centerCrop && !centerInside) {
                    mPicasso.with(this).load(String.valueOf(inputValue.getText())).resize(w, h).into(imageView);
                }else if(centerCrop = true){
                    mPicasso.with(this).load(String.valueOf(inputValue.getText())).resize(w,h).centerCrop().into(imageView);
                }else if (centerInside = true){
                    mPicasso.with(this).load(String.valueOf(inputValue.getText())).resize(w,h).centerInside().into(imageView);
                }
            }else if (i == 1){
                if(!centerCrop && !centerInside) {
                    mPicasso.with(this).load(String.valueOf(inputValue.getText())).rotate(90f).resize(w,h).into(imageView);
                }else if(centerCrop = true){
                    mPicasso.with(this).load(String.valueOf(inputValue.getText())).rotate(90f).resize(w,h).centerCrop().into(imageView);
                }else if (centerInside = true){
                    mPicasso.with(this).load(String.valueOf(inputValue.getText())).rotate(90f).resize(w,h).centerInside().into(imageView);
                }
            }else if (i == 2){
                if(!centerCrop && !centerInside) {
                    mPicasso.with(this).load(String.valueOf(inputValue.getText())).rotate(180f).resize(w,h).into(imageView);
                }else if(centerCrop = true){
                    mPicasso.with(this).load(String.valueOf(inputValue.getText())).rotate(180f).resize(w,h).centerCrop().into(imageView);
                }else if (centerInside = true){
                    mPicasso.with(this).load(String.valueOf(inputValue.getText())).rotate(180f).resize(w,h).centerInside().into(imageView);
                }
            }else if (i == 3){
                if(!centerCrop && !centerInside) {
                    mPicasso.with(this).load(String.valueOf(inputValue.getText())).rotate(270f).resize(w,h).into(imageView);
                }else if(centerCrop = true){
                    mPicasso.with(this).load(String.valueOf(inputValue.getText())).rotate(270f).resize(w,h).centerCrop().into(imageView);
                }else if (centerInside = true){
                    mPicasso.with(this).load(String.valueOf(inputValue.getText())).rotate(270f).resize(w,h).centerInside().into(imageView);
                }
            }
        }
    }

    //Boite de dialogue pour renseigner les paramètres utiles au redimensionnement de l'image
    private void dialogBoxResize(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Comment souhaitez vous recadrer l'image ?");

        LinearLayout lila1= new LinearLayout(this);
        final EditText input = new EditText(this);
        final EditText input1 = new EditText(this);
        lila1.addView(input);
        lila1.addView(input1);

        RadioButton btn1 = new RadioButton(this);
        RadioButton btn2 = new RadioButton(this);
        lila1.addView(btn1);
        lila1.addView(btn2);

        builder.setView(lila1);

        input.setText("Hauteur (pixels):");
        input.setInputType(InputType.TYPE_CLASS_TEXT);

        input1.setText("Largeur (pixels):");
        input1.setInputType(InputType.TYPE_CLASS_TEXT);

        btn1.setText("CenterCrop");
        btn2.setText("CenterInside");

        builder.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                height = input.getText().toString();
                width = input1.getText().toString();
                if(btn1.isChecked()){
                    centerCrop = true;
                }else if (btn2.isChecked()){
                    centerInside = true;
                }
                resize();
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

    //Permet de remplir l'imageView avec l'image
    private void fit(){
        if (i == 0) {
            mPicasso.with(this).load(String.valueOf(inputValue.getText())).fit().into(imageView);
        }else if (i == 1){
            mPicasso.with(this).load(String.valueOf(inputValue.getText())).rotate(90f).fit().into(imageView);
        }else if (i == 2){
            mPicasso.with(this).load(String.valueOf(inputValue.getText())).rotate(180f).fit().into(imageView);
        }else if (i == 3){
            mPicasso.with(this).load(String.valueOf(inputValue.getText())).rotate(270f).fit().into(imageView);
        }
    }
}