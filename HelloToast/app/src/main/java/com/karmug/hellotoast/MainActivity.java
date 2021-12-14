package com.karmug.hellotoast;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import basepackage.SemManager;


public class MainActivity extends AppCompatActivity
{

    private int count = 0;
    private TextView countText;
    private SemManager cseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countText = findViewById(R.id.textView_count);
        Button toastButton = findViewById(R.id.toast_button);
        toastButton.setOnClickListener(this::showToast);
        Button countButton = findViewById(R.id.count_button);
        countButton.setOnClickListener(this::count);
        Button zeroButton = findViewById(R.id.zero_button);
        zeroButton.setOnClickListener(this::zero);

    }

    private void showToast(View view)
    {
       Toast myToast = Toast.makeText(this,R.string.toast_message,Toast.LENGTH_SHORT);
       myToast.show();
    }

    private void zero(View view)
    {
        count=0;
        countText.setText(String.valueOf(count));
    }
    private void count(View view)
    {
        ++count;
        countText.setText(String.valueOf(count));
        InputStream inputStream = getResources().openRawResource(R.raw.csedata);
        cseManager = new SemManager(inputStream);
        //cseManager.getCGPAForOneSem(1);
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Log.i("MainActivity", String.valueOf());
    }

}