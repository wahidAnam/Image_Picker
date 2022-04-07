package com.example.imagepicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;


import java.io.File;

public class MainActivity extends AppCompatActivity {

    private ImageView getImageIV;
    private LinearLayout selectedIV;
    TextView pictureTextTV;
    private File uploadFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedIV = findViewById(R.id.selectedIV);
        getImageIV = findViewById(R.id.getImageIV);
        pictureTextTV  = findViewById(R.id.pictureTextTV);

        selectedIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeImage();
            }

            private void takeImage() {
                ImagePicker.Companion.with(MainActivity.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            Uri uri =  data.getData();
            String path = uri.getPath();
            uploadFile = new File(path);
            pictureTextTV.setText( uploadFile.getName());
            if (uri != null){
                getImageIV.setImageURI(uri);
                getImageIV.setVisibility(View.VISIBLE);
            }else {
                getImageIV.setVisibility(View.GONE);
            }
        }
    }
}