package com.cheekupeeku.testimplicitintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.cheekupeeku.testimplicitintent.databinding.HomeBinding;

public class MainActivity extends AppCompatActivity {
    HomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=  HomeBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) !=
                    PermissionChecker.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},
                        11);
            }
        }
        binding.btnOtherApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent("com.cheekupeeku.testappfirst.OTHER");
                startActivity(in);
            }
        });
        binding.btnDialer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String number = "tel:9009111222";
               Uri numberUri =  Uri.parse(number);
               Intent in = new Intent(Intent.ACTION_DIAL,numberUri);
               startActivity(in);
            }
        });
        binding.btnActionCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               try {
                   String number = "tel:9009111222";
                   Uri numberUri = Uri.parse(number);
                   Intent in = new Intent(Intent.ACTION_CALL, numberUri);
                   startActivity(in);
               }
               catch (SecurityException e){
                   Toast.makeText(MainActivity.this, "Please allow phone call permission", Toast.LENGTH_SHORT).show();
               }
            }
        });
        binding.btnBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Uri uri = Uri.parse("https://www.facebook.com");
               Intent in = new Intent(Intent.ACTION_VIEW,uri);
               startActivity(in);
            }
        });
        binding.btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent in = new Intent("android.media.action.IMAGE_CAPTURE");
              startActivity(in);
            }
        });
    }
}